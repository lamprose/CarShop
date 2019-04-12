import { loginById,loginByToken, logout, getUserInfo,checkSession } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import {register} from "@/api/user";
import {baseUrl} from "../../api";

const user = {
  state: {
    id: '',
    token: getToken('token'),
    name: '',
    sex:'',
    phone:'',
    loc:'',
    role: '',
    avatar: '',
    brandId:'',
    status: false,
  },

  mutations: {
    SET_USER:(state,info)=>{
      if(info.role==='normal'){
        state.id=info.data.id
        state.name = info.data.name===null?"User":info.data.name
        state.sex = info.data.sex
        state.loc = info.data.loc
        state.avatar = info.data.avatar===null?"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif":baseUrl+"/UserAvatar/"+info.data.avatar
      }
      else if(info.role==='admin' || info.role==='superAdmin'){
        state.id = info.data.shopId
        state.name = info.data.shopName===null?"admin":info.data.shopName
        state.brandId=info.data.brand.brandId
        state.avatar = info.data.brand.logo===null?"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif":baseUrl+"/UserAvatar/"+info.data.brand.logo
      }
      state.token = info.data.token
      state.phone = info.data.phone
      state.role = info.role
      state.status = true
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
    SET_ROLES: (state, role) => {
      state.role = role
    },
    CLEAR_USER:(state)=>{
      state.id=''
      state.token = ''
      state.name = ''
      state.sex = ''
      state.phone = ''
      state.loc = ''
      state.role = ''
      state.avatar = ''
      state.status = false
      state.brandId = ''
    }
  },

  actions: {
    //用户信息状态初始化
    UserInfoInit({commit}){
      return new Promise(resolve => {
        commit('CLEAR_USER',false)
        resolve()
      })
    },
    // 用户名登录
    loginById({ commit }, loginInfo) {
      const id = loginInfo.id.trim()
      return new Promise((resolve, reject) => {
        let err=""
        loginById(id, loginInfo.encryptPassword,loginInfo.role).then(data => {
          if(data.code===401)
            err="用户名或密码错误"
          else if(data.code===402)
            err="用户名不存在"
          else if(data.code===400){
            commit('SET_USER',data)
            setToken('token',data.data.token)
            if(loginInfo.radio==='1'){
              setToken('id',id)
              setToken('password',loginInfo.password)
              setToken('role',loginInfo.role)
            }else{
              removeToken('id')
              removeToken('password')
              removeToken('role')
            }
          }
          resolve(err)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    GetUserInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        loginByToken(state.token).then(data => {
          // 由于mockjs 不支持自定义状态码只能这样hack
          console.log(data)
          if (!data) {
            reject('Verification failed, please login again.')
          }
          commit('SET_USER', data)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    Register({ commit }, userInfo) {
      const username = userInfo.id.trim()
      return new Promise((resolve, reject) => {
        register(username, userInfo.password).then(data => {
          commit('SET_USER',data)
          setToken('token',data .token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    //更新用户头像
    updateUserAvatar({commit},avatar){
      return new Promise(resolve => {
        commit('SET_AVATAR',baseUrl+avatar)
        resolve()
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
        logout().then((data) => {
          if(data==='success'){
            commit('CLEAR_USER')
            removeToken('token')
          }
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        logout().then(data=>{
          commit('SET_STATUS',false)
        })
        resolve()
      })
    },

    CheckSession({ commit }) {
      return new Promise(resolve => {
        checkSession().then(data=>{
          commit('SET_STATUS',true)
        })
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
