<template>
  <div>


      <div class="outer_label">
        <img class="inner_label login_logo" src="../assets/logo.png">
      </div>
    <div class="bg">
      <div class="login_form">
        <input type="text"  class="qxs-ic_user qxs-icon"  placeholder="账号" v-model="userName">
        <span v-if="error.userName" class="err-msg">{{error.userName}}</span>
        <input type="text"  class="qxs-ic_password qxs-icon"  placeholder="密码" v-model="password">
        <span v-if="error.password" class="err-msg">{{error.password}}</span>
      <!--<button class="login_btn el-button el-button&#45;&#45;primary is-round" type="primary" round>登录</button>-->
        <button class="login_btn" @click="login" >登录</button>
        <div style="margin-top: 30px">
          <span style="color: #000099;margin-left: 25%" @click="login">邮箱登陆</span><span style="margin-left: 40%;color: #A9A9AB">忘记密码？</span>
        </div>
      </div>

    </div>
  </div>
</template>

<script>

  //  import { userLogin } from '../../api/api';
  import PersonalHome from "./PersonalHome";

  export default {
    data() {
      return {
        userName: '',
        password: '',
        error:{
          userName:'',
          password:''
        }
      }
    },
    /*created () {
      if(JSON.parse( localStorage.getItem('user')) && JSON.parse( localStorage.getItem('user')).userName){
        this.userName = JSON.parse( localStorage.getItem('user')).userName;
        this.password = JSON.parse( localStorage.getItem('user')).password;
      }
    },*/

    methods: {
      check(userName,password){
        if(!userName){
          this.error.userName='请输入账号'
          return false
        }
        else{
          this.error.userName=''
        }

        if(!password){
          this.error.password='请输入密码'
            return false
        }
        else{
          this.error.password=''
        }

      },

      login() {
        if(!this.userName){
          this.error.userName='请输入账号'
          return false
        }
        else{
          this.error.userName=''
        }

        if(!this.password){
          this.error.password='请输入密码'
          return false
        }
        else{
          this.error.password=''
        }

          this.$axios(
            {
              method:'post',
              url:"http://localhost:8082/test/loginId",
              params:{
                  id:this.userName,
                  password:this.password
              }
            }).then(res =>{
              console.info(res)
          }).catch(e =>{
            console.info(e)
          })

      /*this.axios.get('http://localhost:8082/test/hello')
            .then(function (response) {
              console.log(response);
            })
            .catch(function (error) {
              console.log(error);
            });*/

      }
    }
  }

</script>

<style>
  .login_form {
    padding-top: 10%;
    padding-left: 10%;
    padding-right: 10%;
    background-color:white;
  }
  .qxs-ic_user {
    background: url("../assets/user.png") no-repeat;
    background-size: 15px 15px;
    background-position: 3%;
  }

  .qxs-ic_password {
    background: url("../assets/password.png") no-repeat;
    background-size: 15px 15px;
    background-position: 3%;
    margin-bottom: 20px;
  }

  .bg{
    background:url("../assets/login_bg.png") no-repeat;
    backgroundSize:"100% auto";
  }

  .login_logo {
    height: 100%;
  }

  .login_btn {
    width: 10%;
    margin-top: 30px;
    margin-left: 45%;
    font-size: 16px;
    background-color: white;
    filter: brightness(1.4);
    border-radius: 15%;
  }

  body {
    padding: 0;
    margin:0;
    font-family: "Microsoft YaHei UI Light";
  }

  .outer_label {
    position: relative;
    left: 0;
    top: 0;
    width: 100%;
    height: 100px;
    background-color:#2154FA;
    text-align: center;
    filter: brightness(1.4);
  }

  .inner_label {
    position: absolute;
    left:0;
    right: 0;
    bottom: 0;
    top:0;
    margin: auto;
    height: 50px;
  }

  .qxs-icon {
    height: 40px;
    width: 40%;
    margin-left: 25%;
    margin-bottom: 5px;
    padding-left: 10%;
    border: 0;
    border-bottom: solid 1px lavender;
  }
  .err-msg{
    width: 20%;
    background-color: rgba(0,0,0,0.4);
    font-size: 20px;
  }

</style>
