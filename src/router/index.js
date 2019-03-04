import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      components: {
        'topBar': resolve => require(["../components/TopBar.vue"], resolve),
        'sideBar': resolve => require(["../components/SideBar.vue"], resolve),
        'sideBarBlank': resolve => require(["../components/SideBarBlank.vue"], resolve),
        'bottomBar': resolve => require(["../components/BottomBar.vue"], resolve),
        'mainBox': resolve => require(["../components/MainBox.vue"], resolve),
      },
    },
    {
      path:'*',
      name:'404',
      component: resolve => require(["../components/Error404.vue"], resolve),
      meta:{
        title: '404 not found'
      }
    }
  ]
})
