package com.jt.web.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jt.domain.Chargeinfo;
import com.jt.domain.Diningtable;
import com.jt.domain.OrderInfo;
import com.jt.domain.Worker;
import com.jt.service.IChargeInfoService;
import com.jt.service.IDiningtableService;
import com.jt.service.IOrderInfoService;
import com.jt.service.IWorkerService;
import com.jt.service.impl.ChargeInfoServiceImpl;
import com.jt.service.impl.DiningtableServiceImpl;
import com.jt.service.impl.OrderInfoServiceImpl;
import com.jt.service.impl.WorkerServiceImpl;
import com.mysql.fabric.xmlrpc.base.Value;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class ChargeInfoAction extends ActionSupport implements ModelDriven<Chargeinfo> {

	private Chargeinfo chargeInfo = new Chargeinfo();
	
	@Override
	public Chargeinfo getModel() {
		return chargeInfo;
	}

	private IChargeInfoService service = new ChargeInfoServiceImpl();
	//��ʾ������ˮ��
	List<Chargeinfo> chargeinfos;
	public String listAllCharge() throws Exception {
		chargeinfos = service.findAllCharge();
		return SUCCESS;
	}

	//��������ɸѡ��ˮ��
	public String listChargeByCondition() throws Exception {
		chargeinfos = service.findChargeByCondition(chargeInfo.getOrderInfo().getO_id(), chargeInfo.getWorker().getW_id(), chargeInfo.getDiningtable().getD_id(), chargeInfo.getC_giveBillflg());
		return SUCCESS;
	}
	
	//�����ˮ��
	public String addCharge() throws Exception {
		if(chargeInfo.getC_id() == null || StringUtils.isBlank(chargeInfo.getC_id())) {
			addActionError("��ˮ��id����Ϊ��");
			return INPUT;
		}
		if(service.findChagreById(chargeInfo.getC_id()) != null) {
			addActionError("����ˮ��id�Ѵ���");
			return INPUT;
		}
		if(chargeInfo.getC_factReceive() == 0) {
			addActionError("��������ȷ��ʵ�ս��");
			return INPUT;
		}
		
		//���ά��Ȩ�������ӱ����������Ҫ�ж�
		if(StringUtils.isBlank(chargeInfo.getOrderInfo().getO_id()) || chargeInfo.getOrderInfo().getO_id() == null) {
			addActionError("����id����Ϊ��");
			return INPUT;
		} else {
			IOrderInfoService orderInfoService = new OrderInfoServiceImpl();
			OrderInfo orderInfo = orderInfoService.findOrderById(chargeInfo.getOrderInfo().getO_id());
			if(orderInfo == null) {
				addActionError("�ö���������");
				return INPUT;
			}
		}
		if(StringUtils.isBlank(chargeInfo.getWorker().getW_id()) || chargeInfo.getWorker().getW_id() == null) {
			chargeInfo.setWorker(null);
		} else {
			IWorkerService workerService = new WorkerServiceImpl();
			Worker worker = workerService.findWorkerById(chargeInfo.getWorker().getW_id());
			if(worker == null) {
				addActionError("��Ա��������");
				return INPUT;
			}
		}
		if(StringUtils.isBlank(chargeInfo.getDiningtable().getD_id()) || chargeInfo.getDiningtable().getD_id() == null) {
			chargeInfo.setDiningtable(null);
		} else {
			IDiningtableService diningtableService = new DiningtableServiceImpl();
			Diningtable diningtable = diningtableService.findTableById(chargeInfo.getDiningtable().getD_id());	
				if(diningtable == null) {
				addActionError("�ò���������");
				return INPUT;
			}
		}
		//���ݶ��������ѽ��������ˮ��Ӧ����ֵ
		OrderInfo orderInfo = new OrderInfoServiceImpl().findOrderById(chargeInfo.getOrderInfo().getO_id());
		chargeInfo.setC_requestReceive(orderInfo.getO_price());
		if(chargeInfo.getC_factReceive() < chargeInfo.getC_requestReceive()) {
			addActionError("ʵ�ս��Ӧ���ڻ����Ӧ�ս��" + chargeInfo.getC_requestReceive() + "Ԫ");
			return INPUT;
		}
		chargeInfo.setC_returnMoney(chargeInfo.getC_factReceive() - chargeInfo.getC_requestReceive());
		//ʵ�ս��С��Ӧ�����
		
		int res = service.addCharge(chargeInfo);
		if(res > 0){
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	//��ʾ�޸Ľ���
	public String editChargeUI() throws Exception {
		Chargeinfo chargeinfo = service.findChagreById(chargeInfo.getC_id());
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(chargeinfo);
		return SUCCESS;
	}
	
	//�޸���ˮ����Ϣ
	public String editCharge() throws Exception {
		//���ά��Ȩ�������ӱ����������Ҫ�ж�
		if (StringUtils.isBlank(chargeInfo.getOrderInfo().getO_id()) || chargeInfo.getOrderInfo().getO_id() == null) {
			addActionError("����id����Ϊ��");
			return INPUT;
		} else {
			IOrderInfoService orderInfoService = new OrderInfoServiceImpl();
			OrderInfo orderInfo = orderInfoService.findOrderById(chargeInfo.getOrderInfo().getO_id());
			if (orderInfo == null) {
				addActionError("�ö���������");
				return INPUT;
			}
		}
		if (StringUtils.isBlank(chargeInfo.getWorker().getW_id()) || chargeInfo.getWorker().getW_id() == null) {
			chargeInfo.setWorker(null);
		} else {
			IWorkerService workerService = new WorkerServiceImpl();
			Worker worker = workerService.findWorkerById(chargeInfo.getWorker().getW_id());
			if (worker == null) {
				addActionError("��Ա��������");
				return INPUT;
			}
		}
		if (StringUtils.isBlank(chargeInfo.getDiningtable().getD_id())
				|| chargeInfo.getDiningtable().getD_id() == null) {
			chargeInfo.setDiningtable(null);
		} else {
			IDiningtableService diningtableService = new DiningtableServiceImpl();
			Diningtable diningtable = diningtableService.findTableById(chargeInfo.getDiningtable().getD_id());
			if (diningtable == null) {
				addActionError("�ò���������");
				return INPUT;
			}
		}
		chargeInfo.setC_requestReceive(chargeInfo.getOrderInfo().getO_price());
		chargeInfo.setC_returnMoney(chargeInfo.getC_factReceive() - chargeInfo.getC_requestReceive());
		int res = service.modifyCharge(chargeInfo);
		if(res > 0) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	//��ʾ��ˮ����ϸ��Ϣ
	public String viewCharge() throws Exception {
		Chargeinfo chargeinfo = service.findChagreById(chargeInfo.getC_id());
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(chargeinfo);
		return SUCCESS;
	}
	
	//ɾ����ˮ��
	public String deleteCharge() throws Exception {
		int res = service.removeCharge(chargeInfo.getC_id());
		if(res > 0 ){
			return SUCCESS;
		} else {
			return null;
		}
	}
	public Chargeinfo getChargeInfo() {
		return chargeInfo;
	}

	public void setChargeInfo(Chargeinfo chargeInfo) {
		this.chargeInfo = chargeInfo;
	}

	public List<Chargeinfo> getChargeinfos() {
		return chargeinfos;
	}

	public void setChargeinfos(List<Chargeinfo> chargeinfos) {
		this.chargeinfos = chargeinfos;
	}
	
}
