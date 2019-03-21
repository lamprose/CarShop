<template>
    <div style="width: 100%;height: 100%;background-color: #fff">
      <div style="width: 100%">
        <div id="avatar">
          <img :src="user.avatar===null?defaultAvatar:user.avatar" class="avatar">
        </div>
        <div style="min-width: 300px">
          <span>Hi,{{user.name===''?'用户':user.name}}</span><br>
          <span>欢迎来到车享！</span>
        </div>
        <div style="min-width: 300px;margin-top: 100px">
          <el-button>修改信息</el-button>
          <el-button>修改密码</el-button>
        </div>
      </div>
    </div>
</template>

<script>
    export default {
      name: "User",
      data(){
        return{
          user:{
            avatar:null,
          },
          defaultAvatar:require('@/assets/logo.png'),
        }
      },
      mounted(){
        this.user=this.$store.getters.user
      }
    }
</script>

<style lang="less" scoped>
  .avatar{
    margin-top: 20px;
    width: 150px;
    height: 150px;
    border-radius:50%;
    margin-left: 0px;
    transition-property: all;
    transition-duration: 0.8s;
    &:hover{
      transform: rotate(360deg);
    }
  }
  #avatar{
    width: 150px;
    margin-left: 75px;
  }
</style>
