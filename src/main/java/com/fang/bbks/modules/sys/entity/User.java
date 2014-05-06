package com.fang.bbks.modules.sys.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Email;

import com.fang.bbks.common.persistence.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

/**
 * 用户基本表
 * @author Lee
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TB_USER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends BaseEntity implements Serializable{
	 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;//主键id
    
    private String username;//登录名称
    private String password;//登录密码
    
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createDate;//创建日期
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date updateDate;//创建日期
    
	private String delFlag;	// 删除标记（0：正常；1：删除）
	
	@Email
	private String email;//邮箱
	
	@Size(max=2)
	private String isCompany = BaseEntity.NO;//默认为普通用户,No表示公司用户

	private String avatar;//头像
	private String description;//心情说明
	
	
	private Integer reading ;//在读书籍//1-n
	private Integer liking ;//喜欢读书籍//1-n
	private Integer wantRead ;//在读书籍//1-n
	private Integer hasRead ;//已读书籍//1-n
	
	private Integer flowings;//关注数
	private Integer floweds;//粉丝数
	private Integer messages;//粉丝数
	
	
	//多对多定义
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	@Where(clause="del_flag="+DEL_FLAG_NORMAL)
	//@OrderBy("id")
	@Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JsonIgnore
	private List<Roles> roleList = Lists.newArrayList(); // 拥有角色列表
    
	
	
	public User(){
		this.createDate = new Date();
		this.delFlag = DEL_FLAG_NORMAL;
	}
	public User(Long id){
		super();
		this.id = id;
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
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsCompany() {
		return isCompany;
	}

	public void setIsCompany(String isCompany) {
		this.isCompany = isCompany;
	}
	
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public List<Roles> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Roles> roleList) {
		this.roleList = roleList;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public Integer getMessages() {
		return messages;
	}
	public void setMessages(Integer messages) {
		this.messages = messages;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public Integer getReading() {
		return reading;
	}
	public void setReading(Integer reading) {
		this.reading = reading;
	}
	public Integer getLiking() {
		return liking;
	}
	public void setLiking(Integer liking) {
		this.liking = liking;
	}
	public Integer getWantRead() {
		return wantRead;
	}
	public void setWantRead(Integer wantRead) {
		this.wantRead = wantRead;
	}
	public Integer getHasRead() {
		return hasRead;
	}
	public void setHasRead(Integer hasRead) {
		this.hasRead = hasRead;
	}
	public Integer getFlowings() {
		return flowings;
	}
	public void setFlowings(Integer flowings) {
		this.flowings = flowings;
	}
	public Integer getFloweds() {
		return floweds;
	}
	public void setFloweds(Integer floweds) {
		this.floweds = floweds;
	}
	@Transient
	@JsonIgnore
	public List<Long> getRoleIdList() {
		List<Long> roleIdList = Lists.newArrayList();
		for (Roles role : roleList) {
			roleIdList.add(role.getId());
		}
		return roleIdList;
	}

	@Transient
	public void setRoleIdList(List<Long> roleIdList) {
		roleList = Lists.newArrayList();
		for (Long roleId : roleIdList) {
			Roles role = new Roles();
			role.setId(roleId);
			roleList.add(role);
		}
	}
	

}
