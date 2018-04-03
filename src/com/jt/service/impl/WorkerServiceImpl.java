package com.jt.service.impl;

import java.util.List;

import com.jt.dao.WorkerDao;
import com.jt.dao.impl.WorkerDaoImpl;
import com.jt.domain.Worker;
import com.jt.service.IWorkerService;

public class WorkerServiceImpl implements IWorkerService {
	
	WorkerDao dao = new WorkerDaoImpl();
	
	@Override
	public Worker login(String w_id, String w_password) {
		return dao.selectWorkerByInfo(w_id, w_password);
	}

	@Override
	public int addWorker(Worker worker) {
		return dao.insertWorker(worker);
	}

	@Override
	public int modifyWorker(Worker worker) {
		return dao.updateWorker(worker);
	}

	@Override
	public int removeWorker(String w_id) {
		return dao.deleteWorker(w_id);
	}

	@Override
	public Worker findWorkerById(String w_id) {
		return dao.selectWorkerById(w_id);
	}

	@Override
	public List<Worker> findAllWorker() {
		return dao.selectAllWorker();
	}

	@Override
	public List<Worker> findWorkerByCondition(String w_type, String w_sex, String hasPic) {
		return dao.selectWorkerByCondition(w_type, w_sex, hasPic);
	}

	@Override
	public Worker findWorkerByName(String w_name) {
		return dao.selectWorkerByName(w_name);
	}

}
