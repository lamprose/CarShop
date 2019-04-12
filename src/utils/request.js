import axios from 'axios'
import { Message,MessageBox } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'
import { baseUrl } from '@/api'

// 创建axios实例
const service = axios.create({
  baseURL: baseUrl, // api 的 base_url
  timeout: 5000 // request timeout
})

// request拦截器
service.interceptors.request.use(
  config => {
    // 发送请求时添加配置信息
    if(config.headers['Content-Type']==='application/json')
      config.data = JSON.stringify(config.data);
    if (getToken('token')&&typeof(getToken('token'))!=="undefined") {
      // 让每个请求携带token-- ['X-Token']为自定义key 请根据实际情况自行修改
      config.headers['Token'] = getToken('token')
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// response拦截器
service.interceptors.response.use(
  /**
   * 下面的注释为通过在response里，自定义code来标示请求状态
   * 当code返回如下情况则说明权限有问题，登出并返回到登录页
   * 如想通过 xmlhttprequest 来状态码标识 逻辑可写在下面error中
   * 以下代码均为样例，请结合自生需求加以修改，若不需要，则可删除
   */
  response => {
    const res = response.data
    //404:非法的token; 403:其他客户端登录了;  50014:Token 过期了;
    if (res.code === 404 || res.code === 403 || res.code === 402) {
       //请自行在引入 MessageBox
       //import { Message, MessageBox } from 'element-ui'
      MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
        confirmButtonText: '重新登录',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        store.dispatch('FedLogOut').then(() => {
          location.reload()  //为了重新实例化vue-router对象 避免bug
        })
      })
      return Promise.reject('error')
    } else if(res.code===400) {
      return res.datas
    }
   },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
