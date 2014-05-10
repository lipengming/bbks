<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<c:set var="ctxRes" value="${pageContext.request.contextPath }/static"/>  

<ul>
<c:forEach var="bookComment" items="${commentList}">
<li>
	<div class="user_img">
		<a href="${ctx }/">
			<img src="${bookComment.avatar }" width="26" height="26" alt="" />
		</a>
	</div>
    <div class="Ms">
    	<a href="${ctx }/book/search/${bookComment.contentId }">${bookComment.title }</a>:
    	${bookComment.content }
    </div>
</li>
</c:forEach>
</ul>
