$(function(){
	//搜索框字体消失和显示
	$(".foucs_text").focus(function(){
		  $(this).addClass("focus");
		  if($(this).val() ==this.defaultValue){  
			  $(this).val("");           
		  } 
	}).blur(function(){
		 $(this).removeClass("focus");
		 if ($(this).val() == '') {
			$(this).val(this.defaultValue);
		 }
	});
});


function register(){
	$("#register_form").submit();
}

/**
 * 检查输入的邮箱格式是否正确
 * 输入:str  字符串
 * 返回:true 或 flase; true表示格式正确
 */
function checkEmail(str){
    if (str.match(/[A-Za-z0-9_-]+[@](\S*)(net|com|cn|org|cc|tv|[0-9]{1,3})(\S*)/g) == null) {
        return false;
    }
    else {
        return true;
    }
}


function json_reg(url){
	
	console.log(url);
	
	var name = $("#reg_name").val();
	var email = $("#reg_email").val()+"";
	var pwd = $("#reg_pwd").val();
	var repwd = $("#reg_repwd").val();
	
	//validate here
	
	var mdata = {"username":name,"email":email,"password":pwd,"repassword":repwd}; 

	console.log(mdata);

	$.ajax({
	    type: "POST",
	    dataType: "json",
	    data:mdata,
	    url: url,
	    success: function(result){
	    	windows.localtion("/");
	    },
	    error:function(err){
	    	alert(err);
	    }
	});	
}


