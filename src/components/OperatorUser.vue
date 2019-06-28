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
      <li v-for="item,index in leftList" class="ui-link">
        {{item}}
      </li>
    </div>
    <!--分类栏 end-->
    <div id ="mainContent" class="contentBox">
      <table align="center" >
        <tr class="firstLine">
          <td>用户名</td>
          <td>邮箱</td>
          <td>是否允许登录</td>
          <td>是否允许发言</td>
          <td>删除与提交</td>
        </tr>
        <tr v-for="item,index in userList" >
          <td>
            {{item.userName}}
          </td>
          <td>
            {{item.mail}}
          </td>
          <td>
            <select v-if="item.allowAdmin"   size="2" @change='getAdminValue' :data-itemId="index" v-model="item.allowAdmin2">
              <option >是</option>
              <option >否</option>

            </select>
            <select v-else   size="2" @change='getAdminValue' :data-itemId="index" v-model="item.allowAdmin2">
              <option >否</option>
              <option >是</option>

            </select>
          </td>
          <td>
            <select v-if="item.allowCommit" @change='getCommitValue' :data-itemId="index"  size="2" v-model="item.allowCommit2">
              <option >是</option>
              <option>否</option>
            </select>
            <select v-else  @change='getCommitValue' :data-itemId="index" size="2" v-model="item.allowCommit2">
              <option >否</option>
              <option>是</option>
            </select>
          </td>
          <td>
            <span :data-itemId="index" class="click" @click="deleteUser" style="color:red">删除</span>
            <span :data-itemId="index" class="click" @click="confirm">提交更改</span>
          </td>
        </tr>

      </table>
      </div>
      <!--内容显示栏end-->
  </div>


</template>

<script>
  import global from './global.vue'
  var that=this
  var message="";
  var isAllowAdmin=""
  var data = [
  ];
  var leftList=["用户管理","类别管理","标签管理"];
  var userList=[
                ]

  export default {
    name: 'Operator',
    data()
    {
      return{
        message: '',
        questions:data,
        leftList:leftList,
        userList:userList,
        isAllowAdmin
      }
    },
    created()
    {
      this.$axios.get(global.host + '/test/admin',
        {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          params: {
            action:"select",
            entity:"User",
          }
        })
        .then(function (response) {
          for (var i = 0; i < response.data.length; i++) {
            var l={
              userName:response.data[i].name,
              userId:response.data[i].id,
              mail:response.data[i].mail,
              allowAdmin:!response.data[i].cannotLogin,
              allowCommit:!response.data[i].cannotSpeak,
              allowAdmin2:!response.data[i].cannotLogin,
              allowCommit2:!response.data[i].cannotSpeak
            }
           userList.push(l)
          }
          console.log(userList)
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    methods: {

      test:function(event){
        console.log(that.router)
      },
      getAdminValue:function(e){
         var i=e.target.getAttribute('data-itemId')
        console.log(e.target.getAttribute('data-itemId'))
        console.log(2222)
        console.log(userList[i].allowAdmin2)


      },
      getCommitValue:function(e){
        console.log(e.target.getAttribute('data-itemId'))
        console.log(e.target.dataset)
      },
      deleteUser:function(e){
        var i=parseInt(e.target.getAttribute('data-itemId'))
       console.log(typeof (parseInt(i))+"-----"+i)
        this.$axios.get(global.host + '/test/admin',
          {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            },
            params: {
              action: "delete",
              entity: "User",
              id:userList[i].userId
            }
          })
          .then(function (response) {
            console.log(response)
             location.reload()
          })
          .catch(function (error) {
            console.log(error);
          });
      },
      confirm:function(e){
        console.log(e.target.getAttribute('data-itemId'))
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
    margin-top:100px;
    margin-left:20px;
    width:200px;
    height:500px;
    background-color:white;
    text-align: center;
    display:inline-block;
    line-height: 60px;
    font-weight:500;

    position:fixed;
  }

  .contentBox{
    width:80%;
    height:auto;
    margin-top:100px;
    margin-left:250px;
    background-color:white;
    display:inline-block;
    line-height: 50px;
    vertical-align:top;
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



  table tr td{
    border:1px solid gray;
    padding:10px;

  }
  table{
    border-collapse:collapse;
    width:100%;
    table-layout:fixed;
    word-wrap:break-word;
  }
  tr.firstLine{
    background-color: lightGray;
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
  #classList li:hover
  {
    background-color: #f1f1f1;
    z-index:100;
    color:white;
    box-shadow:#bd5151 0px 0px 10px;
  }
  .click:hover{
    cursor:pointer;
    text-decoration: underline;
  }

</style>
