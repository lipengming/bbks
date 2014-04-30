<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<c:set var="ctxRes" value="${pageContext.request.contextPath }/static"/>  

<!-- wall flow start -->
<c:if test="${booksInfo != null}">
<c:forEach var="bookInfo" items="${booksInfo}">
	<!-- wall flow item -->
	    	<div class="pb">
            	<div class="s_btn"><input type="button" class="s_btn_1" /><input type="button" class="s_btn_2" /></div>
            	<div class="pb_img">
                	<a href="${ctx}/book/search/${bookInfo.id }"><img src="${bookInfo.coverPic }" width="158" height="217" alt="" /></a>
                </div>
                
                <div class="pb_num">
                	<span class="like"><em class="like-icon"></em>${bookInfo.likeCount }</span>
                    <span class="comments"><a href="#">评论</a>${bookInfo.commentCount }</span>
                </div>
                <div class="pb_message">
                	<ul>
                		<!--comment 
                    	<li>
                        	<div class="user_img"><a href="#"><img src="${ctxRes }/images/mes_img.gif" width="26" height="26" alt="" /></a></div>
                            <div class="Ms">
                            	<a href="#">风之埃尔夫</a>:什么是美？这又是一个基本的问题， 而又一次毫无意外地， 我们大多数人不知道答案。多。 .........
                            </div>
                        </li>
                        -->
                    </ul>
                </div>
                <div class="pb_write">
                	<input type="text" class="pb_write_text foucs_text" value="添加评论或者@好友" />
                    <div class="ph">
                    	<input type="button" class="btn" value="评论" />
                    </div>
                </div>
        	</div>	
	<!-- wall flow item -->
</c:forEach>
</c:if>
<!-- wall flow end -->