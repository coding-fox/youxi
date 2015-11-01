/**
 * 
 */
package org.lingxi.base.common;

/**
 * @author Administrator
 * 2015年11月1日
 */
public class StringUtil {

	/**
	 * null or ""
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		
		if(str==null || str.length()==0){
			return true;
		}
		return false;
		
	}
}
