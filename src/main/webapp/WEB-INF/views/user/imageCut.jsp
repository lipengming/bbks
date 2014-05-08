<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/> 
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>

<link href="${ctxStatic }/om/css/default/om-default.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic }/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${ctxStatic }/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctxStatic }/om/js/operamasks-ui.min.js"></script>

<!-- 图片上传组件 -->

<!-- Modal -->
<div class="modal fade" id="chooseFile-model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">修改头像</h4>
      </div>
      <div class="modal-body">
        <input id="file_upload" name="file_upload" type="file" />
        <button class="btn btn-primary" onclick="$('#file_upload').omFileUpload('upload')">确定</button>	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 剪切组件  -->
<!-- Modal -->
<div class="modal fade" id="updateImage-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">修改头像</h4>
      </div>
      <div class="modal-body">
        <!-- 源文件 -->
        <form action="${ctx}/user/updateAvatar" method="POST" id="updateAvatarForm">
    		<img src="" id="avatar_target" alt="avatar"  onload="javascript:if(this.width>400)this.width=400"/>
			<input type="hidden" id="h_image_src" name="avatarSrc"/>
			<!-- 切图预览 
			<div id="preview-pane">
				<div class="preview-container">
					<img src="" id="avatar_preview" class="jcrop-preview" alt="Preview" />
				</div>
			</div>-->	
			<hr>
			<button type="button" class="btn btn-warning pull-right" onclick="$('#updateAvatarForm').submit();">确认跟新</button>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script type="text/javascript">
$(function() {
	$('#file_upload').omFileUpload({
	    action : "<c:url value='/f/uploadImage' />",
	    swf : '${ctxStatic }/om/swf/om-fileupload.swf',
	    onComplete : function(ID, fileObj, response, data, event){

	    	$('#chooseFile-model').modal('hide');
	    	
	    	var jsonData =  eval("("+response+")");
			
	    	if(jsonData.isSuccess){
	    		//打开预览的model
	    		//$("#user_avatar").attr("src",jsonData.obj);
	    		$("#avatar_target").attr("src",jsonData.obj);
	    		$("#h_image_src").val(jsonData.obj);
	    		
	    		$("#updateImage-modal").modal('show');
	    	}else{
		    	alert(jsonData.message);	
	    	}
	    },
	    onError:function(ID,fileObj,errorObj,event){
	    	alert('文件'+fileObj.name+'上传失败。错误类型：'+errorObj.type+'。原因：'+errorObj.info);
	    }
	});
	
	function updateAvatarInfo(){
		$("#updateAvatarForm").submit();
	}
	
});

</script>