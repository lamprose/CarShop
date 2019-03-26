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
              <img :src="scope.row.img" width="100px">
              <span style="font-size: 18px;display: block">{{scope.row.description}}</span>
            </template>
          </el-table-column>
        </el-table>
        <div class="order-info">
          <label>付款:</label>
          <label>寄送至:</label>
          <label>收货人:</label>
          <el-button @click="active=2">提交订单</el-button>
        </div>
      </div>
      <div class="pay" v-show="active===2">
        <h2>扫码支付</h2>
        <img src="../../assets/QRCode.svg"><br>
        <el-button @click="active=3">我已支付</el-button>
      </div>
    </div>
</template>

<script>
  export default {
    name: "index",
    data(){
      return{
        data:[],
        active:1
      }
    },
    mounted() {
      if(Object.keys(this.$route.params).length===0){
        this.$alert('订单数据丢失,请返回购物车重新选择', '提示',{
          confirmButtonText: '确定',
          type: 'warning',
          center: true,
          roundButton:true
        }).then(_ => {
          this.$router.push({path:'/home'})
        }).catch(_ => {this.$router.push({path:'/home'})});;
      }else
        this.data=this.$route.params.data
    }
  }
</script>

<style lang="less" scoped>
  .order{
    & h2{
      float: left;
    }
    .order-info{
      width: 200px;
      margin-left: calc(100% - 200px);
      label,button{
        width: 100px;
        display: block;
      }
    }
  }
</style>
