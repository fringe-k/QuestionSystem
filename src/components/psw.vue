<template>
  <div :style="bg">

    <div class="top">
      <ul class="nav" style="padding-left: 6%;">
        <li class="link01"> Q/A SYSTEM</li>
        <li class="nav-item">
          <a class="nav-link" @click="toHome"><i class="iconfont">&#xe625;</i>&nbsp&nbsp主页</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" @click="toQuestion" id="link03"><i class="iconfont">&#xe7bf;</i>&nbsp&nbsp问题</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#"><i class="iconfont">&#xe627;</i>&nbsp&nbsp社区</a>
        </li>
        <div class="search bar">
          <form>
            <input type="text" placeholder="请输入您要搜索的内容...">
            <button id="searchBtn" type="submit"></button>
          </form>
        </div>
        <div class="buBox">
          <!-- 触发按钮 -->
          <div v-if="hasNotLogin[0]">
            <button id="triggerBtn" @click="toLogin"><li><a href="#" data-toggle="tooltip" data-placement="bottom" title="登录"><i class="iconfont">&#xe601;</i></a></li></button>
          </div>
          <div v-else>
            <button id="personBtn">
              <div @click="toPsw">
                <ul>
                  <li style="float:left;margin-top: -2px">
                    <a data-toggle="tooltip" data-placement="bottom" title="个人中心"><el-avatar :size="35" :src="circleUrl"></el-avatar></a>
                  </li>
                  <li style="float:left;">
                    <a data-toggle="tooltip" data-placement="bottom" title="个人中心" style="text-align: end">{{myName}}</a>
                  </li>
                </ul>
              </div>
            </button>
          </div>
        </div>
      </ul>
    </div>

    <div class="person">

      <div>
        <div>
          <el-upload
            class="avatar-uploader"
            action="http://query.liublack.cn/qs/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
      </div>

      <div class="name">
        <h3>&nbsp;{{this.userName}}</h3>
      </div>
      <!--头像&名字 end-->
      <button @click="alterpsw" style="margin-left:10px;width: 100px;font-size: 18px;border:1px solid #bd5151;cursor: pointer;background-color: white">修改密码</button>

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
          <span >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <span>{{number[0].question}}</span>
          <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <span>{{number[0].answer}}</span>
          <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <span>{{number[0].follow}}</span>
          <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <span>{{number[0].collect}}</span>
          <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
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
        <h2 style="margin-left: 35%">请先填写完新旧密码后提交</h2>
        <br>
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
        <button class="login_btn" @click="confirm" style="font-size: 25px;margin-left: 40%;" >确定修改</button>
        <br><br><br><br>

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
  import { JSEncrypt } from 'jsencrypt'

  var data = [
    {score:5,question:5,answer:5,follow:5,collect:5,fan:5},
  ];
  var hasNotLogin = [1]
  var hasNotLogin = [1]
  export default {
    name: "psw",
    data()
    {
      return{
        userName:'',
        imageUrl: '',
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
        rsaoldPassword:'',
        rsanewPassword:'',
        rsaconPassword:'',
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
        circleUrl: global.photo,
        hasNotLogin:hasNotLogin,
        myId:global.userId,
        myName:global.name,
      }
    },
    created(){
      if(global.userId==-1){
        console.log(hasNotLogin)
      }
      else{
        hasNotLogin.splice(0,hasNotLogin.length)
        hasNotLogin.push(0)
      }
      this.error.oldpsw=''
      this.error.null = ''
      this.error.newpsw = ''
      this.error.confirmpsw = ''
      this.is_currect=''
      this.see=''
      this.seeTwo=''
      this.seeThree=''

      this.$axios(
        {
          method:'get',
          url:global.host+"/uploadphoto",
          params:{
            email:global.email,
          }
        }).then(res =>{
        this.imageUrl=res.data.trim()
        console.log(this.imageUrl)

      }).catch(e =>{
        console.log(1111)
        console.info(e)
      })

      this.$axios(
        {
          method:'get',
          url:global.host+"/AlterInformation",
          params:{
            email:global.email
          }
        }).then(res =>{
        console.log(res)
        this.userName=decodeURI(res.data.username)
      }).catch(e =>{
        console.log(1111)
        console.info(e)
      })

      this.$axios(
        {
          method:'get',
          url:global.host+"/ReturnInformation",
          params:{
            email:global.email
          }
        }).then(res =>{
        console.log(res)
        this.number[0].question=res.data.numOfQuery
        this.number[0].answer=res.data.numOfAnswer
        this.number[0].score=res.data.score
      }).catch(e =>{
        console.log(1111)
        console.info(e)
      })
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

      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
        var uri='http://query.liublack.cn/qs'+res[0].uri
        console.log(uri)
        console.log(res[0].uri)
        this.$axios(
          {
            method:'post',
            url:global.host+"/uploadphoto",
            params:{
              email:global.email,
              uri:uri
            }
          }).then(res =>{
          if(res.data.trim()=='success')
          {
            this.$alert('上传头像成功', '提示', {
              confirmButtonText: '确定',
              callback: action => {
              }
            });
          }
          else {
            this.$alert('上传头像失败', '提示', {
              confirmButtonText: '确定',
              callback: action => {

              }
            });
          }

        }).catch(e =>{
          console.log(1111)
          console.info(e)
        })
      },

      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
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


      },
      confirm(){
          this.error.oldpsw=''
          this.error.null = ''
          this.error.newpsw = ''
          this.error.confirmpsw = ''

        if(!this.oldpsw){
          this.error.oldpsw='请输入旧密码'
          return false
        }
        else{
          this.error.oldpsw=''
        }

        if(!this.newpsw){
          this.error.newpsw='请输入新密码'
          return false
        }
        else{
          this.error.newpsw=''
        }

        if(!this.confirmpsw){
          this.error.confirmpsw='请确定新密码'
          return false
        }
        else{
          this.error.confirmpsw=''
        }

        let pPattern = /^[a-zA-Z0-9]{6,20}$/
        if(!pPattern.test(this.newpsw))
        {
          this.$alert('密码至少6位,可包括大写字母，小写字母，数字', '提示', {
            confirmButtonText: '确定',
            callback: action => {
            }
          });
          return false
        }

        if(this.newpsw!=this.confirmpsw)
        {
          this.$alert('两次密码不一致', '提示', {
            confirmButtonText: '确定',
            callback: action => {
              this.success()
            }
          });
          return false
        }

        this.$axios(
          {
            method:'get',
            url:global.host+"/rsaPassword",
            params:{
            }
          }).then(res=>{
          console.log(res)
          let encryptor=new JSEncrypt()
          let publicKey=res.data
          encryptor.setPublicKey(publicKey)
          this.rsaoldPassword=encryptor.encrypt(this.oldpsw)
          this.rsanewPassword=encryptor.encrypt(this.newpsw)
          console.log(this.rsaoldPassword)
          console.log(this.rsanewPassword)
          this.$axios(
              {
                method:'post',
                url:global.host+"/AlterPsw",
                params:{
                  email:global.email,
                  oldpsw:this.rsaoldPassword,
                  newpsw: this.rsanewPassword,
                }
              }).then(res =>{
              console.log(res)
            if(res.data.trim()=="successfully")
            {
              this.$alert('修改密码成功！', '提示', {
                confirmButtonText: '确定',
                callback: action => {
                  this.success()
                }
              });
            }
            if(res.data.trim()=="failed")
            {
              this.$alert('修改密码失败！请确定旧密码是否正确！', '提示', {
                confirmButtonText: '确定',
                callback: action => {
                }
              });
            }
          }).catch(e =>{
            console.info(e)
          })

        }).catch(e =>{
          console.info(e)
          console.log('连接失败')
        })
      },
      toLogin:function(){
        this.$router.push({
          path: '/Login',
          query: {
          }
        })
      },
      toHome:function(){
        this.$router.push({
          path: '/',
          query: {
          }
        })
      },
      toPsw:function () {
        this.$router.push({
          path: '/psw',
          query: {
          }
        })
      },
      toQuestion:function () {
        this.$router.push({
          path: '/QuestionShow',
          query: {
          }
        })
      }
    }
  }
</script>

<style scoped>
  @import "http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css";
  @import "../components/css/buttonBox.css";
  @import "../assets/icon/iconfont.css";
  @import "css/searchBar.css";
  @import "../components/css/guide.css";

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
    left:12%;
    width:180px;
    height:550px;
    background-color:white;
    text-align: center;
    display:inline-block;
    line-height: 40px;
    border:1px solid #bd5151;
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
    outline-color: #bd5151;
  }

  .reg_form {
    position: fixed;
    top:270px;
    width: 1200px;
    margin-left: 30%;
    padding-top: 25px;
    background-color: white;
    box-shadow:5px 5px 5px grey;
  }

  .err-msg{
    width: 50px;
    background-color:white;
    font-size: 22px;
  }

  .avatar-uploader{
    border: 1px dashed #d9d9d9;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    width: 120px;
    height: 120px;
    border-radius: 50%;
    margin-left: 12%;
    margin-top: 10px;
    float:left;
  }
  .avatar-uploader{
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 120px;
    height: 120px;
    line-height: 120px;
    text-align: center;
  }
  .avatar {
    width: 120px;
    height: 120px;
    display: block;
  }
</style>
