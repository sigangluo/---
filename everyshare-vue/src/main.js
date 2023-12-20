import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from "axios";
import VueAxios from 'vue-axios'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import qs from 'qs'
// 配置公共地址
Vue.use(ElementUI);
Vue.use(VueAxios, axios)
// 配置原型链
// Vue.prototype.$http=axios
Vue.config.productionTip = false
Vue.prototype.$qs = qs

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
