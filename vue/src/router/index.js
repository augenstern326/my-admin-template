import Vue from 'vue'
import Router from 'vue-router'

import Login from '@/views/login/Login.vue'

import Register from '@/views/register/Register.vue'
import Error from '@/views/errorPage/Error.vue'
import Layout from "../components/layout/Layout";
import Home from "../views/home/Home";
import SystemInfo from "../views/system/SystemInfo";
import Swagger from "../views/interfaceDoc/Swagger";
import MarkDown from "../views/editTool/MarkDown";
import RoleManage from "../views/systemSetting/RoleManage";
import SysUserManage from "../views/systemSetting/SysUserManage";
import MenuManage from "../views/systemSetting/MenuManage";
import DataSourceInfo from "../views/system/DataSourceInfo";

Vue.use(Router)


//NavigationDuplicated: Avoided redundant navigation to current location:
//暂未想到别的解决方法
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

export const constantRouterMap = [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path:'/register',
    name:'register',
    component:Register
  },
  {
    path:'/home',
    name:'layout',
    component: Layout,
    children:[
      {
        path:'/home',
        name:'home',
        component: Home,
        meta:{
          title:'首页',
          icon:'home'
        }
      },
      {
        path:'/systemInfo',
        name:'systemInfo',
        component: SystemInfo,
        meta:{
          title:'系统信息',
          icon:'dashboard'
        }
      },
      {
        path:'/markdown',
        name:'markdown',
        component:MarkDown,
        meta:{
          title:'Markdown',
          icon:'file-markdown'
        }
      }
    ]
  }
]

export const asynacRouterMap = [
  {
    path:'/home',
    name:'layout',
    component: Layout,
    children:[
    {
      path:"/swagger",
      name:'swagger',
      component:Swagger,
      meta:{
        roles:['admin'],
        title:'接口文档(管理员)',
        icon:'book'
      }
    },
    {
      path:"/druid",
      name:'druid',
      component:DataSourceInfo,
      meta:{
        roles:['admin'],
        title:'druid监控(管理员)',
        icon:'apartment'
      }
    },
    {
      path:'/sysUserManage',
      name:'sysUserManage',
      component:SysUserManage,
      meta: {
        roles:['admin'],
        title:'用户管理(管理员)',
        icon:'team'
      }
    },
    {
      path:'/roleManage',
      name:'roleManage',
      component:RoleManage,
      meta:{
        roles:['admin'],
        title:'角色管理(管理)',
        icon:'user'
      }
    },
    {
      path:'/MenuManage',
      name:'MenuManage',
      component:MenuManage,
      meta:{
        roles:['admin'],
        title:'菜单管理(管理员)',
        icon:'setting'
      }
    },
    {
      path:'*',
      name:'error',
      component:Error
    }]
  }
]


export default new Router({
  mode:'history',
  routes: constantRouterMap
})
