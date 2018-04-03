package com.jt.web.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Order;
import javax.print.attribute.ResolutionSyntax;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.ietf.jgss.Oid;

import com.jt.domain.Diningtable;
import com.jt.domain.Menu;
import com.jt.domain.OrderInfo;
import com.jt.service.IDiningtableService;
import com.jt.service.IMenuService;
import com.jt.service.IOrderInfoService;
import com.jt.service.impl.DiningtableServiceImpl;
import com.jt.service.impl.MenuServiceImpl;
import com.jt.service.impl.OrderInfoServiceImpl;
import com.jt.service.impl.WorkerServiceImpl;
import com.jt.utils.GetRequestJsonStr;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.sun.org.apache.bcel.internal.classfile.Code;

import freemarker.core.CustomAttribute;
import net.sf.json.JSONObject;

public class DiningTableAction extends ActionSupport implements ModelDriven<Diningtable> {

	Diningtable diningtable = new Diningtable();
	
	private IDiningtableService service = new DiningtableServiceImpl();
	
	//��ʾ���в���
	private List<Diningtable> diningtables;
	public String listAllTable() throws Exception {
		diningtables = service.findAllTable();
		System.out.println(diningtables);
		return SUCCESS;
	}
	
	//����������ѯ����
	public String listTableByCondition() throws Exception {
		diningtables = service.findTableByCondition(diningtable.getD_no(), diningtable.getD_status());
		return SUCCESS;
	}
	
