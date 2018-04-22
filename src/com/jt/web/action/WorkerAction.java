package com.jt.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.apache.struts2.util.TokenHelper;

import com.jt.domain.Worker;
import com.jt.service.IWorkerService;
import com.jt.service.impl.WorkerServiceImpl;
import com.jt.utils.GetRequestJsonStr;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import com.sun.xml.internal.fastinfoset.stax.util.StAXParserWrapper;

import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

public class WorkerAction extends ActionSupport implements ModelDriven<Worker> {

	private Worker worker = new Worker();

	@Override
	public Worker getModel() {
		return worker;
	}

	private IWorkerService service = new WorkerServiceImpl();

	//登录
	public String login() throws Exception {
		Worker serviceWorker = service.login(worker.getW_id(), worker.getW_password());
		if (serviceWorker == null) {
			addActionError("用户名或密码错误！"); // 添加错误信息用于界面显示
			return INPUT;
		}
		System.out.println(serviceWorker.getW_type());
		if (!"经理".equals(serviceWorker.getW_type()) && !"店长".equals(serviceWorker.getW_type()) && !"组长".equals(serviceWorker.getW_type())) {
			addActionError("您没有访问权限!");
			return INPUT;
		}
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("worker", serviceWorker);
		return SUCCESS;
	}

