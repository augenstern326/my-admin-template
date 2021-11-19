import Vue from 'vue'
import Vuex from 'vuex'
import getters from "./getters";
import user from "./user";
import permission from "./permission";


Vue.use(Vuex)

const store = new Vuex.Store({
  modules:{
    user,
    permission
  },
  getters
})


export default store;
