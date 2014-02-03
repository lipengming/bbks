/**
 * @author hxl
 */
//清空输入框
function emputyInput(e){
	e.value="";
}
function emputyPasswd(e){
	e.value="";
	$("#otherreg .reg_step1 ul .pass_reg1").hide();
	$("#otherreg .reg_step1 ul .pass_reg2").show();
	$("#otherreg .reg_step1 ul .pass_reg2 input").focus();
}

function loginEmptyPassword(){
	$("#login .login_main .pass1").hide();
	$("#login .login_main .pass2").show();
	$("#login .login_main .pass2 input").focus();
}
