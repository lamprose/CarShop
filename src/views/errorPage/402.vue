<template>
  <div class="errPage-container">
    <el-row>
      <el-col :span="12">
        <h1 class="text-jumbo text-ginormous">Oops!</h1>
        <h2>未设置密保,没有权限购买车辆</h2>
        <h6>请及时<router-link :to="{name:'User',params:{data:'secret'}}"><span style="text-decoration: underline;color: red">设置密保</span></router-link></h6>
        <ul class="list-unstyled">
          <li>或者你可以去:</li>
          <li class="link-type">
            <router-link :to="{name:'Home'}">回首页</router-link>
          </li>
          <li><a href="#" @click.prevent="dialogVisible=true">点我看图</a></li>
        </ul>
      </el-col>
      <el-col :span="12">
        <img :src="errGif" width="313" height="428" alt="Girl has dropped her ice cream.">
      </el-col>
    </el-row>
    <el-dialog width="42%" :visible.sync="dialogVisible" title="随便看">
      <img :src="ewizardClap" class="pan-img">
    </el-dialog>
  </div>
</template>

<script>
  import errGif from '@/assets/401_images/401.gif'

  export default {
    name: 'Page401',
    data() {
      return {
        errGif: errGif + '?' + +new Date(),
        ewizardClap: 'https://wpimg.wallstcn.com/007ef517-bafd-4066-aae4-6883632d9646',
        dialogVisible: false
      }
    },
    methods: {
      back() {
        if (this.$route.query.noGoBack) {
          this.$router.push({ path: '/' })
        } else {
          this.$router.go(-1)
        }
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="less" scoped>
  .errPage-container {
    width: 800px;
    max-width: 100%;
    margin:100px auto;
    .pan-back-btn {
      background: #008489;
      color: #fff;
      border: none!important;
    }
    .pan-gif {
      margin: 0 auto;
      display: block;
    }
    .pan-img {
      display: block;
      margin: 0 auto;
      width: 100%;
    }
    .text-jumbo {
      font-size: 60px;
      font-weight: 700;
      color: #484848;
    }
    .list-unstyled {
      font-size: 14px;
      margin-top: 50px;
      li {
        padding-bottom: 5px;
      }
      a {
        color: #008489;
        text-decoration: none;
        &:hover {
          text-decoration: underline;
        }
      }
    }
  }
</style>
