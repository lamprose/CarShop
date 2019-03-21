<template>
  <div class="all-container" style="margin-top: 40px">
    <div>
      <el-tabs>
        <el-tab-pane label="基本信息" name="first">
          <el-form :model="userInfo">
            <el-form-item label="头像" prop="avatar_url" :label-width="formLabelWidth">
              <el-upload action="">
                <img v-if="imageUrl" :src="imageUrl" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
            <el-form-item label="用户名" prop="username" :label-width="formLabelWidth">
              <el-col :span="8"><el-input v-model="userInfo.name" disabled ></el-input></el-col>
            </el-form-item>
            <el-form-item label="电话" prop="phone" :label-width="formLabelWidth">
              <el-col :span="8"><el-input v-model="userInfo.phone" placeholder="请输入电话"></el-input></el-col>
            </el-form-item>
            <el-form-item label="邮箱" prop="email" :label-width="formLabelWidth">
              <el-col :span="8">   <el-input v-model="userInfo.email" placeholder="请输入邮箱"></el-input></el-col>
            </el-form-item>
            <el-form-item label="用户角色" prop="full_name" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="userInfo.role" disabled ></el-input>
              </el-col>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <div class="grid-content bg-purple">
          <el-button type="primary"  @click="EditorUserClick('userlist')" >保存</el-button>
        </div>
        <el-tab-pane label="修改密码" name="second">
          <el-form :model="changePassword">
            <el-form-item label="原密码" prop="pass" :label-width="formLabelWidth">
              <el-col :span="8"><el-input v-model="changePassword.old" placeholder="请输入原密码" type="password"></el-input></el-col>
            </el-form-item>
            <el-form-item label="新密码" prop="newpass" :label-width="formLabelWidth">
              <el-col :span="8"><el-input v-model="changePassword.new" placeholder="请输入新密码" id="newkey" type="password"></el-input></el-col>
            </el-form-item>
            <el-form-item label="重复新密码" prop="checknewpass" :label-width="formLabelWidth">
              <el-col :span="8"><el-input v-model="changePassword.checkNew" placeholder="请再次输入新密码" id='newkey1' type="password"></el-input></el-col>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>
<script>
  export default {
    data(){
      return{
        userInfo:{
          avatar_url:'',
          name:'123',
          phone:'1231314',
          email:'131231',
          role:'1'
        },
        changePassword:{
          old:'',
          new:'',
          checkNew:''
        },
        formLabelWidth:150,
      }
    }
  }
</script>
