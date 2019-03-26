import { param2Obj } from '@/utils'
import {userMap} from "./data/user";


export default {
  loginById: config => {
    const { id } = JSON.parse(config.body)
    return {
      data:userMap[id],
      code:400
    }

  },
  getUserInfo: config => {
    const { token } = param2Obj(config.url)
    if (userMap[token]) {
      return {
        data:userMap[token],
        code:400
      }
    } else {
      return false
    }
  },
  register: config => {
    const { id } = JSON.parse(config.body)
    return {
      data:userMap[id],
      code:400
    }
  },
  checkedUser:config=>{
    const { username } = JSON.parse(config.body)
    return {
      dara:'success',
      code:400
    };
  },
  logout:config=>{
    return {
      dara:'success',
      code:400
    };
  }
}
