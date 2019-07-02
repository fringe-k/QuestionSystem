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
      <button @click=" alterpsw" style="margin-left:10px;width: 100px;font-size: 18px;border:1px solid #000;cursor: pointer">修改密码</button>

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
      <input type="text"  class="qxs-ic_user qxs-icon"  placeholder="用户名" v-model="userName" style="font-size: 20px;padding-top: 30px">
      <span v-if="error.userName" class="err-msg">{{error.userName}}</span>
      <input type="text"  class="qxs-ic_email qxs-icon"  placeholder="邮箱" v-model="email" style="font-size: 20px;padding-top: 30px">
      <span v-if="error.email" class="err-msg">{{error.email}}</span>
      <input type="text"  class="qxs-ic_age qxs-icon"  placeholder="年龄" v-model="age" style="font-size: 20px;padding-top: 30px">
      <span v-if="error.age" class="err-msg">{{error.age}}</span>
      <input type="text"  class="qxs-ic_phone qxs-icon"  placeholder="电话" v-model="phone" style="font-size: 20px;padding-top: 30px">
      <span v-if="error.phone" class="err-msg">{{error.phone}}</span>
      <!--<button class="login_btn el-button el-button&#45;&#45;primary is-round" type="primary" round>登录</button>-->
        <br>
      <button class="login_btn" @click="confirm" style="font-size: 20px" >确定修改</button>
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
    name: "changeInfo",
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
        userName: '',
        email:'',
        age:'',
        phone:'',
        error:{
          userName:'',
          email:'',
          age:'',
          phone:'',
          null:''
        }
      }
    },

    created(){
      this.error.null=''
      this.error.userName=''
      this.error.email=""
      this.$axios(
        {
          method:'get',
          url:"http://localhost:8082/test/AlterInformation",
          params:{
              email:global.email
          }
        }).then(res =>{
          console.log(res)
        this.userName=res.data.username
        this.email=res.data.mail
        this.age=res.data.age
        this.phone=res.data.phone
      }).catch(e =>{
        console.log(1111)
        console.info(e)
      })
    },

    methods:{
      to_changeInfo(){
        this.$router.push({path:'/changeInfo'})
      },
      to_PersonalHome(){
        this.$router.push({path:'/PersonalHome'})
      },
      alterpsw(){
        this.$router.push({path:'/psw'})
      },
      success(){
        this.$alert('修改成功', '提示', {
          confirmButtonText: '确定',
          callback: action => {

          }
        });

      },

      confirm(){
        this.error.null = ""
        this.error.userName=""
        this.error.email=""
        this.error.age=''
        this.error.phone=''
        this.$axios(
          {
            method:'post',
            url:"http://localhost:8082/test/AlterInformation",
            params:{
              username:this.userName,
              email:this.email,
              age:this.age,
              phone:this.phone

            }
          }).then(res =>{
            console.log(res)
          if((res.data.username.trim()=="true")&&(res.data.email.trim()=="false"))
          {
            this.error.userName="用户名不合格"
          }
          else if((res.data.username.trim()=="false")&&(res.data.email.trim()=="true"))
          {
            this.error.email="邮箱不合法"
          }
          else if(res.data.age==1519767717)
          {
            this.error.age="年龄只能包含数字"
          }
          else if(res.data.phone.trim()=="true")
          {
            this.error.phone="电话不能修改为空值"
          }
          else
          {
            this.error.userName=""
            this.error.email=""
            this.userName = res.data.username
            console.log(res.data.username)
            this.email = res.data.mail
            this.age = res.data.age
            this.phone = res.data.phone
            this.success()
          }
        }).catch(e =>{
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
    top:28%;
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
    margin-left: 40%;
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
    font-size: 20px;
  }

</style>
