function addUser(){
  let User={
    name: '',
    sex: -1,
    age: 0,
    birth: '',
    addr: ''
  }
  return User
}
function addShop(){
  let Shop={
    shopId:'',
    shopName:'',
    phone:'',
    brandId: ''
  }
  return Shop
}

function addCar(){
  let Car={
    carName:'',
    price:'',
    brandId: '',
    carSize:'',
    engine:'',
    level:'',
    structure:'',
    assurance:'',
    oilWear:'',
    manufacturer:'',
    gearbox:'',
    maxSpeed:''
  }
  return Car
}
export {addUser,addShop,addCar}
