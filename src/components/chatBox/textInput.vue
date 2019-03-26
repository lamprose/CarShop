<template>
  <div class="text">
      <el-input class="textarea" type="textarea" resize="none" rows="4" placeholder="按 Ctrl + Enter 发送" v-model="content" @keyup.native="onKeyup"></el-input>
  </div>
</template>

<script>
  import {sendMsg} from "../../api/chat";

  export default {
    data () {
      return {
        content: ''
      };
    },
    methods: {
      onKeyup (e) {
        if (e.ctrlKey && e.keyCode === 13 && this.content.length) {
          sendMsg({sendFrom:this.$store.getters.id,sendTo:this.$store.getters.currentSessionId,msg:this.content}).then((data)=>{
            let result=data!=="success"?data!=="offline"?-1:0:1;
            this.$store.dispatch("SendMessage",{content:this.content,status:result}).then(()=>{
              this.content = '';
            })
          })
        }
      }
    }
  };
</script>

<style lang="less" scoped>
.text {
    height: 100%;
    border-top: solid 1px #ddd;
    textarea {

    }
}
</style>
