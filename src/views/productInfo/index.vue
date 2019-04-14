<template>
    <div style="margin-top: 20px">
      <!--顶部面包屑-->
      <el-breadcrumb separator-class="el-icon-arrow-right" style="width: 100%">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/shop/'+carInfo.brand.brandId }">{{carInfo.brand.brandName}}</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/' }">{{carInfo.carName}}</el-breadcrumb-item>
      </el-breadcrumb>
      <!--商品信息-->
      <el-row :gutter="24" style="margin-top: 20px">
        <el-col :span="9">
          <el-carousel id="product-large" indicator-position="outside" height="260.6px">
            <el-carousel-item v-for="item in carouselImg" :key="item" style="background-color: #000">
              <img width="391" :src="item">
            </el-carousel-item>
          </el-carousel>
        </el-col>
        <el-col :span="15" class="info">
          <div class="info-item" style="margin-top: 20px">
            <h1 class="car-name">{{carInfo.carName}}</h1>
          </div>
          <div class="info-item" style="">
            <div class="car-tag">{{carInfo.tag}}</div>
          </div>
          <el-form class="submit" style="width: 50%;margin: auto">
            <el-form-item label="裸车价">
              <strong class="car-price"><span class="yen">￥</span><span>{{carInfo.price}}</span>万<span></span></strong>
            </el-form-item>
            <el-form-item label="服务承诺" class="promise">
              <span>官方车源</span><span>支付安全</span><span>全国联保</span>
            </el-form-item>
            <el-form-item>
              <el-button @click="toBuy">立即购买</el-button>
              <el-button @click="addCartItem">加入购物车</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <!--商品详细信息-->
      <div>
        <!--侧边固定栏-->
        <div id="sideBar" :class="sideBarFixed === true ? 'isFixed' :''" style="float:left;width:200px;">
          <img width="100%" height="133.3px" :src="carouselImg[0]"/>
          <div style="position: relative;height: 40px;margin-top: 20px">
            <strong class="car-price"><span class="yen">￥</span><span>{{carInfo.price}}</span>万<span></span></strong>
          </div>
          <el-button style="width: 100%" @click="toBuy">立即购买</el-button>
          <el-button style="width: 100%;margin-left: 0px" @click="addCartItem">加入购物车</el-button>
        </div>
        <!--商品图片信息-->
        <div  style="float:right;width:calc(100% - 220px);background-color: white">
          <el-menu :class="sideBarFixed === true ? 'isFixed' :''" style="width: 864px" default-active="1" class="el-menu-demo" mode="horizontal">
            <el-menu-item v-for="(o,index) in navigationBarItem" :key="index">
              <el-button type="text" v-anchor="o.anchor">{{o.text}}</el-button>
            </el-menu-item>
          </el-menu>
          <div id="anchor-introduce">
            <div class="header"><label>商品介绍</label></div>
            <img v-for="o in displayImg" :src="o" width="864.81px">
          </div>
        </div>
        <div class="param">
          <span id="anchor-parameter"></span>
          <div class="header"><label>车辆参数</label></div>
          <table style="width: 100%">
            <template v-for="(o,index) in paramItem">
              <tr class="param-item" :style="{backgroundColor:(index%2===0?'#f9f7f7':'#ffffff')}">
                <td class="param-item-left">{{o.tag}}</td><td class="param-item-right">{{carInfo.param[o.prop]}}</td>
              </tr>
            </template>
          </table>
          <div class="item-text" style="margin-bottom: 50px">注：以上参数配置信息仅供参考，实际参数配置信息以经销商销售车辆为准，解释权归生产厂家所有。</div>
        </div>
        <div id="anchor-evaluate" style="float:right;width:calc(100% - 220px);min-height: 800px">
          <div class="header"><label>商品评价({{evaluateTotal}})</label></div>
          <ul>
            <li v-for="o in evaluations">
              <div class="evaluate-info">
                <div class="evaluate-user item-text">
                  <div class="evaluate-user-name">{{o.user.id}}</div>
                  <div class="evaluate-user-time">{{o.evaTime|StringToTime}}</div>
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
  import {addOrder} from "@/api/order";
  import {addCart} from "@/api/shoppingCart";
  import { pca, pcaa } from "area-data";
  import {getEvaluationListPage} from "@/api/Evaluation";
  import {strToUrlImage} from "@/utils";

  export default {
    name: "GoodsInfo",
    data(){
      return{
        carInfo:{
          carId:'car1',
          carName:'上汽大众 凌渡 280TSI DSG米色内饰限量版',
          brand:{
            brandName:'上汽大众'
          },
          param:{},
          price:'14.59',
          image:'',
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
        carouselImg:[],
        displayImg:[],
        carouselImgPath:[
          ['right.jpg','front.jpg','left.jpg','after.jpg'],
          ['display1.jpg','display2.jpg']
        ],
        placeholders: ['选择省','选择市','选择区'],
        pcaa:pcaa,
        evaluateTotal:0,
        evaluations:[
          {
            user:{id:'李**'},
            evaTime:'20190416181721',
            evaluate:'',
            grade:3,
          },
        ],
        paramItem:[
          {prop:'carSize',tag:"车身尺寸"},
          {prop:'engine',tag:"发动机"},
          {prop:'level',tag:"级别"},
          {prop:'structure',tag:"车身结构"},
          {prop:'assurance',tag:"整车质保"},
          {prop:'oilWear',tag:"油耗"},
          {prop:'manufacturer',tag:"厂商"},
          {prop:'gearbox',tag:"变速箱"},
          {prop:'maxSpeed',tag:"最高车速"},
        ]
      }
    },
    methods:{
      handleScroll () {
        let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
        //let offsetTop = document.querySelector('#sideBar').offsetTop
        scrollTop > this.offsetTop? this.sideBarFixed = true : this.sideBarFixed = false
      },
      toBuy(){
        if(this.loginUser.status){
          this.$router.push({
            name:'Balance',
            params:{type:'submit',data:[{amount:1,car:this.carInfo,user:this.loginUser}]}
          })
        }else
          this.$message.error({
            message:'未登录，请登陆后重试',
            showClose:false
          })
      },
      addCartItem(){
        if(this.loginUser.status)
          addCart(this.loginUser.id,this.carInfo.carId,1).then(response=>{
            if(response==='success'){
              this.$message.success({
                message:'加入购物车成功',
                showClose:false
              })
              this.$store.dispatch('ChangeRefreshCart')
            }else {
              this.$message.error({
                message:'加入购物车失败，请稍后重试',
                showClose:false
              })
            }
          })
        else
          this.$message.error({
            message:'未登录，请登陆后重试',
            showClose:false
          })
      },
      getImage(url){
        return strToUrlImage(url)
      }
    },
    mounted () {
      window.addEventListener('scroll', this.handleScroll)
      this.offsetTop=document.querySelector('#sideBar').offsetTop
      getCarInfo(this.$route.params.id).then(response=>{
        this.carInfo=response
        this.carouselImgPath[0].forEach(item=>{
          this.carouselImg.push(this.getImage(response.image+item))
        })
        this.carouselImgPath[1].forEach(item=>{
          this.displayImg.push(this.getImage(response.image+item))
        })
        console.log(this.carouselImg)
        getEvaluationListPage({carId:this.carInfo.carId,page:1}).then(response1=>{
          this.evaluations=response1.data
          this.evaluateTotal=response1.total
        })
      })
    },
    computed:{
      loginUser(){
        return this.$store.getters.user
      }
    }
  }
</script>

<style lang="less" scoped>
  .param{
    float:right;
    width:calc(100% - 220px);
    height: 500px;
    .param-item{
      width: 100%;
      height: 36px;
      .param-item-left{
        padding:10px;
        padding-right:50px;
        text-align: right;
        width: 50%;
      }
      .param-item-right{
        padding:10px;
        padding-left:50px;
        text-align: left;
        width: 50%;
      }
    }
  }
  strong.car-price{
    font-size: 23px;
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
    top:0px;
    z-index: 1000;
  }
  .item-text{
    text-align: left;
    margin-left: 40px;
  }
  .header{
    background-color: #f9f7f7;
    height: 60px;
    font-size: 20px;
    width: 100%;
    position: relative;
    text-align: left;
    label{
      width: 100%;
      position: absolute;
      left: 35px;
      top: 20px;
    }
  }
  .evaluate-info{
    border-top: 1px solid #e8e8e8;
    padding-top:50px;
    padding-bottom:50px;
    .evaluate-user{
      width: 80%;
      .evaluate-user-name{
        float: left;
      }
      .evaluate-user-time{
        float: right;
        text-align: right;
        margin-left: 40px;
      }
    }
    .evaluate-rate{
      width: 80%;
      margin-top: 30px;
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
