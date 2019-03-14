import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import permission from './modules/permission'
import app from './modules/app'
import getters from './getters'

const handleStore = store => {
  if (localStorage.store) store.replaceState(JSON.parse(localStorage.store))  // 初始化store
  store.subscribe((mutation, state) => {
    localStorage.setItem("store",  JSON.stringify(state))
  })
}

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    user,
    permission,
  },
  getters,
  plugins: [handleStore]
})

export default store
