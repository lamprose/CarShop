import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export const constantRouterMap = [
  {
    path: '/',
    components: {
      'default':resolve => require(["@/components/layout/MainLayout"], resolve),
    },
    children:[
      {
        path:'/',
        redirect: "/home",
        components: {
          'topBar': resolve => require(["@/views/main/topBar"], resolve),
          'bottomBar': resolve => require(["@/views/main/bottomBar"], resolve),
          'mainBox': resolve => require(["@/views/main/mainBox"], resolve),
          'userBlankBox': resolve => require(["@/views/main/aside/user"], resolve),
          'shopCartBlankBox': resolve => require(["@/views/main/aside/shopCart"], resolve),
          'toolBlankBox': resolve => require(["@/views/main/aside/tool"], resolve),
        },
        children: [
          {
            path:'/home',
            name:'Home',
            component:resolve => require(["@/views/home"], resolve),
          },
          {
            path:'/search',
            name:'Search',
            component: resolve => require(["@/views/searchResult"], resolve),
          },
          {
            path:'/product/:id',
            name:'Goods',
            components:{
              'default':resolve => require(["@/views/productInfo"], resolve),
            }
          },
          {
            path:'/shop/:id',
            name:'Shop',
            components:{
              'default':resolve => require(["@/views/shop"], resolve),
            }
          },
          {
            path:'/forgot',
            name:'Forgot',
            components:{
              'default':resolve => require(["@/views/forgot"], resolve),
            }
          },
          {
            path:'/register',
            name:'Register',
            components:{
              'default':resolve => require(["@/views/register"], resolve),
            }
          },
          {
            path:'/user/:id',
            name:'User',
            components:{
              'default':resolve => require(["@/views/userInfo"], resolve),
            }
          }
        ]
      }
    ]
  },
  {
    path:'/',
    component: resolve => require(["@/components/layout/AdminLayout"], resolve),
    children:[
      {
        path: '/admin',
        name:'admin',
        components: {
          'adminHeader': resolve => require(["@/views/admin/AdminHeader"], resolve),
          'adminAside': resolve => require(["@/views/admin/AdminAside"], resolve),
          'adminMain': resolve => require(["@/views/admin/AdminMain"], resolve),
        },
      }
    ]
  },
  {
    path: '/401',
    name:'401',
    component: resolve => require(["@/views/errorPage/401"], resolve),
  },
  {
    path:'*',
    name:'404',
    component: resolve => require(["@/views/errorPage/404"], resolve),
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
