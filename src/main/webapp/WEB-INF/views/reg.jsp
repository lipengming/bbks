<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath }"/> 


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>
<link href="${ctx}/static/css/common.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/reg.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/static/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${ctx}/static/js/reg.js" type="text/javascript"></script>
</head>

<body>
<div class="W890">
	<div class="reg_logo">
    	<a href="${ctx}">
    		<img src="${ctx}/static/images/logo.gif" width="186" height="94" alt="" />
    	</a>
    </div>
    <div class="reg_fg">
    	<strong style="background-color: red;">
    		${error}
    	</strong>
    </div>
    
    <div class="reg_step">
        <form action="${ctx }/regist" method="post" id="register_form">	
	        <ul>
	        	<li class="use_reg"><input name="username" id="reg_name" class="text foucs_text" type="text" value="昵称" /></li>
	            <li class="email_reg"><input name="email" id="reg_email" class="text foucs_text" type="text"  value="登录邮箱地址"/></li>
	            <li class="pass_reg"><input name="password" id="reg_pwd" class="text foucs_text" type="text"  value="密码"/></li>
	            <li class="pass_reg"><input name="repassword" id="reg_repwd" class="text foucs_text" type="text" value="密码" /></li>
	        </ul>
	        <h4>我以认真阅读并接受<a href="#">《墨屋免责声明》</a></h4>
	        <input class="reg_btn" onclick="register();"/>
        </form>
    </div>
</div>
</body>
</html>
