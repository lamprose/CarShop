import request from '@/utils/request'

export function checkCarId(data) {
  return request({
    url: '/cars/check',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      carId:data
    }
  })
}

export function getCarInfo(carId) {
  return request({
    url: '/cars/getCarInfo',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      carId:carId
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
      queryString:query
    }
  })
}

export function getHotSearch() {
  return request({
    url: '/cars/getHotSearch',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
  })
}

export function searchByText(text) {
  return request({
    url: '/cars/searchByText',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      text:text
    }
  })
}

export function queryStringByText(text) {
  return request({
    url: '/cars/queryStringByText',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      text:text
    }
  })
}
