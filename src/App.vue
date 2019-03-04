<template>
  <div id="app" style="border-style: solid;">
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
      <el-aside width="50px" class="aside" id="abc"><router-view name="sideBar" @toggleSideBarBlank="toggleStates"></router-view></el-aside>
      <el-aside v-if="show" width="200px" class="aside"><router-view name="sideBar"></router-view></el-aside>
    </el-container>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  data(){
    return{
      show: false,
    }
  },
  methods:{
    toggleStates(){
      this.show=!this.show
      if(this.show===false)
        document.getElementById("abc").style.cssText="right:0px"
      else
        document.getElementById("abc").style.cssText="right:200px"
    }
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
  margin-top: 0px;
  height: auto;
}
  .Main{
    margin-left: 10%;
    margin-right: 10%;
  }
  .topBar{

  }
  .aside{
    position: absolute;
    right: 0px;
  }
</style>
