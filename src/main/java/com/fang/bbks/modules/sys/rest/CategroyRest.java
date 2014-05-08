package com.fang.bbks.modules.sys.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fang.bbks.common.persistence.Code;
import com.fang.bbks.common.persistence.JsonResult;
import com.fang.bbks.common.web.BaseController;
import com.fang.bbks.modules.sys.entity.Category;
import com.fang.bbks.modules.sys.service.CategoryService;
import com.google.common.collect.Lists;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-4
 * @since 下午1:54:08	
 */
@Controller
@RequestMapping(BaseController.REST_PREFIX+"/category")
public class CategroyRest extends BaseController{
	private final static Logger logger = LoggerFactory.getLogger(CategroyRest.class);
	
	private static List<Category> mapper = Lists.newArrayList();
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value={"/list"},produces="text/plain;charset=UTF-8",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String find(){
		JsonResult jr = new JsonResult();
		try{
			if(mapper.isEmpty()){
				mapper.addAll(categoryService.getCaList());
			}
			jr.setIsSuccess(true);
			jr.setRows(mapper);
			jr.setMessage(Code.SUCCESS.name());
		}catch(Exception e){
			jr.setIsSuccess(false);
			jr.setRows(mapper);
			jr.setMessage(Code.EXCEPTION.name());
			logger.error("异常{}", e.getMessage(),e);
		}
		return jr.toJson(jr);
	}

}
