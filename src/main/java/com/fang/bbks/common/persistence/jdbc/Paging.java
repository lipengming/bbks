package com.fang.bbks.common.persistence.jdbc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Intro 分页
 * @author Lee
 * @Date 2013-8-13
 */
@SuppressWarnings("serial")
public class Paging<T> implements Serializable {
	private int pageNo;//当前页
	private int nextNo;//下一页
	private int priorNo;//上一页
	private int pageCount;//总页
	private int rowCount;//记录总行数
	private int pageSize;//页大小
	private int startRow;//记录开始行
	private String orderBy;//排序
	private String orderType;//类型
	private List<String> pageList;//分页号列举
	private int pageListSize = 10;//分也号列觉多少
	private List<T> list;//结果集
	private String groupby;//分组
	
	public Paging(){
		pageSize = 10;
		pageNo = 1;
		startRow = 0;
		rowCount = 0;
	}
	
	public Paging(int pageNo,int pageSize){
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	
	public int getPageNo(){
		if(pageNo <= 0)
			return 1;
		return pageNo;
	}
	
	public void setPageNo(Integer pageNo){
		if(pageNo == null){
			pageNo = 1;
		}
		this.pageNo = pageNo;
	}
	
	public int getNextNo(){
		this.nextNo = this.pageNo + 1;
		if(this.nextNo > pageCount)
			return pageCount;
		return this.nextNo;
	}
	
	public int getPriorNo(){
		this.priorNo = this.pageNo - 1;
		if(this.priorNo < 1)
			return 1;
		return this.priorNo;
	}
	
	public int getPageCount(){
		if (rowCount % pageSize == 0 && rowCount > pageSize) {
			pageCount = rowCount / pageSize;
		} else if (rowCount % pageSize != 0 && rowCount > pageSize) {
			pageCount = rowCount / pageSize + 1;
		} else {
			pageCount = 1;
		}
		return pageCount;
	}
	
	public void setPageCount(int pageCount){
		this.pageCount = pageCount;
	}
	
	public int getRowCount(){
		return rowCount;
	}
	
	public void setRowCount(int rowCount){
		this.rowCount = rowCount;
		if(pageSize <= 0){
			pageSize = 10;
		}
		pageCount = (rowCount + pageSize -1) / pageSize;//!!!
	}
	
	public int getPageSize(){
		return pageSize;
	}
	
	public void setPageSize(int pageSize){
		if(pageSize > 100){
			pageSize = 100;
		}
		this.pageSize = pageSize;
	}
	
	public int getStartRow(){
		this.startRow = (pageNo - 1) * pageSize;
		if(this.startRow < 0){
			this.startRow = 1;
		}
		return this.startRow;
	}
	
	public void setStartRow(int startRow){
		if(startRow >= rowCount){
			startRow = rowCount - 1; 
		}
		this.startRow = startRow;
	}
	
	public List<String> getPageList(){
		if (pageCount > 0) {
			pageList = new ArrayList<String>();
			int startNo = 1;
			int endNo = 0;
			if (pageNo - pageListSize / 2 > 0) {
				startNo = pageNo - pageListSize / 2;
			}
			if (pageNo + pageListSize / 2 < pageCount) {
				endNo = pageNo + pageListSize / 2;
			} else {
				endNo = pageCount;
			}
			for (int i = startNo; i <= endNo; i++) {
				pageList.add(Integer.toString(i));
			}
			return pageList;
		} else {
			return new ArrayList<String>();
		}
	}
	
	public int getPageListSize(){
		return pageListSize;
	}
	
	public void setPageListSize(int pageListSize){
		this.pageListSize = pageListSize;
	}
	
	public List<T> getList(){
		return this.list;
	}
	
	public void setList(List<T> list){
		this.list = list;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getGroupby() {
		return groupby;
	}

	public void setGroupby(String groupby) {
		this.groupby = groupby;
	}

	@Override
	public String toString() {
		return "Paging [pageNo=" + pageNo + ", nextNo=" + nextNo + ", priorNo="
				+ priorNo + ", pageCount=" + pageCount + ", rowCount="
				+ rowCount + ", pageSize=" + pageSize + ", startRow="
				+ startRow + ", orderBy=" + orderBy + ", orderType="
				+ orderType + ", pageList=" + pageList + ", pageListSize="
				+ pageListSize + ", list=" + list + ", groupby=" + groupby
				+ "]";
	}
	
}
