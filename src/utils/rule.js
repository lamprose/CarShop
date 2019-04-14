import {checkUser} from '@/api/user'
import {checkBrandId} from "@/api/brand";
import {checkCarId} from "@/api/car";

let checkId= (rule, value, callback) =>{
  let rex=/^[a-zA-Z][a-zA-Z0-9]{5,15}$/
  if(value==='')
    return callback(new Error('请输入用户名'))
  else if(!rex.test(value)){
    return callback(new Error('用户名必须以字母开头且为6~20位的字母数字的字符串'));
  }else{
    //获取是否存在用户名
    checkUser(value).then((response)=> {
        if(response!=="success"){
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

let checkBrandID=(rule,value,callback)=>{
  const reg= /^[0-9A-Za-z]+$/
  if(value==='')
    return callback(new Error("请输入商户ID"))
  else if(!reg.test(value))
    return callback(new Error("商户ID不合法，请输入英文ID"))
  else{
    checkBrandId(value).then(response=>{
      if(response==='fail')
        return callback(new Error(value+"已存在，请重新输入"))
      else
        return callback()
    })
  }
}

let checkCarID=(rule,value,callback)=>{
  const reg= /^[0-9A-Za-z]+$/
  if(value==='')
    return callback(new Error("请输入汽车ID"))
  else if(!reg.test(value))
    return callback(new Error("商户汽车不合法，请输入英文数字混合ID"))
  else{
    checkCarId(value).then(response=>{
      if(response==='fail')
        return callback(new Error(value+"已存在，请重新输入"))
      else
        return callback()
    })
  }
}

let checkOilWear=(rule,value,callback)=>{
  const reg= /^[0-9]{0,2}(\.[0-9]{1,2})?$/
  if(!reg.test(value))
    return callback(new Error("请输入正确的格式(输入值小于100且保留两位小数)"))
  else
    return callback()
}

let checkMaxSpeed=(rule,value,callback)=>{
  const reg= /^[0-9]{0,4}$/
  if(!reg.test(value))
    return callback(new Error("请输入正确的格式(输入值小于100000)"))
  else
    return callback()
}

let checkPrice=(rule,value,callback)=>{
  const reg= /^[0-9]{1,8}(\.[0-9]{1,2})?$/
  if(!reg.test(value))
    return callback(new Error("请输入正确的格式(输入值小于1000000000且保留两位小数)"))
  else
    return callback()
}

let checkQuestion=(rule,value,callback)=>{
  if(value===null)
    return callback(new Error("请选择密保问题"))
  else
    return callback()
}

let checkAnswer=(rule,value,callback)=>{
  if(value==='')
    return callback(new Error("请填写密保答案"))
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
    {validator:checkPhone,trigger:['change']}
  ],
  BrandID:[
    {validator:checkBrandID,trigger:['blur','change']}
  ],
  CarID:[
    {validator:checkCarID,trigger:['blur','change']}
  ],
  OilWear:[
    {validator:checkOilWear,trigger:['blur','change']}
  ],
  MaxSpeed:[
    {validator:checkMaxSpeed,trigger:['blur','change']}
  ],
  Price:[
    {validator:checkPrice,trigger:['blur','change']}
  ],
  Question:[
    {validator:checkQuestion,trigger:['blur','change']}
  ],
  Answer:[
    {validator:checkAnswer,trigger:['blur','change']}
  ]
}
