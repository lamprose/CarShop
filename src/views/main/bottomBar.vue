<template>
  <div class="footer">
    <div id="reason">
      <h2>为什么选择我们？</h2>
      <ul id="reason-list">
        <li class="reason-list-item" v-for="o in reasonList">
          <h3>{{o.h3}}</h3>
          <p>{{o.p}}</p>
        </li>
      </ul>
    </div>
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
        reasonList:[
          {h3:"上汽品质",p:"100%原厂车源，值得信赖"},
          {h3:"原厂质保",p:"三包政策，放心购车"},
          {h3:"优势车价",p:"价享优惠，真实享有"},
          {h3:"专属服务",p:"贴身客服，购车无忧"}
        ],
      }
    }
  }
</script>

<style lang="less" scoped>
  .footer {
    clear: both;
    width: 100%;
    height: auto;
    background-color: #ffffff;
    float: left;
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
  #reason{
    background: black;
    margin-top: 30px;
    & h2{
      position: absolute;
      top: 70px;
      left: 10%;
      font-size: 25px;
      color: white;
    }
    & #reason-list{
      width: 80%;
      height: 180px;
      margin-left: 10%;
      margin-right: 10%;
      & .reason-list-item:first-child{
        margin-left: 0px!important;
      }
      & .reason-list-item{
        margin-top: 30px;
        background: url('../../assets/bg-olive.png') 28px no-repeat;
        background-size: 70%;
        float: left;
        width: 180px;
        margin-left: calc(33.3% - 240px);
        height: 130px;
        color: #b8a26a;
        & h3{
          margin-top: 45px;
        }
        & p{
          margin-top: 50px;
        }
      }
    }
  }

</style>
