<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxRes" value="${pageContext.request.contextPath }/static"/>  

<div class="box_reg">
	<div class="close"></div>
	<div id="reg">
    	<div class="reg_step">
        	<ul>
            	<li class="act"><em class="num_1"></em>上传头像和基本信息</li>
                <li><em class="num_2"></em>关注兴趣相同的人</li>
                <li><em class="num_3"></em>关注同一个圈子的人</li>
            </ul>
        </div>
        <div class="reg_photo">
        	<div class="img">
            	<img src="${ctxRes }/images/reg_photo.gif" width="180" height="180" alt="" />
            </div>
            <div class="upload_img">
            	<input type="button" class="u_btn0" />
            </div>
        </div>
        <div class="reg_info">
        	<ul class="reg_list">
            	<li>
                	<span class="text">昵称</span>
                    <span class="name">格子</span>
                </li>
                <li>
                	<span class="text">性别</span>
                    <em class="radio act"></em> 男
                    <em class="radio"></em> 女
                </li>
                <li class="city">
                	<span class="text">所在地</span>
                    <div class="dq">
                    	<em></em>
                        <span>北京</span>
                        <div class="dq_list">
                        	<ul class="dq_list_ul">
                            	<li><a href="#">上海</a></li>
                                <li><a href="#">天津</a></li>
                                <li><a href="#">青岛</a></li>
                                <li><a href="#">济南</a></li>
                                <li><a href="#">淄博</a></li>
                                <li><a href="#">杭州</a></li>
                                <li><a href="#">宁波</a></li>
                                <li><a href="#">乌鲁木齐</a></li>
                                <li><a href="#">上海</a></li>
                                <li><a href="#">天津</a></li>
                                <li><a href="#">青岛</a></li>
                                <li><a href="#">济南</a></li>
                                <li><a href="#">淄博</a></li>
                                <li><a href="#">杭州</a></li>
                                <li><a href="#">宁波</a></li>
                                <li><a href="#">乌鲁木齐</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="dq">
                    	<em></em>
                        <span>东城区</span>
                        <div class="dq_list">
                        	<ul class="dq_list_ul">
                            	<li><a href="#">东城区</a></li>
                                <li><a href="#">西城区</a></li>
                                <li><a href="#">东城区</a></li>
                                <li><a href="#">东城区</a></li>
                                <li><a href="#">西城区</a></li>
                                <li><a href="#">东城区</a></li>
                            </ul>
                        </div>
                    </div>
                </li>
                <li>
                	<span class="text">学校</span>
                    <div class="text_kj">
                    	<input class="text_p" value="点击选择学校" />
                    </div>
                </li>
                <li>
                	<span class="text">公司</span>
                    <div class="text_kj">
                    	<input class="text_p" value="请输入公司的完整名称" />
                    </div>
                </li>
                <li>
                	<span class="text">微博</span>
                    <div class="text_kj">
                    	<input class="text_p" />
                    </div>
                </li>
                <li>
                	<span class="text">手机</span>
                    <div class="text_kj">
                    	<input class="text_p" />
                    </div>
                </li>
                <li>
                	<span class="text">自述</span>
                    <div class="text_kj">
                    	<input class="text_p" />
                    </div>
                </li>
            </ul>
            <div class="next_step">
            	<input class="next_btn" type="button" />
            </div>
        </div>
	</div>
	
	<div class="shadow">
    </div>
    
</div>