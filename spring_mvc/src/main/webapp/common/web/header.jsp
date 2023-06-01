<%@ page import="spring_mvc.util.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="#" style="color:yellow"><b>VnNews</b></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="<c:url value='/trang-chu'/>"><b>Trang chủ</b>
						<span class="sr-only">(current)</span>
				</a>
				</li>
				<c:forEach var="item" items="${categories}">
					<c:url var="detailtURL" value="${item.key}">
						<c:param name="categoryCode" value="${item.key}"/>
						<c:param name="categoryName" value="${item.value}"/>
					</c:url>
				<li class="nav-item active"><a class="nav-link" href="${detailtURL}"><b>${item.value}</b>
						<span class="sr-only">(current)</span></a></li>
				</c:forEach>

				<security:authorize access="isAnonymous()">
					<li class="nav-item"><a class="nav-link" href="<c:url value='/dang-nhap'/>"><b>Đăng nhập</b></a></li>
					<!-- <li class="nav-item"><a class="nav-link" href="#"><b>Đăng ký</b></a></li> -->
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<li class="nav-item">
						<a class="nav-link" 
							 <c:set var="myArray" value="<%=SecurityUtils.getAuthorities()%>"/>
							 <c:if test ="${fn:contains(myArray, 'ADMIN')}">
								href="quan-tri/trang-chu"
							</c:if>
						>
							Xin chào,  <%=SecurityUtils.getPrincipal().getFullName()%>
						</a>
					</li>
					 <c:set var = "theString" value = "I am a test String"/>

					<li class="nav-item"><a class="nav-link" href="<c:url value='/thoat'/>">Thoát</a></li>
				</security:authorize>
			</ul>
		</div>
	</div>
</nav>
