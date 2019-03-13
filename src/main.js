// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import elementUi from 'element-ui'
import axios from 'axios'
import Cookies from 'js-cookie'
import store from './store'
import './mock' // simulation data
import './icons' // icon

import './permission'
import 'element-ui/lib/theme-chalk/index.css';
import './styles/style.css';
import 'font-awesome/css/font-awesome.css';

Vue.config.productionTip = false
Vue.prototype.$ajax = axios

Vue.use(elementUi)
Vue.use(elementUi, {
  size: Cookies.get('size') || 'medium', // set element-ui default size
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  /*components: { App },
  template: '<App/>'*/
  render: h => h(App)
})
