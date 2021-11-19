<template>
  <div class="main" align="center">
    <a-card :hoverable="true">
      <div class="user-login">
        <div class="login-title">
      </div>
        <div class="login-form">
        <a-form :form="form">
          <a-form-item>
              <a-input
                size="large"
                v-decorator="['username',validatorRules.username]"
                placeholder="请输入用户名">
                <a-icon slot="prefix" type="user" :style="{color:'rgba(0,0,0,.25)'}"></a-icon>
              </a-input>
            </a-form-item>
          <a-form-item>
              <a-input-password
                size="large"
                v-decorator="['password',validatorRules.password]"
                placeholder="请输入密码">
               <a-icon slot="prefix" type="lock" :style="{color:'rgba(0,0,0,.25)'}"></a-icon>
              </a-input-password>
          </a-form-item>
          <a-form-item>
              <a-button style="width: 100%"
                type="primary"
                size="large"
                @click="login">
                登录
              </a-button>
          </a-form-item>
        </a-form>
      </div>
      </div>
    </a-card>
  </div>
</template>

<script>
    import {postAction} from "../../api/manage";
    import {ACCESS_TOKEN,USER_NAME} from "../../store/mutation-types";
    import Vue from 'vue'
    export default {
        name: "Login",
        data(){
          return {
              username:"",
              password:"",
              form: this.$form.createForm(this),
              url:{
                login:"/sys/login"
              },
              validatorRules:{
                username:{initialValue:'admin',rules:[{required:true,message:'用户名不能为空!'}]},
                password:{initialValue:'123456',rules:[{required:true,message:"密码不能为空!"}]}
              }
          }
        },
        methods:{
          login(){
            let loginParams = {};
            this.form.validateFields(['username','password'],{force:true},(err,values)=>{
              if(!err){
                loginParams.username = values.username;
                loginParams.password = values.password;
                postAction(this.url.login,loginParams).then((res)=>{
                  const result = res.data;
                  alert(result.message)
                  if(result.success===true){
                    Vue.ls.set(ACCESS_TOKEN,result.data.token,30 * 60 * 1000*10); //设置有效时间为30分钟
                    Vue.ls.set(USER_NAME,result.data.username,30 * 60 * 1000*10);
                    this.$store.commit('SET_NAME',this.username);
                    this.$store.commit('SET_TOKEN',result.data.token);
                    this.$router.push('/home');
                  }
                })
              }
            })
          }
        }
    }
</script>

<style  lang="less" scoped>
.main{
   padding-top: 5rem;
   /deep/.ant-card{
     padding-top: 4rem;
     width:40rem;
     text-align: left;

    .user-login{
        display: flex;
        flex-direction: row;
        justify-content:left;
        .login-title{
           width:50%;
        }
        .login-form{
          width:50%;
        }
     }
  }
}
</style>
