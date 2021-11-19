<template>
  <a-layout style="height:100%;">
    <sider></sider>
    <a-layout style="height:100%;">
      <a-layout-header class="header">
        <div style="font-size: 18px;color:#ffffff;">
          欢迎您!&emsp;<b>{{this.$store.getters.username}}</b>&emsp;您的角色是:&emsp;<b>{{this.$store.getters.role[0]}}</b>
        </div>
        <div>
          <a-dropdown>
            <a-avatar icon="user"></a-avatar>
            <a-menu slot="overlay">
              <a-menu-item key="0">个人中心</a-menu-item>
              <a-menu-item key="1" @click="loginOut">退出登录</a-menu-item>
            </a-menu>
          </a-dropdown>
        </div>
      </a-layout-header>
      <a-layout-content class="content">
        <router-view/>
      </a-layout-content>
      <a-layout-footer style="text-align: center;height:10px;padding-top: 0px">学习练手代码，仅供参考，欢迎交流</a-layout-footer>
    </a-layout>
  </a-layout>

</template>

<script>

  import Vue from 'vue'
  import Sider from "../Sider";
  import {postAction} from "../../api/manage";
  import {ACCESS_TOKEN, USER_NAME} from "../../store/mutation-types";
  import store from '../../store'
  export default {
    name: "Layout",
    components: {Sider},
    data(){
      return {
        url:{
          loginOut:"/sys/logout"
        }
      }
    },
    methods:{
      loginOut(){
        postAction(this.url.loginOut,{}).then((res)=>{
          Vue.ls.remove(ACCESS_TOKEN);
          Vue.ls.remove(USER_NAME);
          this.$store.commit("SET_ROLE",[]);
          this.$router.push('/');
        })
      }
    }
  }
</script>

<style lang="less" scoped>
  .header{
    display: flex;
    flex-direction: row;
    background: #90d7ec;
    justify-content: space-between;
    width:100%;
    padding-right: 20px;
  }
  .trigger {
    font-size: 18px;
    line-height: 64px;
    padding: 0 5px;
    cursor: pointer;
    transition: color 0.3s;
  }
  .content{
    margin: 16px 16px;
    padding: 0px;
    background: #fff;
    minHeight: 400px;
    overflow:auto;
  }
  .content::-webkit-scrollbar {
    width: 8px;
    height:8px;
    background-color: #4EFEB3;
  }
  .content::-webkit-scrollbar-track{
    background-color: white;
  }
  .content::-webkit-scrollbar-thumb{
    border-radius: 10px;
    background-color: #4EFEB3;
  }
</style>
