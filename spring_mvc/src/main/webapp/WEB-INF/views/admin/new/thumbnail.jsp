<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload thumbnail</title>
</head>
<body>
<div class="main-content">
	<div class="main-content-inner">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>

			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="<c:url value ="/quan-tri/trang-chu"/>">Trang quản trị</a></li>
				<li><a href="<c:url value ="/quan-tri/bai-viet/danh-sach?page=1&limit=5"/>">Danh sách</a></li>
				<li><a href="<c:url value ="/quan-tri/bai-viet/chinh-sua"/>">Chỉnh sửa bài viết</a></li>
			    <li class="active"><b>Thêm ảnh đại diện</b></li>
			</ul>
			<!-- /.breadcrumb -->
		</div> 
		<div class="page-content">
			
			<%-- <h3 style="color: red">${filesuccess}</h3> --%>
			<form:form method="post" action="/quan-tri/bai-viet/chinh-sua/thumbnail" enctype="multipart/form-data">
				<p> <label for="image">Ảnh đại diện: </label> </p>
				<p> <input name="file" id="fileToUpload" type="file" /> </p>
				<p> <input type="submit" value="Thêm ảnh đại diện vào thư viện"> </p>
				<input type="hidden" value="${id}" id="id" name="id"/>
				<input type="hidden" value="${categoryCode}" id="categoryCode" name="categoryCode"/>
				
			</form:form>
			
			<c:if test="${not empty thumbnail}">
			<form:form method="get" action="/quan-tri/bai-viet/chinh-sua" enctype="multipart/form-data">
				<h5>Thêm ảnh vào thư viện thành công !!!</h5> 
				<img class="img" alt="anh_dai_dien" width="440" height="264" src="${pageContext.request.contextPath}/my_images/${thumbnail}"/>
				
				<br><br> <br> <br>
						<c:url var="updateThumbnail" value="/quan-tri/bai-viet/chinh-sua">
							<c:param name="id" value="${model.id}" />
							<c:param name="message" value="update_success" /> 
						</c:url>
						
						<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
							title="Cập nhật ảnh đại diện" href='${updateThumbnail}'><i
							class="fa fa-pencil-square-o" aria-hidden="true"></i> Cập nhật ảnh đại diện</a> 
			</form:form>
			</c:if> 
		 </div>
	 	</div>
	 </div>
</body>
</html>