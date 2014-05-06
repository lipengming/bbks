<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>  
<c:set var="ctxRes" value="${pageContext.request.contextPath }/static"/>  
<!-- search bar start -->
	<div class="W960">
    	<div class="index_menu">
        	<ul>
            	<li><a href="${ctx}" class="index">书城</a></li>
                <li><a href="${ctx}/book/news">新书</a></li>
                <li><a href="${ctx}/book/promotion">特价</a></li>
                <li><a href="${ctx}/book/salerank">热销</a></li>
                <li><a href="${ctx}/book/searchrank">热搜</a></li>
            </ul>
            
            <ul class="ul_user">
                <li><a href="#">兴趣</a></li>
                <li><a href="${ctx}/user/bookshelft">书架</a></li>
                <li><a href="${ctx}/user/bookshelft">社交网络</a></li>
            </ul>
        </div>
        <div class="screen_class">
        	<dl class="class">
            	<dt><a href="#" class="hover">全部</a></dt>
            	<c:forEach var="ca" items="${top5 }" >
            		<c:choose>
            			<c:when test="${ca.id == categroy.id }">
            				<dd><a href="${ctx}/book/catlog?catlog=${ca.id}" class="hover">${ca.name}</a></dd>
            			</c:when>
            			<c:otherwise>
            				<dd><a href="${ctx}/book/catlog?catlog=${ca.id}" >${ca.name}</a></dd>
            			</c:otherwise>
            		</c:choose> 		
            	</c:forEach>
                <dd>
                	<a href="#" class="more" id="more_drop">更多</a>
                    <div class="more_div">
                    	<ul>
                    	<c:forEach var="ca" items="${more }" >
                    		<c:choose>
		            			<c:when test="${ca.id == categroy.id }">
		            				<li><a href="${ctx}/book/catlog?catlog=${ca.id}" class="hover">${ca.name}</a></li>
		            			</c:when>
		            			<c:otherwise>
		            				<li><a href="${ctx}/book/catlog?catlog=${ca.id}" >${ca.name}</a></li>
		            			</c:otherwise>
            				</c:choose>
            			</c:forEach>
                        </ul>
                    </div>
                </dd>
            </dl>
            <div class="sort">
            	<dl>
                	<dt><a href="#">默认排序</a></dt>
                    <!-- <dd><a href="#">评级</a><em class="down"></em></dd>-->
                    <dd><a href="javascript:sort_href(1);">点评数</a><em class="down"></em></dd>
                    <dd><a href="javascript:sort_href(2);">价格</a><em class="down"></em></dd>
                </dl>
            </div>
        </div>
    </div>
    <!-- search bar end -->
    <script type="text/javascript">
    	$(function(){
    		var select = ${indexOne};
    		if(!select){select = 0;}
    		console.log(select);
    		
    		$('.index_menu ul').find('a').each(function(item){
    			if(select == item){
    				$(this).addClass("index");
    			}else{
    				$(this).removeClass("index");
    			}
    		});
    	});
    	
    	function sort_href(type){
    		var url = window.location.pathname;
    		
    		if(type==1){
    			url = url + "?1=1&sort=comment&sortOrder=0";
    		}else{
    			url = url + "?1=1&sort=price&sortOrder=0";
    		}
    		window.location = url;
    	}
    </script>