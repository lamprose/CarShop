import request from '@/utils/request'

export function getCarInfo(productId) {
  return request({
    url: '/order/get',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      pId:productId
    }
  })
}
