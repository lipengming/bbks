<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>  
<c:set var="ctxRes" value="${pageContext.request.contextPath }/static"/>  
<!-- search bar start -->
	<div class="W960">
    	<div class="index_menu">
        	<ul>
            	<li><a href="#" class="index">书城</a></li>
                <li><a href="#">新书</a></li>
                <li><a href="#">特价</a></li>
                <li><a href="#">销售排行榜</a></li>
                <li><a href="#">搜索排行榜</a></li>
            </ul>
            <ul class="ul_user">
                <li><a href="#">兴趣</a></li>
                <li><a href="#">书架</a></li>
                <li><a href="#">社交网络</a></li>
            </ul>
        </div>
        <div class="screen_class">
        	<dl class="class">
            	<dt><a href="#" class="hover">全部</a></dt>
                <dd><a href="${ctx}/book/catlog?catlog=4">小说</a></dd>
                <dd><a href="${ctx}/book/catlog?catlog=5">外国文学</a></dd>
                <dd><a href="${ctx}/book/catlog?catlog=7">随笔</a></dd>
                <dd><a href="${ctx}/book/catlog?catlog=8">中国文学</a></dd>
                <dd><a href="${ctx}/book/catlog?catlog=9">经典</a></dd>
                
                <dd>
                	<a href="#" class="more" id="more_drop">更多</a>
                    <div class="more_div">
                    	<ul>
                        	<li><a href="${ctx}/book/catlog?catlog=4">小说</a></li>
			                <li><a href="${ctx}/book/catlog?catlog=5">外文学</a></li>
			                <li><a href="${ctx}/book/catlog?catlog=7">随笔</a></li>
			                <li><a href="${ctx}/book/catlog?catlog=8">中文学</a></li>
			                <li><a href="${ctx}/book/catlog?catlog=9">经典</a></li>
                        </ul>
                    </div>
                </dd>
            </dl>
            <div class="sort">
            	<dl>
                	<dt><a href="#">默认排序</a></dt>
                    <dd><a href="#">评级</a><em class="down"></em></dd>
                    <dd><a href="#">点评数</a><em class="down"></em></dd>
                    <dd><a href="#">价格</a><em class="down"></em></dd>
                </dl>
            </div>
        </div>
    </div>
    <!-- search bar end -->