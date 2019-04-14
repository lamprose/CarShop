import request from '@/utils/request'

export function getShopInfo(brandId) {
  return request({
    url: '/admin/getShopInfo',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      brandId:brandId
    }
  })
}

export function getEachShopTopNumberCars() {
  return request({
    url: '/admin/getEachShopTopNumberCars',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:[
      {brandId:'Volkswagen'},{brandId:'Buick'},{brandId:'Chevrolet'},{brandId:'BaoJun'},{brandId:'SKODA'},
      {brandId:'ROEWE'},{brandId:'MG'},{brandId:'Cadillac'},{brandId:'SGMW'},{brandId:'MAXUS'}
      ]
  })
}
