package com.jt.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.jt.domain.Diningtable;
import com.jt.domain.Menu;
import com.jt.domain.OrderInfo;
import com.jt.domain.Worker;
import com.jt.service.IDiningtableService;
import com.jt.service.IMenuService;
import com.jt.service.IOrderInfoService;
import com.jt.service.IWorkerService;
import com.jt.service.impl.DiningtableServiceImpl;
import com.jt.service.impl.MenuServiceImpl;
import com.jt.service.impl.OrderInfoServiceImpl;
import com.jt.service.impl.WorkerServiceImpl;
import com.jt.utils.GetRequestJsonStr;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

public class OrderInfoAction extends ActionSupport implements ModelDriven<OrderInfo> {

	private OrderInfo order = new OrderInfo();

	@Override
	public OrderInfo getModel() {
		return order;
	}

	private IOrderInfoService service = new OrderInfoServiceImpl();

	// ��ʾ���ж���
	List<OrderInfo> orderInfos;

	public String listAllOrder() throws Exception {
		orderInfos = service.findAllOrder();
		return SUCCESS;
	}

	// ��������ѯ����
	public String listOrderByCondition() throws Exception {
		System.out.println(order.getO_id() + "," + order.getDiningtable().getD_id() + "," + order.getO_pay());
		orderInfos = service.findOrderByCondition(order.getO_id(), order.getDiningtable().getD_id(), order.getO_pay());
		return SUCCESS;
	}

	// ��ʾ��Ӷ�������
	List<Menu> allMenus;

	public String addOrderUI() throws Exception {
		IMenuService menuService = new MenuServiceImpl();
		allMenus = menuService.findAllMenu();
		return SUCCESS;
	}

