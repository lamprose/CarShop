<template>
  <div>
    <transition name="el-zoom-in-center">
      <show-user class="table" v-if="active===0"></show-user>
      <show-book class="table" v-if="active===1"></show-book>
      <show-message class="table" v-if="active===2"></show-message>
      <show-shop class="table" v-if="active===3"></show-shop>
    </transition>
  </div>
</template>

<script>
  export default {
    name: "AdminMain",
    props:{
      active:{
        required:true,
        default:0
      },
    },
    components: {
      ShowMessage: resolve => require(["./ShowMessage.vue"], resolve),
      ShowShop: resolve => require(["./ShowShop.vue"], resolve),
      ShowBook: resolve => require(["./ShowBook.vue"],resolve),
      ShowUser: resolve => require(["./ShowUser.vue"],resolve),
    },
  }
</script>

<style scoped>
  .table{
    position: absolute;
    top:10px;
    left: 0;
    width: 100%;
    transition-duration: 0.3s;
    transition-property: all
  }
</style>
