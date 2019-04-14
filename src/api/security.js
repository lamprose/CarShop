import request from '@/utils/request'

export function checkHaveSecret(id) {
  return request({
    url: '/security/checkHaveSecret',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      id:id,
    }
  })
}

export function checkCorrectSecret(data) {
  return request({
    url: '/security/checkCorrectSecret',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      id:data.id,
      question1:data.secret[0].question,
      answer1:data.secret[0].answer,
      question2:data.secret[1].question,
      answer2:data.secret[1].answer,
      question3:data.secret[2].question,
      answer3:data.secret[2].answer,
    }
  })
}

export function changePasswordBySecret(id,newPassword) {
  return request({
    url: '/security/changePassword',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      id:id,
      newPassword:newPassword
    }
  })
}

export function setSecret(id,secret) {
  return request({
    url: '/security/setSecret',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data:{
      id:id,
      question1:secret[0].question,
      answer1:secret[0].answer,
      question2:secret[1].question,
      answer2:secret[1].answer,
      question3:secret[2].question,
      answer3:secret[2].answer,
    }
  })
}
