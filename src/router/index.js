import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export const constantRouterMap = [
  {
    path: '/',
    components: {
      'default':resolve => require(["@/components/Layout/MainLayout"], resolve),
    },
    children:[
      {
        path:'/',
        redirect: "/home",
        components: {
          'topBar': resolve => require(["@/views/Main/TopBar.vue"], resolve),
          'bottomBar': resolve => require(["@/views/Main/BottomBar.vue"], resolve),
          'mainBox': resolve => require(["@/views/Main/MainBox.vue"], resolve),
          'userBlankBox': resolve => require(["@/views/Main/User.vue"], resolve),
          'shopCartBlankBox': resolve => require(["@/views/Main/ShopCart.vue"], resolve),
          'toolBlankBox': resolve => require(["@/views/Main/Tool.vue"], resolve),
        },
        children: [
          {
            path:'/home',
            name:'Home',
            component:resolve => require(["@/views/Home.vue"], resolve),
          },
          {
            path:'/search',
            name:'Search',
            component: resolve => require(["@/views/SearchResult.vue"], resolve),
          },
          {
            path:'/product/:id',
            name:'Goods',
            components:{
              'default':resolve => require(["@/views/ProductInfo.vue"], resolve),
            }
          },
          {
            path:'/shop/:id',
            name:'Shop',
            components:{
              'default':resolve => require(["@/views/Shop.vue"], resolve),
            }
          },
          {
            path:'/forgot',
            name:'Forgot',
            components:{
              'default':resolve => require(["@/views/Forgot.vue"], resolve),
            }
          },
          {
            path:'/register',
            name:'Register',
            components:{
              'default':resolve => require(["@/views/Register.vue"], resolve),
            }
          },
          {
            path:'/user/:id',
            name:'User',
            components:{
              'default':resolve => require(["@/views/User.vue"], resolve),
            }
          }
        ]
      }
    ]
  },
  {
    path:'/',
    component: resolve => require(["@/components/Layout/AdminLayout"], resolve),
    children:[
      {
        path: '/admin',
        name:'admin',
        components: {
          'adminHeader': resolve => require(["@/views/Admin/AdminHeader.vue"], resolve),
          'adminAside': resolve => require(["@/views/Admin/AdminAside.vue"], resolve),
          'adminMain': resolve => require(["@/views/Admin/AdminMain.vue"], resolve),
        },
      }
    ]
  },
  {
    path: '/401',
    name:'401',
    component: resolve => require(["@/views/ErrorPage/401.vue"], resolve),
  },
  {
    path:'*',
    name:'404',
    component: resolve => require(["@/views/ErrorPage/404.vue"], resolve),
    meta:{
      title: '404 not found'
    }
  }
]

export default new Router({
  mode:'history',
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
