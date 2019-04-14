export const numToSex= {
  read:function (value) {
    return value==='0'?"男":value==='1'?"女":"未知"
  },
  write:function (value) {
    return value==='男'?"0":value==='女'?"1":"-1"
  }
}

export function OrderStatus(value){
  switch (value) {
    case 'unpaid':
      return '未支付'
    case 'paid':
      return '已支付'
    case 'returning':
      return '退货中...'
    case 'returned':
      return '退货成功'
    case 'delivering':
      return '已发货'
    case 'delivered':
      return '已到货'
    case 'signed':
      return '已收货'
    case 'evaluated':
      return '已评价'
    default:
      return value
  }
}

export function StringToTime(value){
  let pattern = /(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})/;
  return value.replace(pattern, '$1/$2/$3 $4:$5:$6');
}
