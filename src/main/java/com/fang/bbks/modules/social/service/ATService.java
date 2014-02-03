package com.fang.bbks.modules.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.common.persistence.Page;
import com.fang.bbks.modules.social.dao.ATDao;
import com.fang.bbks.modules.social.entity.AT;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2013-11-25
 * @since 下午5:46:27
 */

public interface ATService {

	/**
	 * "@一人，把type设置为1"
	 * 
	 * @param at
	 * @return
	 */
	public boolean atSomeOne(String targetUser, String content,Integer uid);

	/**
	 * "@一本书，把type设置为2"
	 * 
	 * @param at
	 * @return
	 */
	public boolean atSomeBook(String targetBook, String content,Integer uid);

	/**
	 * "@其他，把type设置为3"
	 * 
	 * @return
	 */
	public boolean atSomeTh(String targetName, String content,Integer uid);

	/**
	 * 根据类型和target获取at列表
	 * 
	 * @param target
	 * @param type
	 * @return
	 */
	public List<AT> getAts(String target, Integer type);

	/**
	 * 根据类型和target获取at分页
	 * 
	 * @param target
	 * @param type
	 * @return
	 */
	public Page<AT> getAt(Page<AT> page, String target, Integer type);

	/**
	 * 根据target获取at列表
	 * 
	 * @param target
	 * @param type
	 * @return
	 */
	public List<AT> getAts(String target);

}

@Service("atService")
@Transactional(readOnly = true)
class ATServiceImpl implements ATService {

	@Autowired
	ATDao atDao;

	@Override
	public boolean atSomeOne(String targetUser, String content,Integer uid) {
		AT at = new AT();

		at.setContent(content);
		at.setIsRead(AT.READ_HIDE);
		at.setTarget(targetUser);
		at.setType(AT.AT_USER);

		at.setUid(uid);
		atDao.save(at);

		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public boolean atSomeBook(String targetBook, String content,Integer uid) {
		AT at = new AT();
		
		at.setUid(uid);
		at.setContent(content);
		at.setIsRead(AT.READ_HIDE);// 未读
		at.setTarget(targetBook);
		at.setType(AT.AT_BOOK);

		atDao.save(at);

		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public boolean atSomeTh(String targetName, String content,Integer uid) {
		AT at = new AT();

		at.setUid(uid);
		at.setContent(content);
		at.setIsRead(AT.READ_HIDE);
		at.setTarget(targetName);
		at.setType(AT.AT_TH);

		atDao.save(at);

		return true;
	}

	@Override
	public List<AT> getAts(String target, Integer type) {
		return atDao.findByTypeAndTarget(type, target);
	}

	@Override
	public Page<AT> getAt(Page<AT> page, String target, Integer type) {
		String sql = "from AT where target=? and type=?";
		Page<AT> ats = atDao.find(page, sql, target, type);
		return ats;
	}

	@Override
	public List<AT> getAts(String target) {
		return atDao.findByTarget(target);
	}
}