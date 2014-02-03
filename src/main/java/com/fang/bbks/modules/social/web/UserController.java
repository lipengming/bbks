package com.fang.bbks.modules.social.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fang.bbks.common.web.BaseController;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2013-11-15
 * @since 下午1:59:52
 */

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	/**
	 * 个人主页
	 * 
	 * @param request
	 * @param mv
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/profile/index","/profile/","/profile" })
	public String profile(Model uiModel, HttpServletRequest request,
			HttpSession session) {

		return "/user/profile";
	}

	/**
	 * 用户信息主页
	 * 
	 * @param request
	 * @param mv
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/detail/{uid}" })
	public String detail(@PathVariable("uid") Integer uid, Model uiModel,
			HttpServletRequest request, HttpSession session) {

		return "/user/detail";
	}
	
	

}
