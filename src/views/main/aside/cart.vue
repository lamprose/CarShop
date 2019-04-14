<template>
  <div class="main" style="">
    <el-scrollbar style="min-width: 300px" id="scrollbar-table">
      <el-table :data="tableData" style="width: 300px;" @selection-change="selItemsChange">
        <!--选择方框-->
        <el-table-column type="selection" width="50"> </el-table-column>
        <!--显示商品信息-->
        <el-table-column label="商品名称" width="250">
          <template slot-scope="scope"><div style="position: relative">
            <img :src="getImagUrl(scope.row['car']['image']+'right.jpg')" :alt="scope.row['car']['image']" width="90px" height="90px" style="display: inline">
            <span style="font-size: 18px;position: absolute;top: 10px;left: 100px">{{scope.row['car']['carName']}}</span>
            <span style="font-size: 18px;position: absolute;top: 30px;left: 100px;color: red;font-size: 13px">￥{{scope.row['car']['price']}}万元</span><!--@change="handleChange"-->
            <el-button icon="el-icon-delete" type="text" style="position: absolute;top:0px;right: 10px" @click="handleDel(scope.$index, scope.row)"></el-button>
            <el-input-number style="position: absolute;top:50px;left: 100px" size="mini" v-model="scope.row.amount"  :min="1" :max="10" label="描述文字" @change="countTotal"></el-input-number>
          </div></template>
        </el-table-column>
      </el-table>
    </el-scrollbar>
    <el-scrollbar style="min-width: 300px" id="scrollbar-operate">
      <div id="operate">
        <!--商品总价格-->
        <div id="totalInfo">
          <label id="amount">已选{{amount}}件</label><label id="totalPrice">￥{{totalPrice.toFixed(2)}}万元</label>
        </div>
        <div id="balance">
          <el-button type="danger" style="left: 0px" @click="batchRemove" :disabled="this.selItems.length===0">批量删除</el-button>
          <el-button style="margin-left: 0px" @click="balance" :disabled="this.selItems.length===0">结算</el-button>
        </div>
      </div>
    </el-scrollbar>
  </div>
</template>

<script>
  import {getCartInfo} from "@/api/shoppingCart";
  import {getToken} from "@/utils/auth";
  import {removeCart} from "@/api/shoppingCart";
  import {strToUrlImage} from "@/utils";

  export default {
    name: "ShopCart",
    data(){
      return{
        //用户购物车商品信息
        tableData:[],
        amount:0,
        totalPrice:0,
        selItems:[],
      }
    },
    methods:{
      selItemsChange(sels) {
        this.selItems = sels;
      },
      countTotal(){
        let totalGoods = 0;
        let totalPrice = 0;
        for(let goods of this.selItems)
        {
          totalGoods += 1;
          totalPrice += Number(goods.amount)* Number(goods.car.price);
        }
        this.amount=totalGoods
        this.totalPrice=totalPrice
        this.selItems=val
      },
      balance(){
        this.$router.push({
          name:'Balance',
          params:{type:'submit',data:this.selItems}
        })
      },
      //获取用户购物车商品信息
      getCart(){
        if(this.token&&this.loginUserId!=='')
          getCartInfo(this.loginUserId).then(response=>{
            this.tableData=response
          })
      },
      //删除
      handleDel(index, row) {
        this.$confirm('确认删除该记录吗?', '提示', {
          type: 'warning'
        }).then(() => {
          let para=[
            {cartId:row['cartId']}
          ]
          removeCart(para).then((res) => {
            if(res==='success'){
              this.listLoading = false;
              this.$message({
                message: '删除成功',
                type: 'success'
              });
              this.getCart();
            }
            else if(res==='fail'){
              this.listLoading = false;
              this.$message({
                message: '删除失败',
                type: 'warning'
              });
            }
          });
        }).catch(() => {

        });
      },
      //批量删除
      batchRemove() {
        this.$confirm('确认删除选中记录吗？', '提示', {
          type: 'warning'
        }).then(() => {
          let para=[]
          this.selItems.forEach(item=>{
            let itemPara={}
            itemPara['cartId']=item['cartId']
            para.push(itemPara)
          })
          removeCart(para).then((res) => {
            if(res==='success'){
              this.$message({
                message: '删除成功',
                type: 'success'
              });
              this.getCart();
            }
            else if(res==='fail'){
              this.$message({
                message: '删除失败',
                type: 'warning'
              });
            }
          });
        }).catch(() => {

        });
      },
      getImagUrl(str){
        return strToUrlImage(str)
      }
    },
    computed:{
      loginUserId(){
        return this.$store.getters.id;
      },
      token(){
        return getToken('token')
      },
      refreshCart(){
        return this.$store.getters.refreshCart;
      }
    },
    mounted() {
      this.getCart()
    },
    watch:{
      loginUserId(){
        this.getCart()
      },
      refreshCart(val){
        this.getCart()
      },
      selItems(val){
        this.countTotal()
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
          width: 50%;
          top:10px;
          margin-top: 30px;
          float: left;
        }
      }
    }
  }
</style>
