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
			//存在登录人员信息时
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
		//判断是否当前登录人员是超级管理员
		//if(!Constant.SUPER_PUR.equals(login.getPurviews())) {
			//Map<String,String> unitMap=new HashMap<String,String>();
			//unitMap.put("id", login.getUnitid());
			//unitMap.put("unitname",login.getUnitname());
			//list.add(unitMap);
			//查询该单位以及其子单位
			list = loginDAO.findBaseUnitListByUnitid(login.getUnitid());
		//} else {
		//	list=loginDAO.findAllUseUnit();
		//}
		//单位列表
		map.put("unitlist", list);
		//单位ID
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
		//当进入页面，未传递部门性质或部门时，则获取默认的部门性质ID
		if(func.IsEmpty(nature)&&func.IsEmpty(deptid)&&list.size()>0) {
			DataCodeBean codebean = (DataCodeBean)list.get(0);
			nature = codebean.getId();
		}
		map.put("naturelist", list);
		param.put("nature", nature);
		param.put("deptid", deptid);
		//查询本部门
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
		
		sb.append(PubFunc.formatString(strDate, "yyyy年MM月dd日 HH时mm分ss秒", "yyyy-MM-dd HH:mm:ss"));
		sb.append("    ");
		sb.append(login.getUnitname());
		sb.append("  ");
		sb.append(login.getDeptname());
		sb.append("  ");
		sb.append(login.getUsername());
		sb.append("登录成功！");
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
		String splitChar = "；";
		PubFunc func = new PubFunc();
		//所有人员
		StringBuffer sb =null;
		StringBuffer strBuffer =null;
		Map rtnMap = new HashMap();
		
		Map queryMap  = new HashMap();
		if(func.Trim(unitid).indexOf(",")<0){
			queryMap.put("unitid", unitid);
		}
		else{
			queryMap.put("inUnitIdStr", unitid);  //表示在一定的单位范围里边
		}
		
		sb= new StringBuffer();
		strBuffer = new StringBuffer();

		//根据字段获取人员的值
		String usernames = username;
		usernames = PubFunc.formatUsers(usernames);
		usernames = this.getNoSameString(usernames,splitChar);
		usernames = func.Replace(usernames, "；", ",");
		usernames = func.Replace(usernames, "；", ",");
		strBuffer.append(func.getStrd(usernames));
		queryMap.put("inNameStr", strBuffer.toString());
		List<CheckUserBean> userCountList = this.loginDAO.findUserNameStrCount(queryMap);
		List  sameList = new ArrayList();
		String oldUserStr = ","+usernames+",";
		for(CheckUserBean userB :userCountList){
			//通过人员查询出人员列表
			queryMap.put("username", userB.getUsername());
			sameList.add(this.loginDAO.findUserInfoByMap(queryMap));
			sb.append("<span class=\"fontcolor_red_b\">！</span>"+userB.getUsername()+"；");
			oldUserStr = func.Replace(oldUserStr, ","+userB.getUsername()+",", ",");
		}
		
		String[] noExistsUserArr = func.StrToArray(oldUserStr, ",");
		for(int i=0;i<noExistsUserArr.length;i++){
			if(!func.IsEmpty(noExistsUserArr[i])){
				//表示无档案人员 
				sb.append("<span class=\"fontcolor_red\"><s>"+noExistsUserArr[i]+"</s></span>；");
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
		
		//人员选择列表
		rtnMap.put("_sameuserlist", sameList);
		return rtnMap;
	}
	
	@SuppressWarnings("unchecked")
	public Map findPeoNameSameOrNot(String unitid,String fields,HttpServletRequest request) { 
		String splitChar = "；";
		PubFunc func = new PubFunc(); 
		//正常人员
		String okuser = "";
		//正常人员id
		String okuserid = "";
		String error    = "0";
		//所有人员
		StringBuffer sb =null;
		StringBuffer strBuffer =null;
		//正常人数
		int okuserNum = 0;
		
		Map rtnMap = new HashMap();
		
		Map queryMap  = new HashMap();
		if(func.Trim(unitid).indexOf(",")<0){
			queryMap.put("unitid", unitid);
		}
		else{
			queryMap.put("inUnitIdStr", unitid);  //表示在一定的单位范围里边
		}
		
		//要判断的页面字段
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
			//正常人员
			 okuser = "";
			 okuserid ="";
			 okuserNum = 0;
			//根据字段获取人员的值
			String usernames = request.getParameter(field);
			usernames = PubFunc.formatUsers(usernames);
			usernames = this.getNoSameString(usernames,splitChar);
			usernames = func.Replace(usernames, "；", ",");
			usernames = func.Replace(usernames, "；", ",");
			strBuffer.append(func.getStrd(usernames));
			queryMap.put("inNameStr", strBuffer.toString());
			List<CheckUserBean> userCountList = this.loginDAO.findUserNameStrCount(queryMap);
			List  sameList = new ArrayList();
			String oldUserStr = ","+usernames+",";
			for(CheckUserBean userB :userCountList){
				if(func.Cint(userB.getUsercount())==1){
					//正常人员
					okuser +=splitChar+ userB.getUsername();
					okuserid += splitChar + userB.getId();
					okuserNum++;
					sb.append(userB.getUsername()+splitChar);
				}
				else if(func.Cint(userB.getUsercount())>1){
					error = "1";
					//同名人员 
					//通过人员查询出人员列表
					queryMap.put("username", userB.getUsername());
					sameList.add(this.loginDAO.findUserInfoByMap(queryMap));
					sb.append("<span class=\"fontcolor_red_b\">！</span>"+userB.getUsername()+"；");
				}
				oldUserStr = func.Replace(oldUserStr, ","+userB.getUsername()+",", ",");
			}
			
			String[] noExistsUserArr = func.StrToArray(oldUserStr, ",");
			for(int i=0;i<noExistsUserArr.length;i++){
				if(!func.IsEmpty(noExistsUserArr[i])){
					//表示无档案人员 
					sb.append("<span class=\"fontcolor_red\"><s>"+noExistsUserArr[i]+"</s></span>；");
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
			
			//该类字段的重名人员
			rtnMap.put(field+"_sameuserlist", sameList);
			//该类字段的正常人员
			rtnMap.put(field+"_okuser", okuser);
			//该类字段的正常人员id
			rtnMap.put(field+"_okuserid", okuserid);
			//正常人员人数
			rtnMap.put(field+"_okuserNum", okuserNum);
			//表示是否有异常人员
			rtnMap.put(field+"_error", error);
			//验证字段
			rtnMap.put("fields",fields);
		}  
		return rtnMap;
	}
	
	/**返回字符串中无重复的元素
	 * tempString   --"用，分割的字符串"
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
        //插入登录日志
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
	//以下为get和set方法
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