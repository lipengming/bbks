package com.fang.bbks.modules.social.entity;

import java.io.Serializable;

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
 * @Intro '@'功能
 * #可以at人，也可以at书，还可以at任意一个话题#
 * 发布一则at的格式为："@Lee,@Luo:bbbbbbbbbbbbbb..."
 * 其中Lee和Luo是被at的对象。
 * 
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2013-11-19
 * @since 下午5:44:57	
 */

@Table(name="TB_AT")
@Entity
@SuppressWarnings("serial")
public class AT extends BaseEntity implements Serializable{
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer uid;//at发送者
	private Integer isRead;//阅读标志
	
	private Integer type;//0:人,1:书,3:其他
	
	@Size(max=ApplicationCanstant.commonTitleSize)
	private String target;//at的目标
	@Size(max=ApplicationCanstant.commonMessageSize)
	private String content;
	
	@Column(nullable = false,columnDefinition="int(2) default "+DEL_FLAG_NORMAL)
    private Integer delFlag = DEL_FLAG_NORMAL;	//删除标记（0：正常；1：删除）

	
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	/**
	 * @return the isRead
	 */
	public Integer getIsRead() {
		return isRead;
	}
	/**
	 * @param isRead the isRead to set
	 */
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
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
