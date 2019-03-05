<template>
  <div style="height: 100%">
    <router-view v-if="now==='user'" name="userBlankBox"></router-view>
    <router-view v-if="now==='shopCart'" name="shopCartBlankBox"></router-view>
    <router-view v-if="now==='tool'" name="toolBlankBox"></router-view>
  </div>
</template>

<script>
  export default {
    name: "MainBox",
    props:['now'],
    data() {
      return {

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
