<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/> 
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>书城</title>

<jsp:include page="../include/script.jsp" flush="false"></jsp:include>

</head>
<body>

<jsp:include page="../include/header.jsp" flush="false"></jsp:include>
<!-- menu bar start-->
<div id="index_warp">
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
        <div class="ul_menu">
            <a href="#" class="mulu">目  录</a> 
            <div class="ml_con">
            	<h3>《2001：太空漫游》</h3>
                <p>悼库布里克 1</p>
            </div>
            <a href="#" class="online">在线试读</a>
            <div class="online_con">
            	<ul>
                	<li><a href="#">当当读书</a></li>
                </ul>
            </div>
        </div>
        
    </div>
</div>
<!-- menu bar end-->

<!-- book detail start-->
<div  id="pages_book">
	<div class="W960 bg_F fn-clear">
	
		<!-- left start -->
    	<div class="W600">
        	<!-- start base info -->
        	<div class="gb_img">
            	<img src="${bookInfo.coverPic }" width="188" height="258"  alt="" />
            	<div class="gb_img_btn">
                	<input type="button" class="gb_img_btn_1" />
                    <input type="button" class="gb_img_btn_2" />
                </div>
                <div class="gb_img_span">
                	<a href="#">想读</a> <a href="#">在读</a> <a href="#">已读</a>
                </div>
            </div>
            <div class="gb_con">
            	<h1>${bookInfo.bookName }</h1>
                <div class="star">
                	<em class="gb_star"></em><em class="gb_star"></em><em class="gb_star"></em><em class="gb_star"></em><em class="gb_star"></em>
                    <span class="fs">4.2</span>
                    <span class="pj">（126人评级）</span>
                </div>
                <div class="text">
                	<p>${bookInfo.outline }</p>
                    
                </div>
                <span class="sq">收起</span>
                <div class="con_info">
                	<ul>
                    	<li><span>作者：</span><a href="#">${bookInfo.author }</a></li>
                        <li><span>译者：</span>${bookInfo.translator }</li>
                        <li><span>出版社：</span>${bookInfo.press }</li>
                        <li><span>版本：</span>${bookInfo.version }</li>
                        <li><a href="#">>更多</a></li>
                    </ul>
                </div>
                <div class="share">
                	<a href="#" class="write">写书评</a>|<a href="#" class="spa">@</a>|<a href="#" class="sha">分享<em></em></a>
                    <div class="speak_s">
                    	<h2>@</h2>
                        <div class="s_con">
                        	<textarea name="" cols="" rows="" class="s_text_t"></textarea>
                            <input type="button" class="sure_btn" />
                        </div>
                    </div>
                    <div class="share_p">
                    	<h2><a href="#">分享<em></em></a></h2>
                        <ul>
                        	<li>新浪微博</li>
                            <li>QQ空间</li>
                            <li>人人网</li>
                            <li>腾讯微博</li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- end of base info -->
            
            
            <!-- start commit and share info -->
            <div class="gb_tab" id="tabBox">
            	<ul class="gb_ul">
                	<li  class="active">图书概述</li>
                    <li>书评笔记</li>
                    <li>图书微博</li>
                    <li>图书推荐</li>
                </ul>
                <div class="gb_tab_k">
                	<div class="gb_tab_con"  style="display:block;">
                    	<dl class="gb_list">
                        	<dt><span class="more_show"><a href="#">18条相似结果 »</a></span><h3>内容介绍</h3></dt>
                            <dd>
                            	<p>
                                	《创新与企业家精神》人人都知道创新的重要性，激烈的竞争，瞬息万变的市场和技术已经让人们对此深信不
疑，但关键问题是，该如何进行创新呢？创新是每位高层管理者的职责，   它始于有意识地寻找机遇。  如果你
懂得在哪里以及如何寻找创新机遇，  你就能系统化地管理创新；  如果你懂得运用创新的原则，你就能使创新
发展为可行的事来。 这就是德鲁克在《创新与企业家精神》   中为我们揭示的重点
                                </p>
                            </dd>
                        </dl>
                    </div>
                	<div class="gb_tab_con">
                    	<dl class="sp_list">
                    	无
                        </dl>
                    </div>
                    <div class="gb_tab_con">
                    	<dl class="gb_list">
                        	<dt><span class="more_show"><a href="#">18条相似结果 »</a></span><h3>内容介绍</h3></dt>
                            <dd>
                            	<p>
                                	《创新与企业家精神》人人都知道创新的重要性，激烈的竞争，瞬息万变的市场和技术已经让人们对此深信不
