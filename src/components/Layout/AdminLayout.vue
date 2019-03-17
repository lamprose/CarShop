<template>
  <div>
    <el-container>
      <el-header id="admin-header" height="80px"><router-view name="adminHeader" @collapse="collapse"></router-view></el-header>
      <el-container>
        <el-aside id="admin-aside" width="200px"><router-view name="adminAside" :iscollapse="isCollapse" @switch="switchMain"></router-view></el-aside>
        <el-main id="admin-main">
          <router-view name="adminMain" :active="defaultActive"></router-view>
        </el-main>
      </el-container>
    </el-container>

  </div>
</template>

<script>
  export default {
    name: "AdminLayout",
    data(){
      return{
        isCollapse:false,
        defaultActive:1,
      }
    },
    methods:{
      collapse(){
        this.isCollapse=!this.isCollapse
        document.getElementById("admin-aside").style.width=this.isCollapse?"65px":"200px"
        document.getElementById("collapse-button").style.left=this.isCollapse?"75px":"210px"
        document.getElementById("admin-main").style.marginLeft=this.isCollapse?"65px":"200px"
      },
      switchMain(data){
        this.defaultActive=Number(data.key)
      }
    },
    mounted(){
      if(this.$store.getters.roles[0]!=="admin"){
        this.$router.push("/401")
      }
    }
  }
</script>

<style lang="less" scoped>
  #admin-header{
    position: relative;
    height: 80px;
    width: 100%;
    background-color: #409eff;
    position: fixed;
    z-index: 100;
  }
  #admin-aside{
    height: calc(100% - 80px);
    background-color: white;
    position: fixed;
    top:80px;
    transition-property: all;
    transition-duration: 0.5s;
  }
  #admin-main{
    position: relative;
    height: 2000px;
    margin-top: 80px;
    margin-left: 200px;
    transition-property: all;
    transition-duration: 0.5s;
  }
  div.v-modal{
    display: none;
  }
</style>
