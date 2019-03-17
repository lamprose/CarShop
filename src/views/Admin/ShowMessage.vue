<template>
    <div>
      <el-row type="flex"justify="end" style="width: 93%" align="center">
        <el-col :span="6"><div class="grid-content">
          <el-button type="primary" round size="mini" @click="dialogCreateVisible = true">添加留言</el-button>
        </div></el-col>
      </el-row>
      <el-table :data="Message"  header-row-class-name="center" border>
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
