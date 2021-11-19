import Vue from 'vue'
import router, {asynacRouterMap} from './router'
import store from './store'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import {ACCESS_TOKEN, USER_NAME} from "./store/mutation-types"

NProgress.configure({ showSpinner: false });
const whiteList = ['/register','/'];
router.beforeEach((to,from,next)=>{
  NProgress.start();
  store.commit("SET_TOKEN",Vue.ls.get(ACCESS_TOKEN));
  store.commit("SET_NAME",Vue.ls.get(USER_NAME));
  if(Vue.ls.get(ACCESS_TOKEN)){
    if(to.path==='/'){
      next('/home')
    }else{
      if(store.getters.role.length===0){
        store.dispatch("getInfo").then(res=>{
          store.dispatch('generateRoutes',store.getters.role).then(accessRoutes=>{
            router.addRoutes(accessRoutes);
          });
        });
      };

      next()
    }
  }else{
    if(whiteList.indexOf(to.path)!==-1){
      next()
    }else{
      next({path:'/'})
    }
  }
})


router.afterEach(() => {
  NProgress.done();
})
