import anchor from "./anchor";
import Vue from "vue"
Vue.directive('anchor',anchor)
const install = function(Vue) {
  Vue.directive('anchor', anchor)
}

if (window.Vue) {
  window['anchor'] = anchor
  Vue.use(install); // eslint-disable-line
}

anchor.install = install
export default anchor
