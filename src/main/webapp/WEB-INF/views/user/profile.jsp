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

<jsp:include page="../include/user-script.jsp" flush="false"></jsp:include>

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
                	<form action="${ctx }/user/publishDynamic" method="post" id="dynamicform">
                	<textarea class="text" name="content" cols="" rows=""></textarea>
                    <div class="fb">
                    	<div class="fn-right fb_btn">
                    		<input type="button" class="btn" onclick="javascript:$('#dynamicform').submit();"/>
                    	</div>
                    	<!-- <ul class="fb_list">
                        	<li><a href="#"></a></li>
                            <li><a href="#"></a></li>
                            <li><a href="#"></a></li>
                            <li><a href="#"></a></li>
                            <li><a href="#"></a></li>
                            <li><a href="#"></a></li>
                        </ul> -->
                    </div>
                 </form>
                </div>
            </div>
            <div class="info_base">
            	<div class="info_base_con">
                	<a href="#" id="userAvatar">
                		<c:choose>
                			<c:when test="${empty userInfo.avatar}">
                				<img id="user_avatar" src="${ctxStatic}/images/reg_photo.gif" width="110" height="110" alt="" />	
                			</c:when>
                			<c:otherwise>
                				<img id="user_avatar" src="${userInfo.avatar }" width="110" height="110" alt="" />
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
                <li><a href="#">评论</a></li>
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
                         <li>书评</li>
                         <li>未读</li>
                         <li>发出</li>
                         <li>收到</li>
                    </ul>
                    <div class="tab_cont">
                    	<!-- 动态 -->
                    	<div class="tab_con" style="display:block;">
                        	<ul class="tab_list">
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
                        <!-- 书评 -->	
                         <div class="tab_con">
                        	<ul class="tab_list">
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
                            </ul>
                        </div>
                        
                        <!-- 未读 -->
                        <div class="tab_con">
                        	<ul class="tab_list">
                        		<c:forEach var="item" items="${unreadMessages }">	
                        		<li>
                                    <span class="list_con">
                                        ${item.content}
                                        <p class="bott">
                                        	<span class="p_function">
                                        		|<a href="#">阅读</a>
                                                |<a href="#">回复</a>|
                                            </span>
                                            <span class="time">
                                            	${item.creatAt }
                                            </span>
                                        </p>
                                    </span>
                                </li>
                        		</c:forEach>
                            </ul>
                        </div>  
                        <!-- 发出的消息 -->
                        <div class="tab_con">
                        	<ul class="tab_list">
                        		<c:forEach var="item" items="${sendMessages }">	
                        		<li>
                                    <span class="list_con">
                                        ${item.content}
                                        <p class="bott">
                                        	<span class="p_function">
                                            	|<a href="#">回复</a>|
                                            </span>
                                            <span class="time">
                                            	${item.creatAt }
                                            </span>
                                        </p>
                                    </span>
                                </li>
                        		</c:forEach>
                            </ul>
                        </div>
                         <!-- 收到的消息 -->
                        <div class="tab_con">
                        	<ul class="tab_list">
                        		<c:forEach var="item" items="${recivedMessages }">	
                        		<li>
                                    <span class="list_con">
                                        ${item.content}
                                        <p class="bott">
                                        	<span class="p_function">
                                            	|<a href="#">回复</a>|
                                            </span>
                                            <span class="time">
                                            	${item.creatAt }
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
                <div class="interest_person">
                	<div class="title">
                    	<h3><a href="#">您关注的人……</a></h3>
                    </div>
                    <div class="list">
                    	<ul>
                    		<c:forEach var="item" items="${flowed }">
                    		<li>
                                <a href="${ctx }/user/detail/${item.id}">
                                	<img src="${item.avatar }" width="48" height="48" alt="" />
                                    <h5>W</h5>
                                </a>
                                <div class="read_info">
                                    <div class="read_info_base">
                                        <img src="../images/reader.jpg" width="40" height="40" alt="" />
                                        <h2>${item.username }</h2>
                                        <h4><span>关注</span> ${item.floweds } | <Span>粉丝</Span> ${item.flowings } |</h4>
                                        <p>${item.description }</p>
                                    </div>
                                    <div class="read_info_btn">
                                        <input type="button" class="btn" />
                                    </div>
                                </div>
                            </li>
                    		</c:forEach>
                           
                        </ul>
                    </div>
                </div>
                <div class="interest_person">
                	<div class="title">
                    	<h3><a href="#">关注您的人……</a></h3>
                    </div>
                    <div class="list">
                    	<ul>
                        	<c:forEach var="item" items="${flowing }">
                    		<li>
                                <a href="${ctx }/user/detail/${item.id}">
                                	<img src="${item.avatar }" width="48" height="48" alt="" />
                                    <h5>W</h5>
                                </a>
                                <div class="read_info">
                                    <div class="read_info_base">
                                        <img src="${item.avatar }" width="40" height="40" alt="" />
                                        <h2>${item.username }</h2>
                                        <h4><span>关注</span> ${item.floweds } | <Span>粉丝</Span> ${item.flowings } |</h4>
                                        <p>${item.description }</p>
                                    </div>
                                    <div class="read_info_btn">
                                        <input type="button" class="btn" />
                                    </div>
                                </div>
                            </li>
                    		</c:forEach>
                            
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

