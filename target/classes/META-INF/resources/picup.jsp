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

<form name="test">
<input type="text" name="test1" value="fdsahtjdasf">
</form>
</div>
<script>
  var fileInput = document.querySelector('input[type=file]'),
          previewImg = document.querySelector('img');
  fileInput.addEventListener('change', function () {
	  
      var file = this.files[0];
      var reader = new FileReader();
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

	         success :function(data) {

	             alert("OK");

	         },

	       error :function(e) {

	           alert("error");
	        }}) ;
	        document.test.test1.value="7r7r";
  }
    
 </script>
 <button type="button" onclick="myFunction()">calaulate</button>
</body>
</html>

