package com.fang.bbks.modules.social.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fang.bbks.common.persistence.BaseDao;
import com.fang.bbks.common.persistence.BaseDaoImpl;
import com.fang.bbks.modules.social.entity.Message;
import com.fang.bbks.modules.sys.entity.User;

/**
 * @Intro 消息处理类【人-人】
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2013-11-22
 * @since 下午6:20:55	
 */
public interface MessageDao extends MessageDaoCustm , CrudRepository<Message, Long>{
	@Modifying
	@Query("update Message set delFlag=" + Message.DEL_FLAG_DELETE + " where id = ?1")
	public int deleteById(Long id);
	
	@Query("from Message where fromu = ?1")
	public List<Message> findByFrom(Long from);
	
	@Query("from Message where tou = ?1")
	public List<Message> findByTo(Long to);
	
	@Query("from Message where to = ?1 and isRead = " + Message.READ_HIDE)
	public List<Message> findUnRead(Long to);
}


interface MessageDaoCustm extends BaseDao<Message>{
}

@Repository("messageDao")
class MessageDaoImpl extends BaseDaoImpl<Message> implements MessageDaoCustm{
	
}