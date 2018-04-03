package com.jt.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.TokenHelper;
import org.hibernate.classic.Validatable;

import com.jt.domain.Menu;
import com.jt.domain.Worker;
import com.jt.service.IMenuService;
import com.jt.service.IWorkerService;
import com.jt.service.impl.MenuServiceImpl;
import com.jt.service.impl.WorkerServiceImpl;
import com.jt.utils.GetRequestJsonStr;
import com.jt.utils.MobileWorkerJudge;
import com.mysql.fabric.xmlrpc.base.Value;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import net.sf.json.JSONObject;

public class MenuAction extends ActionSupport implements ModelDriven<Menu> {

	Menu menu = new Menu();

	@Override
	public Menu getModel() {
		return menu;
	}

	private IMenuService service = new MenuServiceImpl();

	// ��ʾ���в˵���Ϣ
	List<Menu> menus;

	public String listAllMenu() throws Exception {
		menus = service.findAllMenu();
		return SUCCESS;
	}

	// ����������ѯ
	private String hasPic;

	public String listMenuByCondition() throws Exception {
		System.out.println("action: " + menu.getM_name() + menu.getM_type() + hasPic);
		menus = service.findMenuByCondition(menu.getM_name(), menu.getM_type(), hasPic);
		return SUCCESS;
	}

	// ��Ӳ�Ʒ
	private File m_pic;
	private String m_picFileName;

