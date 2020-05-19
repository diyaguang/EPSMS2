import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router)

export default new Router({
  routes: [
    {
      name:'home',
      path:'/',
      meta:{
        title:'首页'
      },
      component:(resolve)=>require(['../view/index.vue'],resolve)
    },
    {
      name:'userList',
      path:'/admin/user/list',
      meta:{
        title:'用户管理'
      },
      component:(resolve)=>require(['../view/admin/userList.vue'],resolve)
    },
    {
      name:'roleList',
      path:'/admin/sys/roles',
      meta:{
        title:'角色管理'
      },
      component:(resolve)=>require(['../view/admin/roleList.vue'],resolve)
    },
    {
      name:'functionList',
      path:'/admin/sys/functions',
      meta:{
        title:'功能管理'
      },
      component:(resolve)=>require(['../view/admin/functionList.vue'],resolve)
    },
    {
      name:'404',
      path:'/404',
      meta:{
        title:'404'
      },
      component:(resolve)=>require(['../view/page404.vue'],resolve)
    },
    {
      name:'default',
      path: '*',
      redirect: '/',
      meta: {
        title: 'default'
      }
    }

  ]
})
