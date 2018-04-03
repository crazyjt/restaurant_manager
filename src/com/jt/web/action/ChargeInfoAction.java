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
	//显示所有流水账
	List<Chargeinfo> chargeinfos;
	public String listAllCharge() throws Exception {
		chargeinfos = service.findAllCharge();
		return SUCCESS;
	}

	//根据条件筛选流水账
	public String listChargeByCondition() throws Exception {
		chargeinfos = service.findChargeByCondition(chargeInfo.getOrderInfo().getO_id(), chargeInfo.getWorker().getW_id(), chargeInfo.getDiningtable().getD_id(), chargeInfo.getC_giveBillflg());
		return SUCCESS;
	}
	
	//添加流水账
	public String addCharge() throws Exception {
		if(chargeInfo.getC_id() == null || StringUtils.isBlank(chargeInfo.getC_id())) {
			addActionError("流水账id不能为空");
			return INPUT;
		}
		if(service.findChagreById(chargeInfo.getC_id()) != null) {
			addActionError("该流水账id已存在");
			return INPUT;
		}
		if(chargeInfo.getC_factReceive() == 0) {
			addActionError("请输入正确的实收金额");
			return INPUT;
		}
		
		//表的维护权在主表，子表添加数据需要判断
		if(StringUtils.isBlank(chargeInfo.getOrderInfo().getO_id()) || chargeInfo.getOrderInfo().getO_id() == null) {
			addActionError("订单id不能为空");
			return INPUT;
		} else {
			IOrderInfoService orderInfoService = new OrderInfoServiceImpl();
			OrderInfo orderInfo = orderInfoService.findOrderById(chargeInfo.getOrderInfo().getO_id());
			if(orderInfo == null) {
				addActionError("该订单不存在");
				return INPUT;
			}
		}
		if(StringUtils.isBlank(chargeInfo.getWorker().getW_id()) || chargeInfo.getWorker().getW_id() == null) {
			chargeInfo.setWorker(null);
		} else {
			IWorkerService workerService = new WorkerServiceImpl();
			Worker worker = workerService.findWorkerById(chargeInfo.getWorker().getW_id());
			if(worker == null) {
				addActionError("该员工不存在");
				return INPUT;
			}
		}
		if(StringUtils.isBlank(chargeInfo.getDiningtable().getD_id()) || chargeInfo.getDiningtable().getD_id() == null) {
			chargeInfo.setDiningtable(null);
		} else {
			IDiningtableService diningtableService = new DiningtableServiceImpl();
			Diningtable diningtable = diningtableService.findTableById(chargeInfo.getDiningtable().getD_id());	
				if(diningtable == null) {
				addActionError("该餐桌不存在");
				return INPUT;
			}
		}
		//根据订单的消费金额来给流水账应付金额赋值
		OrderInfo orderInfo = new OrderInfoServiceImpl().findOrderById(chargeInfo.getOrderInfo().getO_id());
		chargeInfo.setC_requestReceive(orderInfo.getO_price());
		if(chargeInfo.getC_factReceive() < chargeInfo.getC_requestReceive()) {
			addActionError("实收金额应大于或等于应收金额" + chargeInfo.getC_requestReceive() + "元");
			return INPUT;
		}
		chargeInfo.setC_returnMoney(chargeInfo.getC_factReceive() - chargeInfo.getC_requestReceive());
		//实收金额小于应付金额
		
		int res = service.addCharge(chargeInfo);
		if(res > 0){
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	//显示修改界面
	public String editChargeUI() throws Exception {
		Chargeinfo chargeinfo = service.findChagreById(chargeInfo.getC_id());
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(chargeinfo);
		return SUCCESS;
	}
	
	//修改流水账信息
	public String editCharge() throws Exception {
		//表的维护权在主表，子表添加数据需要判断
		if (StringUtils.isBlank(chargeInfo.getOrderInfo().getO_id()) || chargeInfo.getOrderInfo().getO_id() == null) {
			addActionError("订单id不能为空");
			return INPUT;
		} else {
			IOrderInfoService orderInfoService = new OrderInfoServiceImpl();
			OrderInfo orderInfo = orderInfoService.findOrderById(chargeInfo.getOrderInfo().getO_id());
			if (orderInfo == null) {
				addActionError("该订单不存在");
				return INPUT;
			}
		}
		if (StringUtils.isBlank(chargeInfo.getWorker().getW_id()) || chargeInfo.getWorker().getW_id() == null) {
			chargeInfo.setWorker(null);
		} else {
			IWorkerService workerService = new WorkerServiceImpl();
			Worker worker = workerService.findWorkerById(chargeInfo.getWorker().getW_id());
			if (worker == null) {
				addActionError("该员工不存在");
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
				addActionError("该餐桌不存在");
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
	
	//显示流水账详细信息
	public String viewCharge() throws Exception {
		Chargeinfo chargeinfo = service.findChagreById(chargeInfo.getC_id());
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(chargeinfo);
		return SUCCESS;
	}
	
	//删除流水账
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
