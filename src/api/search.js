import request from '@/utils/request'

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
    data:{text:text}
  })
}
