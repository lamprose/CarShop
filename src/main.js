// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router.js'
import elementUi from 'element-ui'
import axios from 'axios'
import 'element-ui/lib/theme-chalk/index.css';
import './style.css';
import 'font-awesome/css/font-awesome.css';

Vue.config.productionTip = false
Vue.prototype.$ajax = axios

Vue.use(elementUi)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  /*components: { App },
  template: '<App/>'*/
  render: h => h(App)
})
