package com.fang.bbks.modules.sys.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.fang.bbks.common.persistence.BaseEntity;

/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-8-6
 */
@Entity
@Table(name="TB_BOOKCONTENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("serial")
public class BookContent extends BaseEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer pageNum;
	private String content;//文字内容
	@Column(nullable = false,columnDefinition="int(2) default "+DEL_FLAG_NORMAL)
    private Integer delFlag = DEL_FLAG_NORMAL;	//删除标记（0：正常；1：删除）
	
	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY)
	@JoinColumn(name="pics")
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Resource> pics = new HashSet<Resource>();//图片文件资源
	

	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY)
	@JoinColumn(name="bookid")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Book book;//所属书籍
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Set<Resource> getPics() {
		return pics;
	}
	public void setPics(Set<Resource> pics) {
		this.pics = pics;
	}
	public BookContent(Integer pageNum, String content, Integer delFlag) {
		this.pageNum = pageNum;
		this.content = content;
		this.delFlag = delFlag;
	}
	public BookContent(){}
	
}
