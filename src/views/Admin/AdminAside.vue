<template>
    <div>
      <el-menu default-active="1" :collapse-transition="true" :collapse="iscollapse" @select="handleSelect">
        <el-menu-item v-for="(o,i) in menuItem" :index="i+''" :key="i">
          <svg-icon :icon-class="o.icon"></svg-icon>
          <span slot="title">{{o.title}}</span>
        </el-menu-item>
      </el-menu>
    </div>
</template>

<script>
  export default {
    name: "AdminAside",
    props:{
      iscollapse:true,
    },
    data() {
      return{
        menuItem:[
          {icon:"user",title:"用户管理"},
          {icon:"bank",title:"店铺管理"},
          {icon:"archive",title:"商品管理"},
          {icon:"shopping-cart",title:"订单管理"}
        ]
      }
    },
    methods: {
      handleSelect(key, keyPath) {
        this.$emit("switch",{key:key})
      }
    }
  }
</script>

<style scoped>

</style>
