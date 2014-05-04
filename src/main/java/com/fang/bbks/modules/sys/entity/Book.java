/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-7-30
 */
package com.fang.bbks.modules.sys.entity;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.NotBlank;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fang.bbks.common.persistence.BaseEntity;
import com.google.common.collect.Lists;

/**
 * 书籍基本信息表
 * @author Lee
 */
@Entity
@Table(name = "TB_BOOK")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Indexed
@Analyzer(impl = IKAnalyzer.class)
public class Book extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;//主键
	
	@NotBlank
	@Size(min=0, max=250)
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String bookName;//图书名
	
	@NotBlank
	@Size(min=0, max=100)
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String author;//作者
	
	@NotBlank
	@Size(min=0, max=16)
	private String isbn;//图书编号
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String translator;//译者
	
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String press;//出版社
	private String version;//版本
	private String directory;//目录
	
	@Size(min=0, max=2000)
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String outline;//图书概述
	
    private String delFlag;	//删除标记（0：正常；1：删除）
	
	private String coverPic;//封面图片
	
	@ManyToOne
	@JoinColumn(name="category_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Category category;//分类
	
	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY,mappedBy="book")
	@Where(clause="del_flag="+DEL_FLAG_NORMAL)
	@OrderBy(value="code")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<BookContent> contents = Lists.newArrayList();//书记内容
	
	private boolean isFree;//免费阅读：true,收费false
	private Double price;//价格
	private String allPrice;//竞价
	private Double pubPrice;//竞价
	
	private Integer wantRead;//想读人数
	private Integer likeCount;//喜欢人数
	private Integer isReading;//在读人数
	private Integer hasRead;//读过人数
	private Integer commentCount;//读过人数
	
	private String bookSrc;//电子书源文件
	private String eFlag;//电子书
	
	private String authorintro;//作者简介<215
	private String relationship;//关系列，存放：{'dd':'20','amazon':'231',}<50
	
	private Integer islock;//如果该数据被豆瓣网操作过，则被标记成枷锁状态，值为1，其他数据则不能操作
	
	
	private Date createdAt;
	private Date updateAt;
	
	/**
	 * 
	 */
	public Book() {
		this.delFlag = DEL_FLAG_NORMAL;
		this.createdAt = new Date();
		this.eFlag = EBOOK_NO;
	}
	
	public Book(Long id) {
		super();
		this.id = id;
	}
	
	public Book(Category category){
		this();
		this.category = category;
	}
	
	public Book(String bookName,String isbn,String translator,String author){
		super();
		this.bookName = bookName;
		this.isbn = isbn;
		this.translator = translator;
		this.author = author;
		this.category = new Category("不明分类","unknown");
	}
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTranslator() {
		return translator;
	}
	public void setTranslator(String translator) {
		this.translator = translator;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getOutline() {
		return outline;
	}
	public void setOutline(String outline) {
		this.outline = outline;
	}
	public boolean isFree() {
		return isFree;
	}
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getWantRead() {
		return wantRead;
	}
	public void setWantRead(Integer wantRead) {
		this.wantRead = wantRead;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public Integer getIsReading() {
		return isReading;
	}
	public void setIsReading(Integer isReading) {
		this.isReading = isReading;
	}
	public Integer getHasRead() {
		return hasRead;
	}
	public String getCoverPic() {
		return coverPic;
	}
	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}
	public void setHasRead(Integer hasRead) {
		this.hasRead = hasRead;
	}
	
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	/**
	 * @param contents the contents to set
	 */
	public void setContents(List<BookContent> contents) {
		this.contents = contents;
	}/**
	 * @return the contents
	 */
	public List<BookContent> getContents() {
		return contents;
	}	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getBookSrc() {
		return bookSrc;
	}

	public void setBookSrc(String bookSrc) {
		this.bookSrc = bookSrc;
	}

	public String geteFlag() {
		return eFlag;
	}

	public void seteFlag(String eFlag) {
		this.eFlag = eFlag;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}



	public String getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(String allPrice) {
		this.allPrice = allPrice;
	}

	public Double getPubPrice() {
		return pubPrice;
	}

	public void setPubPrice(Double pubPrice) {
		this.pubPrice = pubPrice;
	}

	public String getAuthorintro() {
		return authorintro;
	}

	public void setAuthorintro(String authorintro) {
		this.authorintro = authorintro;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public Integer getIslock() {
		return islock;
	}

	public void setIslock(Integer islock) {
		this.islock = islock;
	}	
}
