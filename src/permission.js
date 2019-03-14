import router from './router'
import store from './store'
import { Message } from 'element-ui'
import {getToken} from "./utils/auth";
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css'// progress bar style

NProgress.configure({ showSpinner: false })// NProgress Configuration

router.beforeEach((to, from, next) => {
  NProgress.start() // start progress bar
  if (getToken()) { // determine if there has token
    /* has token*/
    /*console.log("has token")*/
    if (store.getters.roles.length === 0) { // 判断当前用户是否已拉取完user_info信息
      store.dispatch('GetUserInfo').then(res => { // 拉取user_info
        next({ ...to, replace: true })
      }).catch((err) => {
        store.dispatch('FedLogOut').then(() => {
          Message.error(err)
        })
      })
    } else {
      next()
    }
  }else{
    next()
  }
})


router.afterEach(() => {
  NProgress.done() // finish progress bar
})
