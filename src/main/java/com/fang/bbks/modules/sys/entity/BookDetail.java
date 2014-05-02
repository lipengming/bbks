package com.fang.bbks.modules.sys.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

/**
 * @Intro descrption here
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2014-5-2
 * @since 下午10:41:53	
 */
@MappedSuperclass
@SuppressWarnings("serial")
public abstract class BookDetail implements Serializable{
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;//主键
	
	private String isbn;//图书编号<15
	private String bookname;//图书名<45
	private String author;//作者<45
	private String translator;//译者<45
	private String press;//出版社<45
	private String version;//版本<4
	private String coverPic;//封面图片<45
	private Double price;//单价
	private Double pubPrice;//单价
	
	@Size(min=0, max=2000)
	private String outLine;//概述<2000
	private String directory;//目录<1000【值得是：图书-》小说-》文学】
	private String catelog;//分类<50
	@Size(min=0, max=2000)
	private String authorintro;//作者简介<215
	
	//实时数据的加入
	private String url;//来自的连接

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public String getCoverPic() {
		return coverPic;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPubPrice() {
		return pubPrice;
	}

	public void setPubPrice(Double pubPrice) {
		this.pubPrice = pubPrice;
	}

	public String getOutLine() {
		return outLine;
	}

	public void setOutLine(String outLine) {
		this.outLine = outLine;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getCatelog() {
		return catelog;
	}

	public void setCatelog(String catelog) {
		this.catelog = catelog;
	}


	public String getAuthorintro() {
		return authorintro;
	}

	public void setAuthorintro(String authorintro) {
		this.authorintro = authorintro;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
