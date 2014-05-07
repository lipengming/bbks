<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/> 
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>社交网络-首页</title>

<jsp:include page="../include/script.jsp" flush="false"></jsp:include>

<script type="text/javascript">
	$(function(){
			$(".book_info").hide();
			$(".tab .tab_con .book_img").hover(
				function(){
					$(this).find(".book_info").show();
				},function(){
					$(this).find(".book_info").hide();
			});
	});
</script>
</head>

<body>
<div id="warp">
    
    <!-- header -->
	<jsp:include page="../include/header.jsp" flush="false"></jsp:include>
	    
	
	<div id="layout">
        <div class="info">
        	<div class="doing">
            	<div class="title">我在做什么 ...</div>
                <div class="con">
                	<textarea class="text" name="" cols="" rows=""></textarea>
                    <div class="fb">
                    	<div class="fn-right fb_btn"><input type="button" class="btn" /></div>
                    	<!-- <ul class="fb_list">
                        	<li><a href="#"></a></li>
                            <li><a href="#"></a></li>
                            <li><a href="#"></a></li>
                            <li><a href="#"></a></li>
                            <li><a href="#"></a></li>
                            <li><a href="#"></a></li>
                        </ul> -->
                    </div>
                 
                </div>
            </div>
            <div class="info_base">
            	<div class="info_base_con">
                	<a href="#" onclick="updateAvatar();">
                		<c:choose>
                			<c:when test="${userInfo.avatar == null }">
                				<img src="${ctxStatic}/images/reg_photo.gif" width="110" height="110" alt="" />	
                			</c:when>
                			<c:otherwise>
                				<img src="${ctxStatic}/{userInfo.avatar}" width="110" height="110" alt="" />
                			</c:otherwise>
                		</c:choose>
                		
                	</a>
                    <h1><a href="#">${userInfo.username }</a></h1>
                    <div class="base_num">
                    	<span>粉丝<a href="#">${userInfo.floweds }</a></span><span>消息<a href="#">${userInfo.messages }</a></span><span>关注<a href="#">${userInfo.flowings }</a></span>
                    </div>
                </div>
                <div class="info_cont">
                	<p>状态：${userInfo.description }</p>
                    <div class="info_con">
                    	<form action="${ctx }/user/updateStatus" method="post">
	                    	<input type="text" class="info_text" name="description" />
	                        <input type="button" class="info_btn" onclick="javascript:this.form.submit();"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div id="ul_btn">
        	<ul>
            	<li><a href="#" class="hover">动态</a></li>
                <li><a href="#">消息</a></li>
                <li><a href="#" class="pl">评论</a></li>
                <li><a href="#">随便看看</a></li>
            </ul>
        </div>
        <div class="main">
        	<div class="con_list">
            	<div class="tab_search">
                	<input type="button" class="tab_search_btn" />
                	<input type="text" class="tab_search_text"  value="搜索我关注的人和事"  />
                </div>
            	<div class="tab" id="tabBox">
                	<ul class="tab_ul">
                    	<li class="active">动态</li>
                        <li>消息</li>
                        <li>评论</li>
                        <li>关系</li>
                    </ul>
                    <div class="tab_cont">
                    	<div class="tab_con" style="display:block;">
                        	<ul class="tab_list">
                            	<li>
                                    <span class="list_con">
                                        <h3>正在读<a href="#">《我和这个世界不熟》</a>，<a href="#">黄某某</a> 著，中国民族摄影艺术出版社</h3>
                                        <p>《我和这个世界不熟》极力调侃生活的琐碎，巧解各路话题新闻，用简短、精炼的笔调刻画出生活舞台上一个个鲜活的小人物，当然亦有作者本人的各种领衔出演。这不仅仅是一部原创笑话段子集，更是作者的成长录，它收录了一个网络写手从默默无闻的路人甲到低调网络小红人的整个过程。这里有每个社会人的影子，无论小清新或是小邪恶。其实，人生就是一出悲喜剧，翻开书，用幽默战胜生命的低谷。</p>
                                        <span class="book_img">
                                                <a href="#"><img src="../images/book.gif" width="105" height="140" alt="" /></a>
                                                <span class="book_info">
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
                                            </span>
                                        <p class="bott">
                                        	<span class="p_function">
                                            	<a href="#">转发（23）</a>|
                                                <a href="#">收藏</a>|
                                                <a href="#">评论（13）</a>
                                            </span>
                                            <span class="time">
                                            	10月15日  17：35
                                            </span>
                                        </p>
                                    </span>
                                </li>
                            </ul>
                        </div>
                        <div class="tab_con">
                        	<ul class="tab_list">
                            	<li>
                                    <span class="list_con">
                                        <h3>正在读<a href="#">《我和这个世界不熟》</a>，<a href="#">黄某某</a> 著，中国民族摄影艺术出版社</h3>
                                        <p>《我和这个世界不熟》极力调侃生活的琐碎，巧解各路话题新闻，用简短、精炼的笔调刻画出生活舞台上一个个鲜活的小人物，当然亦有作者本人的各种领衔出演。这不仅仅是一部原创笑话段子集，更是作者的成长录，它收录了一个网络写手从默默无闻的路人甲到低调网络小红人的整个过程。这里有每个社会人的影子，无论小清新或是小邪恶。其实，人生就是一出悲喜剧，翻开书，用幽默战胜生命的低谷。</p>
                                        <span class="book_img">
                                                <a href="#"><img src="../images/book.gif" width="105" height="140" alt="" /></a>
                                                <span class="book_info">
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
                                            </span>
                                        <p class="bott">
                                        	<span class="p_function">
                                            	<a href="#">转发（23）</a>|
                                                <a href="#">收藏</a>|
                                                <a href="#">评论（13）</a>
                                            </span>
                                            <span class="time">
                                            	10月15日  17：35
                                            </span>
                                        </p>
                                    </span>
                                </li>
                            	
                            </ul>
                        </div>
                        <div class="tab_con">
                        	<ul class="tab_list">
                            	<li>
                                    <span class="list_con">
                                        <h3>正在读<a href="#">《我和这个世界不熟》</a>，<a href="#">黄某某</a> 著，中国民族摄影艺术出版社</h3>
                                        <p>《我和这个世界不熟》极力调侃生活的琐碎，巧解各路话题新闻，用简短、精炼的笔调刻画出生活舞台上一个个鲜活的小人物，当然亦有作者本人的各种领衔出演。这不仅仅是一部原创笑话段子集，更是作者的成长录，它收录了一个网络写手从默默无闻的路人甲到低调网络小红人的整个过程。这里有每个社会人的影子，无论小清新或是小邪恶。其实，人生就是一出悲喜剧，翻开书，用幽默战胜生命的低谷。</p>
                                        <span class="book_img">
                                                <a href="#"><img src="../images/book.gif" width="105" height="140" alt="" /></a>
                                                <span class="book_info">
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
                                            </span>
                                        <p class="bott">
                                        	<span class="p_function">
                                            	<a href="#">转发（23）</a>|
                                                <a href="#">收藏</a>|
                                                <a href="#">评论（13）</a>
                                            </span>
                                            <span class="time">
                                            	10月15日  17：35
                                            </span>
                                        </p>
                                    </span>
                                </li>
                            </ul>
                        </div>
                        <div class="tab_con">
                        	<ul class="tab_list">
                            	<li>
                                    <span class="list_con">
                                        <h3>正在读<a href="#">《我和这个世界不熟》</a>，<a href="#">黄某某</a> 著，中国民族摄影艺术出版社</h3>
                                        <p>《我和这个世界不熟》极力调侃生活的琐碎，巧解各路话题新闻，用简短、精炼的笔调刻画出生活舞台上一个个鲜活的小人物，当然亦有作者本人的各种领衔出演。这不仅仅是一部原创笑话段子集，更是作者的成长录，它收录了一个网络写手从默默无闻的路人甲到低调网络小红人的整个过程。这里有每个社会人的影子，无论小清新或是小邪恶。其实，人生就是一出悲喜剧，翻开书，用幽默战胜生命的低谷。</p>
                                        <span class="book_img">
                                                <a href="#"><img src="../images/book.gif" width="105" height="140" alt="" /></a>
                                                <span class="book_info">
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
                                            </span>
                                        <p class="bott">
                                        	<span class="p_function">
                                            	<a href="#">转发（23）</a>|
                                                <a href="#">收藏</a>|
                                                <a href="#">评论（13）</a>
                                            </span>
                                            <span class="time">
                                            	10月15日  17：35
                                            </span>
                                        </p>
                                    </span>
                                </li>
                            	
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="sidebar">
                <div class="interest_person">
                	<div class="title">
                    	<h3><a href="#">您关注的人……</a></h3>
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
                <div class="interest_person">
                	<div class="title">
                    	<h3><a href="#">关注您的人……</a></h3>
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
                <dl class="my_book">
                	<dt><h3><a href="#">我的书架</a></h3></dt>
                    <dd><a href="#">在读 （${userInfo.reading}）</a></dd>
                    <dd><a href="#">想读 （${userInfo.wantRead}）</a></dd>
                    <dd><a href="#">已读 （${userInfo.hasRead}）</a></dd>
                </dl>
            </div>
        </div>
    </div>
