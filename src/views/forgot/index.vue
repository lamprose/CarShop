<template>
  <div style="width: 100%">
    <div style="margin: auto;width: 260px">
    <img src="../../assets/congratulations.png" style="width: 260px">
    </div>
    <el-steps :active="activeStep" finish-status="success" class="stepStatus" simple>
      <el-step title="填写用户名"></el-step>
      <el-step title="密保验证"></el-step>
      <el-step title="设置新密码"></el-step>
      <el-step title="完成并登陆"></el-step>
    </el-steps>
    <div style="width: 100%">
      <el-form class="mainBox" :model="newUser" ref="form"  label-position="left" label-width="100px" :rules="formRule">
        <div v-if="activeStep===0" style="width: 400px">
          <el-form-item label="用户名:" prop="id">
            <el-input placeholder="请输入用户名" v-model="newUser.id"></el-input>
          </el-form-item>
          <drag-verify v-if="identify.show" style="margin: 30px auto" text="拉拽到右边以验证" :width="identify.width" :height="identify.height" success-text="验证成功" ref="Verify"></drag-verify>
          <el-button round type="primary" @click="firstToSecond">下一步</el-button>
        </div>
        <div v-if="activeStep===1">
          <div v-for="(o,index) in newUser.secret">
            <el-form-item label="密保问题:" :prop="'secret['+index+'].question'">
              <el-select v-model="o.question" placeholder="请选择" style="width: 350px" @change="optionChange">
                <el-option v-for="item in nowOptions" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="密保答案:" :prop="'secret['+index+'].answer'">
              <el-input placeholder="请输入密保答案" v-model="o.answer"></el-input>
            </el-form-item>
          </div>
          <el-button round type="primary" @click="secondToThird">提交验证</el-button>
        </div>
        <div v-if="activeStep===2">
          <el-form-item prop="password" label="输入新密码:" class="input-form">
            <el-input v-model="newUser.password" id="password" :show-password="true"></el-input>
          </el-form-item>
          <el-form-item prop="rePassword" label="确认新密码:" class="input-form">
            <el-input v-model="newUser.rePassword" :show-password="true"></el-input>
          </el-form-item>
          <div class="submitButton">
            <el-button round type="primary" @click="thirdToCompleted">提交</el-button>
          </div>
        </div>
      </el-form>
      <div class="mainBox" v-if="activeStep===3">
        <img src="../../assets/congratulations.png" width="200px">
        恭喜！修改密码成功
        <div class="submitButton">
          <el-button round type="primary" @click="thirdToCompleted">{{contentAutoLogin}}</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import DragVerify from "@/components/DragVerify";
  import rule from "@/utils/rule";
  import {checkHaveSecret,checkCorrectSecret,changePassword} from "@/api/security";
  import {encryptMd5} from "@/utils/encrypt";

  export default {
      name: "ForgotPassword",
      components: {DragVerify},
      data() {
        return {
          formRule:{
            id:[
              {required:true,message:'请输入ID',trigger:['blur','change']}
            ],
            password:rule.Password,
            rePassword:rule.RePassword,
            'secret[0].question':rule.Question,
            'secret[1].question':rule.Question,
            'secret[2].question':rule.Question,
            'secret[0].answer':rule.Answer,
            'secret[1].answer':rule.Answer,
            'secret[2].answer':rule.Answer,
          },
          activeStep:0,
          //自动登陆按钮内容
          contentAutoLogin:'s后自动登陆',
          //倒计时时间
          totalTime:5,
          canClick:true,
          //控制验证进度条属性
          identify: {
            show: true,
            width: 350,
            height: 40
          },
          newUser: {
            id: '',
            password: '',
            rePassword:'',
            secret:[
              {question:null,answer:''},
              {question:null,answer:''},
              {question:null,answer:''}
            ]
          },
          user:{},
          options:[
            {value:0, label:'您初中班主任的名字是？'},
            {value:1, label:'您的出生地是？'},
            {value:2, label:'您的学号（或工号）是？'},
            {value:3, label:'您父亲的生日是？'},
            {value:4, label:'您高中班主任的名字是？'},
            {value:5, label:'您母亲的生日是？'},
            {value:6, label:'您小学班主任的名字是？'}],
          nowOptions:[],
          value:'',
        }
      },
      methods:{
        optionChange(event){
          let now=this.options
          this.newUser.secret.forEach(item=>{
            now=now.filter(optionItem=>optionItem.value!==item.question)
          })
          this.nowOptions=now
        },
        firstToSecond(){
          this.$refs['form'].validate(valid=>{
              if(valid){
                if(!this.$refs.Verify.isPassing){
                  this.$message.error({
                    message:"验证失败,请拉拽到右边以验证",
                    showClose:true
                  });
                }else{
                  //忘记密码第一步验证用户名是否存在以及用户密保问题是否设置
                  checkHaveSecret(this.newUser.id).then(data=>{
                    if(data==='success')
                      this.activeStep=1
                    else{
                      this.$message.error({
                        message:"用户不存在或未设置密保问题",
                        showClose:true
                      });
                      this.$refs.Verify.init()
                      this.$refs['form'].resetFields()
                      console.log("123")
                    }
                  })

                }
              }else{
                this.$refs.Verify.init()
              }

          })
        },
        secondToThird(){
          this.$refs['form'].validate(valid=>{
            if(valid){
              //验证密保问题答案是否正确
              checkCorrectSecret(this.newUser).then(data=>{
                if(data==='success')
                  this.activeStep=2;
                else
                  this.$message.error({
                    message:'密保问题错误',
                    showClose:true
                  })
              })
            }
          })
        },
        thirdToCompleted(){
          this.$refs['form'].validate((valid) => {
            if (valid) {
              //验证新密码格式并提交数据修改到数据库
              this.$store.dispatch('updatePasswordBySecret',{id:this.newUser.id,password:encryptMd5(this.newUser.password)}).then(()=>{
                this.activeStep=3;
                this.countDown()
              }).catch(err=>{
                this.$message.error({
                  message:err,
                  showClose:false
                })
              })
            } else {
              console.log('error submit!!');
              return '0';
            }
          });
        },
        countDown () {
          if (!this.canClick) return  //改动的是这两行代码
          this.canClick = false
          this.totalTime=5
          let content =  this.contentAutoLogin
          this.contentAutoLogin = this.totalTime + content
          let clock = window.setInterval(() => {
            this.totalTime--
            this.contentAutoLogin = this.totalTime + content
            if (this.totalTime < 0) {
              window.clearInterval(clock)
              this.canClick = true
            }
          },1000)
        }
      },
      watch:{
        totalTime(){
          if(this.totalTime<0){
            this.$router.push({path:'/home'})
          }
        }
      },
      mounted() {
        this.nowOptions=this.options
      }
  }
</script>

<style lang="less" scoped>
  .stepStatus{
    margin-top: 30px;
  }
  .mainBox{
    width: 350px;
    margin:100px auto;
  }
  .submitButton{
    width: 90px;
    margin: 20px auto;
  }
  li.el-select-dropdown__item{
    float: none;
  }
  .el-input{
    width: 350px;
  }
</style>
