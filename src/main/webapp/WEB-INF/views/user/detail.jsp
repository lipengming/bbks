<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/> 
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>社交网络-用户主页-动态</title>

<jsp:include page="../include/script.jsp" flush="false"></jsp:include>

</head>

<body>
<div id="warp">
	
	<jsp:include page="../include/header.jsp" flush="false"></jsp:include>
	
	<div id="layout">
    	<div class="person_info">
        	<div class="img">
        	<c:choose>
            	<c:when test="${userInfo.avatar == null }">
      				<img src="${ctxStatic}/images/reg_photo.gif" width="110" height="110" alt="" />	
      			</c:when>
      			<c:otherwise>
      				<img src="${userInfo.avatar }" width="110" height="110" alt="" />
      			</c:otherwise>
      		</c:choose>
            </div>
            <div class="info_co">
            	<h1><a href="#">${userInfo.username }</a></h1>
                <h3><a href="${ctx}/user/detail/${userInfo.id}">http://www.bbks.com/user/detail/${userInfo.id} </a></h3>
                <h4>类型：
                	<c:choose>
                		<c:when test="${userInfo.isCompany == 0}">
                			个人用户
                		</c:when>
                		<c:otherwise>
							企业用户
                		</c:otherwise>
                	</c:choose>
                </h4>
                <p>状态：${userInfo.description }</p>
                <div class="info_btn_cont">
               		<input type="button" value="私信"  class="info_btn sx"/>
                    <div class="sixin">
                    	<h2><span class="close"></span>发私信</h2>
                        <label>
                        	<span class="fn-left">发给：</span>
                            <div class="drop_sx">
                            	<span class="more_d"></span>
                        		<div class="sx_name"><img src="${userInfo.avatar }" width="24" height="24" />${userInfo.username }</div>
                            </div>
                        </label>
                        <form action="${ctx }/user/sendMessage" method="post">
	                        <label>
	                        	<span class="fn-left">内容：</span>
	                        	<input type="hidden" name="uid" value="${userInfo.id }"/>
	                        	<textarea name="message" cols="" rows="" class="sixin_text"></textarea>
	                        </label>
	                        <label><input type="button" class="fs" onclick="javasript:this.form.submit();"/></label>
                        </form>
                    </div>
                    <!-- <input type="button" value="@"  class="info_btn sp"/>
                    <div class="speak">
                    	<h2><span class="close"></span>快来说点什么吧...</h2>
                        <h4>还可以输入123个字</h4>
                        <textarea name="" cols="" rows="" class="speak_text"></textarea>
                        <div class="speak_fb">
                        	<div class="fs"></div>
                            <ul>
                            	<li><a href="#"></a></li>
                                <li><a href="#"></a></li>
                                <li><a href="#"></a></li>
                                <li><a href="#"></a></li>
                            </ul>
                        </div>
                    </div> -->
                </div>
            </div>
            <div class="person_gz">
            	<div class="gz_btn_cont">
            		
            		<c:choose>
            			<c:when test="${doFlow != null }">
							<form action="${ctx }/user/unflow?uid=${userInfo.id}" method="post">
								<input class="gz_btn_off" type="button" onclick="javascript:this.form.submit();"/>
							</form>
            			</c:when>
            			<c:otherwise>
            				<form action="${ctx }/user/flow?uid=${userInfo.id}" method="post">
            					<input class="gz_btn" type="button" onclick="javascript:this.form.submit();"/>
            				</form>
            			</c:otherwise>
            		</c:choose>
            	</div>
                <ul class="gz_list">
                	<li><a href="#"><span class="num">${userInfo.messages }</span>消息</a></li>
                    <li><a href="#"><span class="num">${userInfo.flowings }</span>关注</a></li>
                    <li><a href="#"><span class="num">${userInfo.floweds }</span>粉丝</a></li>
                </ul>
            </div>
        </div>
        
        <div id="ul_btn">
        	<ul>
        		<c:choose>
        			<c:when test="${empty type || type == 0}">
        				<li><a href="${ctx }/user/detail/${userInfo.id}?type=0" class="hover">随便看看</a></li>
        			</c:when>
        			<c:otherwise>
        				<li><a href="${ctx }/user/detail/${userInfo.id}?type=0">随便看看</a></li>
        			</c:otherwise>
        		</c:choose>
        		<c:choose>
        			<c:when test="${type == 1}">
        				<li><a href="${ctx }/user/detail/${userInfo.id}?type=1" class="hover">动态</a></li>
        			</c:when>
        			<c:otherwise>
            			<li><a href="${ctx }/user/detail/${userInfo.id}?type=1" >动态</a></li>
        			</c:otherwise>
        		</c:choose>
        		
        		<c:choose>
        			<c:when test="${type == 2}">
        				<li><a href="${ctx }/user/detail/${userInfo.id}?type=2" class="hover">评论</a></li>
        			</c:when>
        			<c:otherwise>
        				<li><a href="${ctx }/user/detail/${userInfo.id}?type=2" >评论</a></li>
        			</c:otherwise>
        		</c:choose>
        		
                
            </ul>
        </div>
        <div class="main">
        	<div class="con_list">
            	<div class="tab" id="tabBox">
                	
                    <div class="tab_cont">
                    	<div class="tab_con" style="display:block;">
                        	<ul class="tab_list W100">
                            	<c:if test="${empty type || type==0 }">
                            	<c:forEach var="item" items="${dynamicInfo }">	
                        		<li>
                        			<strong> ${userInfo.username }发表动态： </strong>
                                    <span class="list_con">
                                        ${item.content}
                                        <p class="bott">
                                        	<span class="p_function">
                                            	<a href="#">转发</a>|
                                                <a href="#">收藏</a>|
                                                <a href="#">评论</a>
                                            </span>
                                            <span class="time">
                                            	${item.createAt }
                                            </span>
                                        </p>
                                    </span>
                                </li>
                        		</c:forEach>
                        		
                        				<c:forEach items="${commentInfo}" var="comment">
			                           	<dl class="sp_list">
			                        	<dt><a href="#"><img src="${comment.avatar }" width="48" height="48" alt=""></a></dt>
			                            <dd>
			                            	<h3><span class="ds"></span><a href="#">${comment.title }</a></h3>
			                                <h4><a href="#">${comment.name }</a><em class="s_on"></em><em class="s_on"></em><em class="s_on"></em><em class="s_on"></em><em class="s_off"></em></h4>
			                                <p>
			                                	${comment.content }
			                                </p>
			                                <h5><span class="time">${comment.createDate }</span><span class="sf"><a href="#">转发</a>|<a href="#">收藏</a>|<a href="#">评论</a></span></h5>
			                            </dd>
			                        </dl>	
			                        </c:forEach>
			                        
                        		</c:if>
                        		
                        		<!-- type==1 -->
                        		
                        		<c:if test="${type==1 }">
                        			<c:forEach var="item" items="${dynamicInfo }">	
                        			<li>
                        			<strong> ${userInfo.username }发表动态： </strong>
                                    <span class="list_con">
                                        ${item.content}
                                        <p class="bott">
                                        	<span class="p_function">
                                            	<a href="#">转发</a>|
                                                <a href="#">收藏</a>|
                                                <a href="#">评论</a>
                                            </span>
                                            <span class="time">
                                            	${item.createAt }
                                            </span>
                                        </p>
                                    </span>
                                </li>
                        		</c:forEach>
                        		</c:if>
                        	
                        	
                        		<!-- type==1 -->
                        		<c:if test="${type==2 }">
                        			<c:forEach items="${commentInfo}" var="comment">
			                           	<dl class="sp_list">
			                        	<dt><a href="#"><img src="${comment.avatar }" width="48" height="48" alt=""></a></dt>
			                            <dd>
			                            	<h3><span class="ds"></span><a href="#">${comment.title }</a></h3>
			                                <h4><a href="#">${comment.name }</a><em class="s_on"></em><em class="s_on"></em><em class="s_on"></em><em class="s_on"></em><em class="s_off"></em></h4>
			                                <p>
			                                	${comment.content }
			                                </p>
			                                <h5><span class="time">${comment.createDate }</span><span class="sf"><a href="#">转发</a>|<a href="#">收藏</a>|<a href="#">评论</a></span></h5>
			                            </dd>
			                        </dl>	
			                        </c:forEach>
                        		</c:if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="sidebar">
            	<div class="side_detail">
                	<h2>个人资料.......</h2>
                    <h4>家在：${userInfo.address }</h4>
                    <h4>微博: <a href="#">${userInfo.weibo }</a></h4>
                    <h4>博客: <a href="#">${userInfo.blogs }</a></h4>
                    <p>自述：${userInfo.introduction }</p>
                    <h5><a href="#">更多...</a></h5>
                </div>
             
               <dl class="my_book">
                	<dt><h3><a href="${ctx }/bs/index?uid=${userInfo.id}">她的书架</a></h3></dt>
                    <dd><a href="#">在读 （${userInfo.reading}）</a></dd>
                    <dd><a href="#">想读 （${userInfo.wantRead}）</a></dd>
                    <dd><a href="#">已读 （${userInfo.hasRead}）</a></dd>
                </dl>
                
               <div class="interest_person">
                	<div class="title">
                    	<h3><a href="#">最近在……</a></h3>
                    </div>
                    <div class="list">
                    	<ul>
                        	<c:forEach items="${invos }" var="vo">
                        		<li>
                            	<a href="${ctx }/book/search/${vo.book.id}"><img src="${vo.book.coverPic }" width="92" alt=""/></a>
		                    	 <span class="book_info" style="display: none; position: absolute; top: 237px; left: 1049px;">
		                                <h2><a href="#">${vo.book.bookName }</a></h2>
		                                <h3><span class="yizhe fn-right">译者：<a href="#">${vo.book.translator }</a></span>作者：<a href="#">${vo.book.author }</a></h3>
		                                <h3>出版社：<a href="#">${vo.book.press }</a></h3>
		                                <h4>定价：${vo.book.pubPrice }元</h4>
		                                <p>
		                                	${fn:substring(vo.book.outline,0,100)}
		                            		...
		                                </p>
		                                <span class="book_info_btn">
		                                    <input type="button" class="text">
		                                    <input type="button" class="join">
		                                </span>
		                          </span>
                             </li>
                        	</c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
