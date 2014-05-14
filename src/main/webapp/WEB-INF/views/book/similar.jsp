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
<title>书架</title>
<jsp:include page="../include/script.jsp" flush="false"></jsp:include>

<script type="text/javascript">

</script>

</head>
<body>
<div id="warp">
	<jsp:include page="../include/header.jsp" flush="false"></jsp:include>

<div id="layout">
    	<div class="circle_menu">
        		<ul>
            	<li  class="active"><a href="${ctx }/bs/similar?uid=${userInfo.id }">和我相似</a></li>
	        	<li><a href="${ctx }/bs/index?uid=${userInfo.id }">我的兴趣</a></li>	 
                <li><a href="${ctx }/bs/bookmarks?uid=${userInfo.id }">我的书签</a></li>
                <li ><a href="${ctx }/bs/donate?uid=${userInfo.id }" >贡献图书</a></li>
            </ul>
        </div>
        <div class="main">
        	<div class="circle_left">
            	
                <dl class="book_list">
                	<dt>图书</dt>
                    <dd><a href="${ctx }/bs/similar?uid=${userInfo.id}">全部 </a></dd>
                    <dd><a href="${ctx }/bs/similar?uid=${userInfo.id}&type=0">搜过相似 </a></dd>
                    <dd><a href="${ctx }/bs/similar?uid=${userInfo.id}&type=1">在读相似 </a></dd>
                    <dd><a href="${ctx }/bs/similar?uid=${userInfo.id}&type=2">想读 相似</a></dd>
                    <dd><a href="${ctx }/bs/similar?uid=${userInfo.id}&type=3">已读相似</a></dd>
                    <dd><a href="${ctx }/bs/similar?uid=${userInfo.id}&type=4">喜欢 相似</a></dd>
                </dl>
            </div>
            <div class="circle_right">
            	<div class="interest_me">
                	<h2>最近我做过：</h2>
                    <!-- <p>1738人和我兴趣相同，1238人和我搜过相同的书……</p> -->
                </div>
              <div class="interest_list">
               		<c:forEach var="vo" items="${invos }">
               			 <div class="hd">
		                	<div class="img">
		                    	<a href="#"><img src="${vo.user.avatar }" width="48" height="48" alt=""/></a>
		                        <input type="button" class="fsx_btn sx_click" value="私信"/>
		                        <span class="sixin">
		                              <h2><span class="close"></span>发私信</h2>
		                              <label>
		                                  <span class="fn-left">发给：</span>
		                                  <span class="drop_sx">
		                                      <span class="more_d"></span>
		                                      <p class="sx_name"><img src="${vo.user.avatar }" width="24" height="24"/>${vo.user.username }</p>
		                                  </span>
		                              </label>
		                              <label>
		                                  <span class="fn-left">内容：</span><textarea name="" cols="" rows="" class="sixin_text"></textarea>
		                              </label>
		                              <label><input type="button" class="fs"/></label>
		                          </span>
		                          <!-- pop -->
		                          <span class="read_info" style="display: inline; position: absolute; top: 107px; left: 391px;">
		                              <span class="read_info_base">
		                                  <img src="${vo.user.avatar }" width="40" height="40" alt=""/>
		                                  <h2>${vo.user.username }</h2>
		                                  <h4><span>关注</span> ${vo.user.floweds } | <span>粉丝</span> ${vo.user.flowings } | </h4>
		                                  <p>${vo.user.description }</p>
		                              </span>
		                              <span class="read_info_btn">
		                                  <input type="button" class="btn"/>
		                          </span>
		                    </div>
		                    <div class="bd">
		                    	<div class="tit">
		                        	
            			<c:choose>	
            					<c:when test="${vo.user.doFlow}">
									<form action="${ctx }/user/unflow?uid=${vo.user.id}" method="post">
										<input class="guanz_btn guanz_btn_on" type="button" onclick="javascript:this.form.submit();"/>
									</form>
		            			</c:when>
		            			<c:otherwise>
		            				<form action="${ctx }/user/flow?uid=${vo.user.id}" method="post">
		            					<input class="guanz_btn" type="button" onclick="javascript:this.form.submit();"/>
		            				</form>
		            			</c:otherwise>
		            		</c:choose>
		                            <h2><a href="#">${vo.user.username }</a></h2>
		                            <h3>最近${vo.type}过《<a href="#">${vo.book.bookName }</a>》</h3>
		                            <h4>${vo.user.description }</h4>
		                            <h5 class="time">${vo.interest.createdAt}</h5>
		                            <div class="bd_zs">
		                            	${fn:substring(vo.book.outline,0,200)}
		                            	...
                            		</div>
		                            
		                        </div>
		                    </div>
		                    <div class="bk_img">
		                    	<a href="#"><img src="${vo.book.coverPic }" width="92" alt=""/></a>
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
		                          </span>
		                    </div>
		                </div>
               			
               		</c:forEach>
              
              	<!-- end -->
              </div>
            </div>
        </div>
    </div>
 </div> 
 <script type="text/javascript"> 
 function tabMenu(tabBox,navClass){
  var tabNavLi=document.getElementById(tabBox).getElementsByTagName("ul")[0].getElementsByTagName("li");
  var tabCon=document.getElementById(tabBox).getElementsByTagName("div")[0].getElementsByTagName("div");
  var tabLens=tabCon.length;
  for(var i=0;i<tabLens;i++){
  //应用js闭包传入参数i作为当前索引值赋值给m
    (function(m){
   tabNavLi[m].onclick = function(){
    for(var j=0; j<tabLens; j++){
     tabNavLi[j].className = (j==m)?navClass:"";
     tabCon[j].style.display = (j==m)?"block":"";
    }
   }
    })(i); 
  }
 }
//函数调用
window.onload=function(){
 tabMenu("tabBox","active");
}
</script>  
</body>
</html>