<template>
  <div>
    <el-dialog ref="loginForm" :model="loginForm" :visible.sync="props.show" title="登录" center width="40%" @open="loginInit" @close="loginClose">
      <el-form ref="form" :model="user" style="width: 350px;margin:auto;">
        <!--用户名输入-->
        <el-form-item>
          <el-input v-model="loginForm.id" placeholder="输入用户名"  class="input">
            <span class="svg-container" slot="suffix">
              <svg-icon icon-class="user" />
            </span>
          </el-input>
        </el-form-item>
        <!--密码输入-->
        <el-form-item>
          <el-input
            v-model="loginForm.password"
            placeholder="输入密码"
            :type="passwordType"
            class="input">
            <span class="show-pwd" @click="showPwd" slot="suffix">
              <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
            </span>
          </el-input>
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
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" round :disabled=loginDisabled @click="onSubmit">立即登录</el-button>
        <el-button type="primary" round >忘记密码</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
    import DragVerify from "vue-drag-verify/src/dragVerify";
    import {encryptMd5} from '@/utils/encrypt'
    export default {
      name: "Login",
      components: {DragVerify},
      props:['props','user'],
      data(){
          return{
            loginForm:{
              id: 'admin',
              password: '1111111'
            },
            //控制验证进度条属性
            identify:{
              show:true,
              width:350,
              height:40
            },
            loginDisabled:false,
            passwordType:'password',
          }
      },
      methods:{
        onSubmit(){
          /*let re=this.props.radio=='0'?"false":"true"
          this.$cookie.setCookie(this.user.id,this.user.password,re,10);*/
          //TODO: 数据格式验证完毕，实现登录功能
          //console.log(this.user)
          //将用户名和token放入sessionStorage
          this.loginForm.password=encryptMd5(this.loginForm.password)
          this.$store.dispatch('LoginByUsername', this.loginForm).then(() => {
            /*console.log(this.$store.getters.user);*/
            /*this.$router.push({ path: '/' })*/
          }).catch(() => {

          })
          //console.log("in login image is " + sessionStorage.getItem("user"))
          //将用户信息放入vuex
          /*this.$store.commit("setUser",this.user)
          this.$store.commit("changeLoginStatus")*/
          this.props.show=false;
        },
        toForgot(){
          //TODO:跳转忘记密码功能
        },
        showPwd() {
          if (this.passwordType === 'password') {
            this.passwordType = ''
          } else {
            this.passwordType = 'password'
          }
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
      },
      mounted(){
        console.log(this.$store.state.remember)
        if(this.$store.state.remember){
          this.user.id=this.$store.state.user.id;
          this.user.password=this.$store.state.user.password;
        }
      },
      computed:{
        loginStatus(){
          //TODO:获取当前用户登录状态

        },
        keepId(){
          return sessionStorage.getItem("keepId")?sessionStorage.getItem("keepId"):''
        },
        keepPassword(){
          return sessionStorage.getItem("keepPassword")?sessionStorage.getItem("keepPassword"):''
        }
      }
    }
</script>

<style scoped>

</style>
