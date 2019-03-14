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
      <el-scrollbar><!--隐藏滚动条-->
        <el-aside v-if="show" width="0px" class="aside" id="asideBarBlank">
          <router-view v-if="asideBarNow=='hover-user'" name="userBlankBox"></router-view>
          <router-view v-if="asideBarNow=='hover-shop-cart'" name="shopCartBlankBox"></router-view>
          <router-view v-if="asideBarNow=='hover-tool'" name="toolBlankBox"></router-view>
        </el-aside>
      </el-scrollbar>
    </el-container>
    <router-view></router-view>
    <float id="float" @toggleAsideBarBlankBox="toggle"></float>
    <el-button id="top" type="primary" circle @click="toTop">
      <svg-icon icon-class="angle-up"></svg-icon>
    </el-button>
  </div>
</template>

<script>
import {goToElement} from '@/utils'
export default {
  name: 'App',
  components:{
    'float':resolve => require(["@/components/Float.vue"], resolve),
  },
  data(){
    return{
      show: true,
      isRouterAlive:true,
      asideBarNow:"hover-tool",
      clientHeight:0,
      showTop:false,
    }
  },
  methods:{
    toTop(){
      goToElement(0)
    },
    toggle(data){
      let blank=document.getElementById("asideBarBlank")
      let float=document.getElementById("float")
      let top=document.getElementById("top")
      let needOpen=(blank.style.width==="0px")
      let needClose=(data.id===this.asideBarNow&&!needOpen)
      if(needOpen){
        blank.style.width="300px"
        float.style.right="310px"
        top.style.right=this.showTop?"310px":"0px"
      }
      this.asideBarNow=data.id;
      if(needClose){
        blank.style.width="0px"
        float.style.right="10px"
        top.style.right=this.showTop?"10px":"-36px"
      }
    },
    scroll(){
      let osTop = document.documentElement.scrollTop || document.body.scrollTop;
      if(osTop>=this.clientHeight)
        this.showTop=true
      else
        this.showTop=false
    }
  },
  mounted(){
    document.getElementById("app").style.minWidth=(screen.width-20)+"px";
    this.clientHeight=document.documentElement.clientHeight
    window.addEventListener("scroll",this.scroll)
  },
  computed: {
    topBarKey() {
      return this.$route.name !== undefined? this.$route.name + +new Date(): this.$route + +new Date()
    }
  },
  watch:{
    showTop(){
      let top=document.getElementById("top")
      if(this.showTop)
        top.style.right="10px"
      else
        top.style.right="-36px"
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
  #top{
    position: fixed;
    bottom: 10px;
    right: -36px;
    z-index: 1000;
    transition-property: all;
    transition-duration: 0.5s;
  }
  #float{
    position: fixed;
    right: 10px;
    height: 320px;
    width: 40px;
    z-index: 100;
    transition-property: all;
    transition-duration: 0.5s;
  }
</style>
