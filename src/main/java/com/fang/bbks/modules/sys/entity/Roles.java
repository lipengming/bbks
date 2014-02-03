/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-7-30
 */
package com.fang.bbks.modules.sys.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.fang.bbks.common.persistence.BaseEntity;
import com.google.common.collect.Lists;

/**
 * @author Lee
 */

@Entity
@Table(name = "TB_ROLE")
public class Roles extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 3446263144818124242L;
	//主键id
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String value;//url
    private String permission;//shiro permission;
    @Column(nullable = false,columnDefinition="int(2) default "+DEL_FLAG_NORMAL)
    private Integer delFlag = DEL_FLAG_NORMAL;	//删除标记（0：正常；1：删除）
    
    public Integer getDelFlag() {
		return delFlag;
	}


	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_user_role", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@OrderBy("id")
	@Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<User> userList = Lists.newArrayList(); // 拥有用户列表

    
	public Roles() {}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}
