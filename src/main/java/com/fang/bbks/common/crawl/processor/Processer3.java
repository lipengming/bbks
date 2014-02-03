package com.fang.bbks.common.crawl.processor;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fang.bbks.common.crawl.HttpConnnectionManager;
import com.fang.bbks.common.crawl.PropertiesUtils;
import com.fang.bbks.modules.sys.entity.Book;
import com.fang.bbks.modules.sys.service.BookService;

/**
 * @Intro 处理单本书籍的数据
 * @author Lee
 * @Date 2013-8-7
 */
public class Processer3 implements Runnable{
	
	static BookService bookService;
	
	public Processer3() {
		if(bookService == null){
			ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:/applicationContext.xml");
			bookService = (BookService) ctx.getBean("bookService"); 
		}
	}
	public static boolean isRunning = false;
	
	@Override
	public void run() {
		//待抓取的链接不空
		while (!Step3Link.unVisitedUrlsEmpty() || !isRunning) {
			System.out.println(isRunning);
			if (Step3Link.unVisitedUrlsEmpty()){
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
			//从未访问的URL队列取出第一个连接
            String visitUrl = Step3Link.unVisitedUrlDeQueue();   
            try {
            	process(visitUrl);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		
	}

	/**
	 * 处理详细页面的信息
	 * @param visitUrl
	 */
	public static void process(String visitUrl) throws Exception{
		//下载页面
		Document doc = Jsoup.parse(HttpConnnectionManager.getHtml(visitUrl));//Jsoup.connect(visitUrl).get();
		Properties p = PropertiesUtils.getProperties();
		Book book = new Book();
		
		//解析数据
		book.setAuthor(doc.select(p.getProperty("author")).get(0).text());
		book.setPress(doc.select(p.getProperty("press")).get(1).text());
		
		book.setIsbn(doc.select(p.getProperty("isbn")).text());
		book.setPrice(Double.parseDouble(doc.select(p.getProperty("price")).text().substring(1)));
		book.setBookName(doc.select(p.getProperty("bookName")).text());
		
		String pic = doc.select(p.getProperty("pic")).attr("wsrc").trim();
		book.setCoverPic(pic);
		
		String outline = doc.select(p.getProperty("outline")).text().trim();
		if(outline.startsWith("<p>")){
			outline = Jsoup.parse(outline).select("p").text();
		}
		book.setOutline(outline.length() < 2000 ? outline : outline.substring(0, 2000));
		
		//封装到实体
		//insert into db
		bookService.save(book);
	}
	
	public static void start(int threadCount){
		// 创建一个可重用固定线程数的线程池
		ExecutorService pool = Executors.newFixedThreadPool(threadCount);
		Processer3 processer3 = new Processer3();
		pool.execute(processer3);
	}
	
	public static void main(String[] args) throws Exception {
		process("http://product.dangdang.com/product.aspx?product_id=22544222");
	}

}
