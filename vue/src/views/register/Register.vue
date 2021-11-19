<template>
    <a-form>
      <a-form-item label="用户名:">
        <a-input v-model="username"></a-input>
      </a-form-item>
      <a-form-item label="密码:">
        <a-input-password v-model="password"></a-input-password>
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="register">注册</a-button>
      </a-form-item>
    </a-form>
</template>

<script>
    import {postAction} from "../../api/manage";

    export default {
        name: "Register",
        data(){
          return {
            username:"",
            password:"",
            url:{
              register: "/sys/register"
            }
          }
        },
        methods:{
          register(){
            postAction(this.url.register,{"username":this.username,"password":this.password})
            this.$router.push("/");
          }
        }
    }
</script>

<style scoped>

</style>
