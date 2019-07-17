<template>
  <div>

    <div class="top">
      <ul class="nav" style="padding-left: 6%;">
        <li class="link01"> Q/A SYSTEM</li>
        <li class="nav-item">
          <a class="nav-link" @click="toHome"><i class="iconfont">&#xe625;</i>&nbsp&nbsp主页</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" @click="toQuestion"><i class="iconfont">&#xe7bf;</i>&nbsp&nbsp问题</a>
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
              <div>
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

      <div class="name">
          <h3>&nbsp;{{this.userName}}</h3>
      </div>

      <button @click="alterpsw" class="button">修改密码</button>
      <!--头像&名字 end-->

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
          <span >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
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
    <div v-for="content in contents" >
      <div class="contain" >
        <div>
          <img class="cycle" :src="imageUrl" style="float:left;height:100px;width: 100px;padding-top: 12px"/>
        </div>

        <div class="name" style="padding-top: 10px">
          <h5>&nbsp;&nbsp;&nbsp;{{content.name}}</h5>
        </div>

        <div style="margin-top: 0px">
          <span>{{content.dateTime}}</span>
        </div>

        <div class="content">
          <p>&nbsp;&nbsp;{{content.con}}</p>
        </div>

        <div>
          <img src="../assets/2.png" style="width: 50%;height: 30%;margin-left: 180px"/>
        </div>

        <div style="padding-bottom: 10px">
          <br>
          <span style="margin-left:25%">评论</span>
          <span>{{content.p}}</span>
          <span style="margin-left:10%">点赞</span>
          <span>{{content.d}}</span>
          <span style="margin-left:10%">转发</span>
          <span>{{content.z}}</span>
        </div>

      </div>
    </div>
    <!--动态 end-->
    </div>

    <div id="classList" class="listContainer" >
      <br>
      <a class="ui-link" href="">个人空间</a>
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
  import util from '@/util.js'
  //import {getAliOSSCreds} from '@/api/common'// 向后端获取 OSS秘钥信息
   //import {createId} from '@/utils' // 一个生产唯一的id的方法
   import OSS from 'ali-oss'
  var hasNotLogin = [1]
  var message="";
  var data = [
    {score:5,question:5,answer:5,follow:5,collect:5,fan:5},
  ];
  var content=[]

  /*var content=[
    {image:"1.jpg",name:"康立言菜弟",day:"2019-6-25",time:"17:02",con:"#隐形守护者# 最近这个游戏有点火，肖途能不能不杀小学妹啊，快哭了？？？",con_image:"2.png",p:10,d:10,z:10},
    {image:"1.jpg",name:"康立言菜弟",day:"2019-6-25",time:"17:02",con:"#隐形守护者# 最近这个游戏有点火，肖途能不能不杀小学妹啊，快哭了？？？",con_image:"2.png",p:10,d:10,z:10},
    {image:"1.jpg",name:"康立言菜弟",day:"2019-6-25",time:"17:02",con:"#隐形守护者# 最近这个游戏有点火，肖途能不能不杀小学妹啊，快哭了？？？",con_image:"2.png",p:10,d:10,z:10}
  ];*/

    export default {
      name: "PersonalHome",
      data()
      {
        return{
          userName:'',
          imageUrl: '',
          message: '',
          number:data,
          contents:content,

          circleUrl: global.photo,
          hasNotLogin:hasNotLogin,
          myId:global.userId,
          myName:global.nametent,

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

       //this.imageUrl='http://query.liublack.cn/qs/contents/1077770518.jpg'
        this.$axios(
          {
            method:'post',
            url:global.host+"/ReturnQuestion",
            params:{
              mail:global.email
            }
          }).then(res =>{
          console.info(res)
          for (var i = 0; i < res.data.question.length; i++) {
            var l = {
              //img: res.data.question[i].photo,
              name: this.userName,
              dateTime: util.formatTime(res.data.question[i].time),
              con: decodeURI(res.data.question[i].content),
              //con_image:res.data.question[i].con_image,
             // p:res.data.question[i].p,
             // d:res.data.question[i].d,
              //z:res.data.question[i].z,
            }
            content.push(l)
          }
        }).catch(e =>{
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
      destroyed(){
        var hasNotLogin = [1]
      },
      methods:{
        to_changeInfo(){
          this.$router.push({path:'/changeInfo'})
        },

        alterpsw(){
          this.$router.push({path:'/psw'})
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
    top:75px;
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

  .contain{
    padding-top: 80px;
    margin-left: 30%;
    width: 60%;
    height: auto;

  }

  .content{
    width: 80%;
    height: auto;
    margin-left: 180px;
  }

  .button{
    margin-left:10px;
    width:100px;
    font-size: 18px;
    border:1px solid #bd5151;
    cursor: pointer;
    background-color: white;
  }

  .avatar-uploader{
    border: 1px solid #000;
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
