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
      <span class="title">{{QuestionDetail[0].title}}</span>
      <div class="labelBox">
          <span v-for="label in QuestionDetail[0].labels">
             #{{label.name}}&ensp;
          </span>
      </div>
    </div>
    <div class="content">
      <div class="contentBox">
         <span style="white-space: pre-line">{{QuestionDetail[0].content}}</span><br>
         <div class="bottomLine">
            <span style="color:rgba(145,139,139,1);font-size:20px;">{{QuestionDetail[0].date}}</span>
            <span style="color:blue;font-size:20px;margin-left:70%;cursor:pointer" @click="toAnswer">我要回答</span>
         </div>
      </div>
      <!--问题展示end-->
      <div v-if="isAnswer.key" style="margin-top:50px;">
        <inputfield ref="input" class="inputfield"></inputfield>
        <button @click="upload">提交</button>
      </div>
      <div class="question-answer">
        <div class="answer-header">
          <span>这些人回答了</span>
        </div>
        <div v-for="answer,index in answerList" class="answerDiv">
          <el-avatar style="display:inline-block;width:70px;height:70px;fit:fill;vertical-align: top" size="large" :src="circleUrl"></el-avatar>
          <div style="display:inline-block;width:620px;">
            <div class="answerInfo">
              <el-tooltip content="点击查看用户信息" placement="bottom" effect="light">
                   <p style="cursor: pointer;width:150px;" >{{answer.answerer}}</p>
              </el-tooltip>
              <p style="color:gray;width:200px;">{{answer.dateTime}}</p>
              <p style="margin-left:75px;cursor: pointer;width:100px;" >我要赞赏</p>
              <p style="margin-left:10px;width:60px;">{{index}}楼</p>
            </div>
            <span class="answerContent">
                  {{answer.content}}
            </span>

            <div style="display:block;margin-top:20px;font-size:18px;">
              <span v-if="!answer.commentShow" style="cursor:pointer;color:#bd5151" @click="showCommentOf" :data-item="index">点击查看评论>></span>
              <span v-else style="cursor:pointer;color:#bd5151" @click="showCommentOf" :data-item="index">点击收起评论</span>
              <span v-if="!answer.inputShow" style="cursor:pointer;margin-left:50px;color:skyblue;" :data-item="index" @click="toComment" >我要评论</span>
              <div v-else style="cursor:pointer;margin-left:50px;color:orangered;"  >
                  <span :data-item="index" @click="uploadComment">提交</span>
                  <span :data-item="index" @click="cancelComment">取消</span>
              </div>
            </div>
            <el-input v-if="answer.inputShow"
              type="textarea"
              :autosize="{ minRows: 2, maxRows: 4}"
              placeholder="请输入内容"
              v-model="answer.commentContent" >
            </el-input>

            <div class="showComments" v-if="answer.commentShow">
              <li v-for="comment in commentList" class="commentList" style="word-break: break-word">
                <span style="color:blue;cursor:pointer">{{comment.user}}</span><span>:{{comment.content}}</span>
              </li>
            </div>
          </div>
        </div>
      </div>
      <!--回复展示end-->
    </div>
    <div class="peInfo">

    </div>


  </div>

</template>

