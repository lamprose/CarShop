<template>
    <div style="margin-top: -150px">
      <h5>默认颜色</h5>
      <el-menu
        :default-active="show"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose" style="position: fixed">
        <el-menu-item index="1" @click="show=1">
          <i class="el-icon-menu"></i>
          <span slot="title">导航一</span>
        </el-menu-item>
        <el-menu-item index="2" @click="show=2">
          <i class="el-icon-menu"></i>
          <span slot="title">导航二</span>
        </el-menu-item>
        <el-menu-item index="3" @click="show=3">
          <i class="el-icon-document"></i>
          <span slot="title">导航三</span>
        </el-menu-item>
        <el-menu-item index="4" @click="show=4">
          <i class="el-icon-setting"></i>
          <span slot="title">导航四</span>
        </el-menu-item>
      </el-menu>
      <show-book v-if="show===1"></show-book>
      <show-message v-if="show===2"></show-message>
      <show-shop v-if="show===3"></show-shop>
      <show-user v-if="show===4"></show-user>
    </div>
</template>

<script>
    export default {
      name: "index",
      components: {
        ShowMessage: resolve => require(["./ShowMessage.vue"], resolve),
        ShowShop: resolve => require(["./ShowShop.vue"], resolve),
        ShowBook: resolve => require(["./ShowBook.vue"],resolve),
        ShowUser: resolve => require(["./ShowUser.vue"],resolve)},
      data(){
        return{
          show:1
        }
      },
      mounted(){
        if(this.$store.getters.roles[0]!=="admin"){
          this.$router.push("/401")
        }
      }
    }
</script>

<style scoped>

</style>
