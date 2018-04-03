package com.jt.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;

import com.jt.utils.SessionUtils;
/**
 * ÿ���������һ������Ŀ������ύ
 * @author ��ͯ
 *
 */
public class TransactionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Session session = SessionUtils.getCurrentSession();
		session.beginTransaction();
		try {
			//����������
			chain.doFilter(request, response);
			if (session != null && session.isOpen()) {
				//�ύ����
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null && session.isOpen()) {
				//�ع�����
				session.getTransaction().rollback();
			}
		}
		
	}

	@Override
	public void destroy() {
		
	}


}
