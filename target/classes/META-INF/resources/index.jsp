<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">

<title>3d智能云</title>
</head>

     <script type="text/javascript">
            function facedetect()
	        {
	           
	           
	                 var url="faceDetect.html";
	                 window.location.href=url;
	           
	        }
            function picup()
	        {
	           
	           
	                 var url="picup.html";
	                 window.location.href=url;
	           
	        }
            function picdown()
	        {
	           
	           
	                 var url="picdown.html";
	                 window.location.href=url;
	           
	        } 
            function modelup()
	        {
		           
		           
                var url="modelup.html";
                window.location.href=url;
          
       }    function login()
       {
           
           
           var url="login.html";
           window.location.href=url;
     
  }
            </script>
<link href="/css/index.css" type="text/css" rel="stylesheet" />
<body>
	<div class="topmenu cbody1">
		<ul class="menu">
			<li class="thisclass"><A href="#" onclick="picup()">上传数据</A></li>

			<li><A href="#" onclick="facedetect()">在线人脸检测</A></li>
			<li><A href="#" onclick="picdown()">下载数据</A></li>
			<li><A href="#" onclick="modelup()">上传模型</A></li>



		</ul>
	</div>
	<div class="foot">
		<A href="#" onclick="login()">登录</A>
	</div>
</body>
</html>