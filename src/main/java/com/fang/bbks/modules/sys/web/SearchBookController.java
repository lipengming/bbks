package com.fang.bbks.modules.sys.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fang.bbks.common.constant.ApplicationCanstant;
import com.fang.bbks.common.persistence.Page;
import com.fang.bbks.common.utils.StringUtils;
import com.fang.bbks.common.web.BaseController;
import com.fang.bbks.modules.sys.dao.CategoryDao;
import com.fang.bbks.modules.sys.entity.Book;
import com.fang.bbks.modules.sys.entity.Category;
import com.fang.bbks.modules.sys.service.BookService;

/**
 * @Intro 书籍搜索controoler
 * @author Lee
 * @Date 2013-8-9
 */
@Controller
@RequestMapping("/book")
public class SearchBookController extends BaseController{
	
	@Autowired
	BookService bs;
	@Autowired
	CategoryDao categoryDao;
	
	@RequestMapping(value={"/search/list"}, method={RequestMethod.GET,RequestMethod.POST})
	public String searchIndex(Model model,HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="categroy_id",required=false) String catlog,
			@RequestParam(value="kw",required=false) String kw) {
		
		Page<Book> page = new Page<Book>(request, response);
		
		if (StringUtils.isNotBlank(kw)){
			
			if ("cmd:reindex".equals(kw)){
				
				System.out.println("重建索引！--start");
				bs.createdAndUpdateIndex();
				System.out.println("重建索引！--end");
				
				model.addAttribute("message", "重建索引成功");
				
			}else{
				
				page = bs.findByKeyWords(page, kw);
				
			}
			
		}else{
			
			page = doSearch(request,response,catlog);
			
		}
		
		if(page != null && !page.getList().isEmpty()){
			model.addAttribute("booksInfo", page.getList());
		}
		
		return "book/list";
	}
	
	@RequestMapping(value={"/search/{bookId}"},method=RequestMethod.GET)
	public String search(@PathVariable("bookId") Long bookId,
			Model uiModel,HttpServletRequest request){

		Book book = bs.findOne(bookId);
		if(book ==null)
			return "redirect:/index";
		
		uiModel.addAttribute("bookInfo",book);
		return "/book/detail";
	}
	
	
	/**
	 * 数据分页
	 * @param request
	 * @return
	 */
	private Page<Book> doSearch(HttpServletRequest request,HttpServletResponse response,String catlog){
		Page<Book> page = new Page<Book>(request, response);
		Book book = new Book();
		
		String bookName = request.getParameter("name");
		String author = request.getParameter("author");
		String translator = request.getParameter("translator");
		
		book.setAuthor(author);
		book.setBookName(bookName);
		book.setTranslator(translator);
		
		System.out.println("search by catlog..."+catlog);

		
		if(StringUtils.isNotBlank(catlog) && StringUtils.isNumeric(catlog)){
			
			Long catlog_id = Long.parseLong(catlog);
			if(catlog_id != null && catlog_id > 0){
				Category ca = categoryDao.findOne(catlog_id);
				if(ca != null && ca.getId() == catlog_id){
					book.setCategory(ca);
				}
			}
		}
		
		page = bs.findBook(page, book);
				
		if(page.getCount() <= page.getFirstResult()){
			return null;
		}
		
		return page;
	}
}
