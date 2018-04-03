package com.jt.web.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jt.domain.Worker;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * ¼ì²éµÇÂ¼×´Ì¬µÄÀ¹½ØÆ÷
 * @author ¾ûÍ¯
 *
 */
public class CheckLoginInterceptor extends MethodFilterInterceptor {
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Worker worker = (Worker) session.getAttribute("worker");
		if(worker == null) {
			//ÎÞµÇÂ¼·µ»ØµÇÂ¼
			return "login";
		}
		//ÓÐÔò·ÅÐÐ
		return invocation.invoke();
	}

}
