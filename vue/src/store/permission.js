import {asynacRouterMap,constantRouterMap} from "../router"


//这里照搬了花裤衩大佬的permission.js，并删减了一些
function hasPermission(role,route){
  if(route.meta && route.meta.roles){
    return role.some(role=>route.meta.roles.includes(role));
  }else{
    return true;
  }
}

//根据角色，递归生成动态路由
export function filterAsyncRoutes(routes,role){
  const res = [];
  routes.forEach(route =>{
    const tmp = {...route};     //深拷贝一个对象
    if(hasPermission(role,tmp)){
      if(tmp.children){
        tmp.children = filterAsyncRoutes(tmp.children,role);
      }
      res.push(tmp);
    }
  })
  return res;
}
const permission = {
  actions:{
    generateRoutes({ commit },role){
      let accessRoutes = filterAsyncRoutes(asynacRouterMap,role);
      return accessRoutes;
    }
  }
}


export default permission;
