<template>
    <div style="height: 100%;width: 100%">
      <user-aside v-show="asideBarNow==='hover-user'"></user-aside>
      <cart-aside v-show="asideBarNow==='hover-cart'"></cart-aside>
      <tool-aside v-show="asideBarNow==='hover-tool'"></tool-aside>
      <chat-aside v-show="asideBarNow==='hover-chat'"></chat-aside>
    </div>
</template>

<script>
  export default {
    name: "AsideLayout",
    components:{
      'userAside':resolve => require(["@/views/main/aside/user"], resolve),
      'cartAside':resolve => require(["@/views/main/aside/cart"], resolve),
      'toolAside':resolve => require(["@/views/main/aside/tool"], resolve),
      'chatAside':resolve => require(["@/views/main/aside/chat"], resolve),
    },
    props:{
      asideBarNow:{
        type:String,
        required:true,
        default:'hover-tool'
      }
    }
  }
</script>

<style scoped>

</style>
