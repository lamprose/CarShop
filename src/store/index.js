import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import app from './modules/app'
import getters from './getters'

//用sessionStorage使vuex持久化，保存至本次回话结束，防止刷新导致前端反复向后端请求数据
const handleStore = store => {
  if (sessionStorage.store) store.replaceState(JSON.parse(sessionStorage.store))  // 初始化store
  store.subscribe((mutation, state) => {
    sessionStorage.setItem("store",  JSON.stringify(state))
  })
}

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    user
  },
  getters,
  plugins: [handleStore]
})

export default store
