package com.fang.bbks.modules.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.avro.data.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fang.bbks.common.persistence.JsonResult;
import com.fang.bbks.common.persistence.Page;
import com.fang.bbks.common.utils.SessionUtil;
import com.fang.bbks.common.web.BaseController;
import com.fang.bbks.modules.sys.entity.Comment;
import com.fang.bbks.modules.sys.service.CommentService;
import com.google.common.collect.Lists;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-10
 * @since 上午9:17:52	
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController{
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private SessionUtil sessionUtil;
	
	@ModelAttribute
	public Comment get(
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="contentId",required=false) Long contentId,
			@RequestParam(value="module",required=false) String module,
			@RequestParam(value="title",required=false) String title,
			@RequestParam(value="content",required=false) String content) {
		
		Comment comment = new Comment();
		
		comment.setContentId(contentId);
		comment.setModule(module);
		comment.setContent(content);
		comment.setTitle(title);
		comment.setId(id);
		
		return comment;
	}
	
	@RequestMapping("/find")
	public String find(HttpServletRequest request,HttpServletResponse response,Model model,Comment comment){
		Page<Comment> page = new Page<Comment>(request,response);
		page = commentService.find(page, comment);
		if(page.getList() != null || !page.getList().isEmpty()){
			model.addAttribute("commentList", page.getList());
		}else{
			model.addAttribute("commentList", Lists.newArrayList());
		}
		return "/book/bookComment";
	}
	
	
	

}
