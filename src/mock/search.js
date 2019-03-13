import Mock from 'mockjs'
import { param2Obj } from '@/utils'

const List = []
const count = 100
const hotSearch=[]

const searchMap = {
  "大众": [
    {text:"大众1"},{text:"大众2"},{text:"大众3"},{text:"大众4"},{text:"大众5"},{text:"大众6"}
  ],
  "比亚迪": [
    {text:"比亚迪1"},{text:"比亚迪2"},{text:"比亚迪3"},{text:"比亚迪4"},{text:"比亚迪5"},{text:"比亚迪6"}
  ]

}

const baseContent = '<p>我是测试数据我是测试数据</p><p><img src="https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943"></p>'
const image_uri = 'https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3'
for (let i = 0; i < 5; i++) {
  hotSearch.push(Mock.mock({
    text:'数据'+i
  }))
}
for (let i = 0; i < count; i++) {
  List.push(Mock.mock({
    id: '@increment',
    timestamp: +Mock.Random.date('T'),
    author: '@first',
    reviewer: '@first',
    title: '@title(5, 10)',
    content_short: '我是测试数据',
    content: baseContent,
    forecast: '@float(0, 100, 2, 2)',
    importance: '@integer(1, 3)',
    'type|1': ['CN', 'US', 'JP', 'EU'],
    'status|1': ['published', 'draft', 'deleted'],
    display_time: '@datetime',
    comment_disabled: true,
    pageviews: '@integer(300, 5000)',
    image_uri,
    platforms: ['a-platform']
  }))
}

export default {
  getHotSearch:()=>({
    data: hotSearch
  }),
  searchByText:config=>{
    const { text } = param2Obj(config.url)
    if (searchMap[text]) {
      return searchMap[text]
    } else {
      return false
    }
  }
}
