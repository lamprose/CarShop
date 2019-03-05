import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode:'history',
  routes: [
    {
      path: '/',
      components: {
        'topBar': resolve => require(["./components/TopBar.vue"], resolve),
        'asideBar': resolve => require(["./components/AsideBar.vue"], resolve),
        'asideBarBlank': resolve => require(["./components/AsideBarBlank.vue"], resolve),
        'bottomBar': resolve => require(["./components/BottomBar.vue"], resolve),
        'mainBox': resolve => require(["./components/MainBox.vue"], resolve),
      },
      children:[
        {
          path:'/home',
          name:'Home',
          components:{
            'default':resolve => require(["./views/Home.vue"], resolve),
            'userBlankBox': resolve => require(["./views/User.vue"], resolve),
            'shopCartBlankBox': resolve => require(["./views/ShopCart.vue"], resolve),
            'toolBlankBox': resolve => require(["./views/Tool.vue"], resolve),
          }
        },
        {
          path:'/search',
          name:'search',
          component: resolve => require(["./views/SearchResult.vue"], resolve),
        },
        {
          path:'/product/:id',
          name:'Goods',
          components:{
            'default':resolve => require(["./views/ProductInfo.vue"], resolve),
            'userBlankBox': resolve => require(["./views/User.vue"], resolve),
            'shopCartBlankBox': resolve => require(["./views/ShopCart.vue"], resolve),
            'toolBlankBox': resolve => require(["./views/Tool.vue"], resolve),
          }
        }
      ]
    },
    {
      path:'/error404',
      name:'404',
      component: resolve => require(["./components/Error404.vue"], resolve),
      meta:{
        title: '404 not found'
      }
    },
    {
      path: "*", // 此处需特别注意置于最底部
      redirect: "/error404"
    }
  ]
})
