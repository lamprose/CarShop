import Cookies from 'js-cookie'
import {encryptMd5} from '@/utils/encrypt'

/*const TokenKey = 'Admin-Token'*/

export function getToken(TokenKey) {
  return Cookies.get(TokenKey)
}

export function setToken(TokenKey,token) {
  if(TokenKey=='token')
    return Cookies.set(TokenKey, token,{ expires: 0.01 })
  else
    return Cookies.set(TokenKey, token,{expires:3})
}

export function removeToken(TonkenKey) {
  return Cookies.remove(TonkenKey)
}

