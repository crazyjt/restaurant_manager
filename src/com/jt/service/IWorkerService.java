package com.jt.service;

import java.util.List;

import com.jt.domain.Worker;

public interface IWorkerService {
	/**
	 * �����û�id�������¼
	 * @param userName
	 * @param password
	 * @return
	 */
	Worker login (String w_id,String w_password);
	
	/**
	 * ����û�
	 * @param worker
	 * @return
	 */
	int addWorker (Worker worker);
	
	/**
	 * �޸��û���Ϣ
	 * @param worker
	 * @return
	 */
	int modifyWorker (Worker worker);
	
	/**
	 * �����û�idɾ���û���Ϣ
	 * @param w_id
	 * @return
	 */
	int removeWorker (String w_id);
	
	/**
	 * ����id��ѯ�û���Ϣ
	 * @param w_id
	 * @return
	 */
	Worker findWorkerById (String w_id);
	
	/**
	 * ��ѯ�����û���Ϣ
	 */
	List<Worker> findAllWorker ();
	
	/**
	 * ��������ɸѡ�û�
	 * @param w_type
	 * @param w_sex
	 * @param w_workTime
	 * @param hasPic 
	 * @return
	 */
	List<Worker> findWorkerByCondition (String w_type, String w_sex, String hasPic);

	/**
	 * �����û�����ѯ�û�
	 * @param w_name
	 */
	Worker findWorkerByName(String w_name);
}
