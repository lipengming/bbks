package com.fang.bbks.modules.sys.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fang.bbks.common.utils.SessionUtil;
import com.fang.bbks.common.web.BaseController;
import com.fang.bbks.modules.social.entity.InterestType;
import com.fang.bbks.modules.social.service.InterestService;
import com.fang.bbks.modules.sys.entity.Book;
import com.fang.bbks.modules.sys.entity.User;
import com.fang.bbks.modules.sys.service.BookService;
import com.fang.bbks.modules.sys.service.UserService;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-13
 * @since 下午2:00:44	
 */
@Controller
@RequestMapping("/bs")
public class BookShelfController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(BookShelfController.class);
	
	@Autowired
	private SessionUtil sessionUtil;
	@Autowired
	private BookService bookService;
	@Autowired
	private InterestService interestService;
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value={"/","/index"})
	public String index(HttpServletRequest request,Model model,
			@RequestParam(value="uid",required=false) Long uid,
			@RequestParam(value="type",required=false) Integer type){
		
		User user = sessionUtil.getSignInUser(request.getSession()); 
		if(user == null){
			model.addAttribute(HANDLER_MSG, "登录后才能查看书架信息");
			return "redirect:/login";
		}
		if(uid == null){
			//使用
			uid = user.getId();
		}
		User cuser = userService.findOne(uid);
		
		InterestType inType = InterestType.getTypeById(type);
		if(inType == InterestType.unknow){
			//search all
			inType = null;
		}
		//search by type
		model.addAttribute("invos", interestService.findAll(uid, inType));
		model.addAttribute("userInfo", cuser);
		
		return "/book/bookShelf";
	}
	@RequestMapping(value={"/donate"})
	public String donate(HttpServletRequest request,Model model){
		if(sessionUtil.isLogin(request.getSession())){
			return "/book/bookDonate";
		}else{
			model.addAttribute(HANDLER_MSG, "登录后才能查看书架信息");
			return "redirect:/login";
		}
	}
	@RequestMapping(value={"/uploadEbook"})
	public String uploadEbook(HttpServletRequest request,Model model,
			@RequestParam(value="bookSrc",required=true) String bookSrc,
			@RequestParam(value="bookIsbn",required=false) String bookIsbn
			//@RequestParam(value="bookName",required=false) String bookName
			){
		if(!sessionUtil.isLogin(request.getSession())){
			model.addAttribute(HANDLER_MSG, "登录后才能查看书架信息");
			return "redirect:/login";
		}
		try{
			Book b =  bookService.findByIsbn(bookIsbn);//bookService.findByBookNameOrIsbn(bookName, bookIsbn);
			System.out.println("--find--");
			if(b != null){
				bookService.updateEbook(bookSrc, "", b.getId());
				System.out.println("--update--");
			}
			model.addAttribute(HANDLER_MSG, "处理成功！");
		}catch(Exception e){
			e.printStackTrace();
			logger.error("异常，{},{}", e.getMessage(),e);
			model.addAttribute(HANDLER_MSG, "处理失败！");
		}
		return "redirect:/bs/donate";
	}
	
}
