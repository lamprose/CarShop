import request from '@/utils/request'

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
    url: '/admin/getBrandListPage',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function editBrand(data) {
  return request({
    url: '/admin/editBrand',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function removeBrand(data) {
  return request({
    url: '/admin/removeBrand',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function addBrand(data) {
  return request({
    url: '/admin/addBrand',
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
    url: '/admin/editUser',
    method: 'get',
    data
  })
}

export function removeCar(data) {
  return request({
    url: '/admin/removeUser',
    method: 'get',
    data
  })
}

export function addCar(data) {
  return request({
    url: '/admin/addUser',
    method: 'get',
    data
  })
}

export function getOrderListPage(data) {
  return request({
    url: '/admin/getOrderListPage',
    method: 'get',
    data
  })
}

export function editOrder(data) {
  return request({
    url: '/admin/editUser',
    method: 'get',
    data
  })
}

export function removeOrder(data) {
  return request({
    url: '/admin/removeUser',
    method: 'get',
    data
  })
}

export function addOrder(data) {
  return request({
    url: '/admin/addUser',
    method: 'get',
    data
  })
}
