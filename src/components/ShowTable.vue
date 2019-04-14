<template>
  <div style="height: 100%">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters">
        <el-form-item style="float: left">
          <el-button v-if="this.$store.getters.tempRoles!==''" type="primary" @click="goBack">返回</el-button>
          <el-button v-else-if="tableType==='Evaluation'" type="primary" @click="goBackTemp">返回</el-button>
        </el-form-item>
        <el-form-item v-if="tableType!=='Evaluation'">
          <el-input v-model="filters.name" :placeholder="searchText"></el-input>
        </el-form-item>
        <el-form-item v-if="tableType!=='Evaluation'">
          <el-button type="primary" @click="getData">查询</el-button>
        </el-form-item>
        <el-form-item v-if="tableType!=='Order'&&tableType!=='Evaluation'">
          <el-button type="primary" @click="handleAdd">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <!--显示数据-->
    <el-table :data="tableData"
              max-height="600"
              header-row-class-name="center"
              @selection-change="selItemsChange"
              ref="table"
              height="480">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column v-if="dataExpand.length!==0" type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item v-for="o in dataExpand" :label="o.tag" :key="o.val?o.val:o.prop">
              <template v-if="o.val">
                <label v-if="o.type==='label'" style="margin-left: 11px" >{{props.row[o.prop][o.val]}}</label>
                <img v-else-if="o.type==='img'" :src="serverAvatarUrl(props.row[o.prop][o.val])" :alt="props.row[o.prop][o.val]" style="width: 50px;height: 50px">
              </template>
              <template v-else>
                <label v-if="o.type==='label'" style="margin-left: 11px" >{{props.row[o.prop]}}</label>
                <img v-else-if="o.type==='img'" :src="serverAvatarUrl(props.row[o.prop])" :alt="props.row[o.prop]" style="width: 50px;height: 50px">
              </template>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <template v-for="o in dataSpread">
        <el-table-column v-if="!o.val&&o.prop==='evaluation'" :label="o.tag">
          <div class="evaluation" slot-scope="scope">
            <span>{{scope.row[o.prop]}}</span>
            <el-button type="primary" @click="switchTable('evaluation',scope.row['carId'])">查看评价</el-button>
          </div>
        </el-table-column>
        <el-table-column v-else-if="!o.val&&o.type==='label'" :prop="o.prop" :label="o.tag"></el-table-column>
        <el-table-column v-else-if="!o.val&&o.type==='img'" :prop="o.prop" :label="o.tag">
          <template slot-scope="scope">
            <img :src="serverAvatarUrl(scope.row[o.prop])" :alt="scope.row[o.prop]" style="width: 50px;height: 50px">
          </template>
        </el-table-column>
        <el-table-column v-else-if="o.val" :prop="o.prop" :label="o.tag">
          <template slot-scope="scope">
            {{scope.row[o.prop][o.val]}}
          </template>
        </el-table-column>
      </template>
      <el-table-column label="操作" width="220" align="center" v-if="loginUserRole!=='admin'||tableType!=='Evaluation'">
        <template slot-scope="scope">
          <el-button v-if="tableType==='Shop'" type="primary" size="mini" icon="el-icon-setting" @click="handleCheck(scope.$index, scope.row)">查看</el-button>
          <el-button v-else-if="tableType!=='Evaluation'" type="primary" size="mini" icon="el-icon-setting" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button  type="danger" size="small" icon="el-icon-delete" @click="handleDel(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-col :span="24" class="toolbar">
      <el-button type="danger" @click="batchRemove" :disabled="this.selItems.length===0">批量删除</el-button>
      <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->
    <el-dialog title="编辑" :visible.sync="editFormVisible" :close-on-click-modal="false">
      <el-form v-if="editFormVisible" :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
        <template v-for="o in dataType" v-if="o.edit">
          <el-form-item v-if="o.val" :label="o.tag" :prop="o.prop+'.'+o.val" :key="o.val">
            <el-input v-model="editForm[o.prop][o.val]"></el-input>
          </el-form-item>
          <el-form-item v-else :label="o.tag" :prop="o.prop" :key="o.prop">
            <el-input v-model="editForm[o.prop]"></el-input>
          </el-form-item>
        </template>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="editFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
      </div>
    </el-dialog>

    <!--新增界面-->
    <el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false">
      <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
        <template v-for="o in dataType" v-if="o.add">
          <el-form-item v-if="o.val" :label="o.tag" :prop="o.val" :key="o.val">
            <el-input v-model="addForm[o.val]"></el-input>
          </el-form-item>
          <el-form-item v-else :label="o.tag" :prop="o.prop" :key="o.prop">
            <el-input v-model="addForm[o.prop]"></el-input>
          </el-form-item>
        </template>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="addFormVisible = false">取消</el-button>
        <el-button type="primary" @click="addSubmit" :loading="addLoading">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import * as dataFunc from '@/api/admin'
  import * as dataAdd from '@/utils/object'
  import {uniqueTableProp} from "@/utils";
  import {baseUrl} from "../api";

  export default {
    name: "ShowTable",
    props:{
      //表格数据名称
      tableType:{
        type:String,
        required:true
      },
      //表格主键
      tableKey:{
        type:Array,
        required:true
      },
      dataSpread:{
        type:Array,
        required:true
      },
      dataExpand:{
        type:Array,
        required:false
      },
      editFormRules:{
        type:Object,
        required:false
      },
      addFormRules:{
        type:Object,
        required:false
      },
      searchText:{
        type:String,
        required:true
      }
    },
    data(){
      return{
        filters: {//过滤条件
          name: ''
        },
        tableData:[],
        dataType:[],
        getFuc:"",
        editFuc:"",
        removeFuc:"",
        addFuc:"",
        addObject:"",
        listLoading:false,//表格数据加载状态
        total: 0,//总页数
        page: 1,//当前页数
        selItems: [],//列表选中列

        editFormVisible: false,//编辑界面是否显示
        editLoading: false,
        //编辑界面数据
        editForm: Object,
        addFormVisible: false,//新增界面是否显示
        addLoading: false,
        //新增界面数据
        addForm: {}
      }
    },
    methods: {
      switchTable(tableName,carId){
        this.$emit('switchTable',{table:tableName,carId:carId})
      },
      goBackTemp(){
        this.$emit('goBackTemp')
      },
      handleCurrentChange(val) {
        this.page = val;
        this.getData();
      },
      //显示新增界面
      handleAdd() {
        this.addFormVisible = true;
        this.addForm = dataAdd[this.addObject]();
      },
      //显示编辑界面
      handleEdit(index, row) {
        this.editForm = Object.assign({}, row);
        this.editFormVisible = true;
      },
      handleCheck(index, row){
        this.$store.dispatch('SetTempRoles',row[this.tableKey])
      },
      goBack: function (index, row){
        this.$store.dispatch('ClearTempRoles')
      },
      //编辑
      editSubmit() {
        this.$refs.editForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.editLoading = true;
              //NProgress.start();
              let para = Object.assign({}, this.editForm);
              dataFunc[this.editFuc](para).then((res) => {
                if(res==='success'){
                  this.editLoading = false;
                  //NProgress.done();
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  });
                }else if(res==='fail'){
                  this.$message({
                    message: '提交失败',
                    type: 'warning'
                  });
                }
                this.$refs['editForm'].resetFields();
                this.editFormVisible = false;
                this.getData();
              });
            }).catch(err=>{
              console.log(err)
            });
          }
        });
      },
      getData(carId){
        this.listLoading = true;
        //NProgress.start();
        let para = {
          page: this.page,
          name: this.filters.name
        };
        if(this.tableType==='Car'||this.tableType==='Order'){
          if(this.$store.getters.role==='superAdmin')
            para["shopId"]=this.$store.getters.tempRoles
          else
            para["shopId"]=this.$store.getters.id
        }else if(this.tableType==='Evaluation'){
          para['carId']=carId
        }
        dataFunc[this.getFuc](para).then((data) => {
          if(this.tableType==='Brand')
            data.data.forEach(item=>{
              item['display']=item['display']+'display1.jpg'
            })
          else if(this.tableType==='Shop')
            data.data.forEach(item=>{
              item['brand']['display']=item['brand']['display']+'display1.jpg'
            })
          else if(this.tableType==='Car')
            data.data.forEach(item=>{
              item['image']=item['image']+'right.jpg'
            })
          else if(this.tableType==='User')
            data.data.forEach(item=>{
              if(item['avatar']===null)
                item['avatar']='/UserAvatar/default_avatar.png'
            })
          this.total = data.total;
          this.tableData = data.data;
          this.listLoading = false;
          //NProgress.done();
        });
      },
      //删除
      handleDel(index, row) {
        this.$confirm('确认删除该记录吗?', '提示', {
          type: 'warning'
        }).then(() => {
          this.listLoading = true;
          //NProgress.start();
          let itemPara={}
          let para = [];
          this.tableKey.forEach((key)=>{
            itemPara[key]=row[key]
          })
          para.push(itemPara)
          dataFunc[this.removeFuc](para).then((res) => {
            if(res==='success'){
              this.listLoading = false;
              this.$message({
                message: '删除成功',
                type: 'success'
              });
              this.getData();
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
      //新增
      addSubmit() {
        this.$refs.addForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.addLoading = true;
              let para = Object.assign({}, this.addForm);
              if(this.tableType==='Car'||this.tableType==='Order'){
                if(this.$store.getters.role==='superAdmin')
                  para["shopId"]=this.$store.getters.tempRoles
                else
                  para["shopId"]=this.$store.getters.id
              }
              dataFunc[this.addFuc](para).then((res) => {
                if(res==='success'){
                  this.addLoading = false;
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  });
                  this.getData();
                }
                else if(res==='fail'){
                  this.addLoading = false;
                  this.$message({
                    message: '提交失败',
                    type: 'warning'
                  });
                }
                this.$refs['addForm'].resetFields();
                this.addFormVisible = false;
              });
            }).catch(err=>{
              console.log(err)
            });
          }
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
            this.tableKey.forEach((key)=>{
              itemPara[key]=item[key]
            })
            para.push(itemPara)
          })
          this.listLoading = true;
          dataFunc[this.removeFuc](para).then((res) => {
            if(res==='success'){
              this.listLoading = false;
              this.$message({
                message: '删除成功',
                type: 'success'
              });
              this.getData();
            }
            else if(res==='fail'){
              this.listLoading = false;
              this.$message({
                message: '删除失败',
                type: 'warning'
              });
            }
            this.$refs.table.clearSelection();
          });
        }).catch(() => {

        });
      },
      selItemsChange(sels) {
        this.selItems = sels;
      },
      serverAvatarUrl(url){
        return baseUrl+url
      }
    },
    mounted() {
      //加载数据
      this.getFuc="get"+this.tableType+"ListPage"
      this.editFuc="edit"+this.tableType
      this.removeFuc="remove"+this.tableType
      this.addFuc=this.addObject="add"+this.tableType
      this.dataType=this.dataType.concat(this.dataSpread).concat(this.dataExpand)
      this.dataType.sort(function (a, b) {
        let order=["img","label","input"]
        return order.indexOf(a.type) - order.indexOf(b.type);
      })
      this.dataType=uniqueTableProp(this.dataType)
      /*if(this.getFuc!=="")
        this.getData()*/
    },
    computed:{
      loginUserRole(){
        return this.$store.getters.role
      }
    }
  }
</script>

<style lang="less" scoped>
  .evaluation{
    span{
      display: block;
    }
    button{
      display: none;
    }
    &:hover{
      span{
        display: none;
      }
      button{
        display: block;
      }
    }
  }
  .toolbar{
    position: relative;
    &>button{
      position: absolute;
      left: 0px;
    }
  }
  .demo-table-expand {
    font-size: 0;
    .el-form-item {
      margin-right: 0;
      margin-bottom: 0;
      width: 100%;
      &+label{
        width: 100px!important;
        color: #99a9bf;
      }
    }
  }

</style>
