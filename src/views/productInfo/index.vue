<template>
    <div style="margin-top: 20px">
      <!--顶部面包屑-->
      <el-breadcrumb separator-class="el-icon-arrow-right" style="width: 100%">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/' }">{{productInfo.shop}}</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/' }">{{productInfo.series}}</el-breadcrumb-item>
        <el-breadcrumb-item >{{productInfo.name}}</el-breadcrumb-item>
      </el-breadcrumb>
      <!--商品信息-->
      <el-row :gutter="24" style="margin-top: 20px">
        <el-col :span="9">
          <el-carousel id="product-large" indicator-position="outside">
            <el-carousel-item v-for="item in 4" :key="item" style="background-color: #000">
              <h3>{{ item }}</h3>
            </el-carousel-item>
          </el-carousel>
        </el-col>
        <el-col :span="15" class="info">
          <div class="info-item" style="margin-top: 20px">
            <h1 class="car-name">{{productInfo.name}}</h1>
          </div>
          <div class="info-item" style="">
            <div class="car-tag">{{productInfo.tag}}</div>
          </div>
          <div class="info-item" style="position: relative">
            <span class="car-price">裸车价</span><strong><span class="yen">￥</span><span>{{productInfo.price}}</span>万<span></span></strong>
            <span class="car-guidingPrice">指导价<span>{{productInfo.guidingPrice}}</span>万</span>
          </div>
          <div class="info-item" style="height: 40px">
            <label class="item-title" style="margin-top:10px;float: left">车身颜色</label>
            <ul class="color-list">
              <li class="color-item" v-for="colorItem in productInfo.color">
                <div :style="{backgroundColor: colorItem}"></div>
              </li>
            </ul>
          </div>
          <div class="info-item car-loc" style="">
            <label class="item-title">提车地区</label>
            <el-select value="1"></el-select>
            <el-select value="1"></el-select>
          </div>
          <div class="info-item" style="">
            <el-button>立即购买</el-button>
            <el-button>加入购物车</el-button>
          </div>
        </el-col>
      </el-row>
      <!--商品详细信息-->
      <div>
        <!--侧边固定栏-->
        <div id="sideBar" :class="sideBarFixed == true ? 'isFixed' :''" style="float:left;width:200px;height: 600px;background-color: red">

        </div>
        <!--商品图片信息-->
        <div style="float:right;width:calc(100% - 220px);height: 1000px;background-color: black"></div>
      </div>
    </div>
</template>

<script>
  export default {
    name: "GoodsInfo",
    data(){
      return{
        productInfo:{
          name:'上汽大众 凌渡 280TSI DSG米色内饰限量版',
          shop:'上汽大众',
          series:'凌渡',
          tag:'【车享自营】综合优惠：46818元，手快抢10万公里免保',
          price:'14.59',
          guidingPrice:'18.49',
          color:[
            'red','black','gold'
          ],
        },
        offsetTop:0,
        sideBarFixed:false,
      }
    },
    methods:{
      handleScroll () {
        let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
        //let offsetTop = document.querySelector('#sideBar').offsetTop
        scrollTop > this.offsetTop? this.sideBarFixed = true : this.sideBarFixed = false
      },
    },
    mounted () {
      window.addEventListener('scroll', this.handleScroll)
      this.offsetTop=document.querySelector('#sideBar').offsetTop
    },
  }
</script>

<style lang="less" scoped>
  .isFixed{
    position: fixed;
    top:5px;
  }
  .info{
    height: 300px;
    .info-item{
      margin-left: 10%;
      margin-right: 10%;
      min-width: 400px;
      margin-top: 10px;
      .car-name{
        margin-left: 0px;
        font-size: 18px;
        color: #333;
        font-weight: normal;
        height: 20px;
      }
      .car-tag{
        font-size: 14px;
        color: #c71445;
        overflow: hidden;
        text-overflow: ellipsis;
        height: 44px;
        line-height: 22px;
      }
      .car-price{
        left: 0px;
        top:5px;
        position: absolute;
        + strong{
          font-size: 24px;
          color: #c71445;
          padding: 0 15px 0 7px;
          font-weight: 100;
          display: inline-block;
          vertical-align: middle;
          line-height: 100%;
          height: 29px;
        }
      }
      .car-guidingPrice{
        position: absolute;
        right: 40px;
        top:10px;
        font-size: 12px;
        color: #999;
        margin-left: 20px;
      }
      .color-item{
        width: 20px;
        height: 20px;
        margin-left: 3px;
        margin-top: 10px;
        &:hover{
          box-shadow: 0px 0px 5px #888888;
        }
        div{
          width: 100%;
          height: 100%;
        }
      }
      &.car-loc{
        .el-select{
          width: 150px;
          margin-left: 70px;
        }
      }
    }
  }
  .item-title{
    font-size: 14px;
    color: #666;
    display: inline-block;
    vertical-align: middle;
    text-align: justify;
    text-align-last: justify;
  }
</style>
