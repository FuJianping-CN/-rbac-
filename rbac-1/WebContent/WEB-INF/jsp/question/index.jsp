<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
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
			<!-- 			用户列表 -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
					<li><a href="#">考试中心</a></li>
					<li><a href="#">题库管理</a></li>
					<li><a href="#">试题类型列表</a></li>
					<li class="active">试题列表</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表工作区
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" style="float: left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">试题类型</div>
									<input class="form-control has-success" type="text" readonly="readonly"
										value="${quesType.quesTypeName }">
								</div>
							</div>
<!-- 							<div class="form-group has-feedback"> -->
<!-- 								<div class="input-group"> -->
<!-- 									<div class="input-group-addon">试题类型描述</div> -->
<!-- 									<input class="form-control has-success" type="text" -->
<!-- 										placeholder="请输入试题类型描述" name="quesTypeContent" id="quesTypeContent"> -->
<!-- 								</div> -->
<!-- 							</div> -->
							<button type="button" class="btn btn-warning"
								onclick="toAddQuestion(${quesType.quesTypeId });">
								<i class="glyphicon glyphicon-plus"></i> 新增试题
							</button>
						</form>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="50">题目编号</th>
										<th width="400">题目</th>
										<th>创建人</th>
										<th>创建时间</th>
										<th>修改人</th>
										<th>修改时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageInfo.list}" var="question" varStatus="s">
										<tr>
											<!-- 											<td><input type="checkbox"></td> -->
											<td>${question.quesId }</td>
											<td>${question.quesContent }</td>
											<td>${question.creator}</td>
											<td><fmt:formatDate 
												value="${question.createDate}" 
												pattern="yyyy年MM月dd日" />
											</td>
<%-- 											<td>${role.createDate}</td> --%>
											<td>${question.updater}</td>
											<td><fmt:formatDate 
												value="${question.updateDate}" 
												pattern="yyyy年MM月dd日" />
											</td>
											<td>
												<button title="查看试题详情" type="button" id="assignRole"
													onclick="questionDetail(${question.quesId});"
													class="btn btn-success btn-xs">
													<i class=" glyphicon glyphicon-check"></i>
												</button>
<!-- 												<button title="修改试题类型" type="button" -->
<%-- 													onclick="toEditQuesType(${question.quesId});" --%>
<!-- 													class="btn btn-primary btn-xs"> -->
<!-- 													<i class=" glyphicon glyphicon-pencil"></i> -->
<!-- 												</button> -->
												<button title="删除试题类型" type="button"
													class="btn btn-danger btn-xs"
													onclick="deleteQuestion(${question.quesId}, ${quesType.quesTypeId });">
													<i class=" glyphicon glyphicon-remove"></i>
												</button>

											</td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="6" align="center">
											<ul class="pagination">
												<li><a href="index?pn=1&quesTypeId=${quesType.quesTypeId }">首页</a></li>
												<c:if test="${pageInfo.hasPreviousPage}">
													<li><a href="index?pn=${pageInfo.pageNum-1}&quesTypeId=${quesType.quesTypeId }"
														aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
												</c:if>
												<c:forEach items="${pageInfo.navigatepageNums }"
													var="page_Num">
													<c:if test="${page_Num==pageInfo.pageNum }">
														<li class="active"><a href="#">${page_Num}</a></li>
													</c:if>
													<c:if test="${page_Num!=pageInfo.pageNum }">
														<li><a href="index?pn=${page_Num}&quesTypeId=${quesType.quesTypeId }">${page_Num}</a></li>
													</c:if>
												</c:forEach>
												<c:if test="${pageInfo.hasNextPage}">
													<li><a href="index?pn=${pageInfo.pageNum+1}&quesTypeId=${quesType.quesTypeId }"
														aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
												</c:if>
												<li><a href="index?pn=${pageInfo.pages }&quesTypeId=${quesType.quesTypeId }">尾页</a></li>
											</ul>
										</td>
									</tr>

								</tfoot>
							</table>
						</div>
					</div>
				</div>
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
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
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
            function deleteQuestion(quesId,quesTypeId) {
            	alert("确定删除编号为" + quesId + "的试题吗？")
	            	$.ajax({
	               	 type: "POST",
	               	 url: "${PATH}/question/deleteQuestionById",
	               	 data: {
	               		 "quesId":quesId
	               	 },
                    success: function(result){  //接收返回数据
                   	 //alert(result+"****")
	                   	 if(result.success){
	                   		alert("试题删除成功")
	                   		 location.href="${PATH}/question/index?quesTypeId="+quesTypeId;
	                   	 }else{
	                   		 alert("试题删除失败")
	//                    		 layer.msg("用户删除失败",{timer:1000,icon:5,shift:6},function(){});
	                   	 }
                    },
            		error: function() {
            			alert("网络错误")
            		}
                });
            }
            function toAddQuesType() {
            	var quesTypeName = $('#quesTypeName').val();
            	var quesTypeContent = $('#quesTypeContent').val();
            	$.ajax({
            		type:"POST",
            		url:"${PATH}/quesType/insertQuesType",
            		data:{
            			"quesTypeName":quesTypeName,
            			"quesTypeContent":quesTypeContent
            		},
            		success: function(result) {
            			if(result.success) {
            				alert("试题类型添加成功")
            				location.href="${PATH}/quesType/index";
            			} else {
            				alert("试题类型添加失败，请重试")
            			}
            		},
            		error: function() {
            			alert("网络错误")
            		}
            		
            	})
            }
            
            function deleteUser(userId) {
            	alert("确定删除编号为" + userId + "的试题类型吗？")
            	$.ajax({
               	 type: "POST",
               	 url: "${PATH}/user/deleteUserById",
               	 data: {
               		 "userId":userId
               	 },
                success: function(result){  //接收返回数据
               	 //alert(result+"****")
               	 if(result.success){
               		 location.href="${PATH}/user/main";
               	 }else{
               		 alert("试题类型删除失败")
//                		 layer.msg("用户删除失败",{timer:1000,icon:5,shift:6},function(){});
               	 }
                }
            });
        }
            function quesManage(quesTypeId){
            	location.href="${PATH}/quesType/toQuesManage?quesTypeId="+quesTypeId;
            }
            function addAuthForRole(roleId){
            	//alert('assignRole'+id);quesManage
            	location.href="${PATH}/role/toAddAuthForRole?roleId="+roleId;
            }
            function questionDetail(quesId){
            	location.href="${PATH}/question/toUpdateQuestion?quesId="+quesId;
            }
            function toAddQuestion(quesTypeId) {
//             	window.location.href='/WEB-INF/jsp/user/addUser.jsp'
            	location.href="${PATH}/question/toAddQuestion?quesTypeId="+quesTypeId;
            }
        </script>
</body>
</html>