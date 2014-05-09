<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
            	<li><a href="#" class="hover">动态</a></li>
                <li><a href="#" class="pl">评论</a></li>
                <li><a href="#">随便看看</a></li>
            </ul>
        </div>
        <div class="main">
        	<div class="con_list">
            	<div class="tab" id="tabBox">
                	
                    <div class="tab_cont">
                    	<div class="tab_con" style="display:block;">
                        	<ul class="tab_list W100">
                            	<c:forEach var="item" items="${dynamicInfo }">	
                        		<li>
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
                <!-- 
                <div class="sider_span">
                	<h2>爱好</h2>
                    <p>
                    	<a href="#">摄影</a> 
                    	<a href="#">户外</a> 
                    	<a href="#">创业36条军规</a> 
                    	<a href="#">北京大学</a> 
                    	<a href="#">拉卡拉</a>
                    </p>
                </div>
                 -->
                <div class="now_read" style="z-index:3">
                	<div class="title">
                    	<h3><a href="#">共同兴趣.......</a></h3>
                    </div>
                    <div class="read_list">
                    	<ul>
                        	<li>
                            	<a href="#" title=""><img src="../images/img1.gif" width="67" height="99" alt="" /></a><span class="book_info">
                                <h2><a href="#">上面好安静</a></h2>
                                <h3><span class="yizhe fn-right">译者：<a href="#">周林</a></span>作者：【荷兰】<a href="#">比克</a></h3>
                                <h3>出版社：<a href="#">人们出版社</a></h3>
                                <h4>定价：30元</h4>
                                <p>
                                《故事发生在荷兰的乡间。亨克和赫尔默是一对双胞胎兄
弟，弟弟亨克勤于农活，深受父亲欢心，哥哥赫尔默不喜
欢农场，渴望去城市生活，因此与父亲关系疏远。谁料，
年轻的弟弟在一场车祸中丧生，一心想离开农场的赫尔默
被迫中断大学学业，从此与 牛羊为伍……
                                </p>
                                <span class="book_info_btn">
                                    <input type="button" class="text" />
                                    <input type="button" class="join" />
                                </span>
                            </span>
                             </li>
                            
                        </ul>
                    </div>
                </div>
               <div class="interest_person">
                	<div class="title">
                    	<h3><a href="#">兴趣相同的人……</a></h3>
                    </div>
                    <div class="list">
                    	<ul>
                        	<li>
                                <a href="#">
                                	<img src="../images/img2.gif" width="48" height="48" alt="" />
                                    <h5>W</h5>
                                </a>
                                <div class="read_info">
                                    <div class="read_info_base">
                                        <img src="../images/reader.jpg" width="40" height="40" alt="" />
                                        <h2>孙陶然</h2>
                                        <h4><span>关注</span> 440 | <Span>粉丝</Span> 280万 | <Span>微博</Span> 2867</h4>
                                        <p>拉卡拉支付有限公司创始人，董事长兼总裁</p>
                                    </div>
                                    <div class="read_info_btn">
                                        <input type="button" class="btn" />
                                    </div>
                                </div>
                            </li>
                            
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<jsp:include page="../include/bookshelf_pop.jsp" flush="false"></jsp:include>


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
