package com.fang.bbks.modules.sys.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fang.bbks.common.persistence.Code;
import com.fang.bbks.common.persistence.JsonResult;
import com.fang.bbks.common.utils.SessionUtil;
import com.fang.bbks.common.web.BaseController;
import com.fang.bbks.modules.sys.entity.Category;
import com.fang.bbks.modules.sys.entity.Comment;
import com.fang.bbks.modules.sys.service.CategoryService;
import com.fang.bbks.modules.sys.service.CommentService;
import com.google.common.collect.Lists;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-4
 * @since 下午1:54:08	
 */
@Controller
@RequestMapping(BaseController.REST_PREFIX+"/comment")
public class CommentRest extends BaseController{
	private final static Logger logger = LoggerFactory.getLogger(CommentRest.class);
	
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
		
		System.out.println(content);
		System.out.println(comment);
		
		return comment;
	}
	
	@RequestMapping(value="add",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest request,HttpServletResponse response,Model model,Comment comment){
		JsonResult jr = new JsonResult();
		if(!sessionUtil.isLogin(request.getSession())){
			jr.setIsSuccess(false);
			jr.setMessage("还未登录，不能评论");
		}
		try{
			commentService.addComment(request,comment);
			jr.setIsSuccess(true);
			jr.setMessage("成功！");
			jr.setObj(comment);
		}catch(Exception e){
			e.printStackTrace();
			jr.setIsSuccess(false);
			jr.setMessage("异常！");
		}
		return jr.toJson(jr);
	}
}
