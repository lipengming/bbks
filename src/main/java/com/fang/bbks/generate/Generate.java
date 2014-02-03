/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-7-30
 */
package com.fang.bbks.generate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;

import com.fang.bbks.common.utils.DateUtils;
import com.fang.bbks.common.utils.FileUtils;
import com.fang.bbks.common.utils.FreeMarkers;
import com.google.common.collect.Maps;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 代码生成器
 * @author Lee
 */
public class Generate {
	
	private static Logger logger = LoggerFactory.getLogger(Generate.class);

	public static void main(String[] args) throws Exception {

		// ========== ↓↓↓↓↓↓ 执行前请修改参数，谨慎执行，以免覆盖原有模块 ====================
		// 主要提供基本功能模块代码生成。
		// 目录生成结构：{packageName}/{moduleName}/{dao,entity,service,web}/{subModuleName}/{className}
		
		String packageName = "com.fang.bbks";	// 包名，例：com.thinkgem.jeesite.modules
		String moduleName = "social";			// 模块名，例：sys
		String ClassName = "Relation";			// 类名，例：user
		String className = "relation";			// 类名，例：user
		String classAuthor = "Lee";		// 类作者，例：ThinkGem
		String functionName = "产品";			// 功能名，例：用户

		// 是否启用生成工具
		Boolean isEnable = true;			
		
		// ========== ↑↑↑↑↑↑ 执行前请修改参数，谨慎执行，以免覆盖原有模块  ====================
		
		if (!isEnable){
			logger.error("请启用代码生成工具，设置参数：isEnable = true");
			return;
		}
		
		if (StringUtils.isBlank(moduleName) || 
			StringUtils.isBlank(className)  || 
			StringUtils.isBlank(functionName) ){
			
			logger.error("参数设置错误：包名、模块名、类名、功能名不能为空。");
			
			return;
		}
		
		// 获取文件路径
		String separator = File.separator;
		String classPath = new DefaultResourceLoader().getResource("").getFile().getPath();
		
		String web_root = separator+"webapp"+separator+"WEB-INF"+separator+"classes";
		String java_root = separator+"java"+separator+(StringUtils.lowerCase(packageName)).replace(".", separator);
		
		String templatePath = classPath.replace(web_root,separator+"java"
															+separator+"com"
															+separator+"fang"
															+separator+"bbks"
															+separator+"generate"
															+separator+"template");
		
		String javaPath = classPath.replace(web_root,java_root);
		
		// 代码模板配置
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(templatePath));

		// 定义模板变量
		Map<String, String> model = Maps.newHashMap();

		model.put("ClassName", StringUtils.capitalize(ClassName));
		model.put("className", StringUtils.uncapitalize(className));
		model.put("moduleName", StringUtils.lowerCase(moduleName));

		
		// 生成 Dao
		Template template = cfg.getTemplate("dao.ftl");
		String content = FreeMarkers.renderTemplate(template, model);
		String filePath = javaPath+separator+"modules"+separator+model.get("moduleName")+separator+"dao"+separator+model.get("ClassName")+"Dao.java";
		//writeFile(content, filePath);
		logger.info(filePath);
		
		// 生成 Service
		template = cfg.getTemplate("service.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = javaPath+separator+"modules"+separator+model.get("moduleName")+separator+"service"+separator+model.get("ClassName")+"Service.java";
		writeFile(content, filePath);
		logger.info(filePath);
		
		logger.info("代码生成成功！");
	}
	
	/**
	 * 将内容写入文件
	 * @param content
	 * @param filePath
	 */
	public static void writeFile(String content, String filePath) {
		try {
			if (FileUtils.createFile(filePath)){
				FileWriter fileWriter = new FileWriter(filePath, true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(content);
				bufferedWriter.close();
				fileWriter.close();
			}else{
				logger.info("生成失败，文件已存在！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
