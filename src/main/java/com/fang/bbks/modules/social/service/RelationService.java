package com.fang.bbks.modules.social.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.modules.social.dao.RelationDao;
import com.fang.bbks.modules.social.entity.RelationShip;
import com.fang.bbks.modules.sys.entity.User;
import com.fang.bbks.modules.sys.service.UserService;
import com.google.common.collect.Lists;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-8
 * @since 下午3:53:09	
 */

@Service("relationService")
@Transactional(readOnly = true)
public class RelationService {
	private static Logger logger = LoggerFactory.getLogger(RelationService.class);
	
	@Autowired
	private RelationDao relationDao;
	@Autowired
	private UserService userService;
	
	/**
	 * 关注某人
	 * @param flowId
	 * @param flowedId
	 * @return
	 */
	public RelationShip flow(Long flowId,Long flowedId){
		RelationShip rs = new RelationShip();
		rs.setCreatedAt(new Date());
		
		rs.setFlowId(flowId);
		rs.setFlowedId(flowedId);
		
		return relationDao.save(rs);
	}
	
	/**
	 * 取消关注
	 * @param flowId
	 * @param flowedId
	 */
	public void unFlow(Long flowId,Long flowedId){
		relationDao.unFlow(flowId, flowedId);
	}
	
	/**
	 * 判断是否flowid关注了flowedid
	 * 是：true
	 * 否:false
	 * @param flowId
	 * @param flowedId
	 * @return
	 */
	public Boolean isFlow(Long flowId,Long flowedId){
		return relationDao.findOne(flowId, flowedId) != null;
	}
	
	/**
	 * 查询当前用户的粉丝
	 * @param flowId
	 * @return
	 */
	public List<User> findFlowings(Long flowId){
		List<User> users = Lists.newArrayList();
		List<RelationShip> rs = relationDao.findFlowing(flowId);
		if(rs != null && !rs.isEmpty()){
			Iterator<RelationShip> iter = rs.iterator();
			while(iter.hasNext()){
				RelationShip r = iter.next();
				users.add(userService.findOne(r.getFlowId()));
			}
		}
		return users;
	}
	
	/**
	 * 查询偶像
	 * @param flowId
	 * @return
	 */
	public List<User> findFloweds(Long flowedId){
		List<User> users = Lists.newArrayList();
		List<RelationShip> rs = relationDao.findFlowed(flowedId);
		if(rs != null && !rs.isEmpty()){
			Iterator<RelationShip> iter = rs.iterator();
			while(iter.hasNext()){
				RelationShip r = iter.next();
				users.add(userService.findOne(r.getFlowedId()));
			}
		}
		return users;
	}
}
