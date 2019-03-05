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
      <el-aside width="55px" class="aside" id="asideBar"><router-view name="asideBar" @toggleAsideBarBlankBox="toggle" @toggleAsideBarBlank="toggleStates"></router-view></el-aside>
      <el-scrollbar><!--隐藏滚动条-->
        <el-aside v-if="show" width="200px" class="aside" id="asideBarBlank">
          <router-view :now="asideBarNow" v-if="isRouterAlive" name="asideBarBlank"></router-view>

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
      this.asideBarNow=data.id;
      this.isRouterAlive = false;
      this.$nextTick(()=>(this.isRouterAlive=true));
    },
    toggleStates(){
      var blank=document.getElementById("asideBarBlank")
      var side=document.getElementById("asideBar")
      if(blank.style.width==="0px"){
        blank.style.width="200px"
        side.style.right="200px"
      }else{
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
    right: 200px;
    transition-property: right;
    transition-duration: 2s;
    height: calc(100% + 10px );
  }
  #asideBarBlank{
    transition-property: width;
    transition-duration: 2s;
  }
  .el-scrollbar__wrap {
    overflow-x: hidden;
    overflow-y: hidden;
  }
</style>
