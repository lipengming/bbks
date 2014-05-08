package com.fang.bbks.modules.social.entity;

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
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-2
 * @since 下午1:39:42	
 */

@Entity
@Table(name = "TB_RELATIONSHIP")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RelationShip extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String delFlag;
	private Long flowId;//关注发起方
	private Long flowedId;//被关注方
	private Date createdAt;
	private Date updatedAt;
	
	public RelationShip() {
		this.delFlag = DEL_FLAG_NORMAL;
		this.createdAt = new Date();
	}
	
	public RelationShip(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getFlowId() {
		return flowId;
	}

	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}

	public Long getFlowedId() {
		return flowedId;
	}

	public void setFlowedId(Long flowedId) {
		this.flowedId = flowedId;
	}
}
