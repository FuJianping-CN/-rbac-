<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
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
					<a class="navbar-brand" style="font-size: 32px;" href="user.html">考试管理系统</a>
				</div>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li style="padding-top: 8px;">
						<div class="btn-group">
							<button type="button"
								class="btn btn-default btn-success dropdown-toggle"
								data-toggle="dropdown">
								<i class="glyphicon glyphicon-user"></i> 张三 <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#"><i class="glyphicon glyphicon-cog"></i>
										个人设置</a></li>
								<li><a href="#"><i class="glyphicon glyphicon-comment"></i>
										消息</a></li>
								<li class="divider"></li>
								<li><a href="login.html"><i
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
					<input type="text" class="form-control" placeholder="Search...">
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
					<li><a href="#">角色维护</a></li>
					<li class="active">为角色添加权限</li>
				</ol>

				<div class="panel-body">
					<form class="form-inline" role="form" style="float: left;">
						<div class="form-group has-feedback">
							<div class="input-group">
								<div class="input-group-addon">角色id</div>
								<input class="form-control has-success" type="text"
									value="${role.roleId}" readonly="readonly" 
									style="color:red;">
							</div>
						</div>
						<div class="form-group has-feedback">
							<div class="input-group">
								<div class="input-group-addon">角色名称</div>
								<input class="form-control has-success" type="text"
									value="${role.roleName}" readonly="readonly" style="color:red;">
							</div>
						</div>
					</form>
				</div>

				<div class="panel panel-default">
					<div class="panel-body">
						<form id="roleForm" role="form" class="form-inline">
							<input type="hidden" id="roleId" name="roleId" value="${role.roleId}">
							<div class="form-group">
								<label for="exampleInputPassword1">未分配权限列表&nbsp;&nbsp;&nbsp;(<span style="color:red;">${unAssignedAuths.size()}</span>)</label><br> <select
									id="leftList" name="unAssignedAuthIds" class="form-control"
									multiple size="10" style="width: 200px; overflow-y: auto;">

									<c:forEach items="${unAssignedAuths}" var="auth">
										<option value="${auth.authId }">${auth.authName }</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<ul>
									<li id="left2RightBtn"
										class="btn btn-default glyphicon glyphicon-chevron-right">增加权限</li>
									<br/>
									<li id="right2LeftBtn"
										class="btn btn-default glyphicon glyphicon-chevron-left"
										style="margin-top: 20px;">取消权限</li>
								</ul>
							</div>
							<div class="form-group" style="margin-left: 40px;">
								<label for="exampleInputPassword1">已分配权限列表&nbsp;&nbsp;&nbsp;(<span style="color:red;">${assignedAuths.size()}</span>)</label><br> <select
									id="rightList" name="assignedAuthIds" class="form-control"
									multiple size="10" style="width: 200px; overflow-y: auto;">

									<c:forEach items="${assignedAuths}" var="auth">
										<option value="${auth.authId }">${auth.authName }</option>
									</c:forEach>
								</select>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">帮助</h4>
				</div>
				<div class="modal-body">
					<div class="bs-callout bs-callout-info">
						<h4>测试标题1</h4>
						<p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
					</div>
					<div class="bs-callout bs-callout-info">
						<h4>测试标题2</h4>
						<p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
					</div>
				</div>
				<!--
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		  -->
			</div>
		</div>
	</div>
	<script src="${PATH}/jquery/jquery-2.1.1.min.js"></script>
	<script src="${PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${PATH}/script/docs.min.js"></script>
	<script src="${PATH}/layer/layer.js"></script>
	<script type="text/javascript">
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
			
// 			给角色增加权限
			$("#left2RightBtn").click(function() {
				//alert('left2right');
				var opts = $('#leftList :selected');
				//alert(opts.length+"****");
				if (opts.length == 0) {
					layer.msg("请选择需要分配的权限数据", {
						timer : 2000,
						icon : 5
					}, function() {
					});
				} else {
					$.ajax({
						type : "POST",
						url : "${PATH}/role/addAuthForRole",
						data : $("#roleForm").serialize(),
						success : function(result) {
							if (result.success) {
								$("#rightList").append(opts);
								layer.msg("分配权限数据成功", {
									timer : 2000,
									icon : 6
								}, function() {
								});
							} else {
								layer.msg("分配权限数据失败", {
									timer : 2000,
									icon : 5
								}, function() {
								});
							}

						}
					});
				}
			});

// 			给角色取消权限
			$("#right2LeftBtn").click(function() {

				var opts = $('#rightList :selected');

				if (opts.length == 0) {
					layer.msg("请选择需要取消分配的权限数据", {
						timer : 2000,
						icon : 5
					}, function() {
					});
				} else {
					$.ajax({
						type : "POST",
						url : "${PATH}/role/deleteAuthForRole",
						data : $("#roleForm").serialize(),
						success : function(result) {
							if (result.success) {
								$("#leftList").append(opts);
								layer.msg("取消分配权限数据成功", {
									timer : 2000,
									icon : 6
								}, function() {
								});
							} else {
								layer.msg("取消分配权限数据失败", {
									timer : 2000,
									icon : 5
								}, function() {
								});
							}

						}
					});
				}
			});

		});
	</script>
</body>
</html>
