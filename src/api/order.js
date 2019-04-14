import request from '@/utils/request'

export function getUserOrderListPage(id) {
  return request({
    url: '/order/getUserOrderListPage',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      id:id
    }
  })
}

//用户添加订单
export function addOrder(data) {
  return request({
    url: '/order/addOrder',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function payment(data) {
  return request({
    url: '/order/addOrder',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

//用户改变订单状态，包括支付，退款
export function changeOrderStatus(data) {
  return request({
    url: '/order/changeOrderStatus',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}
