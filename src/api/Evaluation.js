import request from '@/utils/request'

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

export function addEvaluation(data) {
  return request({
    url: '/evaluation/addEvaluation',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}

export function editEvaluation(data) {
  return request({
    url: '/evaluation/editEvaluation',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:data
  })
}
