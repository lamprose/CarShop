export const numToSex= {
  read:function (value) {
    return value==='0'?"男":value==='1'?"女":"未知"
  },
  write:function (value) {
    return value==='男'?"0":value==='女'?"1":"-1"
  }
}
