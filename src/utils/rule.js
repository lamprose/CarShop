import {checkUser} from '@/api/user'

let checkId= (rule, value, callback) =>{
  let rex=/^[a-zA-Z][a-zA-Z0-9]{5,15}$/
  if(value==='')
    return callback(new Error('请输入用户名'))
  else if(!rex.test(value)){
    return callback(new Error('用户名必须以字母开头且为6~20位的字母数字的字符串'));
  }else{
    //TODO:获取是否存在用户名
    checkUser(value).then((data)=> {
        /*console.log(response.data)*/
        if(data!=="success"){
          return callback(new Error('该用户名已被注册，请重新输入'));
        }else
          return callback();
      })
  }
}

let checkPassword=(rule, value, callback) =>  {
  const reg= /^[0-9A-Za-z]{6,}$/
  if(value==='')
    return callback(new Error('请输入密码'))
  else if(!reg.test(value))
    return callback(new Error('密码只能是数字、大小写字母混合的至少6位字符串'));
  else
    return callback();
}

let checkRePassword=(rule, value, callback) => {
  let password=document.getElementById("password").value
  if(value==='')
    return callback(new Error('请确认密码'))
  if(value!==password){
    return callback(new Error('密码不一致'));
  }else
    return callback();
}

let checkIdentify=(rule, value, callback) => {
  let code=document.getElementById("identifyCode").innerHTML
  if(value!==code){
    return callback(new Error('验证码错误，请重试'));
  }else
    return callback();
}

let checkPhone=(rule, value, callback)=>{
  let rex=/^1[3|4|5|8][0-9]\d{4,8}$/
  if(!value)
    return callback()
  if(!rex.test(value))
    return callback(new Error('电话号码格式错误'));
  else
    return callback()
}

export default {
  ID:[
    {validator:checkId,trigger:['blur','change']}
  ],
  Password:[
    {validator:checkPassword,trigger:['blur','change']},
  ],
  RePassword:[
    {validator:checkRePassword,trigger:['blur','change']},
  ],
  Identify:[
    {required:true,message:'请输入验证码',trigger:'blur'},
    {validator:checkIdentify,trigger:['blur','change']},
  ],
  Phone:[
    {validator:checkPhone,trigger:['blur','change']}
  ]
}
