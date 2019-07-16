<template>
  <div id="body">
    <div class="top">
      <ul class="nav" style="padding-left: 6%;">
        <li class="link01"> Q/A SYSTEM</li>
        <li class="nav-item">
          <a class="nav-link" href="#"><i class="iconfont">&#xe625;</i>&nbsp&nbsp主页</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#"><i class="iconfont">&#xe7bf;</i>&nbsp&nbsp问题</a>
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
          <button class="triggerBtn"><li><a href="#" data-toggle="tooltip" data-placement="bottom" title="登录"><i class="iconfont">&#xe601;</i></a></li></button>
          <button class="triggerBtn"><li><a href="#" data-toggle="tooltip" data-placement="bottom" title="个人中心"><i class="iconfont">&#xe601;dsgrgrg</i></a></li></button>
        </div>
      </ul>
    </div>
    <!--导航栏end-->

    <div id="classList" class="listContainer" >
      <li v-for="item,index in leftList" :class="{'ui-link':1,'chosen':leftChosen[index]==1}" @click="chose" :data-item="index">
        {{item}}
      </li>
    </div>
    <!--分类栏 end-->
    <div id ="mainContent" class="contentBox">
      <el-tag
        :key="tag"
        v-for="tag in labelList"
        closable
        :disable-transitions="false"
        @close="handleClose(tag)">
        #{{tag}}
      </el-tag>
      <el-input
        class="input-new-tag"
        v-if="inputVisible"
        v-model="inputValue"
        ref="saveTagInput"
        size="small"
        @keyup.enter.native="$event.target.blur"
        @blur="handleInputConfirm"
      >
      </el-input>
      <el-button v-else class="button-new-tag" size="small" @click="showInput">+添加新类别</el-button>

    </div>
    <!--内容显示栏end-->
  </div>


</template>

<script>
  import global from './global.vue'
  var that=this
  var leftList=["用户管理","类别管理","标签管理"];
  var labelList=[]
  var leftChosen=[0,0,1]
  var hasNotLogin = [1]
  export default {
    data() {
      return {
        leftList:leftList,
        dynamicTags: ['标签一', '标签二', '标签三'],
        inputVisible: false,
        labelList:labelList,
        inputValue: '',
        leftChosen:leftChosen,
        circleUrl: global.photo,
        hasNotLogin:hasNotLogin,
        myId:global.userId,
        myName:global.name
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
      console.log("labelcreated在执行")
      this.$axios.get(global.host + '/admin',
        {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          params: {
            action: "select",
            entity: "Label",
          }
        })
        .then(function (response) {
          for (var i = 0; i < response.data.length; i++) {
            labelList.push(response.data[i].name)
          }
          console.log(labelList)
        })

    },
    destroyed(){
      labelList=[]
      console.log("labeldestroyed在执行")
      var hasNotLogin = [1]
    },
    methods:
      {
        chose:function(e){
          var i=e.target.getAttribute('data-item')
          console.log(e.target.getAttribute('data-item'))
          if(i==0){
            this.$router.push({path:'/OperatorUser'})
          }
          if(i==1){
            this.$router.push({path:'/OperatorClass'})
          }
        },
        handleClose(tag) {
          console.log(tag)
          this.labelList.splice(this.labelList.indexOf(tag), 1);
          this.$axios.get(global.host + '/admin',
            {
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              },
              params: {
                action: "delete",
                entity: "Label",
                name:encodeURI(tag)
              }
            })
            .then(function (response) {
              console.log(response)
            })
        },

        showInput() {
          this.inputVisible = true;
          this.$nextTick(_ => {
            this.$refs.saveTagInput.$refs.input.focus();
          });
        },

        handleInputConfirm() {
          let inputValue = this.inputValue;
          if (inputValue) {
            this.labelList.push(inputValue);
          }
          this.inputVisible = false;

          this.$axios.get(global.host + '/admin',
            {
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              },
              params: {
                action: "insert",
                entity: "Label",
                name:encodeURI(inputValue)
              }
            })
            .then(function (response) {
              console.log(response)
            })
          this.inputValue = '';
        },
        toLogin:function(){
          this.$confirm('是否登陆?', '提示', {
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
        },
        toPsw:function () {
          this.$router.push({
            path: '/psw',
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
    box-shadow:#bd5151 0px 0px 10px;
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

  #classList li:hover
  {
    background-color: #f1f1f1;
    z-index:100;
    color:white;

  }
  .el-tag + .el-tag {
    margin-left: 10px;
  }
  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
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
  .testShow{

  }
  .chosen{
    color:red;
  }

</style>
