<template>
    <div class="m-main">
      <div class="m-userinfo s-main">
        <div class="s-bar" >
          <router-link :to="{name:'User'}" class="s-avatar">
            <span class="s-edit-mask">编辑资料</span>
            <img :src="loginUser.avatar">
          </router-link>
          <div class="s-name" style="float: left;margin-top: 5px">
            <em>{{loginUser.name}}</em><br>
            (id:{{loginUser.id}})
          </div>
        </div>
      </div>
      <div class="m-carryinfo s-main">
        <div class="s-bar">
          <svg-icon icon-class="shopping-cart"></svg-icon>
          <label>我的订单</label>
        </div>
        <div>
          <el-table :data="tableDataOrder" max-height="600">
            <template v-for="o in dataTableOrder">
              <el-table-column v-if="o.val" :prop="o.val" :label="o.tag">
                <template slot-scope="scope">
                  {{scope.row[o.prop][o.val]}}
                </template>
              </el-table-column>
              <el-table-column v-else-if="o.prop==='orderStatus'" :label="o.tag">
                <template slot-scope="scope">
                  {{scope.row[o.prop]|OrderStatus}}
                </template>
              </el-table-column>
              <el-table-column v-else-if="o.prop==='orderTime'" :label="o.tag">
                <template slot-scope="scope">
                  {{scope.row[o.prop]|StringToTime}}
                </template>
              </el-table-column>
              <el-table-column v-else :prop="o.prop" :label="o.tag"></el-table-column>
            </template>
            <el-table-column label="操作" width="220" align="center" >
              <template slot-scope="scope">
                <el-button class="handle-button" v-if="scope.row['orderStatus']==='paid'||scope.row['orderStatus']==='delivering'" type="primary" size="mini" icon="el-icon-setting" @click="handleStatus(scope.$index, scope.row,'returning')">退货</el-button>
                <el-button class="handle-button" v-if="scope.row['orderStatus']==='unpaid'" type="primary" size="mini" icon="el-icon-setting" @click="handleToBalance(scope.$index, scope.row,'pay')">支付</el-button>
                <el-button class="handle-button" v-if="scope.row['orderStatus']==='delivered'" type="primary" size="mini" icon="el-icon-setting" @click="handleStatus(scope.$index, scope.row,'signed')">确认收货</el-button>
                <el-button class="handle-button" v-if="scope.row['orderStatus']==='returning'" type="danger" size="small" icon="el-icon-delete" @click="handleStatus(scope.$index, scope.row,'paid')">取消退货</el-button>
                <el-button class="handle-button" v-if="scope.row['orderStatus']==='signed'" type="primary" size="small" icon="el-icon-plus" @click="handleToBalance(scope.$index, scope.row,'assess')">评价</el-button>
                <el-button class="handle-button" v-if="scope.row['orderStatus']==='evaluated'" type="primary" size="small" icon="el-icon-plus" @click="handleToBalance(scope.$index, scope.row,'evaluated')">查看评价</el-button>
                <el-button class="handle-button-del" type="text" size="small" icon="el-icon-delete" @click="handleDel(scope.$index, scope.row)"></el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
    </div>
</template>

<script>
  import {getUserOrderListPage,changeOrderStatus} from "@/api/order";
  import {removeOrder} from "@/api/admin";

  export default {
    name: "index",
    data(){
      return{
        dataTableOrder:[
          {prop:"orderId",tag:"订单ID"},
          {prop:"car",val:'carName',tag:"车名"},
          {prop:"amount",tag:"数量"},
          {prop:"car",val:'price',tag:"单价"},
          {prop:"orderTime",tag:"下单时间"},
          {prop:"orderStatus",tag:"订单状态"},
        ],
        tableDataOrder:[],
        activeNow:'order',
      }
    },
    computed:{
      loginUser(){
        return this.$store.getters.user
      }
    },
    methods:{
      //获取订单信息
      getOrder(){
        getUserOrderListPage(this.loginUser.id).then(response=>{
          this.tableDataOrder=response
        })
      },
      //申请退货退款
      handleStatus(index,row,status){
        let confirmMsg='',successMsg='',failMsg=''
        if(status==='returning'){
          confirmMsg='确认申请退货?'
          successMsg='申请退货成功'
          failMsg='申请退货失败，请稍后重试'
        }else if(status==='paid'){
          confirmMsg='确认取消申请退货?'
          successMsg='取消申请退货成功'
          failMsg='取消申请退货失败，请稍后重试'
        }else if(status==='signed'){
          confirmMsg='确认收货?'
          successMsg='确认收货成功'
          failMsg='确认收货失败，请稍后重试'
        }
        this.$confirm(confirmMsg, '提示', {
          type: 'warning'
        }).then(()=>{
          let para=[]
          para.push({orderId:row.orderId,orderStatus:status})
          changeOrderStatus(para).then(response=>{
            if(response==='success'){
              this.$message.success({
                message:successMsg,
                showClose:false
              })
              this.getOrder()
            } else {
              this.$message.error({
                message:failMsg,
                showClose:false
              })
            }
          })
        }).catch(()=>{})
      },
      //用户支付订单跳转支付界面
      handleToBalance(index,row,type){
        this.$router.push({
          name:'Balance',
          params:{type:type,orderId:row.orderId}
        })
      },
      //用户删除订单
      handleDel(index, row){
        this.$confirm('确认删除该记录吗?', '提示', {
          type: 'warning'
        }).then(() => {
          let para=[
            {orderId:row['orderId']}
          ]
          removeOrder(para).then((res) => {
            if(res==='success'){
              this.$message({
                message: '删除成功',
                type: 'success'
              });
              this.getOrder();
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
      }
    },
    mounted() {
      this.getOrder()
    }
  }
</script>

<style lang="less" scoped>
  .handle-button-del{
    position: absolute;
    top:-5px;
    right: 10px;
  }
  .s-item{
    float: left;
    margin-left: 10px;
    margin-right: 10px;
  }
  .s-bar{
    text-align: left;
    position: relative;
    color: #546d7e;
    padding: 10px;
    line-height: 23px;
    height: 40px;
  }
  .s-main{
    margin-left: 10%;
    margin-right: 10%;
  }
  .m-main{
    .m-userinfo {
      border-radius: 10px;
      margin-bottom: 23px;
      font-size: 12px;
      color: #4b4b4b;
      position: relative;
      .s-avatar {
        float: left;
        display: block;
        width: 52px;
        height: 52px;
        margin-right: 13px;
        margin-left: 12px;
        position: relative;
        border: 2px solid #FFF;
        border-radius: 50%;
        overflow: hidden;
        .s-edit-mask {
          line-height: 1;
          padding-top: 23px;
          display: none;
          width: 100%;
          height: 34px;
          position: absolute;
          text-align: center;
          background: #000;
          color: #fff;
          opacity: .6;
          filter: alpha(opacity=60);
        }
        &:hover{
          span{
            display: inline;
          }
        }
        img{
          border-radius: 50%;
          width: 100%;
          height: 100%;
        }
      }
    }
  }

</style>