	//��Ӳ���
	public String addTable() throws Exception {
		if(diningtable.getD_id() == null || StringUtils.isBlank(diningtable.getD_id())) {
			addActionError("id����Ϊ��");
			return INPUT;
		}
		Diningtable dbTable = service.findTableById(diningtable.getD_id());
		if(dbTable != null) {
			addActionError("��id�Ѵ���");
			return INPUT;
		}
		int res = service.addTable(diningtable);
		if(res > 0) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	//��ʾ�޸Ľ���
	public String editTableUI() throws Exception {
		Diningtable dbTable = service.findTableById(diningtable.getD_id());
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(dbTable);
		return SUCCESS;
	}
	
	//�޸Ĳ�����Ϣ
	public String editTable() throws Exception {
		int res = service.modifyTable(diningtable);
		if(res > 0) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	//��ʾ������Ϣ
	public String viewTable() throws Exception {
		Diningtable dbTable = service.findTableById(diningtable.getD_id());
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(dbTable);
		return SUCCESS;
	}
	
	//ɾ��������Ϣ
	public String deleteTable() throws Exception {
		int res = service.removeTable(diningtable.getD_id());
		if(res > 0) {
			return SUCCESS;
		} else {
			return null;
		}
	}
	
	//�ƶ��˲����б�
	private JSONObject tableJson;
	public String mobileTables() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		int code = 0;
		try {
			diningtables = service.findAllTable();
			code = 200;
		} catch (Exception e) {
			code = 300;
		}
		List<Diningtable> returnTables = new ArrayList<>();
		for(int i = 0; i < diningtables.size(); i++) {
			Diningtable newTable = new Diningtable();
			newTable.setD_id(diningtables.get(i).getD_id());
			newTable.setD_no(diningtables.get(i).getD_no());
			newTable.setD_num(diningtables.get(i).getD_num());
			newTable.setD_status(diningtables.get(i).getD_status());
			newTable.setOrderInfos(null);
			newTable.setChargeinfos(null);
			returnTables.add(newTable);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("code", code);
		map.put("tables", returnTables);
		tableJson = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	//�ƶ��˲�������
	private JSONObject tableOperatorJson;
	public String mobileTableOperator() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();
		String jsonStr = new GetRequestJsonStr().getJsonStr(request);
		JSONObject jsonRequest = JSONObject.fromObject(jsonStr);
		int operator = jsonRequest.getInt("operator");
		Diningtable newTable = new Diningtable();
		newTable.setD_id(jsonRequest.getString("d_id"));
		newTable.setD_no(jsonRequest.getString("d_no"));
		newTable.setD_num(jsonRequest.getInt("d_num"));
		newTable.setD_status("�п�");
		newTable.setChargeinfos(service.findTableById(newTable.getD_id()).getChargeinfos());
		newTable.setOrderInfos(service.findTableById(newTable.getD_id()).getOrderInfos());
		IOrderInfoService orderInfoService = new OrderInfoServiceImpl();
		int code = 0;
		String returnStr = "";
		if(operator == 1) {
			//�޸Ĳ�����Ϣ
			int res = service.modifyTable(newTable);
			//�޸Ķ�����Ϣ
			OrderInfo orderInfoByTable = orderInfoService.findOrderByTable(jsonRequest.getString("d_id"), "��");
			IMenuService menuService = new MenuServiceImpl();
			List<Menu> menus = menuService.findAllMenu();
			System.out.println("oldOrder: " + orderInfoByTable.getM_order());
			String[] oldM_orders = orderInfoByTable.getM_order().split(",");  
			orderInfoByTable.setM_order(jsonRequest.getString("m_order"));
			String[] m_orders = jsonRequest.getString("m_order").split(",");
			orderInfoByTable.setMenus(new HashSet<Menu>());
			float cost = orderInfoByTable.getO_price();
			System.out.println("oldPrice:  " + cost);
			for(int i = 0; i < m_orders.length; i++) {
				int oldNum = Integer.parseInt(oldM_orders[i]);
				int newNum = Integer.parseInt(m_orders[i]);
				Menu dbMenu = menuService.findMenuById(menus.get(i).getM_id());  //�����޸Ŀ��
				if(newNum >= oldNum) {
					cost +=  menus.get(i).getM_price() * (newNum - oldNum);
					dbMenu.setM_inventory(dbMenu.getM_inventory() - (newNum - oldNum));
				} else if(newNum < oldNum){
					cost -= menus.get(i).getM_price() * (oldNum - newNum);
					dbMenu.setM_inventory(dbMenu.getM_inventory() + (oldNum - newNum));
				}
				if(newNum > 0) {
					orderInfoByTable.getMenus().add(menus.get(i));
				}
			}
			orderInfoByTable.setO_price(cost);
			System.out.println("newOrder: " + orderInfoByTable.getM_order());
			System.out.println("newPrice:  " + orderInfoByTable.getO_price());
			int res1 = orderInfoService.modifyOrder(orderInfoByTable);
			if(res > 0 && res1 > 0) {
				code = 200;
				returnStr = "�޸ĳɹ�";
			} else {
				code = 300;
				returnStr = "�޸�ʧ��";
			}
		} else if(operator == 2){
			
			//��Ӷ���
			String o_id = orderInfoService.findLastOrder().getO_id();
			OrderInfo newOrder = new OrderInfo();
			//�������ݿ������ĸ�ʽ��������������
			int temp = Integer.parseInt(o_id.substring(1));
			String o_idBottom = String.valueOf(temp + 1);
			for(; o_idBottom.length() < 4;) {
				o_idBottom = "0" + o_idBottom;
			}
			newOrder.setO_id("o" + o_idBottom);
			System.out.println(newOrder.getO_id());
			
			newOrder.setWorker(new WorkerServiceImpl().findWorkerById(jsonRequest.getString("w_id")));
			newOrder.setDiningtable(newTable);
			newOrder.setM_order(jsonRequest.getString("m_order"));
			newOrder.setO_serverTime(new Timestamp(System.currentTimeMillis()));
			newOrder.setO_pay("��");
			String[] m_orders = newOrder.getM_order().split(",");
			IMenuService menuService = new MenuServiceImpl();
			List<Menu> menus = menuService.findAllMenu();
			float cost = 0;
			for(int i = 0; i < m_orders.length; i++) {
				Menu dbMenu = menuService.findMenuById(menus.get(i).getM_id());  //�����޸Ŀ��
				if(!"0".equals(m_orders[i])) {
					newOrder.getMenus().add(menus.get(i));
					cost += menus.get(i).getM_price() * Integer.parseInt(m_orders[i]);
					dbMenu.setM_inventory(dbMenu.getM_inventory() - Integer.parseInt(m_orders[i]));
				}
				menuService.modifyMenu(dbMenu);
			}
			newOrder.setO_price(cost);
			int res = orderInfoService.addOrder(newOrder);
			//��Ӳ���
			System.out.println("newTable: " + newTable);
			newTable.getOrderInfos().add(newOrder);
			System.out.println("table-orders: " + newTable.getOrderInfos());
			System.out.println("table-chargeinfo: " + newTable.getChargeinfos());
			int res1 = service.modifyTable(newTable);
			if(res > 0 && res1 > 0) {
				code = 200;
				returnStr = "�����ɹ�";
			} else {
				code = 300;
				returnStr = "����ʧ��";
			}
		} else if(operator == 3){
			//��
			OrderInfo orderInfoByTable = orderInfoService.findOrderByTable(jsonRequest.getString("d_id"), "��");
			orderInfoByTable.setO_pay("��");
			int res = orderInfoService.modifyOrder(orderInfoByTable);
//			Diningtable table = service.findTableById(jsonRequest.getString("d_id"));
			newTable.setD_status("����");
			newTable.setD_num(0);
			newTable.setD_no("");
			int res1 = service.modifyTable(newTable);
			if(res > 0 && res1 > 0) {
				code = 200;
				returnStr = "�򵥳ɹ�";
			} else {
				code = 300;
				returnStr = "��ʧ��";
			}
		}
		
		System.out.println("returnStr: " + returnStr);
		Map<String, Object> map = new HashMap<>();
		map.put("code", code);
		map.put("returnStr", returnStr);
		System.out.println("map: " + map);
		tableOperatorJson = JSONObject.fromObject(map);
		System.out.println("tableOpenJson: " + tableInfoJson);
		return SUCCESS;
	}
	
	//�ƶ��˲�����ϸ��Ϣ
	private JSONObject tableInfoJson;
	public String mobileTableInfo() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();
		String jsonStr = new GetRequestJsonStr().getJsonStr(request);
		JSONObject jsonRequest = JSONObject.fromObject(jsonStr);
		String d_id = jsonRequest.getString("d_id");
		int code = 0;
		Diningtable newTable = new Diningtable();
		try {
			Diningtable returnTable = service.findTableById(d_id);
			newTable.setD_id(returnTable.getD_id());
			newTable.setD_no(returnTable.getD_no());
			newTable.setD_num(returnTable.getD_num());
			newTable.setD_status(returnTable.getD_status());
			newTable.setChargeinfos(null);
			newTable.setOrderInfos(null);
			code = 200;
		} catch (Exception e) {
			code = 300;
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<>();
		map.put("code", code);
		map.put("table", newTable);
		tableInfoJson = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	
	@Override
	public Diningtable getModel() {
		return diningtable;
	}
/*
	public Diningtable getdiningtable() {
		return diningtable;
	}

	public void setdiningtable(Diningtable diningtable) {
		this.diningtable = diningtable;
	}
*/

	public void setTableJson(JSONObject tableJson) {
		this.tableJson = tableJson;
	}

	public Diningtable getDiningtable() {
		return diningtable;
	}
	
	public List<Diningtable> getDiningtables() {
		return diningtables;
	}

	public void setDiningtables(List<Diningtable> diningtables) {
		this.diningtables = diningtables;
	}

	public JSONObject getTableJson() {
		return tableJson;
	}

	public void setDiningtable(Diningtable diningtable) {
		this.diningtable = diningtable;
	}

	public JSONObject getTableOperatorJson() {
		return tableOperatorJson;
	}

	public void setTableOperatorJson(JSONObject tableOperatorJson) {
		this.tableOperatorJson = tableOperatorJson;
	}

	public JSONObject getTableInfoJson() {
		return tableInfoJson;
	}

	public void setTableInfoJson(JSONObject tableInfoJson) {
		this.tableInfoJson = tableInfoJson;
	}
	

}
