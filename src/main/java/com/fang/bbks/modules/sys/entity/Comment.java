package com.fang.bbks.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.NotBlank;

import com.fang.bbks.common.constant.ApplicationCanstant;
import com.fang.bbks.common.persistence.BaseEntity;
import com.fang.bbks.modules.sys.entity.User;

/**
 * @Intro 书评[book]
 * @author Lee
 * @Date 2013-8-4
 */

@Table(name = "TB_COMMENT")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Comment extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private Long id;		// 编号
	private String module; 	// 内容模型（article：图书；user:用户；picture：图片；download：下载）
	private Long contentId;	// 归属分类内容的编号（Book.id、User.id、Photo.id、Download.id）
	private String title;	// 归属分类内容的标题（Book.title、Photo.title、Download.title）
	private String content; // 评论内容
	private String name; 	// 评论姓名
	private String avatar;	// 评论头像
	private String ip; 		// 评论IP
	private Date createDate;// 评论时间
	private User auditUser; // 审核人
	private Date auditDate;	// 审核时间
	private String status;	// 删除标记（0：发布；1：作废；2：审核；）

	public Comment() {
		this.createDate = new Date();
		this.status = STATUS_RELEASE;
	}
	
	public Comment(Long id){
		this();
		this.id = id;
	}

	public Comment(String module, Long contentId, String title, String content,
			String name) {
		super();
		this.module = module;
		this.contentId = contentId;
		this.title = title;
		this.content = content;
		this.name = name;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	@NotBlank
	@Size(min=0, max=255)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@NotBlank
	@Size(min=0, max=255)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	@Size(min=0, max=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name="audit_user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public User getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(User auditUser) {
		this.auditUser = auditUser;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Size(min=0, max=1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
