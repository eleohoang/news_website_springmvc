<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail</title>
</head>
<body>

	<!-- Page Content -->
	<div class="container">
			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="/trang-chu">Trang chủ </a></li>
				 <li><a  style="color:gray"> > </a></li>  
				<li class="active"><a href="#" style="color:black">${categoryName}</a></li>
			</ul>
					
			<!-- Heading Row -->
			<div class="row align-items-center my-5">
				<div class="col-lg-7">
					<img class="img-fluid rounded mb-4 mb-lg-0" alt="${newest.title}" width="900" height="400" 
					 src="${pageContext.request.contextPath}/my_images/${newest.thumbnail}"/>
				</div>
				<!-- /.col-lg-8 -->
				<div class="col-lg-5">
					<h2 class="font-weight-light">
						<b>${newest.title}</b>
					</h2>
					<p>${newest.shortDescription}</p>
					<c:url var="postURL" value="/chi-tiet-bai-viet">
						<c:param name="postId" value="${newest.id}" />
					</c:url>
					<a href="${postURL}" class="btn btn-primary btn-sm">Xem thêm
						thông tin</a>
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="red" class="bi bi-chat-left" viewBox="0 0 16 16">
				  			<path d="M14 1a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H4.414A2 2 0 0 0 3 11.586l-2 2V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12.793a.5.5 0 0 0 .854.353l2.853-2.853A1 1 0 0 1 4.414 12H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
						</svg>
						<span>${newest.commentNumber}</span>
				</div>
			</div>

			<!-- Content Row -->
			<div class="row">
				<c:forEach var="model" items="${models.listResult}">
					<div class="col-md-4 mb-5">
						<div class="card h-100">
							<div class="card-body">
								<h5 class="card-title">${model.title}</h5>
								<div>
								<a>
									<img class="img-fluid rounded mb-4 mb-lg-0" alt="${model.title}" width="250" height="97" 
						 			 src="${pageContext.request.contextPath}/my_images/${model.thumbnail}"/>
						 		  </a>
						 		  <br>
								<p class="card-text">${model.shortDescription}</p>
								</div>
							</div>
							<div class="card-footer">
								<c:url var="postURL" value="/chi-tiet-bai-viet">
									<c:param name="postId" value="${model.id}" />
								</c:url>
								<a href="${postURL}" class="btn btn-primary btn-sm">Xem thêm
									thông tin</a>
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="red" class="bi bi-chat-left" viewBox="0 0 16 16">
									  <path d="M14 1a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H4.414A2 2 0 0 0 3 11.586l-2 2V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12.793a.5.5 0 0 0 .854.353l2.853-2.853A1 1 0 0 1 4.414 12H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
									</svg>
									<span>${model.commentNumber}</span>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
	</div>
	<!-- /.container -->
</body>
</html>