疑，但关键问题是，该如何进行创新呢？创新是每位高层管理者的职责，   它始于有意识地寻找机遇。  如果你
懂得在哪里以及如何寻找创新机遇，  你就能系统化地管理创新；  如果你懂得运用创新的原则，你就能使创新
发展为可行的事来。 这就是德鲁克在《创新与企业家精神》   中为我们揭示的重点
                                </p>
                            </dd>
                            
                        </dl>
                    </div>
                    <div class="gb_tab_con">
                    	<dl class="gb_list">
                        	<dt><span class="more_show"><a href="#">18条相似结果 »</a></span><h3>内容介绍</h3></dt>
                            <dd>
                            	<p>
                                	《创新与企业家精神》人人都知道创新的重要性，激烈的竞争，瞬息万变的市场和技术已经让人们对此深信不
疑，但关键问题是，该如何进行创新呢？创新是每位高层管理者的职责，   它始于有意识地寻找机遇。  如果你
懂得在哪里以及如何寻找创新机遇，  你就能系统化地管理创新；  如果你懂得运用创新的原则，你就能使创新
发展为可行的事来。 这就是德鲁克在《创新与企业家精神》   中为我们揭示的重点
                                </p>
                            </dd>                            
                        </dl>
                    </div>
                </div>
            </div>
            <!-- end commit and share info -->
            
        </div>
        <!-- left end -->
        
        <!-- right start -->
        <div class="W290">
        
  			<!--start book relation -->		      
        	<div class="reader">
            	<a href="#">298人想读</a>|<a href="#">91人读过</a>|<a href="#">5人已读</a><em></em>
                <div class="reader_list">
                	<ul>
                    	<li>162人喜欢</li>
                        <li>162人搜过</li>
                    </ul>
                </div>
            </div>
            <!--end book relation -->
            
            <!--start book price -->
            <div class="pb_bj">
            	<h2>定价：25元</h2>
                <dl class="pb_bj_list">
                	<dt>购买新书</dt>
                    <dd><span class="dd_money">￥18元</span><a href="#">当当网:</a></dd>
                    <dd><span class="dd_money">￥18元</span><a href="#">当当网:</a></dd>
                </dl>
                <dl class="pb_bj_list">
                	<dt>购买二手书</dt>
                    <dd><span class="dd_money">￥18元</span><a href="#">当当网:</a></dd>
                </dl>
                <dl class="pb_bj_list">
                	<dt>购买电子书</dt>
                    <dd><span class="dd_money">￥18元</span><a href="#">当当网:</a></dd>
                </dl>
            </div>
            <!--end book price -->
            
            <!--start book tag -->
            <div class="pb_book_span">
            	<h2><a href="#">书签（32个）</a></h2>
                <div class="span_list">
                	<ul> 
                    	<li><a href="#">管理</a>(1009)</li>
                        <li><a href="#">德鲁克</a>(725)</li>
                        <li><a href="#">管理的实践</a>(323)</li>
                        <li><a href="#">管理学</a>(254)</li>
                        <li><a href="#">管理经典</a>(163)</li>
                        <li><a href="#">大师</a>(62)</li>
                        <li><a href="#">管理的实践</a>(323)</li>
                        <li><a href="#">管理学</a>(254)</li>
                        <li><a href="#">管理经典</a>(163)</li>
                        <li><a href="#">大师</a>(62)</li>
                    </ul>
                    <p><a href="#">>更多</a></p>
                </div>
            </div>
          	<!-- end book tag -->
       		 
        </div>
   		<!-- right ended -->
   </div>
</div>
<!-- book detail end-->

<jsp:include page="../include/index_login.jsp" flush="false"></jsp:include>
<jsp:include page="../include/index_reg.jsp" flush="false"></jsp:include>
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

 