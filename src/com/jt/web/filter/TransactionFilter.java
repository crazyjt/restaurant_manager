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
 * 每次请求进行一次事务的开启和提交
 * @author 钧童
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
			//过滤器放行
			chain.doFilter(request, response);
			if (session != null && session.isOpen()) {
				//提交事务
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null && session.isOpen()) {
				//回滚事务
				session.getTransaction().rollback();
			}
		}
		
	}

	@Override
	public void destroy() {
		
	}


}