</div>
<div class="box_wap">
	<div class="bookshelf">
    	<div class="po">
    	<div class="close"></div>
        <div class="img">
        	<img src="../images/img6.gif" width="196" height="294" alt="" />
        </div>
        <div class="read">
        	<span class="more">
            	<em class="s"></em>
            	<div class="more_list">
                	<ul>
                    <li>在读</li>
                    <li>已读</li>
                    <li>其他</li>
                    </ul>
                </div>
            </span>
            <a href="#">想读</a>
        </div>
        <div class="read_span">
        	<a href="#"><em class="x"></em>小说</a> <a href="#"><em class="x"></em>小说</a> <a href="#"><em class="x"></em>小说</a>
            <span>还可以添加自己的标签</span>
        </div>
        <div class="add">
        	<input class="add_btn fn-right"  type="button"/>
            <div class="share fn-left">
            	<!-- Baidu Button BEGIN -->
                <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare">
                <span class="bds_more">分享到：</span>
                <a class="bds_tsina"></a>
                <a class="bds_qzone"></a>
                <a class="bds_tqq"></a>
                <a class="bds_renren"></a>
                </div>
                <script type="text/javascript" id="bdshare_js" data="type=tools&amp;mini=1&amp;uid=6485092" ></script>
                <script type="text/javascript" id="bdshell_js"></script>
                <script type="text/javascript">
                document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
                </script>
                <!-- Baidu Button END -->
            </div>
        </div>
        </div>
    </div>
    <div class="shadow">
    </div>
</div>


<script type="text/javascript"> 
	
	function updateAvatar(){
		$('#chooseFile-model').modal('show');
	}
	
	


 function tabMenu(tabBox,navClass){
  var tabNavLi=document.getElementById(tabBox).getElementsByTagName("ul")[0].getElementsByTagName("li");
  var tabCon=document.getElementById(tabBox).getElementsByTagName("div")[0].getElementsByTagName("div");
  var tabLens=tabCon.length;
  for(var i=0;i<tabLens;i++){
  //应用js闭包传入参数i作为当前索引值赋值给m
    (function(m){
   tabNavLi[m].onmouseover = function(){
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
<jsp:include page="./imageCut.jsp" flush="false"></jsp:include>

</body>
</html>
