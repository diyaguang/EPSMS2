// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import router from './router';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';
import Vuex from 'vuex';
import vConsole from 'vconsole'

//开启 Graphql-client ---------------------------------------------------------------
import {ApolloClient} from 'apollo-client'
import {HttpLink} from 'apollo-link-http'
import {InMemoryCache} from "apollo-cache-inmemory";
import VueApollo from 'vue-apollo'

const httpLink  = new HttpLink({
  uri:'http://localhost:8090/graphql'
})

const apolloClient = new ApolloClient({
  link:httpLink,
  cache: new InMemoryCache(),
  connectToDevTools:true,
})

Vue.use(VueApollo)

const apolloProvider = new VueApollo({
  defaultClient:apolloClient,
})
//----------------------------------------------------------------------------------

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
  provide:apolloProvider.provide(),
  components: { App },
  template: '<App/>'
})
