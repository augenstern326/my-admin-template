// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import Storage from 'vue-ls'
import '@/permission.js'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import * as echarts from 'echarts'
import moment from 'moment'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'


Vue.use(Antd)
Vue.use(Storage)
Vue.use(mavonEditor)


Vue.prototype.$echarts = echarts
Vue.prototype.$moment = moment
Vue.config.productionTip = false
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
