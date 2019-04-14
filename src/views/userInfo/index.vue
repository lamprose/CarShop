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
                <img width="100px" v-show="!avatarFlag&&userInfo.avatar" :src="userInfo.avatar" class="avatar">
                <i v-show="!avatarFlag&&!userInfo.avatar" class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
            <el-form-item label="用户名">
              <el-col :span="8"><el-input v-model="userInfo.id" disabled class="user-input"></el-input></el-col>
            </el-form-item>
            <el-form-item label="用户角色">
              <el-col :span="8"><el-input v-model="userInfo.role" disabled class="user-input"></el-input></el-col>
            </el-form-item>
            <el-form-item label="昵称" prop="username">
              <el-col :span="8"><el-input v-model="changeUser.name" :placeholder="userInfo.name" class="user-input"></el-input></el-col>
            </el-form-item>
            <el-form-item label="性别" v-if="userInfo.role==='normal'">
              <el-select v-model="changeUser.sex" id="user-sex" ref="sex" :placeholder="userInfo.sex">
                <el-option label="未知" value="null" disabled></el-option>
                <el-option label="男" value="男"></el-option>
                <el-option label="女" value="女"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="电话" prop="phone">
              <el-col :span="8"><el-input v-model="changeUser.phone" :placeholder="userInfo.phone" class="user-input"></el-input></el-col>
            </el-form-item>
            <el-form-item label="收货地址" v-if="userInfo.role==='normal'">
              <area-select id="area-select" v-model="changeUser.area" type="text" :placeholders='placeholders' :level="2" :data="pcaa"></area-select>
              <el-col :span="8"><el-input v-model="changeUser.areaLoc" :placeholder="areaLoc" class="user-input" style="margin-top: 20px"></el-input></el-col>
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
        <el-tab-pane label="设置密保" name="secret" v-if="!userInfo.secretStatus">
          <el-form :model="secretData" :rules="secretRule" ref="secret" :label-width="formLabelWidth" status-icon label-position="left">
            <template v-for="(o,index) in secretData.secret">
              <el-form-item label="密保问题:" :prop="'secret['+index+'].question'">
                <el-select v-model="o.question" placeholder="请选择" style="width: 475px;" @change="optionChange">
                  <el-option v-for="item in nowOptions" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="密保答案:" :prop="'secret['+index+'].answer'">
                <el-input placeholder="请输入密保答案" v-model="o.answer" style="width: 475px;float: left"></el-input>
              </el-form-item>
            </template>
          </el-form>
          <el-button round type="primary" @click="submitAddSecret">提交</el-button>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>
<script>
  import { pca, pcaa } from "area-data";
  import {baseUrl} from "../../api";
  import {avatarUpload,changePassword} from "@/api/user";
  import rule from "@/utils/rule";
  import {encryptMd5} from "@/utils/encrypt";
  import {setSecret,checkHaveSecret} from "@/api/security";

  export default {
    data(){
      return{
        tabNow:'user',
        changeUser:{
          id:'',
          loc:'',
          name:'',
          phone:'',
          sex:'',
          area:[],
          areaLoc:'',
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
        changeUserRule:{},
        changePwdRule:{
          old:rule.Password,
          new:rule.Password,
          checkNew:rule.RePassword
        },
        avatarUploadUrl:baseUrl+'/user/avatar',
        avatarFlag:false,
        avatarPercentage:0,
        haveSecret:false,
        secretData:{
          secret:[
            {question:null,answer:''},
            {question:null,answer:''},
            {question:null,answer:''}
          ]
        },
        secretRule:{
          'secret[0].question':rule.Question,
          'secret[1].question':rule.Question,
          'secret[2].question':rule.Question,
          'secret[0].answer':rule.Answer,
          'secret[1].answer':rule.Answer,
          'secret[2].answer':rule.Answer,
        },
        options:[
          {value:0, label:'您初中班主任的名字是？'},
          {value:1, label:'您的出生地是？'},
          {value:2, label:'您的学号（或工号）是？'},
          {value:3, label:'您父亲的生日是？'},
          {value:4, label:'您高中班主任的名字是？'},
          {value:5, label:'您母亲的生日是？'},
          {value:6, label:'您小学班主任的名字是？'}
          ],
        nowOptions:[],
      }
    },
    methods:{
      optionChange(event){
        let now=this.options
        this.secretData.secret.forEach(item=>{
          now=now.filter(optionItem=>optionItem.value!==item.question)
        })
        this.nowOptions=now
      },
      avatarUpload(param){
        avatarUpload(param,this.$store.getters.id).then(data=>{
          this.$store.dispatch('updateUserAvatar',baseUrl+data)
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
            console.log(this.changeUser.area.join("|")+'|'+this.areaLoc)
            let para={
              id:this.userInfo.id,
              name:this.changeUser.name===''?this.userInfo.name:this.changeUser.name,
              phone:this.changeUser.phone===''?this.userInfo.phone:this.changeUser.phone,
              loc:this.changeUser.area.join("|")+'|'+this.areaLoc,
              sex:this.changeUser.sex===''?this.userInfo.sex:this.changeUser.sex
            }
            this.$store.dispatch('updateUserInfo',para).then(success=>{
              this.$message.success({
                message:success,
                showClose:false
              })
            }).catch(err=>{
              this.$message.error({
                message:err,
                showClose:false
              })
            })
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
            this.$store.dispatch("updatePassword",{old:encryptMd5(this.changePwd.old),new:encryptMd5(this.changePwd.new)}).then(msg=>{
              this.$message.success({
                message: msg,
                showClose:true
              })
            }).catch(err=>{
              this.$message.error({
                message: err,
                showClose:true
              })
            })
            this.$refs['password'].resetFields()
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      submitAddSecret(){
        this.$store.dispatch('setSecret',this.secretData.secret).then(success=>{
          this.$message.success({
            message:success,
            showClose:false
          })
          this.tabNow='user'
        }).catch(err=>{
          this.$message.error({
            message:err,
            showClose:false
          })
        })
      }
    },
    computed:{
      userInfo(){
        return this.$store.getters.user
      }
    },
    mounted() {
      this.changeUser.id=this.userInfo.id
      if(this.userInfo.loc!==null){
        let tempLoc=this.userInfo.loc.split('|')
        this.placeholders=tempLoc.slice(0,3)
        this.changeUser.area=tempLoc.slice(0,3)
        this.areaLoc=tempLoc[3]
      }
      if(Object.keys(this.$route.params).length!==0){
        this.tabNow=this.$route.params.data
      }
      this.nowOptions=this.options
      checkHaveSecret(this.userInfo.id).then(response=>{
        if(response==='success')
          this.haveSecret=false
        else
          this.haveSecret=true
      })
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
