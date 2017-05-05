<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>使用jsp和jdbc手动事务演示转账功能</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/accountAction" method="post">
转出方：<input type="text" name="fromuser">
<br>
转入方：<input type="text" name="touser">
<br>
金额：<input type="text" name="money">
<input type="submit" value="转账">
</form>
</body>
</html>