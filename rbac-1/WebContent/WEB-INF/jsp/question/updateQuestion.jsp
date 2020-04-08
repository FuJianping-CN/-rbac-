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
					<li><a href="#">考试中心</a></li>
					<li><a href="#">题库管理</a></li>
					<li><a href="#">试题列表</a></li>
					<li class="active">试题详情</li>
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
							<input type="hidden" id="quesId" name="quesId" value="${question.quesId}">
							<input type="hidden" id="quesTypeId" name="quesTypeId" value="${quesType.quesTypeId}">
							<label for="exampleInputPassword1">试题所属类别：<span style="color:red;">${quesType.quesTypeName}</span></label>
							<div class="form-group">
								<label for="exampleInputPassword1">题目</label> 
								<input
									id="quesContent" name="quesContent" type="text" class="form-control"
									value="${question.quesContent }">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">选项A</label> 
								<input
									id="optionA" name="optionA" type="text" class="form-control"
									value="${question.optionA }">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">选项B</label> 
								<input
									id="optionB" name="optionB" type="text" class="form-control"
									 value="${question.optionB }">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">选项C</label> 
								<input
									id="optionC" name="optionC" type="text" class="form-control"
									value="${question.optionC }">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">选项D</label> 
								<input
									id="optionD" name="optionD" type="text" class="form-control"
									value="${question.optionD }">
							</div>
							<div class="form-group">
								<div class="input-group">
									<label for="exampleInputPassword1">正确选项: <span style="color:red;">${question.quesAnswer }</span></label> <br/>
									<label><input type="radio" name="quesAnswer" value="A">A</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label><input type="radio" name="quesAnswer" value="B">B</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label><input type="radio" name="quesAnswer" value="C">C</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label><input type="radio" name="quesAnswer" value="D">D</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</div>
<!-- 							<div class="form-group"> -->
<!-- 								<label for="exampleInputPassword1">新闻内容</label>  -->
<!-- 								<textArea id="newsContent" name="newsContent" class="form-control"  -->
<!-- 								placeholder="请输入新闻内容"></textArea> -->
<!-- 							</div> -->
							<button type="button" onclick="editQuestion();" 
								class="btn btn-success">
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
		function editQuestion() {
			var quesId = $('#quesId').val();
			var quesTypeId = $('#quesTypeId').val();
			var quesContent = $('#quesContent').val();
			var optionA = $('#optionA').val();
			var optionB = $('#optionB').val();
			var optionC = $('#optionC').val();
			var optionD = $('#optionD').val();
			var quesAnswer = $("input[name='quesAnswer']:checked").val();  
// 			alert("quesTypeId="+quesTypeId+", quesContent="+quesContent
// 					+", optionA="+optionA+", optionB="+optionB+", optionC="+optionC
// 					+", optionD="+optionD+", quesAnswer="+quesAnswer)
			$.ajax({
				type : "POST",
				url : "${PATH }/question/updateQuestionIdById",
				data : {
					"quesId" : quesId,
					"quesType" : quesTypeId,
					"quesContent" : quesContent,
					"optionA" : optionA,
					"optionB" : optionB,
					"optionC" : optionC,
					"optionD" : optionD,
					"quesAnswer" : quesAnswer
				},
				success : function(result) { //接收返回数据
					if (result.success) {
						alert("试题修改成功")
						location.href = "${PATH }/question/index?quesTypeId="+quesTypeId;
					} else {
						alert("发生未知错误，试题修改失败,请重新输入")
					}
				},
				error: function() {
					alert("网络错误，试题修改失败,请重新输入")
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
