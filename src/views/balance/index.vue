<template>
    <div>
      <div>
        <el-steps :active="active" align-center finish-status="success">
          <el-step title="查看购物车"></el-step>
          <el-step title="提交订单"></el-step>
          <el-step title="付款"></el-step>
          <el-step title="确认收货"></el-step>
          <el-step title="评价"></el-step>
        </el-steps>
      </div>
      <div class="order" v-show="active===1">
        <h2>确认订单信息:</h2>
        <el-table :data="data">
          <el-table-column label="商品名称">
            <template slot-scope="scope">
              <img :src="getImagUrl(scope.row['car']['image']+'right.jpg')" width="90px" height="90px" style="float: left">
              <label style="font-size: 18px;display: inline-block;margin-left: 20px;padding-top: 30px">{{scope.row['car']['carName']}}</label>
            </template>
          </el-table-column>
          <el-table-column label="单价" prop="car.price">
            <template slot-scope="scope">
              {{scope.row['car']['price']}}
            </template>
          </el-table-column>
          <el-table-column label="数量" prop="amount"></el-table-column>
        </el-table>
        <div class="order-info">
          <label>付款:{{totalPrice.toFixed(2)}}万元</label>
          <label>寄送至:{{loginUser.loc}}</label>
          <label>收货人:{{loginUser.name}}</label>
          <el-button @click="onSubmit">提交订单</el-button>
        </div>
      </div>
      <div class="pay" v-show="active===2">
        <h2>扫码支付</h2>
        <img src="../../assets/QRCode.svg"><br>
        <el-button @click="onPayment">我已支付</el-button>
      </div>
      <div class="assess" v-show="active===4">
        <h2 style="float: left">评分:</h2>
        <el-rate style="text-align: left" v-model="evaluation.grade" show-text></el-rate><br>
        <h2 style="float: left">评价:</h2>
        <el-input v-model="evaluation.evaluate" type="textarea" :autosize="{ minRows: 6, maxRows: 20}" :rows="6"></el-input>
        <el-button type="primary" style="float: right" @click="onSubmitAssess">提交评价</el-button>
      </div>
      <div class="evaluated" v-show="active===5">

        <h2 style="float: left">评分:</h2>
        <el-rate style="text-align: left" v-model="evaluation.grade" show-text></el-rate><br>
        <h2 style="float: left">评价:</h2>
        <el-input v-model="evaluation.evaluate" type="textarea" :autosize="{ minRows: 6, maxRows: 20}" :rows="6"></el-input>
        <el-button type="primary" style="float: right" @click="onEditAssess">修改评价</el-button>
      </div>
    </div>
</template>

<script>
  import {addOrder,changeOrderStatus} from "@/api/order";
  import {getEvaluationListPage,addEvaluation,editEvaluation} from "@/api/Evaluation";
  import {editOrder} from "@/api/admin";
  import {strToUrlImage} from "@/utils";

  export default {
    name: "index",
    data(){
      return{
        data:[],
        active:1,
        orderId:[],
        evaluation:{
          grade:0,
          evaluate:'',
        }
      }
    },
    mounted() {
      let params=this.$route.params
      if(Object.keys(params).length===0){
        this.$alert('订单数据丢失,请返回购物车重新选择', '提示',{
          confirmButtonText: '确定',
          type: 'warning',
          center: true,
          roundButton:true
        }).then(_ => {
          this.$router.push({path:'/home'})
        }).catch(_ => {this.$router.push({path:'/home'})});;
      }else{
        if(params.type==='submit'){
          this.data=this.$route.params.data
        }else if(params.type==='pay'){
          this.orderId.push({orderId:params.orderId})
          this.active=2
        }else if(params.type==='assess'){
          this.orderId.push({orderId:params.orderId})
          this.active=4
        }else if(params.type==='evaluated'){
          getEvaluationListPage({orderId:params.orderId}).then(response=>{
            this.evaluation.grade=response.grade
            this.evaluation.evaluate=response.evaluate
            console.log(response)
          })
          this.orderId.push({orderId:params.orderId})
          this.active=5
        }
      }
    },
    computed:{
      totalPrice(){
        let total=0
        this.data.forEach(item=>{
          total+=item['amount']*item['car']['price']
        })
        return total
      },
      loginUser(){
        return this.$store.getters.user
      }
    },
    methods:{
      getImagUrl(str){
        return strToUrlImage(str)
      },
      onSubmit(){
        let para=[]
        this.data.forEach(item=>{
          let paraItem={}
          paraItem['carId']=item['car']['carId']
          paraItem['id']=item['user']['id']
          paraItem['amount']=item['amount']
          if(item['cartId']){
            paraItem['cartId']=item['cartId']
            paraItem['type']=1
          }else {
            paraItem['type']=0
          }
          para.push(paraItem)
        })
        addOrder(para).then(response=>{
          if(response){
            this.$message.success({
              message:'提交成功',
              showClose:false
            })
            this.orderId=response
            this.active=2
          }
        })
      },
      //提交评价
      onSubmitAssess(){
        addEvaluation({orderId:this.orderId[0].orderId,grade:this.evaluation.grade,evaluate:this.evaluation.evaluate}).then(response=>{
          if(response==='success'){
            this.$message.success({
              message:'评价成功',
              showClose:false
            })
            this.active=5
          }
          else{
            this.$message.error({
              message:'评价失败，请稍后重试',
              showClose:false
            })
          }
        })
      },
      //修改评价
      onEditAssess(){
        editEvaluation({orderId:this.orderId[0].orderId,grade:this.evaluation.grade,evaluate:this.evaluation.evaluate}).then(response=>{
          if(response==='success'){
            this.$message.success({
              message:'修改评价成功',
              showClose:false
            })
            this.active=5
          }
          else{
            this.$message.error({
              message:'修改评价失败，请稍后重试',
              showClose:false
            })
          }
        })
      },
      //支付成功，改变订单状态
      onPayment(){
        let para=[]
        this.orderId.forEach(item=>{
          para.push({orderId:item.orderId,orderStatus:'paid'})
        })
        changeOrderStatus(para).then(response=>{
          if(response==='success'){
            this.$message.success({
              message: '支付成功',
              showClose: false
            })
            this.active=3
          }
          else{
            this.$message.error({
              message: '支付失败，请稍后重试',
              showClose: false
            })
          }
        })
      }
    }
  }
</script>

<style lang="less" scoped>
  .order{
    & h2{
      float: left;
    }
    .order-info{
      width: 100%;
      button{
        width: 100px;
        margin-left: calc(100% - 100px);
      }
      label{
        text-align: right;
        width: 100%;
        display: block;
      }
    }
  }
</style>
