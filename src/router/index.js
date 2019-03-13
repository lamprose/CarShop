import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export const constantRouterMap = [
  {
    path: '/',
    components: {
      'topBar': resolve => require(["@/components/TopBar.vue"], resolve),
      'asideBar': resolve => require(["@/components/AsideBar.vue"], resolve),
      'bottomBar': resolve => require(["@/components/BottomBar.vue"], resolve),
      'mainBox': resolve => require(["@/components/MainBox.vue"], resolve),
      'userBlankBox': resolve => require(["@/components/User.vue"], resolve),
      'shopCartBlankBox': resolve => require(["@/components/ShopCart.vue"], resolve),
      'toolBlankBox': resolve => require(["@/components/Tool.vue"], resolve),
    },
    children:[
      {
        path:'/home',
        name:'Home',
        component:resolve => require(["@/views/Home.vue"], resolve),
      },
      {
        path:'/search',
        name:'search',
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
  },
  {
    path: '/admin',
    name:'admin',
    component: resolve => require(["@/views/admin"], resolve),
  },
  {
    path: '/401',
    name:'401',
    component: resolve => require(["@/views/errorPage/401.vue"], resolve),
  },
  {
    path:'/404',
    name:'404',
    component: resolve => require(["@/views/errorPage/404.vue"], resolve),
    meta:{
      title: '404 not found'
    }
  },
  {
    path: "*", // 此处需特别注意置于最底部
    redirect: "/404"
  }
]

export default new Router({
  mode:'history',
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

export const adminRouterMap = [
  {
    path: '/admin',
    name:'admin',
    component: resolve => require(["@/views/admin"], resolve),
  }
]
export const userRouterMap = [
  {
    path: '/admin',
    name:'admin',
    component: resolve => require(["@/views/errorPage/401.vue"], resolve),
  }
]
export const defalutRouterMap = [
  {
    path: "*", // 此处需特别注意置于最底部
    redirect: "/404"
  }
]
