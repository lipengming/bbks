var op = {
	pb_onbind_event:function(d){
		d.find(".pb").hover(
				function(){
					$(this).find(".s_btn").show();
				},
				function(){
					$(this).find(".s_btn").hide();	
				}
		);
		
		d.find(".pb_write_text").next(".ph").hide();
		d.find(".pb_write_text").focus(
			function(){
				console.log("::show:");
				$(this).next(".ph").show();
			}
		);
//		d.find(".pb_write_text").blur(
//			function(){
//				console.log("::hide:");
//				$(this).next(".ph").hide();
//			}
//		);
		
		//事件监听：：：：
		//喜欢
		d.find(".like em").click(
			function(){
				$(this).toggleClass("liked");	
		});
		
		//书城比价按钮
		d.find(".s_btn_1").click(
			function(){
				//弹出层显示
				$(".box_wap").show();
				//弹出层比价层显示
				$(".box_wap .money").show();			
		});
		//书城加入书架按钮
		d.find(".s_btn_2,.book_info_btn .join,.gb_img .gb_img_btn_2").click(
			function(){
				//弹出层显示
				d.find(".box_wap").show();
				//弹出层加入书架显示
				d.find(".box_wap .bookshelf").show();
				//弹出层下拉按钮
				d.find(".bookshelf .read .s").click(
					function(){
						$(this).next(".more_list").toggle();
				});
				//弹出层标签删除
				d.find(".read_span a .x").click(
					function(){
						$(this).parents("a").hide();
						return false;
				});
		});
		//弹出层关闭按钮
		d.find(".box_wap .close").click(
			function(){
				$(".box_wap").hide();
				$(".box_wap .bookshelf").hide();
				$(".box_wap .money").hide();
		});
		
	},
	index_form_post:function(){
		alert("ok");
//		$('#index_search_form').ajaxForm({ 
//		    dataType:  'json', 
//		    beforeSubmit: function(formData, jqForm, options){
//	            //validate in js
//		    },
//		    success:  function(data){
//		    	alert("");
//		    },
//		    complete: function(jqXHR, textStatus){
//		    	
//		  	}
//		});	
	}
	
};