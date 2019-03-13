import {checkUser} from '@/api/user'

let checkId= (rule, value, callback) =>{
  let rex=/^[a-zA-Z][a-zA-Z0-9]{5,15}$/
  if(!rex.test(value)){
    return callback(new Error('用户名必须以字母开头且为6~20位的字母数字的字符串'));
  }else{
    //console.log(data);
    //TODO:获取是否存在用户名
    checkUser(value).then((response)=> {
        /*console.log(response.data)*/
        if(response.data!="success"){
          return callback(new Error('该用户名已被注册，请重新输入'));
        }else
          return callback();
      })
  }
}

let checkPassword=(rule, value, callback) =>  {
  const reg= /^[0-9A-Za-z]{6,}$/
  if(!value)
    return callback(new Error('请输入密码'))
  else if(!reg.test(value))
    return callback(new Error('密码只能是数字、大小写字母混合的至少6位字符串'));
  else
    return callback();
}

let checkRePassword=(rule, value, callback) => {
  let password=document.getElementById("password").value
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

export default {
  ID:[
    {required:true,message:'请输入用户名',trigger:'blur'},
    {required:true,validator:checkId,trigger:['blur','change']}
  ],
  Password:[
    {required:true,validator:checkPassword,trigger:['blur','change']},
    {required:true,message:'请输入密码',trigger:'blur'},
  ],
  RePassword:[
    {required:true,validator:checkRePassword,trigger:['blur','change']},
    {required:true,message:'请确认密码',trigger:'blur'},
  ],
  Identify:[
    {required:true,validator:checkIdentify,trigger:['blur','change']},
    {required:true,message:'请输入验证码',trigger:'blur'},
  ]
}
