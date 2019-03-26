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
          <el-form class="submit" style="width: 50%;margin: auto">
            <el-form-item label="裸车价">
              <strong class="car-price"><span class="yen">￥</span><span>{{productInfo.price}}</span>万<span></span></strong>
              <span class="car-guidingPrice">指导价<span>{{productInfo.guidingPrice}}</span>万</span>
            </el-form-item>
            <el-form-item label="服务承诺" class="promise">
              <span>官方车源</span><span>支付安全</span><span>全国联保</span>
            </el-form-item>
            <!--<el-form-item label="提车区域">
              <area-select id="area-select" v-model="area" type="text" :placeholders='placeholders' :level="2" :data="pcaa"></area-select>
            </el-form-item>-->
            <el-form-item>
              <el-button>立即购买</el-button>
              <el-button>加入购物车</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <!--商品详细信息-->
      <div>
        <!--侧边固定栏-->
        <div id="sideBar" :class="sideBarFixed === true ? 'isFixed' :''" style="float:left;width:200px;">
          <img width="80%" height="150px" src="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"/>
          <div style="position: relative;height: 40px;margin-top: 20px">
            <strong class="car-price"><span class="yen">￥</span><span>{{productInfo.price}}</span>万<span></span></strong>
            <span class="car-guidingPrice" style="top:5px">指导价<span>{{productInfo.guidingPrice}}</span>万</span>
          </div>
          <el-button style="width: 100%">立即购买</el-button>
          <el-button style="width: 100%;margin-left: 0px">加入购物车</el-button>
        </div>
        <!--商品图片信息-->
        <div  style="float:right;width:calc(100% - 220px);height: 1000px;background-color: white">
          <el-menu :class="sideBarFixed === true ? 'isFixed' :''" style="width: 986px" default-active="1" class="el-menu-demo" mode="horizontal">
            <el-menu-item v-for="(o,index) in navigationBarItem" :key="index">
              <el-button type="text" v-anchor="o.anchor">{{o.text}}</el-button>
            </el-menu-item>
          </el-menu>
          <div id="anchor-introduce">
            <div class="header"><label>商品介绍</label></div>
          </div>
        </div>
        <div  style="float:right;width:calc(100% - 220px);height: 1000px;background-color: red">
          <span id="anchor-parameter"></span>
          <div class="header"><label>车辆参数</label></div>
          <ul>
            <li v-for="i in 13" style="margin-top: 30px"><label style="margin-right: 100px">你好</label><label>哈哈</label></li>
          </ul>
          <div class="item-text" style="margin-bottom: 50px">注：以上参数配置信息仅供参考，实际参数配置信息以经销商销售车辆为准，解释权归生产厂家所有。</div>
        </div>
        <div id="anchor-evaluate" style="float:right;width:calc(100% - 220px);height: 1000px;background-color: blue">
          <div class="header"><label>商品评价</label></div>
          <ul>
            <li v-for="o in evaluates">
              <div class="evaluate-info">
                <div class="evaluate-user item-text">
                  <div class="evaluate-user-name">{{o.id}}</div>
                  <div class="evaluate-user-time">{{o.time}}</div>
                </div>
                <div class="evaluate-rate item-text">
                  <div class="evaluate-rate-item"><el-rate v-model="o.grade" show-score disabled></el-rate></div>
                </div>
                <div class="evaluate-text item-text"><label class="text">{{o.evaluate}}</label></div>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
</template>

<script>
  import {getCarInfo} from "@/api/car";
  import { pca, pcaa } from "area-data";

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
        navigationBarItem:[
          {text:"商品介绍",anchor:"introduce"},
          {text:"车辆参数",anchor:"parameter"},
          {text:"商品评价",anchor:"evaluate"},
        ],
        area:[],
        areaLoc:'',
        placeholders: ['选择省','选择市','选择区'],
        pcaa:pcaa,
        evaluates:[
          {id:'李**',time:"2019-04-07",grade:1,evaluate:'服务很好'},
          {id:'饶**',time:"2019-04-08",grade:2,evaluate:'服务很好服务很好'},
          {id:'宋**',time:"2019-04-09",grade:3,evaluate:'服务很好服务很好服务很好'},
          {id:'谭**',time:"2019-04-10",grade:4,evaluate:'服务很好服务很好服务很好服务很好'},
        ]
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
      getCarInfo(this.$route.params.id).then(data=>{
        this.productInfo=data
      })
    },
  }
</script>

<style lang="less" scoped>
  strong.car-price{
    font-size: 24px;
    color: #c71445;
    padding: 0 15px 0 7px;
    font-weight: 100;
    display: inline-block;
    vertical-align: middle;
    line-height: 100%;
    height: 29px;
    position: absolute;
    right: 70px;
  }
  .car-guidingPrice{
    position: absolute;
    right: 0px;
    font-size: 12px;
    color: #999;
  }
  .promise span{
    margin-left: 20px;
  }
  .isFixed{
    position: fixed;
    top:5px;
    z-index: 1000;
  }
  .item-text{
    text-align: left;
    margin-left: 40px;
  }
  .header{
    background-color: #d0cdc7;
    height: 60px;
    font-size: 20px;
    width: 100%;
    position: relative;
    label{
      width: 85px;
      position: absolute;
      left: 35px;
      top: 20px;
    }
  }
  .evaluate-info{
    margin-top: 40px;
    .evaluate-user{
      width: 80%;
      .evaluate-user-name,.evaluate-user-time{
        display: inline;
      }
    }
    .evaluate-rate{
      width: 80%;
      margin-top: 10px;
    }
    .evaluate-text{
      width: 100%;
      .text{
        display:block;
        width: 80%;
        max-width: 100%;
        margin-top: 10px;
      }
    }
  }
  .info{
    height: 300px;
    .submit{

    }
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
