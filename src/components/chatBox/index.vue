<template>
    <div style="height: 500px;width: 100%">
      <audio id="ring" controls hidden src="../../../static/ringtone.wav"></audio>
      <el-col :span="6" style="height: 100%"><div class="chat-aside" style="background-color: #2e3238;height: 100%">
        <card></card>
        <list @refresh="refreshMessages"></list>
      </div></el-col>
      <el-col :span="18" style="height: 100%"><div class="chat-main" style="background-color: #eee;height: 100%">
        <message style="height: 68%" ref="messages"></message>
        <text-input style="height: 32%"></text-input>
      </div></el-col>
    </div>
</template>

<script>
  import url from '@/api'
  export default {
    name: "index",
    components: {
      Card: resolve => require(["./card"], resolve),
      List: resolve => require(["./list"], resolve),
      Message: resolve => require(["./message"],resolve),
      TextInput: resolve => require(["./textInput"],resolve),
    },
    methods:{
      refreshMessages(){
        this.$refs.messages.refreshMsg()
      },
      initWebSocket: function () {
        let con="ws://"+url.serverUrl.toString()+"/websocket/"+this.$store.getters.id.toString()
        // WebSocket与普通的请求所用协议有所不同，ws等同于http，wss等同于https
        this.websock = new WebSocket(con);
        this.websock.onopen = this.websocketonopen;
        this.websock.onerror = this.websocketonerror;
        this.websock.onmessage = this.websocketonmessage;
        this.websock.onclose = this.websocketclose;
      },
      websocketonopen: function () {
        console.log("WebSocket连接成功");
      },
      websocketonerror: function (e) {
        console.log("WebSocket连接发生错误");
      },
      websocketonmessage: function (e) {
        let loc = e.data.indexOf("&&");
        let _this=this
        let _audio = document.getElementById('ring');//获取audio元素
        _audio.currentTime = 0;//从头开始播放提示音
        _audio.play();//播放
        this.$store.dispatch("ReceiveMessage",{sendFrom:e.data.substr(0,loc),content:e.data.substr(loc+2),status:1}).then(()=>{
          _this.refreshMessages()
        })
      },
      webSocketClose: function (e) {
        if(e.code)
          console.log("connection closed (" + e.code + ")");
      }
    },
    mounted() {
      this.$store.dispatch("InitData")
    },
    created() { // 页面创建生命周期函数
      this.initWebSocket()
    },
    beforeDestroy(){
      this.$store.dispatch("StoreSessions");
    },
    destroyed: function () { // 离开页面生命周期函数
      this.webSocketClose();
    },
    computed:{
      userSessions(){
        return this.$store.getters.userSessions
      },
      update(){
        return this.$store.getters.update
      }
    },
    watch:{
      userSession(){
        this.$store.dispatch("SortSessions")
      },
      update(){
        if(this.update)
          this.$store.dispatch("StoreSessions")
      }
    }
  }
</script>

<style scoped>

</style>
