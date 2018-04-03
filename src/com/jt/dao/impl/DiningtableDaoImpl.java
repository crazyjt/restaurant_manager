package com.jt.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.jt.dao.DiningtableDao;
import com.jt.domain.Diningtable;
import com.jt.utils.SessionUtils;

public class DiningtableDaoImpl implements DiningtableDao {

	int SUCCESS = 1;
	
	@Override
	public int insertTable(Diningtable diningtable) {
		try {
			Session session = SessionUtils.getCurrentSession();
			session.save(diningtable);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateTable(Diningtable diningtable) {
		try {
			Session session = SessionUtils.getCurrentSession();
			System.out.println(diningtable);
			Diningtable dbTable = (Diningtable) session.get(Diningtable.class, diningtable.getD_id());
			dbTable.setD_no(diningtable.getD_no());
			dbTable.setD_num(diningtable.getD_num());
			dbTable.setD_status(diningtable.getD_status());
			System.out.println(dbTable);
			session.update(dbTable);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteTable(String d_id) {
		try {
			Session session = SessionUtils.getCurrentSession();
			Diningtable dbTable = (Diningtable) session.get(Diningtable.class, d_id);
			session.delete(dbTable);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Diningtable selectTableById(String d_id) {
		try {
			Session session = SessionUtils.getCurrentSession();
			Diningtable dbTable = (Diningtable) session.get(Diningtable.class, d_id);
			if(dbTable != null) {
				return dbTable;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Diningtable> selectAllTable() {
		try {
			Session session = SessionUtils.getCurrentSession();
			Query query = session.createQuery("from Diningtable");
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Diningtable> selectTableByCondition(String d_no, String d_status) {
		if(StringUtils.isBlank(d_no) && StringUtils.isBlank(d_status)) {
			return selectAllTable();
		} else {
			try {
				Session session = SessionUtils.getCurrentSession();
				String queryStr = "from Diningtable where 1=1";
				StringBuffer queryBuffer = new StringBuffer(queryStr);
				if(StringUtils.isNotBlank(d_no)) {
					queryBuffer.append(" and d_no='");
					queryBuffer.append(d_no + "'");
				}
				if(StringUtils.isNotBlank(d_status)) {
					queryBuffer.append(" and d_status='");
					queryBuffer.append(d_status + "'");
				}
				Query query = session.createQuery(queryBuffer.toString());
				return query.list();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

}
