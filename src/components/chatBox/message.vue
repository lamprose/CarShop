<template>
  <div>
    <div class="message-top">
      <svg-icon icon-class="angle-left" style="float: left" @click="back"></svg-icon>
      <span>{{typeof(session)==="undefined"?"":session.user.name}}</span>
    </div>
    <div class="message" ref="ms">
      <ul>
        <li v-for="item in (typeof(session)==='undefined'?'':session.messages)" ref="list" v-scroll>
          <p class="time">
            <span>{{ item.date | time }}</span>
          </p>
          <div class="main" :class="{ self: item.self }">
            <img class="avatar" width="30" height="30" :src="item.self ? loginUser.avatar : session.user.avatar" />
            <div class="text">
              <i v-show="item.self" :class="item.status===1?'el-icon-success':item.status===0?'el-icon-warning':'el-icon-error'"></i>
              {{ item.content }}
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>

</template>

<script>
  import {mapGetters} from 'vuex'
  export default {
    data(){
      return{

      }
    },
    filters: {
      // 将日期过滤为 hour:minutes
      time (date) {
        if (typeof date === 'string') {
          date = new Date(date);
        }
        return date.getHours() + ':' + date.getMinutes();
      }
    },
    methods:{
      refreshMsg(){
        /*this.messageToBottom()*/
      },
      messageToBottom(){
        if(typeof(this.$refs.list)!=="undefined")
          this.$refs.list[this.$refs.list.length-1].scrollIntoView()
      },
      back(){
        this.$emit("back")
      }
    },
    mounted() {
      /*this.messageToBottom();*/
    },
    computed:{
      session(){
        return this.$store.getters.nowSession
      },
      loginUser(){
        return this.$store.getters.user
      }
    },
    directives: {
      scroll: {
        inserted(el) {
          el.scrollIntoView()
        }
      }
    }
  };
</script>

<style lang="less" scoped>
  .message-top{
    padding: 10px;
  }
  .message {
    padding: 10px 15px;
    overflow-y: scroll;
    height: 87%;
    li {
      margin-bottom: 15px;
    }
    .time {
      margin: 7px 0;
      text-align: center;

      > span {
        display: inline-block;
        padding: 0 18px;
        font-size: 12px;
        color: #fff;
        border-radius: 2px;
        background-color: #dcdcdc;
      }
    }
    .avatar {
      float: left;
      margin: 0 10px 0 0;
      width: 36px;
      height: 36px;
    }
    .text {
      display: table;
      position: relative;
      padding: 0 10px;
      max-width: 100%;
      min-height: 30px;
      line-height: 2.5;
      font-size: 12px;
      text-align: left;
      word-break: break-all;
      background-color: #fafafa;
      border-radius: 4px;

      &:before {
        content: " ";
        position: absolute;
        top: 9px;
        right: 100%;
        border: 6px solid transparent;
        border-right-color: #fafafa;
      }
    }
    .self {
      text-align: right;
      .avatar {
        float: right;
        margin: 0 0 0 10px;
      }
      .text {
        background-color: #b2e281;
        max-width: ~'calc(100% - 70px)';
        display: inline-block;
          &:before {
            right: inherit;
            left: 100%;
            border-right-color: transparent;
            border-left-color: #b2e281;
          }
      }
    }
  }
</style>
