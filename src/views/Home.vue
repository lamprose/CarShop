<template>
    <div>
      <div>
        <el-row :gutter="24">
          <!--显示分类列表-->
          <el-col :span="5">
            <div>
              <h2>saasdas</h2>
              <h2>323432</h2>
            </div>
          </el-col>
          <!--广告位跑马灯-->
          <el-col :span="19">
            <el-carousel indicator-position="none">
              <el-carousel-item v-for="item in 4" :key="item">
                <h3>{{ item }}</h3>
              </el-carousel-item>
            </el-carousel>
          </el-col>
        </el-row>
      </div>
      <!--享推荐-->
      <div id="recommend">
        <div class="block-kit">
          <!--标题-->
          <div class="slogan">
            <h2>享推荐</h2><span>为你所享，推荐所需</span>
          </div>
          <div v-for="i in 4">
            <div class="clear-fix">
              <img src="../assets/reTab1.jpg" width="1190px">
              <div class="goods-box">
                <ul class="custom-list-car">
                  <li>
                    <a href="//car.chexiang.com/product/1092403.htm" target="_blank" ca="v170727_cm-home_pc_customization_c2d1$凌渡$">
                      <h3 class="goods-title">凌渡米色内饰限量版</h3>
                      <p class="goods-subtitle">综合优惠4.68万元</p>
                      <p class="goods-price">￥14.59万</p>
                      <img class="lazy goods-img" width="180" height="120" data-original="//i1.cximg.com/cms/carmall/upload/resources/image/2018/05/25/65565.jpg" src="//i1.cximg.com/cms/carmall/upload/resources/image/2018/05/25/65565.jpg" style="display: inline;">
                    </a>
                  </li>
                  <li>
                    <a href="//car.chexiang.com/product/1092403.htm" target="_blank" ca="v170727_cm-home_pc_customization_c2d1$凌渡$">
                      <h3 class="goods-title">凌渡米色内饰限量版</h3>
                      <p class="goods-subtitle">综合优惠4.68万元</p>
                      <p class="goods-price">￥14.59万</p>
                      <img class="lazy goods-img" width="180" height="120" data-original="//i1.cximg.com/cms/carmall/upload/resources/image/2018/05/25/65565.jpg" src="//i1.cximg.com/cms/carmall/upload/resources/image/2018/05/25/65565.jpg" style="display: inline;">
                    </a>
                  </li>
                  <li>
                    <a href="//car.chexiang.com/product/1092403.htm" target="_blank" ca="v170727_cm-home_pc_customization_c2d1$凌渡$">
                      <h3 class="goods-title">凌渡米色内饰限量版</h3>
                      <p class="goods-subtitle">综合优惠4.68万元</p>
                      <p class="goods-price">￥14.59万</p>
                      <img class="lazy goods-img" width="180" height="120" data-original="//i1.cximg.com/cms/carmall/upload/resources/image/2018/05/25/65565.jpg" src="//i1.cximg.com/cms/carmall/upload/resources/image/2018/05/25/65565.jpg" style="display: inline;">
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div id="brandShop">
        <div class="block-kit">
          <!--标题-->
          <div class="slogan">
            <h2>品牌旗舰店</h2><span>下单送好礼</span>
          </div>
          <div></div>
          <!--品牌旗舰店列表-->
          <div>
            <ul class="shop-list">
              <li class="shop-item" v-for="shop in shops">
                <a :href="shop.link">
                  <img alt="" :src="shop.img" style="display: block;">
                  <h4>{{shop.title}}</h4>
                  <p>{{shop.tag}}</p>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
    export default {
        name: "Home",
      data(){
          return{
            shops:[
              {
                link:'//c.chexiang.com/promotion/brands/volkswagen/index.htm',
                img:'//i1.cximg.com/cms/carmall/upload/resources/image/2017/07/28/44813.jpg',
                title:'大众品牌旗舰店',tag:'最高30000元金融补贴'
              },
              {
                link:'//c.chexiang.com/promotion/brands/buick/index.htm',
                img:'//i2.cximg.com/cms/carmall/upload/resources/image/2017/07/28/44811.jpg',
                title:'别克品牌旗舰店',tag:'心静思远 志行千里'
              },
              {
                link:'//c.chexiang.com/promotion/brands/chevrolet/index.htm',
                img:'//i2.cximg.com/cms/carmall/upload/resources/image/2017/07/28/44814.jpg',
                title:'雪佛兰品牌旗舰店',tag:'最高2000元大礼包'
              },
              {
                link:'//c.chexiang.com/promotion/brands/wuling/index.htm',
                img:'//i2.cximg.com/cms/carmall/upload/resources/image/2017/08/01/45243.jpg',
                title:'宝骏品牌旗舰店',tag:'国民爆款 畅销热卖'
              },
              {
                link:'//c.chexiang.com/promotion/brands/skoda/index.htm',
                img:'//i1.cximg.com/cms/carmall/upload/resources/image/2017/07/28/44819.jpg',
                title:'斯柯达品牌旗舰店',tag:'最高2年0利率'
              },
              {
                link:'//c.chexiang.com/promotion/brands/roewe/index.htm',
                img:'//i1.cximg.com/cms/carmall/upload/resources/image/2017/07/28/44812.png',
                title:'荣威品牌旗舰店',tag:'互联网汽车 领先典范'
              },
              {
                link:'//c.chexiang.com/promotion/brands/mg/index.htm',
                img:'//i1.cximg.com/cms/carmall/upload/resources/image/2017/07/28/44816.jpg',
                title:'MG品牌馆',tag:'年轻时尚 英伦潮流'
              },
              {
                link:'//c.chexiang.com/promotion/brands/cadillac/index.htm',
                img:'//i2.cximg.com/cms/carmall/upload/resources/image/2017/07/28/44817.jpg',
                title:'凯迪拉克品牌旗舰店',tag:'豪华美系 总统座驾'
              },
              {
                link:'//c.chexiang.com/promotion/brands/wuling/index.htm',
                img:'//i1.cximg.com/cms/carmall/upload/resources/image/2017/08/01/45242.jpg',
                title:'五菱品牌旗舰店',tag:'家商两用 方便实惠'
              },
              {
                link:'//c.chexiang.com/promotion/brands/maxus/index.htm',
                img:'//i3.cximg.com/cms/carmall/upload/resources/image/2018/12/21/75205.jpg',
                title:'大通品牌馆',tag:'大形象 大空间'
              },

            ]
          }
      }
    }
</script>

<style lang="less" scoped>
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
</style>
