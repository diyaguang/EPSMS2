const routers =[
    {
        name:'home',
        path:'/',
        meta:{
            title:'首页'
        },
        component:(resolve)=>require(['../view/index.vue'],resolve)
    },
    {
        name:'default',
        path: '*',
        redirect: '/',
        meta: {
            title: '首页'
        }
    }
];
export default routers;
