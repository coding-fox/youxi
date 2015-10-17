/**
 * 
 */
package org.lingxi.youxi.web.controller;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 页面请求控制
 * @author Administrator
 * 2015年9月20日
 */
@Controller  
@RequestMapping("qnyh")  
public class PapeController {

	@RequestMapping("")  
    public String qnyhCollect(@RequestParam String pageId) {  
		System.out.println(pageId);
        return "qnyh/"+pageId;  
    } 
}
