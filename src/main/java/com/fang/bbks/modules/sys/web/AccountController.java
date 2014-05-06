/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-7-30
 */
package com.fang.bbks.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fang.bbks.common.utils.CookieUtils;
import com.fang.bbks.common.utils.SessionUtil;
import com.fang.bbks.common.utils.Validate;
import com.fang.bbks.common.web.BaseController;
import com.fang.bbks.common.web.BaseResponse;
import com.fang.bbks.modules.sys.entity.User;
import com.fang.bbks.modules.sys.service.UserService;


/**
 * @author Lee
 */

@Controller
public class AccountController extends BaseController{
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method=RequestMethod.GET,value={"/login"})
	public String signIn(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		
		if(SessionUtil.getSignInUser(session) != null)
			return "redirect:/index";
		if(CookieUtils.getUser(request) != null){
			User u = CookieUtils.getUser(request);
			return "redirect:/index";
		}
		
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.GET,value={"/regist"})
	public String signUp(){
		return "reg";
	}
	
	@RequestMapping(method=RequestMethod.POST,value={"/login"})
	public String doSignIn(
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="password",required=false) String pwd,
			HttpServletRequest request,HttpServletResponse response,HttpSession session){
		
		boolean isname = username == null || "".equals(username) || username.length() < 3;
		boolean ispwd = pwd == null || "".equals(pwd) || pwd.length() < 3;
		
		System.out.println("login....");
		
		if(!isname && !ispwd){
			User u = userService.signIn(username, pwd);
			if(u != null){
				//设置session
				SessionUtil.setSignInUser(session, u);
				//设置cookies
				//CookieUtils.setUser(response, username, pwd, u.getId()+"");
				return "redirect:/index";
			}
		}
		return "redirect:/login";
	}
	
	@RequestMapping(method=RequestMethod.POST,value={"/regist"})
	public String  doSignUp(
			@RequestParam(value="username",required=true) String username,
			@RequestParam(value="email",required=true) String email,
			@RequestParam(value="password",required=true) String pwd,
			@RequestParam(value="repassword",required=true) String repwd,
			HttpServletRequest request,Model uiModel,
			HttpSession session,HttpServletResponse response){
		
		boolean isname = username == null || "".equals(username) || username.length() < 3;
		boolean ispwd = pwd == null|| "".equals(pwd) || pwd.length() < 3;
		boolean isEmail = email == null || !Validate.isEmail(email);
		
		if(!isname && !ispwd && ! isEmail){
			if(userService.isExit(username)){
				uiModel.addAttribute("error", "用户名已被占用");
				return "redirect:/regist";
			}
			
			if(userService.isExitEmail(email)){
				uiModel.addAttribute("error", "邮件已被占用");
				return "redirect:/regist";
			}
			
			User u = userService.signUp(username,email, pwd);
			
			if(u != null){
				//注册成功，去登录
				return "redirect:/login";
			}
		}else{
			uiModel.addAttribute("error", "为满足校验规则！");
		}
		
		return "redirect:/regist";
	}
	
	@RequestMapping(method=RequestMethod.GET,value={"/logout","/logOut"})
	public String logOut(HttpSession session,HttpServletRequest request){
		SessionUtil.logOut(session);
		return "redirect:/index";
	}
}

