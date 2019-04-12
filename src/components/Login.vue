<template>
  <div>
    <el-dialog ref="loginForm" :model="loginForm" :visible.sync="props.show" title="登录" center width="40%" @open="loginInit" @close="loginClose">
      <el-form ref="form" :model="loginForm" style="width: 350px;margin:auto;">
        <el-tabs v-model="loginForm.role" @tab-click="handleClick">
          <el-tab-pane label="普通用户登陆" name="normal"></el-tab-pane>
          <el-tab-pane label="管理员登陆" name="admin"></el-tab-pane>
        </el-tabs>
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
          <el-input v-model="loginForm.password" placeholder="输入密码" :show-password="true" class="input"></el-input>
        </el-form-item>
        <!--是否记住密码-->
        <el-form-item>
          <label>记住密码</label>
          <el-radio-group v-model="loginForm.radio">
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
        <el-button type="primary" round @click="toForgot">忘记密码</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import DragVerify from "vue-drag-verify/src/dragVerify";
  import {getToken} from "@/utils/auth";
  import {encryptMd5} from "../utils/encrypt";

  export default {
    name: "Login",
    components: {DragVerify},
    props:['props'],
    data(){
        return{
          loginForm:{
            id: getToken('id'),
            password: getToken('password'),
            encryptPassword:'',
            role:getToken('role')?getToken('role'):'normal',
            radio:'0'
          },
          //控制验证进度条属性
          identify:{
            show:true,
            width:350,
            height:40
          },
          loginDisabled:false,
          passwordType:'password',
          warnShow:false,

        }
    },
    methods:{
      onSubmit(){
        //数据格式验证完毕，实现登录功能
        if(!this.$refs.Verity.isPassing){
          this.$message.error({
            message:"验证失败,请拉拽到右边以验证",
            showClose:true
          });
          return
        }else{
          this.loginForm.encryptPassword=encryptMd5(this.loginForm.password)
          this.$store.dispatch('loginById', this.loginForm).then(() => {

          }).catch(() => {
            this.$message.error({
              message:"登录失败,请检查后重试",
              showClose:true
            })
          })
        }
        this.props.show=false;
      },
      toForgot(){
        //跳转忘记密码页面
        this.$router.push({path:'/forgot'})
      },
      handleClick(tab, event) {
        this.loginInit()
      },
      //显示登陆窗口时初始化数据
      loginInit(){
        this.loginForm.radio='0'
        this.identify.show=true
        this.loginForm.id=getToken('id')
        this.loginForm.password=getToken('password')
        if(this.loginForm.id!==''&&this.loginForm.password!=='')
          this.loginDisabled=false
        this.passwordType = 'password'
      },
      //关闭登陆窗口时清除数据
      loginClose(){
        this.identify.show=false
      },
    },
    watch:{
      passwordRadio(){
        if(this.loginForm.radio=='1'){
          this.$confirm('此操作将记住密码,请确保此电脑是私人电脑,密码不会泄露,是否继续?', '提示',{
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            center: true,
            roundButton:true
          }).then(_ => {
              this.loginForm.radio='1';
            })
            .catch(_ => {this.loginForm.radio='0'});
        }
      }
    },
    computed:{
      passwordRadio(){
        return this.loginForm.radio
      }
    }
  }
</script>

<style scoped>

</style>
