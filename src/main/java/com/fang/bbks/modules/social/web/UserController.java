package com.fang.bbks.modules.social.web;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fang.bbks.common.utils.SessionUtil;
import com.fang.bbks.common.web.BaseController;
import com.fang.bbks.modules.social.entity.Dynamic;
import com.fang.bbks.modules.social.service.DynamicService;
import com.fang.bbks.modules.social.service.NetWorkService;
import com.fang.bbks.modules.social.service.RelationService;
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
	@Autowired
	private DynamicService dynamicService;
	@Autowired
	private NetWorkService netWorkService;
	@Autowired
	RelationService relationService;
	
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
			uiModel.addAttribute(HANDLER_MSG, "您还未登录，请登录");
			return "redirect:/login";
		}

		uiModel = netWorkService.getUserInfo(u.getId(),uiModel);
		
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
	public String detail(@PathVariable("uid") Long uid, Model uiModel,
			HttpServletRequest request, HttpSession session) {
		User cu =SessionUtil.getSignInUser(session);
		//未登录
		if(cu == null){
			uiModel.addAttribute(HANDLER_MSG, "您还未登录，请登录");
			return "redirect:/login";
		}
		
		//资源不存在，转首页
		if(userService.findOne(uid) == null){
			uiModel.addAttribute(HANDLER_MSG, "您要访问的资源已经被删除！");
			return "redirect:/index";
		}
		
		//转个人主页
		if(uid == cu.getId()){
			return "redirect:/user/profile";
		}
		
		uiModel = netWorkService.getUserInfo(uid,uiModel);
		
		if(relationService.isFlow(cu.getId(), uid)){
			uiModel.addAttribute("doFlow", true);
		}
		
		return "/user/detail";
	}
	
	/**
	 * 跟新用户状态信息
	 * @param uiModel
	 * @param request
	 * @param response
	 * @param description
	 * @return
	 */
	@RequestMapping(value = { "/updateStatus" })
	public String updateStatus( Model uiModel,HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "description",required = true) String description){
		
		log.debug("update description...{}",description);
		
		User u = SessionUtil.getSignInUser(request.getSession());
		if(u == null){
			uiModel.addAttribute(HANDLER_MSG, "您还未登录，请登录");
			return "redirect:/login";
		}
		
		try{
			userService.updateState(description,u.getId());
			
			u.setDescription(description);
			SessionUtil.setSignInUser(request.getSession(), u);
			
		}catch(Exception e){
			log.error("数据操作异常，{}",e.getMessage(),e);
			uiModel.addAttribute(HANDLER_MSG,"系统异常，稍后再试！");
		}
		return "redirect:/user/profile";
	}
	
	@RequestMapping(value = { "/updateAvatar" })
	public String updateAvatar( Model uiModel,HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "avatarSrc",required = true) String avatarSrc){
		
		log.debug("update avatarSrc...{}",avatarSrc);
		
		User u = SessionUtil.getSignInUser(request.getSession());
		if(u == null){
			uiModel.addAttribute(HANDLER_MSG, "您还未登录，请登录");
			return "redirect:/login";
		}
		
		try{
			userService.updateAvatar(avatarSrc,u.getId());
			
			u.setAvatar(avatarSrc);
			SessionUtil.setSignInUser(request.getSession(), u);
			
		}catch(Exception e){
			log.error("数据操作异常，{}",e.getMessage(),e);
			uiModel.addAttribute(HANDLER_MSG,"系统异常，稍后再试！");
		}
		return "redirect:/user/profile";
	}
	
	@RequestMapping(value = { "/publishDynamic" })
	public String publishDynamic( Model uiModel,HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "content",required = true) String content){
		
		log.debug("update content...{}",content);
		
		User u = SessionUtil.getSignInUser(request.getSession());
		if(u == null){
			uiModel.addAttribute(HANDLER_MSG, "您还未登录，请登录");
			return "redirect:/login";
		}
		
		try{
			dynamicService.publishDynamic(content, u.getId());
		}catch(Exception e){
			log.error("数据操作异常，{}",e.getMessage(),e);
			uiModel.addAttribute(HANDLER_MSG,"系统异常，稍后再试！");
		}
		return "redirect:/user/profile";
	}
	
	@RequestMapping(value = { "/flow" })
	public String flow( Model uiModel,HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "uid",required = true) Long uid){
		log.debug("flow...{}",uid);
		
		User u = SessionUtil.getSignInUser(request.getSession());
		if(u == null){
			uiModel.addAttribute(HANDLER_MSG, "您还未登录，请登录");
			return "redirect:/login";
		}
		try{
			netWorkService.flow(u.getId(), uid);
		}catch(Exception e){
			e.printStackTrace();
			log.error("err flow handler,{},{}", e.getMessage(),e);
		}
		
		return "redirect:/user/detail/"+uid;
	}
	
	@RequestMapping(value = { "/unflow" })
	public String unflow( Model uiModel,HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "uid",required = true) Long uid){
		log.debug("unflow...{}",uid);
		
		User u = SessionUtil.getSignInUser(request.getSession());
		if(u == null){
			uiModel.addAttribute(HANDLER_MSG, "您还未登录，请登录");
			return "redirect:/login";
		}
		try{
			netWorkService.unflow(u.getId(), uid);
		}catch(Exception e){
			e.printStackTrace();
			log.error("err flow handler,{},{}", e.getMessage(),e);
		}
		
		return "redirect:/user/detail/"+uid;
	}
}
