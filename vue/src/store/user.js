//TODO 准备重写  代码比较混乱，没有模块化

import {getAction, postAction} from "../api/manage";

const user = {
  state:{
    username:'',
    token:'',
    role:[],
    info:{}
  },
  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, username) => {
      state.username = username
    },
    SET_ROLE: (state, role) => {
      state.role=role
    }
  },
  actions:{
    //获取用户信息
    getInfo({commit,state}){
      return new Promise((resolve,reject)=>{
        getAction("/sys/getRoleByToken",{"Token":state.token}).then((res)=>{
          const {data} = res;
          commit('SET_ROLE',[data]);
          resolve(res);
        })
      })

    }
  }
}

export default user;
