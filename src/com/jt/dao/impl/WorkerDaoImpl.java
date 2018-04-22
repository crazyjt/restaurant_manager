package com.jt.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.jt.dao.WorkerDao;
import com.jt.domain.Worker;
import com.jt.utils.SessionUtils;

public class WorkerDaoImpl implements WorkerDao {


	@Override
	public Worker selectWorkerByInfo(String w_id, String w_password) {
		try {
		    Worker worker = new Worker();
			Session session = SessionUtils.getCurrentSession();
			Query query = session.createQuery("from Worker where w_id =:w_id and w_password =:w_password")
					.setString("w_id", w_id)
					.setString("w_password", w_password);
			worker = (Worker) query.uniqueResult();
			if (worker != null){
				return worker;
			} else {
				return null;
			}
		} catch (HibernateException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public int insertWorker(Worker worker) {
		try {
			int SUCCESS = 1;
			Session session = SessionUtils.getCurrentSession();
			session.save(worker);
			return SUCCESS;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateWorker(Worker worker) {
		try {
			int SUCCESS = 1;
			Session session = SessionUtils.getCurrentSession();
			Worker dbworker = (Worker) session.get(Worker.class, worker.getW_id());
			dbworker.setW_password(worker.getW_password());
			dbworker.setW_name(worker.getW_name());
			dbworker.setW_sex(worker.getW_sex());
			dbworker.setW_type(worker.getW_type());
			dbworker.setW_workTime(worker.getW_workTime());
			dbworker.setW_path(worker.getW_path());
			dbworker.setW_filename(worker.getW_filename());
			System.out.println("dbworker: " + dbworker + ",worker: " + worker);
			session.update(dbworker);
			return SUCCESS;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteWorker(String w_id) {
		try {
			int SUCCESS = 1;
			Session session = SessionUtils.getCurrentSession();
			Worker worker = (Worker) session.get(Worker.class, w_id);
			session.delete(worker);
			return SUCCESS;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Worker selectWorkerById(String w_id) {
		try {
			Session session = SessionUtils.getCurrentSession();
			Worker worker = (Worker) session.get(Worker.class, w_id);
			if (worker != null) {
				return worker;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Worker> selectAllWorker() {
		try {
			Session session = SessionUtils.getCurrentSession();
			Query query = session.createQuery("from Worker");
			return query.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Worker> selectWorkerByCondition(String w_type, String w_sex, String hasPic) {
		Session session = SessionUtils.getCurrentSession();
		System.out.println(w_type +" " + w_sex + " " + hasPic);
		//没选条件
		if(StringUtils.isBlank(w_type) && StringUtils.isBlank(w_sex) && StringUtils.isBlank(hasPic)) {
			return selectAllWorker();
		} else {
			try {
				String sqlStr = "from Worker";
				StringBuffer queryBuff = new StringBuffer(sqlStr);
				queryBuff.append(" where 1=1 ");
				if(StringUtils.isNotBlank(w_type)) {
					queryBuff.append(" and w_type = '");
					queryBuff.append(w_type.toString() + "'");
				}
				if(StringUtils.isNotBlank(w_sex)) {
					queryBuff.append(" and w_sex = '");
					queryBuff.append(w_sex.toString() + "'");
				}
				if(StringUtils.isNotBlank(hasPic)) {
					if("true".equals(hasPic)) {
						queryBuff.append(" and w_filename is not null ");
					} else {
						queryBuff.append(" and w_filename is null ");
					}
				}
				System.out.println(queryBuff.toString());
				Query query = session.createQuery(queryBuff.toString());
				return query.list();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public Worker selectWorkerByName(String w_name) {
		try{
			Session session = SessionUtils.getCurrentSession();
			Query query = session.createQuery("from Worker where w_name=:w_name").setString("w_name", w_name);
			Worker worker = (Worker) query.uniqueResult();
			return worker;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
