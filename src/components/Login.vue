<template>
  <div  :style="bg" style="height: 100%">

      <div class="login_form">
        <input type="text"  class="qxs-ic_user qxs-icon"  placeholder="邮箱" v-model="userName" style="font-size: 20px">
        <span v-if="error.userName" class="err-msg">{{error.userName}}</span>
        <input type="text"  class="qxs-ic_password qxs-icon"  placeholder="密码" v-model="password" style="font-size: 20px">
        <span v-if="error.password" class="err-msg">{{error.password}}</span>
      <!--<button class="login_btn el-button el-button&#45;&#45;primary is-round" type="primary" round>登录</button>-->
        <br>
        <span v-if="error.null" class="err-msg" style="margin-left: 30%">{{error.null}}</span>
        <button class="login_btn" @click="login" style="font-size: 20px">登录</button>
        <div style="margin-top: 30px;padding-bottom: 10%">
          <span style="color: #000099;margin-left: 20%;font-size: 20px" @click="to_reg">注册</span><span style="margin-left: 40%;color: #A9A9AB;font-size: 20px" @click="to_reset">忘记密码？</span>
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
        bg: {
          backgroundImage: "url(" + require("../assets/bg1.jpg") + ")",
          backgroundRepeat: "no-repeat",
          backgroundSize: "100% 100%",
        },
        userName: '',
        password: '',
        error:{
          userName:'',
          password:'',
          null:''
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
      to_reset(){
        this.error.null='好好想一下，加油！'
      },

      to_reg(){
        this.$router.push({path:'/register'})
      },

      login() {
        this.error.null=''
        if(!this.userName){
          this.error.userName='请输入邮箱'
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
              url:"http://localhost:8082/test/loginMail",
              params:{
                  mail:this.userName,
                  password:this.password
              }
            }).then(res =>{
            if(res.data.trim()== "success")
            {
              this.$router.push({path:'/home'})

            }
            else if(res.data.trim()== "forbidden")
            {
              console.info(res)
              this.error.null='你已经被禁止登录！'
              return false
            }
            else
            {
              console.info(res)
              this.error.null='邮箱或密码错误'
              return false
            }
          }).catch(e =>{
            console.info(e)
            console.log('连接失败')
          })
      }

    }
  }

</script>

<style>
  .login_form {
    position: absolute;
    top:30%;
    width: 500px;
    margin-left: 37%;
    background-color: white;
    padding-top: 30px;
    box-shadow:5px 5px 5px grey;
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
    background:url("../assets/login_bg.png") center center;
    backgroundSize:"100% 100%";
  }

  .login_logo {
    height: 100%;
  }

  .login_btn {
    width: 100px;
    height: 50px;
    margin-top: 30px;
    margin-left: 35%;
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
    padding-bottom: 10px;
    padding-left: 10%;
    border: 0;
    border-bottom: solid 2px lavender;
  }
  .err-msg{
    width: 50px;
    background-color:wheat;
    font-size: 20px;
  }

</style>
