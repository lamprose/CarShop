<template>
  <div class="main" style="">
    <el-scrollbar style="min-width: 300px" id="scrollbar-table">
      <el-table :data="tableData" style="width: 300px;" @selection-change="selected">
        <!--选择方框-->
        <el-table-column type="selection" width="50"> </el-table-column>
        <!--显示商品信息-->
        <el-table-column label="商品名称" width="250">
          <template slot-scope="scope"><div style="position: relative">
            <img :src="scope.row.img" width="90px" style="display: inline">
            <span style="font-size: 18px;position: absolute;top: 10px;left: 100px">{{scope.row.name}}</span>
            <span style="font-size: 18px;position: absolute;top: 30px;left: 100px">{{scope.row.price}}</span>
            <el-input-number style="position: absolute;top:50px;left: 100px" size="mini" v-model="scope.row.amount" @change="handleChange" :min="1" :max="10" label="描述文字"></el-input-number>
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
        <div id="balance"><el-button @click="balance">结算</el-button></div>
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
              name:'123',
              img:"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
              price:120,
              amount:0
            },
            {
              name:'123',
              img:"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
              price:120,
              amount:2
            },
            {
              name:'1234',
              img:"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
              price:120,
              amount:1
            },
            {
              name:'12',
              img:"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
              price:120,
              amount:3
            },
            {
              name:'132',
              img:"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
              price:120,
              amount:1
            },
            {
              name:'12312',
              img:"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
              price:120,
              amount:3
            },
            {
              name:'1231',
              img:"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
              price:120,
              amount:2
            },
            {
              name:'1231',
              img:"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
              price:120,
              amount:1
            },
          ],
          amount:0,
          totalPrice:0,
          selItems:[],
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
          this.selItems=val
        },
        balance(){
          this.$router.push({
            name:'Balance',
            params:{data:this.selItems}
          })
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
