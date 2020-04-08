<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul style="padding-left:0px;" class="list-group">
       <c:forEach items="${rootAuth.children }" var="auth">
           <c:if test="${ empty rootAuth.children }">
               <li class="list-group-item tree-closed" >
						<a href="${PATH}${auth.authUrl}"><i class="${auth.icon}"></i>${auth.authName }</a> 
					</li>
           </c:if>
           <c:if test="${not empty auth.children }">
              <li class="list-group-item tree-closed">
						<span><i class="${auth.icon }"></i>${auth.authName } <span class="badge" style="float:right">${auth.children.size()}</span></span> 
						<ul style="margin-top:10px;display:none;">
						  <c:forEach items="${auth.children }" var="child">
							<li style="height:30px;">
								<a href="${PATH}${child.authUrl}"><i class="${child.icon }"></i> ${child.authName }</a> 
							</li>
							</c:forEach>
						</ul>
					</li>
           
           </c:if>
       
       </c:forEach>
					
</ul>