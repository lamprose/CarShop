<template>
  <div style="height:100%;background-color: #222">
    <el-menu class="el-menu-vertical-demo" style="height: 100%">
      <el-menu-item id="user" index="1" class="asideBarItem" @click="toggle">
        <i class="fa fa-user fa-lg"></i>
        <span slot="title"></span>
      </el-menu-item>
      <el-menu-item id="shopCart" index="2" class="asideBarItem" @click="toggle">
        <i class="fa fa-shopping-cart fa-lg"></i>
        <span slot="title"></span>
      </el-menu-item>
      <el-menu-item id="tool" index="3" class="asideBarItem" @click="toggle">
        <i class="fa fa-wrench fa-lg"></i>
        <span slot="title"></span>
      </el-menu-item>
      <el-menu-item index="0" class="blank" style="height: calc(100% - 350px)"></el-menu-item>
      <el-menu-item index="4" class="asideBarItem">
        <i class="fa fa-phone fa-lg"></i>
        <span slot="title"></span>
      </el-menu-item>
      <el-menu-item index="5" class="asideBarItem">
        <i class="fa fa-map-marker fa-lg"></i>
        <span slot="title"></span>
      </el-menu-item>
      <el-menu-item index="6" class="asideBarItem">
        <i class="fa fa-angle-up fa-lg"></i>
        <span slot="title"></span>
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
            {fa:"fa fa-user fa-lg",tag:"我的"},
            {fa:"fa fa-shopping-cart fa-lg",tag:"购物车"},
            {fa:"fa fa-wrench fa-lg",tag:"工具箱"},
            {fa:"fa fa-phone fa-lg",tag:"客服电话"},
            {fa:"fa fa-map-marker fa-lg",tag:"定位"},
            {fa:"fa fa-angle-up fa-lg",tag:"返回顶部"},
          ],
          scrollTop: "",
          goTopShow: false
        }
      },
      methods:{
        toggle(){
          this.$emit("toggleAsideBarBlankBox",{id:event.currentTarget.id})
        },
        toggleState(){
          // console.log(event.target);
          this.$emit('toggleAsideBarBlank');
        },
        handleScroll() {
          this.scrollTop =
            window.pageYOffset ||
            document.documentElement.scrollTop ||
            document.body.scrollTop;
          if (this.scrollTop > 500) {
            this.goTopShow = true;
          }
        },
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
              _that.goTopShow = false;
            }
          });
        },
      },
      mounted() {
        window.addEventListener("scroll", this.handleScroll);
        document.getElementsByClassName("fa fa-angle-up")[0].addEventListener("click",this.goTop);
        document.getElementsByClassName("fa fa-wrench")[0].addEventListener("click",this.toggleState);
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
  .el-menu{
    border: none;
  }
  .el-menu-item.blank:hover{
    background-color: #fff;
  }
</style>
