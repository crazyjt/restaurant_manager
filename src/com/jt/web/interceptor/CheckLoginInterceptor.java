package com.jt.web.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jt.domain.Worker;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * ����¼״̬��������
 * @author ��ͯ
 *
 */
public class CheckLoginInterceptor extends MethodFilterInterceptor {
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Worker worker = (Worker) session.getAttribute("worker");
		if(worker == null) {
			//�޵�¼���ص�¼
			return "login";
		}
		//�������
		return invocation.invoke();
	}

}
