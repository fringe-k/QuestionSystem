<template>
  <div :style="bg" style="height: 100%">
    <div style="width: 100%"></div>
    <div class="reg_form" >
      <input type="text"  class="qxs-ic_user qxs-icon"  placeholder="用户名" v-model="userName" style="font-size: 20px;padding-top: 30px">
      <span v-if="error.userName" class="err-msg">{{error.userName}}</span>

      <input type="text"  class="qxs-ic_password qxs-icon"  placeholder="密码" v-if="this.seen.pswType" v-model="password" style="font-size: 20px;padding-top: 30px">
      <input type="password" class="qxs-ic_password qxs-icon" style="font-size: 20px;padding-top: 30px" placeholder="密码" v-model="password" v-else>
      <img :src="see?seeImg:unseeImg" @click="changeType" style="height: 20px;width: 20px;cursor: pointer">
      <span v-if="error.password" class="err-msg">{{error.password}}</span>

      <input type="text"  class="qxs-ic_password qxs-icon"  placeholder="确定密码"  v-if="this.seen.confirmpswType" v-model="confirmPassword" style="font-size: 20px;padding-top: 30px">
      <input  type="password" class="qxs-ic_password qxs-icon" style="font-size: 20px;padding-top: 30px" placeholder="确定密码" v-model="confirmPassword" v-else>
      <img :src="seeTwo?seeImg:unseeImg" @click="confirmType" style="height: 20px;width: 20px;cursor: pointer">
      <span v-if="error.confirmPassword" class="err-msg">{{error.confirmPassword}}</span>

      <input type="text"  class="qxs-ic_email qxs-icon"  placeholder="邮箱" v-model="email" style="font-size: 20px;padding-top: 30px">
      <span v-if="error.email" class="err-msg">{{error.email}}</span>
      <input type="text"  class="qxs-ic_age qxs-icon"  placeholder="年龄" v-model="age" style="font-size: 20px;padding-top: 30px">
      <span v-if="error.age" class="err-msg">{{error.age}}</span>
      <input type="text"  class="qxs-ic_phone qxs-icon"  placeholder="电话" v-model="phone" style="font-size: 20px;padding-top: 30px">
      <span v-if="error.phone" class="err-msg">{{error.phone}}</span>
      <!--<button class="login_btn el-button el-button&#45;&#45;primary is-round" type="primary" round>登录</button>-->
      <br>
      <button class="login_btn" @click="login" style="font-size: 20px" >注册</button>
      <div style="margin-top: 30px;padding-bottom: 5%">
        <span style="color: #000099;margin-left: 18%;font-size: 20px;cursor: pointer" @click="to_login">登录</span><span style="margin-left: 45%;color:  #000099;font-size: 20px;cursor: pointer;" @click="to_home">主界面</span>
      </div>
    </div>

  </div>

</template>

