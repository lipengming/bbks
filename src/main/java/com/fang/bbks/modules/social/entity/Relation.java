package com.fang.bbks.modules.social.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fang.bbks.common.persistence.BaseEntity;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2013-11-19
 * @since 下午5:21:59	
 */
@Entity
@Table(name = "TB_RELATION")
@SuppressWarnings("serial")
public class Relation extends BaseEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer following;//发起关注
	private Integer followed;//被关注
	
	@Column(nullable = false,columnDefinition="int(2) default "+DEL_FLAG_NORMAL)
    private Integer delFlag = DEL_FLAG_NORMAL;	//删除标记（0：正常；1：删除）

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFollowing() {
		return following;
	}
	public void setFollowing(Integer following) {
		this.following = following;
	}
	public Integer getFollowed() {
		return followed;
	}
	public void setFollowed(Integer followed) {
		this.followed = followed;
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
