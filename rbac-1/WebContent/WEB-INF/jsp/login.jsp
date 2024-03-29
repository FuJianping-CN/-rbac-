<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/login.css">
	<style>

	</style>
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><a class="navbar-brand" href="index.html" style="font-size:32px;">RBAC权限考试管理系统</a></div>
        </div>
      </div>
    </nav>

    <div class="container">
<!-- action="doLogin" -->
      <form class="form-signin" role="form" id="loginForm" action="" method="post">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-user"></i> 用户登录</h2>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" id="username" name="userName" placeholder="请输入登录账号" autofocus>
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="password" class="form-control" id="password" name="password" placeholder="请输入登录密码" style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
<!-- 		  <div class="form-group has-success has-feedback"> -->
<!-- 			<select class="form-control" > -->
<!--                 <option value="member">会员</option> -->
<!--                 <option value="user">管理</option> -->
<!--             </select> -->
<!-- 		  </div> -->
        <div class="checkbox">
          <label>
<!--             <input type="checkbox" value="remember-me"> 记住我 -->
          </label>
          <br>
          <label>
<!--             忘记密码 -->
          </label>
          <label style="float:right">
            <a href="#">我要注册</a>
          </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
      </form>
    </div>
    <script src="jquery/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script>
    function dologin() {
         var username=$('#username').val();
         if(username==""){
        	 //alert("用户登录账号不能为空,请输入");
        	 layer.msg("用户登录账号不能为空,请输入",{timer:1000,icon:5,shift:6},function(){});
        	 return;
         }
         var password=$('#password').val();
         if(password==""){
        	// alert("用户登录密码不能为空,请输入");
        	 layer.msg("用户登录账号不能为空,请输入",{timer:1000,icon:5,shift:6},function(){});
        	 return;
         }
         //提交表单
        // $('#loginForm').submit();
         var loadingIndex=null;
         $.ajax({
        	 type: "POST",
        	 url: "user/doAjaxLogin",
        	 data: {"userName":username,
        		   "password":password
        		  },
              beforeSend: function(){
            	  loadingIndex= layer.msg("数据正在处理中,请输入",{icon:16});
              },
             success: function(result){  //接收返回数据
            	 //alert(result+"****")
            	 layer.close(loadingIndex);  //关闭层
            	 if(result.success){
            		 location.href="user/main";
            	 }else{
            		 layer.msg("账号或密码错误,或账户未被激活",{timer:1000,icon:5,shift:6},function(){});
            	 }
             }
         });
         
    }
    </script>
  </body>
</html>