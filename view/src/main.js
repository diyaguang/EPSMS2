// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import router from './router';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';
import Vuex from 'vuex';

axios.defaults.baseURL="/api"
//axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
Vue.prototype.$ajax = axios
Vue.use(ElementUI,{size:'mini'});
Vue.config.productionTip = false;

Vue.use(Vuex);
Vue.prototype.CurrentUser={};
//Vue.use(axios);


const store = new Vuex.Store({
  state:{
    //
  }
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store:store,
  components: { App },
  template: '<App/>'
})
