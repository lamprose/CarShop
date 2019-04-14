<template>
  <div style="margin-top: 20px">
    <img :src="getImage(brand.display+'bar.png')" width="100%">
    <div>
      <el-carousel id="pic-box" :interval="4000" type="card" height="300px" indicator-position="none">
        <el-carousel-item v-for="item in 3" :key="item">
          <img width="542.41px" height="300px" :src="displayImg[item-1]">
        </el-carousel-item>
      </el-carousel>
    </div>
    <div style="margin-top:30px;width: 100%" v-for="i in 3">
      <img :src="displayImg[i-1]" width="100%">
    </div>
  </div>
</template>

<script>
  import {getShopInfo} from "@/api/shop";
  import {strToUrlImage} from "@/utils";

  export default {
    name: "Shop",
    data(){
        return{
          brand:{
            display:'',
            logo:'',
          },
          displayImg:[],
          carouselImgPath:[
            'display1.jpg','display2.jpg','display3.jpg'
          ],
          picItems:[
            {pic:"../static/images/pic1.jpeg"},
            {pic:"../static/images/pic2.jpeg"},
            {pic:"../static/images/pic3.jpeg"},
          ]
        }
    },
    methods:{
      getImage(url){
        return strToUrlImage(url)
      }
    },
    mounted() {
      getShopInfo(this.$route.params.shopId).then(response=>{
        this.brand=response.brand
        this.carouselImgPath.forEach(item=>{
          this.displayImg.push(this.getImage(response.brand.display+item))
        })
        console.log(response.brand)
      })
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
