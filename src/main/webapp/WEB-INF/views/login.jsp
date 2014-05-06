<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath }"/> 


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<link href="${ctx}/static/css/common.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/otherreg.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/static/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${ctx}/static/js/login.js" type="text/javascript"></script>
<script src="${ctx}/static/js/empty.js" type="text/javascript"></script>
</head>

<body>
<div id="otherreg">
	<div class="otherreg_logo">
    	<a href="${ctx }"><img src="${ctx}/static/images/logo.gif" width="186" height="94" alt="" /></a>
    </div>
	<div class="reg_step1">
    	<form action="${ctx }/login" method="post" id="login_form">
	        <ul>
	            <li class="email_reg"><input id="login_name" name="username" class="text foucs_text" type="text"  onfocus="emputyInput(this)"/></li>
	            <li class="pass_reg1"><input id="login_pwd" name="password" class="text foucs_text" type="password"  onfocus="emputyInput(this)"/></li>
	         </ul>
	        <input class="login_btn" onclick="login();"/>
	        <span class="forget"><a onclick="reset();" href="javascript:void(0)">忘记密码？</a></span>
	        <div class="someinfos">还没有墨屋的账号？<a href="${ctx }/regist">立即注册</a></div>
        </form>
    </div>
</div>
</body>
</html>
  