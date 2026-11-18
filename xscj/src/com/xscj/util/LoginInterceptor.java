package com.xscj.util;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 6031927993649759521L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		String userName = (String) ctx.getSession().get("userName");
		String userRole = (String) ctx.getSession().get("userRole");
		if (userName != null && userRole != null) {
			return invocation.invoke();
		}
		((ActionSupport) invocation.getAction()).addActionError("您还没有登录，系统拒绝了您的访问请求");
		return Action.LOGIN;
	}

}
