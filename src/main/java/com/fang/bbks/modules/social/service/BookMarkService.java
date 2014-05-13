package com.fang.bbks.modules.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.bbks.modules.social.dao.BookMarkDao;
import com.fang.bbks.modules.social.entity.BookMark;
import com.fang.bbks.modules.sys.dao.BookDao;
import com.fang.bbks.modules.sys.dao.UserDao;
import com.fang.bbks.modules.sys.entity.Book;
import com.fang.bbks.modules.sys.entity.User;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-13
 * @since 下午7:03:13	
 */

@Service("bookMarkService")
@Transactional(readOnly = true)
public class BookMarkService {
	
	@Autowired
	private BookMarkDao bookMarkDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BookDao bookDao;
	
	@Transactional(readOnly=false)
	public void addBookMark(Long uid,Long bookId,String name,String link,int pageNo,int sectionNo){
		
		BookMark bm = new BookMark(uid,bookId);
		bm.setName(name);
		bm.setLink(link);
		bm.setPageNo(pageNo);
		bm.setSectionNo(sectionNo);
		
		User user = userDao.findOne(uid);
		Book book = bookDao.findOne(bookId);
		
		bm.setAvatar(user.getAvatar());
		bm.setUserName(user.getUsername());
		
		bm.setAuthor(book.getAuthor());
		bm.setBookName(book.getBookName());
		bm.setCoverPic(book.getCoverPic());
		
		bookMarkDao.save(bm);
	}
	
	
	public List<BookMark> findByUid(Long uid){
		return bookMarkDao.findByUid(uid);
	}
	

}
