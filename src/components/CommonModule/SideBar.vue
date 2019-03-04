<template>
  <div style="height: 600px;background-color: #222">
    <div>
      <el-tooltip class="item" transition="el-zoom-in-left" effect="dark" :content="sideBarItems[i-1].tag" placement="left" v-for="i in 2" :key="i">
        <i :class="sideBarItems[i-1].fa" @click="toggleState"></i>
      </el-tooltip>
    </div>
    <div style="margin-top: 400px">
      <el-tooltip class="item" transition="el-zoom-in-left" effect="dark" :content="sideBarItems[i-1].tag" placement="left" v-for="i in [3,4,5]" :key="i">
        <i :class="sideBarItems[i-1].fa"></i>
      </el-tooltip>
    </div>
  </div>
</template>

<script>
    export default {
      data(){
        return{
          sideBarItems:[
            {fa:"fa fa-user",tag:"我的"},
            {fa:"fa fa-shopping-cart",tag:"购物车"},
            {fa:"fa fa-paper-plane",tag:"反馈"},
            {fa:"fa fa-map-marker",tag:"定位"},
            {fa:"fa fa-angle-up",tag:"返回顶部"},
          ],
          show:false
        }
      },
      methods:{
        toggleState(){
          // console.log(event.target);
          this.$emit('toggleSideBarBlank');
        }
      }
    }
</script>

<style scoped>
  .fa{
    float: top;
    width: 100%;
    margin-top: 20px;
  }
  i{
    color: #fff;
  }
  i:hover{
    color: red;
  }
</style>
