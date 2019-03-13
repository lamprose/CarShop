import Mock from 'mockjs'
import userAPI from './user'
import searchAPI from './search'

// 修复在使用 MockJS 情况下，设置 withCredentials = true，且未被拦截的跨域请求丢失 Cookies 的问题
// https://github.com/nuysoft/Mock/issues/300
Mock.XHR.prototype.proxy_send = Mock.XHR.prototype.send
Mock.XHR.prototype.send = function() {
  if (this.custom.xhr) {
    this.custom.xhr.withCredentials = this.withCredentials || false
  }
  this.proxy_send(...arguments)
}

// Mock.setup({
//   timeout: '350-600'
// })

// 登录相关
Mock.mock(/\/user\/login/, 'post', userAPI.loginByUsername)
Mock.mock(/\/user\/logout/, 'post', userAPI.logout)
Mock.mock(/\/user\/register/, 'post', userAPI.register)
Mock.mock(/\/user\/check/, 'post', userAPI.checkedUser)
Mock.mock(/\/user\/info\.*/, 'get', userAPI.getUserInfo)

Mock.mock(/\/search\/getHotSearch/, 'get', searchAPI.getHotSearch)
Mock.mock(/\/search\/searchByText/, 'get', searchAPI.searchByText)

export default Mock
