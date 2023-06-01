<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="commentAPI" value="/api/comment"/>
<c:url var="postURL" value="/chi-tiet-bai-viet"/>
<html>
<head>
<meta charset="UTF-8">
<title>Nội dung bài viết</title>
 <!-- css -->
	<%-- <link rel="stylesheet" href="<c:url value='/template/web/css/stylesdetail.css'/>" type="text/css" media="all"> --%>
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
			<!-- /.breadcrumb -->
			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="<c:url value ="/trang-chu"/>">Trang chủ</a></li>
			</ul>
		</div>
   <!-- Page content-->
   <div class="container mt-5">
       <div class="row">
      			<c:if test="${not empty message}">
				<div class="alert alert-${alert}">${message}</div>
				</c:if>
           <div class="col-lg-12">
               <!-- Post content-->
               <article>
                   <!-- Post header-->
                   <header class="mb-4">
                       <!-- Post title-->
                       <h1 class="fw-bolder mb-1">${model.title}</h1>
                       <!-- Post meta content-->
                       <div class="text-muted fst-italic mb-2">Posted on ${model.createdDate} by ${model.createdBy}</div>
                       <!-- Post categories-->
                       <!-- <a class="badge bg-secondary text-decoration-none link-light" href="#!">Web Design</a>
                       <a class="badge bg-secondary text-decoration-none link-light" href="#!">Freebies</a>
                        -->
                   </header>
                   <!-- Preview image figure-->
                   <!-- <figure class="mb-4"><img class="img-fluid rounded" src="https://dummyimage.com/900x400/ced4da/6c757d.jpg" alt="..." /></figure> -->
                   <!-- Post content-->
                   <section class="mb-5">
                       <p>${model.content}</p>
                   </section>
               </article>
               <!-- Comments section-->
               <section class="mb-5">
                   <div class="card bg-light">
                       <div class="card-body">
                           <!-- Comment form-->
                           <form:form class="mb-4" role="form" id="formSubmit" modelAttribute="commentmodel" >
                           		<form:textarea cssClass="form-control" rows="5" placeholder="Join the discussion and leave a comment!" path="content" id="content"/>
                          	    <div class="float-right"><br>
		                          	    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
		  							 	<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"></path>
		  									<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"></path>
										</svg>
										 &nbsp ${user.userName} &nbsp
                                       <form:hidden path="id" id="commentId"/>
                                      <%--  <form:hidden path="newId" id="newId" value=""/>
                                       <form:hidden path="userId" id="userId" value=""/>  --%>
										<button class="btn btn-info" type="button" id="btnAddComment">
											<i class="ace-icon fa fa-check bigger-110"></i>
											Gửi
										</button>  
									  </div>
									
                          </form:form>
                           </div>
                               
                          
                           <!-- Comment with nested comments-->
                         <!--   <div class="d-flex mb-4">
                                //Parent comment
                               <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /></div>
                               <div class="ms-3">
                                   <div class="fw-bold">Commenter Name</div>
                                   If you're going to lead a space frontier, it has to be government; it'll never be private enterprise. Because the space frontier is dangerous, and it's expensive, and it has unquantified risks.
                              //Child comment 1
                                   <div class="d-flex mt-4">
                                       <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /></div>
                                       <div class="ms-3">
                                           <div class="fw-bold">Commenter Name</div>
                                           And under those conditions, you cannot establish a capital-market evaluation of that enterprise. You can't get investors.
                                       </div>
                                   </div>
                                   //Child comment 2
                                   <div class="d-flex mt-4">
                                       <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /></div>
                                       <div class="ms-3">
                                           <div class="fw-bold">Commenter Name</div>
                                           When you put money directly to a problem, it makes a good headline.
                                       </div>
                                   </div>
                               </div>
                           </div>
                           -->
                          <!-- Single comment-->
                         <c:forEach var="commentmodel" items="${commentmodel.listResult}">
                           <div class="d-flex">
                               <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /></div>
                               <div class="ms-3">
                                   <div class="fw-bold"><b>${commentmodel.userName}</b></div>
                                   	<p>${commentmodel.content}</p>
                               </div>
                           </div>
                           </c:forEach>
                            <!-- Single comment-->
                       </div>
               	</section>
           	</div>
      		 </div>
        </div>
        </div>
        </div>
        
<script>
 $('#btnAddComment').click(function (e) {
	    e.preventDefault(); 
	    if (${user.id} == 0) {
	    	window.location.href = "/dang-nhap"
	    } else {
		    var data = {}; // create an array
		    var formData = $('#formSubmit').serializeArray(); // get all value from the formSubmit
		    $.each(formData, function (i, v) { 
	            data[""+v.name+""] = v.value;
	        });
		    data["newId"] = ${model.id};
		    data["userId"] = ${user.id};
		    
		    var id = $('#commentId').val();
		    if (id == "") {
		    	addComment(data); 
		    } else {
		    	updateComment(data);
		    }
	    }
	});
	
	function addComment(data) {
		$.ajax({
          	url: '${commentAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
           	dataType: 'json',
            success: function (commentdto) {
            	window.location.href = "${postURL}?postId="+${model.id}+"&message=insert_success";
            },
            error: function (error) {
            	window.location.href = "${postURL}?postId="+${model.id}+"&message=error_system";
            }  
        });
	}
	
	function updateComment(data) {
		$.ajax({
            url: '${commentAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data), //data is javaScript object-> need ro convert to json
            dataType: 'json',
            success: function (commentdto) {
            	window.location.href = "${postURL}?postId="+${model.id}+"&message=update_success"; 
            },
            error: function (error) {
            	window.location.href = "${postURL}?postId="+${model.id}+"&message=error_system";
            }
        });
	} 
</script>
</body>
</html>