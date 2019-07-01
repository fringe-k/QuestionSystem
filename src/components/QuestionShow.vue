<template>
  <div id="body">
    <div class="top">
      <ul>
        <li class="link01">Q/A SYSTEM</li>
        <li><a href="#" id="link03"><i class="iconfont">&#xe625;</i>&nbsp&nbsp主页</a></li>
        <li class="link02"><a href="#"><i class="iconfont">&#xe7bf;</i>&nbsp&nbsp提问</a></li>
        <li class="link02">
          <a href="#"><i class="iconfont">&#xe627;</i>&nbsp&nbsp社区</a>
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
    <!--导航栏end-->

    <div id="classList" class="listContainer" >
      <a   id="chosen" class="" >
        &ensp;热点
        <span class="arrow-right"></span>
      </a>
      <li v-for="item,index in classList" class="ui-link" @click="chose" :data-item="index">
        <i class="iconfont " style=" color:#bd5151;font-size:30px;">&{{item.icon}};</i>
        &ensp;{{item.name}}
        <span class="arrow-right"></span>
      </li>
    </div>
    <!--分类栏 end-->
    <div id ="mainContent" class="questionBox">
      <p class="show-headline" >
         <span class="t1">主题</span>
         <span class="t2">提问者</span>
         <span class="t3">回复/浏览</span>
      </p>
      <!--标题栏end-->
        <li v-for="question,index in questionList" class="question">
          <div class="titleBox">
            <span @click="toQuestionDetail" class="toQuestion" >{{question.theme}}</span>
          </div>
          <div class="authorBox">
               <span class="alink"  >{{question.Questioner}}</span>
               <br>
               <span style="color:#808080;cursor: initial;height: 20px;font-size:18px;">{{question.date}}</span>
          </div>
          <span  class="ansourBox" style="font-size: 20px;">{{question.answer}}&nbsp;/&nbsp;{{question.view}}</span>
        </li>
      </div>
    </div>
     <!--问题栏end-->

</template>

<script>
  import global from './global.vue'
  var that=this
  var message=""
  var questionList = [
  ]
  var classList=[]
  export default {
    name: 'QuestionShow',
    data()
    {
      return{
        message: '',
        classList:classList,
        questionList:questionList,
      }
    },
   created(){
      this.$axios(
        {
          method:'get',
          url:global.host+'/test/getQuestionByType',
          params:{
            questionType:encodeURI("科学"),
            index:"0"
          }
        }).then(res =>{
        console.log(res)
      }).catch(e =>{
        console.log(e)
      })
    },
    methods: {
      toQuestionDetail:function (e) {
        this.$router.push({path:'/QuestionDetail'})
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

  #body{
    width: 100%;
    height: 100%;

  }
  .listContainer{
    margin-top:100px;
    margin-left:20px;
    width:200px;
    height:500px;
    background-color:white;
    text-align: center;
    display:inline-block;
    line-height: 60px;
    font-weight:500;
    border:1px solid #bd5151;
    position:fixed;
  }
  #chosen{
    margin: 0 auto;
    width:80%;
    display:block;
    font-family:Roboto;
    color:#bd5151;
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
    box-shadow:#bd5151 0px 0px 10px;
  }
  .questionBox{
    width:80%;
    height:auto;
    margin-top:80px;
    margin-left:250px;
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
    -webkit-margin-end: 00px;
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
    color:#bd5151;
    box-shadow:#bd5151 0px 0px 10px;
  }

  .toQuestion{
    text-decoration: none;
    color: #555;
    cursor:pointer;
  }
  .toQuestion:hover{
    text-decoration:underline;
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



</style>
