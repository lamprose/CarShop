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
            <el-button type="text" @click="loginProps.show=true">登陆</el-button>
            <el-button type="text" @click="logoutProps.show=true">注册</el-button>
            <el-button type="text">关注我们</el-button>
            <el-button type="text">商户中心</el-button>
            <el-button type="text">首页</el-button>
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
            //热门搜索
            hotSearchText:[
              "威朗","晶锐","Yeti"
            ],
            //搜索内容文字
            searchText:"",
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
        handleSelect(key, keyPath) {
          this.typeIndex = key
          //console.log(key, keyPath);
        },
        //TODO:实现搜索建议下拉的数据写入
        querySearchAsync(queryString, cb) {
          let searchSuggest = this.searchSuggestions;
          searchSuggest=this.unique(searchSuggest)
          let results = queryString ? searchSuggest.filter(this.createStateFilter(queryString)) : searchSuggest;
          //实现加载效果
          clearTimeout(this.timeout);
          this.timeout = setTimeout(() => {
            cb(results);
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
          let searchArray=new Array();
          this.$ajax.get('http://localhost:8080/book/0')
            .then(response=>{
              response.data.forEach(ob=>{
                let o={'value':ob['title'],'title':ob['value']}
                searchArray.push(o)
              })
              this.searchSuggestions=searchArray
              //console.log(this.searchSuggestions);
            });
        }
      },
      mounted(){
        this.$cookie.getCookie();
      },
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
