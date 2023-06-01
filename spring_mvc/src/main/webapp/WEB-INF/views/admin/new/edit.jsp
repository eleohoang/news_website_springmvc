
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="newURL" value="/quan-tri/bai-viet/danh-sach"/>
<c:url var="editNewURL" value="/quan-tri/bai-viet/chinh-sua"/>
<c:url var="editThumbnailURL" value="/quan-tri/bai-viet/thumbnail"/>
<c:url var="newAPI" value="/api/new"/>
<html>
<head>
<title>Chỉnh sửa bài viết</title>
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
				<li class="active"><b>Chỉnh sửa bài viết</b></li>
			</ul>
			<!-- /.breadcrumb -->
		</div>
		<div class="page-content">
				<div class="row">
				<c:if test="${not empty message}">
				<div class="alert alert-${alert}">${message}</div>
				</c:if>
				<div class="col-xs-12">
					<form:form class="form-horizontal" role="form" id="formSubmit" modelAttribute="model" >
						<div class="form-group">
							  <label for="categoryCode" class="col-sm-3 control-label no-padding-right">Thể loại:</label>
								<div class="col-sm-9">
									 <form:select path="categoryCode" id="categoryCode">
									 	<form:option value="" label="-- Chọn thể loại --"/>
									 	<form:options items="${categories}"/>
									 </form:select>
								</div>
						</div>
						<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Tên bài viết</label>
								<div class="col-sm-9">
									<form:input path="title" id="title" cssClass="form-control"/>
								</div>
						</div>
						<!-- <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Ảnh đại diện</label>
								<div class="col-sm-9">
									 <input type="file" class="image-upload" name="thumbnail"  accept="image/*" value="" id="thumbnail"/>
								</div>
						</div> -->
						<div class="form-group">
						  	<label for="shortDescription" class="col-sm-3 control-label no-padding-right">Mô tả ngắn:</label>
						  	<div class="col-sm-9">
						  		<form:textarea path="shortDescription" rows="5" cols="10" cssClass="form-control" id="shortDescription"/>
						  	</div>
						</div>
						<div class="form-group">
						  	<label for="content" class="col-sm-3 control-label no-padding-right">Nội dung:</label>
						  	<div class="col-sm-9">
						  		<form:textarea path="content" rows="5" cols="10" cssClass="form-control" id="content"/>
						  	</div>
						</div>
						<form:hidden path="id" id="newId"/> <!-- get value id -->
							<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<c:if test="${not empty model.id}">
										<%-- <c:url var="newAPI" value="/api/new"/> --%>
										<button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
										<i class="ace-icon fa fa-check bigger-110"></i>
										Cập nhật bài viết
									</button>
								</c:if>
								<c:if test="${empty model.id}">
										<%-- <c:url value="/api/new" var="newAPI">
											<c:param name="thumbnail" value="${document.getElementById('thumbnail')}" />
										</c:url> --%>
										<button class="btn btn-info" type="button" id="btnAddOrUpdateNew"> <!--type="button" -->
										<i class="ace-icon fa fa-check bigger-110"></i>
										Thêm bài viết
									</button>
								</c:if>

								&nbsp; &nbsp; &nbsp;
								<button class="btn" type="reset" id="btnReset" onClick="window.location.reload()"> 
									<i class="ace-icon fa fa-undo bigger-110"></i>
									Hủy
								</button>
							</div>		
						</div>
					</form:form>
				</div>
			</div>
		</div>
		</div>  
</div>	
<script>
	var editor = '';
	$(document).ready(function() {
	 	editor = CKEDITOR.replace('content');
	});

	$('#btnAddOrUpdateNew').click(function (e) { //send data by click button
	    e.preventDefault(); // prevent reload the url default
	   	//var content = $('#content').val();
	    //var shortDescription = $('#shortDescription').val();
	    // the way to get data from each variables is not good
	    var data = {}; // create an array
	    var formData = $('#formSubmit').serializeArray(); // get all value from the formSubmit
	    $.each(formData, function (i, v) { 
            data[""+v.name+""] = v.value;
        });
	    data["content"] = editor.getData();
	    /* var fileVal=document.getElementById("thumbnail");
	    data["thumbnail"] = fileVal.value; */
	    var id = $('#newId').val();
	    if (id == "") {
	    	addNew(data); 
	    } else {
	    	updateNew(data);
	    }
	});
	
	function addNew(data) {
		$.ajax({
          	url: '${newAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
           	dataType: 'json',
            success: function (newdto) {
            	/* window.location.href = "${editNewURL}?id="+newdto.id+"&message=insert_success"; */
            	 window.location.href = "${editThumbnailURL}?id="+newdto.id+"&categoryCode="+newdto.categoryCode;
            },
            error: function (error) {
            	window.location.href = "${newURL}?page=1&limit=2&message=error_system";
            }  
        });
	}
	
	function updateNew(data) {
		$.ajax({
            url: '${newAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data), //data is javaScript object-> need ro convert to json
            dataType: 'json',
            success: function (newdto) {
            	/* window.location.href = "${editNewURL}?id="+newdto.id+"&message=update_success"; */
            	window.location.href = "${editThumbnailURL}?id="+newdto.id+"&categoryCode="+newdto.categoryCode;
            },
            error: function (error) {
            	window.location.href = "${editNewURL}?id="+newdto.id+"&message=error_system";
            }
        });
	}
	
</script>
 
</body>
</html>
