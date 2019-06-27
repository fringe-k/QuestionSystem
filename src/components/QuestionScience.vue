<template>
  <div id="body">
    <div class="top">
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
          <button id="triggerBtn"><li><a href="#"><i class="iconfont">&#xe601;</i></a></li></button>

        </div>
      </ul>
    </div>
    <!--导航栏 end-->
    <div id="classList" class="listContainer" >
      <a id="chosen" class="" href="">
        <i class="iconfont " style=" color:red;font-size:30px;">&#xe9b0;</i>
        &ensp;科学
        <span class="arrow-right"></span>
      </a>
      <a class="ui-link"  href="">
        <i class="iconfont ">&#xe630;</i>
        &ensp;数码
        <span class="arrow-right"></span>
      </a>
      <a class="ui-link" href="">
        <i class="iconfont ">&#xe617;</i>
        &ensp;体育
        <span class="arrow-right"></span>
      </a>
      <a class="ui-link" href=""> <i class="iconfont ">&#xe61d;</i>
        &ensp;影视
        <span class="arrow-right"></span>
      </a>
      <a class="ui-link"  href=""> <i class="iconfont ">&#xe600;</i>
        &ensp;时尚
        <span class="arrow-right"></span>
      </a>
      <a class="ui-link" href=""> <i class="iconfont ">&#xe645;</i>
        &ensp;餐饮
        <span class="arrow-right"></span>
      </a>
      <a class="ui-link" href=""> <i class="iconfont ">&#xe60a;</i>
        &ensp;娱乐
        <span class="arrow-right"></span>
      </a>
    </div>
    <!--分类栏 end-->
    <div id ="mainContent" class="questionBox">
      <p class="show-headline" >
         <span class="t1">主题</span>
         <span class="t2">提问者</span>
         <span class="t3">回复/浏览</span>
      </p>
      <!--标题栏end-->
        <li v-for="question,index in questions" class="question">
          <div class="titleBox"><a class="toQuestion" href="">{{question.theme}}</a></div>
          <div class="authorBox">
               <a class="alink" v-on:mouseenter="enter(index)"  href="">{{question.Questioner}}</a>
               <br>
               <a style="color:#808080;cursor: initial;height: 20px;font-size:18px;">{{question.date}}</a>
          </div>
          <span class="ansourBox" style="font-size: 20px;">{{question.answer}}&nbsp;/&nbsp;{{question.view}}</span>
        </li>
      </div>
    </div>
     <!--问题栏end-->

</template>

<script>
  import global from './global.vue'

  var message="";
  var data = [
    {theme:"这该怎么办",Questioner:"wlzdd",date:"2019-6-18",view:158,answer:20},
    {theme:"这把枪厉害吗，比wlz厉害吧",Questioner:"wlzdd",date:"2019-6-18",view:158,answer:20},
    {theme:"这把枪厉害吗，比wlz厉害吧",Questioner:"wlzdd",date:"2019-6-18",view:158,answer:20},
    {theme:"这把枪厉害吗，比wlz厉害吧",Questioner:"wlzdd",date:"2019-6-18",view:158,answer:20},
  ];

  export default {
    name: 'QuestionScience',
    data()
    {
      return{
        message: '',
        questions:data,
      }
    },
    methods:{
      enter(index){
        console.log(global)
        console.log(index);
        this.$axios.get(global.host+'/test/hello')
          .then(function (response) {
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
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

  #body{
    width: 100%;
    height: 100%;

  }
  .listContainer{
    margin-top:10px;
    margin-left:20px;
    width:200px;
    height:500px;
    background-color:white;
    text-align: center;
    display:inline-block;
    line-height: 60px;
    font-weight:500;
    border:1px solid red;
  }
  #chosen{
    margin: 0 auto;
    width:80%;
    display:block;
    font-family:Roboto;
    color:red;
    font-size:30px;
    text-decoration: none;
    border-bottom: 2px solid rgba(187,187,187,1);
  }
  .ui-link{
    margin: 0 auto;
    width:80%;
    display:block;
    font-size:20px;
    font-family:Roboto;
    color: #888;
    text-decoration: none;
    border-bottom: 2px solid rgba(187,187,187,1);
    color:rgba(0,0,0,0.87);

  }
  #classList a:hover
  {
    background-color: #f1f1f1;
    z-index:100;
    color:white;
    box-shadow:red 0px 0px 10px;
  }
  .questionBox{
    width:80%;
    height:auto;
    margin-top:20px;
    margin-left:50px;
    background-color:white;
    display:inline-block;
    line-height: 50px;
    vertical-align:top;
  }

  .show-headline{
    width: 100%;
    height: 50px;
    line-height: 50px;
    background-color: #edf1f4;
    color: #555;
    border-bottom: 2px solid rgba(204,82,82,1);
    font-weight: 700;
    margin:0;

  }
  span{
    display:inline-block;
    margin:0;
  }
  p {
    display: block;
    -webkit-margin-before: 1em;
    -webkit-margin-after: 1em;
    -webkit-margin-start: 0px;
    -webkit-margin-end: 0px;
  }
  .t1 {
    width:60%;
    margin-left: 20px;
  }
  .t2{
    width: 20%;
  }
  .t3{
    width: 10%;
    text-align: center;
  }
  .titleBox{
    width:60%;
    height:auto;
    vertical-align: middle;
    line-height: 1.5;
    display:inline-block;
    margin-left: 20px;
    font-size: 20px;
  }
  .authorBox{
    display:inline-block;
    height:auto;
    width:20%;
    line-height: 1.5;
    vertical-align: middle;
  }
  .ansourBox{
    vertical-align: middle;
    text-align:center;
    width:10%;
  }
  .alink{
    color: #005eac;
    text-align: left;
    cursor: pointer;
    height: 20px;
    font-size:18px;
    text-decoration: none;
  }
  .question{
    list-style: none;
    border-bottom: 2px solid #ccc;
    line-height:100px;
  }
  .question:hover{
    background-color: #f1f1f1;
  }
  .arrow-right {
    width: 7px;
    height: 7px;
    border-top: 2px solid rgba(187,187,187,1);
    border-right: 2px solid rgba(187,187,187,1);
    transform: rotate(45deg);
    text-align: right;
    margin-right:5px;
  }
  alink:hover
  {
    background-color: #005eac;
    color:white;
    z-index:100;
    color:red;
    box-shadow:red 0px 0px 10px;
  }

  .toQuestion{
    text-decoration: none;
    color: #555;
  }
  .toQuestion:hover{
    text-decoration:underline;
  }



</style>
