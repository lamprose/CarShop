import Mock from 'mockjs'
import { param2Obj } from '@/utils'
import {searchMap,hotSearch} from "./data/search";

export default {
  getHotSearch:()=>({
    data: hotSearch
  }),
  searchByText:config=>{
    const { text } = param2Obj(config.url)
    if (searchMap[text]) {
      return searchMap[text]
    } else {
      return {
        code:400,
        data:false
      }
    }
  }
}
