<template>
  <div id="app" @click="refreshTime">
    <router-view/>
  </div>
</template>

<script>
  export default{
    name: 'App',
    data(){
      return{
        lTime: 0, // 最后一次点击的时间
        cTime: 0, //当前时间
        tOut: 60 * 30  //超时时间10min
      }
    },
    methods:{
      //点击页面刷新当前时间
      refreshTime () {
        this.lTime = this.cTime  //当界面被点击更新点击时间
      },
      tTime() {
        this.cTime += 1
        if(!this.$store.getters.status||this.cTime -this.lTime <= this.tOut)
          return
        else {
          this.$store.dispatch("LogOut").then(()=>{
            /*window.location.reload()*/
            this.$alert('长时间未操作，自动注销', 'Error', {
              confirmButtonText: '确定',
              type: 'error',
              center: true,
            });
            document.getElementById("asideBarBlank").style.width="0px"
            document.getElementById("float").style.right="10px"
            document.getElementById("top").style.right=this.showTop?"10px":"-36px"
          })
        }
      }
    },
    mounted(){
      window.setInterval(this.tTime, 1000)
    }
  }
</script>
