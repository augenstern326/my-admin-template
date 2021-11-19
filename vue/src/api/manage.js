import  axios  from 'axios'
import Vue from 'vue'
import {ACCESS_TOKEN} from "../store/mutation-types";

const API_ROOT = process.env.API_ROOT

export function postAction(url,parameter) {
  return axios({
    url: url,
    baseURL:API_ROOT,
    method:'post' ,
    data: parameter,
    headers:url=='/sys/login'||url=='/sys/register'?{}:{'token':Vue.ls.get(ACCESS_TOKEN)}
  })
}

export  function getAction(url,parameter){
  return axios({
    url: url,
    baseURL: API_ROOT,
    method: 'get',
    params: parameter,
    headers: url=='/sys/login'||url=='/sys/register'?{}:{'token':Vue.ls.get(ACCESS_TOKEN)}
  })
}
