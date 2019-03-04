<template>
  <el-scrollbar style="width: 100%">
    <div class="main">
      <div  class="icon">
        <h2 class="item">工具箱</h2>
        <ul class="item">
          <li class="fa fa-calculator fa-2x"></li>
          <li class="fa fa-credit-card fa-2x"></li>
          <li class="fa fa-compass fa-2x"></li>
          <li class="fa fa-cog fa-2x"></li>
          <li class="fa fa-car fa-2x"></li>
          <li class="fa fa-search fa-2x"></li>
        </ul>
      </div>
    </div>
  </el-scrollbar>
</template>

<script>
    export default {
        name: "SideBarBlank"
    }
</script>

<style scoped>
  .main{
    margin-top: -20px;
    background-color: #fff;
    height: 820px;
    position: relative;
  }
  .icon{
    overflow: auto;
  }
  .item{
    position: absolute;
    width: 140px;
  }
  h2{
    left: 40px;
  }
  ul{
    margin-top: 50px;
  }
  ul li{
    width: 50%;
    text-align: center;
    float: left;
    margin-top: 40px;
  }
  .el-scrollbar__wrap {
    overflow-x: hidden;
  }
</style>
