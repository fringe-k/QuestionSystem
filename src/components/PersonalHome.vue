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
    <div v-for="content in contents" >
      <div class="contain" >
        <div>
          <img class="cycle" src="../assets/1.jpg" style="float:left;height:100px;width: 100px;padding-top: 12px"/>
        </div>

        <div class="name" style="padding-top: 0px">
          <h6>&nbsp;&nbsp;&nbsp;{{content.name}}</h6>
        </div>

        <div style="margin-top: 0px">
          <span>{{content.day}}</span>
          <span>{{content.time}}</span>
        </div>

        <div class="content">
          <p>{{content.con}}</p>
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
  var message="";
  var data = [
    {score:10,question:10,answer:20,follow:10,collect:10,fan:10},
  ];

  var content=[
    {image:"1.jpg",name:"康立言菜弟",day:"2019-6-25",time:"17:02",con:"#隐形守护者# 最近这个游戏有点火，肖途能不能不杀小学妹啊，快哭了？？？",con_image:"2.png",p:10,d:10,z:10},
    {image:"1.jpg",name:"康立言菜弟",day:"2019-6-25",time:"17:02",con:"#隐形守护者# 最近这个游戏有点火，肖途能不能不杀小学妹啊，快哭了？？？",con_image:"2.png",p:10,d:10,z:10},
    {image:"1.jpg",name:"康立言菜弟",day:"2019-6-25",time:"17:02",con:"#隐形守护者# 最近这个游戏有点火，肖途能不能不杀小学妹啊，快哭了？？？",con_image:"2.png",p:10,d:10,z:10}
  ];
    export default {
      name: "PersonalHome",
      data()
      {
        return{
          message: '',
          number:data,
          contents:content,
          bg: {
            backgroundImage: "url(" + require("../assets/login_bg.png") + ")",
            backgroundRepeat: "no-repeat",
            backgroundSize: "100% 100%",
          },
        }
      },

      methods:{
        to_changeInfo(){
          this.$router.push({path:'/changeInfo'})
        },

        alterpsw(){
          this.$router.push({path:'/psw'})
        },

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
    top:280px;
    left:12%;
    width:180px;
    height:580px;
    background-color:white;
    text-align: center;
    display:inline-block;
    line-height: 50px;
    border:1px solid #000
  }
  .ui-link{
    margin: 0 auto;
    width:80%;
    display:block;
    font-size:30px;
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
    border:1px solid #000;
    cursor: pointer
  }


</style>