<!-- 书架弹出框 -->
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
<!-- 上传头像 -->
<div class="dialogDiv" style="display: none;" id="chooseFile-model">
	<div class="dialogBox">
		<div class="header">
			<div class="header_left">
				<h3>上传图片</h3>
			</div>
			<div class="header_right">
				<a href="javascript:;" onclick="javascript:$('#chooseFile-model').hide();"><img
					class="close" src="${ctxStatic }/images/close.png" /></a>
			</div>
		</div>
		<div class="content">
				<input id="file_upload" name="file_upload" type="file" />
				
				<div id="updateFile_model" style="display:none;">
					 <form action="${ctx}/user/updateAvatar" method="post" id="updateAvatarForm">
					 	<img src="" id="avatar_target" alt="avatar"  onload="javascript:if(this.width>400)this.width=400;if(this.height>400)this.height=400;"/>
					 	<input type="hidden" id="h_image_src" name="avatarSrc"/>
					 </form>
				</div>
				
				<input type="button" onclick="javascript:$('#file_upload').omFileUpload('upload');" value="上传文件" class="btn" /> 
			    <input type="button" onclick="javascript:$('#updateAvatarForm').submit();" value="跟新头像" class="btn" /> 
			    <input type="button" onclick="javascript:$('#chooseFile-model').hide();" value="取消" class="btn" />
			  
		</div>
	</div>
	<div class="shadow"></div>
</div>
<script type="text/javascript"> 

$("#userAvatar").click(function(){
	$( "#chooseFile-model").show();
});


$('#file_upload').omFileUpload({
    action : "<c:url value='/f/uploadImage' />",
    swf : '${ctxStatic }/om/swf/om-fileupload.swf',
    onComplete : function(ID, fileObj, response, data, event){
    	
    	$('#file_upload').hide();
    	
    	var jsonData =  eval("("+response+")");
    	
    	if(jsonData.isSuccess){
    		//打开预览的model
    		//$("#user_avatar").attr("src",jsonData.obj);
    		$("#avatar_target").attr("src",jsonData.obj);
    		$("#h_image_src").val(jsonData.obj);
    		
    	}else{
	    	alert(jsonData.message);	
    	}
    	$('#updateFile_model').show();
    },
    onError:function(ID,fileObj,errorObj,event){
    	alert('文件'+fileObj.name+'上传失败。错误类型：'+errorObj.type+'。原因：'+errorObj.info);
    }
});

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

</body>
</html>
