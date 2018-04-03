package com.jt.utils;

import com.jt.domain.Worker;
import com.jt.service.IWorkerService;
import com.jt.service.impl.WorkerServiceImpl;

import net.sf.json.JSONObject;

public class MobileWorkerJudge {
	public Worker judeWorker(JSONObject jsonObject) {
		String w_id = jsonObject.getString("w_id");
		IWorkerService workerService = new WorkerServiceImpl();
		Worker worker = workerService.findWorkerById(w_id);
		return worker;
	}
}
