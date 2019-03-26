<template>
  <div style="margin-top: 20px">
    <div style="width: 100%;margin-top: 30px;border:1px solid #222" v-for="i in 3">
      <el-row :gutter="24">
        <el-col :span="2"><span>&nbsp</span></el-col>
        <el-col :span="5">
          <p>别克  威朗</p>
          <img class="lazy" src="//i1.cximg.com/images/210x140/84bd33606b889b25/20180816/36894bec1d4446e8a947b72f8b758b8b.png" alt="别克&nbsp; 威朗">
        </el-col>
        <el-col :span="2"><span>&nbsp</span></el-col>
        <el-col :span="13">
          <div class="car-info">
            <span>&nbsp</span>
            <span class="name">2018款 Health Plus定制版</span>
            <span class="price">裸车价  <span>¥10.29万</span></span>
          </div>
          <!--<div class="color">
            <span>&nbsp</span>
            <div class="color-info">
              <span class="item-title" style="float: left">车身颜色</span>
              <span style="margin-left:30px;width:60px;height: 20px;float: left">
              <ul class="color-list" style="width: 60px;height: 20px">
                <li colorid="42979" skucode="10168291" colorname="玛瑙红" class="has-amount cur" availablenum="29">
                  <img src="//i3.cximg.com/images/84bd33606b889b25/20171017/3f0b4c0c09204275b3db9bad67bc6112.jpg" alt="玛瑙红" width="20" height="20">
                </li>
                <li colorid="42982" skucode="10168289" colorname="雪域白" class="has-amount" availablenum="27">
                  <img src="//i3.cximg.com/images/84bd33606b889b25/20171017/355e400f40b84db392b28c11b376eed9.jpg" alt="雪域白" width="20" height="20">
                </li>
                <li colorid="42983" skucode="10168290" colorname="流沙金" class="has-amount" availablenum="10">
                  <img src="//i3.cximg.com/images/84bd33606b889b25/20171025/3172d04cff45456996357d57e07b06b1.jpg" alt="流沙金" width="20" height="20">
                </li>
              </ul>
            </span>
            </div>
          </div>-->
          <div class="operate">
            <span>&nbsp</span>
            <span class="show-info"><el-button>查看详情</el-button></span>
            <span class="buy-now"><el-button>立即购买</el-button></span>
          </div>
        </el-col>
        <el-col :span="2"><span>&nbsp</span></el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
  import {search} from "@/api/car";

  export default {
    name: "SearchResult",
    mounted(){
      search(this.$route.params.query).then(data=>{
        console.log(this.$route.params.query)
      })
    },
    watch: {
      $route(to,from){
        //TODO:监控路由变化获取搜索数据
        search(this.$route.params.query).then(data=>{
          console.log(this.$route.params.query)
        })
      }
    }
  }
</script>

<style lang="less" scoped>
  .color{
    position: relative;
    .color-info{
      position: absolute;
      left: 20px;
      top:30px;
    }
  }
  .car-info{
    position: relative;
    .name{
      position: absolute;
      left: 20px;
      top:10px;
    }
    .price{
      position: absolute;
      right: 20px;
      top:10px;
    }
  }
  .operate{
    position: relative;
    width: 100%;
    .show-info{
      position: absolute;
      left:30%;
      top: 60px;
    }
    .buy-now{
      position: absolute;
      right:30%;
      top:60px;
    }
  }
</style>
