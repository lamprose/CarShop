<template>
  <div>
    <div id="hover-button">
      <el-badge :hidden="floatTotal===0?true:!floatHide" :value="floatTotal">
        <el-tooltip v-for="(o,index) in float" placement="left" :key="index" v-if="showPermissionFloat(o.authority)">
          <div slot="content">{{o.tag}}</div>
          <el-badge :hidden="o.val===0?true:floatHide" :id="o.id" :class="floatHide?'hover-button-close':'hover-button-open'" :value="o.val">
            <el-button circle type="primary" size="medium" :key="index" @click="toggle(o.id)">
              <svg-icon :icon-class="o.icon"></svg-icon>
            </el-button>
          </el-badge>
        </el-tooltip>
      </el-badge>
    </div>
  </div>
</template>

<script>
  import {showPermission} from "../utils";

  export default {
    name: "FloatButton",
    props:{
      float:Array
    },
    data(){
      return{
        floatHide:true,
      }
    },
    methods:{
      hoverEnter(){
        this.floatHide=false
        document.getElementById("hover-button").style.height="320px"
      },
      hoverLeave(){
        this.floatHide=true
        document.getElementById("hover-button").style.height="40px"
      },
      toggle(id){
        if(id==="hover-phone"||id==="hover-locate")
          return;
        else if(!this.$store.getters.status&&(id==="hover-login"||id==="hover-register")){
          this.$message.error({
            message:"请先登录",
            showClose:true
          })
        }else
          this.$emit("toggleAsideBarBlankBox",{id:id})
      },
      showPermissionFloat(auth){
        let role = this.$store.getters && this.$store.getters.role
        const tempRoles= this.$store.getters && this.$store.getters.tempRoles
        return showPermission(auth,role,tempRoles)
      }
    },
    mounted() {
      document.getElementById("hover-button").addEventListener("mouseenter",this.hoverEnter);
      document.getElementById("hover-button").addEventListener("mouseleave",this.hoverLeave);
    },
    computed:{
      floatTotal(){
        return this.float.reduce(function (prev, cur) {
          if(typeof(cur.val)!=="undefined")
            return prev + cur.val;
          else
            return prev
        },0)
      }
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
  #hover-cart{
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
