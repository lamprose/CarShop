<template>
    <div>
      <!--书籍信息-->
      <el-row type="flex"justify="end" style="width: 93%" align="center">
        <el-col :span="6"><div class="grid-content">
          <el-button type="primary" round size="mini" @click="dialogCreateVisible = true">添加书籍</el-button>
        </div></el-col>
      </el-row>
      <div>
        <el-table :data="Book" header-row-class-name="center" border>
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
              >编辑</el-button>
              <el-button type="danger" size="small" icon="el-icon-delete">删除</el-button>
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
            <el-button type="primary">确 定</el-button>
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
              <el-button type="primary">确 定</el-button>
            </div>
          </el-dialog>
      </div>
    </div>
</template>

<script>
    export default {
      name: "ShowBook",
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
