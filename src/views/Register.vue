<template>
  <div class="register" >
    <el-form ref="form" :model="user" label-width="80px" label-position="left" class="main">
      <el-form-item label="用户名">
        <el-input v-model="user.id" class="input" @blur="checkId"></el-input><br>
        <label class="placement">用户名必须以字母开头且为6~20位的字母数字的字符串</label>
        <el-alert v-if="showAlertId"
                  :title="msgId"
                  type="error" :closable="false" show-icon style="height: 30px;width: 300px;margin-top: 10px">
        </el-alert>
      </el-form-item>
      <el-form-item label="登陆密码" v-if="showPassword">
        <el-input v-model="user.password" :type="showPasswordType" class="input">
          <i
            class="el-icon-view"
            slot="suffix"
            @click="showPasswordChange">
          </i>
        </el-input><br>
        <label class="placement">密码只能是数字、大小写字母混合的至少6位字符串</label><br>
        <label class="placement" v-if="pwd" style="float: left">密码强度：</label>
        <el-rate
          v-model="passwordStrength"
          :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
          disabled
          show-score
          v-if="pwd" style="float: left;margin-top: 10px">
        </el-rate>
      </el-form-item>
      <el-form-item label="确认密码" v-if="showPassword">
        <el-input v-model="rePassword" :type="showPasswordType" class="input">
          <i
            class="el-icon-view"
            slot="suffix"
            @click="showPasswordChange">
          </i>
        </el-input>
        <el-alert v-if="showAlertPassword"
          :title="msgPassword"
          type="error" :closable="false" show-icon style="height: 30px;width: 300px;margin-top: 10px">
        </el-alert>
      </el-form-item>
      <el-form-item label="验证码">
        <div style="float: left">
          <el-input style="width: 189px" v-model="identifyText"></el-input>
        </div>
        <div style="float: left" @click="refreshCode">
        <identify :identify-code="identifyInfo.identifyCode"></identify>
        </div>
        <el-alert v-if="showAlertIdentify"
                  :title="msgIdentify"
                  type="error" :closable="false" show-icon style="height: 30px;width: 300px;margin-top: 10px">
        </el-alert>
      </el-form-item>
      <el-form-item>
        <el-checkbox v-model="checked">
          我已阅读并同意<el-button @click="argumentShow=true" type="text" style="text-decoration: underline;">《交易条款》</el-button>
        </el-checkbox>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit" round style="margin-left: 60px">立即注册</el-button>
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
  export default {
    components: {identify},
    data() {
      return {
        identifyInfo:{
          //验证码随机出现的验证码
          identifyCodes:"0123456789qwertyuiopasdfghjklzxcvbnm",
          //验证码控件的验证码数据
          identifyCode:'',
        },
        //验证码输入框内容
        identifyText:'',
        //重复密码框里的数据
        rePassword:'',
        passwordStrength:null,
        //控制是否显示密码
        showPassword:true,
        //控制密码输入框的类型
        showPasswordType:'password',
        //是否选中同意条款
        checked:true,
        //控制用户名输入框下错误是否显示
        showAlertId:false,
        //控制密码输入框下错误是否显示
        showAlertPassword:false,
        //控制验证码输入框下错误是否显示
        showAlertIdentify:false,
        //用户名输入框下错误内容
        msgId:'',
        //密码输入框下错误内容
        msgPassword:'',
        //验证码输入框下错误内容
        msgIdentify:'',
        //控制交易条款弹窗是否显示
        argumentShow:false,
        //交易条款内容
        argument:'  此网页上的商品由XXXX有限公司（“XXXX公司”）或其关联公司或第三方卖家销售。如无相反表示，本交易条款中的“XXX”为北京XXX公司及其关联公司的统称，亦包括其所运营的网站。如果您在此网页购物，您便接受了以下交易条款。请仔细阅读这些条款。当您使用此网页目前或将来提供的服务（例如，我的账户、礼品卡、VIP会员、商店街等）时，您同时应接受适用于那些服务的条款、准则和条件（“特殊条款”）；如果以下交易条款与特殊条款有不一致之处，则以特殊条款为准。通过在XXX购买商品和/或使用其提供的服务，您同意接受本交易条款和所有有关的政策、条件和准则的约束。如果您不同意本交易条款中的任何一条，您可以选择不在此网页购买商品或接受服务。',
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
      //提交注册
      onSubmit:async function() {
        if(this.user.id===''){
          this.msgId='用户名不能为空'
          this.showAlertId=true
          this.refreshCode()
          return
        }
        if (this.user.password===''){
          this.msgPassword='密码不能为空'
          this.showAlertPassword=true
          this.refreshCode()
          return
        }
        let rex=/^[a-zA-Z][a-zA-Z0-9]{5,15}$/
        if(!rex.test(this.user.id)){
          this.msgId='用户名不合法'
          this.showAlertId=true
          this.refreshCode()
          return
        }
        if (this.passwordStrength===0){
          this.msgPassword='密码强度不够'
          this.showAlertPassword=true
          this.refreshCode()
          return
        }
        if (this.identifyText===''){
          this.msgIdentify='验证码不能为空'
          this.showAlertIdentify=true
          this.refreshCode()
          return
        }
        if(!this.checked){
          this.$message.error({
            message:"需同意交易条款才能注册成功",
            showClose:true
          })
          this.refreshCode()
          return
        }
        if(this.showAlertId||this.showAlertPassword||this.showAlertIdentify)
          return
        let data = this.user;
        //console.log(data);
        this.$ajax.post('http://localhost:8080/user/saveOne/', JSON.stringify(data),
          {
            headers: { 'Content-Type': 'application/json;charset=UTF-8'}
          }
        ).then(response=> {
          //console.log(response);
          sessionStorage.setItem("user",JSON.stringify(this.user))
          sessionStorage.setItem("userId",this.user.id)
          this.$store.commit("setUser",this.user)
          this.$store.commit("changeLoginStatus")
          if(sessionStorage.getItem("userId")===this.user.id){
            this.$message.success({
              message:"注册成功",
              showClose:true
            })
            this.$router.push({name:'Home'})
          }
          //console.log('submit!');
        }).catch(function (error){
          //console.log(error)
          //console.log("save failed！")
        });
      },
      //判断用户名是否已存在
      checkId(){
        let data = this.user;
        //console.log(data);
        this.$ajax.get('http://localhost:8080/user/login/?id='+this.user.id)
          .then((response)=> {
            //console.log(response.data)
            if(response.data.length!==0){
              this.msgId='该用户名已被注册，请重新输入'
              this.showAlertId=true
            }else
              this.showAlertId=false
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
          //console.log(this.showPassword)
        },
      refreshCode() {
        this.identifyInfo.identifyCode = "";
        this.makeCode(this.identifyInfo.identifyCodes, 4);
        this.identifyText=''
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
      testPasswordStrength(str){
        if(str.length<6)
          return 0
        let findFlag=false
        let leval=[
          [
            /[a-z]{6,}$/,
            /[A-Z]{6,}$/,
            /[0-9]{6,}$/
          ],
          [
            /[a-zA-Z]{6,}$/
          ],
          [
            /[0-9a-z]{6,}$/
          ],
          [
            /[0-9A-Z]{6,}$/
          ],
          [
            /[a-zA-Z0-9]{6,}$/
          ]
        ]
        for(let i=0;i<leval.length;i++){
          if(findFlag)
            return i
          leval[i].forEach((item)=>{
            //console.log(item+":")
            //console.log(item.exec(str))
            if(item.test(str)){
              findFlag=true
            }
          })
        }
        if(findFlag)
          return 5
        return 0
      }
    },
    mounted() {
      this.refreshCode()
    },
    watch:{
      rePassword() {
        if(this.rePassword!==this.user.password){
          this.showAlertPassword=true
          this.msgPassword='密码不一致'
        }
        else
          this.showAlertPassword=false
        //console.log(this.rePassword,this.user.password)
      },
      pwd(){
        if(this.rePassword!==this.user.password){
          this.showAlertPassword=true
          this.msgPassword='密码不一致'
        }
        else
          this.showAlertPassword=false
        //console.log(this.rePassword,this.user.password)
        this.passwordStrength=this.testPasswordStrength(this.user.password)
      },
      userId() {
        if(this.user.id!=='')
          this.showAlertId=false
      },
      identifyText(){
        if(this.identifyText!==this.identifyInfo.identifyCode&&this.identifyText.length>=4){
          this.showAlertIdentify=true
          this.msgIdentify='验证码不正确'
        }else {
          this.showAlertIdentify=false
        }
      }
    },
    computed:{
      pwd(){
        return this.user.password
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