	public String addMenu() throws Exception {
		if (menu.getM_id() == null || StringUtils.isBlank(menu.getM_id())) {
			addActionError("id����Ϊ��");
			return INPUT;
		}
		// �ж�id�Ƿ��Ѿ�����
		Menu dbMenu = service.findMenuById(menu.getM_id());
		if (dbMenu != null) {
			addActionError("��id�Ѵ���");
			return INPUT;
		}
		// ������ϴ���Ƭ�򴴽���Ƭ�ļ�
		if (m_pic != null && m_picFileName != null) {
			// 1.�ļ�����·��
			String filePath = ServletActionContext.getServletContext().getRealPath("/pictures");
			System.out.println("filePath: " + filePath);
			String dir = generateChildPath(filePath);
			// ���ɴ�������Ե��ļ���
			String fileName = TokenHelper.generateGUID() + "_" + m_picFileName;

			menu.setM_path(dir);
			menu.setM_filename(fileName); // ��������������ļ�����������Ҫ
			System.out.println(
					"w_path: " + menu.getM_path() + " w_filename: " + menu.getM_filename() + "filePath: " + filePath);
			// ���ļ����е�������Ŀ¼
			m_pic.renameTo(new File(filePath + File.separator + dir, fileName));
		}
		int res = service.addMenu(menu);
		if (res > 0) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	// ����һ���������Ϊ�ļ������ļ���
	private String generateChildPath(String filePath) {
		String dir = menu.getM_type();
		File file = new File(filePath, dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		System.out.println("dir: " + dir + "  filePath: " + filePath);
		return dir;
	}

	// ��ʾ�޸Ĳ�Ʒ��Ϣ����
	public String editMenuUI() throws Exception {
		menu = service.findMenuById(menu.getM_id());
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(menu);
		return SUCCESS;
	}

	// ������Ƭ
	private InputStream inputStream; // ����struts.xml�ļ���ע��Ĳ���
	private String oldFileName; // ԭʼ�ļ���

	public String menuPicDownload() throws Exception {
		Menu dbMenu = service.findMenuById(menu.getM_id());
		System.out.println("dbmenu: " + dbMenu.getM_path() + "   " + dbMenu.getM_filename());
		String filePath = ServletActionContext.getServletContext().getRealPath("/pictures");
		inputStream = new FileInputStream(
				filePath + File.separator + dbMenu.getM_path() + File.separator + dbMenu.getM_filename());
		oldFileName = dbMenu.getM_filename().substring(dbMenu.getM_filename().indexOf("_") + 1);
		return SUCCESS;
	}

	// �޸Ĳ�Ʒ
	public String editMenu() throws Exception {
		if (m_pic == null) {
			// �û�û�������ϴ���Ƭ
			// ��ǰVauleStack�е�workerû����Ƭ��Ϣ��ֻ�б��е�����
			Menu dbmenu = service.findMenuById(menu.getM_id());
			menu.setM_path(dbmenu.getM_path());
			menu.setM_filename(dbmenu.getM_filename());
		} else {
			// �û������ϴ���Ƭ
			// 1.�ļ�����·��
			String filePath = ServletActionContext.getServletContext().getRealPath("/pictures");
			String dir = generateChildPath(filePath);
			// ���ɴ�������Ե��ļ���
			String fileName = TokenHelper.generateGUID() + "_" + m_picFileName;

			menu.setM_path(dir);
			menu.setM_filename(fileName); // ��������������ļ�����������Ҫ
			System.out.println("w_path: " + menu.getM_path() + " w_filename: " + menu.getM_filename());
			// ���ļ����е�������Ŀ¼
			m_pic.renameTo(new File(filePath + File.separator + dir, fileName));
		}

		int res = service.modifyMenu(menu);
		if (res > 0) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	// ��ʾ������Ʒ��ϸ��Ϣ
	public String viewMenu() throws Exception {
		menu = service.findMenuById(menu.getM_id());
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(menu);
		return SUCCESS;
	}

	// ��ʾ��Ƭ
	public String menuPicShow() throws Exception {
		// ���ͼƬ��ʵ·��
		Menu dbMenu = service.findMenuById(menu.getM_id());
		String filePath = ServletActionContext.getServletContext().getRealPath(
				"/pictures" + File.separator + dbMenu.getM_path() + File.separator + dbMenu.getM_filename());
		FileInputStream fileInputStream = new FileInputStream(filePath);
		// ��ȡ�����
		ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
		String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + fileName);
		ServletActionContext.getResponse().setHeader("content-type", "image/jpeg");
		int len = 1;
		byte[] b = new byte[1024];
		while ((len = fileInputStream.read(b)) != -1) {
			outputStream.write(b, 0, len);
		}
		outputStream.close();
		fileInputStream.close();
		return SUCCESS;
	}

	// ɾ����Ʒ
	public String deleteMenu() throws Exception {
		int res = service.removeMenu(menu.getM_id());
		if (res > 0) {
			return SUCCESS;
		} else {
			return null;
		}
	}

	// �ƶ�����ʾ�˵��б�
	private JSONObject menuJson;
	public String mobileMenus() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		int code = 0;
		try {
			menus = service.findAllMenu();
			System.out.println("meus: " + menus);
			code = 200;
		} catch (Exception e) {
			code = 300;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		List<Menu> returnMenus = new ArrayList<>();
		for(int i = 0; i < menus.size(); i++) {
//			System.out.println("before: " + menus.get(i).getOrderInfos());
			Menu newMenu = new Menu();
			newMenu.setM_id(menus.get(i).getM_id());
			newMenu.setM_inventory(menus.get(i).getM_inventory());
			newMenu.setM_filename(menus.get(i).getM_filename());
			newMenu.setM_name(menus.get(i).getM_name());
			newMenu.setM_path(menus.get(i).getM_path());
			newMenu.setM_price(menus.get(i).getM_price());
			newMenu.setM_type(menus.get(i).getM_type());
			newMenu.setOrderInfos(null);
			returnMenus.add(newMenu);
		}
//		for(int i = 0; i < service.findAllMenu().size(); i++){
//			System.out.println("after: " + service.findAllMenu().get(i).getOrderInfos());
//		}
		System.out.println("returnMenus: " + returnMenus);
		map.put("menus", returnMenus);
		System.out.println("map-menus: " + map.get("menus"));
		menuJson = JSONObject.fromObject(map);
		return SUCCESS;
	}

	//�ƶ��˲�Ʒ��ϸ��Ϣ
	private JSONObject menuInfoJson;
	public String mobileMenuInfo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();
		String jsonStr = new GetRequestJsonStr().getJsonStr(request);
		JSONObject jsonRequest = JSONObject.fromObject(jsonStr);
		String m_id = jsonRequest.getString("m_id");
		int code = 0;
	    Menu serviceMenu = service.findMenuById(m_id);
	    Menu newMenu = new Menu();
		if(serviceMenu == null) {
			code = 300;
		} else {
			code = 200;
			newMenu.setM_id(serviceMenu.getM_id());
			newMenu.setM_inventory(serviceMenu.getM_inventory());
			newMenu.setM_filename(serviceMenu.getM_filename());
			newMenu.setM_name(serviceMenu.getM_name());
			newMenu.setM_path(serviceMenu.getM_path());
			newMenu.setM_price(serviceMenu.getM_price());
			newMenu.setM_type(serviceMenu.getM_type());
			newMenu.setOrderInfos(null);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("code", code);
		map.put("menu", newMenu);
		menuInfoJson = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public String getHasPic() {
		return hasPic;
	}

	public void setHasPic(String hasPic) {
		this.hasPic = hasPic;
	}

	public File getM_pic() {
		return m_pic;
	}

	public void setM_pic(File m_pic) {
		this.m_pic = m_pic;
	}

	public String getM_picFileName() {
		return m_picFileName;
	}

	public void setM_picFileName(String m_picFileName) {
		this.m_picFileName = m_picFileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getOldFileName() {
		return oldFileName;
	}

	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}

	public JSONObject getMenuJson() {
		return menuJson;
	}

	public void setMenuJson(JSONObject menuJson) {
		this.menuJson = menuJson;
	}

	public JSONObject getMenuInfoJson() {
		return menuInfoJson;
	}

	public void setMenuInfoJson(JSONObject menuInfoJson) {
		this.menuInfoJson = menuInfoJson;
	}

}
