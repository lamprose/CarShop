<template>
  <div style="height:100%;width: 45px">
    <!--侧栏图标-->
    <el-menu style="height: 100%" :collapse="true">
      <!--侧栏上部三个图标-->
      <el-menu-item v-for="i in 3" :id="sideBarItems[i-1].id" class="asideBarItem" @click="toggle" :key="i">
        <el-tooltip :content="sideBarItems[i-1].tag" placement="left">
          <i :class="sideBarItems[i-1].fa"></i>
        </el-tooltip>
      </el-menu-item>
      <!--上下图标间隔-->
      <el-menu-item  class="blank" style="height: calc(100% - 350px)"></el-menu-item>
      <!--侧栏下部三个图标-->
      <el-menu-item  class="asideBarItem" v-for="i in [4,5,6]" :key="i">
        <el-tooltip :content="sideBarItems[i-1].tag" placement="left">
          <i :class="sideBarItems[i-1].fa"></i>
        </el-tooltip>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
    export default {
      data(){
        return{
          //侧边栏项
          sideBarItems:[
            {fa:"fa fa-user fa-lg",tag:"我的",id:"user"},
            {fa:"fa fa-shopping-cart fa-lg",tag:"购物车",id:"shopCart"},
            {fa:"fa fa-wrench fa-lg",tag:"工具箱",id:"tool"},
            {fa:"fa fa-phone fa-lg",tag:"客服电话"},
            {fa:"fa fa-map-marker fa-lg",tag:"定位"},
            {fa:"fa fa-angle-up fa-lg",tag:"返回顶部"},
          ],
          scrollTop: "",
        }
      },
      methods:{
        //侧栏点击图标后发出事件
        toggle(){
          this.$emit("toggleAsideBarBlankBox",{id:event.currentTarget.id})
        },
        handleScroll() {
          this.scrollTop =
            window.pageYOffset ||
            document.documentElement.scrollTop ||
            document.body.scrollTop;
        },
        //返回顶部
        goTop() {
          let timer = null,
            _that = this;
          cancelAnimationFrame(timer);
          timer = requestAnimationFrame(function fn() {
            if (_that.scrollTop > 0) {
              _that.scrollTop -= 50;
              document.body.scrollTop = document.documentElement.scrollTop =
                _that.scrollTop;
              timer = requestAnimationFrame(fn);
            } else {
              cancelAnimationFrame(timer);
            }
          });
        },
      },
      //初始化绑定事件
      mounted() {
        window.addEventListener("scroll", this.handleScroll);
        document.getElementsByClassName("fa fa-angle-up fa-lg")[0].addEventListener("click",this.goTop);
      },
      destroyed() {
        window.removeEventListener("scroll", this.handleScroll);
      }
    }
</script>

<style lang="less" scoped>
  .asideBarItem{
    padding-left: 1px;
  }
  i{
    width: 15px;
  }
  li.el-menu-item{
    padding-left: 15px !important;
    padding-right: 15px !important;
  }
  ul{
    width: auto;
  }
  .el-tooltip__popper.is-dark{
    transform-origin: left center;
  }
  .el-menu{
    border: none;
  }
  .el-menu-item{
    &:hover{
      background-color: transparent;
    }
    .blank:hover{
      background-color: #fff;
    }
    .is-active{
      background-color: #ffffff !important;
    }
  }
</style>
