package com.fang.bbks.modules.social.entity;

import java.io.Serializable;

import com.fang.bbks.modules.sys.entity.Book;
import com.fang.bbks.modules.sys.entity.User;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-13
 * @since 下午9:55:41	
 */
@SuppressWarnings("serial")
public class InterestVo implements Serializable{
	
	private Interest interest;
	private User user;
	private Book book;
	private String type;
	
	public InterestVo(){}

	public InterestVo(Interest interest, User user, Book book) {
		super();
		this.interest = interest;
		this.user = user;
		this.book = book;
		this.type = interest.getType().getType();
	}

	public Interest getInterest() {
		return interest;
	}

	public void setInterest(Interest interest) {
		this.interest = interest;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
