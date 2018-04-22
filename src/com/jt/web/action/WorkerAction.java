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

	//��¼
	public String login() throws Exception {
		Worker serviceWorker = service.login(worker.getW_id(), worker.getW_password());
		if (serviceWorker == null) {
			addActionError("�û������������"); // ��Ӵ�����Ϣ���ڽ�����ʾ
			return INPUT;
		}
		System.out.println(serviceWorker.getW_type());
		if (!"����".equals(serviceWorker.getW_type()) && !"�곤".equals(serviceWorker.getW_type()) && !"�鳤".equals(serviceWorker.getW_type())) {
			addActionError("��û�з���Ȩ��!");
			return INPUT;
		}
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("worker", serviceWorker);
		return SUCCESS;
	}

	//ע��
	public String logout() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("worker", null);
		return SUCCESS;
	}
	
	// ���ϴ����ļ�
	private File w_pic;
	// �ļ���
	private String w_picFileName;

	// ���Ա��
	public String addWorker() throws Exception {
		if(worker.getW_id() == null || StringUtils.isBlank(worker.getW_id())){
			addActionError("id����Ϊ��");
			return INPUT;
		}
		// �ж�id�Ƿ��Ѿ�����
		Worker findWorkerById = service.findWorkerById(worker.getW_id());
		if (findWorkerById != null) {
			addActionError("��id�Ѵ���");
			return INPUT;
		}
		//������ϴ���Ƭ�򴴽���Ƭ�ļ�
		if(w_pic != null && w_picFileName != null){
			// 1.�ļ�����·��
			String filePath = ServletActionContext.getServletContext().getRealPath("/pictures");
			String dir = generateChildPath(filePath);
			// ���ɴ�������Ե��ļ���
			String fileName = TokenHelper.generateGUID() + "_" + w_picFileName;

			worker.setW_path(dir);
			worker.setW_filename(fileName); // ��������������ļ�����������Ҫ
			System.out.println("w_path: " + worker.getW_path() + " w_filename: " + worker.getW_filename());
			// ���ļ����е�������Ŀ¼
			w_pic.renameTo(new File(filePath + File.separator + dir, fileName));
		}
		int res = service.addWorker(worker);
		if (res > 0) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	// ����һ���Ը�λ��Ϊ�ļ������ļ���
	private String generateChildPath(String filePath) {
		String dir = worker.getW_type();
		File file = new File(filePath, dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		System.out.println("dir: " + dir);
		return dir;
	}

	// ��ѯ�����û�
	// list�������ڴ�Ų�ѯ�õ������ݣ����ҷ���ValueStack�й�jsp��ȡ
	List<Worker> workers;

	public String listAllWorker() throws Exception {
		workers = service.findAllWorker();
		return SUCCESS;
	}

	// ��ʾ����Ա������ϸ��Ϣ
	public String viewWorker() throws Exception {
		worker = service.findWorkerById(worker.getW_id());
		// ѹ��ջ��
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(worker);
		return SUCCESS;
	}
	
	// Ա����Ƭ����
	private InputStream inputStream;
	private String oldFileName; //ԭʼ�ļ���
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
	
	//Ա����Ϣ��ͼƬ����ʾ
	public String workerPicShow() throws Exception {
		//���ͼƬ��ʵ·��
		Worker dbWorker = service.findWorkerById(worker.getW_id());
		String filePath = ServletActionContext.getServletContext().getRealPath("/pictures" + File.separator + dbWorker.getW_path() + File.separator + dbWorker.getW_filename());
		FileInputStream fileInputStream = new FileInputStream(filePath);
		// ��ȡ�����
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
		
	//ɾ��Ա��
	public String deleteWorker() throws Exception{
		int res = service.removeWorker(worker.getW_id());
		if(res > 0) {			
			return SUCCESS;
		} else {
			return null;
		}
	}
	
	//��ʾ�޸�Ա������
	public String editWorkerUI() throws Exception{
		worker = service.findWorkerById(worker.getW_id());
		// ѹ��ջ��
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(worker);
		return SUCCESS;
	}
	
	//�޸�Ա��
	public String editWorker() throws Exception {
		if(w_pic == null){
			//�û�û�������ϴ���Ƭ
			//��ǰVauleStack�е�workerû����Ƭ��Ϣ��ֻ�б��е�����
			Worker dbWorker = service.findWorkerById(worker.getW_id());
			worker.setW_path(dbWorker.getW_path());
			worker.setW_filename(dbWorker.getW_filename());
		} else {
			//�û������ϴ���Ƭ
			// 1.�ļ�����·��
			String filePath = ServletActionContext.getServletContext().getRealPath("/pictures");
			String dir = generateChildPath(filePath);
			// ���ɴ�������Ե��ļ���
			String fileName = TokenHelper.generateGUID() + "_" + w_picFileName;

			worker.setW_path(dir);
			worker.setW_filename(fileName); // ��������������ļ�����������Ҫ
			System.out.println("w_path: " + worker.getW_path() + " w_filename: " + worker.getW_filename());
			// ���ļ����е�������Ŀ¼
			w_pic.renameTo(new File(filePath + File.separator + dir, fileName));
		}
		
		int res = service.modifyWorker(worker);
		if(res > 0) {
			return SUCCESS;
		} else {
			return null;
		}
	}
	
	//��������ɸѡԱ��
	private String hasPic;
	public String listWorkerByCondition() throws Exception{
		System.out.println("worker: " + worker);
		workers = service.findWorkerByCondition(worker.getW_type(), worker.getW_sex(), hasPic);
		return SUCCESS;
	}
	
	//�ƶ��˵�¼
	private JSONObject loginJson;
	public String mobileLogin() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		//��ȡǰ̨�ύ��json����
		HttpServletRequest request = ServletActionContext.getRequest();
		String jsonStr = new GetRequestJsonStr().getJsonStr(request);
		System.out.println("jsonStr in action: " + jsonStr);
		JSONObject jsonRequest = JSONObject.fromObject(jsonStr);
		String w_id = jsonRequest.getString("w_id");
		String w_password = jsonRequest.getString("w_password");
		
		//���ظ�ǰ̨�������������ı�ʶ��300ʧ��/200�ɹ�
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
		//����json���ݸ�ǰ̨
		map.put("code", code);
		loginJson = JSONObject.fromObject(map);
		System.out.println("jsonReturn String: " + loginJson.toString());
		return SUCCESS;
	}
	
	//�ƶ��˸�������
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
			//ͼƬתΪbase64
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
