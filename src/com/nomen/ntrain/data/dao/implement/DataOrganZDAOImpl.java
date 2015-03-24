package com.nomen.ntrain.data.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.data.bean.DataOrganZBean;
import com.nomen.ntrain.data.dao.DataOrganZDAO;
import com.nomen.ntrain.util.NsoftBaseDao;

@SuppressWarnings("unchecked")
public class DataOrganZDAOImpl extends NsoftBaseDao implements DataOrganZDAO {
	/****************************************************************************/
	/******************** 机构管理 >> 机构维护 [2014-11-18]修改 ********************/
	/****************************************************************************/
	public List findDataOrganZListByFatherid(Map map) {
		return this.getSqlMapClientTemplate().queryForList("DataOrganZ.findDataOrganZListByFatherid", map);
	}
	public List findDataOrganZTreeList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("DataOrganZ.findDataOrganZTreeList", map);
	}
	public List findDataOrganZTreeListByIdStr(Map map) {
		return this.getSqlMapClientTemplate().queryForList("DataOrganZ.findDataOrganZTreeListByIdStr", map);
	}
	public DataOrganZBean findDataOrganZBeanById(String id) {
		return (DataOrganZBean)this.getSqlMapClientTemplate().queryForObject("DataOrganZ.findDataOrganZBeanById", id);
	}

	public DataOrganZBean findDataOrganZTreeBeanById(String id) {
		return (DataOrganZBean)this.getSqlMapClientTemplate().queryForObject("DataOrganZ.findDataOrganZTreeBeanById", id);
	}
	
	public String insertDataOrganZBean(DataOrganZBean dataOrganZBean) throws Exception {
		return (String)this.getSqlMapClientTemplate().insert("DataOrganZ.insertDataOrganZBean", dataOrganZBean);
	}
	
	public void updateDataOrganZUserInfoByMerge(Map map) throws Exception {
		this.getSqlMapClientTemplate().update("DataOrganZ.updateDataOrganZUserInfoByMerge", map);
	}
	
	public void updateDataOrganZBean(DataOrganZBean dataOrganZBean) throws Exception {
		this.getSqlMapClientTemplate().update("DataOrganZ.updateDataOrganZBean", dataOrganZBean);
	}

	public void updateDataOrganZUseSignByMerge(Map map) {
		this.getSqlMapClientTemplate().update("DataOrganZ.updateDataOrganZUseSignByMerge", map);
	}
	
	public void deleteDataOrganZBeanById(String id) {
		this.getSqlMapClientTemplate().delete("DataOrganZ.deleteDataOrganZBeanById", id);
	}

	public String findNextSortNum(Map map) {
		return (String)this.getSqlMapClientTemplate().queryForObject("DataOrganZ.findNextSortNum",map);
	}
	
	public String findMaxOrganZCode(Map map){
		return (String)this.getSqlMapClientTemplate().queryForObject("DataOrganZ.findMaxOrganZCode",map);
	}
	
	public void updateDataOrganZUseSign(Map map) {
		this.getSqlMapClientTemplate().update("DataOrganZ.updateDataOrganZUseSign",map);
	}
	
	public String chkDataOrganZCodeValueIsExists(Map map) {
		return (String)this.getSqlMapClientTemplate().queryForObject("DataOrganZ.chkDataOrganZCodeValueIsExists",map);
	}
}