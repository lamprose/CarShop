import request from '@/utils/request'

export function getUserListPage(data) {
  return request({
    url: '/admin/getUserListPage',
    method: 'get',
    data
  })
}
