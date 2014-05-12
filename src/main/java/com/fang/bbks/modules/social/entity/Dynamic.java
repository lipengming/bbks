package com.fang.bbks.modules.social.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.fang.bbks.common.constant.ApplicationCanstant;
import com.fang.bbks.common.persistence.BaseEntity;
import com.fang.bbks.modules.sys.entity.Resource;

/**
 * @Intro 用户动态、消息、心情[user]
 * 
 * TODO: ！！！转发、收藏、评论功能暂时不开放！！！！
 * 
 * @author Lee
 * @Date 2013-8-1
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TB_DYNAMIC")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dynamic extends BaseEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long creatBy;
	
	private Date createAt;
	private Date updateAt;
	
	@Size(max=ApplicationCanstant.commonMessageSize)
	private String content;
	
    private String delFlag;	//删除标记（0：正常；1：删除）
    private Integer count;//回复数量
	
    private String createdname;
    private String createdAvatar;
    
	/**
	 * 
	 */
	public Dynamic() {
		this.delFlag = DEL_FLAG_NORMAL;
		this.count = 0;
		this.createAt = new Date();
	}
	public Dynamic(Long id){
		super();
		this.id = id;
	}
	
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	public Long getCreatBy() {
		return creatBy;
	}
	public void setCreatBy(Long creatBy) {
		this.creatBy = creatBy;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public String getCreatedname() {
		return createdname;
	}
	public void setCreatedname(String createdname) {
		this.createdname = createdname;
	}
	public String getCreatedAvatar() {
		return createdAvatar;
	}
	public void setCreatedAvatar(String createdAvatar) {
		this.createdAvatar = createdAvatar;
	}	
}
