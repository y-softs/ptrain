package com.nomen.ntrain.base.service.implement;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nomen.ntrain.annotation.LoginEnums;
import com.nomen.ntrain.base.bean.BaseLogonBean;
import com.nomen.ntrain.base.bean.BaseOptLogBean;
import com.nomen.ntrain.base.bean.BaseUnitBean;
import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.dao.BaseOptLogDAO;
import com.nomen.ntrain.base.dao.LoginDAO;
import com.nomen.ntrain.base.service.LoginService;
import com.nomen.ntrain.base.util.CheckUserBean;
import com.nomen.ntrain.base.util.DataUserInfoBean;
import com.nomen.ntrain.common.CodeFatherUtil;
import com.nomen.ntrain.data.bean.DataCodeBean;
import com.nomen.ntrain.data.bean.DataOrganBean;
import com.nomen.ntrain.data.bean.DataUserBean;
import com.nomen.ntrain.util.Constant;
import com.nomen.ntrain.util.PubFunc;

public class LoginServiceImpl implements LoginService
{
	private LoginDAO         loginDAO;
	private BaseOptLogDAO   baseOptLogDAO ;
	@SuppressWarnings("unchecked")
	public Map findLoginUser(Map map) {
		try {
			List list = this.loginDAO.findLoginUser(map);
			//���ڵ�¼��Ա��Ϣʱ
			if(list.size()>0) {
				LoginBean login=(LoginBean)list.get(0);
				login.setEnterunitid(login.getUnitid());
				Map loginMap=new HashMap();
				loginMap.put(Constant.LOGIN_PARAM, login);
				login = null;
				list = null;
				return loginMap;
			}
			return null;
		} catch(Exception ex) {
			return null;
		}
	}
	
	public List<BaseUnitBean> findAllUseUnit() {
		return this.loginDAO.findAllUseUnit();
	}
	
	public List<BaseUnitBean> findOwnUnitListById(String unitId) {
		return this.loginDAO.findOwnUnitListById(unitId);
	}
	
