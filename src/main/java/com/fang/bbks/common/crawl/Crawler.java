package com.fang.bbks.common.crawl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.fang.bbks.common.crawl.processor.Processer1;
import com.fang.bbks.common.crawl.processor.Processer2;
import com.fang.bbks.common.crawl.processor.Processer3;
import com.fang.bbks.common.crawl.processor.Step1Link;
import com.fang.bbks.modules.sys.entity.Category;
import com.fang.bbks.modules.sys.service.CategoryService;

/**
 * @Intro 抓取主程序
 * @author Lee
 * @Date 2013-8-7
 */
//@Component("crawler")
public class Crawler {
	
	/**
	 * 处理程序：[大类：小类：分页]
	 * 分三步走：
	 * 第一级：抓取所有进入List页面的链接【进入当当图书：http://category.dangdang.com/all/?category_path=01.00.00.00.00.00】
	 * 		|
	 *		|解析分页连接，（左侧的图书分类）
	 *		|
	 * 第二级：抓取所有List页面的连接
	 *		|
	 *		|解析每一个书籍信息的list页面
	 *		| 
	 * 第三集抓取详细页面的连接
	 * 		|
	 *		|解析每一个书籍详细信息
	 *		|
	 * 	 解析书籍信息
	 * @throws InterruptedException 
	 */
	@Autowired
	CategoryService cs;
	
	public static void process() throws InterruptedException{
		Processer1.start(1);
		Processer2.start(2);
		Processer3.start(10);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		process();
	}
	public void init(){
		String starturl = PropertiesUtils.getProperties().getProperty("starturl");
		Document doc = Jsoup.parse(HttpConnnectionManager.getHtml(starturl));//Jsoup.connect(visitUrl).get();
		//解析左边的
		Elements cas = doc.select(PropertiesUtils.getProperties().getProperty("getLeftCategroy"));
        if(cas.size() > 0){
        	Category ca = null;
        	for(Element e : cas){
        		String url = e.attr("href");
        		String name = e.text();
        		Step1Link.addUnvisitedUrl(url);
        		//将此数据添加到分类中去
        		ca = new Category();
        		ca.setName(name);
        		ca.setIntro(url);
        		cs.save(ca);
        	}
        }
	}
}

