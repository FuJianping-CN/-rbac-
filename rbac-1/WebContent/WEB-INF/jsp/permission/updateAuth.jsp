<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="utf-8">
<head>
<meta charset="GB18030">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" href="${PATH}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${PATH}/css/font-awesome.min.css">
<link rel="stylesheet" href="${PATH}/css/main.css">
<link rel="stylesheet" href="${PATH}/css/doc.min.css">
<style>
.tree li {
	list-style-type: none;
	cursor: pointer;
}
</style>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" style="font-size: 32px;" href="#">考试管理系统
						</a>
				</div>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li style="padding-top: 8px;">
						<div class="btn-group">
							<button type="button"
								class="btn btn-default btn-success dropdown-toggle"
								data-toggle="dropdown">
								<i class="glyphicon glyphicon-user"></i>${user.userName } <span
									class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#"><i class="glyphicon glyphicon-cog"></i>
										个人设置</a></li>
								<li><a href="#"><i class="glyphicon glyphicon-comment"></i>
										消息</a></li>
								<li class="divider"></li>
								<li><a href="index.html"><i
										class="glyphicon glyphicon-off"></i> 退出系统</a></li>
							</ul>
						</div>
					</li>
					<li style="margin-left: 10px; padding-top: 8px;">
						<button type="button" class="btn btn-default btn-danger">
							<span class="glyphicon glyphicon-question-sign"></span> 帮助
						</button>
					</li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="查询">
				</form>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<div class="tree">
					<%@include file="/WEB-INF/jsp/common/menu.jsp"%>
				</div>
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
					<li><a href="#">权限管理</a></li>
					<li><a href="#">权限维护</a></li>
					<li class="active">修改权限</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						表单数据
						<div style="float: right; cursor: pointer;" data-toggle="modal"
							data-target="#myModal">
							<i class="glyphicon glyphicon-question-sign"></i>
						</div>
					</div>
					<div class="panel-body">
						<form role="form" id="addForm">
							<div class="form-group">
								<label for="exampleInputPassword1">权限名称</label> 
								<input
									id="authName" name="authName" type="text" class="form-control"
									 value="${auth.authName }">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">访问路径</label> 
								<input
									id="authUrl" name="authUrl" type="text"
									class="form-control" 
									 value="${auth.authUrl }">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">所属节点</label> 
								<select
									class="form-control has-success" 
									id="authParentRoot" name="authParentRoot">
									<c:forEach items="${authIdNames}" var="auth">
										<option value="${auth.authId}">${auth.authName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<div class="input-group">
									<label for="exampleInputPassword1">是否是根结点</label> <br/>
									<label><input type="radio" name="authIsRoot" value="是">是</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label><input type="radio" name="authIsRoot" value="否">否</label>
								</div>
							</div>
							<button type="button" class="btn btn-success" onclick="editAuth(${auth.authId });">
								<i class="glyphicon glyphicon-plus"></i> 确认修改
							</button>
							<button type="reset" class="btn btn-danger">
								<i class="glyphicon glyphicon-refresh"></i> 重置
							</button>
						</form>
					</div>
				</div>
			</div>

		</div>
	</div>

	<script src="${PATH}/jquery/jquery-2.1.1.min.js"></script>
	<script src="${PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${PATH}/script/docs.min.js"></script>
	<script src="jquery/jquery-2.1.1.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="layer/layer.js"></script>
	<script type="text/javascript">
		function editAuth(authId) {
			var authName = $('#authName').val();
			var authUrl = $('#authUrl').val();
			var authParentRoot = $('#authParentRoot').val();
			var authIsRoot = $("input[name='authIsRoot']:checked").val();  
// 			alert("authName = " + authName + ", authUrl = " + authUrl
// 					+ ", authParentRoot = " + authParentRoot + ", authIsRoot = " + authIsRoot)
			$.ajax({
				type : "POST",
				url : "${PATH}/permission/editAuth",
				data : {
					"authId" : authId,
					"authName" : authName,
					"authUrl" : authUrl,
					"authParentRoot" : authParentRoot,
					"authIsRoot" : authIsRoot
				},
				success : function(result) { //接收返回数据
					if (result.success) {
						alert("权限修改成功")
						location.href = "${PATH}/permission/index";
					} else {
						alert("发生未知错误，权限修改失败,请重新输入")
					}
				}
			});
		}

		$(function() {
			$(".list-group-item").click(function() {
				if ($(this).find("ul")) {
					$(this).toggleClass("tree-closed");
					if ($(this).hasClass("tree-closed")) {
						$("ul", this).hide("fast");
					} else {
						$("ul", this).show("fast");
					}
				}
			});
		});
	</script>
</body>
</html>
