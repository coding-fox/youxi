/**
 * 
 */
package org.lingxi.base.common;

/**
 * @author Administrator
 * 2015年10月9日
 */
public class AjaxResultBean {

	/**
	 * success
	 * fail
	 */
	private boolean ok;
	private String msg;
	private Object data;
	
	
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	/**
	 * 
	 */
	public AjaxResultBean() {
		super();
		this.ok=true;
	}
	
	/**
	 * @param data
	 */
	public AjaxResultBean(Object data) {
		super();
		this.data = data;
		this.ok=true;
	}
	/**
	 * @param msg
	 */
	public AjaxResultBean(String msg) {
		super();
		this.ok = false;
		this.msg = msg;
	}
	
	/**
	 * @param ok
	 * @param msg
	 * @param data
	 */
	public AjaxResultBean(boolean ok, String msg, Object data) {
		super();
		this.ok = ok;
		this.msg = msg;
		this.data = data;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return the ok
	 */
	public boolean isOk() {
		return ok;
	}
	/**
	 * @param ok the ok to set
	 */
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	
}
