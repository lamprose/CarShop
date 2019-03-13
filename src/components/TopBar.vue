<template>
  <div class="navigation">
    <div style="width: 100%;">
      <el-row class="firstNav">
        <!--logo图片-->
        <el-col :span="4" ><div class="log">
          <img class="logo" src="../assets/carshop-logo.jpg">
        </div></el-col>
        <!--搜索栏-->
        <el-col :span="13"><div id="search">
          <el-autocomplete placeholder="搜索商品"
                           v-model="searchText"
                           :fetch-suggestions="querySearchAsync"
                           class="searchInput">
            <el-button v-for="(hot,index) in hotSearchText" slot="suffix" type="text" :key="index">{{hot}}</el-button>
            <el-button slot="append" icon="el-icon-search" ></el-button>
          </el-autocomplete>
          <br>
        </div></el-col>
        <!--搜索栏右侧快速导航-->
        <el-col :span="7">
          <div class="top-button">
            <el-button type="text" v-if="!status" @click="loginProps.show=true">登陆</el-button>
            <el-button type="text" v-if="!status" @click="toRegister">注册</el-button>
            <label v-if="status">
              <el-dropdown>
                <i>{{username}}</i>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>
                    <el-button type="text">我的</el-button>
                  </el-dropdown-item>
                  <el-dropdown-item>
                    <el-button type="text" @click="toAdmin">管理</el-button>
                  </el-dropdown-item>
                  <el-dropdown-item>
                    <el-button type="text" @click="logoutProps.show=true" style="color: #f78989">注销</el-button>
                  </el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </label>
            <el-button type="text" @click="toAdmin">关注我们</el-button>
            <el-button type="text">商户中心</el-button>
          </div>
        </el-col>
      </el-row>
      <!--导航栏项-->
      <div><el-menu default-active="1" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item v-for="(o,index) in navigationBarItem" :key="index">
          <router-link :to="o.to">{{o.text}}</router-link>
        </el-menu-item>
        <el-menu-item>
          <label>400 866 999</label>
        </el-menu-item>
      </el-menu></div>
    </div>
    <!--登陆弹窗-->
    <login :props="loginProps" :user="user"></login>
    <!--注销弹窗-->
    <logout-confirm :props="logoutProps"></logout-confirm>
  </div>
</template>

<script>
import {getHotSearch,searchByText} from "../api/search";
export default {
  name: "TopBar",
  components:{
    'login':resolve => require(["./Login.vue"], resolve),
    'logout-confirm':resolve => require(["./LogoutConfirm.vue"], resolve),
  },
  data(){
      return{
        loginProps:{
          show:false,
          radio:'0',
        },
        logoutProps:{
          show:false,
        },
        //用户  账号和密码
        user: {
          address:null,
          flag:null,
          id:'',
          name:null,
          password:'',
          phone:null,
          sex:null,
          uId:null,
          secretOption:null,
          secret:null
        },
        timeout:null,
        //热门搜索
        hotSearchText:[],
        //搜索内容文字
        searchText:"",
        //
        searchSuggestions:[],
        showHotSearch:true,
        //导航栏内容
        navigationBarItem:[
          {text:"首页",to:"/home"},
          {text:"享推荐",to:"/price"},
          {text:"享定制",to:"/search"},
          {text:"品牌旗舰店",to:"/shop"},
          {text:"全国实体店",to:"/shop"},
          {text:"贷款购车",to:"/tool"},
        ],
      }
  },
  methods:{
    toAdmin(){
      this.$router.push({name:'admin'});
    },
    toRegister(){
      this.$router.push({name:'Register'});
    },
    handleSelect(key, keyPath) {
      this.typeIndex = key
      //console.log(key, keyPath);
    },
    //TODO:实现搜索建议下拉的数据写入
    querySearchAsync(queryString, cb) {
      let searchSuggest = this.searchSuggestions;
      searchSuggest=this.unique(searchSuggest)
      if(searchSuggest[0].value!="无结果..."&&queryString){
        searchSuggest =  searchSuggest.filter(this.createStateFilter(queryString))  ;
      }
      //实现加载效果
      clearTimeout(this.timeout);
      this.timeout = setTimeout(() => {
        cb(searchSuggest);
      }, 2700 * Math.random());
    },
    //TODO:实现输入过滤功能
    createStateFilter(queryString) {
      return (state) => {
        return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) !== -1);
      };
    },
    unique(arr) {
      const res = new Map();
      return arr.filter((arr) => !res.has(arr.value) && res.set(arr.value, 1))
    },
    //TODO:实现搜索跳转功能
    toSearchResult(){
      if(this.searchText==''){
        this.$message.error({
          message:"搜索内容不能为空",
          showClose:true
        });
        return
      }
      this.$router.push({
        name:'Search',
        params:{queryString:this.searchText}
      })
    },
    //TODO:加载热门搜索
    loadSearchSuggestion(){
      getHotSearch().then(response =>{
        let temp=[]
        response.data.data.forEach(ob => {
          let o={'value':ob['text']}
          temp.push(o)
        })
        this.searchSuggestions=temp;
      })
    }
  },
  mounted(){
    this.loadSearchSuggestion();
    this.searchText=this.searchSuggestions[0,3];
    console.log(this.$store.getters.status)
  },
  watch:{
    //监控搜索框的内容
    searchText(){
      //如果为空则加载热门分类到searchSuggestions
      if(this.searchText===''){
        this.loadSearchSuggestion()
      }else {
        let searchArray=new Array();
        searchByText(this.searchText).then(response=>{
            if(!response.data){
              searchArray.push({value:'无结果...'})
            }else{
              response.data.forEach(ob=>{
                let o={'value':ob['text']}
                searchArray.push(o)
              })
            }
            this.searchSuggestions=searchArray
            //console.log(this.searchSuggestions);
          });
      }
    }
  },
  computed:{
    status(){
      return this.$store.getters.status;
    },
    username(){
      return this.$store.getters.name;
    }
  }
}
</script>

<style lang="less" scoped>
  .logo{
    min-width: 120px;
    width: 100%;
    height: 100%;
  }
  #search{
    margin-left: 20px;
    min-width: 275px;
  }
  .searchInput{
    width: 100%;
  }
  .el-menu-demo{
    width: 710px;
    margin-left: calc( 50% - 305px ) ;
    border: none;
    min-width: 710px;
  }
  .el-menu-demo li a{
    text-decoration: none;
    font-size: 16px;
    font-weight: bold;
  }
  .el-menu-demo li:first-child a{
    color: red;
  }
  .el-menu-demo li a:hover{
    color: red;
  }
  .top-button{
    margin-left:calc(50% - 132px);
    min-width: 264px;
    float: left;
  }
</style>
