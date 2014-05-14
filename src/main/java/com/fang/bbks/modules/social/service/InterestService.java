package com.fang.bbks.modules.social.service;

import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
	@Autowired
	private RelationService relationService;
	
	
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
		return findAll(uid, type,uid);
	}
	
	/**'
	 * 
	 * @param uid -- 查询用户
	 * @param type
	 * @param cuid -- 登录用户
	 * @return
	 */
	public List<InterestVo> findAll(Long uid,InterestType type,Long cuid){
		List<Interest> ins = Lists.newArrayList();
		if(type == null){
			ins.addAll(interestDao.findByUid(uid));
		}else{
			ins.addAll(interestDao.findByUidAndType(uid, type));
		}
		return parser(ins,cuid); 
	}
	
	public List<InterestVo> findSimilar(Long uid,InterestType type){
		List<Interest> ins = Lists.newArrayList();//我的兴趣
		if(type == null){
			ins.addAll(interestDao.findByUid(uid));
		}else{
			ins.addAll(interestDao.findByUidAndType(uid, type));
		}
		
		//相似兴趣
		List<Interest> similars = findSimilars(uid, getMId(ins));
		
		return parser(similars,uid);
		
	}

	public Boolean isExit(Long uid,Long mid,InterestType type){
		return interestDao.findByUidAndMidAndtype(uid, mid, type) != null;
	}
	
	public List<Interest> findSimilars(Long uid, Long[] ins){
		if(ins == null || ins.length < 1){
			return Lists.newArrayList();
		}
		DetachedCriteria dc = interestDao.createDetachedCriteria();
		dc.add(Restrictions.ne("uid", uid));
		dc.add(Restrictions.in("mid", ins));
		return interestDao.find(dc);
	}
	
	private Long[] getMId(List<Interest> ins){
		Long[] arr = new Long[ins.size()];
		for(int a=0;a<ins.size();a++){
			arr[a] = ins.get(a).getMid();
		}
		return arr;
	}
	
	private List<InterestVo> parser(List<Interest> ins,Long uid){
		
		List<InterestVo> vos = Lists.newArrayList();
		if(ins == null || ins.isEmpty()){
			return vos;
		}
		
		for(Interest in : ins){
			User u = userDao.findOne(in.getUid());
			Book b = bookDao.findOne(in.getMid());
			
			if(uid != null && u != null && u.getId() != null){
				Boolean isFlow = relationService.isFlow(uid, u.getId());
				u.setDoFlow(isFlow);
			}
			
			vos.add(new InterestVo(in,u,b));
		}
		return vos;
	}
}
