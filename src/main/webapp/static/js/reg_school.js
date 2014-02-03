/**
 * @author 黄小龙
 */

var xmlrequest;
function createXMLHttpRequest() {
  
	if (window.ActiveXObject) {
		xmlrequest = new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		xmlrequest = new XMLHttpRequest();
	}
}


function showSchools(){
	$("#school_div").show();
	//$("#reg .reg_info ul li .text_kj .text_p").val("");
}

function closeUniTbl(){
	$("#school_div").hide();
}

function setProvince(event){
	this.createXMLHttpRequest();
	//alert("ok");
	//发送数据获取省份
	if (xmlrequest) {
			
	}
}

function setUniversity(event){
	this.createXMLHttpRequest();
	//alert("ok");
	//发送数据获取大学
	if (xmlrequest) {
			
	}
}