	// ��Ӷ���
	public String addOrder() throws Exception {
		System.out.println("m_order: " + order.getM_order());
		if (StringUtils.isBlank(order.getO_id())) {
			addActionError("����id����Ϊ��");
			return INPUT;
		}
		OrderInfo dbOrder = service.findOrderById(order.getO_id());
		if (dbOrder != null) {
			addActionError("�ö���id�Ѿ�����");
			return INPUT;
		}
		if (StringUtils.isBlank(order.getWorker().getW_id())) {
			order.setWorker(null);
		} else {
			IWorkerService workerService = new WorkerServiceImpl();
			Worker dbWorker = workerService.findWorkerById(order.getWorker().getW_id());
			if (dbWorker == null) {
				addActionError("��Ա��id������");
				return INPUT;
			}
		}
		if (StringUtils.isBlank(order.getDiningtable().getD_id())) {
			order.setDiningtable(null);
		} else {
			IDiningtableService diningtableService = new DiningtableServiceImpl();
			Diningtable dbTable = diningtableService.findTableById(order.getDiningtable().getD_id());
			if (dbTable == null) {
				addActionError("�ò���id������");
				return INPUT;
			}
		}
		// ��Ʒѡ��
		String[] orderNum = order.getM_order().split(",");
		float cost = 0;
		IMenuService menuService = new MenuServiceImpl();
		allMenus = menuService.findAllMenu();
		StringBuffer newM_order = new StringBuffer();
		System.out.println("------  " + allMenus.size() + " ------- " + orderNum.length);
		for (int i = 0; i < allMenus.size(); i++) {
			int num = 0;
			Menu dbMenu = menuService.findMenuById(allMenus.get(i).getM_id());
			if(StringUtils.isBlank(orderNum[i])) {
				num = 0;
			} else if (orderNum[i].contains(" ")) {
				num = Integer.parseInt(orderNum[i].substring(1));
				order.getMenus().add(dbMenu);
			} else {
				num = Integer.parseInt(orderNum[i]);
				order.getMenus().add(dbMenu);
			}
			cost += allMenus.get(i).getM_price() * num;
			System.out.println("num: " + num);
			dbMenu.setM_inventory(dbMenu.getM_inventory() - num);
			menuService.modifyMenu(dbMenu);
			newM_order.append(String.valueOf(num));
			if(i != (allMenus.size() - 1)){
				newM_order.append(",");
			}
		}
		order.setM_order(newM_order.toString());
		System.out.println("orer_m_order: " + order.getM_order());
		order.setO_price(cost);
		order.setO_serverTime(new Timestamp(System.currentTimeMillis()));
		int res = service.addOrder(order);
		if (res > 0) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	// ��ʾ�޸Ľ���
	public String editOrderUI() throws Exception {
		OrderInfo dbOrder = service.findOrderById(order.getO_id());
		IMenuService menuService = new MenuServiceImpl();
		allMenus = menuService.findAllMenu();
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(dbOrder);
		valueStack.push(allMenus);
		return SUCCESS;
	}

	// �޸Ķ���
	public String editOrder() throws Exception {
		if (StringUtils.isBlank(order.getWorker().getW_id())) {
			order.setWorker(null);
		} else {
			IWorkerService workerService = new WorkerServiceImpl();
			Worker dbWorker = workerService.findWorkerById(order.getWorker().getW_id());
			if (dbWorker == null) {
				addActionError("��Ա��id������");
				return INPUT;
			}
		}
		if (StringUtils.isBlank(order.getDiningtable().getD_id())) {
			order.setDiningtable(null);
		} else {
			IDiningtableService diningtableService = new DiningtableServiceImpl();
			Diningtable dbTable = diningtableService.findTableById(order.getDiningtable().getD_id());
			if (dbTable == null) {
				addActionError("�ò���id������");
				return INPUT;
			}
		}
		// ��Ʒѡ��
		StringBuffer newM_order = new StringBuffer();
		OrderInfo oldOrder = service.findOrderById(order.getO_id());
		System.out.println("oldm_Order: " + oldOrder.getM_order());
		String[] oldOrderNum = oldOrder.getM_order().split(",");
		String[] orderNum = order.getM_order().split(",");
		float cost = oldOrder.getO_price();
		IMenuService menuService = new MenuServiceImpl();
		allMenus = menuService.findAllMenu();
		System.out.println("------  " + allMenus.size() + " ------- " + orderNum.length);
		for (int i = 0; i < allMenus.size(); i++) {
			//��ȡ��������ķ���
			int num = 0;
			if(StringUtils.isBlank(orderNum[i])) { //û�������������
				num = -1;
			} else if (orderNum[i].contains(" ")) {
				num = Integer.parseInt(orderNum[i].substring(1));
			} else {
				num = Integer.parseInt(orderNum[i]);
			}
			//��������ķ����Ա�ԭ���ķ���
			int oldNum = Integer.parseInt(oldOrderNum[i]);
			Menu dbMenu = menuService.findMenuById(allMenus.get(i).getM_id());
			if(num == -1 || num == oldNum) { //û�������������
				newM_order.append(String.valueOf(oldNum));
			} else if (num > oldNum){
				cost += allMenus.get(i).getM_price() * (num - oldNum);
				dbMenu.setM_inventory(dbMenu.getM_inventory() - (num - oldNum));
				newM_order.append(String.valueOf((num)));
			} else if (num < oldNum) {
				cost -= allMenus.get(i).getM_price() * (oldNum - num);
				dbMenu.setM_inventory(dbMenu.getM_inventory() + (oldNum - num));
				newM_order.append(String.valueOf((num)));
			}
			if(i != (allMenus.size() -1)) {
				newM_order.append(",");
			}
			
			if( num != 0 ) {
				if(num == -1 && oldNum == 0) {
				} else {
					order.getMenus().add(dbMenu);
				}
			}
			menuService.modifyMenu(dbMenu);			
		}
		order.setM_order(newM_order.toString());
		order.setO_price(cost);
		int res = service.modifyOrder(order);
		if (res > 0) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	// ��ʾ������Ϣ
	List<Menu> menuList = new ArrayList<>();
	private String menuSetStr;
	public String viewOrder() throws Exception {
		System.out.println("------------" + order);
		order = service.findOrderById(order.getO_id());
		IMenuService menuService = new MenuServiceImpl();
		allMenus = menuService.findAllMenu();
		String[] strings = order.getM_order().split(",");
		StringBuffer stringBuffer = new StringBuffer();
		for(int i = 0; i < strings.length; i++) {
			if(!"0".equals(strings[i])) {
				stringBuffer.append(strings[i] + ",");
				menuList.add(allMenus.get(i));
			}
		}
		menuSetStr = stringBuffer.toString().substring(0, stringBuffer.toString().length()-1);
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(order);
		valueStack.push(menuList);
		return SUCCESS;
	}

	// ɾ��������Ϣ
	public String deleteOrder() throws Exception {
		System.out.println("deletOrder-----------");
		int res = service.removeOrder(order.getO_id());
		if (res > 0) {
			return SUCCESS;
		} else {
			return null;
		}
	}

	//�ƶ�����ʾ�ѵ㶩��
	private JSONObject orderJson;
	public String mobileOrder() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();
		String jsonStr = new GetRequestJsonStr().getJsonStr(request);
		JSONObject jsonRequest = JSONObject.fromObject(jsonStr);
		String d_id = jsonRequest.getString("d_id");

		int code = 0;
		Map<String, List<String>> map = new HashMap<>();
		
		float o_price = 0;
		try{
			OrderInfo orderInfoByTable = service.findOrderByTable(d_id, "��");
			o_price = orderInfoByTable.getO_price();
			String[] m_orderByTableNum = orderInfoByTable.getM_order().split(",");
			IMenuService menuService = new MenuServiceImpl();
			List<Menu> menusByTable = menuService.findAllMenu();
			for(int i = 0; i < menusByTable.size(); i++) {
				List<String> returnList = new ArrayList<>();
				returnList.add(menusByTable.get(i).getM_name());
				returnList.add(String.valueOf(menusByTable.get(i).getM_inventory()));
				returnList.add(String.valueOf(menusByTable.get(i).getM_price()));
				returnList.add(m_orderByTableNum[i]);
				
				//��ƷͼƬ
				byte[] bytes = null;
				String picStr = "";
				try {
					String picPath = ServletActionContext.getServletContext().getRealPath("/pictures" + File.separator + menusByTable.get(i).getM_path() + File.separator + menusByTable.get(i).getM_filename());
					File picFile = new File(picPath);
					InputStream inputStream = new FileInputStream(picFile);
					bytes = new byte[inputStream.available()];
					inputStream.read(bytes);
					inputStream.close();
					BASE64Encoder encoder = new BASE64Encoder();
					picStr = encoder.encode(bytes);
				} catch (Exception e) {
					e.printStackTrace();
				}
				returnList.add(picStr);
				
				map.put(String.valueOf(i), returnList);
			}
			code = 200;
		} catch (Exception e) {
			code = 300;
			e.printStackTrace();
		}
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("code", code);
		returnMap.put("map", map);
		returnMap.put("price", o_price);
		orderJson = JSONObject.fromObject(returnMap);
		return SUCCESS;
	}
	
	public OrderInfo getOrder() {
		return order;
	}

	public void setOrder(OrderInfo order) {
		this.order = order;
	}

	public List<OrderInfo> getOrderInfos() {
		return orderInfos;
	}

	public void setOrderInfos(List<OrderInfo> orderInfos) {
		this.orderInfos = orderInfos;
	}

	public List<Menu> getAllMenus() {
		return allMenus;
	}

	public void setAllMenus(List<Menu> allMenus) {
		this.allMenus = allMenus;
	}


	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public String getMenuSetStr() {
		return menuSetStr;
	}

	public void setMenuSetStr(String menuSetStr) {
		this.menuSetStr = menuSetStr;
	}

	public JSONObject getOrderJson() {
		return orderJson;
	}

	public void setOrderJson(JSONObject orderJson) {
		this.orderJson = orderJson;
	}

}
