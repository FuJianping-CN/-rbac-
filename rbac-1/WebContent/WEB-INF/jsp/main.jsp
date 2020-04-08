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
<link rel="stylesheet" href="${PATH }/css/font-awesome.min.css">
<link rel="stylesheet" href="${PATH }/css/main.css">
<style>
.tree li {
	list-style-type: none;
	cursor: pointer;
}

.tree-closed {
	height: 40px;
}

.tree-expanded {
	height: auto;
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
								<li><a href="#"><i class="glyphicon glyphicon-off"></i>
										退出系统</a></li>
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
					<li><a href="#">用户维护</a></li>
					<li class="active">用户列表</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">
						<!-- 						<form class="form-inline" role="form" style="float: left;"> -->
						<!-- 							<div class="form-group has-feedback"> -->
						<!-- 								<div class="input-group"> -->
						<!-- 									<div class="input-group-addon">查询条件</div> -->
						<!-- 									<input class="form-control has-success" type="text" -->
						<!-- 										placeholder="请输入查询条件"> -->
						<!-- 								</div> -->
						<!-- 							</div> -->
						<!-- 							<button type="button" class="btn btn-warning"> -->
						<!-- 								<i class="glyphicon glyphicon-search"></i> 查询 -->
						<!-- 							</button> -->
						<!-- 						</form> -->
<!-- 						<button type="button" class="btn btn-danger" -->
<!-- 							style="float: right; margin-left: 10px;"> -->
<!-- 							<i class=" glyphicon glyphicon-remove"></i> 删除 -->
<!-- 						</button> -->
						<button type="button" class="btn btn-primary"
							style="float: right;" onclick="toAddUser();">
							<i class="glyphicon glyphicon-plus"></i> 新增用户
						</button>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input type="checkbox"></th>
										<th>用户编号</th>
										<th>账号</th>
										<th>密码</th>
										<th>真实姓名</th>
										<th>用户状态</th>
										<th width="150">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageInfo.list}" var="user" varStatus="s">
										<tr>
											<td>${s.count }</td>
											<td><input type="checkbox"></td>
											<td>${user.userId }</td>
											<td>${user.userName }</td>
											<td>${user.password }</td>
											<td>${user.userTrueName}</td>
											<td>${user.userState}</td>
											<td>
												<button title="为用户添加角色" type="button" id="assignRole"
													onclick="assignRole(${user.userId});"
													class="btn btn-success btn-xs">
													<i class=" glyphicon glyphicon-check"></i>
												</button>
												<button title="为用户添加权限" type="button"
													class="btn btn-success btn-xs">
													<i class="glyphicon glyphicon-eye-open"></i>
												</button>
												<button title="修改用户修改" type="button"
													onclick="toEditUser(${user.userId});"
													class="btn btn-primary btn-xs">
													<i class=" glyphicon glyphicon-pencil"></i>
												</button>
												<button title="删除用户" type="button"
													class="btn btn-danger btn-xs"
													onclick="deleteUser(${user.userId});">
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
												<li><a href="index?pn=1">首页</a></li>
												<c:if test="${pageInfo.hasPreviousPage}">
													<li><a href="index?pn=${pageInfo.pageNum-1}"
														aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
												</c:if>
												<c:forEach items="${pageInfo.navigatepageNums }"
													var="page_Num">
													<c:if test="${page_Num==pageInfo.pageNum }">
														<li class="active"><a href="#">${page_Num}</a></li>
													</c:if>
													<c:if test="${page_Num!=pageInfo.pageNum }">
														<li><a href="index?pn=${page_Num}">${page_Num}</a></li>
													</c:if>
												</c:forEach>
												<c:if test="${pageInfo.hasNextPage}">
													<li><a href="index?pn=${pageInfo.pageNum+1}"
														aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
												</c:if>
												<li><a href="index?pn=${pageInfo.pages }">尾页</a></li>
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

	<!-- 	<script src="jquery/jquery-2.1.1.min.js"></script> -->
	<!--     <script src="bootstrap/js/bootstrap.min.js"></script> -->
	<!--     <script type="text/javascript" src="layer/layer.js"></script> -->
	<script>
            $(function () {
			    $(".list-group-item").click(function(){
                    // jquery对象的回调方法中的this关键字为DOM对象
                    // $(DOM) ==> JQuery
				    if ( $(this).find("ul") ) { // 3 li
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
            });
            function deleteUser(userId) {
            	alert("确定删除编号为" + userId + "的用户吗？")
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
                   		 alert("用户删除失败")
//                    		 layer.msg("用户删除失败",{timer:1000,icon:5,shift:6},function(){});
                   	 }
                    }
                });
            }
            function assignRole(id){
//             	alert('assignRole'+id);
            	location.href="${PATH}/user/assign?id="+id;
            }
            
            function toEditUser(userId){
            	location.href="${PATH}/user/toEditUser?userId="+userId;
            }
            
            function toAddUser() {
//             	window.location.href='/WEB-INF/jsp/user/addUser.jsp'
            	location.href="${PATH}/user/toAddUser";
            }
        </script>
</body>
</html>
