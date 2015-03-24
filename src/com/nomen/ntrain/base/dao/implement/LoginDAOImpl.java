package com.nomen.ntrain.base.dao.implement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.nomen.ntrain.base.bean.BaseLogonBean;
import com.nomen.ntrain.base.bean.BaseUnitBean;
import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.dao.LoginDAO;
import com.nomen.ntrain.base.util.CheckUserBean;
import com.nomen.ntrain.base.util.DataUserInfoBean;
import com.nomen.ntrain.common.CodeFatherUtil;
import com.nomen.ntrain.data.bean.DataCodeBean;
import com.nomen.ntrain.data.bean.DataOrganBean;
import com.nomen.ntrain.data.bean.DataUserBean;

public class LoginDAOImpl extends SqlMapClientDaoSupport implements LoginDAO 
{
	public List findLoginUser(Map map) {
		return this.getSqlMapClientTemplate().queryForList("Login.getLoginByUser", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseUnitBean> findAllUseUnit(){
		return this.getSqlMapClientTemplate().queryForList("Login.getAllUseUnit","");
	}
	
	@SuppressWarnings("unchecked")
	public List<DataCodeBean> findAllUseNature(Map map) {
		return this.getSqlMapClientTemplate().queryForList("Login.getAllUseNature", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<DataOrganBean> findAllUseDept(Map map) {
		return this.getSqlMapClientTemplate().queryForList("Login.getAllUseDept", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<DataOrganBean> findAllUseGroup(Map map) {
		return this.getSqlMapClientTemplate().queryForList("Login.getAllUseGroup", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<DataOrganBean> findUseGroupList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("Login.getUseGroupList", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<LoginBean> findAllUserList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("Login.getAllUserList", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<LoginBean> findAllUseUser(Map map) {
		String userstate = CodeFatherUtil.DATA_USERSTATEFATHER;
		map.put("userstatefather",userstate); 
		return this.getSqlMapClientTemplate().queryForList("Login.getAllUseUser", map);
	} 
	 
	@SuppressWarnings("unchecked")
	public List<DataUserBean> findImageBlob(Map map) throws Exception {
		return this.getSqlMapClientTemplate().queryForList("Login.getImageBlob", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<CheckUserBean> findUserInfoByMap(Map map) {
		return this.getSqlMapClientTemplate().queryForList("Login.getUserInfoByMap", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<CheckUserBean> findUserNameStrCount(Map map) {
		return this.getSqlMapClientTemplate().queryForList("Login.getUserNameStrCount", map);
	}
	
	public Map findAdminUser(Map map) { 
		return this.getSqlMapClientTemplate().queryForMap("Login.findAdminUser", map, "CFGNAME", "CFGVALUE");
	}
	
	public String insertBaseLogon(BaseLogonBean baseLogonBean) { 
		return (String)this.getSqlMapClientTemplate().insert("Login.insertBaseLogon", baseLogonBean); 
	}

	public void updateBaseLogon(String id) { 
		this.getSqlMapClientTemplate().update("Login.updateBaseLogon", id); 
	}
	
	public DataOrganBean findDataOrganById(String id) {
		DataOrganBean  bean =(DataOrganBean)this.getSqlMapClientTemplate().queryForObject("Login.findDataOrganById",id);
		return bean;
	}

	public List findBaseUnitListByUnitid(String fatherid) {
		return this.getSqlMapClientTemplate().queryForList("Login.findBaseUnitListByUnitid", fatherid);
	}

	public String findDataUserPurByUserId(String id) {
		return (String)this.getSqlMapClientTemplate().queryForObject("Login.findDataUserPurByUserId", id);
	}

	public DataUserInfoBean findDataUserInfoById(String id) {
		return (DataUserInfoBean)this.getSqlMapClientTemplate().queryForObject("Login.findDataUserInfoById", id);
	}

	public List<BaseUnitBean> findBigUnitList() {
		return this.getSqlMapClientTemplate().queryForList("Login.getBigUnitList");
	}

	public List<BaseUnitBean> findOwnUnitListById(String unitId) {
		return this.getSqlMapClientTemplate().queryForList("Login.findOwnUnitListById",unitId);
	}

	public List<BaseUnitBean> findChildUnitList(String unitId) {
		return this.getSqlMapClientTemplate().queryForList("Login.findChildUnitList",unitId);
	}

	public List<BaseUnitBean> findSmallUnitList() {
		String unitLev = CodeFatherUtil.BASE_XLEVEL;
		return this.getSqlMapClientTemplate().queryForList("Login.findSmallUnitList",unitLev);
	}
	public List findDateUserByUserids(String userids) {
		return this.getSqlMapClientTemplate().queryForList("DataUser.findDateUserByUserids",userids);
	}

	public List findLoginByUserSSO(Map map) {
		return this.getSqlMapClientTemplate().queryForList("Login.findLoginByUserSSO",map);
	}

	public void updatePassword(LoginBean loginBean) {
		this.getSqlMapClientTemplate().update("Login.updatePassword",loginBean);
	}
}
