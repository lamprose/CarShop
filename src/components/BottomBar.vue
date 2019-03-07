<template>
  <div class="footer">
    <div class="footerContent">
      <!--底栏快速导航-->
      <el-breadcrumb separator="    ">
        <el-breadcrumb-item :to="{ path: '/' }" class="footerItem">网站首页</el-breadcrumb-item>
        <el-breadcrumb-item class="footerItem">企业合作</el-breadcrumb-item>
        <el-breadcrumb-item class="footerItem">人才招聘</el-breadcrumb-item>
        <el-breadcrumb-item class="footerItem" >
          <label type="text" @click="aboutUs = true">关于我们</label>
        </el-breadcrumb-item>
        <el-breadcrumb-item class="footerItem">专家招募</el-breadcrumb-item>
        <el-breadcrumb-item class="footerItem">常见问题</el-breadcrumb-item>
        <el-breadcrumb-item class="footerItem" :to="toMessage">意见反馈</el-breadcrumb-item>
        <el-breadcrumb-item class="footerItem">友情链接</el-breadcrumb-item>
      </el-breadcrumb>
      <!--版权声明-->
      <div class="footerRight">
        {{footerRight}}
      </div>
      <!--关于我们弹窗-->
      <el-dialog title="关于" :visible.sync="aboutUs" width="30%" center>
        <div style="width: 170px;margin:30px auto">
          <label>组长：谭健雄 141140128</label><br>
          <label>组员：李鹏书 161310523</label><br>
          <label style="margin-left: 42px">饶学亮 161310120</label><br>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  export default {
    data(){
      return{
        //footer版权声明
        footerRight:"Copyright © 2018 cxy.com All Rights Reserved | 沪ICP备 12003892号-11",
        aboutUs: false,
        toMessage:{ path: '/messageboard' },
      }
    }
  }
</script>

<style scoped>
  .footer {
    clear: both;
    width: 100%;
    height: auto;
    background-color: #ffffff;
    float: left;
    margin-right: 30px;
    margin-top: 30px;
    margin-bottom: 10px;
    color: #000000;
    font-size: 10px;
  }
  .footerContent{
    margin: 10px auto;
    width: 700px;
  }
  .footerItem {
    margin-right: 10px;
  }
  .footer .footerRight {
    clear: both;
    font-size: 10px;
    color: darkgray;
  }
</style>
