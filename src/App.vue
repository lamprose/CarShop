<template>
  <div id="app">
    <el-container>
      <el-container id="Main">
        <el-header id="topBar"><router-view name="topBar"></router-view></el-header>
        <el-container id="mainBox">
          <el-main>
            <router-view name="mainBox"></router-view>
          </el-main>
        </el-container>
        <el-footer><router-view name="bottomBar"></router-view></el-footer>
      </el-container>
      <el-aside width="45px" class="aside" id="asideBar"><router-view name="asideBar" @toggleAsideBarBlankBox="toggle"></router-view></el-aside>
      <el-scrollbar><!--隐藏滚动条-->
        <el-aside v-if="show" width="0px" class="aside" id="asideBarBlank">
          <router-view v-if="asideBarNow=='user'" name="userBlankBox"></router-view>
          <router-view v-if="asideBarNow=='shopCart'" name="shopCartBlankBox"></router-view>
          <router-view v-if="asideBarNow=='tool'" name="toolBlankBox"></router-view>
        </el-aside>
      </el-scrollbar>
    </el-container>
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: 'App',
  data(){
    return{
      show: true,
      isRouterAlive:true,
      asideBarNow:"tool"
    }
  },
  methods:{
    toggle(data){
      console.log(data);
      let blank=document.getElementById("asideBarBlank")
      let side=document.getElementById("asideBar")
      let needOpen=(blank.style.width=="0px")
      let needClose=(data.id==this.asideBarNow&&!needOpen)
      if(needOpen){
        blank.style.width="300px"
        side.style.right="300px"
      }
      this.asideBarNow=data.id;
      if(needClose){
        blank.style.width="0px"
        side.style.right="0px"
      }
    }
  }
}
</script>

<style>
  #Main{
    margin-left: 10%;
    margin-right: 10%;
  }
  #topBar{
    margin-top: 30px;
  }
  #mainBox{
    margin-top: 20px;
  }
  .aside{
    position: fixed;
    right: 0px;
    height: calc(100% + 10px );
    z-index: 100000;
  }
  #asideBar{
    right: 0px;
    transition-property: right;
    transition-duration: 1s;
    height: calc(100% + 10px );
  }
  #asideBarBlank{
    transition-property: width;
    transition-duration: 1s;
  }
  .el-scrollbar__thumb {
    display: none;
  }
  .el-scrollbar__wrap {
    overflow-x: hidden;
  }
</style>
