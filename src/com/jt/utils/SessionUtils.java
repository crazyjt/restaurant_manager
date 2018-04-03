package com.jt.utils;

import org.hibernate.classic.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtils {
	
	private static SessionFactory sf;
	
	//��̬��ʼ������飬ֻ����һ��
	static {
		//��������
		Configuration conf = new Configuration().configure();
		sf = conf.buildSessionFactory();
		//�ر���Դ����������ر�ʱ����
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("SessionFactory�رգ��ͷ���Դ");
				sf.close();
			}
		}));
	}
	
	//openSession������ÿ�ζ�����һ���µ�session
	public static Session openSession () {
		Session openSession = sf.openSession();
		return openSession;
	}
	
	//getCurrentSession��õ�ǰ�߳��е�session
	public static org.hibernate.Session getCurrentSession () {
		Session currentSession = sf.getCurrentSession();
		return currentSession;
	}
	
	public static void main(String[] args) {
		getCurrentSession();
	}
}
