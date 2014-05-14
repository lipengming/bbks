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
import com.fang.bbks.modules.social.entity.DonateType;
import com.fang.bbks.modules.social.entity.InterestType;
import com.fang.bbks.modules.social.service.DonateService;
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
	@Autowired
	private DonateService donateService;
	
	
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
		
		InterestType inType = InterestType.getTypeById(type);
		if(inType == InterestType.unknow){
			//search all
			inType = null;
		}
		
		//如果uid == user.getId ,访问的自己的书架信息，所以展示相似的就是了。否则展示，该用户相关的。
		//search by type
		model.addAttribute("invos", interestService.findAll(uid, inType,user.getId()));
		User cuser = userService.findOne(uid);
		
		model.addAttribute("userInfo", cuser);
		
		return "/book/bookShelf";
	}
	
	@RequestMapping(value={"/similar"})
	public String similar(HttpServletRequest request,Model model,
			@RequestParam(value="uid",required=false) Long uid,
			@RequestParam(value="type",required=false) Integer type){
		User user = sessionUtil.getSignInUser(request.getSession()); 
		if(user == null){
			model.addAttribute(HANDLER_MSG, "登录后才能查看书架信息");
			return "redirect:/login";
		}
		if(uid != null && !user.getId().equals(uid)){
			return "redirect:/bs/index?uid="+uid;
		}
		
		InterestType inType = InterestType.getTypeById(type);
		if(inType == InterestType.unknow){
			//search all
			inType = null;
		}
		
		model.addAttribute("userInfo", user);
		model.addAttribute("invos", interestService.findSimilar(uid, inType));

		return "/book/similar";
	}
	
	@RequestMapping(value={"/donate"})
	public String donate(HttpServletRequest request,Model model,
			@RequestParam(value="uid",required=false) Long uid){
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
		model.addAttribute("userInfo", cuser);
		model.addAttribute("donateInfo",donateService.findAll(cuser.getId()));
		
		return "/book/bookDonate";
	}
	
	@RequestMapping(value={"/bookmarks"})
	public String bookMark(HttpServletRequest request,Model model,
			@RequestParam(value="uid",required=false) Long uid){
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
		model.addAttribute("userInfo", cuser);
		
		
		return "/book/bookmarks";
	}
	
	
	
	
	
	@RequestMapping(value={"/uploadEbook"})
	public String uploadEbook(HttpServletRequest request,Model model,
			@RequestParam(value="bookSrc",required=true) String bookSrc,
			@RequestParam(value="bookIsbn",required=false) String bookIsbn
			//@RequestParam(value="bookName",required=false) String bookName
			){
		
		User u = sessionUtil.getSignInUser(request.getSession());
		if(u == null){
			model.addAttribute(HANDLER_MSG, "登录后才能查看书架信息");
			return "redirect:/login";
		}
		try{
			Book b =  bookService.findByIsbn(bookIsbn);//bookService.findByBookNameOrIsbn(bookName, bookIsbn);
			System.out.println("--find--");
			if(b != null){
				bookService.updateEbook("", bookSrc, b.getId());
				System.out.println("--update--");
			}
			donateService.DonateBook(b.getId(), bookSrc, DonateType.donate_txt,bookIsbn,u.getId());
			model.addAttribute(HANDLER_MSG, "处理成功！");
		}catch(Exception e){
			e.printStackTrace();
			logger.error("异常，{},{}", e.getMessage(),e);
			model.addAttribute(HANDLER_MSG, "处理失败！");
		}
		return "redirect:/bs/donate";
	}
	
}
