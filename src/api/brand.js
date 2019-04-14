import request from '@/utils/request'

export function checkBrandId(data) {
  return request({
    url: '/brands/check',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      brandId:data
    }
  })
}
