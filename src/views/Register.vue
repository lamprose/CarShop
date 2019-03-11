<template>
  <div class="register" >
    <el-form ref="registerUser" status-icon :rules="registerRule" :model="registerUser" label-width="80px" label-position="left" class="main">
      <el-form-item label="用户名" prop="id">
        <el-input v-model="registerUser.id" class="input"></el-input><br>
      </el-form-item>
      <el-form-item label="登陆密码" prop="password" v-if="showPassword">
        <el-input v-model="registerUser.password" :type="showPasswordType" class="input" @change="testPasswordStrength" id="password">
          <i class="el-icon-view" slot="suffix" @click="showPasswordChange"></i>
        </el-input><br>
        <label class="placement" v-if="pwd" style="float: left">密码强度：</label>
        <el-rate
          v-model="passwordStrength"
          :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
          disabled
          show-score
          v-if="pwd" style="float: left;margin-top: 10px">
        </el-rate>
      </el-form-item>
      <el-form-item label="确认密码" prop="rePassword" v-if="showPassword">
        <el-input v-model="registerUser.rePassword" :type="showPasswordType" class="input">
          <i class="el-icon-view" slot="suffix" @click="showPasswordChange"></i>
        </el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="identifyText">
        <div style="float: left">
          <el-input style="width: 189px" v-model="registerUser.identifyText"></el-input>
        </div>
        <div style="float: left" @click="refreshCode">
          <identify :identify-code="identifyInfo.identifyCode"></identify>
          <label id="identifyCode" style="display: none">{{identifyInfo.identifyCode}}</label>
        </div>
      </el-form-item>
      <el-form-item prop="checked">
        <el-checkbox v-model="registerUser.checked">
          我已阅读并同意<el-button @click="argumentShow=true" type="text" style="text-decoration: underline;">《交易条款》</el-button>
        </el-checkbox>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('registerUser')" round>立即注册</el-button>
        <el-button type="primary" @click="resetForm('registerUser')" round>重置</el-button>
      </el-form-item>
    </el-form>

    <el-dialog :visible.sync="argumentShow" title="交易条款" center width="50%">
      <div style="width:80%;margin: 30px auto">
        {{argument}}
      </div>
      <span slot="footer">
        <el-button type="primary" round @click="argumentShow=false">确认</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import identify from '../tool/Identify'
  import rule from "../tool/rule";
  export default {
    components: {identify},
    data() {
      let checkChecked=(rule, value, callback) => {
        if(!this.registerUser.checked){
          return callback(new Error('需同意交易条款才能继续注册'));
        }else
          return callback();
      }
      return {
        registerRule:{
          id:rule.ID,
          password:rule.Password,
          rePassword:rule.RePassword,
          identifyText:rule.Identify,
          checked:[
            { validator: checkChecked, trigger: 'change' },
          ]
        },
        identifyInfo:{
          //验证码随机出现的验证码
          identifyCodes:"0123456789qwertyuiopasdfghjklzxcvbnm",
          //验证码控件的验证码数据
          identifyCode:'',
        },
        passwordStrength:null,
        //控制是否显示密码
        showPassword:true,
        //控制密码输入框的类型
        showPasswordType:'password',
        //控制交易条款弹窗是否显示
        argumentShow:false,
        //交易条款内容
        argument:'  此网页上的商品由XXXX有限公司（“XXXX公司”）或其关联公司或第三方卖家销售。如无相反表示，本交易条款中的“XXX”为北京XXX公司及其关联公司的统称，亦包括其所运营的网站。如果您在此网页购物，您便接受了以下交易条款。请仔细阅读这些条款。当您使用此网页目前或将来提供的服务（例如，我的账户、礼品卡、VIP会员、商店街等）时，您同时应接受适用于那些服务的条款、准则和条件（“特殊条款”）；如果以下交易条款与特殊条款有不一致之处，则以特殊条款为准。通过在XXX购买商品和/或使用其提供的服务，您同意接受本交易条款和所有有关的政策、条件和准则的约束。如果您不同意本交易条款中的任何一条，您可以选择不在此网页购买商品或接受服务。',
        registerUser:{
          id:'',
          password:'',
          rePassword:'',
          identifyText:'',
          checked:true,
        },
        //定义用户对象
        user: {
          address:null,
          flag:null,
          id:'',
          name:null,
          password:'',
          phone:null,
          sex:null,
          uId:null,
          secretOption:null,
          secret:null
        }
      }
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            //TODO : 验证数据成功，实现注册
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      showPasswordChange(){
        if(this.showPasswordType==='password')
          this.showPasswordType='text'
        else
          this.showPasswordType='password'
        this.showPassword=!this.showPassword
        if(!this.showPassword)
          this.showPassword=!this.showPassword
        //console.log(this.showPassword)
      },
      refreshCode() {
        this.identifyInfo.identifyCode = "";
        this.makeCode(this.identifyInfo.identifyCodes, 4);
        this.registerUser.identifyText=''
      },
      makeCode(o, l) {
        for (let i = 0; i < l; i++) {
          this.identifyInfo.identifyCode += this.identifyInfo.identifyCodes[
            this.randomNum(0, this.identifyInfo.identifyCodes.length)
            ];
        }
        console.log(this.identifyInfo.identifyCode);
      },
      // 生成一个随机数
      randomNum (min, max) {
        return Math.floor(Math.random() * (max - min) + min)
      },
      testPasswordStrength(){
        if(this.registerUser.password.length<6){
          this.passwordStrength=0;
          return
        }
        let findFlag=false;
        let leval=[
          [/[a-z]{6,}$/, /[A-Z]{6,}$/, /[0-9]{6,}$/],
          [/[a-zA-Z]{6,}$/],
          [/[0-9a-z]{6,}$/],
          [/[0-9A-Z]{6,}$/],
          [/[a-zA-Z0-9]{6,}$/]
        ]
        for(let i=0;i<leval.length;i++){
          if(findFlag) {
            this.passwordStrength = i;
            return;
          }
          leval[i].forEach((item)=>{
            console.log(item+":")
            //console.log(item.exec(this.registerUser.password))
            if(item.test(this.registerUser.password)){
              findFlag=true
            }
          })
        }
        if(findFlag){
          this.passwordStrength =  5;
          return;
        }
        this.passwordStrength = 0
      }
    },
    watch:{
      pwd() {
        this.testPasswordStrength();
      }
    },
    mounted() {
      this.refreshCode()
    },
    computed:{
      pwd(){
        return this.registerUser.password
      },
      userId(){
        return this.user.id
      }
    }
  }
</script>

<style scoped>
  .input{
    width: 300px;
  }
  .main{
    width: 400px;
    margin: 100px auto;
  }

  .placement{
    color:#969696;
    margin-left: 0px;
    margin-right: 0px;
    font-size: 1px;
  }
</style>
