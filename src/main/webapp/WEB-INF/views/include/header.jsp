<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>  
<c:set var="ctxRes" value="${pageContext.request.contextPath }/static"/>  


<c:choose>
<c:when test="${empty sessionScope._SIGN_USER_}">
<!-- unsigned -->
<div id="index_top" class="fn-clear">
	<div class="W960 header">
    	<div id="logo">
    		<a href="${ctx}"><img src="${ctxRes }/images/logo.gif" width="93" height="42" alt="" /></a>
    	</div>
        <form action="<c:url value="/book/search"/>" method="post" id="index_search_form">
        	<div id="index_search">
	        	<input type="text" name="keywords" class="s_text foucs_text" value="搜索书名，作者、出版社等"/>
	            <input type="button" class="s_btn"/>
        	</div>
        </form>
        
        <div class="index_member">
        	<div class="drop_icon">
            </div>
            <div class="drop_icon_con">
            	<ul class="zy">
                	<li><a href="#">主页</a></li>
                </ul>
            	<dl>
                	<dt>墨屋手机客户端</dt>
                    <dd><a href="#">iPhone</a></dd>
                    <dd><a href="#">Android</a></dd>
                </dl>
                <ul class="zr">
                	<li><a href="#">找人</a></li>
                    <li><a href="#">兴趣相同的人</a></li>
                </ul>
                <ul class="help">
                	<li>关于&帮助</li>
                    <li><a href="#">用户反馈</a></li>
                    <li><a href="#">友情连接</a></li>
                    <li><a href="#">加入我们</a></li>
                </ul>
                <ul class="zh">
                    <li><a href="#">帐号设置</a></li>
                    <li><a href="#">退出登录</a></li>
                </ul>
            </div>
            <span class="fn-left login">
            	<input type="button" class="l_btn" value="登录" />
            </span>
            <span class="fn-left reg">
            	<input type="button" class="r_btn" value="注册" />
            </span>
        </div>
    </div>
</div>
</c:when>
<c:otherwise>
<!-- signed -->
<div id="top">
    	<div class="top_con">
            <div id="logos"><a href="${ctx}"><img src="${ctxRes }/images/logo.gif" width="93" height="42" alt=""></a></div>
            <div class="nav">
                <ul>
                    <li class="class">
                    	<a href="#">书城</a>
                        <dl class="sc_dlist" style="display: none;">
                        	<dd><a href="${ctx}/book/news">新书</a></dd>
                            <dd><a href="${ctx}/book/promotion">特价</a></dd>
                            <dd><a href="${ctx}/book/searchrank">搜索排行榜</a></dd>
                            <dd><a href="${ctx}/book/salerank">销售排行榜</a></dd>
                        </dl>
                    </li>
                    <li><a href="${ctx}/bs/index">书架</a></li>
                    <li><a href="${ctx}/user/profile">社交网络</a></li>
                </ul>
            </div>
            <form action="<c:url value="/book/search"/>" method="post" id="index_search_form">
	            <div class="search">
	                <input type="text" class="search_text" name="keywords"> 
	                <input type="button" class="search_btn" onClick="javascript:this.form.submit();"/>
	            </div>
	         </form>   
        	
            <div class="top_info">
                <span class="name"><a href="${ctx }/logout" class="drop" /><a href="${ctx }/user/profile/index"><c:out value="${sessionScope._SIGN_USER_.username }"></c:out></a></span>
                <span class="change" onclick="javascript:window.location='${ctx}/bs/donate';">
                </span>
                <span class="mail">
                	<div class="mail_drop" style="display:none;">
                    	<div class="drop_mail"></div>
                        <div class="drop_list">
                            <ul class="tz">
                                <li class="">五条系统通知，<a href="#">查看消息</a></li>
                            </ul>
                            <ul class="ck">
                                <li><a href="#">查看私信</a></li>
                                <li><a href="#">查看@我</a></li>
                                <li><a href="#">查看评论</a></li>
                                <li><a href="#">系统通知</a>（5）</li>
                            </ul>
                            <ul class="fsx">
                                <li><a href="#">发私信</a></li>
                            </ul>
                         </div>
                    </div>
                </span>
            </div>
        </div>
    </div>
</c:otherwise>
</c:choose>

<script type="text/javascript">
$(function(){
	//登录
	$("#index_top .index_member .l_btn").click(
		function(){
			$(".box_login").show();
			$(".box_login .shadow").height($(document.body).height());
			$(".box_login .shadow,#login .close,#login .lg .l_btn").click(function(){
				
				var name = $("#login_name").val();
				var pwd = $("#login_pwd").val();
				
				if(name != "" && pwd != ""){
					var url = "${ctx}/api/user/login";
					var mdata = {"name":name,"pwd":pwd};
					
					$.ajax({
					    type: "POST",
					    dataType: "json",
					    data:mdata,
					    url: url,
					    success: function(result){
					    	if(result.isSuccess){
					    		window.location = "";
					    	}else{
					    		$(".box_login").hide();
					    	}
					    	alert(result.message);
					    },
					    error:function(err){
					    	alert(err);
					    	$(".box_login").hide();
					    }
					});
				}else{
					$(".box_login").hide();
				}
				
			});
		}
	);
	//注册
	$("#index_top .index_member .r_btn").click(
		function(){
			window.location = "regist";	
		
//			$(".box_reg").show();
//			$(".box_reg .shadow").height($(document.body).height());
//			$(".box_reg .shadow,.box_reg .close").click(function(){
//				$(".box_reg").hide();	
//			});
	});
	$("#reg .dq em").click(function(){
			var  list = $(this).parents(".dq").find(".dq_list");
			list.show();
			var sp = $(this).parents(".dq").find("span");
			list.find("ul").find("li").click(
				function(){
					sp.empty();
					$(this).find("a").clone().appendTo(sp);
					$(".dq_list").hide();
			});
	});
});
</script>
