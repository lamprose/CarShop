<template>
  <div style="width: 100%">
    <div style="margin: auto;width: 260px">
    <img src="../assets/congratulations.png" style="width: 260px">
    </div>
    <el-steps :active="activeStep" finish-status="success" class="stepStatus">
      <el-step title="填写用户名" description="填写忘记密码的用户名"></el-step>
      <el-step title="身份验证" description="选择密保问题并验证密保答案以确认身份"></el-step>
      <el-step title="设置新密码" description="设置新的密码"></el-step>
      <el-step title="完成并登陆" description="恭喜你！修改密码完成"></el-step>
    </el-steps>
    <div style="width: 100%">
      <div class="mainBox" v-if="activeStep===0">
        <label>用户名:</label>
        <el-input placeholder="请输入用户名" v-model="newUser.id"></el-input>
        <drag-verify v-if="identify.show" style="margin: 30px auto" text="拉拽到右边以验证" :width="identify.width" :height="identify.height" success-text="验证成功" ref="Verity"></drag-verify>
        <div class="submitButton">
          <el-button round type="primary" @click="firstToSecond">下一步</el-button>
        </div>
      </div>
      <div class="mainBox" v-else-if="activeStep===1">
        <label>密保问题:</label>
        <el-select v-model="newUser.secretOption" placeholder="请选择" style="width: 350px">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select><br><br>
        <label>密保答案:</label>
        <el-input placeholder="请输入密保答案" v-model="newUser.secret"></el-input>
        <div class="submitButton">
          <el-button round type="primary" @click="secondToThird">提交验证</el-button>
        </div>
      </div>
      <div class="mainBox" v-else-if="activeStep===2">
        <div>
          <label>输入新密码:</label>
          <el-input v-model="newUser.password" :type="showPasswordType" class="input">
            <i
              class="el-icon-view"
              slot="suffix"
              @click="showPasswordChange">
            </i>
          </el-input>
        </div><br>
        <div>
          <label>确认新密码:</label>
          <el-input v-model="rePassword" :type="showPasswordType" class="input">
            <i
              class="el-icon-view"
              slot="suffix"
              @click="showPasswordChange">
            </i>
          </el-input>
          <el-alert v-if="showAlertPassword"
                    title="密码不一致"
                    type="error" :closable="false" show-icon style="height: 30px;width: 350px;margin-top: 10px">
          </el-alert>
        </div>
        <div class="submitButton">
          <el-button round type="primary" @click="thirdToCompleted">提交</el-button>
        </div>
      </div>
      <div class="mainBox" v-else>
        <img src="../assets/congratulations.png" width="200px">
        恭喜！修改密码成功
        <div class="submitButton">
          <el-button round type="primary" @click="thirdToCompleted">{{contentAutoLogin}}</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import DragVerify from "vue-drag-verify/src/dragVerify";
    export default {
      name: "ForgotPassword",
      components: {DragVerify},
      data() {
        return {
          activeStep:0,
          //自动登陆按钮内容
          contentAutoLogin:'s后自动登陆',
          //倒计时时间
          totalTime:5,
          canClick:true,
          //重复密码框里的数据
          rePassword:'',
          //控制密码输入框下错误是否显示
          showAlertPassword:false,
          //控制是否显示密码
          showPassword:true,
          //控制密码输入框的类型
          showPasswordType:'password',
          //控制验证进度条属性
          identify: {
            show: true,
            width: 350,
            height: 40
          },
          newUser: {
            id: '',
            password: '',
            secretOption:null,
            secret:''
          },
          user:{},
          options:[{
            value:0,
            label:'您初中班主任的名字是？'
          },{
            value:1,
            label:'您的出生地是？'
          },{
            value:2,
            label:'您的学号（或工号）是？'
          },{
            value:3,
            label:'您父亲的生日是？'
          },{
            value:4,
            label:'您高中班主任的名字是？'
          },{
            value:5,
            label:'您母亲的生日是？'
          },{
            value:6,
            label:'您小学班主任的名字是？'
          }],
          value:'',
        }
      },
      methods:{
        firstToSecond(){
          if(this.newUser.id===''){
            this.$message.error({
              message:"用户名不能为空",
              showClose:true
            });
            return
          }
          if(!this.$refs.Verity.isPassing){
            this.$message.error({
              message:"验证失败,请拉拽到右边以验证",
              showClose:true
            });
            return
          }
          this.$ajax.get('http://localhost:8080/user/login/?id='+this.newUser.id)
            .then((response)=>{
              if(response.data.length===0){
                this.$message.error({
                  message:"用户名不存在，3s后自动刷新",
                  showClose:true
                })
                setTimeout(function () {
                  location.reload()
                },3000)
                return
            }else if(!response.data['secretOption']){
                this.$message.error({
                  message:"未设置密保问题，无法找回密码",
                  showClose:true
                })
                setTimeout(function () {
                  location.reload()
                },3000)
              }else{
                console.log(response.data)
                this.user=response.data
                this.activeStep=1
              }
            })
        },
        secondToThird(){
          console.log(this.user)
          if(this.newUser.secretOption==null){
            this.$message.error({
              message:"请选择密保问题",
              showClose:true
            });
            console.log(this.newUser.secretOption)
            return
          }
          if(this.newUser.secret===''){
            this.$message.error({
              message:"请填写密保答案",
              showClose:true
            });
            return
          }
          if(this.user.secretOption===this.newUser.secretOption&&this.user.secret===this.newUser.secret)
            this.activeStep=2
          else{
            this.$message.error({
              message:"密保答案错误",
              showClose:true
            });
            this.newUser.secretOption=null
            this.newUser.secret=null
          }
        },
        thirdToCompleted(){
          if(this.showAlertPassword)
            return
          this.$ajax.get('http://localhost:8080/user/updateOnePassword/?id='+this.newUser.id+"&password="+this.newUser.password)
            .then((response)=>{
              if(response.data==='success'){
                this.activeStep=3
                if(this.activeStep===3)
                  this.countDown()
              }
            })
        },
        showPasswordChange(){
          if(this.showPasswordType==='password')
            this.showPasswordType='text'
          else
            this.showPasswordType='password'
          this.showPassword=!this.showPassword
          if(!this.showPassword)
            this.showPassword=!this.showPassword
          console.log(this.showPassword)
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
        rePassword() {
          if(this.rePassword!==this.newUser.password)
            this.showAlertPassword=true
          else
            this.showAlertPassword=false
          //console.log(this.rePassword,this.newUser.password)
        },
        totalTime(){
          if(this.totalTime<0){
            //将用户名和token放入sessionStorage
            sessionStorage.setItem("userId",this.user.id)
            sessionStorage.setItem("user",JSON.stringify(this.user))
            console.log("in login image is " + sessionStorage.getItem("user"))
            //将用户信息放入vuex
            this.$store.commit("setUser",this.user)
            this.$store.commit("changeLoginStatus")
            if(this.user.flag===0)
              this.$router.push({name:'Home'})
            else
              this.$router.push({name:'Admin'})
          }
        }
      },
      computed:{

      }
    }
</script>

<style scoped>
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
</style>
