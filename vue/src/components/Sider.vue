<template>
  <a-layout-sider width="200px" v-model="collapsed" :trigger="null" collapsible>

    <div class="logo"></div>
    <a-menu
      mode="inline"
      theme="dark"
      style="text-align: left"
    >
    </a-menu>
  </a-layout-sider>
</template>

<script>
  export default {
    name: "Sider",
    data() {
      return {
        collapsed: false
      };
    },
    methods:{
      menuJump(item){
        if(item.title==="项目源码"){
           window.open(item.path)
        }else{
          this.$router.push(item.path)
        }
      }
    }
  };
</script>

<style scoped>
  .logo {
    height: 32px;
    background:lightblue;
    margin: 16px;
  }
</style>
