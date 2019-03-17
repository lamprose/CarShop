<template>
  <div>
    <div id="hover-button">
      <el-tooltip v-for="(o,index) in float" placement="left" :key="index">
        <div slot="content">{{o.tag}}</div>
        <el-button circle class="hover-button-close"
                   :id="o.id"
                   type="primary"
                   size="medium"
                   :key="index"
                   @click="toggle">
          <svg-icon :icon-class="o.icon"></svg-icon>
        </el-button>
      </el-tooltip>
    </div>
  </div>
</template>

<script>
  export default {
    name: "FloatButton",
    props:{
      float:Array
    },
    data(){
      return{
        floatStatus:false
      }
    },
    methods:{
      hoverEnter(){
        this.float.forEach(item=>{
          document.getElementById(item.id).classList.remove("hover-button-close");
          document.getElementById(item.id).classList.add("hover-button-open");

        })
        document.getElementById("hover-button").style.height="320px"
      },
      hoverLeave(){
        this.float.forEach(item=>{
          document.getElementById(item.id).classList.add("hover-button-close");
          document.getElementById(item.id).classList.remove("hover-button-open");
        })
        document.getElementById("hover-button").style.height="40px"
      },
      toggle(){
        let id=event.currentTarget.id
        if(id=="hover-phone"||id=="hover-locate")
          return;
        else if(!this.$store.getters.status&&(id==="hover-login"||id==="hover-register")){
          this.$message.error({
            message:"请先登录",
            showClose:true
          })
        }else
          this.$emit("toggleAsideBarBlankBox",{id:id})
      },
    },
    mounted() {
      document.getElementById("hover-button").addEventListener("mouseenter",this.hoverEnter);
      document.getElementById("hover-button").addEventListener("mouseleave",this.hoverLeave);
    }
  }
</script>

<style lang="less" scoped>
  #hover-button{
    position: fixed;
    top: 30px;
    width: 40px;
    height: 40px;
  }
  .hover-button-open+.hover-button-open{
    margin-top: 20px;
    opacity: 1;
  }
  .hover-button-close+.hover-button-close{
    margin-top: 0px;
    opacity: 0;
  }
  .el-button+.el-button {
    margin-left: 0px;

  }
  #hover-shop-cart{
    transition-property: margin-top;
    transition-duration: 0.2s;
  }
  #hover-tool{
    transition-property: margin-top;
    transition-duration: 0.4s;
  }
  #hover-phone{
    transition-property: margin-top;
    transition-duration: 0.6s;
  }
  #hover-locate{
    transition-property: margin-top;
    transition-duration: 0.8s;
  }
  #hover-top{
    transition-property: margin-top;
    transition-duration: 1s;
  }
</style>
