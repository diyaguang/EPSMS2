import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import VueRouter from 'vue-router';
import Routers from './config/routerConfig';

Vue.config.productionTip = false

Vue.use(ElementUI,{size:'mini'});
Vue.use(VueRouter);

const RouterConfig={
  mode:'history',
  routes:Routers
};
const router = new VueRouter(RouterConfig);

new Vue({
  render: h => h(App),
  router:router
}).$mount('#app')
