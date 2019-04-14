<template>
  <div style="margin-top: 20px">
    <el-row style="width: 100%;border-bottom: 1px solid #e8e8e8;padding-top: 50px" v-for="(car,index) in cars" :key="index">
      <el-col :span="2"><span>{{index+1}}</span></el-col>
      <el-col :span="8">
        <p>{{car.carName}}</p>
        <img class="lazy" width="200px" :src="getImage(car.image+'right.jpg')" :alt="car.carName">
      </el-col>
      <el-col :span="7">
        <div class="car-info">
          <span>&nbsp</span>
          <span class="price">价格<span>¥{{car.price}}万</span></span>
        </div>
      </el-col>
      <el-col :span="7">
        <div class="operate">
          <span>&nbsp</span>
          <span class="show-info"><el-button @click="toPage('/car/'+car.carId)">查看详情</el-button></span>
          <span class="buy-now"><el-button @click="toBuy(car)">立即购买</el-button></span>
        </div>
      </el-col>
    </el-row>
    <el-row style="width: 100%;border-bottom: 1px solid #e8e8e8;padding-top: 50px" v-for="(brand,index) in brands" :key="index">
      <el-col :span="2"><span>{{cars.length+index}}</span></el-col>
      <el-col :span="8">
        <p>{{brand.brandName}}</p>
        <img class="lazy" width="200px" :src="getImage(brand.logo)" :alt="brand.brandName">
      </el-col>
      <el-col :span="7">
        <div class="car-info">
          <span>&nbsp</span>
        </div>
      </el-col>
      <el-col :span="7">
        <div class="operate">
          <span>&nbsp</span>
          <span class="show-info"><el-button @click="toPage('/shop/'+brand.brandId)">查看详情</el-button></span>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {searchByText} from "@/api/car";
  import {strToUrlImage} from "@/utils";

  export default {
    name: "SearchResult",
    data(){
      return{
        cars:[],
        shops:[],
        brands:[]
      }
    },
    methods:{
      getImage(url){
        return strToUrlImage(url)
      },
      toPage(page){
        this.$router.push({path:page})
      },
      toBuy(carInfo){
        if(this.loginUser.status){
          this.$router.push({
            name:'Balance',
            params:{type:'submit',data:[{amount:1,car:carInfo,user:this.loginUser}]}
          })
        }else
          this.$message.error({
            message:'未登录，请登陆后重试',
            showClose:false
          })
      },
    },
    mounted(){
      searchByText(this.$route.params.query).then(data=>{
        this.cars=data.cars
        this.shops=data.shops
        this.brands=data.brands
        console.log(data.brands)
      })
    },
    watch: {
      $route(to,from){
        //TODO:监控路由变化获取搜索数据
        console.log(to)
        searchByText(this.$route.params.query).then(data=>{
          this.cars=data.cars
          this.shops=data.shops
          this.brands=data.brands
          console.log(data.brands)
        })
      }
    },
    computed:{
      loginUser(){
        return this.$store.getters.user
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
      right: 50%;
      top:50%;
    }
  }
  .operate{
    position: relative;
    width: 100%;
    .show-info{
      position: absolute;
      left:18%;
      top: 60px;
    }
    .buy-now{
      position: absolute;
      right:18%;
      top:60px;
    }
  }
</style>
