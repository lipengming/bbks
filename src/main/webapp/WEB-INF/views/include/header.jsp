<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>  
<c:set var="ctxRes" value="${pageContext.request.contextPath }/static"/>  


<c:choose>
<c:when test="${empty sessionScope._SIGN_USER_}">
<!-- unsigned -->
<div id="index_top" class="fn-clear">
	<div class="W960 header">
    	<div id="logo"><img src="${ctxRes }/images/logo.gif" width="93" height="42" alt="" /></div>
        <form action="<c:url value="/book/search"/>" method="post" id="index_search_form">
        	<div id="index_search">
	        	<input type="text" class="s_text foucs_text" value="搜索书名，作者、出版社等"/>
	            <input type="button" class="s_btn"/>
        	</div>
        </form>
        
        <div class="index_member">
        	<div class="drop_icon">
            </div>
            <div class="drop_icon_con">
            	<ul class="zy">
                	<li><a href="#">主页</a></li>
                </ul>
            	<dl>
                	<dt>笔墨酷手机客户端</dt>
                    <dd><a href="#">iPhone</a></dd>
                    <dd><a href="#">Android</a></dd>
                </dl>
                <ul class="zr">
                	<li><a href="#">找人</a></li>
                    <li><a href="#">兴趣相同的人</a></li>
                </ul>
                <ul class="help">
                	<li>关于&帮助</li>
                    <li><a href="#">用户反馈</a></li>
                    <li><a href="#">友情连接</a></li>
                    <li><a href="#">加入我们</a></li>
                </ul>
                <ul class="zh">
                    <li><a href="#">帐号设置</a></li>
                    <li><a href="#">退出登录</a></li>
                </ul>
            </div>
            <span class="fn-left login">
            	<input type="button" class="l_btn" value="登录" />
            </span>
            <span class="fn-left reg">
            	<input type="button" class="r_btn" value="注册" />
            </span>
        </div>
    </div>
</div>
</c:when>
<c:otherwise>
<!-- signed -->
<div id="top">
    	<div class="top_con">
            <div id="logos"><a href="#"><img src="${ctxRes }/images/logo.gif" width="93" height="42" alt=""></a></div>
            <div class="nav">
                <ul>
                    <li class="class">
                    	<a href="#">书城</a>
                        <dl class="sc_dlist" style="display: none;">
                        	<dd><a href="#">新书</a></dd>
                            <dd><a href="#">特价</a></dd>
                            <dd><a href="#">搜索排行榜</a></dd>
                            <dd><a href="#">销售排行榜</a></dd>
                        </dl>
                    </li>
                    <li><a href="#">兴趣</a></li>
                    <li><a href="../user_bookshelf.html">书架</a></li>
                    <li><a href="../user_index.html">社交网络</a></li>
                </ul>
            </div>
            <div class="search">
                <input type="text" class="search_text"> <input type="button" class="search_btn">
            </div>
            <div class="top_info">
                <span class="name"><a href="${ctx }/logout" class="drop" /><a href="${ctx }/user/profile/index"><c:out value="${sessionScope._SIGN_USER_.username }"></c:out></a></span>
                <span class="change"></span>
                <span class="mail">
                	<div class="mail_drop" style="display:none;">
                    	<div class="drop_mail"></div>
                        <div class="drop_list">
                            <ul class="tz">
                                <li class="">五条系统通知，<a href="#">查看消息</a></li>
                            </ul>
                            <ul class="ck">
                                <li><a href="#">查看私信</a></li>
                                <li><a href="#">查看@我</a></li>
                                <li><a href="#">查看评论</a></li>
                                <li><a href="#">系统通知</a>（5）</li>
                            </ul>
                            <ul class="fsx">
                                <li><a href="#">发私信</a></li>
                            </ul>
                         </div>
                    </div>
                </span>
            </div>
        </div>
    </div>
</c:otherwise>
</c:choose>