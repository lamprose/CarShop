import {  getUserInfo } from '@/api/user'
import {baseUrl} from "@/api";

const defaultAvatar=baseUrl+'/UserAvatar/default_avatar.png'

const now = new Date();
const chat = {
  state: {
    // 会话列表
    //key:会话主键
    //data:最后收到消息时间
    //unread:未读消息数量
    //user:会话聊天用户对象
    //messages:聊天记录
    //content:聊天内容
    //data:聊天消息发送或接受时间
    //self:判断发送或接受
    //status:消息发送状态:1:成功0:离线-1:错误
    sessions: [
      {
        key:'admin',
        date:now,
        unread:0,
        user:{
          id:'admin',
          name:'admin',
          avatar:'https://raw.githubusercontent.com/taylorchen709/markdown-images/master/vueadmin/user.png'
        },
        messages:[
          {
            content: 'Hello，这是一个基于Vue + Vuex + Webpack构建的简单chat示例，聊天记录保存在localStorge, 有什么问题可以通过Github Issue问我。',
            date: now,
            self:false,
            status:1
          }, {
            content: '项目地址: https://github.com/coffcer/vue-chat',
            date: now,
            self:false,
            status:1
          }
        ]
      },
      {
        key:'superAdmin',
        date:now,
        unread:0,
        user:{
          id:'superAdmin',
          name:'superAdmin',
          avatar:'https://raw.githubusercontent.com/taylorchen709/markdown-images/master/vueadmin/user.png'
        },
        messages:[
          {
            content: 'Hello，这是一个基于Vue + Vuex + Webpack构建的简单chat示例，聊天记录保存在localStorge, 有什么问题可以通过Github Issue问我。',
            date: now,
            self:false,
            status:1
          }, {
            content: '项目地址',
            date: now,
            self:false,
            status:1
          }
        ]
      },
    ],
    // 当前选中的会话
    currentSessionId: '',
    // 过滤出只包含这个key的会话
    filterKey: '',
    err:'',
    update:false,
  },
  mutations: {
    INIT_DATA (state,loginId) {
      let data = localStorage.getItem(loginId);
      if (data) {
        state.sessions = JSON.parse(data);
        state.currentSessionId=''
        state.filterKey=''
        state.err=''
        state.update=false
      }else{
        state.sessions=[]
      }
    },
    // 发送消息
    SEND_MESSAGE ({sessions,currentSessionId}, data) {
      let session = sessions.find(item => item.key === currentSessionId);
      session.messages.push({
        content: data.content,
        date: new Date(),
        self: true,
        status:data.status
      });

    },
    // 接受消息
    RECEIVE_MESSAGE (state, data) {
      let session = state.sessions.find(item => item.key === data.sendFrom);
      let dateNow=new Date()
      if(data.sendFrom!==state.currentSessionId)
        session.unread++
      session.date=dateNow
      session.messages.push({
        content: data.content,
        date: dateNow,
        self: false,
        status:data.status
      });
    },
    // 选择会话
    SELECT_SESSION (state, key) {
      state.currentSessionId = key;
      if(key!==''){
        let session = state.sessions.find(item => item.key === key);
        session.unread=0
      }
    } ,
    // 搜索
    SET_FILTER_KEY (state, value) {
      state.filterKey = value;
    },
    // 搜索
    SORT_SESSIONS (state) {
      state.sessions.sort(function (a,b) {
        if(a.unread===0)
          return 1
        else
          return a.date>b.date?1:-1
      })
    },
    ADD_SESSION(state,id){
      getUserInfo({id:id}).then(response => {
        console.log(typeof(response.role)==="undefined")
        console.log(response)
        if(typeof(response.role)==="undefined"){
          state.err='add:no user'
        }else{
          let msg=[]
          console.log("add:"+id)
          if(state.err!=='receive:fail')
            msg.push({
              content: '开始聊天吧^!^',
              date: now,
              self:false,
              status:1
            })
          state.sessions.push({
            key:response.role==='superAdmin'||response.role==='admin'?response.data.shopId:response.data.id,
            user:{
              id:response.role==='superAdmin'||response.role==='admin'?response.data.shopId:response.data.id,
              name:response.role==='superAdmin'||response.role==='admin'?response.data.shopName:response.data.name===null?'用户':response.data.name,
              avatar:response.role==='superAdmin'||response.role==='admin'?(response.data.brand.logo===null?defaultAvatar:baseUrl+response.data.brand.logo):(response.data.avatar===null?defaultAvatar:baseUrl+response.data.avatar),
            },
            date:new Date(),
            unread:0,
            messages:msg
          })
        }
      })
      if(state.err!=='add:no user'&&state.err!=="receive:fail")
        state.currentSessionId=id
    },
    CLEAR_MESSAGES(state,key){
      let session = state.sessions.findIndex(item => item.key === key);
      state.sessions.splice(session,1);
    },
    CLEAR_ALL_MESSAGES(state,loginId){
      state.sessions=[]
      localStorage.removeItem(loginId)
    },
    STORE_SESSIONS(state,loginId){
      if(state.update){
        let obj=JSON.stringify(state.sessions)
        localStorage.setItem(loginId,obj)
        state.update=false
      }
    },
    CHANGE_UPDATE(state){
      state.update=!state.update
    },
    FIND_SESSION(state,data){
      let session = state.sessions.find(item => item.key === data.id);
      if(typeof(session)==="undefined")
        state.err=data.type+":fail"
      else
        state.err=data.type+":success"
    }
  },

  actions:{
    InitData({commit,rootState}){
      return new Promise(resolve => {
        commit('INIT_DATA',rootState.user.id)
        commit('SORT_SESSIONS')
        commit('CHANGE_UPDATE')
        resolve()
      })
    },
    SortSessions({commit}){
      return new Promise(resolve => {
        commit('SORT_SESSIONS')
        commit('CHANGE_UPDATE')
        resolve()
      })
    },
    SendMessage({commit},data){
      return new Promise(resolve => {
        commit('SEND_MESSAGE',data)
        commit('CHANGE_UPDATE')
        resolve()
      })
    },
    ReceiveMessage({commit,state},data){
      return new Promise(resolve => {
        commit('FIND_SESSION',{id:data.sendFrom,type:"receive"})
        if(state.err==="receive:fail")
          commit('ADD_SESSION',data.sendFrom)
        commit('RECEIVE_MESSAGE',data)
        console.log(state.err)
        state.err=""
        commit('SORT_SESSIONS')
        commit('CHANGE_UPDATE')
        resolve()
      })
    },
    SelectSession({commit},key){
      return new Promise(resolve => {
        commit('SELECT_SESSION',key)
        commit('CHANGE_UPDATE')
        resolve()
      })
    },
    Search({commit},value){
      return new Promise(resolve => {
        commit('SET_FILTER_KEY',value)
        resolve()
      })
    },
    AddSession({commit,state},id){
      return new Promise((resolve,reject) => {
        commit('FIND_SESSION',{id:id,type:"add"})
        if(state.err==="add:fail"){
          commit('ADD_SESSION',id)
          commit('CHANGE_UPDATE')
          console.log(state.err)
          if(state.err==="add:fail")
            state.err=''
        }
        if(state.err!==''){
          state.err=''
          reject(state.err)
        }
        else
          resolve()
      })
    },
    StoreSessions({commit,rootState}){
      return new Promise(resolve => {
        commit('STORE_SESSIONS',rootState.user.id)
        resolve()
      })
    },
    ClearMessages({commit},key){
      return new Promise(resolve => {
        commit('CLEAR_MESSAGES',key)
        commit('CHANGE_UPDATE')
        resolve()
      })
    },
    ClearAllMessages({commit,rootState}){
      return new Promise(resolve => {
        commit('CLEAR_ALL_MESSAGES',rootState.user.id)
        commit('CHANGE_UPDATE')
        resolve()
      })
    }
  }
}

export default chat
