<template>
  <div style="margin-top: 20px">
    <img src="../../assets/shop-header.jpeg" width="100%">
    <div>
      <el-carousel id="pic-box" :interval="4000" type="card" height="200px" indicator-position="none">
        <el-carousel-item v-for="item in 6" :key="item">
          <h3>{{ item }}</h3>
        </el-carousel-item>
      </el-carousel>
    </div>
    <div style="width: 100%" v-for="(o,index) in picItems">
      <img :src="o.pic" width="100%">
    </div>
  </div>
</template>

<script>
    export default {
        name: "Shop",
      data(){
          return{
            picItems:[
              {pic:"../static/images/pic1.jpeg"},
              {pic:"../static/images/pic2.jpeg"},
              {pic:"../static/images/pic3.jpeg"},
              {pic:"../static/images/pic4.jpeg"},
              {pic:"../static/images/pic5.jpeg"}
            ]
          }
      }
    }
</script>

<style scoped>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
