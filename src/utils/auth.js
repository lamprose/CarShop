import Cookies from 'js-cookie'
import {encryptMd5} from '@/utils/encrypt'

const TokenKey = 'Admin-Token'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token,{ expires: 0.01 })
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

