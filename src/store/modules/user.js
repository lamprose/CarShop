import { loginByUsername, logout, getUserInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import {register} from "@/api/user";

const user = {
  state: {
    id: '',
    token: getToken('token'),
    status: false,
    name: '',
    avatar: '',
    introduction: '',
    roles: [],
  },

  mutations: {
    SET_USER:(state,user)=>{
      state.id=user.id
      state.token = user.token
      state.status = true
      state.name = user.name
      state.avatar = user.avatar
      state.introduction = user.introduction
      state.roles = user.roles
    },
    SET_ID: (state, id) => {
      state.id = id
    },
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_STATUS: (state, status) => {
      state.status = status
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_INTRODUCTION: (state, introduction) => {
      state.introduction = introduction
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    CLEAR_USER:(state)=>{
      state.id=''
      state.token = ''
      state.status = false
      state.name = ''
      state.avatar = ''
      state.introduction = ''
      state.roles = []
    }
  },

  actions: {
    // 用户名登录
    LoginByUsername({ commit }, loginInfo) {
      const id = loginInfo.id.trim()
      return new Promise((resolve, reject) => {
        loginByUsername(id, loginInfo.password).then(response => {
          const data = response.data
          commit('SET_USER',data)
          setToken('token',response.data.token)
          if(loginInfo.radio==='1'){
            setToken('id',id)
            setToken('password',loginInfo.password)
          }else{
            removeToken('id')
            removeToken('password')
          }
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    GetUserInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getUserInfo(state.token).then(response => {
          // 由于mockjs 不支持自定义状态码只能这样hack
          if (!response.data) {
            reject('Verification failed, please login again.')
          }
          const data = response.data

          if (data.roles && data.roles.length > 0) { // 验证返回的roles是否是一个非空数组
            commit('SET_ROLES', data.roles)
          } else {
            reject('getInfo: roles must be a non-null array!')
          }
          commit('SET_USER', data)
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    Register({ commit }, userInfo) {
      const username = userInfo.id.trim()
      return new Promise((resolve, reject) => {
        register(username, userInfo.password).then(response => {
          const data = response.data
          commit('SET_USER',data)
          setToken('token',response.data.token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 第三方验证登录
    // LoginByThirdparty({ commit, state }, code) {
    //   return new Promise((resolve, reject) => {
    //     commit('SET_CODE', code)
    //     loginByThirdparty(state.status, state.email, state.code).then(response => {
    //       commit('SET_TOKEN', response.data.token)
    //       setToken(response.data.token)
    //       resolve()
    //     }).catch(error => {
    //       reject(error)
    //     })
    //   })
    // },

    // 登出
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_STATUS',false)
          commit('CLEAR_USER', [])
          removeToken('token')
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        commit('SET_STATUS',false)
        removeToken('token')
        resolve()
      })
    },

    // 动态修改权限
    /*ChangeRoles({ commit, dispatch }, role) {
      return new Promise(resolve => {
        commit('SET_TOKEN', role)
        setToken(role)
        getUserInfo(role).then(response => {
          const data = response.data
          commit('SET_ROLES', data.roles)
          commit('SET_NAME', data.name)
          commit('SET_AVATAR', data.avatar)
          commit('SET_INTRODUCTION', data.introduction)
          dispatch('GenerateRoutes', data) // 动态修改权限后 重绘侧边菜单
          resolve()
        })
      })
    }*/
  }
}

export default user
