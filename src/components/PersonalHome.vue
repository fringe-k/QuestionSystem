<template>
  <div id="body">
    <div style="background-color: red;height:10%;">导航栏</div>
    <!--导航栏 end-->

    <div class="person">

      <div>
        <img class="cycle" src="../assets/1.jpg" style="float:left"/>
      </div>

      <div class="name">
          <h3>&nbsp;康立言菜弟</h3>
      </div>
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
    <div v-for="content in contents">
      <div class="contain" >
        <div>
          <img class="cycle" src="../assets/1.jpg" style="float:left;height:100px;width: 100px;padding-top: 12px"/>
        </div>

        <div class="name" style="padding-top: 0px">
          <h6>&nbsp;&nbsp;&nbsp;{{content.name}}</h6>
        </div>

        <div style="margin-top: 0px">
          <spanp>{{content.day}}</spanp>
          <span>{{content.time}}</span>
        </div>

        <div class="content">
          <p>{{content.con}}</p>
        </div>

        <div>
          <img src="../assets/2.png" style="width: 50%;height: 30%;margin-left: 180px"/>
        </div>

        <div>
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

    <div id="classList" class="listContainer" >
      <br>
      <a class="ui-link" href="">个人空间</a>
      <br>
      <br>
      <a class="ui-link"  href="">我的关注</a>
      <br>
      <br>
      <a class="ui-link" href="">我的收藏</a>
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
          contents:content
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

  .person{
    height: 150px;
    margin-left: 20px;
    margin-right: 20px;
    margin-top: 20px;
    background-color: white;
  }

  .cycle{
    width: 140px;
    height: 140px;
    border-radius: 50%;
    margin-left: 8%;
  }

  .name{
    font-size: 25px;
    text-align:left;
    padding-top: 1px;
  }

  .study_info{
    position: absolute;
    top:150px;
    right: 10%;
    min-width: 200px;
    text-align: right;
  }

  .listContainer{
    position: fixed;
    top:300px;
    left:12%;
    width:180px;
    height:450px;
    background-color:white;
    text-align: center;
    display:inline-block;
    line-height: 50px;
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
  }

  .contain{
    padding-top: 50px;
    margin-left: 30%;
    background-color:white;
    width: 60%;
    height: auto;
  }

  .content{
    width: 80%;
    height: auto;
    margin-left: 180px;
  }


</style>
