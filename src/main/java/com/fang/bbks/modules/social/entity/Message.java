package com.fang.bbks.modules.social.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fang.bbks.common.constant.ApplicationCanstant;
import com.fang.bbks.common.persistence.BaseEntity;

/**
 * @Intro 用户间私信[user]
 * @author Lee
 * @Date 2013-8-4
 */

@SuppressWarnings("serial")
@Table(name = "TB_MESSAGE")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Message extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Integer isRead;//阅读的标记
	
	private Long fromu;//发件人
	private Long tou;//收件人
	
	@Size(max=ApplicationCanstant.commonMessageSize)
	private String content;
	
	private Date creatAt;
	private Date updateAt;
    private String delFlag = DEL_FLAG_NORMAL;	//删除标记（0：正常；1：删除）

    
    public Message() {
		this.delFlag = DEL_FLAG_NORMAL;
		this.creatAt = new Date();
		this.isRead = READ_HIDE;
	}
	
	public Message(Long id){
		super();
		this.id = id;
	}
	
    public Message(Long from,Long to,String content) {
    	super();
    	this.fromu = from;
    	this.tou = to;
    	this.content = content;
    }

	
	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}


	public Long getFromu() {
		return fromu;
	}

	public void setFromu(Long fromu) {
		this.fromu = fromu;
	}

	public Long getTou() {
		return tou;
	}

	public void setTou(Long tou) {
		this.tou = tou;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the creatAt
	 */
	public Date getCreatAt() {
		return creatAt;
	}

	/**
	 * @param creatAt the creatAt to set
	 */
	public void setCreatAt(Date creatAt) {
		this.creatAt = creatAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
