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
		uiModel.addAttribute("indexOne",new Integer(0));
		
		if(request.getSession() !=null){
			System.out.println("get.."+request.getSession().getAttribute("aaa"));
		}else{
			System.out.println("-----!-000000");
		}
		

		
		return "index";
	}
	
	@RequestMapping(value = {"/book/search"}, method = {RequestMethod.POST,RequestMethod.GET})
	public String search(Model uiModel,HttpServletRequest request,HttpSession session,
			@RequestParam(value="keywords",required=true)String keywords,
			@RequestParam(value="sort",required=false)String sort,
			@RequestParam(value="sortOrder",required=false)String sortOrder){
		String qStr = "kw="+keywords+"&sortby="+sort+"&sortOrders"+sortOrder;
		uiModel.addAttribute("qStr", qStr);
		uiModel.addAttribute("top5", top5());
		uiModel.addAttribute("more", more());
		uiModel.addAttribute("indexOne",new Integer(0));
		return "index";
	}
	
	@RequestMapping(value = {"/book/catlog"}, method = {RequestMethod.POST,RequestMethod.GET})
	public String catlog(Model uiModel,HttpServletRequest request,HttpSession session,
			@RequestParam(value="catlog",required=true) String catlog,
			@RequestParam(value="sort",required=false)String sort,
			@RequestParam(value="sortOrder",required=false)String sortOrder){
		String qStr = "categroy_id="+catlog+"&sortby="+sort+"&sortOrders"+sortOrder;
		uiModel.addAttribute("qStr", qStr);
		uiModel.addAttribute("categroy",categoryService.findOne(Long.parseLong(catlog)));
		uiModel.addAttribute("top5", top5());
		uiModel.addAttribute("more", more());
		uiModel.addAttribute("indexOne",new Integer(0));
		return "index";
	}
	
	@RequestMapping(value = {"/book/news"}, method = {RequestMethod.POST,RequestMethod.GET})
	public String news(Model uiModel,HttpServletRequest request,HttpSession session,
			@RequestParam(value="sort",required=false)String sort,
			@RequestParam(value="sortOrder",required=false)String sortOrder){
		String qStr = "sortby="+sort+"&typeStr=news"+"&sortOrders"+sortOrder;;
		uiModel.addAttribute("qStr", qStr);
		uiModel.addAttribute("top5", top5());
		uiModel.addAttribute("more", more());
		uiModel.addAttribute("indexOne",new Integer(1));
		return "index";
	}
	
	@RequestMapping(value = {"/book/promotion"}, method = {RequestMethod.POST,RequestMethod.GET})
	public String promotion(Model uiModel,HttpServletRequest request,HttpSession session,
			@RequestParam(value="sort",required=false)String sort,
			@RequestParam(value="sortOrder",required=false)String sortOrder){
		String qStr = "sortby="+sort+"&&typeStr=promotion"+"&sortOrders"+sortOrder;;
		uiModel.addAttribute("qStr", qStr);
		uiModel.addAttribute("top5", top5());
		uiModel.addAttribute("more", more());
		uiModel.addAttribute("indexOne",new Integer(2));
		return "index";
	}
	
	@RequestMapping(value = {"/book/salerank"}, method = {RequestMethod.POST,RequestMethod.GET})
	public String saleRank(Model uiModel,HttpServletRequest request,HttpSession session,
			@RequestParam(value="sort",required=false)String sort,
			@RequestParam(value="sortOrder",required=false)String sortOrder){
		String qStr = "sortby="+sort+"&typeStr=salerank"+"&sortOrders"+sortOrder;;
		uiModel.addAttribute("qStr", qStr);
		uiModel.addAttribute("top5", top5());
		uiModel.addAttribute("more", more());
		uiModel.addAttribute("indexOne",new Integer(3));
		return "index";
	}
	

	@RequestMapping(value = {"/book/searchrank"}, method = {RequestMethod.POST,RequestMethod.GET})
	public String searchRank(Model uiModel,HttpServletRequest request,HttpSession session,
			@RequestParam(value="sort",required=false)String sort,
			@RequestParam(value="sortOrder",required=false)String sortOrder){
		String qStr = "sortby="+sort+"&typeStr=searchrank"+"&sortOrders"+sortOrder;;
		uiModel.addAttribute("qStr", qStr);
		uiModel.addAttribute("top5", top5());
		uiModel.addAttribute("more", more());
		uiModel.addAttribute("indexOne",new Integer(4));
		return "index";
	}
	
	
	
	
	//////////////////////////////////////////
	//////////////////////////////////////////	
	
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
		
		for(int a = 0;a < 20; a++){
			top.add(all.get(a+9));
		}
		return top;
	}
}
