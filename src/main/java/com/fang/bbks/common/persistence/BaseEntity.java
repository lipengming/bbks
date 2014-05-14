package com.fang.bbks.common.persistence;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * Entity支持类
 * @author Lee
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// 删除标记（0：正常；1：删除）
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";

	// 显示/隐藏
	public static final String SHOW = "1";
	public static final String HIDE = "0";
	
	// 是/否
	public static final String YES = "1";
	public static final String NO = "0";

	// 状态状态（0：发布；1：作废；2：审核；）
	public static final String STATUS_RELEASE = "0";
	public static final String STATUS_DELETE = "1";
	public static final String STATUS_AUDIT = "2";
	
	// 状态状态（0：文档；1：图片；2：其他；）
	public static final String SOURCE_TYPE_DOC = "0";
	public static final String SOURCE_TYPE_PIC = "1";
	public static final String SOURCE_TYPE_OTHERS = "2";
	
	// 状态状态（0：未读；1：读了）
	public static final int READ_HIDE = 0;
	public static final int READ_SHOW = 1;
	

	public static final String EBOOK_YEW = "1";
	public static final String EBOOK_NO = "0";
}
