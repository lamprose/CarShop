<template>
  <div style="height: 100%">
    <el-scrollbar style="width: 100%;height: 100%;background-color: #fff"><!--隐藏滚动条-->
      <div class="main">
        <div  class="icon">
          <h2 class="item">工具箱</h2>
          <ul class="item">
            <li v-for="(o,index) in toolItems" :class="o.fa"><br><span>{{o.tag}}</span></li>
          </ul>
        </div>
      </div>
    </el-scrollbar>
  </div>
</template>

<script>
  export default {
    name: "SideBarBlank",
    data(){
      return{
        toolItems:[
          {fa:"fa fa-calculator fa-2x",tag:"购车计算器"},
          {fa:"fa fa-credit-card fa-2x",tag:"贷款计算器"},
          {fa:"fa fa-compass fa-2x",tag:"车型对比"},
          {fa:"fa fa-cog fa-2x",tag:"选车工具"},
          {fa:"fa fa-car fa-2x",tag:"车险计算"},
          {fa:"fa fa-search fa-2x",tag:"违章查询"},
        ]
      }
    }
  }
</script>

<style lang="less" scoped>
  .main{
    margin-top: -20px;
    background-color: #fff;
    height: 100%;
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
    top:50px;
  }
  ul{
    margin-top: 80px;
    margin-left: 40px;
    li{
      width: 50%;
      text-align: center;
      margin-top: 40px;
      &:hover{
        color: red;
      }
      span{
        font-size: 10px;
      }
    }
  }
  .el-scrollbar__wrap {
    overflow-x: hidden;
    overflow-y: hidden;
  }
</style>
