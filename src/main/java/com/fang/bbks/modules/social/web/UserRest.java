package com.fang.bbks.modules.social.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fang.bbks.common.utils.CookieUtils;
import com.fang.bbks.common.utils.SessionUtil;
import com.fang.bbks.common.utils.Validate;
import com.fang.bbks.common.web.BaseController;
import com.fang.bbks.common.web.BaseResponse;
import com.fang.bbks.modules.social.dao.DynamicDao;
import com.fang.bbks.modules.social.entity.Dynamic;
import com.fang.bbks.modules.social.service.DynamicService;
import com.fang.bbks.modules.sys.entity.User;
import com.fang.bbks.modules.sys.service.UserService;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2013-12-2
 * @since 下午3:26:36	
 */

@Controller
@RequestMapping(BaseController.REST_PREFIX + "/user")
public class UserRest extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(UserRest.class);
	
	@Autowired
	UserService userService;
	@Autowired
	DynamicService dynamicService;
	
	@RequestMapping(method=RequestMethod.POST,value={"/signIn","/login"},produces = {"application/json;charset=UTF-8"},headers={"Content-Type=*","Accept=application/json"})
	public @ResponseBody ResponseEntity<BaseResponse> doSignIn(
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="password",required=false) String pwd,
			HttpServletRequest request,HttpServletResponse response,HttpSession session){
		
		boolean isname = username == null || "".equals(username) || username.length() < 3;
		boolean ispwd = pwd == null || "".equals(pwd) || pwd.length() < 3;
		
		if(!isname && !ispwd){
			User u = userService.signIn(username, pwd);
			if(u != null){
				//设置session
				SessionUtil.setSignInUser(session, u);
				//设置cookies
				CookieUtils.setUser(response, username, pwd, u.getId()+"");
				return new ResponseEntity<BaseResponse>(defaultOK(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<BaseResponse>(defaultError(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST,value={"/signUp","/regist"},produces = {"application/json;charset=UTF-8"},headers={"Content-Type=*","Accept=application/json"})
	public @ResponseBody ResponseEntity<BaseResponse>  doSignUp(
			@RequestParam(value="username",required=true) String username,
			@RequestParam(value="email",required=true) String email,
			@RequestParam(value="password",required=true) String pwd,
			@RequestParam(value="repassword",required=true) String repwd,
			HttpServletRequest request,HttpSession session,HttpServletResponse response){
		
		boolean isname = username == null || "".equals(username) || username.length() < 3;
		boolean ispwd = pwd == null|| "".equals(pwd) || pwd.length() < 3;
		boolean isEmail = email == null || !Validate.isEmail(email);
		
		if(!isname && !ispwd && !isEmail){
			
			if(userService.isExitEmail(email))
				return new ResponseEntity<BaseResponse>(validateError("邮件已被占用"), HttpStatus.OK);
			if(userService.isExit(username))
				return new ResponseEntity<BaseResponse>(validateError("当前用户已存在"), HttpStatus.OK);
			
			User u = userService.signUp(username,email, pwd);
			
			if(u != null){
				//设置session
				SessionUtil.setSignInUser(session, u);
				//设置cookies
				CookieUtils.setUser(response, username, pwd, u.getId()+"");
				return new ResponseEntity<BaseResponse>(defaultOK(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<BaseResponse>(defaultError(), HttpStatus.OK);
	}
	
	/***dynamic****/
	
	@RequestMapping(value = {"/dynamics/:uid"}, method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"},headers={"Content-Type=*","Accept=application/json"} )
	public @ResponseBody List<Dynamic> getDynamics(
			@PathVariable(value="uid") Integer uid,
			HttpServletRequest request,HttpSession session){
		
		List<Dynamic> ds = dynamicService.listDynamic(uid);
		//TODO 需要对信息进行装载
		
		return ds;
	}
	
	@RequestMapping(value = {"/dynamic/:uid"}, method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"},headers={"Content-Type=*","Accept=application/json"} )
	public @ResponseBody ResponseEntity<BaseResponse> publishDynamic(
			@PathVariable(value="uid") Integer uid,
			@RequestParam(required = true,value = "content") String content,
			HttpServletRequest request,HttpSession session){
		try{
			dynamicService.publishDynamic(content, uid);
			return new ResponseEntity<BaseResponse>(defaultError(), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<BaseResponse>(defaultOK(), HttpStatus.OK);
		}
	}
	
	
	
}
