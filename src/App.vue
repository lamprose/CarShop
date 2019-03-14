<template>
  <div id="app" style="position: relative">
    <el-container>
      <el-container id="Main">
        <el-header id="topBar"><router-view name="topBar" :key="topBarKey"></router-view></el-header>
        <el-container id="mainBox">
          <el-main>
            <router-view name="mainBox"></router-view>
          </el-main>
        </el-container>
      </el-container>
      <el-footer id="bottomBar"><router-view name="bottomBar"></router-view></el-footer>
      <el-aside width="45px" class="aside aside-close" id="asideBar"><router-view name="asideBar" @toggleAsideBarBlankBox="toggle"></router-view></el-aside>
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
      /*console.log(data);*/
      let blank=document.getElementById("asideBarBlank")
      let side=document.getElementById("asideBar")
      let needOpen=(blank.style.width==="0px")
      let needClose=(data.id===this.asideBarNow&&!needOpen)
      if(needOpen){
        blank.style.width="300px"
        side.classList.remove("aside-close")
        side.classList.add("aside-open")
      }
      this.asideBarNow=data.id;
      if(needClose){
        blank.style.width="0px"
        side.classList.remove("aside-open")
        side.classList.add("aside-close")
      }
    }
  },
  mounted(){
    document.getElementById("app").style.minWidth=(screen.width-20)+"px";
  },
  computed: {
    topBarKey() {
      return this.$route.name !== undefined? this.$route.name + +new Date(): this.$route + +new Date()
    }
  }
}
</script>

<style lang="less">
  #Main{
    margin-left: 10%;
    margin-right: 10%;
  }
  .el-main{
    padding: 0px;
  }
  #topBar{
    margin-top: 30px;
  }
  #mainBox{
    margin-top: 20px;
  }
  .aside{
    position: fixed;
    top:0px;
    height: calc(100% + 10px );
    z-index: 100000;
  }
  #asideBar{
    transition-property: all;
    transition-duration: 0.5s;
    height: calc(100% + 10px );
  }
  .aside-close{
    right: 0px;
    height: 20px;
    opacity: 0;
    &:hover{
      opacity: 1;
    }
  }
  .aside-open{
    right: 300px;
    opacity: 1;
  }
  #asideBarBlank{
    right: 0px;
    transition-property: width;
    transition-duration: 0.5s;
  }
  .el-scrollbar__thumb {
    display: none;
  }
  .el-scrollbar__wrap {
    overflow-x: hidden;
  }
  #bottomBar{
    position: absolute;
    bottom: 0px;
    width: 100%;
    padding: 0px;
  }
</style>
