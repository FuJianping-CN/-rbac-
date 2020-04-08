<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" href="${PATH}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${PATH}/css/font-awesome.min.css">
<link rel="stylesheet" href="${PATH}/css/main.css">
<style>
.tree li {
	list-style-type: none;
	cursor: pointer;
}

table tbody tr:nth-child(odd) {
	background: #F4F4F4;
}

table tbody td:nth-child(even) {
	color: #C00;
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
								<i class="glyphicon glyphicon-user"></i> ${user.userName }<span
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
				<!-- 				<form class="navbar-form navbar-right"> -->
				<!-- 					<input type="text" class="form-control" placeholder="Search..."> -->
				<!-- 				</form> -->
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
			<!-- 			用户列表 -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
					<li><a href="#">考试中心</a></li>
					<li><a href="#">考试</a></li>
					<li class="active">考试结果</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 考试结果反馈
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" style="float: left;">
							<!-- 							<div class="form-group has-feedback"> -->
							<!-- 								<div class="input-group"> -->
							<!-- 									<div class="input-group-addon">试题类型名称</div> -->
							<!-- 									<input class="form-control has-success" type="text" -->
							<!-- 										placeholder="请输入试题类型名称" name="quesTypeName" id="quesTypeName"> -->
							<!-- 								</div> -->
							<!-- 							</div> -->
							<!-- 							<div class="form-group has-feedback"> -->
							<!-- 								<div class="input-group"> -->
							<!-- 									<div class="input-group-addon">试题类型描述</div> -->
							<!-- 									<input class="form-control has-success" type="text" -->
							<!-- 										placeholder="请输入试题类型描述" name="quesTypeContent" id="quesTypeContent"> -->
							<!-- 								</div> -->
							<!-- 							</div> -->
							<!-- 							<button type="button" class="btn btn-warning" -->
							<!-- 								onclick="toAddQuesType();"> -->
							<!-- 								<i class="glyphicon glyphicon-plus"></i> 新增试题类型 -->
							<!-- 							</button> -->
						</form>
						<br> <label>此试卷您的总成绩为：<span style="color: red;">${test.grade }</span>分</label>
						<br> <label>考试科目：<span style="color: red;">${test.quesTypeName }</span></label>
						<br><label>您的答题情况如下：</label>
						<hr style="clear: both;">
						<div>

							<label id="answers" style="display: none;">${answers }</label> 
							<label id="quesTypeName" style="display: none;">${test.quesTypeName }</label>
							<label
								id="quesIds" style="display: none;">${quesIds }</label>

							<%-- 						quesIds	<form method="post" action="${PATH}/exam/checkSubmitedAnswerTest"> --%>
						<c:if test="${not empty questions}" >
							<c:forEach items="${questions}" var="question" varStatus="s">
								<c:if test="${question.quesAnswer != question.submitAnswer}">
								<label style="color:red;">
								<button type="button" class="btn btn-warning" id="toCollectQuesBtn"
									onclick="toCollectQues(${question.quesId});">收藏此题
									</button>
								${s.count }、&nbsp;&nbsp;${question.quesContent}</label>
								</c:if>
								<c:if test="${question.quesAnswer == question.submitAnswer}">
								<label>
									<button type="button" class="btn btn-warning" id="toCollectQuesBtn"
									onclick="toCollectQues(${question.quesId});">收藏此题
									</button>
									${s.count }、&nbsp;&nbsp;${question.quesContent}
								</label>
								</c:if>
								<p style="padding: 1px 0; margin-left: 25px;">
									&nbsp;A.&nbsp;${question.optionA}
								</p>
								<p style="padding: 1px 0; margin-left: 25px;">
									&nbsp;B.&nbsp;${question.optionB}
								</p>
								<p style="padding: 1px 0; margin-left: 25px;">
									&nbsp;C.&nbsp;${question.optionC}
								</p>
								<p style="padding: 1px 0; margin-left: 25px;">
									&nbsp;D.&nbsp;${question.optionD}
								</p>
								<c:if test="${question.quesAnswer != question.submitAnswer}">
								<p>正确答案：&nbsp;<span style="color:red">${question.quesAnswer}</span>&nbsp;&nbsp;
								您的答案：&nbsp;<span style="color:red">
								<c:if test="${question.submitAnswer == 'X'}">
								未作答
								</c:if>
								<c:if test="${question.submitAnswer != 'X'}">
								${question.submitAnswer}
								</c:if>
								
								</span></p>
								</c:if>
								<c:if test="${question.quesAnswer == question.submitAnswer}">
								<p>正确答案：&nbsp;<span >${question.quesAnswer}</span>&nbsp;&nbsp;您的答案：&nbsp;<span>${question.submitAnswer}</span></p>
								</c:if>
								<hr>
							</c:forEach>
						</c:if>
							<!-- 								<button type="submit" >提交试卷</button> -->

							<!-- 							</form> -->
						</div>
					</div>
				</div>
<!-- 				<button type="button" onclick="submitSelectedAnswer();">提交试卷</button> -->
			</div>
		</div>
	</div>

	<script src="${PATH}/jquery/jquery-2.1.1.min.js"></script>
	<script src="${PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${PATH}/script/docs.min.js"></script>
	<!--     <script src="jquery/jquery-2.1.1.min.js"></script> -->
	<!--     <script src="bootstrap/js/bootstrap.min.js"></script> -->
	<!--     <script type="text/javascript" src="layer/layer.js"></script> -->
	<script>
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
		/*
		$("tbody .btn-success").click(function(){
		    window.location.href = "assignRole.html";
		});
		$("tbody .btn-primary").click(function(){
		    window.location.href = "edit.html";
		});
		 */

		function submitSelectedAnswer1() {
			$('input:radio:checked').each(function() {
				var checkValue = $(this).val();
				// 				    console.log($(this).val());　　// 选中框中的值
				alert("checkValue=" + checkValue)
			});
		}

		function submitSelectedAnswer() {
			var quesIds = $("#quesIds").html().trim();
			// 			alert("quesIds=" + quesIds)
			// 			 var quesIdsArr = [2,4]
			// 			 var number2 = 2;
			// 			 var number4 = 4;
			var quesIds1 = quesIds.substr(1);
			var quesIds2 = quesIds1.substr(0, quesIds1.length - 1);
			// 			alert("quesIds2=" + quesIds2)
			var quesIdsArr = quesIds2.split(", ");
			// 			alert("quesIdsArr=" + quesIdsArr)
			var submitedAnswer = "";
			for (var i = 0; i < quesIdsArr.length; i++) {
				var answer = $("input[name='" + quesIdsArr[i] + "']:checked")
						.val();
				if (typeof (answer) == "undefined") {
					submitedAnswer += 'X';
					// 					alert("题目编号为" + quesIdsArr[i] + "的题目未选")
				} else {
					submitedAnswer += answer;
					// 					alert("题目编号为" + quesIdsArr[i] + "的题目答案为" + answer)
				}
			}

			var answers = $("#answers").html().trim();
			alert("用户的答案=" + submitedAnswer + "\n" + "正确答案=" + answers)

			$.ajax({
				type : "POST",
				url : "${PATH}/exam/checkSubmitedAnswer",
				data : {
					"submitedAnswer" : submitedAnswer,
					"answers" : answers,
					"quesIds":quesIds
				},
				success : function(result) { //接收返回数据
					// 					alert("result="+result)
					if (result.success) {
						alert("试卷已提交成功")
						location.href = "${PATH}/exam/index";
					} else {
						alert("试卷已提交失败")
						//                    		 layer.msg("用户删除失败",{timer:1000,icon:5,shift:6},function(){});
					}
				},
				error : function() {
					alert("网络延时")
				}

			});
		}


		function deleteQuesType(quesTypeId) {
			alert("确定删除编号为" + quesTypeId + "的试题类型吗？")
			$.ajax({
				type : "POST",
				url : "${PATH}/quesType/deleteQuesTypeById",
				data : {
					"quesTypeId" : quesTypeId
				},
				success : function(result) { //接收返回数据
					//alert(result+"****")
					if (result.success) {
						alert("试题类型删除成功")
						location.href = "${PATH}/quesType/index";
					} else {
						alert("试题类型删除失败")
						//                    		 layer.msg("用户删除失败",{timer:1000,icon:5,shift:6},function(){});
					}
				}
			});
		}
		function toAddQuesType() {
			var quesTypeName = $('#quesTypeName').val();
			var quesTypeContent = $('#quesTypeContent').val();
			$.ajax({
				type : "POST",
				url : "${PATH}/quesType/insertQuesType",
				data : {
					"quesTypeName" : quesTypeName,
					"quesTypeContent" : quesTypeContent
				},
				success : function(result) {
					if (result.success) {
						alert("试题类型添加成功")
						location.href = "${PATH}/quesType/index";
					} else {
						alert("试题类型添加失败，请重试")
					}
				},
				error : function() {
					alert("网络错误")
				}

			})
		}

		function deleteUser(userId) {
			alert("确定删除编号为" + userId + "的试题类型吗？")
			$.ajax({
				type : "POST",
				url : "${PATH}/user/deleteUserById",
				data : {
					"userId" : userId
				},
				success : function(result) { //接收返回数据
					//alert(result+"****")
					if (result.success) {
						location.href = "${PATH}/user/main";
					} else {
						alert("试题类型删除失败")
						//                		 layer.msg("用户删除失败",{timer:1000,icon:5,shift:6},function(){});
					}
				}
			});
		}
		function toCollectQues111(quesId) {
			alert("已成功收藏该题目...")
			document.getElementById("toCollectQuesBtn").disabled = true;
// 			$('#toCollectQuesBtn').attr("style","display:none;");
		}
		function toCollectQues(quesId) {
// 			alert('收藏的题目编号quesId='+quesId);
// 			location.href = "${PATH}/role/toAddAuthForRole?roleId=" + roleId;
// 			alert("确定收藏编号为" + userId + "的试题类型吗？")
			var quesTypeName = $("#quesTypeName").html().trim();
			$.ajax({
				type : "POST",
				url : "${PATH}/quesCollect/insertQuesCollect",
				data : {
					"quesId" : quesId,
					"quesTypeName" : quesTypeName
				},
				success : function(result) { //接收返回数据
					//alert(result+"****")
					if (result.success) {
						alert("已成功收藏该题目") 
// 						document.getElementById("toCollectQuesBtn").disabled = true;
// 						$('#toCollectQuesBtn').attr("disabled", true);
// 						$('#toCollectQuesBtn').attr("style","display:none;");
// 						location.href = "${PATH}/quesCollect/index";
// document.getElementById("toCollectQuesBtn").setAttribute("disabled", true);//设置不可点击
						
					} else {
						/* alert("网络错误，题目收藏失败") */
						alert("您已收藏该题目，不可重复收藏")
						//                		 layer.msg("用户删除失败",{timer:1000,icon:5,shift:6},function(){});
					}
				}
			});
		}
	</script>
</body>
</html>