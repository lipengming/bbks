package com.fang.bbks.modules.sys.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fang.bbks.common.persistence.BaseEntity;

/**
 * @Intro 资源文件，可以是图片（png,jpg），文档（docx,xlxs,pdf）
 * @author Lee
 * @Date 2013-8-1
 */
@SuppressWarnings("serial")
@Entity
@Table(name="TB_RESOURCE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Resource extends BaseEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(max=4)
	private String type;//资源类型（0：文档；1：图片；2：其他；）
	private String resSrc;//资源的原路径
	private String resId;//资源的使用路径
	private Integer[] orgSize;//原始大小
    private String delFlag;	//删除标记（0：正常；1：删除）
	
    
    /**
	 * @param delFlag the delFlag to set
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * @return the delFlag
	 */
	public String getDelFlag() {
		return delFlag;
	}
    
    /**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getResSrc() {
		return resSrc;
	}
	public void setResSrc(String resSrc) {
		this.resSrc = resSrc;
	}
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public Integer[] getOrgSize() {
		return orgSize;
	}
	public void setOrgSize(Integer[] orgSize) {
		this.orgSize = orgSize;
	}
	public Resource(String type, String resSrc, String resId) {
		super();
		this.type = type;
		this.resSrc = resSrc;
		this.resId = resId;
	}
	public Resource(){
		this.delFlag = DEL_FLAG_NORMAL;
	}	
	
}
