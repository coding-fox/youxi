<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxfileupload.js"></script>  
<script type="text/javascript">
	function ajaxFileUpload(){  
    //开始上传文件时显示一个图片,文件上传完成将图片隐藏  
    //$("#loading").ajaxStart(function(){$(this).show();}).ajaxComplete(function(){$(this).hide();});  
    //执行上传文件操作的函数  
	    $.ajaxFileUpload({  
	        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)  
	        url:'${pageContext.request.contextPath}/qnyh/collect/validData?uname=admins',  
	        secureuri:false,                           //是否启用安全提交,默认为false   
	        fileElementId:'myBlogImage',               //文件选择框的id属性  
	        dataType:'text',                           //服务器返回的格式,可以是json或xml等  
	        success:function(data, status){            //服务器响应成功时的处理函数  
	           $("#result").html(data);
	        },  
	        error:function(data, status, e){ //服务器响应失败时的处理函数  
	            $('#result').html('图片上传失败，请重试！！');  
	        }  
	    });  
	}  
</script>
</head>
<body>
	
	  
	<input type="file" id="myBlogImage" name="myfiles"/>  
	<input type="button" value="上传文件" onclick="ajaxFileUpload()"/>  
	<div id="result"></div>  
</body>
</html>