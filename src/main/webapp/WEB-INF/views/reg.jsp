<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath }"/> 


<html>
<head>
<base href="${ctx}/static/"/>
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
    	<a href="#"><img src="${ctx}/static/images/reg_logo.gif" width="186" height="94" alt="" /></a>
    </div>
    <div class="reg_fg"></div>
    <div class="other_reg">
    	<h2>你可以选择下方合作网站账号直接登录书管家，一分钟完成注册</h2>
        <ul>
        	<li><a href="#"></a></li>
            <li><a href="#"></a></li>
            <li><a href="#"></a></li>
            <li><a href="#"></a></li>
        </ul>
    </div>
    <div class="reg_step">
    	<h2>你也可以直接注册书管家，三步完成注册</h2>
        <form action="${ctx }/regist" method="post">	
	        <ul>
	        	<li class="use_reg"><input name="username" id="reg_name" class="text foucs_text" type="text" value="昵称" /></li>
	            <li class="email_reg"><input name="email" id="reg_email" class="text foucs_text" type="text"  value="登录邮箱地址"/></li>
	            <li class="pass_reg"><input name="password" id="reg_pwd" class="text foucs_text" type="text"  value="密码"/></li>
	            <li class="pass_reg"><input name="repassword" id="reg_repwd" class="text foucs_text" type="text" value="密码" /></li>
	        </ul>
	        <h4>我以认真阅读并接受<a href="#">《笔墨酷免责声明》</a></h4>
	        <input type="submit" class="reg_btn"  /><!-- onclick="reg('${ctx}/regist');"  -->
        </form>
    </div>
</div>
</body>
</html>
