<template>
  <div :style="bg">

    <div class="top" style="border: 1px dashed #000">
      <ul>
        <li class="link01">Q/A SYSTEM</li>
        <li><a href="#" id="link03"><i class="iconfont">&#xe625;</i>&nbsp&nbsp主页</a></li>
        <li class="link02"><a href="#"><i class="iconfont">&#xe7bf;</i>&nbsp&nbsp提问</a></li>
        <li class="link02">
          <a href="#"><i class="iconfont">&#xe627;</i>&nbsp&nbsp社区</a>
          <!-- <ul>
              <li><a href="#">二级菜单</a></li>
              <li><a href="#">二级菜单</a></li>
          </ul> -->
        </li>

        <div class="search bar">
          <form>
            <input type="text" placeholder="请输入您要搜索的内容...">
            <button type="submit"></button>
          </form>
        </div>
        <div class="buBox">
          <!-- 触发按钮 -->
          <button id="triggerBtn">
            <li>
              <a href="#">
                <i class="iconfont">&#xe601;</i>
              </a>
            </li>
          </button>

        </div>
      </ul>
      <!--导航栏 end-->
    </div>

    <div class="person">

      <div>
        <img class="cycle" src="../assets/1.jpg" style="float:left"/>
      </div>

      <div class="name">
        <h3>&nbsp;康立言菜弟</h3>
      </div>
      <!--头像&名字 end-->
      <button @click="alterpsw" style="margin-left:10px;width: 100px;font-size: 18px;border:1px solid #000;cursor: pointer">修改密码</button>

      <div class="study_info">
        <div style="font-size: 25px">
          <span>我的积分</span>
          <span>&nbsp;</span>
          <span>发表问题</span>
          <span>&nbsp;</span>
          <span>回答问题</span>
          <span>&nbsp;</span>
          <span>我的关注</span>
          <span>&nbsp;</span>
          <span>我的收藏</span>
          <span>&nbsp;</span>
          <span>我的粉丝</span>
        </div>

        <div style="font-size: 20px">
          <br>
          <span >{{number[0].score}}</span>
          <span >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <span>{{number[0].question}}</span>
          <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <span>{{number[0].answer}}</span>
          <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <span>{{number[0].follow}}</span>
          <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <span>{{number[0].collect}}</span>
          <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <span>{{number[0].fan}}</span>
          <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
        </div>
      </div>
      <!--积分 end-->

    </div>
    <!--上面信息栏 end-->

    <!--动态-->
    <div style="margin-top: 220px">
      <div class="reg_form" >
        <h2 style="margin-left: 35%">请先验证旧密码是否正确</h2>
        <input type="text"  class="qxs-ic_password qxs-icon" v-model="oldpsw" v-if="this.seen.oldpwdType" placeholder="旧密码" style="font-size: 20px;padding-top: 30px">
        <input  type="password" class="qxs-ic_password qxs-icon" style="font-size: 20px;padding-top: 30px" placeholder="旧密码" v-model="oldpsw" v-else>
        <img :src="see?seeImg:unseeImg" @click="changeoldType" style="height: 20px;width: 20px;cursor: pointer">
        <span v-if="error.oldpsw" class="err-msg">{{error.oldpsw}}</span>

        <input type="text"  class="qxs-ic_password qxs-icon"  placeholder="新密码" v-if="this.seen.newpwdType" v-model="newpsw" style="font-size: 20px;padding-top: 30px">
        <input  type="password" class="qxs-ic_password qxs-icon" style="font-size: 20px;padding-top: 30px" placeholder="新密码" v-model="newpsw" v-else>
        <img :src="seeTwo?seeImg:unseeImg" @click="changenewType" style="height: 20px;width: 20px;cursor: pointer">
        <span v-if="error.newpsw" class="err-msg">{{error.newpsw}}</span>

        <input type="text"  class="qxs-ic_password qxs-icon"  placeholder="确定密码" v-if="this.seen.confirmpswType" v-model="confirmpsw" style="font-size: 20px;padding-top: 30px">
        <input  type="password" class="qxs-ic_password qxs-icon" style="font-size: 20px;padding-top: 30px" placeholder="确定密码" v-model="confirmpsw" v-else>
        <img :src="seeThree?seeImg:unseeImg" @click="confirmnewType" style="height: 20px;width: 20px;cursor: pointer">
        <span v-if="error.confirmpsw" class="err-msg">{{error.confirmpsw}}</span>
        <!--<button class="login_btn el-button el-button&#45;&#45;primary is-round" type="primary" round>登录</button>-->
        <br>
        <span v-if="error.null" class="err-msg" style="margin-left: 40%">{{error.null}}</span>
        <br>
        <button class="login_btn" :disabled="is_currect =='currect'" @click="con" style="font-size: 20px;margin-left: 20%" >验证密码</button>
        <button class="login_btn" :disabled="is_currect !='currect'" @click="confirm" style="font-size: 20px;margin-left: 60%;" >确定修改</button>
        <!--动态 end-->
      </div>
    </div>

    <div id="classList" class="listContainer" >
      <br>
      <a class="ui-link" @click="to_PersonalHome">个人空间</a>
      <br>
      <br>
      <a class="ui-link"  href="">我的关注</a>
      <br>
      <br>
      <a class="ui-link" href="">我的收藏</a>
      <br>
      <br>
      <a class="ui-link" @click="to_changeInfo">我的信息</a>
    </div>
    <!--左边导航栏 end-->
  </div>

</template>

