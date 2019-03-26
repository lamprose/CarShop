import request from '@/utils/request'
import axios from 'axios'

export function loginById(id, password,role) {
  return request({
    url: '/user/login',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data: {
      id:id,
      password:password,
      role:role
    }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}

export function getUserInfo(token) {
  return request({
    url: '/user/getInfo',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      token:token
    }
  })
}

export function checkUser(id) {
  return request({
    url: '/user/check',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      id:id,
    }
  })
}

export function register(username, password) {
  const data = {
    id:username,
    password:password
  }
  return request({
    url: '/user/register',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data
  })
}

export function avatarUpload(params,id) {
  let formData=new FormData //使用formdata传输头像
  formData.append("picture",params.file)
  formData.append("id",id)
  return request({
    url:'/user/avatar',
    method:'post',
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    processData:false,
    contentType:false,
    data:formData
  })
}

export function checkHaveSecret(id) {
  return request({
    url: '/user/checkHaveSecret',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      id:id,
    }
  })
}

export function checkCorrectSecret(id,option,secret) {
  return request({
    url: '/user/checkCorrectSecret',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      id:id,
      option:option,
      secret:secret
    }
  })
}

export function checkSession() {
  return request({
    url: '/user/checkSession',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
  })
}
