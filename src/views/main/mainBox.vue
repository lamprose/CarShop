<template>
  <div style="min-height: 1000px">
    <router-view v-if="isRouterAlive"></router-view>
  </div>
</template>

<script>
  export default {
    name: "MainBox",
    data() {
      return {
        msg: "Welcome to Your Vue.js App",
        isRouterAlive:true,
      };
    },
    provide(){
      return{
        reload:this.reload
      }
    },
    methods:{
      reload(){
        this.isRouterAlive = false;
        this.$nextTick(()=>(this.isRouterAlive=true));
      }
    }
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1,
  h2 {
    font-weight: normal;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }
</style>
