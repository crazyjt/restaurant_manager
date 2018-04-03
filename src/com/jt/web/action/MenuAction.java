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

	// 显示所有菜单信息
	List<Menu> menus;

	public String listAllMenu() throws Exception {
		menus = service.findAllMenu();
		return SUCCESS;
	}

	// 根据条件查询
	private String hasPic;

	public String listMenuByCondition() throws Exception {
		System.out.println("action: " + menu.getM_name() + menu.getM_type() + hasPic);
		menus = service.findMenuByCondition(menu.getM_name(), menu.getM_type(), hasPic);
		return SUCCESS;
	}

	// 添加菜品
	private File m_pic;
	private String m_picFileName;

	public String addMenu() throws Exception {
		if (menu.getM_id() == null || StringUtils.isBlank(menu.getM_id())) {
			addActionError("id不能为空");
			return INPUT;
		}
		// 判断id是否已经存在
		Menu dbMenu = service.findMenuById(menu.getM_id());
		if (dbMenu != null) {
			addActionError("该id已存在");
			return INPUT;
		}
		// 如果有上传照片则创建照片文件
		if (m_pic != null && m_picFileName != null) {
			// 1.文件保存路径
			String filePath = ServletActionContext.getServletContext().getRealPath("/pictures");
			System.out.println("filePath: " + filePath);
			String dir = generateChildPath(filePath);
			// 生成带有随机性的文件名
			String fileName = TokenHelper.generateGUID() + "_" + m_picFileName;

			menu.setM_path(dir);
			menu.setM_filename(fileName); // 保存带有随机码的文件名，下载需要
			System.out.println(
					"w_path: " + menu.getM_path() + " w_filename: " + menu.getM_filename() + "filePath: " + filePath);
			// 将文件剪切到服务器目录
			m_pic.renameTo(new File(filePath + File.separator + dir, fileName));
		}
		int res = service.addMenu(menu);
		if (res > 0) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	// 生成一个以类别作为文件名的文件夹
	private String generateChildPath(String filePath) {
		String dir = menu.getM_type();
		File file = new File(filePath, dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		System.out.println("dir: " + dir + "  filePath: " + filePath);
		return dir;
	}

	// 显示修改菜品信息界面
	public String editMenuUI() throws Exception {
		menu = service.findMenuById(menu.getM_id());
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(menu);
		return SUCCESS;
	}

	// 下载相片
	private InputStream inputStream; // 用于struts.xml文件中注入的参数
	private String oldFileName; // 原始文件名

	public String menuPicDownload() throws Exception {
		Menu dbMenu = service.findMenuById(menu.getM_id());
		System.out.println("dbmenu: " + dbMenu.getM_path() + "   " + dbMenu.getM_filename());
		String filePath = ServletActionContext.getServletContext().getRealPath("/pictures");
		inputStream = new FileInputStream(
				filePath + File.separator + dbMenu.getM_path() + File.separator + dbMenu.getM_filename());
		oldFileName = dbMenu.getM_filename().substring(dbMenu.getM_filename().indexOf("_") + 1);
		return SUCCESS;
	}

	// 修改菜品
	public String editMenu() throws Exception {
		if (m_pic == null) {
			// 用户没有重新上传照片
			// 当前VauleStack中的worker没有照片信息，只有表单中的数据
			Menu dbmenu = service.findMenuById(menu.getM_id());
			menu.setM_path(dbmenu.getM_path());
			menu.setM_filename(dbmenu.getM_filename());
		} else {
			// 用户重新上传照片
			// 1.文件保存路径
			String filePath = ServletActionContext.getServletContext().getRealPath("/pictures");
			String dir = generateChildPath(filePath);
			// 生成带有随机性的文件名
			String fileName = TokenHelper.generateGUID() + "_" + m_picFileName;

			menu.setM_path(dir);
			menu.setM_filename(fileName); // 保存带有随机码的文件名，下载需要
			System.out.println("w_path: " + menu.getM_path() + " w_filename: " + menu.getM_filename());
			// 将文件剪切到服务器目录
			m_pic.renameTo(new File(filePath + File.separator + dir, fileName));
		}

		int res = service.modifyMenu(menu);
		if (res > 0) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	// 显示单个菜品详细信息
	public String viewMenu() throws Exception {
		menu = service.findMenuById(menu.getM_id());
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(menu);
		return SUCCESS;
	}

	// 显示相片
	public String menuPicShow() throws Exception {
		// 获得图片真实路径
		Menu dbMenu = service.findMenuById(menu.getM_id());
		String filePath = ServletActionContext.getServletContext().getRealPath(
				"/pictures" + File.separator + dbMenu.getM_path() + File.separator + dbMenu.getM_filename());
		FileInputStream fileInputStream = new FileInputStream(filePath);
		// 获取输出流
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

	// 删除菜品
	public String deleteMenu() throws Exception {
		int res = service.removeMenu(menu.getM_id());
		if (res > 0) {
			return SUCCESS;
		} else {
			return null;
		}
	}

	// 移动端显示菜单列表
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

	//移动端菜品详细信息
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
