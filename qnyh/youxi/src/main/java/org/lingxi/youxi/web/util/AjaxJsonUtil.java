/**
 * 
 */
package org.lingxi.youxi.web.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Administrator
 * 2015年10月9日
 */
public class AjaxJsonUtil {

	public static void write(String text,HttpServletResponse response){
		
		response.setContentType("text/plain; charset=UTF-8");
		
		try {
			
			PrintWriter out = response.getWriter();
			
			out.write(text);
			
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void write(Object obj,HttpServletResponse response){
		if(obj==null){
			return ;
		}
		response.setContentType("text/plain; charset=UTF-8");
		
		try {
			
			PrintWriter out = response.getWriter();
			
			out.write(JSONObject.fromObject(obj).toString());
			
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void writeList(Object arrayObj,HttpServletResponse response){
		if(arrayObj==null){
			return ;
		}
		response.setContentType("text/plain; charset=UTF-8");
		
		try {
			
			PrintWriter out = response.getWriter();
			
			out.write(JSONArray.fromObject(arrayObj).toString());
			
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