<script>
  import global from './global.vue'
  var data = [
    {score:10,question:10,answer:20,follow:10,collect:10,fan:10},
  ];

  export default {
    name: "psw",
    data()
    {
      return{
        message: '',
        number:data,
        bg: {
          backgroundImage: "url(" + require("../assets/login_bg.png") + ")",
          backgroundRepeat: "no-repeat",
          backgroundSize: "100% 100%",
        },
        oldpsw: '',
        newpsw:'',
        confirmpsw:'',
        phone:'',
        is_currect:'',
        error:{
          oldpsw: '',
          newpsw:'',
          confirmpsw:'',
          null:''
        },
        seen:{
          oldpwdType: false,
          newpswType:false,
          confirmpswType:false,
        },
        see:'',
        seeTwo:'',
        seeThree:'',
        seeImg:require('../assets/see.png'),
        unseeImg:require('../assets/unsee.png'),
      }
    },
    created(){
      this.error.oldpsw=''
      this.error.null = ''
      this.error.newpsw = ''
      this.error.confirmpsw = ''
      this.is_currect=''
      this.see=''
      this.seeTwo=''
      this.seeThree=''
    },

    methods:{
      success(){
        this.oldpsw=''
        this.newpsw=''
        this.confirmpsw=''
        this.error.oldpsw=''
        this.error.null = ''
        this.error.newpsw = ''
        this.error.confirmpsw = ''
        this.is_currect=''
        this.see=''
        this.seeTwo=''
        this.seeThree=''
      },

      changeoldType(){
        this.see=!this.see
        this.seen.oldpwdType=!this.seen.oldpwdType
      },

      changenewType(){
        this.seeTwo=!this.seeTwo
        this.seen.newpwdType=!this.seen.newpwdType
      },
      confirmnewType(){
        this.seeThree=!this.seeThree
        this.seen.confirmpswType=!this.seen.confirmpswType
      },

      to_changeInfo(){
        this.$router.push({path:'/changeInfo'})
      },
      to_PersonalHome(){
        this.$router.push({path:'/PersonalHome'})
      },
      alterpsw(){
        this.$router.push({path:'/psw'})
      },

      con(){
        this.$axios(
          {
            method:'post',
            url:"http://localhost:8082/test/psw",
            params:{
              email:global.email,
              oldpsw:this.oldpsw,
            }
          }).then(res =>{
            console.log(this.oldpsw)
            console.log(res)
            if(res.data.trim()=="correct")
            {
              this.error.oldpsw="验证成功"
              this.is_currect='currect'
            }
          if(res.data.trim()=="failed")
          {
            this.error.oldpsw="验证失败"
          }
        }).catch(e =>{
          console.info(e)
        })
      },
      confirm(){
          this.error.oldpsw=''
          this.error.null = ''
          this.error.newpsw = ''
          this.error.confirmpsw = ''
          this.$axios(
            {
              method: 'post',
              url: "http://localhost:8082/test/AlterPsw",
              params: {
                email: global.email,
                oldpwd: this.oldpwd,
                newpwd: this.newpsw,
                confirm: this.confirmpsw
              }
            }).then(res => {
              console.log(res)
            if (res.data.trim() == "successfully") {
              this.$alert('修改成功', '提示', {
                confirmButtonText: '确定',
                callback: action => {
                  this.success()
                }
              });
            }
            else if (res.data.trim() == "unqualified") {
              this.error.newpsw = "密码至少要6位数"
            }
            else if (res.data.trim() == "not same") {
              this.error.confirmpsw = "两次密码不一致"
            }

          }).catch(e => {
            console.info(e)
          })
      }

    }
  }
</script>

<style scoped>
  @import "http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css";
  @import "../components/css/buttonBox.css";
  @import "../components/css/iconfont.css";
  @import "../components/css/searchBar.css";
  @import "../components/css/top.css";

  .person{
    position: fixed;
    left: 20px;
    right: 20px;
    top:76px;
    background-color: white;
    height: 150px;
  }

  .cycle{
    width: 120px;
    height: 120px;
    border-radius: 50%;
    margin-left: 8%;
    padding-top: 10px;
  }

  .name{
    font-size: 20px;
    text-align:left;
    padding-top: 10px;
  }

  .study_info{
    position: fixed;
    top:120px;
    right: 5%;
    min-width: 200px;
    text-align: right;
  }

  .listContainer{
    position: fixed;
    top:270px;
    margin-left:12%;
    width:180px;
    height:550px;
    background-color:white;
    text-align: center;
    display:inline-block;
    line-height: 40px;
    border:1px solid #000
  }
  .ui-link{
    margin: 0 auto;
    width:80%;
    display:block;
    font-size:25px;
    font-family:Roboto;
    color: #888;
    text-decoration: none;
    border-bottom: 1px solid rgba(187,187,187,1);
    color:rgba(0,0,0,0.87);
    cursor: pointer;
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
    width: 150px;
    height: 60px;
    margin-top: 30px;
    font-size: 20px;
    background-color: white;
    filter: brightness(1.4);
    border-radius: 15%;
  }

  .qxs-icon {
    height: 40px;
    width: 60%;
    margin-left: 5%;
    padding-bottom: 30px;
    padding-left: 10%;
    border: 0;
    border-bottom: solid 3px lavender;
  }

  .reg_form {
    position: fixed;
    top:32%;
    width: 1200px;
    margin-left: 30%;
    padding-top: 25px;
    background-color: white;
    box-shadow:5px 5px 5px grey;
  }

  .err-msg{
    width: 50px;
    background-color:wheat;
    font-size: 22px;
  }

</style>
