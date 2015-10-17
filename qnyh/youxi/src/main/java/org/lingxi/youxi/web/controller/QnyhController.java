/**
 * 
 */
package org.lingxi.youxi.web.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lingxi.base.common.AjaxResultBean;
import org.lingxi.youxi.collect.parser.TextParser;
import org.lingxi.youxi.web.service.CollectService;
import org.lingxi.youxi.web.util.AjaxJsonUtil;
import org.lingxi.youxi.web.util.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Administrator
 * 2015年9月20日
 */
@Controller  
@RequestMapping("qnyh/collect")
public class QnyhController {

	@Resource
	private CollectService collectService;
	@Resource
	private TextParser textParser;
	@RequestMapping("validData")
    public String collect(@RequestParam("uname") String uname, @RequestParam MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException {  
		
		//可以在上传文件的同时接收其它参数
        String realPath = request.getSession().getServletContext().getRealPath("/upload");
       
        String msg = FileUploadUtil.upload(realPath, myfiles);
       
        if(msg==null){
        	List<String> fetchContent = textParser.fetchContent(FileUploadUtil.readString(myfiles).get(0));
        	AjaxJsonUtil.write(new AjaxResultBean(textParser.parseOutline(fetchContent)), response);
        }else{
        	AjaxJsonUtil.write(new AjaxResultBean(msg), response);
        }
        collectService.readFightingSummary();
        return null;  
    }  
}
