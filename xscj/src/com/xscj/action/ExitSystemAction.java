/**
 * 
 */
package com.xscj.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author leorain
 *
 */
public class ExitSystemAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4820345843697430799L;

	@Override
	public String execute() throws Exception {
		if(!ActionContext.getContext().getSession().isEmpty())
		{
			ActionContext.getContext().getSession().clear();
		}
		return SUCCESS;
	}

	@Override
	public void validate() {
	}
	
	

}
