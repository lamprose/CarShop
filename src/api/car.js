import request from '@/utils/request'

export function getCarInfo(productId) {
  return request({
    url: '/car/get',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      pId:productId
    }
  })
}

export function search(query) {
  return request({
    url: '/product/search',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      query:query
    }
  })
}
