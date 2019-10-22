<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
  <meta charset="UTF-8">
  <title>Document</title>
</head>
<body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<div><input type="file"><br>
<img src="" height="200" alt="Image preview area..." title="preview-img">
</div>
<script>
  var fileInput = document.querySelector('input[type=file]'),
          previewImg = document.querySelector('img');
  fileInput.addEventListener('change', function () {
	  
      var file = this.files[0];
      var reader = new FileReader();
      $("#ImagePic").attr("src","");
      // 监听reader对象的的onload事件，当图片加载完成时，把base64编码賦值给预览图片
      reader.addEventListener("load", function () {
          previewImg.src = reader.result;
      }, false);
      
      // 调用reader.readAsDataURL()方法，把图片转成base64
     reader.readAsDataURL(file);
    
        
  }, false);
  
  function myFunction() {
	  
	  $.ajax({
	         type:'POST',

	         //data:JSON.stringify(reader.readAsDataURL(file)),
	           
	        

	         url :'/picup',
	         data:{
	        	 "pic":previewImg.src
	         },
	         success:function(data){
	        	                 if(data=="error"){
	        	                     alert("没有检测到人脸");
	        	                 
	        	                 }else{
	        	                 $("#ImagePic").attr("src","data:image/gif;base64,"+data);
	        	                      }
	        	             }
	       }
	        	
	        	
	         ) 
  }
    
 </script>
 <button type="button" onclick="myFunction()">calaulate</button>
<div id="div">
<img id="ImagePic" src="" height="800" alt="Image return">
</div>

</body>
</html>

