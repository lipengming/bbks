package com.fang.bbks.modules.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.modules.social.dao.DonateDao;
import com.fang.bbks.modules.social.entity.Donate;
import com.fang.bbks.modules.social.entity.DonateType;
import com.fang.bbks.modules.sys.dao.BookDao;
import com.fang.bbks.modules.sys.entity.Book;
import com.google.common.collect.Lists;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-14
 * @since 下午1:47:14	
 */
@Service("donateService")
@Transactional(readOnly = true)
public class DonateService {
	
	@Autowired
	private DonateDao donateDao;
	@Autowired
	private BookDao bookDao; 
	
	@Transactional(readOnly=false)
	public void DonateBook(Long mid,String link,DonateType type,String desc,Long uid){
		Donate donate = new Donate();
		
		donate.setMid(mid);
		donate.setLink(link);
		donate.setType(type);
		donate.setDescription(desc);
		donate.setUid(uid);
		
		donateDao.save(donate);
	}
	
	public List<Donate> findAll(Long uid){
		List<Donate> dos = donateDao.findByUid(uid);
		if(dos == null || dos.isEmpty()){
			return Lists.newArrayList();
		}
		
		List<Donate> rs = Lists.newArrayList();
		for(Donate d : dos){
			Book bk = bookDao.findOne(d.getMid());
			d.setBook(bk);
			rs.add(d);
		}
		
		return rs;
	}
}
