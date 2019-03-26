
<template>
<div class="list">
  <div class="list-top">
    <el-input class="addTalk round" placeholder="search or add user..." v-model="topInput" @keyup.native.enter="addTalkTo"></el-input>
    <el-button size="mini" class="clear-btn" icon="delete el-icon-delete" @click="clearAllMessages"></el-button>
  </div>
  <ul>
    <li v-for="item in sessions">
      <div style="width: 70%;" @click="selectSession(item.key)">
        <el-badge :hidden="item.unread===0" :value="item.unread" style="float: left">
          <img class="avatar"  width="30" height="30" :alt="item.user.name" :src="item.user.avatar">
        </el-badge>
        <p class="name">{{item.user.name}}</p>
      </div>
      <el-button class="list-delete" style="" icon="danger el-icon-delete" type="text" circle @click="clearMessages(item.key)"></el-button>
    </li>
  </ul>
</div>
</template>

<script>
  import {mapGetters} from 'vuex'
  export default {
    data(){
      return{
        topInput:"",
      }
    },
    methods:{
      selectSession(key){
        this.$store.dispatch("SelectSession",key);
        this.$emit("refresh")
      },
      addTalkTo(){
        if(this.topInput===this.$store.getters.id){
          this.$message.error({
            message:"不能添加自己喔^!^",
            showClose:true
          })
          return
        }
        this.$store.dispatch("AddSession",this.topInput).then((err)=>{
          if(err!=="")
            this.$message.error({
              message:err,
              showClose:true
            })
          else{
            this.topInput=""
            this.$emit("refresh")
          }
        })
      },
      clearMessages(key){
        console.log(key)
        this.$store.dispatch("ClearMessages",key);
        this.$emit("refresh")
      },
      clearAllMessages(){
        this.$store.dispatch("ClearAllMessages");
      },
    },
    computed:mapGetters({
      sessions:'userSessions'
    }),
    watch:{
      topInput(val){
        this.$store.dispatch("Search",val);
      }
    }
  };
</script>

<style scoped lang="less">
  .el-input__suffix{
    border-radius: 20px!important;
    height: 25px;
    width: 25px;
  }
  .delete{
    display: block;
  }
  .list-delete{
    color: white;
    display: inline-block;
    opacity: 0;
    &:hover{
      opacity: 1;
    }
  }
  .list-top{
    padding: 15px;
  }
  .list {
    .clear-btn{
      display: inline-block;
      width: 50px;
      font-size: 12px;
      color: #fff;
      height: 30px;
      line-height: 30px;
      border: solid 1px #3a3a3a;
      border-radius: 20px;
      outline: none;
      background-color: #26292E;
    }
    .addTalk{
      display: block;
      float: left;
      margin-left: 30px;
      width: 60%;
      height: 36px;
    }
    li {
      padding: 12px 15px;
      border-bottom: 1px solid #292C33;
      cursor: pointer;
      transition: background-color .1s;
      &:hover {
        background-color: rgba(255, 255, 255, 0.1);

      }
    }
    .avatar, .name {
      vertical-align: middle;
      width: calc(100% - 51px);
      float: left;
      color: #ffffff;
    }
    .avatar {
      display: inline-block;
      float: left;
      width: 36px;
      height: 36px;
    }
    .name {
        display: inline-block;
        margin: 0 0 0 15px;
      padding-top: 8px;
    }
  }
</style>
