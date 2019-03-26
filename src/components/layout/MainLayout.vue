<template>
  <div id="mainLayout" style="position: relative">
    <el-container>
      <el-container id="Main">
        <!--顶部栏-->
        <el-header id="topBar"><router-view name="topBar" :key="topBarKey"></router-view></el-header>
        <!--主体部分-->
        <el-container id="mainBox">
          <el-main>
            <router-view name="mainBox"></router-view>
          </el-main>
        </el-container>
      </el-container>
      <!--底部footer-->
      <el-footer id="bottomBar"><router-view name="bottomBar"></router-view></el-footer>
      <el-scrollbar><!--隐藏滚动条-->
        <el-aside v-if="show&&this.$route.path!=='/balance'" width="300px" id="asideBarBlank">
          <aside-layout :asideBarNow="asideBarNow"></aside-layout>
        </el-aside>
      </el-scrollbar>
    </el-container>
    <!--其他-->
    <router-view></router-view>
    <!--侧边悬浮栏-->
    <float v-if="this.$route.path!=='/balance'" id="float" :float="status?floatLogin:floatNoLogin" @toggleAsideBarBlankBox="toggle"></float>
    <!--右下角返回顶部按钮-->
    <el-button id="top" type="primary" circle @click="toTop">
      <svg-icon icon-class="angle-up"></svg-icon>
    </el-button>
  </div>
</template>

<script>
  import {goToElement} from '@/utils'
  export default {
    name: 'MainLayout',
    components:{
      'float':resolve => require(["@/components/FloatButton.vue"], resolve),
      'asideLayout':resolve => require(["@/components/layout/AsideLayout.vue"], resolve),
    },
    data(){
      return{
        show: true,
        isRouterAlive:true,
        asideBarNow:"hover-tool",
        clientHeight:0,
        showTop:false,
        floatLogin:[
          {id:"hover-user",icon:"user",tag:"我的",val:2},
          {id:"hover-cart",icon:"shopping-cart",tag:"购物车"},
          {id:"hover-chat",icon:"comments",tag:"聊天室",val:0},
          {id:"hover-tool",icon:"wrench",tag:"工具箱"},
          {id:"hover-phone",icon:"phone",tag:"客服电话"},
          {id:"hover-locate",icon:"map-marker",tag:"定位"},
        ],
        floatNoLogin:[
          {id:"hover-tool",icon:"wrench",tag:"工具箱"},
          {id:"hover-phone",icon:"phone",tag:"客服电话"},
          {id:"hover-locate",icon:"map-marker",tag:"定位"},
        ],

      }
    },
    methods:{
      //返回顶部
      toTop(){
        goToElement(0)
      },
      //打开侧边栏
      toggle(data){
        let blank=document.getElementById("asideBarBlank")
        let float=document.getElementById("float")
        let top=document.getElementById("top")
        let needOpen=(blank.style.right==="-300px")
        let needClose=(data.id===this.asideBarNow&&!needOpen)
        if(needOpen){
          blank.style.right="10px"
          float.style.right="320px"
        }
        this.asideBarNow=data.id;
        if(needClose){
          blank.style.right="-300px"
          float.style.right="10px"
        }
      },
      //监控滑动 是否显示返回顶部按钮
      scroll(){
        let osTop = document.documentElement.scrollTop || document.body.scrollTop;
        if(osTop>=this.clientHeight)
          this.showTop=true
        else
          this.showTop=false
        if(document.getElementById("asideBarBlank"))
          document.getElementById("asideBarBlank").style.right="-300px"
        if(document.getElementById("float"))
          document.getElementById("float").style.right="10px"
        if(document.getElementById("top"))
          document.getElementById("top").style.right=this.showTop?"10px":"-36px"
      },
    },
    //主页初始化
    mounted(){
      //初始化整个页面宽度
      document.getElementById("app").style.minWidth=(screen.width-20)+"px";
      //获取屏幕高度
      this.clientHeight=document.documentElement.clientHeight
      //为浏览器滑动绑定事件以达到滑动半屏显示返回顶部按钮
      window.addEventListener("scroll",this.scroll)
      this.floatLogin[2].val=this.totalUnread
    },
    destroyed(){
      window.removeEventListener('scroll',this.scroll)
    },
    computed: {
      topBarKey() {
        return this.$route.name !== undefined? this.$route.name + +new Date(): this.$route + +new Date()
      },
      status(){
        return this.$store.getters.status
      },
      totalUnread(){
        return this.$store.getters.userSessions.reduce(function (prev, cur) {
          return prev+cur.unread
        },0)
      }
    },
    watch:{
      //监控是否显示返回顶部按钮属性
      showTop(){
        let top=document.getElementById("top")
        if(this.showTop)
          top.style.right="10px"
        else
          top.style.right="-36px"
      },
      totalUnread(val){
        this.floatLogin[2].val=this.totalUnread
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
  #asideBarBlank{
    right: -300px;
    transition-property: all;
    transition-duration: 0.5s;
    border-radius:10px;
    height: 90%;
    position: fixed;
    top:10px;
    z-index: 100000;
    &:hover{
      box-shadow: 5px 5px 10px #888888;
    }
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
