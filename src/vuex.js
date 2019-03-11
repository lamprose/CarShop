import Vue from 'vue'
import Vuex from 'vuex';

Vue.use(Vuex)

const state = {
  user:{
    address:null,
    flag:null,
    id:'',
    name:null,
    password:'',
    phone:null,
    sex:null,
    uId:null,
    secretOption:null,
    secret:null
  },
  loginStatus:false
}

const mutations = {
  setUser(state, str){
    state.user = str;
  },
  changeLoginStatus(state){
    state.loginStatus=!state.loginStatus;
  }
}

const store = new Vuex.Store({
  state: state,
  mutations: mutations
})

export default store;
