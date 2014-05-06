<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxRes" value="${pageContext.request.contextPath }/static"/>  


<div class="box_login">
    <div id="login">
        <div class="close"></div>
        <div class="login_title">
            登录墨屋
        </div>
        <div class="login_con">
            <div class="login_zh">
                <dl>
                    <dt>使用合作网站账号登录</dt>
                    <dd><a href="#"><img src="${ctxRes }/images/zh_1.gif" width="135" height="36" alt="" /></a></dd>
                    <dd><a href="#"><img src="${ctxRes }/images/zh_2.gif" width="135" height="36" alt="" /></a></dd>
                    <dd><a href="#"><img src="${ctxRes }/images/zh_3.gif" width="135" height="36" alt="" /></a></dd>
                    <dd><a href="#"><img src="${ctxRes }/images/zh_4.gif" height="37" width="134" alt="" /></a></dd>
                </dl>
                <p>未注册墨屋也可以直接登录！</p>
            </div>
            <div class="login_main">
                <div class="lt">使用注册邮箱登录</div>
                <div class="ld user"><input type="text" name="username" id="login_name"/></div>
                <div class="ld pass1"><input type="text" name="pwd" id="login_pwd"/></div>
                <div class="lg"><span class="fotegt"><a href="#">忘记了密码？</a></span>
                <input type="button" class="l_btn" onclick=""/></div>
            </div>
        </div>
    </div>
    <div class="shadow">
    </div>
</div>
