/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-7-31
 */
package com.fang.bbks.modules.sys.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fang.bbks.common.web.BaseController;
import com.fang.bbks.modules.sys.entity.Category;
import com.fang.bbks.modules.sys.service.CategoryService;
import com.google.common.collect.Lists;

/**
 * @author Lee
 */
@Controller("/home")
public class HomeController extends BaseController{
	private static Logger log = LoggerFactory.getLogger(HomeController.class);
	@Resource
	private CategoryService categoryService;
	
	private static List<Category> all = Lists.newArrayList();
	
	@RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
	public String index(Model uiModel,HttpServletRequest request,HttpSession session) {
		uiModel.addAttribute("qStr", "");
		uiModel.addAttribute("top5", top5());
		uiModel.addAttribute("more", more());
		return "index";
	}
	
	@RequestMapping(value = {"/book/search"}, method = {RequestMethod.POST,RequestMethod.GET})
	public String search(Model uiModel,HttpServletRequest request,HttpSession session,
			@RequestParam(value="keywords",required=true)String keywords){
		String qStr = "kw="+keywords+"&";
		uiModel.addAttribute("qStr", qStr);
		uiModel.addAttribute("top5", top5());
		uiModel.addAttribute("more", more());
		return "index";
	}
	
	@RequestMapping(value = {"/book/catlog"}, method = {RequestMethod.POST,RequestMethod.GET})
	public String catlog(Model uiModel,HttpServletRequest request,HttpSession session,
			@RequestParam(value="catlog",required=true) String catlog){
		String qStr = "categroy_id="+catlog+"&";
		uiModel.addAttribute("qStr", qStr);
		uiModel.addAttribute("categroy",catlog);
		uiModel.addAttribute("top5", top5());
		uiModel.addAttribute("more", more());
		return "index";
	}
	
	private List<Category> top5(){
		List<Category> top = Lists.newArrayList();
		
		if(all.isEmpty()){
			all.addAll(categoryService.getCaList());
		}

		if(all.isEmpty()){
			log.error("catlog is null..");
			return all;
		}
				
		for(int a = 0;a < 5; a++){
			top.add(all.get(a+3));
		}
		
		return top;
	}
	
	private List<Category> more(){
		List<Category> top = Lists.newArrayList();
		
		if(all.isEmpty()){
			all.addAll(categoryService.getCaList());
		}

		if(all.isEmpty()){
			log.error("catlog is null..");
			return all;
		}
		
		for(int a = 0;a < 5; a++){
			top.add(all.get(a+9));
		}
		return top;
	}
}
