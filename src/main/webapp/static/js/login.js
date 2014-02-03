/**
 * @author Lee
 */


function change_login(){
	$(".box2_login").show();
	$(".box_login").hide();
	$(".box2_login .shadow").height($(document.body).height());
	$(".box2_login .shadow,#change_login .change_close,#change_login .lg .l_btn").click(function(){
		$(".box2_login").hide();	
	});
}

function ChangeSuccess(){
	$(".box3_login").show();
	$(".box2_login").hide();
	$(".box3_login .shadow").height($(document.body).height());
	$(".box3_login .shadow,#loginSuccess .closeSuccess").click(function(){
		 $(".box3_login").hide();	
	});
}

function back_login(){
	$(".box_login").show();
	$(".box2_login").hide();
	$(".box_login .shadow").height($(document.body).height());
	$(".box_login .shadow,#login .close,#login .lg .l_btn").click(function(){
		$(".box_login").hide();			
	});
}


function json_login(url){
	var name = $("#login_name").val();
	var pwd = $("#login_pwd").val();
	
	var mdata = {"username":name,"password":pwd}; 

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
};
