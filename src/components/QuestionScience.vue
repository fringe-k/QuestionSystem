<template>
  <div id="body">
    <div style="background-color: red;height:20%;">导航栏</div>
    <!--导航栏 end-->
    <div id="classList" class="listContainer" >
      <a class="ui-link" href="">科学</a>
      <a class="ui-link"  href="">数码</a>
      <a class="ui-link" href="">体育</a>
      <a class="ui-link" href="">影视</a>
      <a class="ui-link"  href="">时尚</a>
      <a class="ui-link" href="">餐饮</a>
      <a class="ui-link" href="">娱乐</a>
    </div>
    <!--分类栏 end-->
    <div id ="mainContent" class="question">
      <tr v-for="question,index in questions">
        <td>{{index+1}}</td>
        <td>{{question.theme}}</td>
        <td>{{question.Questioner}}</td>
        <td>{{question.date}}</td>
        <td>{{question.view}}</td>
        <td>{{question.answer}}</td>
      </tr>
    </div>
  </div>
</template>

<script>
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
    }
  }
</script>

<style scoped>
  #body{
    width: 100%;
    height: 100%;
    background-color: rgba(221, 221, 221, 0.5);
  }
  .listContainer{
    margin-top:10px;
    margin-left:10px;
    width:165px;
    height:400px;
    background-color:white;
    text-align: center;
    display:inline-block;
    line-height: 50px;

  }
  .ui-link{
    margin: 0 auto;
    width:80%;
    display:block;
    font-size:20px;
    font-family:Roboto;
    color: #888;
    text-decoration: none;
    border-bottom: 1px solid rgba(187,187,187,1);
    color:rgba(0,0,0,0.87);
  }
  a:hover
  {
    background-color: #f1f1f1;
  }
  .question{
    width:80%;
    height:400px;
    margin-top:50px;
    margin-left:50px;
    background-color:white;
    text-align: center;
    display:inline-block;
    line-height: 50px
  }
</style>
