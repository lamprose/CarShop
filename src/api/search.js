import request from '@/utils/request'

export function getHotSearch() {
  return request({
    url: '/search/getHotSearch',
    method: 'get'
  })
}
export function searchByText(text) {
  return request({
    url: '/search/searchByText',
    method: 'get',
    params:{text}
  })
}
