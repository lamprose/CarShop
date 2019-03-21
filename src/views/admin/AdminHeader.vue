<template>
  <div>
    <div class="logo" style="opacity: 1">
      <img width="150px" :src="logo">
    </div>
    <el-button medium type="text" id="collapse-button" @click="collapse">
      <svg-icon icon-class="align-justify"></svg-icon>
    </el-button>
    <div class="user-info">
      <el-dropdown trigger="hover">
        <div class="user-info-inner">
          <span>{{user.name}}</span>
          <img :src="user.avatar===''?defaultAvatar:user.avatar" />
        </div>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>设置</el-dropdown-item>
          <el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>

  </div>
</template>

<script>
  export default {
    name: "AdminHeader",
    data(){
      return{
        user:{
          name:'',
          avatar:null,
        },
        defaultAvatar:require('@/assets/logo.png'),
        logo:require('@/assets/carshop-logo.png'),
      }
    },
    methods:{
      collapse(){
        let logo=document.getElementsByClassName("logo")[0]
        logo.style.opacity=logo.style.opacity==="1"?"0":"1"
        this.$emit("collapse")
      }
    },
    mounted() {
      this.user.avatar=this.$store.getters.avatar;
      this.user.name=this.$store.getters.name
    }
  }
</script>

<style lang="less" scoped>
  .logo{
    position: absolute;
    margin-top: 15px;
    opacity: 1;
    transition-property: all;
    transition-duration: 0.2s;
  }
  #collapse-button{
    position: absolute;
    left: 210px;
    top:18px;
    transition-property: all;
    transition-duration: 0.5s;
    font-size: 20px;
  }
  .user-info{
    position: absolute;
    right: 20px;

    .user-info-inner{
      margin-top: 10px;
      float: left;
      color: #ffffff;
      span{
        margin-top: 20px;
        display: inline-block;
      }
    }
    & img {
      width: 40px;
      height: 40px;
      border-radius: 20px;
      margin: 10px 0px 10px 10px;
      float: right;
    }
  }
</style>
