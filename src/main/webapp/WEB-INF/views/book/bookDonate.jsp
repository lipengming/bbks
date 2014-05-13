<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/> 
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>书架</title>
<jsp:include page="../include/user-script.jsp" flush="false"></jsp:include>

<script type="text/javascript">

</script>

</head>
<body>
<div id="index_warp">
	<jsp:include page="../include/header.jsp" flush="false"></jsp:include>

<div id="layout">
    	<div class="circle_menu">
        	<ul>
            	<li class="active">
            		<a href="${ctx }/bs/index">我的兴趣</a>
            	</li>
            	 <li><a href="${ctx }/bs/similar">和我相似</a></li>
                <li><a href="${ctx }/bs/bookmarks">我的书签</a></li>
                <li><a href="${ctx }/bs/donate">贡献图书</a></li>
            </ul>
        </div>
        <div class="main">
        	<div class="circle_left">
            	<div class="c_col">
                	<div class="title">
                    	<h2>已经捐赠</h2>
                    </div>
                    <ul>
                    	<li><a href="#">《中国人》</a></li>
                    </ul>
                </div>
                <div class="c_col bort">
                	<div class="title">
                    	<h2>捐赠受理中</h2>
                    </div>
                    <ul>
                        <li><a href="#">《中国人》</a></li>
                    </ul>
                </div>
            </div>
            <div class="circle_right">
            	<div class="dr">
                	<h1>捐赠电子书</h1>
                    <div class="dr_con">
                    	<div class="dr_left">
                        	<div id="div_upload_file">
	                            <label><span class="text">上传文件：</span><input id="file_upload" name="file_upload" type="file" /></label>
	                            <label></label>
	                            <label></label>
	                            <label><input type="button" class="dr_btn" value="导入"  onclick="javascript:$('#file_upload').omFileUpload('upload');"/></label>
                            </div>
                            
                            <div id="div_submit_info" style="display: none;">
	                           <form action="${ctx }/bs/uploadEbook" method="post"> 
		                           <label>
		                            	文件已经正确上传，请完善资料信息
		                            	<input type="hidden" id="bookSrc" name="bookSrc"/>
		                            </label>
		                            <label><span class="text">ISBN</span><input type="text" name="bookIsbn" class="dr_text W300"/></label>
		                           
		                            <label><span class="text">书名</span><input type="text" name="bookName" class="dr_text W300"/></label>
		                            <label><input type="button" class="dr_btn" value="确认信息"  onclick="javascript:this.form.submit();"/></label>
	                            </form>
                            </div>
                        </div>
                        <div class="dr_right">
                        	<dl>
                            	<dt>友情提示：</dt>
                                <dd>1、安全。</dd>
                                <dd>2、请放心使用。</dd>
                                <dd>3、请放心使用。</dd>
                            </dl>
                        </div>
                    </div>
                </div>
              	<!-- end -->
              </div>
            </div>
        </div>
    </div>

 <script type="text/javascript"> 
 $('#file_upload').omFileUpload({
	    action : "<c:url value='/f/upLoadAndUnZip' />",
	    swf : '${ctxStatic }/om/swf/om-fileupload.swf',
	    onComplete : function(ID, fileObj, response, data, event){
	    	var jsonData =  eval("("+response+")");
	    	console.log(jsonData);
	    	if(jsonData.isSuccess){
	    		//打开预览的model
	    		$("#div_upload_file").hide();
	    		$("#div_submit_info").show();
	    		$("#bookSrc").val(jsonData.obj);
	    	}else{
		    	alert(jsonData.message);	
	    	}
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