<template>
  <div>
    <!--用户信息汇总-->
    <el-row type="flex"justify="end" style="width: 93%" align="center">
      <el-col :span="6"><div class="grid-content">
        <el-button type="primary" round size="mini" @click="dialogCreateVisible = true">添加用户</el-button>
      </div></el-col>
    </el-row>
    <div>
      <el-table :data="UserInfo" style="width: 70%;margin:auto;margin-top: 10px" max-height="600" header-row-class-name="center" border>
        <el-table-column type="index" label="No." width="50" ></el-table-column>
        <el-table-column prop="id" label="账号" ></el-table-column>
        <el-table-column prop="password" label="密码"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="sex" label="性别" width="50"></el-table-column>
        <el-table-column prop="phone" label="手机号"></el-table-column>
        <el-table-column prop="address" label="地址"></el-table-column>
        <el-table-column prop="flag" label="身份" width="50"></el-table-column>
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
    </div>

    <!--添加用户-->
    <div>
      <el-dialog title="添加用户" :visible.sync="dialogCreateVisible">
        <el-form id="#create" :model="create" ref="create" label-width="100px">
          <el-form-item label="账号" prop="id">
            <el-input v-model="create.id"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="create.password"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input v-model="create.name" ></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-input v-model="create.sex" ></el-input>
          </el-form-item>
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="create.phone" ></el-input>
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input v-model="create.address" ></el-input>
          </el-form-item>
          <el-form-item label="身份" prop="flag">
            <el-input v-model="create.flag" ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogCreateVisible = false">取 消</el-button>
          <el-button type="primary" @click="createUser">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <!--编辑用户信息-->
    <div>
      <el-dialog
        title="修改订单信息"
        :visible.sync="dialogUpdateVisible"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
      >
        <el-form id="#update" :model="update" ref="update" label-width="100px">
          <el-form-item label="账号" prop="id">
            <el-input disabled="true" v-model="update.id"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="update.password"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input v-model="update.name" ></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-input v-model="update.sex" ></el-input>
          </el-form-item>
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="update.phone" ></el-input>
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input v-model="update.address" ></el-input>
          </el-form-item>
          <el-form-item label="身份" prop="flag">
            <el-input v-model="update.flag" ></el-input>
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
      name: "ShowUser",
      inject:['reloadAdmin'],
      methods:{

        //增加用户
        createUser(){
          let data = this.create;
          console.log(data);
          this.$ajax.post('http://localhost:8080/user/saveOne/', JSON.stringify(data),
            {
              headers: { 'Content-Type': 'application/json;charset=UTF-8'}
            }
          ).then(response=> {
            console.log(response);
            this.reloadAdmin(0);
            this.dialogCreateVisible=false;
          }).catch(function (error){
            console.log("save failed！")
          });
        },

        //编辑用户信息
        setCurrent(currentUser) {
          console.log(currentUser);
          this.update.id= currentUser.id;
          this.update.password = currentUser.password;
          this.update.name = currentUser.name;
          this.update.sex = currentUser.sex;
          this.update.phone = currentUser.phone;
          this.update.address = currentUser.address;
          this.update.flag = currentUser.flag;
          this.dialogUpdateVisible = true;
          console.log(this.dialogUpdateVisible);
        },
        updateUser() {
          let data = this.update;
          console.log(data);
          this.$ajax.post('http://localhost:8080/user/updateOne/', JSON.stringify(data),
            {
              headers: { 'Content-Type': 'application/json;charset=UTF-8'}
            }
          ).then(response=> {
            console.log(response);
            this.dialogUpdateVisible=false;
            this.reloadAdmin(0);
          }).catch(function (error){
            console.log("update failed！")
          });
        },

        //删除用户
        removed(currentUser) {
          console.log("删除订单");
          this.$confirm(
            "此操作将永久删除该用户 " + currentUser.id + ", 是否继续?",
            "提示",
            {
              type: "warning"
            }
          ).then(() => {
            console.log("确认删除用户");
            // 向请求服务端删除
            let id = currentUser.id;
            console.log(id);
            this.$ajax.get('http://localhost:8080/user/deleteOne/' + id,).then(response => {
              console.log(response);
              if (response.data == "success") {
                this.reloadAdmin(0);
              }
            }).catch(function (error) {
              console.log("delete failed！")
            });
          })
            .catch(() => {
              this.$message.info("已取消操作!");
            });
        }
      },

      mounted() {
        // 加载数据
        console.log("loading data.")
        this.$ajax({
          method: 'get',
          url: 'http://localhost:8080/user/findAll',
        }).then(response => {
          console.log(response.data);
          for (let i = 0; i < response.data.length; i++) {
            this.UserInfo.push(response.data[i])
          }
        });
      },
      data(){
        return{
          dialogCreateVisible: false,
          dialogUpdateVisible: false,
          UserInfo: [],

          create:{
            id:"",
            password:"",
            name:"",
            sex:"",
            phone:"",
            address:"",
            flag:"",
          },

          update:{
            id:"",
            password:"",
            name:"",
            sex:"",
            phone:"",
            address:"",
            flag:"",
          }
        }
      }
    }
</script>

<style scoped>

</style>
