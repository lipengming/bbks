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
                	<c:choose>
                		<c:when test="${bookInfo.islike }">
                			<span class="like" >
		                		<em class="like-icon liked"></em>
		                		${bookInfo.likeCount }
		                	</span>
                		</c:when>
                		<c:otherwise>
                			<span class="like" onclick="addInterested(${bookInfo.id });">
		                		<em class="like-icon" id="em_${bookInfo.id }"></em>
		                		${bookInfo.likeCount }
		                	</span>
                		</c:otherwise>
                	</c:choose>
                	
                	
                    <span class="comments"><a href="#">评论</a>${bookInfo.commentCount }</span>
                </div>
                <div class="pb_message" id="comment_${bookInfo.id }">
                <ul>	
                <c:forEach items="${commentMap }" var="map">
                    <c:if test="${map.key eq bookInfo.id }"> <!--这样写试一下-->
                        <c:forEach items="${map.value}" var="bookComment">
                           	<li>
	                        	<div class="user_img">
									<a href="${ctx }/user/detail/${bookComment.uid }">
										<img src="${bookComment.avatar }" width="26" height="26" alt="" />
									</a>
								</div>
							    <div class="Ms">
							    	<a href="${ctx }/book/search/${bookComment.contentId }">${bookComment.title }</a>:
							    	${bookComment.content }
							    </div>
                        	</li>
                        </c:forEach>
                    </c:if>
                 </c:forEach>
                 </ul>
                </div>
                <div class="pb_write">
                	<input type="hidden" value="${bookInfo.bookName }" id="comment_title_${bookInfo.id }">
                	<input type="text" class="pb_write_text foucs_text" value="添加评论或者@好友" id="comment_content_${bookInfo.id }"/>
                    <div class="ph">
                    	<input type="button" class="btn" value="评论" onclick="addCommentInfo(${bookInfo.id });"/>
                    </div>
                </div>
        	</div>	
	<!-- wall flow item -->
</c:forEach>
</c:if>
<!-- wall flow end -->

<script type="text/javascript">
function addCommentInfo(bookId){
	console.log('-----------'+bookId);
	<c:if test="${empty sessionScope._SIGN_USER_}">
		alert("登陆之后才能评论！");
		return;
	</c:if>
	
	var contentStr = $("#comment_content_"+bookId).val();
	var titleStr = $("#comment_title_"+bookId).val();
	
	var data = {module: 'book',contentId: bookId,content:contentStr,title:titleStr};
	
	request(data);			
}
function request(data){
	console.log('-----req------');
	$.ajax({
	    type: "POST",
	    dataType: "json",
	    data:data,
	    url: '${ctx}/api/comment/add',
	    success: function(result){
	    	
	    	var imgSrc = "${sessionScope._SIGN_USER_.avatar}";
	    	$("#comment_"+data.contentId+" ul").append("<li><div class='user_img'><a href='${ctx}/user/detail/${sessionScope._SIGN_USER_.id}'><img src='"+imgSrc+"' width='26' height='26' alt='' /></a></div>"+
	    			"<div class='Ms'><a href='${ctx}/book/search/"+data.contentId+"'>"+data.title+"</a>:"+data.content+"</div></li>");
	    	$('#container').masonry( 'reload');
	    },
	    error:function(err){
	    	alert(err);
	    }
	});	
}

function addInterested(bookId){
	if("${sessionScope._SIGN_USER_.id }" == ""){
		alert("登陆之后才能继续操作！");	
		return;
	}
	var uid = "${sessionScope._SIGN_USER_.id }";
	var data = {
			userId:uid,
			bookId:bookId,
			typeId:4
	};
	$.ajax({
	    type: "POST",
	    dataType: "json",
	    data:data,
	    url: '${ctx}/api/interest/addInterest',
	    success: function(result){
	    	$("#em_"+bookId).toggleClass("liked");
	    },
	    error:function(err){
	    	alert(err);
	    }
	});	
}

	//书城比价按钮
	$(".s_btn_1").click(
		function(){
			//弹出层显示
			$(".box_wap").show();
			//弹出层比价层显示
			$(".box_wap .money").show();			
	});
	//书城加入书架按钮
	$(".s_btn_2,.book_info_btn .join,.gb_img .gb_img_btn_2").click(function(){
		<c:if test="${empty sessionScope._SIGN_USER_}">
			alert("登陆之后才能评论！");
			return;
		</c:if>
		
		//弹出层显示
		$(".box_wap").show();
			//弹出层加入书架显示
			$(".box_wap .bookshelf").show();
			//弹出层下拉按钮
			$(".bookshelf .read .s").click(
				function(){
					$(this).next(".more_list").toggle();
			});
			//弹出层标签删除
			$(".read_span a .x").click(
				function(){
					$(this).parents("a").hide();ling
					return false;
			});
		});
	//弹出层关闭按钮
	$(".box_wap .close").click(function(){
			$(".box_wap").hide();
			$(".box_wap .bookshelf").hide();
			$(".box_wap .money").hide();
	});
</script>
