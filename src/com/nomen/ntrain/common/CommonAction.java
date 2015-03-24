package com.nomen.ntrain.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.util.Constant;
import com.nomen.ntrain.util.PubFile;
import com.nomen.ntrain.util.PubFunc;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @description Action������  
 * @author ������
 * @date 2011-05-20
 */

public class CommonAction extends ActionSupport 
{
	private static final long serialVersionUID=200905253423L;
	private static final Log LOG = LogFactory.getLog(CommonAction.class);
	protected static PubFunc func=null;					//������ʵ��
	@SuppressWarnings("unused")
	private static int flag=CommonAction.getInstance();
	protected File[] upfile;								//�ϴ����ļ�����
	protected String[] filename;                        //�ϴ����ļ�������
	private String tokenid;								//�ύ�쳣�ж��ֶ�
	private String tagpage;                          	//��ǰҳ��
	private String record;               				//ÿҳ��ʾ��¼��
	private String webrecord;							//ǰ̨ÿҳ��ʾ��¼��
	private String total;                              	//�ܼ�¼��
	private String unitid;								//ϵͳĬ�ϵ�λID
	private String searchColor; 
	protected CommonAction(){
	}
	
	public static synchronized int getInstance(){
		if(func==null) {
			func=new PubFunc();
		}
		return 0;
	}

	/**
	 * ��ȡ��ǰ��¼���û���Ϣ����
	 * @return
	 */
	public static LoginBean getLoginSessionBean(){
		LoginBean loginBean=(LoginBean) ActionContext.getContext().getSession().get(Constant.LOGIN_PARAM);
		return loginBean;
	}
	
	/**
	 * ִ���ϴ������������ƴ�С��
	 * @param path Ŀ¼���·��
	 * @param buffersize �����С
	 * @return
	 */
	protected String[] execUpload(String path,int buffersize) {
		try {
			//�ж��Ƿ�����ϴ��ļ�
			if(this.upfile == null) {
				return null;
			}
			//δ���û����Сʱ��ʹ��Ĭ�ϵĻ���
			if(buffersize == 0) {
				buffersize = 16*1024;
			}
			//��ʽ�����·��
			if(path.endsWith("/")) {
				path = path.substring(0,path.length()-1);
			}
			//���屣����ļ�������
			String []savename = new String[upfile.length];
			//��ȡ��������·��
			path = ServletActionContext.getServletContext().getRealPath(path);
			//����·��������ʱ������·��
			PubFile file = new PubFile(path);
			if(!file.isExists()) {
				file.createFilefolder();
			}
			//�����ļ�
			for(int i=0;i<this.upfile.length;i++) {
				InputStream in = null;
				OutputStream out = null;
				try{
					if(this.upfile[i] != null) {
						savename[i] = this.getRandomFileName(filename[i]);
						in = new BufferedInputStream(new FileInputStream(this.upfile[i]), buffersize);
						out = new BufferedOutputStream(new FileOutputStream(new File(path+"/"+savename[i])), buffersize);
						byte []buffer = new byte[buffersize];
						//��д����
						while(in.read(buffer)>0) {
							out.write(buffer);
						}
					}
				} catch (IOException ioex) {
					ioex.printStackTrace();
				} finally {
					if(in != null) {
						in.close();
					}
					if(out != null) {
						out.close();
					}
				}
			}
			return savename;
		} catch(Exception ex) {
			return null;
		}
	}

