<template>
  <div>
    <el-dialog :visible.sync="props.show" title="登录" center width="40%" @open="loginInit" @close="loginClose">
      <el-form ref="form" :model="user" style="width: 350px;margin:auto;">
        <!--用户名输入-->
        <el-form-item>
          <el-input v-model="user.id" placeholder="输入用户名"  class="input" clearable></el-input>
        </el-form-item>
        <!--密码输入-->
        <el-form-item>
          <el-input v-model="user.password" placeholder="输入密码" type="password" class="input" clearable></el-input>
        </el-form-item>
        <!--是否记住密码-->
        <el-form-item>
          <label>记住密码</label>
          <el-radio-group v-model="props.radio">
            <el-radio label='1'>是</el-radio><el-radio label='0'>否</el-radio>
          </el-radio-group>
        </el-form-item>
        <!--拖拽验证-->
        <el-form-item>
          <drag-verify v-if="identify.show" text="拉拽到右边以验证" :width="identify.width" :height="identify.height" success-text="验证成功" ref="Verity"></drag-verify>
        </el-form-item>
      </el-form>
      <!--底部登陆忘记密码按钮-->
      <span slot="footer" class="dialog-footer" v-if="!loginStatus">
        <el-button type="primary" round :disabled=loginDisabled @click="onSubmit">立即登录</el-button>
        <el-button type="primary" round  @click="getCookie">忘记密码</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
    import DragVerify from "vue-drag-verify/src/dragVerify";
    import Crypto from 'crypto'
    export default {
      name: "Login",
      components: {DragVerify},
      props:['props','user'],
      data(){
          return{
            //控制验证进度条属性
            identify:{
              show:true,
              width:350,
              height:40
            },
            loginDisabled:false,
          }
      },
      methods:{
        onSubmit(){
          this.setCookie(this.user.id,this.user.password,10);
          //TODO: 数据格式验证完毕，实现登录功能
        },
        toForgot(){
          //TODO:跳转忘记密码功能
        },
        //显示登陆窗口时初始化数据
        loginInit(){
          this.props.radio='0'
          this.identify.show=true
          if(this.user.id!==''&&this.user.password!=='')
            this.loginDisabled=false
        },
        //关闭登陆窗口时清除数据
        loginClose(){
          this.identify.show=false
        },
        //设置cookie方法
        setCookie(id, password, exdays) {
          let hash = Crypto.createHash('md5');
          hash.update(password)
          let text = hash.digest('hex');//使用CryptoJS方法加密
          console.log(text);
          let d = new Date();
          d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
          let expires = "expires=" + d.toUTCString();
          //字符串拼接存入cookie
          window.document.cookie = "id" + "=" + id  +';'+ expires+"; path=/";
          window.document.cookie = "password" + "=" + text +';'+ expires+"; path=/";
        },
        //读取cookie
        getCookie() {
          if (document.cookie.length > 0) {
            let arr = document.cookie.split('; '); //这里显示的格式需要切割一下自己可输出看下
            for (let i = 0; i < arr.length; i++) {
              let arr2 = arr[i].split('='); //再次切割
              //这里会切割出以mobile为第0项的数组、以password为第0项的数组，判断查找相对应的值
              if (arr2[0] === 'id') {
                console.log(arr2[1])
                /*this.ruleForm.mobile = arr2[1]; //拿到账号*/
              } else if (arr2[0] === 'password') {
                /*//拿到拿到加密后的密码arr2[1]并解密
                let bytes = hash.decrypt(arr2[1].toString(), 'secret key 123');
                let plaintext = bytes.toString(CryptoJS.enc.Utf8); //拿到解密后的密码（登录时输入的密码）*/
                console.log(arr2[1]);
              }
            }
          }
        },
        //清除cookie
        clearCookie() {
          this.setCookie("", "", 0); //账号密码置空，天数置0
        }
      },
      computed:{
        loginStatus(){
          //TODO:获取当前用户登录状态
        }
      }
    }
</script>

<style scoped>

</style>
