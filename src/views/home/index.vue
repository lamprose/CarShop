<template>
    <div>
      <div><el-menu default-active="1" class="el-menu-demo" mode="horizontal">
        <el-menu-item v-for="(o,index) in navigationBarItem" :key="index">
          <el-button type="text" :id="o.id" v-anchor="o.anchor">{{o.text}}</el-button>
        </el-menu-item>
        <el-menu-item>
          <label>400 866 999</label>
        </el-menu-item>
      </el-menu></div>
      <div style="height: 350px">
        <el-row >
          <!--显示分类列表-->
          <el-col :span="5" class="classify" :style="{backgroundImage:'url('+sideListBg+')'}">

            <div class="classify-item" >
              <div style="height: 20%">
                <span>品牌</span>
              </div>
              <ul>
                <li v-for="o in shops" style="float: left;margin-left: 30px">
                  <router-link class="header-brand" :to="o.link">
                    {{o.short}}
                  </router-link>
                </li>
              </ul>
            </div>
          </el-col>
          <!--广告位跑马灯-->
          <el-col :span="19">
            <el-carousel indicator-position="none" height="350px">
              <el-carousel-item v-for="(o,index) in carousel" :key="index">
                <img width="817px" :src="o">
              </el-carousel-item>
            </el-carousel>
          </el-col>
        </el-row>
      </div>
      <!--享推荐-->
      <div id="anchor-recommend">
        <div class="block-kit">
          <!--标题-->
          <div class="slogan">
            <h2>享推荐</h2><span>为你所享，推荐所需</span>
          </div>
          <div v-for="brand in brands">
            <div class="clear-fix">
              <img src="../../assets/reTab1.jpg" width="1190px">
              <div class="goods-box">
                <ul class="custom-list-car">
                  <li v-for="car in brand.topShop">
                    <router-link :to="{path:'/car/'+car.carId}">
                      <h3 class="goods-title">{{car.carName}}</h3>
                      <p class="goods-subtitle">综合优惠{{(car.price * 0.05).toFixed(2)}}万元</p>
                      <p class="goods-price">￥{{car.price}}万</p>
                      <img class="lazy goods-img" width="180" height="120" :src="getImage(car.image+'right.jpg')" style="display: inline;">
                    </router-link>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div id="anchor-brandShop">
        <div class="block-kit">
          <!--标题-->
          <div class="slogan">
            <h2>品牌旗舰店</h2><span>下单送好礼</span>
          </div>
          <!--品牌旗舰店列表-->
          <div>
            <ul class="shop-list">
              <li class="shop-item" v-for="shop in shops">
                <router-link :to="shop.link">
                  <img alt="" :src="shop.img" style="display: block;">
                  <h4>{{shop.title}}</h4>
                  <p>{{shop.tag}}</p>
                </router-link>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div id="anchor-localShop">
        <div class="block-kit">
          <!--标题-->
          <div class="slogan">
            <h2>车享实体店</h2><span>车享家汽车商城是车享平台旗下的全国连锁线下实体店</span>
          </div>
          <div>
            <img width="100%" src="@/assets/bg-local-store.jpg">
          </div>
        </div>
      </div>
      <div id="anchor-loan">
        <div class="block-kit">
          <!--标题-->
          <div class="slogan">
            <h2>贷款购车</h2><span>生活0负担，享受自在生活</span>
          </div>
          <div>
            <img width="100%" src="@/assets/bg-loan.jpg">
          </div>
        </div>
      </div>

    </div>
</template>

