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
      <el-aside width="50px"><router-view name="sideBar"></router-view></el-aside>
    </el-container>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
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
    margin-right: 9%;
    margin-right: calc(10% - 50px);
  }
  .topBar{

  }
</style>
