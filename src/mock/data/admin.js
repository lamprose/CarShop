import Mock from 'mockjs';

const LoginUsers = [
  {
    id: 1,
    username: 'admin',
    password: '123456',
    avatar: 'https://raw.githubusercontent.com/taylorchen709/markdown-images/master/vueadmin/user.png',
    name: '张某某'
  }
];

const Users = [];
const Shops=[];
for (let i = 0; i < 86; i++) {
  Users.push(Mock.mock({
    id: Mock.Random.guid(),
    name: Mock.Random.cname(),
    addr: Mock.mock('@county(true)'),
    'age|18-60': 1,
    birth: Mock.Random.date(),
    avatar:'https://raw.githubusercontent.com/taylorchen709/markdown-images/master/vueadmin/user.png',
    sex: Mock.Random.integer(0, 1)
  }));
}


for (let i = 0; i < 86; i++) {
  Shops.push(Mock.mock({
    shopId: Mock.Random.guid(),
    shopName: Mock.Random.cname(),
    brandId: Mock.mock('@county(true)'),
    brandName: Mock.Random.date(),
    display:'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    logo:'https://raw.githubusercontent.com/taylorchen709/markdown-images/master/vueadmin/user.png',
    phone: Mock.Random.integer(0, 1)
  }));
}
export { LoginUsers, Users , Shops }
