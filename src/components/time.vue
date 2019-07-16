<template>
  <div class="tq">
  <div class="cque" v-for="item,index in queTime" tabindex="0">
    <h4 class="card-title">{{item.title}}</h4>
    <!--            <p class="card-text">{{item.content}}</p>-->
    <a href="#" class="card-link" :data-itemId="index" @click="toQuestionDetail">详情</a>
    <a href="#" class="card-link" :data-itemId="index" @click="toQuestionDetail">回答</a>
    <p class="card-text"><small class="text-muted">{{item.author}} 发表于 {{item.time}}</small></p>
  </div>
  </div>
</template>

<script>
  import util from '../util.js'
  import global from './global.vue'
  let queTime = []
    export default {
        name: "tq",
      data(){
        return{
          queTime:queTime,
        }
      },
      created() {
        this.$axios(
          {
            method:'get',
            url:'http://query.liublack.cn/qs'+'/main',
            params:{
              action:"select",
              entity:"Question",
              QuestionType: encodeURI("热帖"),
              index: 0,
              num: 50,
              orderBy:'time'
            }
          })
          .then(res =>{
            console.log(res)
            console.log()
            for(let i = 0; i < res.data.length; i++)
            {
              var l = {
                title: res.data[i].title,
                content:res.data[i].content,
                author:res.data[i].name,
                time:util.formatTime(res.data[i].time),
                questionId: res.data[i].id,
                userId: res.data[i].userId
              }
              queTime.push(l)
            }
          })
      },
      destroyed(){
        console.log("QueTime has been destroyed")
        queTime.splice(0,queTime.length)
      },
      methods:{
        toQuestionDetail:function (e){

          if(global.userId==-1){
            this.$confirm('查看问题详情需要您登陆, 是否继续?', '提示', {
              confirmButtonText: '前往登陆',
              cancelButtonText: '否',
              type: 'warning'
            }).then(() => {
              this.$router.push({
                path: '/Login',
                query: {
                }
              })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消登陆'
              });
            });
          }
          else {
            var i = e.target.getAttribute('data-itemId')
            console.log(e.target.getAttribute('data-itemId'))
            this.$router.push({
              path: '/QuestionDetail',
              query: {
                questionId: queTime[i].questionId,
                userId: queTime[i].userId
              }
            })
          }
        },
      }
    }
</script>

<style scoped>
  .cque{
    border:1px solid #dee2e6;
    padding:3%;
    margin: 5px 5px 5px 0px ;
    background: #FFFFFF;
    outline-color: #bd5151;
    /* border-color: rgba(189,81,81,.8);*/
    box-shadow: 0 0 8px rgba(181, 185, 189, 0.6);
  }
  .card-link{
    color: #bd5151;
    border: none;
  }
  .card-title {
    margin-bottom: .75rem;
  }
  h4{
    font-size: 1.5rem;
    font-family: inherit;
    font-weight: 500;
    line-height: 1.2;
    color: inherit;
    margin-top: 0;
    display: block;
    -webkit-margin-before: 1.33em;
    -webkit-margin-after: 1.33em;
    -webkit-margin-start: 0px;
    -webkit-margin-end: 0px;
  }
</style>
