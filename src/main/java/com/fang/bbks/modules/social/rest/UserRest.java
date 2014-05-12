package com.fang.bbks.modules.social.rest;

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

import com.fang.bbks.common.persistence.JsonResult;
import com.fang.bbks.common.utils.CookieUtils;
import com.fang.bbks.common.utils.SessionUtil;
import com.fang.bbks.common.utils.Validate;
import com.fang.bbks.common.web.BaseController;
import com.fang.bbks.common.web.BaseResponse;
import com.fang.bbks.modules.social.dao.DynamicDao;
import com.fang.bbks.modules.social.entity.Dynamic;
import com.fang.bbks.modules.social.entity.Friend;
import com.fang.bbks.modules.social.entity.Message;
import com.fang.bbks.modules.social.service.DynamicService;
import com.fang.bbks.modules.social.service.MessageService;
import com.fang.bbks.modules.social.service.RelationService;
import com.fang.bbks.modules.sys.entity.User;
import com.fang.bbks.modules.sys.service.UserService;
import com.google.common.collect.Lists;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2013-12-2
 * @since 下午3:26:36
 */

@Controller
@RequestMapping(BaseController.REST_PREFIX + "/user")
public class UserRest extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserRest.class);

	@Autowired
	private UserService userService;
	@Autowired
	private DynamicService dynamicService;
	@Autowired
	private SessionUtil sessionUtil;
	@Autowired
	private RelationService relationService;
	@Autowired
	private MessageService messageService;
	

	@RequestMapping(value = { "/login" }, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String login(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "pwd", required = true) String pwd) {

		JsonResult jr = new JsonResult();

		try {
			User user = userService.signIn(name, pwd);

			if (user != null) {
				// 设置session
				sessionUtil.setSignInUser(request.getSession(), user);
				// 设置cookies
				CookieUtils.setUser(response, user.getUsername(), pwd,
						user.getId() + "");

				jr.setIsSuccess(Boolean.TRUE);
				jr.setMessage("成功！");
				jr.setObj(user);
				jr.setUser_id(user.getId());
			} else {
				jr.setIsSuccess(Boolean.FALSE);
				jr.setMessage("失败！");
			}
		} catch (Exception e) {
			jr.setIsSuccess(Boolean.FALSE);
			jr.setMessage("异常！");
		}

		return jr.toJson(jr);
	}

	@RequestMapping(value = { "/regist" }, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String login(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "pwd", required = true) String pwd) {

		JsonResult jr = new JsonResult();

		try {

			if (userService.isExit(name)) {
				jr.setIsSuccess(Boolean.FALSE);
				jr.setMessage("用户名被占用！");
				return jr.toJson(jr);
			} else if (userService.isExitEmail(email)) {
				jr.setIsSuccess(Boolean.FALSE);
				jr.setMessage("邮箱被占用！");
				return jr.toJson(jr);
			}

			User user = userService.signUp(name, email, pwd);

			if (user != null) {
				// 设置session
				sessionUtil.setSignInUser(request.getSession(), user);
				// 设置cookies
				CookieUtils.setUser(response, user.getUsername(), pwd,
						user.getId() + "");

				jr.setIsSuccess(Boolean.TRUE);
				jr.setMessage("成功！");
				jr.setObj(user);
				jr.setUser_id(user.getId());
			} else {
				jr.setIsSuccess(Boolean.FALSE);
				jr.setMessage("失败！");
			}

		} catch (Exception e) {
			jr.setIsSuccess(Boolean.FALSE);
			jr.setMessage("异常！");
		}

		return jr.toJson(jr);
	}

	/*** friends ****/
	@RequestMapping(value = { "/friends" }, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getFriends(
			@RequestParam(value = "uid", required = true) Long uid,
			@RequestParam(value = "type", required = true) Integer type) {

		JsonResult jr = new JsonResult();

		try {
			List<User> users = Lists.newArrayList();
			if (type == 1) {
				users = relationService.findFloweds(uid);
			} else {
				users = relationService.findFlowings(uid);
			}
			
			List<Friend> fs = Friend.transfer(users);
			
			if (fs != null && !fs.isEmpty()) {
				jr.setIsSuccess(Boolean.TRUE);
				jr.setMessage("成功！");
				jr.setUser_id(uid);
				jr.setRows(fs);
			} else {
				jr.setIsSuccess(Boolean.FALSE);
				jr.setMessage("失败！");
			}
		} catch (Exception e) {
			jr.setIsSuccess(Boolean.FALSE);
			jr.setMessage("异常！");
		}

		return jr.toJson(jr);
	}
	
	/*** messages ****/
	@RequestMapping(value = { "/messages" }, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getMessages(
			@RequestParam(value = "type", required = true) Integer type,
			@RequestParam(value = "uid", required = true) Long uid) {

		JsonResult jr = new JsonResult();

		try {
			
			List<Message> ms = Lists.newArrayList();
			if(type == 1){
				ms = messageService.ISend(uid);
			}else if(type == 2){
				ms = messageService.IRecived(uid);
			}else{
				ms = messageService.unRead(uid);
			}
			
					
			if (ms != null && !ms.isEmpty()) {
				jr.setIsSuccess(Boolean.TRUE);
				jr.setMessage("成功！");
				jr.setUser_id(uid);
				jr.setRows(ms);
			} else {
				jr.setIsSuccess(Boolean.FALSE);
				jr.setMessage("失败！");
			}
		} catch (Exception e) {
			jr.setIsSuccess(Boolean.FALSE);
			jr.setMessage("异常！");
		}

		return jr.toJson(jr);
	}
	
	/*** dynamic ****/

	@RequestMapping(value = { "/dynamics" }, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getDynamics(
			@RequestParam(value = "uid" ,required=true) Long uid,
			@RequestParam(value = "type" ,required=false) Integer type) {

		// TODO 需要对信息进行装载
		JsonResult jr = new JsonResult();

		try {
			
			List<Dynamic> ds = dynamicService.listDynamic(uid);
					
			if (ds != null && !ds.isEmpty()) {
				jr.setIsSuccess(Boolean.TRUE);
				jr.setMessage("成功！");
				jr.setUser_id(uid);
				jr.setRows(ds);
			} else {
				jr.setIsSuccess(Boolean.FALSE);
				jr.setMessage("失败！");
			}
		} catch (Exception e) {
			jr.setIsSuccess(Boolean.FALSE);
			jr.setMessage("异常！");
		}

		return jr.toJson(jr);
	}

	@RequestMapping(value = { "/dynamic/:uid" }, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	ResponseEntity<BaseResponse> publishDynamic(
			@PathVariable(value = "uid") Long uid,
			@RequestParam(required = true, value = "content") String content,
			HttpServletRequest request, HttpSession session) {
		try {
			dynamicService.publishDynamic(content, uid);
			return new ResponseEntity<BaseResponse>(defaultError(),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<BaseResponse>(defaultOK(), HttpStatus.OK);
		}
	}

}
