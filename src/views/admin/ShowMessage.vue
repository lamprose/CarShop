<template>
    <div>
      <el-row type="flex"justify="end" style="width: 93%" align="center">
        <el-col :span="6"><div class="grid-content">
          <el-button type="primary" round size="mini" @click="dialogCreateVisible = true">添加留言</el-button>
        </div></el-col>
      </el-row>
      <el-table :data="Message" style="width: 70%;margin:auto; margin-top: 10px" max-height="250" header-row-class-name="center" border>
        <el-table-column type="index" label="No." width="50px" ></el-table-column>
        <el-table-column prop="userid" label="留言账号" ></el-table-column>
        <el-table-column prop="content" label="留言内容"></el-table-column>
        <el-table-column prop="date" label="留言时间" ></el-table-column>
        <el-table-column label="操作" width="220" align="center">
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
</template>

<script>
    export default {
      name: "ShowMessage",
      inject:['reloadAdmin'],
      methods:{
        //删除用户
        removed(currentMessage) {
          this.deleteone.userid = currentMessage.userid;
          this.deleteone.content = currentMessage.content;
          this.deleteone.date = currentMessage.date;
          let data = this.delete;
          this.$confirm(
            "此操作将永久删除该留言 "  + ", 是否继续?",
            "提示",
            {
              type: "warning"
            }
          ).then(() => {
            this.$message.info("操作开始!");
            console.log("确认删除留言");
            // 向请求服务端删除;
            console.log(data);
            console.log(deleteone + "sly");
            this.$ajax.post('http://localhost:8080/message/deleteOne/', JSON.stringify(this.deleteone),
              {
                headers: {'Content-Type': 'application/json;charset=UTF-8'}
              }
            ).then(response => {
              console.log(response);
              if (response.data == "success") {
                this.reloadAdmin(3);
              }
              else{
                this.$message.info("删除失败!");
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
          url: 'http://localhost:8080/message/findAll',
        }).then(response=>{
          console.log(response.data);
          for(let i= 0; i<response.data.length;i++) {
            this.Message.push(response.data[i])
          }
        });
      },
      data(){
        return{
          Message: [],
          dialogCreateVisible: false,
          deleteone:{
            userid:"",
            content:"",
            date:"",
          }
        }
      }
    }
</script>

<style scoped>

</style>
