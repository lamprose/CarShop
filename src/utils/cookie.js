import Crypto from 'crypto'
import store from '../store/vuex'
//设置cookie方法
export function setCookie(id, password ,radio, exdays) {
  let hash = Crypto.createHash('md5');
  hash.update(password)
  let text = hash.digest('hex');//使用CryptoJS方法加密
  console.log(text);
  let d = new Date();
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  let expires = ";expires=" + d.toUTCString();
  if(radio!="")
    window.document.cookie = "remember" + "="+ radio + expires+"; path=/";
  //字符串拼接存入cookie
  window.document.cookie = "id" + "=" + id  + expires+"; path=/";
  window.document.cookie = "password" + "=" + text + expires+"; path=/";
  store.commit("setRemember",radio=="true")
}
//读取cookie
export function getCookie() {
  if (document.cookie.length > 0) {
    let arr = document.cookie.split('; '); //这里显示的格式需要切割一下自己可输出看下
    for (let i = 0; i < arr.length; i++) {
      let arr2 = arr[i].split('='); //再次切割
      //这里会切割出以id为第0项的数组、以password为第0项的数组，判断查找相对应的值
      if(arr2[0]=='remember'){
        arr2[1]=="true"?store.commit("setRemember",true):store.commit("setRemember",false);
      } else if (arr2[0] == 'id') {
        store.commit("setUserId",arr2[1]);
        /*this.ruleForm.mobile = arr2[1]; //拿到账号*/
      } else if (arr2[0] == 'password') {
        /*//拿到拿到加密后的密码arr2[1]*/
        store.commit("setUserPassword",arr2[1])
      }
    }
    console.log("20"+store.state.remember)
  }
}
//清除cookie
export function clearCookie() {
  setCookie("","","true",0) //账号密码置空，天数置0
}
