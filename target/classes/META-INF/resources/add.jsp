<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html lang="en">
<head>

    <meta charset="UTF-8">
    <title>SmartCloud智能云</title>
    <link href="/css/faceDetect.css" rel="stylesheet" type="text/css" />
</head>
<body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
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
      }, false);
      
      // 调用reader.readAsDataURL()方法，把图片转成base64
     reader.readAsDataURL(file);
    
        
  }, false);
  
  
 </script>
    <div id="main-body">
        <div class="select-show-img">
            <div class="img-frame">
             
                <img class="src-img" src= "FaceDetect/Images/scroll003.jpg"  alt="图片预览区" title="预览图片" id="ImagePic">
            </div>
            <div class="select-img">
                <div class="select-img-area">
                     <input type="text" spellcheck="false" alt="图片URL输入框" placeholder="请输入图片URL" class="input-url">
                     <button class="image-button">检测</button>
                     <div class="image-text">或</div>
                    <label class="image-local">

                        <button class="select-img-btn" type="button" onclick="document.getElementById('upload').click();">上传图片</button>
                    </label>
                </div>
            </div>
        </div>
    </div>
<input type="file" id="upload" hidden="hidden" ><br>
</body>
</html>