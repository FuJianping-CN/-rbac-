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
						- 控制面板</a>
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
					<li><a href="#">新闻中心</a></li>
					<li><a href="#">新闻管理</a></li>
					<li class="active">新增新闻</li>
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
								<label for="exampleInputPassword1">新闻标题</label> <input
									id="newsTitle" name="newsTitle" type="text" class="form-control"
									id="exampleInputPassword1" placeholder="请输入新闻标题">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">所属栏目</label> 
								<select
									class="form-control has-success" 
									id="category" name="category">
									<c:forEach items="${categories}" var="category">
										<option value="${category.categName}">${category.categName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">新闻内容</label> 
								<textArea id="newsContent" name="newsContent" class="form-control" 
								placeholder="请输入新闻内容"></textArea>
							</div>
							<button type="button" onclick="addNews();" 
								class="btn btn-success">
								<i class="glyphicon glyphicon-plus"></i> 确认新增
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
		function addNews() {
			var newsTitle = $('#newsTitle').val();
			var category = $('#category').val();
			var newsContent = $('#newsContent').val();
// 			alert("newsTitle="+newsTitle+", category="+category
// 					+", newsContent="+newsContent)
			$.ajax({
				type : "POST",
				url : "${PATH }/news/insertNews",
				data : {
					"newsTitle" : newsTitle,
					"category" : category,
					"newsContent" : newsContent
				},
				success : function(result) { //接收返回数据
					if (result.success) {
						alert("新闻添加成功")
						location.href = "${PATH }/news/index";
					} else {
						alert("发生未知错误，新闻添加失败,请重新输入")
					}
				},
				error: function() {
					alert("网络错误，新闻添加失败,请重新输入")
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
