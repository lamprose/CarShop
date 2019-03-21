<template>
  <div>
    <el-row type="flex"justify="end" style="width: 93%" align="center">
      <el-col :span="6"><div class="grid-content">
        <el-button type="primary" round size="mini" @click="dialogCreateVisible = true">添加订单</el-button>
      </div></el-col>
    </el-row>
    <el-table :data="Shop" max-height="600" header-row-class-name="center" border>
      <el-table-column type="index" label="No." width="50" ></el-table-column>
      <el-table-column prop="ordernum" label="订单编号" ></el-table-column>
      <el-table-column prop="id" label="用户账号" ></el-table-column>
      <el-table-column prop="title" label="书籍名称"></el-table-column>
      <el-table-column prop="sum" label="订购数量" ></el-table-column>
      <el-table-column label="操作" width="220" align="center" >
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-setting"
            @click="setCurrent(scope.row)"
          >编辑</el-button>
          <el-button type="danger" size="small" icon="el-icon-delete" @click="removed(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--添加订单-->
    <div>
      <el-dialog title="添加订单" :visible.sync="dialogCreateVisible">
        <el-form id="#create" :model="create" ref="create" label-width="100px">
          <el-form-item label="订单编号" prop="ordernum">
            <el-input v-model="create.ordernum"></el-input>
          </el-form-item>
          <el-form-item label="用户账号" prop="id">
            <el-input v-model="create.id"></el-input>
          </el-form-item>
          <el-form-item label="书籍名称" prop="title">
            <el-input v-model="create.title" ></el-input>
          </el-form-item>
          <el-form-item label="订购数量" prop="sum">
            <el-input v-model="create.sum" ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogCreateVisible = false">取 消</el-button>
          <el-button type="primary" @click="createUser">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <!--编辑订单信息-->
    <div>
      <el-dialog
        title="修改订单信息"
        :visible.sync="dialogUpdateVisible"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
      >
        <el-form id="#update" :model="update" ref="update" label-width="100px">
          <el-form-item label="订单编号" prop="ordernum">
            <el-input disabled v-model="update.ordernum"></el-input>
          </el-form-item>
          <el-form-item label="用户账号" prop="id">
            <el-input v-model="update.id"></el-input>
          </el-form-item>
          <el-form-item label="书籍名称" prop="title">
            <el-input v-model="update.title" ></el-input>
          </el-form-item>
          <el-form-item label="订购数量" prop="sum">
            <el-input v-model="update.sum" ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogUpdateVisible = false">取 消</el-button>
          <el-button type="primary" @click="updateUser">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>

</template>

<script>
    export default {
      name: "ShowShop",
      methods: {

        //增加用户
        createUser() {
          let data = this.create;
          console.log(data);

        },

        //编辑用户信息
        setCurrent(currentShop) {
          console.log(currentShop);
          this.update.ordernum = currentShop.ordernum;
          this.update.id = currentShop.id;
          this.update.title = currentShop.title;
          this.update.sum = currentShop.sum;
          this.dialogUpdateVisible = true;
          console.log(this.dialogUpdateVisible);
        },
        updateUser() {

        },

        //删除订单
        removed(currentShop) {
          console.log("删除订单");
          this.$confirm(
            "此操作将永久删除该订单 " + currentShop.ordernum + ", 是否继续?",
            "提示",
            {
              type: "warning"
            }
          ).then(() => {
            console.log("确认删除用户");
            // 向请求服务端删除
            let ordernum = currentShop.ordernum;
            console.log(ordernum);
            //TODO:后端删除订单
          })
            .catch(() => {
              this.$message.info("已取消操作!");
            });
        }
      },
      mounted() {
        //TODO: 加载数据
      },
      data(){
        return{
          Shop: [],
          dialogCreateVisible: false,
          dialogUpdateVisible: false,

          create:{
            ordernum:"",
            id:"",
            title:"",
            sum:"",
          },

          update:{
            ordernum:"",
            id:"",
            title:"",
            sum:"",
          }
        }
      }
    }
</script>

<style scoped>

</style>
