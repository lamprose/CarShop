import Mock from 'mockjs'
import userAPI from './user'
import searchAPI from './search'
import adminAPI from './admin'

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
Mock.mock(/\/user\/login/, 'post', userAPI.loginById)
Mock.mock(/\/user\/logout/, 'post', userAPI.logout)
Mock.mock(/\/user\/register/, 'post', userAPI.register)
Mock.mock(/\/user\/check/, 'post', userAPI.checkedUser)
Mock.mock(/\/user\/info\.*/, 'get', userAPI.getUserInfo)

Mock.mock(/\/search\/getHotSearch/, 'get', searchAPI.getHotSearch)
Mock.mock(/\/search\/searchByText/, 'get', searchAPI.searchByText)

Mock.mock(/\/admin\/getUserListPage/, 'get', adminAPI.getUserListPage)
Mock.mock(/\/admin\/editUser/, 'get', adminAPI.returnTrue)
Mock.mock(/\/admin\/removeUser/, 'get', adminAPI.returnTrue)
Mock.mock(/\/admin\/addUser/, 'get', adminAPI.returnTrue)
Mock.mock(/\/admin\/getShopListPage/, 'get', adminAPI.getShopListPage)
Mock.mock(/\/admin\/editShop/, 'get', adminAPI.returnTrue)
Mock.mock(/\/admin\/removeShop/, 'get', adminAPI.returnTrue)
Mock.mock(/\/admin\/addShop/, 'get', adminAPI.returnTrue)
export default Mock
