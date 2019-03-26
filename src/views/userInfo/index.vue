<template>
  <div class="all-container" style="margin-top: 40px">
    <div style="width: 70%;margin-left: 15%;height: 100%">
      <el-tabs type="border-card" v-model="tabNow">
        <el-tab-pane label="基本信息" name="user">
          <el-form id="user-form" :model="changeUser" :rules="changeUserRule" ref="changeUser" :label-width="formLabelWidth" status-icon label-position="left">
            <el-form-item label="头像" prop="avatar_url">
              <el-upload :action="avatarUploadUrl"
                         class="avatar-upload"
                         :http-request="avatarUpload"
                         :before-upload="beforeUpload"
                         :on-change="addFile"
                         list-type="picture"
                         :show-file-list="false">
                <img width="100px" v-show="!avatarFlag&&changeUser.avatar" :src="changeUser.avatar" class="avatar">
                <i v-show="!avatarFlag&&!changeUser.avatar" class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
            <el-form-item label="用户名">
              <el-col :span="8"><el-input v-model="changeUser.id" disabled class="user-input"></el-input></el-col>
            </el-form-item>
            <el-form-item label="用户角色">
              <el-col :span="8"><el-input v-model="changeUser.role" disabled class="user-input"></el-input></el-col>
            </el-form-item>
            <el-form-item label="昵称" prop="username">
              <el-col :span="8"><el-input v-model="changeUser.name" class="user-input"></el-input></el-col>
            </el-form-item>
            <el-form-item label="性别">
              <el-select v-model="changeUser.sex" id="user-sex" ref="sex">
                <el-option label="未知" value="-1" disabled></el-option>
                <el-option label="男" value="1"></el-option>
                <el-option label="女" value="0"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="电话" prop="phone">
              <el-col :span="8"><el-input v-model="changeUser.phone" placeholder="请输入电话" class="user-input"></el-input></el-col>
            </el-form-item>
            <el-form-item label="收货地址">
              <area-select id="area-select" v-model="area" type="text" :placeholders='placeholders' :level="2" :data="pcaa"></area-select>
              <el-col :span="8"><el-input v-model="areaLoc" class="user-input" style="margin-top: 20px"></el-input></el-col>
            </el-form-item>
          </el-form>
          <div class="grid-content bg-purple">
            <el-button type="primary"  @click="submitUserForm" >保存</el-button>
            <el-button type="primary"  @click="resetForm('changeUser')" >重置</el-button>
          </div>
        </el-tab-pane>
        <el-tab-pane label="修改密码" name="password">
          <el-form :model="changePwd" :rules="changePwdRule" ref="password" :label-width="formLabelWidth" status-icon label-position="left">
            <el-form-item label="原密码" prop="old">
              <el-col :span="8"><el-input class="user-input" v-model="changePwd.old" placeholder="请输入原密码" type="password" :show-password="true"></el-input></el-col>
            </el-form-item>
            <el-form-item label="新密码" prop="new">
              <el-col :span="8"><el-input class="user-input" v-model="changePwd.new" placeholder="请输入新密码" id="password" type="password" :show-password="true"></el-input></el-col>
            </el-form-item>
            <el-form-item label="重复新密码" prop="checkNew">
              <el-col :span="8"><el-input class="user-input" v-model="changePwd.checkNew" placeholder="请再次输入新密码" type="password" :show-password="true"></el-input></el-col>
            </el-form-item>
          </el-form>
          <div class="grid-content bg-purple">
            <el-button type="primary"  @click="submitPassForm" >保存</el-button>
            <el-button type="primary"  @click="resetForm('password')" >重置</el-button>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>
<script>
  import { pca, pcaa } from "area-data";
  import {baseUrl} from "../../api";
  import {avatarUpload} from "@/api/user";
  import rule from "@/utils/rule";

  export default {
    data(){
      return{
        tabNow:'user',
        changeUser:{
          avatar:'',
          id:'',
          role:'',
          name:'',
          phone:'',
          loc:''
        },
        changePwd:{
          old:'',
          new:'',
          checkNew:''
        },
        formLabelWidth:"150px",
        area:[],
        areaLoc:'',
        placeholders: ['选择省','选择市','选择区'],
        pcaa:pcaa,
        changeUserRule:{
          phone:rule.Phone
        },
        changePwdRule:{
          old:rule.Password,
          new:rule.Password,
          checkNew:rule.RePassword
        },
        avatarUploadUrl:baseUrl+'/user/avatar',
        avatarFlag:false,
        avatarPercentage:0
      }
    },
    methods:{
      avatarUpload(param){
        avatarUpload(param,this.$store.getters.id).then(data=>{
          this.$store.dispatch('updateUserAvatar',data)
          this.changeUser.avatar=this.$store.getters.avatar
        })
      },
      addFile(file){
        if(file.size>3 * 1024 * 1024){
          this.$message.error({
            message:'头像大小不能超过3M',
            showClose:false
          })
        }
      },
      beforeUpload(file){
        /*//创建临时的路径来展示图片
        var windowURL = window.URL || window.webkitURL;

        this.src=windowURL.createObjectURL(file);
        //重新写一个表单上传的方法
        this.param = new FormData();
        this.param.append('picture', file, file.name);*/
        return true;
      },
      submitUserForm(){
        this.$refs['changeUser'].validate((valid) => {
          if (valid) {
            this.changeUser.loc=this.area.join("|")+'|'+this.areaLoc
            console.log(this.changeUser)
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      submitPassForm(){
        this.$refs['password'].validate((valid) => {
          if (valid) {
            console.log(this.changePwd)
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.changeUser=JSON.parse(JSON.stringify(this.userInfo))
        if(this.changeUser.loc!==null){
          let tempLoc=this.changeUser.loc.split('|')
          this.area=tempLoc.slice(0,3)
          this.areaLoc=tempLoc[3]
        }
        this.$refs[formName].resetFields();
      }
    },
    computed:{
      userInfo(){
        return this.$store.getters.user
      }
    },
    mounted() {
      this.changeUser=JSON.parse(JSON.stringify(this.userInfo))
      if(this.changeUser.loc!==null){
        let tempLoc=this.changeUser.loc.split('|')
        this.area=tempLoc.slice(0,3)
        this.areaLoc=tempLoc[3]
      }
    }
  }
</script>
<style lang="less" scoped>
  .avatar-upload{
    display: flex;
    margin-left: 160px;
  }
  .el-select{
    display: flex;
    width: 475px;
  }
  .user-input{
    width: 475px;
  }
  #user-info{
    margin: auto;
  }
</style>
