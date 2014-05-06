package com.fang.bbks.modules.social.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fang.bbks.common.utils.SessionUtil;
import com.fang.bbks.common.web.BaseController;
import com.fang.bbks.modules.sys.entity.User;
import com.fang.bbks.modules.sys.service.UserService;

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
	private final static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 个人主页
	 * 
	 * @param request
	 * @param mv
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/profile/index","/profile/","/profile" })
	public String profile(Model uiModel,HttpServletRequest request,HttpServletResponse response) {
		User u = SessionUtil.getSignInUser(request.getSession());
		
		if(u == null){
			return "redirect:/login";
		}
		//TODO 动态信息
		//TODO 评论信息
		//TODO 关注的人
		//TODO 消息列表
		uiModel.addAttribute("userInfo",u);
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
