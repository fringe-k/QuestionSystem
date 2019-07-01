<template>
  <div>
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
    <div class="titleBox">
      <span class="title">{{QuestionDetail.title}}</span>
      <div class="labelBox">
          <span v-for="label in QuestionDetail.labels">
             #{{label}}&ensp;
          </span>
      </div>
    </div>
    <div class="content">
      <div class="contentBox">
         <span>{{QuestionDetail.content}}</span><br>
         <div class="bottomLine">
            <span style="color:rgba(145,139,139,1);font-size:20px;">{{QuestionDetail.date}}</span>
            <span style="color:blue;font-size:20px;margin-left:70%;" @click="toAnswer">我要回答</span>
         </div>
      </div>
      <!--问题展示end-->
      <div class="question-answer">
        <div class="answer-header">
          <span>这些人回答了</span>
        </div>
        <div v-for="answer,index in answerList" class="answerDiv">
          <el-avatar style="display:inline-block;width:70px;height:70px;fit:fill;" size="large" :src="circleUrl"></el-avatar>
          <div style="display:inline-block">
            <div class="answerInfo">

            </div>
            <span class="answerContent">
                  {{answer.content}}
            </span>
          </div>
        </div>
      </div>
      <!--回复展示end-->
    </div>
    <div class="peInfo"></div>

  </div>

</template>

<script>
  var data =
    {title:"这该怎么办",
      content:"情况是这样的，我和女朋友在一起快3年了，就是那种大学到工作的校园恋情。所以我的确是很珍惜这段感情。\n" +
        "篮球，是我从初中到大学唯一一个没有改变过的爱好。喜欢独行侠也很长时间，最喜欢的球员是077，也一直很想去美国看一次他们的现场。工作了1年多，自己也攒了一点钱，去美国来回的机票钱反正是够了。",
      Questioner:"wlzdd",
      date:"2019-6-18",
      labels:["王者荣耀","游戏"]}

  var PeInfo={img:"../assets/image/test.jpg",Questioner:"wlzdd",numOfQuestion:5,numOfAnswer:5,points:5}
  var answerList=[{img:"../assets/image/test.jpg",answerer:"wlzdd",date:"2019-6-18 7:54",content:"撒的都"},
                  {img:"../assets/image/test.jpg",answerer:"wlzdd",date:"2019-6-18 7:54",content:"撒的都"}]
    export default {
        name: "QuestionDetail",
      data() {
        return {
          QuestionDetail: data,
          peInfo: PeInfo,
          answerList:answerList,
          circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
        }
      },
      methods:{
        created:function(){
          console.log("I AM IN");
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
  .top{
    position:absolute;
    border-bottom: 1px solid #bd5151;
  }
  .titleBox{
    width:100%;
    height:150px;
    background-color: white;
    margin-top:75px;
    vertical-align: middle;
  }
  .title{
    margin-left:250px;
    font-size:36px;
    display:block;
    padding-top:30px;
  }
  .labelBox{
    margin-left:250px;
    display:inline-block;
    font-size:20px;
    margin-top:20px;
    color:rgba(11,51,232,1);
  }
  .content{
    display:inline-block;
    margin-left: 250px;
    width:750px;
    height:auto;
    font-size:25px;
    line-height:40px;
    vertical-align: top;
  }
  .contentBox{
    display:block;
    background-color: white;
    margin-top:20px;
    width:700px;
    height:auto;
    font-size:20px;
    padding:30px 30px 20px 30px;
    line-height:40px;
    vertical-align: top;
  }
  .peInfo{
    display:inline-block;
    background-color: white;
    width:500px;
    height:500px;
    margin-left:50px;
    vertical-align: top;
    margin-top:20px;
  }
  .bottomLine{
    margin-top:50px;
  }
  .question-answer{
    margin-top:30px;
    display:block;
    background-color: white;
    border:1px solid rgba(51,143,211,1);
    width:760px;
    height:auto;
  }
  .answer-header{
    height:50px;
    border-bottom:1px solid rgba(187,187,187,1) ;
    margin-left:30px;
    margin-right: 30px;
    color:#1C56E3 ;
    font-size:24px;
    vertical-align: middle;
    padding-top:20px;
  }
  .answerDiv{
    margin-top:20px;
    margin-left:30px;
    margin-right:30px;
    border-bottom: 1px solid rgba(187,187,187,1);
  }
  .answerInfo answerContent{
    display:block;
    margin:20px;

  }
</style>
