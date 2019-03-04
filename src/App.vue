<template>
  <div id="app">
    <el-container>
      <el-container class="Main">
        <el-header class="topBar"><router-view name="topBar"></router-view></el-header>
        <el-container>
          <el-main>
            <router-view name="mainBox"></router-view>
          </el-main>
        </el-container>
        <el-footer><router-view name="bottomBar"></router-view></el-footer>
      </el-container>
      <el-aside width="50px" class="aside" id="sideBar"><router-view name="sideBar" @toggleSideBarBlank="toggleStates"></router-view></el-aside>
      <el-aside v-if="show" width="0px" class="aside" id="sideBarBlank"><router-view name="sideBarBlank"></router-view></el-aside>
    </el-container>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  data(){
    return{
      show: true,
    }
  },
  methods:{
    toggleStates(){
      var blank=document.getElementById("sideBarBlank")
      var side=document.getElementById("sideBar")
      if(blank.style.width==="0px"){
        blank.style.width="200px"
        side.style.right="200px"
      }else{
        blank.style.width="0px"
        side.style.right="0px"
      }
    }
  },
  mounted() {
    /*document.addEventListener('click',function (e) {
      let flag=e.target.contains(document.getElementsByClassName('fa')[0])
      console.log(flag)
      console.log(e.target)
    })*/
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: -10px;
  height: auto;
}
  .Main{
    margin-left: 10%;
    margin-right: 10%;
  }
  .topBar{

  }
  .aside{
    position: fixed;
    right: 0px;
    height: max-content;
  }
  #sideBar{
    transition-property: right;
    transition-duration: 2s;
  }
  #sideBarBlank{
    transition-property: width;
    transition-duration: 2s;
  }
</style>
