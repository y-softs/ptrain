package com.nomen.ntrain.data.service.implement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.data.bean.DataOrganZBean;
import com.nomen.ntrain.data.dao.DataOrganZDAO;
import com.nomen.ntrain.data.service.DataOrganZService;
import com.nomen.ntrain.util.PubFunc;

@SuppressWarnings("unchecked")
public class DataOrganZServiceImpl extends BaseServiceImpl implements DataOrganZService {
	private DataOrganZDAO   dataOrganZDAO;
	private PubFunc func = new PubFunc();
	
	public List findDataOrganZListByFatherid(Map map){
		return this.dataOrganZDAO.findDataOrganZListByFatherid(map);
	}
	
	public List findDataOrganZTreeList(Map map){
		return this.dataOrganZDAO.findDataOrganZTreeList(map);
	}

	public List findDataOrganZTreeListByIdStr(Map map) {
		return this.dataOrganZDAO.findDataOrganZTreeListByIdStr(map);
	}
	
	public DataOrganZBean findDataOrganZBeanById(String id) {
		return this.dataOrganZDAO.findDataOrganZBeanById(id);
	}
	
	public DataOrganZBean findDataOrganZTreeBeanById(String id) {
		return this.dataOrganZDAO.findDataOrganZTreeBeanById(id);
	}
	
	public String findNextSortNum(Map map) {
		String sortnum=this.dataOrganZDAO.findNextSortNum(map);
		return sortnum==null||"".equals(sortnum)?"1":sortnum;
	}
	
	public String findDeptMaxOrganZCode(String unitid,String nature,String parentid){
		Map paraMap= new HashMap();
		paraMap.put("unitid", unitid);
		paraMap.put("fatherid", parentid);
		paraMap.put("nature", nature);
		String code= this.dataOrganZDAO.findMaxOrganZCode(paraMap);
		String defaulV="01";
		if (code==null){
			return defaulV;
		}
		try{
			if (code.length()>2){
				code=code.substring(2);
			}
			int v=Integer.valueOf(code);
			v+=1;
			code= v<10 ? "0"+v : v+"";
			return code;
		}catch(NumberFormatException ex){
			return defaulV;
		}
	}
	
	public String findGroupMaxOrgCode(String unitId,String parentOrgId){
		Map paraMap= new HashMap();
		paraMap.put("unitid", unitId);
		paraMap.put("fatherid", parentOrgId);
		
		String code= this.dataOrganZDAO.findMaxOrganZCode(paraMap);
		String defaulV="01";
		if (code==null){
			return defaulV;
		}
		code=code.substring(code.lastIndexOf("-")+1);
		try{
			int v=Integer.valueOf(code);
			v+=1;
			code=v<10 ? "0"+v : v+"";
			return code;
		}catch(NumberFormatException ex){
			return defaulV;
		}
	}
	
	public String findSubGroupMaxOrgCode(String unitId,String parentOrgId){
		Map paraMap= new HashMap();
		paraMap.put("unitid", unitId);
		paraMap.put("fatherid", parentOrgId);
		
		String code= this.dataOrganZDAO.findMaxOrganZCode(paraMap);
		String defaulV="01";
		if (code==null){
			return defaulV;
		}else if (code.length()>2){
			code=code.substring(code.length()-2);
		}
		try{
			int v=Integer.valueOf(code);
			v+=1;
			code=v<10 ? "0"+v : v+"";
			return code;
		}catch(NumberFormatException ex){
			return defaulV;
		}
	}
	
	public String saveDataOrganZBean(Map map) throws Exception {
		DataOrganZBean dataOrganZBean = (DataOrganZBean) map.get("dataOrganZBean");
		String pkid = dataOrganZBean.getId();
		if(func.IsEmpty(dataOrganZBean.getId())){
			if(func.IsEmpty(dataOrganZBean.getFatherid())){
				dataOrganZBean.setFatherid("0");
			}
			pkid = this.dataOrganZDAO.insertDataOrganZBean(dataOrganZBean);
		}else{
			this.dataOrganZDAO.updateDataOrganZBean(dataOrganZBean);
		}
		return pkid;
	}
	
	public void saveDataOrganZMerge(Map map) throws Exception {
		String userid = (String) map.get("userid");
		String newdeptid = (String) map.get("newdeptid");
		String newgroupid = (String) map.get("newgroupid");
		String idstr = (String) map.get("idstr");
		DataOrganZBean dataOrganZBean = (DataOrganZBean) map.get("dataOrganZBean");
		// 更新合并机构操作的人员
		Map param = new HashMap();
		String temp = "',"+idstr+",'";
		param.put("idstr", temp);
		param.put("deptid", newdeptid);
		param.put("groupid", newgroupid);
		//更新人员部门、班组信息（操作TB_DATA_USER表）
		this.dataOrganZDAO.updateDataOrganZUserInfoByMerge(param);
		//更新合并部门机构名称
		this.dataOrganZDAO.updateDataOrganZBean(dataOrganZBean);
		//禁用合并部门机构名称
		param = new HashMap();
		param.put("usesign", "0");
		param.put("mainuser", userid);
		param.put("id", idstr);
		this.dataOrganZDAO.updateDataOrganZUseSignByMerge(param);
	}
	
	public String insertDataOrganZBean(DataOrganZBean dataOrganZBean) throws Exception {
		String pkId = this.dataOrganZDAO.insertDataOrganZBean(dataOrganZBean);
		return pkId;
	}

	public void updateDataOrganZBean(DataOrganZBean dataOrganZBean) throws Exception {
		this.dataOrganZDAO.updateDataOrganZBean(dataOrganZBean);
	}
	
	public void deleteDataOrganZBeanById(String pkId) {
		this.dataOrganZDAO.deleteDataOrganZBeanById(pkId);
	}
	
	public void updateDataOrganZUseSign(Map param) {
		this.dataOrganZDAO.updateDataOrganZUseSign(param);
	}

	public String chkDataOrganZCodeValueIsExists(Map map) {
		return this.dataOrganZDAO.chkDataOrganZCodeValueIsExists(map);
	}
	
	//get,set方法
	public DataOrganZDAO getDataOrganZDAO() {
		return dataOrganZDAO;
	}

	public void setDataOrganZDAO(DataOrganZDAO dataOrganZDAO) {
		this.dataOrganZDAO = dataOrganZDAO;
	}
	
}