<script>
    export default {
     data() {
       return {
         bg: {
           backgroundImage: "url(" + require("../assets/login_bg.png") + ")",
           backgroundRepeat: "no-repeat",
           backgroundSize: "100% 100%",
         },

         userName: '',
         password: '',
         confirmPassword:'',
         email:'',
         age:'',
         phone:'',
         error:{
           userName:'',
           password:'',
           confirmPassword:'',
           email:'',
           age:'',
           phone:'',
           null: ''
         },
         seen:{
           pswType:false,
           confirmpswType:false,
         },
         see:'',
         seeTwo:'',
         seeImg:require('../assets/see.png'),
         unseeImg:require('../assets/unsee.png'),
       }
     },

      methods: {
        changeType(){
          this.see=!this.see
          this.seen.pswType=!this.seen.pswType
        },
        confirmType(){
          this.seeTwo=!this.seeTwo
          this.seen.confirmpswType=!this.seen.confirmpswType
        },

       to_home(){
         this.$router.push({path:'/home'})
       },
        to_login()
        {
          this.$router.push({path:'/Login'})
        },

        login() {
          this.error.null=''
          if(!this.userName){
            this.error.userName='请输入用户名'
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

          if(!this.confirmPassword){
            this.error.confirmPassword='请确定密码'
            return false
          }
          else{
            this.error.confirmPassword=''
          }

          if(!this.email){
            this.error.email='请输入邮箱'
            return false
          }
          else{
            this.error.email=''
          }

          if(!this.age){
            this.error.age='请输入年龄'
            return false
          }
          else{
            this.error.age=''
          }

          if(!this.phone){
            this.error.phone='请输入电话'
            return false
          }
          else{
            this.error.phone=''
          }

          this.$axios(
            {
              method:'post',
              url:"http://localhost:8082/test/sign",
              params:{
                username:this.userName,
                password:this.password,
                confirmPassword:this.confirmPassword,
                email:this.email,
                age:this.age,
                phone:this.phone
              }
            }).then(res =>{
            console.info(res)
            if(res.data.trim()== "successfully")
            {
              this.$confirm('注册成功！按确定前往登录界面？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'success'
              }).then(() => {
                this.$message({
                  type: 'success',
                  message: '到达登录界面',
              });
                  this.$router.push({path:'/Login'})
              }).catch(() => {
                this.$message({
                  type: 'info',
                  message: '已取消',
                });
              });

            }
            else if(res.data.trim()== "unsuccessfully")
            {
              this.$alert('邮箱已经注册', '提示', {
                confirmButtonText: '确定',
                callback: action => {

                }
              });
              this.error.null=''
            }
            else if(res.data.trim()== "username")
            {
              this.$alert('用户名不合法', '提示', {
                confirmButtonText: '确定',
                callback: action => {
                }
              });
              this.error.null=''
            }
            else if(res.data.trim()== "password")
            {
              this.$alert('密码至少6位', '提示', {
                confirmButtonText: '确定',
                callback: action => {

                }
              });
              this.error.null=''
            }
            else if(res.data.trim()== "passwordTwo")
            {
              this.$alert('两次密码不一致', '提示', {
                confirmButtonText: '确定',
                callback: action => {

                }
              });
              this.error.null=''
            }
            else if(res.data.trim()== "email")
            {
              this.$alert('邮箱不合格', '提示', {
                confirmButtonText: '确定',
                callback: action => {

                }
              });
              this.error.null=''
            }
            else {
              this.$alert('对不起，服务器繁忙', '提示', {
                confirmButtonText: '确定',
                callback: action => {

                }
              });
              this.error.null=''
            }
          }).catch(e =>{
            console.info(e)
          })

        }
      }

    }
</script>

<style scoped>

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

  .res_logo {
    height: 100%;
  }

  .bg{
    background:url("../assets/login_bg.png") center center;
    backgroundSize:"100% 100%";
  }


  .reg_form {
    position: absolute;
    top:20%;
    width: 520px;
    margin-left: 37%;
    background-color: white;
    padding-top: 25px;
    box-shadow:5px 5px 5px grey;
  }
  .qxs-ic_user {
    background: url("../assets/user.png") no-repeat;
    background-size: 30px 30px;
    background-position: 3%;
  }

  .qxs-ic_password {
    background: url("../assets/password.png") no-repeat;
    background-size: 30px 30px;
    background-position: 3%;
  }
  .qxs-ic_phone {
    background: url("../assets/phone.png") no-repeat;
    background-size: 30px 30px;
    background-position: 3%;
  }

  .qxs-ic_age {
    background: url("../assets/age.png") no-repeat;
    background-size: 30px 30px;
    background-position: 3%;
  }

  .qxs-ic_email{
    background: url("../assets/email.png") no-repeat;
    background-size: 30px 30px;
    background-position: 3%;
  }

  .login_btn {
    width: 100px;
    height: 50px;
    margin-top: 30px;
    margin-left: 37%;
    font-size: 16px;
    background-color: white;
    filter: brightness(1.4);
    border-radius: 15%;
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
