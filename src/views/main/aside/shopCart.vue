<template>
  <div class="main" style="">
    <el-scrollbar style="min-width: 300px" id="scrollbar-table">
      <el-table :data="tableData" style="width: 300px;" @selection-change="selected">
        <!--选择方框-->
        <el-table-column type="selection" width="50"> </el-table-column>
        <!--显示商品信息-->
        <el-table-column label="商品名称" width="250">
          <template slot-scope="scope"><div style="margin-left: 50px">
            ![](scope.row.goods.img)
            <span style="font-size: 18px;padding-left: 200px;">{{scope.row.description}}</span>
          </div></template>
        </el-table-column>
      </el-table>
    </el-scrollbar>
    <el-scrollbar style="min-width: 300px" id="scrollbar-operate">
      <div id="operate">
        <!--商品总价格-->
        <div id="totalInfo">
          <label id="amount">已选{{amount}}件</label><label id="totalPrice">￥{{totalPrice}}</label>
        </div>
        <div id="balance"><el-button>结算</el-button></div>
      </div>
    </el-scrollbar>
  </div>
</template>

<script>
    export default {
      name: "ShopCart",
      data(){
        return{
          //TODO:获取用户购物车商品信息
          tableData:[
            {
              price:120,
              description:"123"
            },
            {
              price:120,
              description:"123"
            },
            {
              price:120,
              description:"123"
            },
            {
              price:120,
              description:"123"
            },
            {
              price:120,
              description:"123"
            },
            {
              price:120,
              description:"123"
            },
            {
              price:120,
              description:"123"
            },
            {
              price:120,
              description:"123"
            },
          ],
          amount:0,
          totalPrice:0,
        }
      },
      methods:{
        selected(val){
          let totalGoods = 0;
          let totalPrice = 0;
          for(let goods of val)
          {
            totalGoods += 1;
            totalPrice += Number(goods.price);
          }
          this.amount=totalGoods
          this.totalPrice=totalPrice
        }
      }
    }
</script>

<style lang="less" scoped>
  #scrollbar-table{
    height:80%;
    background-color: #fff;
    overflow-y: visible;
    z-index: 1;
  }
  div#scrollbar-operate {
    height:100%;
    background-color: #fff;
    z-index: 0;
  }
  div.el-scrollbar__bar.is-horizontal .el-scrollbar__thumb{
    display: none!important;
  }
  .main{

    height: 100%;
    background-color: #ffffff;
    min-width: 300px;
    #scrollbar-operate{
      position: absolute;
      width: 300px;
      bottom: 0px;
    }
    #operate{
      position: absolute;
      height: 80px;
      bottom: 0px;
      left:0px;
      right: 0px;
      #totalInfo{
        position: absolute;
        width: 300px;
        height: 21px;
        #amount{
          position: absolute;
          left: 10px;
        }
        #totalPrice{
          position: absolute;
          right: 10px;
        }
      }
      #balance{
        position: absolute;
        width: 300px;
        button{
          width: 100%;
          top:10px;
          margin-top: 30px;
        }
      }
    }
  }
</style>