	//注销
	public String logout() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("worker", null);
		return SUCCESS;
	}
	
	// 表单上传的文件
	private File w_pic;
	// 文件名
	private String w_picFileName;

	// 添加员工
	public String addWorker() throws Exception {
		if(worker.getW_id() == null || StringUtils.isBlank(worker.getW_id())){
			addActionError("id不能为空");
			return INPUT;
		}
		// 判断id是否已经存在
		Worker findWorkerById = service.findWorkerById(worker.getW_id());
		if (findWorkerById != null) {
			addActionError("该id已存在");
			return INPUT;
		}
		//如果有上传照片则创建照片文件
		if(w_pic != null && w_picFileName != null){
			// 1.文件保存路径
			String filePath = ServletActionContext.getServletContext().getRealPath("/pictures");
			String dir = generateChildPath(filePath);
			// 生成带有随机性的文件名
			String fileName = TokenHelper.generateGUID() + "_" + w_picFileName;

			worker.setW_path(dir);
			worker.setW_filename(fileName); // 保存带有随机码的文件名，下载需要
			System.out.println("w_path: " + worker.getW_path() + " w_filename: " + worker.getW_filename());
			// 将文件剪切到服务器目录
			w_pic.renameTo(new File(filePath + File.separator + dir, fileName));
		}
		int res = service.addWorker(worker);
		if (res > 0) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	// 生成一个以岗位作为文件名的文件夹
	private String generateChildPath(String filePath) {
		String dir = worker.getW_type();
		File file = new File(filePath, dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		System.out.println("dir: " + dir);
		return dir;
	}

	// 查询所有用户
	// list集合用于存放查询得到的数据，并且放入ValueStack中供jsp读取
	List<Worker> workers;

	public String listAllWorker() throws Exception {
		workers = service.findAllWorker();
		return SUCCESS;
	}

	// 显示单个员工的详细信息
	public String viewWorker() throws Exception {
		worker = service.findWorkerById(worker.getW_id());
		// 压入栈顶
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(worker);
		return SUCCESS;
	}
	
	// 员工相片下载
	private InputStream inputStream;
	private String oldFileName; //原始文件名
	public String workerPicDownload() throws Exception {
		System.out.println("workerPicDownload action");
		Worker dbWorker = service.findWorkerById(worker.getW_id());
		String filePath = ServletActionContext.getServletContext().getRealPath("/pictures");
		inputStream = new FileInputStream(
				filePath + File.separator + dbWorker.getW_path() + File.separator + dbWorker.getW_filename());
		oldFileName = dbWorker.getW_filename().substring(dbWorker.getW_filename().indexOf("_") + 1);
	
		System.out.println("inputStream: " + inputStream + ", oldFileName: " + oldFileName);
		return SUCCESS;
	}
	
	//员工信息中图片的显示
	public String workerPicShow() throws Exception {
		//获得图片真实路径
		Worker dbWorker = service.findWorkerById(worker.getW_id());
		String filePath = ServletActionContext.getServletContext().getRealPath("/pictures" + File.separator + dbWorker.getW_path() + File.separator + dbWorker.getW_filename());
		FileInputStream fileInputStream = new FileInputStream(filePath);
		// 获取输出流
		ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
		String fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + fileName);
		ServletActionContext.getResponse().setHeader("content-type", "image/jpeg");
		int len = 1;
		byte[] b = new byte[1024];
		while((len = fileInputStream.read(b)) != -1){
			outputStream.write(b, 0, len);
		}
		outputStream.close();
		fileInputStream.close();
		return SUCCESS;
	}
		
	//删除员工
	public String deleteWorker() throws Exception{
		int res = service.removeWorker(worker.getW_id());
		if(res > 0) {			
			return SUCCESS;
		} else {
			return null;
		}
	}
	
	//显示修改员工界面
	public String editWorkerUI() throws Exception{
		worker = service.findWorkerById(worker.getW_id());
		// 压入栈顶
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(worker);
		return SUCCESS;
	}
	
	//修改员工
	public String editWorker() throws Exception {
		if(w_pic == null){
			//用户没有重新上传照片
			//当前VauleStack中的worker没有照片信息，只有表单中的数据
			Worker dbWorker = service.findWorkerById(worker.getW_id());
			worker.setW_path(dbWorker.getW_path());
			worker.setW_filename(dbWorker.getW_filename());
		} else {
			//用户重新上传照片
			// 1.文件保存路径
			String filePath = ServletActionContext.getServletContext().getRealPath("/pictures");
			String dir = generateChildPath(filePath);
			// 生成带有随机性的文件名
			String fileName = TokenHelper.generateGUID() + "_" + w_picFileName;

			worker.setW_path(dir);
			worker.setW_filename(fileName); // 保存带有随机码的文件名，下载需要
			System.out.println("w_path: " + worker.getW_path() + " w_filename: " + worker.getW_filename());
			// 将文件剪切到服务器目录
			w_pic.renameTo(new File(filePath + File.separator + dir, fileName));
		}
		
		int res = service.modifyWorker(worker);
		if(res > 0) {
			return SUCCESS;
		} else {
			return null;
		}
	}
	
	//根据条件筛选员工
	private String hasPic;
	public String listWorkerByCondition() throws Exception{
		System.out.println("worker: " + worker);
		workers = service.findWorkerByCondition(worker.getW_type(), worker.getW_sex(), hasPic);
		return SUCCESS;
	}
	
	//移动端登录
	private JSONObject loginJson;
	public String mobileLogin() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		//获取前台提交的json数据
		HttpServletRequest request = ServletActionContext.getRequest();
		String jsonStr = new GetRequestJsonStr().getJsonStr(request);
		System.out.println("jsonStr in action: " + jsonStr);
		JSONObject jsonRequest = JSONObject.fromObject(jsonStr);
		String w_id = jsonRequest.getString("w_id");
		String w_password = jsonRequest.getString("w_password");
		
		//返回给前台，代表操作结果的标识，300失败/200成功
		int code = 0;
		Worker serviceWorker = service.login(w_id, w_password);
		Map<String, Object> map = new HashMap<>();
		if(serviceWorker == null) {
			code = 300;
		} else {
			map.put("w_id", serviceWorker.getW_id());
			map.put("w_password", serviceWorker.getW_password());
			map.put("w_name", serviceWorker.getW_name());
			code = 200;
		}
		//返回json数据给前台
		map.put("code", code);
		loginJson = JSONObject.fromObject(map);
		System.out.println("jsonReturn String: " + loginJson.toString());
		return SUCCESS;
	}
	
	//移动端个人中心
	private JSONObject workerInfoJson;
	public String moblieWorkerInfo(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();
		String jsonStr = new GetRequestJsonStr().getJsonStr(request);
		JSONObject jsonRequest = JSONObject.fromObject(jsonStr);
		String w_id = jsonRequest.getString("w_id");
		int code = 0;
		Worker serviceWorker = service.findWorkerById(w_id);
		Map<String, Object> map = new HashMap<>();
		if(serviceWorker != null) {
			map.put("w_id", serviceWorker.getW_id());
			map.put("w_password", serviceWorker.getW_password());
			map.put("w_name", serviceWorker.getW_name());
			map.put("w_sex", serviceWorker.getW_sex());
			map.put("w_type", serviceWorker.getW_type());
			map.put("w_workTime", serviceWorker.getW_workTime());
			//图片转为base64
			byte[] bytes = null;
			String picStr = "";
			try {
				String picPath = ServletActionContext.getServletContext().getRealPath("/pictures" + File.separator + serviceWorker.getW_type() + File.separator + serviceWorker.getW_filename());
				System.out.println("picPath: " + picPath);
				File picFile = new File(picPath);
				System.out.println("picFile: " + picFile);
				InputStream inputStream = new FileInputStream(picFile);
				System.out.println("inputStream: " + inputStream);
				bytes = new byte[inputStream.available()];
				inputStream.read(bytes);
				inputStream.close();
				BASE64Encoder encoder = new BASE64Encoder();
				picStr = encoder.encode(bytes);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			map.put("w_pic", picStr);
			
			code = 200;
		} else {
			code = 300;
		}
		map.put("code", code);
		workerInfoJson = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public File getW_pic() {
		return w_pic;
	}

	public void setW_pic(File w_pic) {
		this.w_pic = w_pic;
	}

	public String getW_picFileName() {
		return w_picFileName;
	}

	public void setW_picFileName(String w_picFileName) {
		this.w_picFileName = w_picFileName;
	}

	public List<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
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
	
	public String getHasPic() {
		return hasPic;
	}

	public void setHasPic(String hasPic) {
		this.hasPic = hasPic;
	}

	public JSONObject getLoginJson() {
		return loginJson;
	}

	public void setLoginJson(JSONObject loginJson) {
		this.loginJson = loginJson;
	}

	public JSONObject getWorkerInfoJson() {
		return workerInfoJson;
	}

	public void setWorkerInfoJson(JSONObject workerInfoJson) {
		this.workerInfoJson = workerInfoJson;
	}
	
	
}
