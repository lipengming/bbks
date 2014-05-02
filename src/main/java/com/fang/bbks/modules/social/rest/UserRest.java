package com.fang.bbks.modules.social.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fang.bbks.common.persistence.JsonResult;
import com.fang.bbks.common.web.BaseController;
import com.fang.bbks.modules.sys.entity.User;
import com.fang.bbks.modules.sys.service.UserService;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-2
 * @since 下午6:32:41	
 */

@Controller
@RequestMapping(value = BaseController.REST_PREFIX+"/user")
public class UserRest extends BaseController{
	
	@Autowired
	UserService us;
	
	@RequestMapping(value={"/login"},method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String login(
			@RequestParam(value="name",required=true)String name,
			@RequestParam(value="pwd",required=true)String pwd){
		JsonResult jr = new JsonResult();
		
		try{
			User user = us.signIn(name, pwd);
			
			if(user != null){
				jr.setIsSuccess(Boolean.TRUE);
				jr.setMessage("成功！");
				jr.setObj(user);
			}else{
				jr.setIsSuccess(Boolean.FALSE);
				jr.setMessage("失败！");
			}
		}catch(Exception e){
			jr.setIsSuccess(Boolean.FALSE);
			jr.setMessage("异常！");
		}
		
		return jr.toJson();
	}
	
	@RequestMapping(value={"/login"},method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String login(
			@RequestParam(value="name",required=true)String name,
			@RequestParam(value="email",required=true)String email,
			@RequestParam(value="pwd",required=true)String pwd){
		
		JsonResult jr = new JsonResult();
		
		try{
			User user = us.signUp(name, email, pwd);
			if(user != null){
				jr.setIsSuccess(Boolean.TRUE);
				jr.setMessage("成功！");
				jr.setObj(user);
			}else{
				jr.setIsSuccess(Boolean.FALSE);
				jr.setMessage("失败！");
			}
		}catch(Exception e){
			jr.setIsSuccess(Boolean.FALSE);
			jr.setMessage("异常！");
		}
		
		return jr.toJson();
	}
	
	
}
