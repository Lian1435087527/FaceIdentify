<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="sf"  uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
    
    <form method="post" enctype="multipart/form-data">
        Username:<input type="text" name="username"/> <sf:errors path="username"/>
        Password:<input type="password" name="password"/> <sf:errors path="password"/>
        Nickname:<input type="text" name="nickname"/></br>
        Email<input type="text" name="email"/>
            
    </form>
</body>
</html>