import permission from './scroll'

const install = function(Vue) {
  Vue.directive('scroll', scroll)
}

if (window.Vue) {
  window['scroll'] = scroll
  Vue.use(install); // eslint-disable-line
}

scroll.install = install
export default permission