<script>
  import global from './global.vue'
  import util from '@/util.js'
  import Input from './InputField.vue'
  /*var data =
    {title:"这该怎么办",
      content:"情况是这样的，我和女朋友在一起快3年了，就是那种大学到工作的校园恋情。所以我的确是很珍惜这段感情。\n" +
        "篮球，是我从初中到大学唯一一个没有改变过的爱好。喜欢独行侠也很长时间，最喜欢的球员是077，也一直很想去美国看一次他们的现场。工作了1年多，自己也攒了一点钱，去美国来回的机票钱反正是够了。",
      Questioner:"wlzdd",
      date:"2019-6-18",
      labels:["王者荣耀","游戏"]}

  var PeInfo={img:"../assets/image/test.jpg",Questioner:"wlzdd",numOfQuestion:5,numOfAnswer:5,points:5}
  var answerList=[{img:"../assets/image/test.jpg",answerer:"wlzdd",dateTime:"2019-6-18 7:54",content:"撒的都",answerId:5},
                  {img:"../assets/image/test.jpg",answerer:"wlzdd",dateTime:"2019-6-18 7:54",content:"撒的是大家把第三比第三季度那是你你删的暖单独insad那第四年迪士尼那第四那第四厺都",answerId:5},
    {img:"../assets/image/test.jpg",answerer:"wlzdd",dateTime:"2019-6-18 7:54",content:"撒的都",answerId:5},
    {img:"../assets/image/test.jpg",answerer:"wlzdd",dateTime:"2019-6-18 7:54",content:"撒的都",answerId:5},
    {img:"../assets/image/test.jpg",answerer:"wlzdd",dateTime:"2019-6-18 7:54",content:"撒的都",answerId:5},
    {img:"../assets/image/test.jpg",answerer:"wlzdd",dateTime:"2019-6-18 7:54",content:"撒的都",answerId:5},
    {img:"../assets/image/test.jpg",answerer:"wlzdd",dateTime:"2019-6-18 7:54",content:"撒的都",answerId:5},]
  var commitList=[{user:"asd",commit:"dasd sad dsa sad s "},
    {user:"asd",commit:"dasd sad dsa sad s "},
    {user:"asd",commit:"dasd sad dsa sad s "}
    ]*/
  var QuestionDetail =[]
  var PeInfo=[]
  var answerList=[]
  var commentList=[]
  var isAnswer={key:false}
    export default {
      name: "QuestionDetail",
      components: {"inputfield": Input},
      data() {
        return {
          QuestionDetail: QuestionDetail,
          peInfo: PeInfo,
          answerList: answerList,
          circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
          commentList: commentList,
          isAnswer: isAnswer,
          textarea2:"",
        }
      },
      created() {
        console.log(this.$route.query)
        console.log(typeof (this.$route.query.questionId))
        this.$axios.get(global.host + '/test/detail',
          {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            },
            params: {
              action: "select",
              entity: "Question",
              questionId: this.$route.query.questionId
            }
          })
          .then(function (response) {
            console.log(response)
            var l = {
              title: response.data.title,
              content: response.data.content,
              Questioner: response.data.user,
              date: util.formatDate(response.data.date),
              labels: JSON.parse(response.data.label)
            }
            QuestionDetail.push(l)
            console.log(QuestionDetail.Questioner)
          });
        this.$axios.get(global.host + '/test/detail',
          {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            },
            params: {
              action: "select",
              entity: "Answer",
              questionId: this.$route.query.questionId,
              index: 0,
              num: 50
            }
          })
          .then(function (response) {
            for (var i = 0; i < response.data.length; i++) {
              var l = {
                img: response.data[i].photo,
                answerer: response.data[i].name,
                dateTime: util.formatTime(response.data[i].time),
                content: response.data[i].content,
                answerId: response.data[i].id,
                commentShow: false,
                inputShow:false,
                commentContent:""
              }
              answerList.push(l)
            }
          });

      },
      methods: {
        showCommentOf: function (e) {
          var i = e.target.getAttribute('data-item')
          console.log(e.target.getAttribute('data-item'))
          if (answerList[i].commentShow) {
            answerList[i].commentShow = false
            commentList = []
          } else {
            answerList[i].commentShow = true
            this.$axios.get(global.host + '/test/detail',
              {
                headers: {
                  'Content-Type': 'application/x-www-form-urlencoded'
                },
                params: {
                  action: "select",
                  entity: "Comment",
                  answerId: answerList[i].answerId,
                  index: 0,
                  num: 20
                }
              })
              .then(function (response) {
                for (var i = 0; i < response.data.length; i++) {
                  var l = {
                    user: response.data[i].name,
                    content: response.data[i].content
                  }
                  commentList.push(l)
                }
              });


          }

        },
        toComment: function (e) {
          var i = e.target.getAttribute('data-item')
          answerList[i].inputShow=true

        },
        toAnswer: function (e) {
          isAnswer.key = true
          console.log(isAnswer)
        },
        upload: function (e) {
          console.log(this.$refs.input)
          var i=document.getElementById("content").value
          console.log(document.getElementById("content").value)
          this.$confirm('确认提交?', '提示', {
           confirmButtonText: '确定',
           cancelButtonText: '取消',
           type: 'warning'
         }).then(() => {
           this.$axios({
               method:'post',
               url:global.host + '/test/detail',
               params: {
                 action: "insert",
                 entity: "Answer",
                 questionId:this.$route.query.questionId,
                 mail:global.email,
                 content:encodeURI(i)
               }
             })
             .then(function (response) {
               location.reload()
             })
       });
        },
        uploadComment:function(e){
          var u = e.target.getAttribute('data-item')
         console.log(answerList[u].commentContent)
          this.$confirm('确认提交评论?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$axios({
              method:'post',
              url:global.host + '/test/detail',
              params: {
                action: "insert",
                entity: "Comment",
                answerId:answerList[u].answerId,
                mail:global.email,
                content:encodeURI(answerList[u].commentContent)
              }
            })
              .then(function (response) {
                answerList[u].commentContent=''
                answerList[u].inputShow=false
                location.reload()

              })
          });
        },
         cancelComment:function(e){
           var i = e.target.getAttribute('data-item')
           answerList[i].inputShow=false
           answerList[i].commentContent=''
         }
      }
    }
</script>

<style scoped>
  @import "http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css";
  @import "../components/css/buttonBox.css";
  @import "../assets/icon/iconfont.css";
  @import "../components/css/searchBar.css";
  @import "../components/css/top.css";
  .top{
    position:fixed;
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
    position:fixed;
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
    padding-bottom:50px;
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
    padding-bottom: 30px;
    border-bottom: 1px solid rgba(187,187,187,1);
  }
  .answerInfo answerContent{
    display:block;
    margin:20px;
  }
  .answerInfo{
    font-size:18px;
    border-bottom: 1px dashed rgba(187,187,187,1);
  }
  .answerContent{
    margin-top:20px;
    margin-bottom:20px;
    font-size:20px;
  }
  p{
    display:inline-block;
    margin-top:0;
    margin-bottom:0;
  }
  .commentList{
    list-style: none;
    text-decoration: none;
  }
  .showComments{
    background-color: rgba(246,246,246,0.5);
    font-size:20px;
    height:auto;
    width:600px;
    padding-bottom:30px;
  }
  .inputfield{
    width:500px;
    height:500px;
    z-index: 1000;
    postition:fixed;
    top:200px;
  }


</style>
