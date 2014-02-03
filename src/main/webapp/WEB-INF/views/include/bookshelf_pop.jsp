<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxRes" value="${pageContext.request.contextPath }/static"/>  
<div class="box_wap">
	<div class="bookshelf">
    	<div class="po">
    	<div class="close"></div>
        <div class="img">
        	<img src="${ctxRes }/images/img6.gif" width="196" height="294" alt="" />
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
    <div class="money">
    	<div class="po">
    	<div class="close"></div>
    	<div class="title">
        	<a href="#">《告别的年代》</a>定价：25元
        </div>
        <div class="money_list">
        	<dl>
            	<dt>购买新书</dt>
                <dd><span class="money_f">18元</span><a href="#">当当网</a></dd>
                <dd><span class="money_f">18元</span><a href="#">当当网</a></dd>
                <dd><span class="money_f">18元</span><a href="#">当当网</a></dd>
                <dd><span class="money_f">18元</span><a href="#">当当网</a></dd>
            </dl>
            <dl>
            	<dt>购买二手书</dt>
                <dd><span class="money_f">18元</span><a href="#">当当网</a></dd>
                <dd><span class="money_f">18元</span><a href="#">当当网</a></dd>
                <dd><span class="money_f">18元</span><a href="#">当当网</a></dd>
                <dd><span class="money_f">18元</span><a href="#">当当网</a></dd>
            </dl>
            <dl>
            	<dt>购买电子书</dt>
                <dd><span class="money_f">18元</span><a href="#">当当网</a></dd>
                <dd><span class="money_f">18元</span><a href="#">当当网</a></dd>
                <dd><span class="money_f">18元</span><a href="#">当当网</a></dd>
                <dd><span class="money_f">18元</span><a href="#">当当网</a></dd>
            </dl>
        </div>
       </div>
    </div>
    <div class="shadow">
    </div>
</div>
