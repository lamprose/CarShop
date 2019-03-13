<template>
    <div>
      <!--书籍信息-->
      <el-row type="flex"justify="end" style="width: 93%" align="center">
        <el-col :span="6"><div class="grid-content">
          <el-button type="primary" round size="mini" @click="dialogCreateVisible = true">添加书籍</el-button>
        </div></el-col>
      </el-row>
      <div>
        <el-table :data="Book" style="width: 70%;margin:auto; margin-top: 10px" max-height="600" header-row-class-name="center" border>
          <el-table-column type="index" label="No." width="50px" ></el-table-column>
          <el-table-column prop="title" label="书名" ></el-table-column>
          <el-table-column prop="author" label="作者" ></el-table-column>
          <el-table-column prop="img" label="图片地址"></el-table-column>
          <el-table-column prop="isbn" label="ISBN号" ></el-table-column>
          <el-table-column prop="type" label="类型" ></el-table-column>
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

      <!--添加书籍-->
      <div>
        <el-dialog title="添加书籍" :visible.sync="dialogCreateVisible">
          <el-form id="#create" :model="create" ref="create" label-width="100px">
            <el-form-item label="书名" prop="title">
              <el-input v-model="create.title" ></el-input>
            </el-form-item>
            <el-form-item label="作者" prop="author">
              <el-input v-model="create.author"></el-input>
            </el-form-item>
            <el-form-item label="图片地址" prop="img">
              <el-input v-model="create.img"></el-input>
            </el-form-item>
            <el-form-item label="ISBN号" prop="isbn">
              <el-input v-model="create.isbn" ></el-input>
            </el-form-item>
            <el-form-item label="类型" prop="type">
              <el-input v-model="create.type" ></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogCreateVisible = false">取 消</el-button>
            <el-button type="primary" @click="createBook">确 定</el-button>
          </div>
        </el-dialog>
      </div>

      <!--编辑书籍信息-->
      <div>
          <el-dialog
            title="修改订单信息"
            :visible.sync="dialogUpdateVisible"
            :close-on-click-modal="false"
            :close-on-press-escape="false"
          >
            <el-form id="#update" :model="update" ref="update" label-width="100px">
              <el-form-item label="书名" prop="title">
                <el-input disabled="true" v-model="update.title" ></el-input>
              </el-form-item>
              <el-form-item label="作者" prop="author">
                <el-input v-model="update.author"></el-input>
              </el-form-item>
              <el-form-item label="图片地址" prop="img">
                <el-input v-model="update.img"></el-input>
              </el-form-item>
              <el-form-item label="ISBN号" prop="isbn">
                <el-input v-model="update.isbn" ></el-input>
              </el-form-item>
              <el-form-item label="类型" prop="type">
                <el-input v-model="update.type" ></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogUpdateVisible = false">取 消</el-button>
              <el-button type="primary" @click="updateBook">确 定</el-button>
            </div>
          </el-dialog>
      </div>
    </div>
</template>

<script>
    export default {
      name: "ShowBook",
      inject:['reloadAdmin'],
      methods:{

        //增加书籍
        createBook(){
          let data = this.create;
          console.log(data);
          this.$ajax.post('http://localhost:8080/book/saveOne/', JSON.stringify(data),
            {
              headers: { 'Content-Type': 'application/json;charset=UTF-8'}
            }
          ).then(response=> {
            console.log(response);
            this.reloadAdmin(1);
            this.dialogCreateVisible=false;
          }).catch(function (error){
            console.log("save failed！")
          });
        },

        //编辑书籍信息
        setCurrent(currentBook) {
          console.log(currentBook);
          this.update.title = currentBook.title;
          this.update.author = currentBook.author;
          this.update.img = currentBook.img;
          this.update.isbn = currentBook.isbn;
          this.update.type = currentBook.type;
          this.dialogUpdateVisible = true;
          console.log(this.dialogUpdateVisible);
        },
        updateBook() {
          let data = this.update;
          console.log(data);
          this.$ajax.post('http://localhost:8080/book/updateOne/', JSON.stringify(data),
            {
              headers: { 'Content-Type': 'application/json;charset=UTF-8'}
            }
          ).then(response=> {
            console.log(response);
            this.dialogUpdateVisible=false;
            this.reloadAdmin(1);
          }).catch(function (error){
            console.log("update failed！")
          });
        },

        //删除用户
        removed(currentBook) {
          console.log("删除订单");
          this.$confirm(
            "此操作将永久删除该书籍 " + currentBook.title + ", 是否继续?",
            "提示",
            {
              type: "warning"
            }
          ).then(() => {
            console.log("确认删除书籍");
            // 向请求服务端删除
            let title = currentBook.title;
            console.log(title);
            this.$ajax.get('http://localhost:8080/book/deleteOne/' + title,).then(response => {
              console.log(response);
              if (response.data == "success") {
                this.reloadAdmin(1);
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
          url: 'http://localhost:8080/book/findAll',
        }).then(response=>{
          console.log(response.data);
          for(let i= 0; i<response.data.length;i++) {
            this.Book.push(response.data[i])
          }
        });
      },
      data(){
        return{
          Book: [],
          dialogCreateVisible:false,
          dialogUpdateVisible:false,
          create:{
            author:"",
            img:"",
            isbn:"",
            title:"",
            type:"",
          },
          update:{
            author:"",
            img:"",
            isbn:"",
            title:"",
            type:"",
          }
        }
      }
    }
</script>

<style scoped>

</style>
