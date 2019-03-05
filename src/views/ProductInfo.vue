<template>
    <div>
      <h4>{{this.$route.params.id}}</h4>
      <el-row :gutter="24">
        <el-col :span="9">
          <el-carousel id="product-large" indicator-position="outside">
            <el-carousel-item v-for="item in 4" :key="item" style="background-color: #000">
              <h3>{{ item }}</h3>
            </el-carousel-item>
          </el-carousel>
        </el-col>
        <el-col :span="15">
          <div class="base-info-cont-name">
            <h1>上汽大众 凌渡 280TSI DSG米色内饰限量版</h1>
            <div class="car-ad">【车享自营】综合优惠：46818元，手快抢10万公里免保</div>
          </div>
          <div class="naked-car-price-mod">
            <div class="naked-car-price">
              <div class="summary-price">
                <span class="summary-price-name item-title">裸车价</span><strong><span class="yen">¥</span><span>14.59</span>万<span></span></strong>
                <span class="zd-price">指导价<span>¥18.49</span>万</span>
              </div>
            </div>
          </div>
          <div class="from-operate">
            <div class="form-outside-color">
              <label class="item-title" style="float: left">车身颜色</label>
              <div style="height: 30px">
                <ul class="color-list">
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
              </div>
            </div>
            <div class="form-region">
              <label class="item-title">提车地区</label>
              <el-select value="1"></el-select>
              <el-select value="1"></el-select>
            </div>
          </div>
          <div class="form-submit">
            <el-button>立即购买</el-button>
            <el-button>加入购物车</el-button>
          </div>
        </el-col>
      </el-row>
      <div>
        <div id="sideBar" :class="sideBarFixed == true ? 'isFixed' :''" style="float:left;width:200px;height: 600px;background-color: red">

        </div>
        <div style="float:right;width:calc(100% - 220px);height: 1000px;background-color: black"></div>
      </div>
      </div>
    </div>
</template>

<script>
  export default {
    name: "GoodsInfo",
    data(){
      return{
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
  #product-large{

  }
  .base-info-cont-name h1 {
    width: 70%;
    padding-bottom: 12px;
    font-size: 18px;
    color: #333;
    font-weight: normal;
    line-height: 22px;
  }
  .car-ad {
    font-size: 14px;
    color: #c71445;
    overflow: hidden;
    text-overflow: ellipsis;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    display: -webkit-box;
    width: 70%;
    height: 44px;
    line-height: 22px;
  }
  .naked-car-price {
    position: relative;
    padding: 19px 0 20px 20px;
    .summary-price{
      position: relative;
      z-index: 4;
      color: #666;
      font-size: 0;
      strong{
        font-size: 24px;
        color: #c71445;
        padding: 0 15px 0 7px;
        font-weight: 100;
        display: inline-block;
        vertical-align: middle;
        line-height: 100%;
        height: 29px;
      }
      .zd-price{
        font-size: 12px;
        margin-right: 260px;
        color: #999;
      }
    }
  }
  .item-title{
    min-width: 56px;
    margin-right: 26px;
    font-size: 14px;
    color: #666;
    display: inline-block;
    vertical-align: middle;
    text-align: justify;
    text-align-last: justify;
  }
  .isFixed{
    position: fixed;
    top:5px;
  }
</style>
