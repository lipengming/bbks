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
public class Message extends BaseEntity implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer isRead;//阅读的标记
	
	private Integer fromu;//发件人
	private Integer tou;//收件人
	
	@Size(max=ApplicationCanstant.commonMessageSize)
	private String content;
	
	private Date creatAt;
	@Column(nullable = false,columnDefinition="int(2) default "+DEL_FLAG_NORMAL)
    private Integer delFlag = DEL_FLAG_NORMAL;	//删除标记（0：正常；1：删除）

	
	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}



	public Integer getFromu() {
		return fromu;
	}

	public void setFromu(Integer fromu) {
		this.fromu = fromu;
	}

	public Integer getTou() {
		return tou;
	}

	public void setTou(Integer tou) {
		this.tou = tou;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	/**
	 * @return the delFlag
	 */
	public Integer getDelFlag() {
		return delFlag;
	}

	/**
	 * @param delFlag the delFlag to set
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
