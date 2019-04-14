import request from '@/utils/request'
import {encryptMd5} from "@/utils/encrypt";

export function getUserListPage(data) {
  return request({
    url: '/admin/getUserListPage',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function editUser(data) {
  return request({
    url: '/admin/editUser',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function removeUser(data) {
  return request({
    url: '/admin/removeUser',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function addUser(data) {
  return request({
    url: '/admin/addUser',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function getBrandListPage(data) {
  return request({
    url: '/brands/getBrandListPage',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function editBrand(data) {
  return request({
    url: '/brands/editBrand',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function removeBrand(data) {
  return request({
    url: '/brands/removeBrand',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function addBrand(data) {
  return request({
    url: '/brands/addBrand',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function getShopListPage(data) {
  return request({
    url: '/admin/getShopListPage',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function removeShop(data) {
  return request({
    url: '/admin/removeShop',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function addShop(data) {
  data['password']=encryptMd5(data['shopId'])
  return request({
    url: '/admin/addShop',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function getCarListPage(data) {
  return request({
    url: '/cars/getCarListPage',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function editCar(data) {
  return request({
    url: '/cars/editCar',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function removeCar(data) {
  return request({
    url: '/cars/removeCar',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function addCar(data) {

  return request({
    url: '/cars/addCar',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function getOrderListPage(data) {
  return request({
    url: '/order/getOrderListPage',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function editOrder(data) {
  return request({
    url: '/order/editOrder',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      orderId:data.orderId,
      orderStatus:data.orderStatus
    }
  })
}

export function removeOrder(data) {
  return request({
    url: '/order/removeOrder',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

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

export function getEvaluationListPage(data) {
  return request({
    url: '/evaluation/getEvaluationListPage',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function removeEvaluation(data) {
  return request({
    url: '/evaluation/removeEvaluation',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}
