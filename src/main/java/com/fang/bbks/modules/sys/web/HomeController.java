/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-7-31
 */
package com.fang.bbks.modules.sys.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fang.bbks.common.web.BaseController;
import com.fang.bbks.modules.sys.service.CategoryService;

/**
 * @author Lee
 */
@Controller("/home")
public class HomeController extends BaseController{
	
	@Resource
	CategoryService categoryService;
	
	@RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
	public String index(Model uiModel,HttpServletRequest request,HttpSession session) {
		uiModel.addAttribute("qStr", "");
		uiModel.addAttribute("categoryMapper", categoryService.getCaList());
		return "index";
	}
	
	@RequestMapping(value = {"/book/search"}, method = {RequestMethod.POST,RequestMethod.GET})
	public String search(Model uiModel,HttpServletRequest request,HttpSession session,
			@RequestParam(value="keywords",required=true)String keywords){
		String qStr = "kw="+keywords+"&";
		uiModel.addAttribute("qStr", qStr);
		uiModel.addAttribute("categoryMapper", categoryService.getCaList());
		return "index";
	}
	
	@RequestMapping(value = {"/book/catlog"}, method = {RequestMethod.POST,RequestMethod.GET})
	public String catlog(Model uiModel,HttpServletRequest request,HttpSession session,
			@RequestParam(value="catlog",required=true) String catlog){
		String qStr = "categroy_id="+catlog+"&";
		uiModel.addAttribute("qStr", qStr);
		uiModel.addAttribute("categroy",catlog);
		uiModel.addAttribute("categoryMapper", categoryService.getCaList());
		return "index";
	}
	
}