	/**
	 * ���ļ������������
	 * @param filename Դ�ļ�
	 * @return
	 */
	private String getRandomFileName(String filename) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(PubFunc.dateToString(new java.util.Date(), "yyyyMMddHHmmss"));
		sb.append((int)(10000+Math.random()*99999));
		int pos = filename.lastIndexOf(".");
		sb.append(filename.substring(pos));
		return sb.toString();
	}
	
	/**
	 * ���ҳ����ʾ��Ϣ
	 * @param sParam ��ʾ��Ϣ���� 
	 */
	protected void setActMessage(String sParam) {
		String msg="<script language=\"JavaScript\">alert(\""+this.getText(sParam)
	            +"\");</script>";
		this.addActionMessage(msg);
	}
	
	/**
	 * ���ҳ����ʾ��Ϣ
	 * @param sParam ��ʾ��Ϣ����
	 * @param script ������ʾ��Ϣ������Ľű�
	 */
	protected void setActMessage(String sParam,String script) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script language=\"JavaScript\">\n");
		if(!func.IsEmpty(sParam)) {
			sb.append(" alert(\""+this.getText(sParam)+"\");\n");
		}
		sb.append(" "+script+"\n");
		sb.append("</script>");
		this.addActionMessage(sb.toString());
	}

	/**
	 * ���ҳ����ʾ��Ϣ
	 * @param sParam ��ʾ��Ϣ����
	 * @param script ������ʾ��Ϣ������Ľű�
	 */
	protected void reloadParentPage() {
		StringBuffer sb = new StringBuffer();
		sb.append("<script language=\"JavaScript\">\n");
		sb.append(" parent.window.reloadForSubPage();parent.$.jBox.close();");
		sb.append("</script>");
		this.addActionMessage(sb.toString());
	}
	
	/**
	 * ���ҳ����ʾ��Ϣ
	 * @param sParam ��ʾ��Ϣ����
	 * @param script ������ʾ��Ϣ������Ľű�
	 */
	protected void reloadParentPage2() {
		StringBuffer sb = new StringBuffer();
		sb.append("<script language=\"JavaScript\">\n");
		sb.append(" parent.window.reloadForSubPage();");
		sb.append("</script>");
		this.addActionMessage(sb.toString());
	}
	
	/**
	 * �ж��Ƿ�Ϸ����ύ����
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected boolean isValidToken() {
		ActionContext ctx=ActionContext.getContext();
		Map session= ctx.getSession();
		String sessToken=(String)session.get("tokenid");
		//��ҳ���tokenid��session�е�tokenidһ�µĻ�����ʾ����ִ�зǷ��ύ����
		if(this.tokenid == null||func.Trim(this.tokenid).equals(sessToken)) {
			return false;
		} else {
			session.put("tokenid", func.Trim(this.tokenid));
			return true;
		}
	}
	
	
	/**
	 * ������ͻ���
	 * @param response
	 * @param info
	 * @throws IOException
	 */
	protected void print( String info){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");		
			response.getWriter().print(info);
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}

	/**
	 * ������ͻ���
	 * @param response
	 * @param info
	 * @throws IOException
	 */
	protected void printList(List list){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");	
			JSONArray json = JSONArray.fromObject(list);
			response.getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}

	/**
	 * ������ͻ���
	 * @param response
	 * @param info
	 * @throws IOException
	 */
	protected void printBean(Object obj){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");	
			JSONArray json = JSONArray.fromObject(obj);
			response.getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}
	/**
	 * ά���ˡ��������š�ά��ʱ��
	 */
	protected void getOpraterInfoIntoRequest(){
		LoginBean login=(LoginBean)ActionContext.getContext().getSession().get(Constant.LOGIN_PARAM);
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("opraName", login.getUsername());
		request.setAttribute("opraDeptName", login.getDeptname());
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		request.setAttribute("opraTime", format.format(new Date()));
		request.setAttribute("opraId",login.getId());
		
		System.out.println(format.format(new Date()));
	}
	
	/**
	 * ͨ��������ƶ�̬��ȡĳ�����ĳ���ֶζ�Ӧֵ
	 * @param fieldname
	 * @return
	 */
	public String getStaticValueByName(String fieldname){ 
		String result = null;
		Field field;
		try {
			field = CodeFatherUtil.class.getField(fieldname);
			result =  (String)field.get(fieldname);  
		} catch (Exception e) { 
			LOG.info( fieldname+ ":�þ�̬����ֵ������ �Զ���ԭ��ֵ����䣡");
			result = fieldname;
		}
		return result;
	}
	/**
	 * ��ȡ��ȷ��uri
	 * @return
	 */
	public String getRightUri(){
		String uri = ServletActionContext.getRequest().getRequestURI();  
		uri = func.Trim(uri); 
		if(uri.startsWith("/")){
			uri = uri.substring(1);
		}
		String[] param = uri.split("/");
		uri = param[0]; 
		return uri;
	}
	/** 
	 * .properties�ļ�
     * ����Key ��ȡValue
     * @param key 
     * @param path ·��
     * @return 
     */  
    public static String proVal(String key,String path) {  
        Properties props = new Properties();  
        try {  
            InputStream in = new BufferedInputStream(new FileInputStream(path));  
            props.load(in);  
            in.close();  
            String value = props.getProperty(key);  
            return value;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }
    public static void proDate(String key, String value,String path){
    	 Properties prop = new Properties();  
         try {  
             File file = new File(path);  
             if (!file.exists())  
                 file.createNewFile();  
             InputStream fis = new FileInputStream(file);  
             prop.load(fis);  
             fis.close();//һ��Ҫ���޸�ֵ֮ǰ�ر�fis  
             OutputStream fos = new FileOutputStream(path);  
             prop.setProperty(key, value);  
             prop.store(fos, "Update '" + key + "' value");  
             fos.close();  
         } catch (IOException e) {  
             System.err.println("Visit "+ path +" for updating "+ value +" value error");  
             e.printStackTrace();
         }  
    }
	/**
	 * ������ҳ�����б�
	 * list �����б�totalNum �����ܼ�¼��
	 */
	public String creItemListPage(List list,String totalNum){
		String jsonList=JSONArray.fromObject(list).toString();
		String itemList="{\"totalCount\":" +totalNum+", \"items\":"+jsonList+"}";
		return itemList;
	}
	
	//����Ϊget��set����
	public static PubFunc getFunc() {
		return func;
	}
	public String getTokenid() {
		return Constant.getTokenId();
	}
	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}
	public String getRecord() {
		if(record == null) {
			return this.getText("page.record");
		} else {
			return record;
		}
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTagpage() {
		if(tagpage == null || "0".equals(tagpage)) {
			return "1";
		} else {
			return tagpage;
		}
	}
	public void setTagpage(String tagpage) {
		this.tagpage = tagpage;
	}
	
	public String getSearchColor() {
		return searchColor;
	}

	public void setSearchColor(String searchColor) {
		this.searchColor = searchColor;
	}

	public String getWebrecord() {
		if(record == null) {
			return this.getText("page.webrecord");
		} else {
			return webrecord;
		}
	}

	public void setWebrecord(String webrecord) {
		this.webrecord = webrecord;
	}

	public String getUnitid() {
		if(unitid == null) {
			return this.getText("reg.default.unitid");
		} else {
			return unitid;
		}
	}

	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}

	public void setUpfileFileName(String[] filename) {
		this.filename = filename;
	}

	public File[] getUpfile() {
		return upfile;
	}

	public void setUpfile(File[] upfile) {
		this.upfile = upfile;
	}

		
}