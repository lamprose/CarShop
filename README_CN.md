[For English User](./README.md)

## 前端
前端是采用VUE框架与ElemeUI写的

## 前序准备
你需要在本地安装 node 和 git。本项目技术栈基于 ES2015+、vue、vuex、vue-router 、axios 和 element-ui,提前了解和学习这些知识会对使用本项目有很大的帮助。

## 目录结构
本项目差不多算一个完整的开发框架，提供了涵盖中后台开发的各类功能，下面是整个项目的目录结构
```text
├── build                      // 构建相关
├── config                     // 配置相关
├── src                        // 源代码
│   ├── api                    // 所有请求
│   ├── assets                 // 主题 字体等静态资源
│   ├── components             // 全局公用组件
│   ├── directive              // 全局指令
│   ├── filters                // 全局 filter
│   ├── icons                  // 项目所有 svg icons
│   ├── mock                   // 项目mock 模拟数据
│   ├── router                 // 路由
│   ├── store                  // 全局 store管理
│   ├── styles                 // 全局样式
│   ├── utils                  // 全局公用方法
│   ├── views                  // views 所有页面
│   ├── App.vue                // 入口页面
│   ├── main.js                // 入口文件 加载组件 初始化等
│   └── permission.js          // 网页入口初始化路由
├── static                     // 第三方不打包资源
├── .babelrc                   // babel-loader 配置
├── .eslintrc.js               // eslint 配置项
├── .gitignore                 // git 忽略项
├── .travis.yml                // 自动化CI配置
├── favicon.ico                // favicon图标
├── index.html                 // html模板
└── package.json               // package.json
```
## 开发
```text
# 克隆项目前端
git clone -b browser https://github.com/lamprose/CarShop.git

# 安装依赖
npm install

# 修改项目配置文件./src/config/index.js后端IP地址
serverIp:'localhost'  //将localhost修改为对应IP地址，如192.168.0.1

# 运行
npm run dev
```
浏览器访问[http://localhost:8081](http://localhost:8081).

我在开发过程遇到的一些问题以及解决方法，希望对你有所帮助：

- [前后端跨域问题](https://lamprose.github.io/development/vue-cros/)
- [前端Token生成](https://lamprose.github.io/development/vue-token/)

## 截图

### 主页

![home-1](./screenshots/home-1.png)
![home-2](./screenshots/home-2.png)
![home-3](./screenshots/home-3.png)

### 搜索

![search-1](./screenshots/search-1.png)
![search-2](./screenshots/search-2.png)

### 登录

![login](./screenshots/login.png)

### 注册

![register](./screenshots/register.png)

### 在线聊天

![chat-1](./screenshots/chat-1.png)
![chat-2](./screenshots/chat-2.png)

### 购物车

![shopping-cart](./screenshots/shopping-cart.png)

### 我的订单

![my-order](./screenshots/my-order.png)

### 我的信息

![my-info](./screenshots/my-info.png)

### 店铺

![shop](./screenshots/shop.png)

### 汽车信息

![car-1](./screenshots/car-1.png)
![car-2](./screenshots/car-2.png)

### 订单信息

![order](./screenshots/order.png)

### 管理员页面

![admin-1](./screenshots/admin-1.png)
![admin-2](./screenshots/admin-2.png)
![admin-1](./screenshots/admin-3.png)

## 参考资料

- Vue:[https://cn.vuejs.org](https://cn.vuejs.org/)
- Vue-router:[https://router.vuejs.org](https://router.vuejs.org/)
- Vuex:[https://vuex.vuejs.org](https://vuex.vuejs.org/)
- Element-UI:[https://element.eleme.cn](https://element.eleme.cn/)
- vue-element-admin:[https://panjiachen.github.io/vue-element-admin-site](https://panjiachen.github.io/vue-element-admin-site/)
- git：[http://www.liaoxuefeng.com](http://www.liaoxuefeng.com/)

## 许可证
[MIT](./LICENSE)  
Copyright (c) 2017-present lamprose
