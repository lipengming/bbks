package com.fang.bbks.modules.social.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fang.bbks.common.persistence.JsonResult;
import com.fang.bbks.common.utils.CookieUtils;
import com.fang.bbks.common.web.BaseController;
import com.fang.bbks.modules.social.entity.InterestType;
import com.fang.bbks.modules.social.service.InterestService;
import com.fang.bbks.modules.sys.entity.User;
import com.fang.bbks.modules.sys.service.UserService;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-13
 * @since 下午7:36:14
 */

@Controller
@RequestMapping(BaseController.REST_PREFIX + "/interest")
public class InterestRest extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserRest.class);

	@Autowired
	private UserService userService;
	@Autowired
	private InterestService interestService;

	@RequestMapping(value = { "/addInterest" }, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String addInterest(
			@RequestParam(value = "userId", required = true) Long userId,
			@RequestParam(value = "bookId", required = true) Long bookId,
			@RequestParam(value = "typeId", required = true) Integer typeId) {
		
		JsonResult jr = new JsonResult();
		try {
			interestService.addInterest(userId, bookId, InterestType.getTypeById(typeId));
			jr.setIsSuccess(Boolean.TRUE);
			jr.setMessage("成功！");
			jr.setUser_id(userId);
		} catch (Exception e) {
			jr.setIsSuccess(Boolean.FALSE);
			jr.setMessage("异常！");
		}
		return jr.toJson(jr);
	}
	
	

}
