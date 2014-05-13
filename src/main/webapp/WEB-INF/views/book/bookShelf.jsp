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
<div id="index_warp">
	<jsp:include page="../include/header.jsp" flush="false"></jsp:include>

<div id="layout">
    	<div class="circle_menu">
        	<ul>
            	<li class="active">
            		<a href="${ctx }/bs/index">我的兴趣</a>
            	</li>
            	 <li><a href="${ctx }/bs/similar">和我相似</a></li>
                <li><a href="${ctx }/bs/bookmarks">我的书签</a></li>
                <li><a href="${ctx }/bs/donate">贡献图书</a></li>
            </ul>
        </div>
        <div class="main">
        	<div class="circle_left">
            	
                <dl class="book_list">
                	<dt>图书</dt>
                    <dd><a href="${ctx }/bs/index?uid=${userInfo.id}">全部 </a></dd>
                    <dd><a href="${ctx }/bs/index?uid=${userInfo.id}&type=0">搜过 </a></dd>
                    <dd><a href="${ctx }/bs/index?uid=${userInfo.id}&type=1">在读 </a></dd>
                    <dd><a href="${ctx }/bs/index?uid=${userInfo.id}&type=2">想读 </a></dd>
                    <dd><a href="${ctx }/bs/index?uid=${userInfo.id}&type=3">已读</a></dd>
                    <dd><a href="${ctx }/bs/index?uid=${userInfo.id}&type=4">喜欢 </a></dd>
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
		                    </div>
		                    <div class="bd">
		                    	<div class="tit">
		                        	<input type="button" class="guanz_btn"/>
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