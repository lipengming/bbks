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

<jsp:include page="./include/script.jsp" flush="false"></jsp:include>

</head>
<body>

<jsp:include page="./include/header.jsp" flush="false"></jsp:include>

<div id="index_warp">
	<!-- search bar start -->
	<jsp:include page="./include/searchbar.jsp" flush="false"></jsp:include>
	<!-- search bar end -->
   	<!-- wall flow start -->
   	<div class="W960">
   		<div class="masonry" id="container">
   			<c:import url="/book/search/list?${qStr }}pageNo=0"></c:import>
   		</div>
	</div>
</div>
<!-- wall flow end -->
<div id="page-nav">
	<a href="<c:url value="/book/search/list?${qStr }pageNo=0" />"></a>
</div>


<jsp:include page="./include/index_login.jsp" flush="false"></jsp:include>
<jsp:include page="./include/index_reg.jsp" flush="false"></jsp:include>
<jsp:include page="./include/bookshelf_pop.jsp" flush="false"></jsp:include>

<script type="text/javascript">
$(function(){
	
	var $container = $('#container');
	var dom = $(this);
	
	$('.pb').each(function(){
		op.pb_onbind_event(dom);
	});
	
	$container.masonry({
		itemSelector : '.pb',
	    columnWidth : 222,
	    gutterWidth: 10,
	    isAnimated: false,
	    animationOptions: {
	    	queue: false
	    },
	    isFitWidth: false
	});
	
	$container.infinitescroll(
			{
				navSelector  : '#page-nav', // selector for the paged navigation
				nextSelector : '#page-nav a', // selector for the NEXT link (to page 2)
				itemSelector : '.pb', // selector for all items you'll retrieve
				debug        : false,
				animate	 	 : false,
				animationOptions: {
				    duration: 750,
				    easing: 'linear',
				    queue: false
				},
				loading: {
					selector: '#index_warp',
					finishedMsg: '没有更多了',
					msgText: '加载中...',
					img: '${ctxStatic }/images/big-loading.gif',
					speed: 0
				},
				state : {
					currPage: 0
				},
				pathParse: function() {
			        return ['<c:url value="/book/search/list?${qStr }pageNo=" />',''];
			    }
			},
			// trigger Masonry as a callback
			function( newElements ) {
				// hide new items while they are loading
				var $newElems = $(newElements).hide();
				$newElems.each(function(){
					op.pb_onbind_event(dom);
				});
				
				// ensure that images load before adding to masonry layout
				$newElems.imagesLoaded(function(){
					// show elems now they're ready
					$container.append( $newElems ).masonry( 'appended', 
							$newElems, false, function(){
						$newElems.fadeIn('slow');
					});
				});	
		});
});		
</script>
</body>
</html>

 