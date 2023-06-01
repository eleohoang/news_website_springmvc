<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="newAPI" value="/api/new"/>
<c:url var="newURL" value="/quan-tri/bai-viet/danh-sach"/>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Danh sách bài viết</title>
</head>

<body>
	<div class="main-content">
		<form action="<c:url value='/quan-tri/bai-viet/danh-sach'/>" id="formSubmit" method="get">
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				
					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="<c:url value ="/quan-tri/trang-chu"/>">Trang quản trị</a>
						</li>
						
						<li class="active"><a href="#">Danh sách</a></li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
						<c:if test="${not empty message}">
							<div class="alert alert-${alert}">${message}</div>
						</c:if>
							<div class="row">
							<div class="widget-box table-filter">
										<div class="table-btn-controls">
											<div class="pull-right tableTools-container">
												<div class="dt-buttons btn-overlap btn-group">
													<c:url var="createNewURL" value="/quan-tri/bai-viet/chinh-sua"/>
													<a flag="info"
														class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
														data-toggle="tooltip" title='Thêm bài viết'
														href='${createNewURL}'> <span>
															<i class="fa fa-plus-circle bigger-110 purple"></i>
													</span>
													</a>
													<button id="btnDelete" type="button" onclick="warningBeforeDelete()"
														class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
														data-toggle="tooltip" title='Xóa bài viết' disabled>
														<span> <i class="fa fa-trash-o bigger-110 pink"></i>
														</span>
													</button>
												</div>
											</div>
										</div>
									</div>
								<div class="col-xs-12">
									
									<div class="table-responsive">
										<table class="table table-bordered">
											<thead>
												<tr>
													<th><input title="Chọn tất cả" type="checkbox" id ="checkAll" onclick='checkUncheckAll(this)'></th>
													<th>Tên bài viết</th>
													<th>Mô tả ngắn</th>
													<th>Thao tác</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${model.listResult}">
													<tr>
														<td><input name ="check" type="checkbox" id ="checkbox_${item.id}"value="${item.id}" onclick ="checkbtnDelete(this)"></td>
														<td>${item.title}</td>
														<td>${item.shortDescription}</td>
														<td>
															<c:url var="updateNewURL" value="/quan-tri/bai-viet/chinh-sua">
																<c:param name="id" value="${item.id}" />
															</c:url> 
															<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip" title="Cập nhật bài viết"
															href='${updateNewURL}'><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
															</a>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<ul class="pagination" id="pagination"></ul>
										<input type="hidden" value="" id="page" name="page" />
										<input type="hidden" value="" id="limit" name="limit"/>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- /.main-content -->
	 <script>
	 var totalPages = ${model.totalPage};
	 var currentPage = ${model.page};
	 var totalItem = ${model.totalItem};
		$(function () {
	        window.pagObj = $('#pagination').twbsPagination({
	            totalPages: totalPages,
	            visiblePages: 10,
	            startPage: currentPage,
	            onPageClick: function (event, page) {
	            	if (currentPage != page) {
	            		$('#limit').val(5);
						$('#page').val(page);
						$('#formSubmit').submit();
					}
	            }
	        });
	    });
		
	var x = 0; 
	
	function checkbtnDelete(check) {
		if (check.checked == true) { 
			x++;
		} else {
			x--;
		}
		if (x>0) {
			document.getElementById("btnDelete").disabled = false;
		} else {
			document.getElementById("btnDelete").disabled = true;
		}
	}
	
	function checkUncheckAll(checkBox) {
		get = document.getElementsByName('check');
		for(var i=0; i<get.length; i++) {
			get[i].checked = checkBox.checked;
			x++;
		} 
		if (checkBox.checked == true) {
			if (x>0) {
				document.getElementById("btnDelete").disabled = false;
			}
		} else {
			document.getElementById("btnDelete").disabled = true;
			x=0;
		}
	}
	
	function warningBeforeDelete() {
		swal({
		  title: "Xác nhận xóa",
		  text: "Bạn có chắc chắn muốn xóa hay không?",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonClass: "btn-success",
		  cancelButtonClass: "btn-danger",
		  confirmButtonText: "Xác nhận",
		  cancelButtonText: "Hủy bỏ",
		}).then (function(isConfirm){
			   if (isConfirm.value === true){
				var ids = $('tbody input[type=checkbox]:checked').map(function () {
		            return $(this).val();
		        }).get();
				deleteNew(ids);
		  }
		});
	}
	
	function deleteNew(data) {
        $.ajax({
            url: '${newAPI}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${newURL}?page=1&limit=5&message=delete_success";
            },
            error: function (error) {
            	window.location.href = "${newURL}?page=1&limit=5&message=error_system";
            }
        });
    }
	
	</script>
</body>

</html>