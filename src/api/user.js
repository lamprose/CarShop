import request from '@/utils/request'

export function loginByUsername(username, password) {
  const data = {
    username,
    password
  }
  return request({
    url: '/user/login',
    method: 'post',
    data
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
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function checkUser(username) {
  const data = {
    username,
  }
  return request({
    url: '/user/check',
    method: 'post',
    data
  })
}

export function register(username, password) {
  const data = {
    username,
    password
  }
  return request({
    url: '/user/register',
    method: 'post',
    data
  })
}
