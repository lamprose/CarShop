<template>
  <div>
    <transition name="el-zoom-in-center">
      <show-normal v-show="active==='-1'"></show-normal>
    </transition>
    <transition name="el-zoom-in-center" v-for="(o,index) in data" :key="index+1">
      <show-table ref="adminTable" :searchText="o.searchText" :addFormRules="o.addRules" :editFormRules="o.editRules"  :tableType="o.type" :tableKey="o.key" :dataSpread="o.spread" :dataExpand="o.expand" @switchTable="switchTable" @goBackTemp="goBackTemp" class="table" v-if="loginRole==='superAdmin'?true:o.showAdmin"  v-show="tempActive==='0'?active===index.toString():tempActive===index.toString()"></show-table>
    </transition>
  </div>
</template>

<script>
  import rule from "@/utils/rule";
  export default {
    name: "AdminMain",
    props:{
      active:{
        required:true,
        default:0
      },
    },
    components: {
      ShowNormal: resolve => require(["./adminHome"],resolve),
      ShowTable: resolve => require(["@/components/ShowTable"],resolve)
    },
    data(){
      return{
        tempActive:'0',
        /*数据
        * type:表数据类型
        * key:主键
        * spread:表格数据内容
        * expand:表格折叠内容
        * prop:数据项属性名称或外键对象名称
        * val:外键对象属性名称
        * tag:表格头文字说明
        * type:显示类型
        * add:是否显示在添加数据表单内
        * edit:是否显示在编辑数据表单内
        * addRules:添加数据表单验证规则
        * editRules:编辑数据表单规则*/
        data:[
          {
            type:"User",
            key:["id"],
            showAdmin:false,
            spread:[
              {prop:"avatar",tag:"头像",type:"img"},
              {prop:"id",tag:"账号",type:"label",add:true},
              {prop:"name",tag:"姓名",type:"label",add:true,edit:true},
              {prop:"sex",tag:"性别",type:"label",add:true,edit:true},
            ],
            expand:[
              {prop:"phone",tag:"联系电话",type:"label",add:true,edit:true},
              {prop:"loc",tag:"地址",type:"label",add:true,edit:true},
              {prop:"token",tag:"token",type:"label"},
              {prop:"status",tag:"登陆状态",type:"label"}
            ],
            searchText:"姓名",
            addRules:{
              id:rule.ID
            }
          },
          {
            type:"Brand",
            key:["brandId"],
            showAdmin:false,
            spread:[
              {prop:"brandId",tag:"品牌ID",type:"label",add:true},
              {prop:"brandName",tag:"品牌名称",type:"label",add:true,edit:true},
              {prop:"display",tag:"展示图",type:"img"},
              {prop:"logo",tag:"logo",type:"img"},
            ],
            expand:[],
            searchText:"品牌名称",
            addRules:{
              brandId:rule.BrandID
            }
          },
          {
            type:"Shop",
            key:["shopId"],
            showAdmin:false,
            spread:[
              {prop:"shopId",tag:"商户ID",type:"label",add:true},
              {prop:"shopName",tag:"商户名称",type:"label",add:true},
              {prop:"brand",val:'brandId',tag:"品牌ID",type:"label",add:true},
              {prop:"phone",tag:"联系电话",type:"label",add:true},
            ],
            expand:[
              {prop:"brand",val:"display",tag:"品牌展图",type:"img"},
              {prop:"brand",val:"logo",tag:"品牌logo",type:"img"},
              {prop:"brand",val:"brandId",tag:"品牌ID",type:"label",add:true},
              {prop:"brand",val:"brandName",tag:"品牌名称",type:"label"},
            ],
            addRules:{
              shopId:rule.ID
            },
            searchText:"商户名称",
          },
          {
            type:"Car",
            key:["carId"],
            showAdmin:true,
            spread:[
              {prop:"carId",tag:"汽车ID",type:"label",add:true},
              {prop:"carName",tag:"车名",type:"label",add:true,edit:true},
              {prop:"price",tag:"价格",type:"label",add:true,edit:true},
              {prop:"image",tag:"预览图",type:"img"},
              {prop:"evaluation",tag:"评价",type:"label"},
              {prop:"brand",val:"brandName",tag:"品牌",type:"label"},
            ],
            expand:[
              {prop:"param",val:'carSize',tag:"车身尺寸",type:"label",add:true,edit:true},
              {prop:"param",val:'engine',tag:"发动机",type:"label",add:true,edit:true},
              {prop:"param",val:'level',tag:"级别",type:"label",add:true,edit:true},
              {prop:"param",val:'structure',tag:"车身结构",type:"label",add:true,edit:true},
              {prop:"param",val:'assurance',tag:"整车质保",type:"label",add:true,edit:true},
              {prop:"param",val:'oilWear',tag:"油耗",type:"label",add:true,edit:true},
              {prop:"param",val:'manufacturer',tag:"厂商",type:"label",add:true,edit:true},
              {prop:"param",val:'gearbox',tag:"变速箱",type:"label",add:true,edit:true},
              {prop:"param",val:'maxSpeed',tag:"最高车速",type:"label",add:true,edit:true},
            ],
            searchText:'车名',
            addRules:{
              carId:rule.CarID,
              oilWear:rule.OilWear,
              maxSpeed:rule.MaxSpeed,
              price:rule.Price
            },
            editRules:{
              'param.oilWear':rule.OilWear,
              'param.maxSpeed':rule.MaxSpeed,
              price:rule.Price
            }
          },
          {
            type:"Order",
            key:["orderId"],
            showAdmin:true,
            spread:[
              {prop:"orderId",tag:"订单ID",type:"label"},
              {prop:"user",val:'id',tag:"用户ID",type:"label"},
              {prop:"car",val:'carName',tag:"车名",type:"label"},
              {prop:"amount",tag:"数量",type:"label"},
              {prop:"car",val:'price',tag:"单价",type:"label"},
              {prop:"orderTime",tag:"下单时间",type:"label"},
              {prop:"orderStatus",tag:"订单状态",type:"label",edit:true},
            ],
            expand:[],
            searchText:'订单号'
          },
          {
            type:"Evaluation",
            key:["evaId"],
            showAdmin:true,
            spread:[
              {prop:"evaId",tag:"订单ID",type:"label"},
              {prop:"grade",tag:"评分",type:"label"},
              {prop:"evaluate",tag:"评价内容",type:"label"},
              {prop:"evaTime",tag:"评论时间",type:"label"},
            ],
            expand:[],
            searchText:'订单号'
          },
        ],
      }
    },
    methods:{
      switchTable(data){
        if(data.table==='evaluation'){
          this.tempActive='5'
          if(this.loginRole==='superAdmin')
            this.$refs.adminTable['5'].getData(data.carId)
          else if(this.loginRole==='admin')
            this.$refs.adminTable['2'].getData(data.carId)
        }
      },
      goBackTemp(){
        this.tempActive='0'
      }
    },
    computed:{
      loginRole(){
        return this.$store.getters.role
      }
    },
    watch:{
      active(val){
        this.tempActive='0'
        if(val!=='-1'){
          if(this.loginRole==='superAdmin')
            this.$refs.adminTable[val].getData()
          else if(this.loginRole==='admin')
            this.$refs.adminTable[val-3].getData()
        }
      }
    }
  }
</script>

<style scoped>
  .table{
    position: absolute;
    top:10px;
    left: 0;
    width: 100%;
    transition-duration: 0.3s;
    transition-property: all
  }
</style>
