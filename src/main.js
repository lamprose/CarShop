// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import elementUi from 'element-ui'
import VueAreaLinkage from 'vue-area-linkage'
import Cookies from 'js-cookie'
import store from './store'//
import anchor from './directive/anchor'
import permission from './directive/permission'
import scroll from './directive/scroll'
import "animate.css/animate.min.css";

/*import './mock' // simulation data*/
import './permission' //
import './icons' // icon


import 'element-ui/lib/theme-chalk/index.css';
import 'vue-area-linkage/dist/index.css'
import './styles/style.css';
import 'font-awesome/css/font-awesome.css';

import * as filters from './filters' // global filters

import {getToken} from "./utils/auth";

Vue.config.productionTip = false
Vue.directive(anchor)
Vue.use(permission)
Vue.use(elementUi, {
  size: Cookies.get('size') || 'medium', // set element-ui default size
})
Vue.use(VueAreaLinkage)

// register global utility filters.
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  /*components: { App },
  template: '<App/>'*/
  render: h => h(App),
  mounted(){
    if(getToken('token'))
      store.dispatch("CheckSession")
    window.addEventListener("beforeunload",e=>{
      if(getToken('token'))
        store.dispatch("FedLogOut")
    })
  }
})
