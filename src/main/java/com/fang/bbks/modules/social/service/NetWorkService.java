package com.fang.bbks.modules.social.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.fang.bbks.modules.sys.service.UserService;
import com.google.common.collect.Maps;

/**
 * @Intro 社交部分服务
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-8
 * @since 下午4:23:06	
 */
@Service("netWorkService")
@Transactional(readOnly = true)
public class NetWorkService {
	
	@Autowired
	private RelationService relationService;
	@Autowired
	private UserService userService;
	@Autowired
	private DynamicService dynamicService;
	@Autowired
	private MessageService messageService;

	@Transactional(readOnly=false)
	public void flow(Long flowId,Long flowedId){
		//加入关系
		relationService.flow(flowId, flowedId);
		//update
		userService.flow(flowId, flowedId);
	}
	
	@Transactional(readOnly=false)
	public void unflow(Long flowId,Long flowedId){
		relationService.unFlow(flowId, flowedId);
		userService.unflow(flowId, flowedId);
	}
	
}
