package com.fang.bbks.modules.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.modules.social.dao.InterestDao;
import com.fang.bbks.modules.social.entity.Interest;
import com.fang.bbks.modules.social.entity.InterestType;
import com.fang.bbks.modules.social.entity.InterestVo;
import com.fang.bbks.modules.sys.dao.BookDao;
import com.fang.bbks.modules.sys.dao.UserDao;
import com.fang.bbks.modules.sys.entity.Book;
import com.fang.bbks.modules.sys.entity.User;
import com.google.common.collect.Lists;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-13
 * @since 下午7:15:42	
 */
@Service("interestService")
@Transactional(readOnly = true)
public class InterestService {
	
	@Autowired
	private InterestDao interestDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BookDao bookDao;
	
	
	@Transactional(readOnly=false)
	public void addInterest(Long uid,Long mid,InterestType type){
		Interest interest = new Interest(uid,mid);
		interest.setType(type);
		
		User user = userDao.findOne(uid);
		Book book = bookDao.findOne(mid);
		
		interest.setAvatar(user.getAvatar());
		interest.setUserName(user.getUsername());
		
		interest.setAuthor(book.getAuthor());
		interest.setBookName(book.getBookName());
		interest.setCoverPic(book.getCoverPic());
		
		interestDao.save(interest);
	}
	
	public List<Interest> find(Long uid,InterestType type){
		if(type == null){
			return interestDao.findByUid(uid);
		}
		return interestDao.findByUidAndType(uid, type);
	}
	
	public List<InterestVo> findAll(Long uid,InterestType type){
		List<Interest> ins = Lists.newArrayList();
		if(type == null){
			ins.addAll(interestDao.findByUid(uid));
		}else{
			ins.addAll(interestDao.findByUidAndType(uid, type));
		}
		
		System.out.println("get ins-->"+ins.size());

		List<InterestVo> vos = Lists.newArrayList();
		for(Interest in : ins){
			User u = userDao.findOne(in.getUid());
			Book b = bookDao.findOne(in.getMid());
			vos.add(new InterestVo(in,u,b));
		}
		System.out.println("get vos-->"+vos.size());
		return vos;
	}

	public Boolean isExit(Long uid,Long mid,InterestType type){
		return interestDao.findByUidAndMidAndtype(uid, mid, type) != null;
	}
}
