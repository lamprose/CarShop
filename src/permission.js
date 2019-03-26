import router from './router'
import store from './store'
import { Message } from 'element-ui'
import {getToken} from "./utils/auth";
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css'// progress bar style

NProgress.configure({ showSpinner: false })// NProgress Configuration

const NoLogin=['/home','/search','/register','/forgot','/product']
const LoginNormal=['/home', '/search','/product','/user','/balance','/updateUser']
const LoginAdmin=['/admin','/shop','/updateUser']
const whiteList = ['/home','/401','/404','/shop']//  白名单
const blackList= ['/user','/balance','/admin'] //未登录黑名单

router.beforeEach((to, from, next) => {
  NProgress.start() // start progress bar
  if (getToken('token')) { // determine if there has token
    /* has token*/
    if (store.getters.role=== '') { // 判断当前用户是否已拉取完user_info信息
      store.dispatch('GetUserInfo').then(res => { // 拉取user_info
        next({ ...to, replace: true })
      }).catch((err) => {
        store.dispatch('FedLogOut').then(() => { //拉取用户信息失败则清除token信息
          Message.error(err)
        })
      })
    } else {
      if(whiteList.findIndex(item=>{
        return to.path.indexOf(item)!==-1
      })!==-1)
        next()
      else{
        if(store.getters.role==='admin'||store.getters.role==='superAdmin'){
          if(LoginAdmin.findIndex(item=>{
            return to.path.indexOf(item)!==-1
          })!==-1)
            next()
          else
            next('/401')
        }
        else if(store.getters.role==='normal'){
          if(LoginNormal.findIndex(item=>{
            return to.path.indexOf(item)!==-1
          })!==-1)
            next()
          else
            next('/401')
        }
      }

      /*if(store.getters.role==='normal'&&to.path==='/admin')//普通用户无权限访问管理员页面则重定向到401
        next('/401')
      else
        next()*/
    }
  }else{
    /*has no token*/
    store.dispatch('UserInfoInit').then(res => {
      if(whiteList.findIndex(item=>{
        return to.path.indexOf(item)!==-1
      })!==-1)
        next()
      else if (NoLogin.findIndex(item=>{
        return to.path.indexOf(item)!==-1
      })!==-1) { // 在免登录白名单，直接进入
        next()
      }else if(blackList.findIndex(item=>{
        return to.path.indexOf(item)!==-1
      })!==-1){
        next(`/401`)//无权限访问则重定向到401
      }else {
        next(`/404`) // 否则全部重定向到404
        NProgress.done() // if current page is login will not trigger afterEach hook, so manually handle it
      }
    })
  }
})


router.afterEach(() => {
  NProgress.done() // finish progress bar
})
