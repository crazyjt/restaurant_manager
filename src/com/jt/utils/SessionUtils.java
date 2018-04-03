package com.jt.utils;

import org.hibernate.classic.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtils {
	
	private static SessionFactory sf;
	
	//静态初始化代码块，只运行一次
	static {
		//加载配置
		Configuration conf = new Configuration().configure();
		sf = conf.buildSessionFactory();
		//关闭资源，在虚拟机关闭时调用
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("SessionFactory关闭，释放资源");
				sf.close();
			}
		}));
	}
	
	//openSession方法，每次都创建一个新的session
	public static Session openSession () {
		Session openSession = sf.openSession();
		return openSession;
	}
	
	//getCurrentSession获得当前线程中的session
	public static org.hibernate.Session getCurrentSession () {
		Session currentSession = sf.getCurrentSession();
		return currentSession;
	}
	
	public static void main(String[] args) {
		getCurrentSession();
	}
}
