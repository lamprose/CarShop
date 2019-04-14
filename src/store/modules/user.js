import { loginById,loginByToken, logout, getUserInfo,checkSession,changePassword, register} from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import {setSecret,changePasswordBySecret,checkHaveSecret} from "@/api/security";
import {baseUrl} from "@/api";
import {editUser} from "@/api/admin";

const defaultAvatar=baseUrl+'/UserAvatar/default_avatar.png'

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
    secretStatus:false
  },

  mutations: {
    SET_USER:(state,info)=>{
      if(info.role==='normal'){
        state.id=info.data.id
        state.name = info.data.name===null?"User":info.data.name
        state.sex = info.data.sex
        state.loc = info.data.loc
        state.avatar = info.data.avatar===null?defaultAvatar:baseUrl+info.data.avatar
      }
      else if(info.role==='admin' || info.role==='superAdmin'){
        state.id = info.data.shopId
        state.name = info.data.shopName===null?"admin":info.data.shopName
        state.brandId=info.data.brand.brandId
        state.avatar = info.data.brand.logo===null?defaultAvatar:baseUrl+info.data.brand.logo
      }
      state.token = info.data.token
      state.phone = info.data.phone
      state.role = info.role
      state.status = true
    },
    UPDATE_USER:(state,user)=>{
      if(state.role==='normal'){
        state.id=user.id
        state.name = user.name===null?"User":user.name
        state.sex = user.sex
        state.loc = user.loc
        state.avatar = user.avatar===null?defaultAvatar+'':user.avatar
      }
      else if(state.role==='admin'||state.role==='superAdmin'){
        state.id = user.shopId
        state.name = user.shopName===null?"admin":user.shopName
        state.brandId=user.brand.brandId
        state.avatar = user.brand.logo===null?defaultAvatar:user.brand.logo
      }
      state.token = user.token
      state.phone = user.phone
      state.status = true
    },
    UPDATE_USER_INFO:(state,user)=>{
      if(state.role==='normal'){
        state.name = user.name===null?"User":user.name
        state.sex = user.sex
        state.loc = user.loc
      }
      else if(state.role==='admin'||state.role==='superAdmin'){
        state.name = user.shopName===null?"admin":user.shopName
      }
    },
    SET_ID: (state, id) => {
      state.id = id
    },
    SET_SECRET_STATUS:(state,status)=>{
      state.secretStatus=status
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
        commit('CLEAR_USER')
        resolve()
      })
    },
    // 用户名登录
    loginById({ commit }, loginInfo) {
      const id = loginInfo.id.trim()
      return new Promise((resolve, reject) => {
        loginById(id, loginInfo.encryptPassword,loginInfo.role).then(data => {
          if(data.code===401)
            reject("用户名或密码错误")
          else if(data.code===402)
            reject("用户名不存在")
          else if(data.code===400){
            if(data.role==='admin'||data.role==='superAdmin')
              commit('SET_SECRET_STATUS',true)
            else {
              checkHaveSecret(id).then(response=>{
                commit('SET_SECRET_STATUS',response==='success')
              })
            }
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
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    GetUserInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        loginByToken().then(data => {
          // 由于mockjs 不支持自定义状态码只能这样hack
          if (!data) {
            reject('Verification failed, please login again.')
          }
          commit('SET_USER', data)
          if(data.role==='admin'||data.role==='superAdmin')
            commit('SET_SECRET_STATUS',true)
          else {
            checkHaveSecret(id).then(response=>{
              commit('SET_SECRET_STATUS',response==='success')
            })
          }
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
          commit('SET_SECRET_STATUS',false)
          setToken('token',data.data.token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    //更新用户头像
    updateUserAvatar({commit},avatar){
      return new Promise(resolve => {
        commit('SET_AVATAR',avatar)
        resolve()
      })
    },

    //更改密码
    updatePassword({commit},changPwd){
      return new Promise((resolve,reject) => {
        changePassword(changPwd.old,changPwd.new).then(response=>{
          if(response){
            commit('UPDATE_USER',response)
            setToken('token',response.token)
            resolve("修改成功")
          }
          else
            reject("修改失败")
        }).catch(err=>{
          reject(err)
        })
      })
    },

    //修改用户信息
    updateUserInfo({commit},changeUser){
      return new Promise((resolve,reject) => {
        editUser(changeUser).then(response=>{
          if(response==='success'){
            commit('UPDATE_USER_INFO',changeUser)
            resolve('修改成功')
          }
          else
            reject('修改失败，请稍后重试')
        })
      })
    },

    setSecret({commit,state},secret){
      return new Promise((resolve,reject) => {
        setSecret(state.id,secret).then(response=>{
          if(response==='success'){
            resolve('修改成功')
            commit('SET_SECRET_STATUS',true)
          }else
            reject('修改失败，请稍后重试')
        })
      })
    },

    //忘记密码验证密保更改密码并登陆
    updatePasswordBySecret({commit},changPwd){
      return new Promise((resolve,reject) => {
        changePasswordBySecret(changPwd.id,changPwd.password).then(response=>{
          if(response){
            commit('SET_USER',response)
            setToken('token',response.data.token)
            resolve("修改成功")
          }
          else
            reject("修改失败")
        }).catch(err=>{
          reject(err)
        })
      })
    },

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
        commit('CLEAR_USER')
        removeToken('token')
        resolve()
      })
    },

    // 前端 登出
    SessionLogOut({ commit }) {
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
