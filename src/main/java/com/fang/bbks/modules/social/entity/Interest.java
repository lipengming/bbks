package com.fang.bbks.modules.social.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fang.bbks.common.persistence.BaseEntity;

/**
 * @Intro 兴趣
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-13
 * @since 下午6:29:02	
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TB_INTEREST")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Interest extends BaseEntity implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long uid;//用户id
	
	private Long mid;//目标对象的id
	
	private InterestType type;
	
	private Date createdAt;
	private String delFlag;
	
	
	private String bookName;
	private String coverPic;
	private String author;
	
	private String userName;
	private String avatar;
	
	
	public Interest() {
		this.createdAt = new Date();
		this.delFlag = "0";
	}
	
	public Interest(Long id) {
		super();
		this.id = id;
	}
	
	public Interest(Long uid,Long mid) {
		super();
		this.uid = uid;
		this.mid = mid;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public InterestType getType() {
		return type;
	}

	public void setType(InterestType type) {
		this.type = type;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getCoverPic() {
		return coverPic;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
}
