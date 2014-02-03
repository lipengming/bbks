package com.fang.bbks.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2013-12-3
 * @since 下午1:58:53	
 */

@Controller
public class ApiTest {
	
	@RequestMapping(value="/api",method=RequestMethod.GET)
	public String api(HttpServletRequest req,HttpServletResponse res){
		return "api";
	}
}
