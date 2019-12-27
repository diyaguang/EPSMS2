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
      name:'item1',
      path:'/item1',
      meta:{
        title:'item1'
      },
      component:(resolve)=>require(['../view/item1.vue'],resolve)
    },
    {
      name:'item2',
      path:'/item2',
      meta:{
        title:'item2'
      },
      component:(resolve)=>require(['../view/item2.vue'],resolve)
    },
    {
      name:'item3',
      path:'/item3',
      meta:{
        title:'item3'
      },
      component:(resolve)=>require(['../view/item3.vue'],resolve)
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
