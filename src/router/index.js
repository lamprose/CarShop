import Vue from 'vue'
import Router from 'vue-router'
import TopBar from '@/components/CommonModule/TopBar'
import BottomBar from '@/components/CommonModule/BottomBar'
import SideBar from '@/components/CommonModule/SideBar'
import MainBox from '@/components/CommonModule/MainBox'
import page404 from '@/404'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      components: {
        'topBar': TopBar,
        'sideBar': SideBar,
        'bottomBar':BottomBar,
        'mainBox':MainBox
      },
    },
    {
      path:'*',
      name:'404',
      component: page404,
      meta:{
        title: '404 not found'
      }
    }
  ]
})
