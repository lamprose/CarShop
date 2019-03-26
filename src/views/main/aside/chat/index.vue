<template>
  <div style="height: 100%">
    <audio id="ring" preload hidden src="../../../../../static/ringtone.wav"></audio>
    <transition enter-active-class="slideInLeft" leave-active-class="slideOutLeft">
        <div v-show="!showMessage" class="chat-aside" style="animation-duration: 0.5s;background-color: #2e3238;height: 100%">
          <list @refresh="refreshMessages"></list>
        </div>
    </transition>
    <transition enter-active-class="slideInRight" leave-active-class="slideOutRight">
        <div v-show="showMessage" class="chat-main" style="position:absolute;top:0px;animation-duration: 0.5s;background-color: #eee;width:100%;height: 100%">
          <message style="height: 81%" @back="backClear" ref="messages"></message>
          <text-input style="height: 18%"></text-input>
        </div>
    </transition>

  </div>
</template>

<script>
  import {serverUrl} from '@/api'
  export default {
    name: "chat",
    components: {
      Card: resolve => require(["@/components/chatBox/card"], resolve),
      List: resolve => require(["@/components/chatBox/list"], resolve),
      Message: resolve => require(["@/components/chatBox/message"],resolve),
      TextInput: resolve => require(["@/components/chatBox/textInput"],resolve),
    },
    data(){
      return{
        showMessage:false
      }
    },
    methods:{
      refreshMessages(){
        this.$refs.messages.refreshMsg()
        this.showMessage=true
      },
      backClear(){
        this.showMessage=false;
        this.$store.dispatch('SelectSession','')
      },
      initWebSocket: function () {
        let con= serverUrl + this.$store.getters.id.toString()
        // WebSocket与普通的请求所用协议有所不同，ws等同于http，wss等同于https
        this.websock = new WebSocket(con);
        this.websock.onopen = this.webSocketOnOpen;
        this.websock.onerror = this.webSocketOnError;
        this.websock.onmessage = this.webSocketOnMessage;
        this.websock.onclose = this.webSocketOnClose;
      },
      webSocketOnOpen: function () {
        console.log("WebSocket连接成功");
      },
      webSocketOnError: function (e) {
        console.log("WebSocket连接发生错误");
      },
      webSocketOnMessage: function (e) {
        let loc = e.data.indexOf("&&");
        let _this=this
        let _audio = document.getElementById('ring');//获取audio元素
        _audio.currentTime = 0;//从头开始播放提示音
        _audio.play();//播放
        this.$store.dispatch("ReceiveMessage",{sendFrom:e.data.substr(0,loc),content:e.data.substr(loc+2),status:1}).then(()=>{

        })
      },
      webSocketOnClose: function (e) {
        if(typeof(e)!=='undefined')
          console.log("connection closed (" + e + ")");
      }
    },
    mounted() {
      this.$store.dispatch("InitData")
    },
    created() { // 页面创建生命周期函数
      if(this.userStatus)
        this.initWebSocket()
    },
    beforeDestroy(){
      this.$store.dispatch("StoreSessions");
    },
    destroyed: function () { // 离开页面生命周期函数
      this.webSocketOnClose();
    },
    computed:{
      userSessions(){
        return this.$store.getters.userSessions
      },
      update(){
        return this.$store.getters.update
      },
      currentSessionId(){
        return this.$store.getters.currentSessionId
      },
      userStatus(){
        return this.$store.getters.status
      }
    },
    watch:{
      userSession(){
        this.$store.dispatch("SortSessions")
      },
      update(){
        if(this.update)
          this.$store.dispatch("StoreSessions")
      },
      userStatus(val){
        if(val)
          this.initWebSocket()
        else
          this.webSocketOnClose();
      }
    }
  }
</script>

<style scoped>

</style>