	@SuppressWarnings("unchecked")
	public Map findUnitListByUser(LoginBean login,String unitid) {
		List list=new ArrayList();
		Map map=new HashMap();
		String strUnit= login.getUnitid();
		//�ж��Ƿ�ǰ��¼��Ա�ǳ�������Ա
		//if(!Constant.SUPER_PUR.equals(login.getPurviews())) {
			//Map<String,String> unitMap=new HashMap<String,String>();
			//unitMap.put("id", login.getUnitid());
			//unitMap.put("unitname",login.getUnitname());
			//list.add(unitMap);
			//��ѯ�õ�λ�Լ����ӵ�λ
			list = loginDAO.findBaseUnitListByUnitid(login.getUnitid());
		//} else {
		//	list=loginDAO.findAllUseUnit();
		//}
		//��λ�б�
		map.put("unitlist", list);
		//��λID
		map.put("unitid", unitid==null||"".equals(unitid)?strUnit:unitid);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public Map findDeptListByUser(String unitid,String nature,String deptid,boolean isabled) {
		List list=new ArrayList();
		Map map = new HashMap();
		PubFunc func = new PubFunc();
		Map<String,String> param = new HashMap<String,String>();
		param.put("unitid", unitid);
		param.put("fatherid", CodeFatherUtil.DATA_DEPTNATURE);
		list = this.loginDAO.findAllUseNature(param);
		//������ҳ�棬δ���ݲ������ʻ���ʱ�����ȡĬ�ϵĲ�������ID
		if(func.IsEmpty(nature)&&func.IsEmpty(deptid)&&list.size()>0) {
			DataCodeBean codebean = (DataCodeBean)list.get(0);
			nature = codebean.getId();
		}
		map.put("naturelist", list);
		param.put("nature", nature);
		param.put("deptid", deptid);
		//��ѯ������
		if(!isabled) {
			param.put("id", deptid);
		}
		list = this.loginDAO.findAllUseDept(param);
		map.put("deptlist", list);
		if(list.size()>0) {
			DataOrganBean dataOrganBean = (DataOrganBean)list.get(0);
			deptid = (func.IsEmpty(deptid)?dataOrganBean.getId():deptid);
			nature = (func.IsEmpty(nature)?dataOrganBean.getNature():nature);
		}
		else{
			//deptid="-1";
			//nature="-1";
		}
		map.put("deptid", deptid);
		map.put("nature", nature);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public Map findGroupListByUser(String deptid,String groupid,boolean isabled) {
		List list=new ArrayList();
		Map map = new HashMap();
		Map<String,String> param = new HashMap<String,String>();
		if(isabled) {
			//param.put("deptid", deptid);
			param.put("fatherid", deptid);
			list = this.loginDAO.findUseGroupList(param);
			map.put("grouplist", list);
			map.put("groupid", groupid);
			if(list.size()>0 && (groupid==null || "".equals(groupid))) {
				DataOrganBean dataOrganBean = (DataOrganBean)list.get(0);
				map.put("groupid", dataOrganBean.getId());
			}
		} else {
			//param.put("id", groupid);
			//list = this.loginDAO.findAllUseGroup(param);
			param.put("fatherid", deptid);
			list = this.loginDAO.findUseGroupList(param);
			map.put("grouplist", list);
			map.put("groupid", groupid);
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public Map findGroupList(String fatherid,String groupid,boolean isabled) {
		List list=new ArrayList();
		Map map = new HashMap();
		Map<String,String> param = new HashMap<String,String>();
		if(isabled) {
			param.put("fatherid", fatherid);
			list = this.loginDAO.findUseGroupList(param);
			map.put("grouplist", list);
			map.put("groupid", groupid);
			if(list.size()>0 && (groupid==null || "".equals(groupid))) {
				DataOrganBean dataOrganBean = (DataOrganBean)list.get(0);
				map.put("groupid", dataOrganBean.getId());
			}
		} else {
			param.put("id", groupid);
			list = this.loginDAO.findUseGroupList(param);
			map.put("grouplist", list);
			map.put("groupid", groupid);
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public Map findUserList(String groupid,String userid,boolean isabled) {
		List list=new ArrayList();
		Map map = new HashMap();
		Map<String,String> param = new HashMap<String,String>();
		if(isabled) {
			param.put("groupid", groupid);
			list = this.loginDAO.findAllUserList(param);
			map.put("userlist", list);
			map.put("userid", userid);
			if(list.size()>0 && (userid==null || "".equals(userid))) {
				LoginBean loginBean = (LoginBean)list.get(0);
				map.put("userid", loginBean.getId());
			}
		} else {
			param.put("id", userid);
			list = this.loginDAO.findAllUserList(param);
			map.put("userlist", list);
			map.put("userid", userid);
		}
		return map;
	}
	
	public List<LoginBean> findAllUseUser(Map map) {
		return this.loginDAO.findAllUseUser(map);
	}
	
	public String findSystemOutStr(LoginBean login) {
		StringBuffer sb=new StringBuffer();
		String strDate=PubFunc.dateToString(new java.util.Date(), "yyyy-MM-dd HH:mm:ss");
		
		sb.append(PubFunc.formatString(strDate, "yyyy��MM��dd�� HHʱmm��ss��", "yyyy-MM-dd HH:mm:ss"));
		sb.append("    ");
		sb.append(login.getUnitname());
		sb.append("  ");
		sb.append(login.getDeptname());
		sb.append("  ");
		sb.append(login.getUsername());
		sb.append("��¼�ɹ���");
		return sb.toString();
	}
	
	public void findImageBlob(Map map,HttpServletResponse response) {
		OutputStream os = null;
		try {
			List list = this.loginDAO.findImageBlob(map);
			if(list.size() > 0) {
				DataUserBean dataUserBean = (DataUserBean)list.get(0);
				response.reset();
				response.setContentType("image/*");
				os = response.getOutputStream();
				byte[] buffer = dataUserBean.getImage();
				os.write(buffer);
				os.flush();
			}
		} catch (Exception ex) {
		} finally {
			try {
				if(os != null) {
					os.close();
				}
			}catch (IOException e) {}
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map findPeoNameSameOrNot(String unitid,String username) { 
		String splitChar = "��";
		PubFunc func = new PubFunc();
		//������Ա
		StringBuffer sb =null;
		StringBuffer strBuffer =null;
		Map rtnMap = new HashMap();
		
		Map queryMap  = new HashMap();
		if(func.Trim(unitid).indexOf(",")<0){
			queryMap.put("unitid", unitid);
		}
		else{
			queryMap.put("inUnitIdStr", unitid);  //��ʾ��һ���ĵ�λ��Χ���
		}
		
		sb= new StringBuffer();
		strBuffer = new StringBuffer();

		//�����ֶλ�ȡ��Ա��ֵ
		String usernames = username;
		usernames = PubFunc.formatUsers(usernames);
		usernames = this.getNoSameString(usernames,splitChar);
		usernames = func.Replace(usernames, "��", ",");
		usernames = func.Replace(usernames, "��", ",");
		strBuffer.append(func.getStrd(usernames));
		queryMap.put("inNameStr", strBuffer.toString());
		List<CheckUserBean> userCountList = this.loginDAO.findUserNameStrCount(queryMap);
		List  sameList = new ArrayList();
		String oldUserStr = ","+usernames+",";
		for(CheckUserBean userB :userCountList){
			//ͨ����Ա��ѯ����Ա�б�
			queryMap.put("username", userB.getUsername());
			sameList.add(this.loginDAO.findUserInfoByMap(queryMap));
			sb.append("<span class=\"fontcolor_red_b\">��</span>"+userB.getUsername()+"��");
			oldUserStr = func.Replace(oldUserStr, ","+userB.getUsername()+",", ",");
		}
		
		String[] noExistsUserArr = func.StrToArray(oldUserStr, ",");
		for(int i=0;i<noExistsUserArr.length;i++){
			if(!func.IsEmpty(noExistsUserArr[i])){
				//��ʾ�޵�����Ա 
				sb.append("<span class=\"fontcolor_red\"><s>"+noExistsUserArr[i]+"</s></span>��");
			}
		}
		if(sb.toString().endsWith(splitChar))
		{
			rtnMap.put("_alluser", (sb.toString()).substring(0, sb.toString().lastIndexOf(splitChar)));
		}
		else
		{
			rtnMap.put("_alluser", "");
		}
		
		//��Աѡ���б�
		rtnMap.put("_sameuserlist", sameList);
		return rtnMap;
	}
	
	@SuppressWarnings("unchecked")
	public Map findPeoNameSameOrNot(String unitid,String fields,HttpServletRequest request) { 
		String splitChar = "��";
		PubFunc func = new PubFunc(); 
		//������Ա
		String okuser = "";
		//������Աid
		String okuserid = "";
		String error    = "0";
		//������Ա
		StringBuffer sb =null;
		StringBuffer strBuffer =null;
		//��������
		int okuserNum = 0;
		
		Map rtnMap = new HashMap();
		
		Map queryMap  = new HashMap();
		if(func.Trim(unitid).indexOf(",")<0){
			queryMap.put("unitid", unitid);
		}
		else{
			queryMap.put("inUnitIdStr", unitid);  //��ʾ��һ���ĵ�λ��Χ���
		}
		
		//Ҫ�жϵ�ҳ���ֶ�
		if(fields.startsWith(",")){
			fields = fields.substring(1);
		}
		if(fields.endsWith(",")){
			fields = fields.substring(0,fields.length()-1);
		}
		String[] fieldArr= fields.split(","); 
		for(String field:fieldArr){
			sb= new StringBuffer();
			strBuffer = new StringBuffer();
			//������Ա
			 okuser = "";
			 okuserid ="";
			 okuserNum = 0;
			//�����ֶλ�ȡ��Ա��ֵ
			String usernames = request.getParameter(field);
			usernames = PubFunc.formatUsers(usernames);
			usernames = this.getNoSameString(usernames,splitChar);
			usernames = func.Replace(usernames, "��", ",");
			usernames = func.Replace(usernames, "��", ",");
			strBuffer.append(func.getStrd(usernames));
			queryMap.put("inNameStr", strBuffer.toString());
			List<CheckUserBean> userCountList = this.loginDAO.findUserNameStrCount(queryMap);
			List  sameList = new ArrayList();
			String oldUserStr = ","+usernames+",";
			for(CheckUserBean userB :userCountList){
				if(func.Cint(userB.getUsercount())==1){
					//������Ա
					okuser +=splitChar+ userB.getUsername();
					okuserid += splitChar + userB.getId();
					okuserNum++;
					sb.append(userB.getUsername()+splitChar);
				}
				else if(func.Cint(userB.getUsercount())>1){
					error = "1";
					//ͬ����Ա 
					//ͨ����Ա��ѯ����Ա�б�
					queryMap.put("username", userB.getUsername());
					sameList.add(this.loginDAO.findUserInfoByMap(queryMap));
					sb.append("<span class=\"fontcolor_red_b\">��</span>"+userB.getUsername()+"��");
				}
				oldUserStr = func.Replace(oldUserStr, ","+userB.getUsername()+",", ",");
			}
			
			String[] noExistsUserArr = func.StrToArray(oldUserStr, ",");
			for(int i=0;i<noExistsUserArr.length;i++){
				if(!func.IsEmpty(noExistsUserArr[i])){
					//��ʾ�޵�����Ա 
					sb.append("<span class=\"fontcolor_red\"><s>"+noExistsUserArr[i]+"</s></span>��");
					error = "1";
				}
			}
			if(okuser.length()>0){
				okuser = okuser.substring(1);
			}
			if(okuserid.length()>0){
				okuserid = okuserid.substring(1);
			}
			
			if(sb.toString().endsWith(splitChar))
			{
				rtnMap.put(field+"_alluser", (sb.toString()).substring(0, sb.toString().lastIndexOf(splitChar)));
			}
			else
			{
				rtnMap.put(field+"_alluser", "");
			}
			
			//�����ֶε�������Ա
			rtnMap.put(field+"_sameuserlist", sameList);
			//�����ֶε�������Ա
			rtnMap.put(field+"_okuser", okuser);
			//�����ֶε�������Աid
			rtnMap.put(field+"_okuserid", okuserid);
			//������Ա����
			rtnMap.put(field+"_okuserNum", okuserNum);
			//��ʾ�Ƿ����쳣��Ա
			rtnMap.put(field+"_error", error);
			//��֤�ֶ�
			rtnMap.put("fields",fields);
		}  
		return rtnMap;
	}
	
	/**�����ַ��������ظ���Ԫ��
	 * tempString   --"�ã��ָ���ַ���"
	 * @param tempString
	 * @return
	 */
	private String getNoSameString(String tempString,String splitChar)
	{ 
		String emp="";
		if(tempString.length()==0) {
			return "";
		}
		if(tempString.indexOf(splitChar)<0) {
			emp = tempString;
			return emp;
		} else {
			String[] array = tempString.split(splitChar);
			for(int i=0;i<array.length;i++)  
			{ 
				String temp = array[i]; 
				for(int j=i+1;j<array.length;j++) { 
					if(temp.equals(array[j])) { 
						array[i] = ""; 
					} 
				}
				if(!array[i].equals("")) {
						emp += splitChar+array[i];
				}
			}
			if(emp.length()>0) {
				emp=  emp.substring(1);
			}
			return emp;
		}
	}

	public void insertLoginLog(LoginBean loginBean, LoginEnums logEnums) {
		if(null == loginBean) return ;
        //�����¼��־
        BaseOptLogBean baseOptLogBean = new BaseOptLogBean(); 
        baseOptLogBean.setOptremark("");
        baseOptLogBean.setOpttype(logEnums.getKey()+"");
        baseOptLogBean.setArgvalues("");
        baseOptLogBean.setOptuserid(loginBean.getId());
        baseOptLogBean.setOptusername(loginBean.getUsername());
        baseOptLogBean.setOptdeptid(loginBean.getDeptid());
        baseOptLogBean.setOptdeptname(loginBean.getDeptname());
        baseOptLogBean.setIntflag("0");
        this.baseOptLogDAO.insertBaseOptLog(baseOptLogBean);
	
}
	public void updatePassword(LoginBean loginBean) {
		this.loginDAO.updatePassword(loginBean);
	}
	public List findDeptNatureList(Map map) { 
		return this.loginDAO.findAllUseNature(map);
	}
	
	public List findDeptListByUnitId(String unitId){
		Map m = (new HashMap());
		m.put("unitid",unitId);
		return this.loginDAO.findAllUseDept(m);
	}
	
	public List findDeptListByMap(Map map) { 
		return this.loginDAO.findAllUseDept(map);
	}

	public List findGroupListByMap(Map map) {  
		return this.loginDAO.findUseGroupList(map);
	}

	public DataOrganBean findDataOrganById(String id) {
		return this.loginDAO.findDataOrganById(id);
	}
	
	public List findBaseUnitListByUnitid(String fatherid) {
		return this.loginDAO.findBaseUnitListByUnitid(fatherid);
	}

	public String findDataUserPurByUserId(String id) {
		return this.loginDAO.findDataUserPurByUserId(id);
	}

	public DataUserInfoBean Comm_findDataUserInfoById(String id) {
		return this.loginDAO.findDataUserInfoById(id); 
	}

	public List<BaseUnitBean> findBigUnitList() {
		return this.loginDAO.findBigUnitList(); 
	}
	
	public List<BaseUnitBean> findSmallUnitList(){
		return this.loginDAO.findSmallUnitList(); 
	}

	public List<BaseUnitBean> findChildUnitList(String unitId){
		return this.loginDAO.findChildUnitList(unitId); 
	}
	
	public List<CheckUserBean> findUserNameStrCount(Map map) {
		return this.loginDAO.findUserNameStrCount(map);
	}
	public List findDateUserByUserids(String userids) {
		return this.loginDAO.findDateUserByUserids(userids);
	}

	public List<CheckUserBean> findUserInfoByMap(Map map) {
		return this.loginDAO.findUserInfoByMap(map);
	}
	//����Ϊget��set����
	public LoginDAO getLoginDAO() {
		return loginDAO;
	}
	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public BaseOptLogDAO getBaseOptLogDAO() {
		return baseOptLogDAO;
	}

	public void setBaseOptLogDAO(BaseOptLogDAO baseOptLogDAO) {
		this.baseOptLogDAO = baseOptLogDAO;
	}

	
}