/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-7-30
 */
package com.fang.bbks.modules.sys.entity;

import java.io.Serializable;
import java.util.HashSet;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotBlank;

import com.fang.bbks.common.persistence.BaseEntity;

/**
 * 书籍基本信息表
 * @author Lee
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TB_BOOK")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Book extends BaseEntity implements Serializable{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;//主键
	
//	private User user;//所属用户！！！！！！
	
	@NotBlank
	@Size(min=0, max=250)
	private String bookName;//图书名
	@NotBlank
	@Size(min=0, max=100)
	private String author;//作者
	@NotBlank
	@Size(min=0, max=10)
	private String isbn;//图书编号
	private String translator;//译者
	private String press;//出版社
	private String version;//版本
	@Size(min=0, max=2000)
	private String outline;//图书概述
	
	@Column(nullable = false,columnDefinition="int(2) default "+DEL_FLAG_NORMAL)
    private Integer delFlag = DEL_FLAG_NORMAL;	//删除标记（0：正常；1：删除）
	
	private String coverPic;//封面图片
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY)
	@JoinColumn(name="category_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Category category;//分类
	
	//直接属性，并无关联
	private String catLog;//目录：格式为：[{"第1章",1},{"第2章",23},{"第3章",55}]Set<Map<String,Integer>>
	
	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY,mappedBy="book")
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<BookContent> contents = new HashSet<BookContent>();//书记内容
	
	private boolean isFree;//免费阅读：true,收费false
	private Double price;//价格
	
	private Integer wantRead;//想读人数
	private Integer likeCount;//喜欢人数
	private Integer isReading;//在读人数
	private Integer hasRead;//读过人数
	private Integer commentCount;//读过人数
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Book() {
	}
	public Book(String bookName, String author, String isbn) {
		this.bookName = bookName;
		this.author = author;
		this.isbn = isbn;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Set<BookContent> getContents() {
		return contents;
	}
	public void setContents(Set<BookContent> contents) {
		this.contents = contents;
	}
	public String getCatLog() {
		return catLog;
	}
	public void setCatLog(String catLog) {
		this.catLog = catLog;
	}
	
//	public List<Map<String,Integer>> catLogMapping(String catlog){
//		//[{c,'csdvs'},{b,'casca'},{a,'casca'}]
//		List<Map<String,Integer>> res = new ArrayList<Map<String,Integer>>();
//		JSONArray ja = 
//		return res;
//	}
	
}
