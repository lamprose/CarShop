<template>
  <div>
    <el-container>
      <el-header id="admin-header" height="70px"><router-view name="adminHeader" @collapse="collapse"></router-view></el-header>
      <el-container>
        <el-aside id="admin-aside" width="200px"><router-view name="adminAside" :active="defaultActive" :isCollapse="isCollapse" @switch="switchMain"></router-view></el-aside>
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
        defaultActive:'-1'
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
        this.defaultActive=data.key
      }
    },
    watch:{
      tempRoles(){
        if(this.tempRoles==='')
          this.defaultActive='1'
        else
          this.defaultActive='3'
      }
    },
    computed:{
      tempRoles(){
        return this.$store.getters.tempRoles
      }
    }
  }
</script>

<style lang="less" scoped>
  #admin-header{
    height: 70px;
    width: 100%;
    background-color: #409eff;
    position: fixed;
    z-index: 100;
  }
  #admin-aside{
    height: calc(100% - 70px);
    background-color: white;
    position: fixed;
    top:70px;
    border-right: solid 2px #e6e6e6;
    transition-property: all;
    transition-duration: 0.5s;
  }
  #admin-main{
    position: relative;
    height: 585px;
    margin-top: 70px;
    margin-left: 200px;
    transition-property: all;
    transition-duration: 0.5s;
  }
  div.v-modal{
    display: none;
  }
</style>
