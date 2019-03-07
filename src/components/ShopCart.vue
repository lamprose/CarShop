<template>
  <div class="main">
    <el-scrollbar id="scrollbar-table">
      <el-table :data="tableData" style="width: 300px;" @selection-change="selected">
        <!--选择方框-->
        <el-table-column type="selection" width="50"> </el-table-column>
        <!--显示商品信息-->
        <el-table-column label="商品名称" width="250">
          <template slot-scope="scope"><div style="margin-left: 50px">
            ![](scope.row.goods.img)
            <span style="font-size: 18px;padding-left: 200px;">{{scope.row.goods.description}}</span>
          </div></template>
        </el-table-column>
      </el-table>
    </el-scrollbar>
    <el-scrollbar id="scrollbar-operate">
      <div id="operate">
        <!--商品总价格-->
        <div id="totalInfo">
          <label id="amount">已选3件</label><label id="totalPrice">￥1223</label>
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
            tableData:[
              {
                goods:{
                  description:"123"
                }
              },
              {
                goods:{
                  description:"123"
                }
              },
              {
                goods:{
                  description:"123"
                }
              },
              {
                goods:{
                  description:"123"
                }
              },
              {
                goods:{
                  description:"123"
                }
              },
              {
                goods:{
                  description:"123"
                }
              },
            ],
          }
      }
    }
</script>

<style lang="less" scoped>
  div#scrollbar-table{
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
    display: block!important;
  }
  .main{
    height: 100%;
    background-color: #ffffff;
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
          left: 0px;
        }
        #totalPrice{
          position: absolute;
          right: 0px;
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
