<template>
    <div>
      <el-menu :default-active="active" :collapse-transition="true" :collapse="isCollapse" @select="handleSelect">
        <transition name="el-zoom-in-center">
          <el-menu-item index="-1" key="-1">
            <svg-icon icon-class="home"></svg-icon>
            <span slot="title">首 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;页</span>
          </el-menu-item>
        </transition>
        <transition-group name="el-zoom-in-center">
          <el-menu-item v-for="(o,index) in menuItem" :index="index+''" :key="index+1" v-show="showPermission(o.authority)">
            <svg-icon :icon-class="o.icon"></svg-icon>
            <span slot="title">{{o.title}}</span>
          </el-menu-item>
        </transition-group>

      </el-menu>
    </div>
</template>

<script>
  export default {
    name: "AdminAside",
    props:{
      isCollapse:{
        type:Boolean,
        default:true,
      },
      active:{
        type:String,
        required:true
      }
    },
    data() {
      return{
        menuItem:[
          {icon:"user",title:"用户管理",authority:'superAdmin'},
          {icon:"bookmark",title:"品牌管理",authority:'superAdmin'},
          {icon:"bank",title:"店铺管理",authority:'superAdmin'},
          {icon:"archive",title:"商品管理",authority:'admin'},
          {icon:"shopping-cart",title:"订单管理",authority:'admin'}
        ]
      }
    },
    methods: {
      handleSelect(key, keyPath) {
        this.$emit("switch",{key:key})
      },
      showPermission(authority){
        let role = this.$store.getters && this.$store.getters.role
        const tempRoles= this.$store.getters && this.$store.getters.tempRoles
        if(tempRoles && tempRoles.length>0){
          role="admin"
        }
        return authority===role
      }
    },
    computed:{

    }
  }
</script>

<style scoped>
  .el-menu{
    border: none;
  }
</style>