<script>
  import {getEachShopTopNumberCars} from "@/api/shop";
  import {strToUrlImage} from "@/utils";

  export default {
    name: "Home",
    data(){
      return{
        carousel:[
          require("@/assets/carousel/carousel-1.jpg"),
          require("@/assets/carousel/carousel-2.jpg"),
          require("@/assets/carousel/carousel-3.jpg"),
          require("@/assets/carousel/carousel-4.jpg")
        ],
        sideListBg:require("@/assets/bg-side-list.jpg"),
        brands:[
          {brandName:"上汽大众",brandId:'Volkswagen',topShop:[]},
          {brandName:"别克",brandId:'Buick',topShop:[]},
          {brandName:"雪佛兰",brandId:'Chevrolet',topShop:[]},
          {brandName:"宝骏",brandId:'BaoJun',topShop:[]},
          {brandName:"斯柯达",brandId:'SKODA',topShop:[]},
          {brandName:"荣威",brandId:'ROEWE',topShop:[]},
          {brandName:"MG",brandId:'MG',topShop:[]},
          {brandName:"凯迪拉克",brandId:'Cadillac',topShop:[]},
          {brandName:"五菱",brandId:'SGMW',topShop:[]},
          {brandName:"上汽大通",brandId:'MAXUS',topShop:[]}
        ],

        shops:[
          {
            link:{path:'/shop/Volkswagen'},
            img:'//i1.cximg.com/cms/carmall/upload/resources/image/2017/07/28/44813.jpg',
            title:'大众品牌旗舰店',short:'上汽大众',tag:'最高30000元金融补贴'
          },
          {
            link:{path:'/shop/Buick'},
            img:'//i2.cximg.com/cms/carmall/upload/resources/image/2017/07/28/44811.jpg',
            title:'别克品牌旗舰店',short:'别克',tag:'心静思远 志行千里'
          },
          {
            link:{path:'/shop/Chevrolet'},
            img:'//i2.cximg.com/cms/carmall/upload/resources/image/2017/07/28/44814.jpg',
            title:'雪佛兰品牌旗舰店',short:'雪佛兰',tag:'最高2000元大礼包'
          },
          {
            link:{path:'/shop/BaoJun'},
            img:'//i2.cximg.com/cms/carmall/upload/resources/image/2017/08/01/45243.jpg',
            title:'宝骏品牌旗舰店',short:'宝骏',tag:'国民爆款 畅销热卖'
          },
          {
            link:{path:'/shop/SKODA'},
            img:'//i1.cximg.com/cms/carmall/upload/resources/image/2017/07/28/44819.jpg',
            title:'斯柯达品牌旗舰店',short:'斯柯达',tag:'最高2年0利率'
          },
          {
            link:{path:'/shop/ROEWE'},
            img:'//i1.cximg.com/cms/carmall/upload/resources/image/2017/07/28/44812.png',
            title:'荣威品牌旗舰店',short:'荣威',tag:'互联网汽车 领先典范'
          },
          {
            link:{path:'/shop/MG'},
            img:'//i1.cximg.com/cms/carmall/upload/resources/image/2017/07/28/44816.jpg',
            title:'MG品牌馆',short:'MG',tag:'年轻时尚 英伦潮流'
          },
          {
            link:{path:'/shop/Cadillac'},
            img:'//i2.cximg.com/cms/carmall/upload/resources/image/2017/07/28/44817.jpg',
            title:'凯迪拉克品牌旗舰店',short:'凯迪拉克',tag:'豪华美系 总统座驾'
          },
          {
            link:{path:'/shop/SGMW'},
            img:'//i1.cximg.com/cms/carmall/upload/resources/image/2017/08/01/45242.jpg',
            title:'五菱品牌旗舰店',short:'五菱',tag:'家商两用 方便实惠'
          },
          {
            link:{path:'/shop/MAXUS'},
            img:'//i3.cximg.com/cms/carmall/upload/resources/image/2018/12/21/75205.jpg',
            title:'大通品牌馆',short:'上汽大通',tag:'大形象 大空间'
          },
        ],
        //导航栏内容
        navigationBarItem:[
          {text:"享推荐",anchor:"recommend"},
          {text:"品牌旗舰店",anchor:"brandShop"},
          {text:"全国实体店",anchor:"localShop"},
          {text:"贷款购车",anchor:"loan"},
        ],
      }
    },
    mounted() {
      getEachShopTopNumberCars().then(response=>{
        this.brands.forEach(item=>{
          item.topShop=response[item.brandId]
        })
      })
    },
    methods:{
      getImage(url){
        return strToUrlImage(url)
      }
    }
  }
</script>

<style lang="less" scoped>
  .header-brand{
    color: white;
    :visited{
      color: aquamarine;
    }
  }
  el-col{
    border: solid;
  }
  .block-kit{
    margin: 30px 0 12px;
    line-height: 46px;
    overflow: hidden;
  }
  .slogan{
    h2{
      float: left;
      font-size: 25px;
    }
    span{
      float: left;
      margin-top: 4px;
      margin-left: 10px;
    }
  }
  .clear-fix{
    position: relative;
  }
  .goods-box{
    background-color: #fff;
  }
  .custom-list-car{
    width: 720px;
    position: absolute;
    margin-top: -300px;
    margin-left: 360px;
    li{
      float: left;
      width: 180px;
      height: 210px;
      padding: 25px 10px;
      margin-right: 10px;
      background: #fff;
      text-align: center;
    }
  }
  .goods-title{
    font-size: 18px;
    height: 31px;
    overflow: hidden;
    margin-top: -5px;
  }
  .goods-subtitle{
    font-size: 16px;
    line-height: 30px;
    height: 28px;
    overflow: hidden;
  }
  .goods-price{
    color: #c71445;
    font-size: 24px;
  }
  .lazy{
    transition-property: margin-left;
    transition-duration: 0.5s;
    &:hover{
      margin-left: -20px;
    }
  }
  .shop-list{
    margin-top: 60px;
    margin-left: 1px;
  }
  .shop-item{
    width: 20%;
    height: 193px;
    border: 1px solid #ccc;
    float: left;
    text-align: center;
    margin: -1px 0 0 -1px;
    box-sizing: border-box;
    a img{
      width: 100px;
      height: 75px;
      margin: 0 auto;
      margin-top: 30px;
    }
    h4{
      font-size: 18px;
      font-weight: normal;
      color: #000;
      line-height: 30px;
      margin-top: 10px;
    }
    p{
      font-size: 14px;
      color: #999;
      margin-top: -20px;
    }
    &:hover{
      -webkit-box-shadow:0px 3px 3px #c8c8c8 inset;
      -moz-box-shadow:0px 3px 3px #c8c8c8 inset;
      box-shadow:0px 3px 3px #c8c8c8 inset;
    }
  }
  .el-menu-demo{
    width: 524px;
    margin-left: calc( 50% - 262px ) ;
    border: none;
    min-width: 524px;
    & li{
      & a{
        text-decoration: none;
        font-size: 16px;
        font-weight: bold;
      }

    }
  }
  .el-menu-demo li:first-child a{
    color: red;
  }
  .el-menu-demo li a:hover{
    color: red;
  }
  .classify{
    color: white;
    /*background-color: red;*/
    height: 350px;
    & .classify-item{
      width: 100%;
      height: 50%;
      & div{
        & span:first-child{
          position: absolute;
          left: 0px;
        }
      }

    }
  }
</style>
