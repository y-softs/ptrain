package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.bean.BaseUnitBean;
import com.nomen.ntrain.ptrain.bean.PtrainCodeBean;
import com.nomen.ntrain.ptrain.dao.PtrainCodeDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainCodeDAOImpl extends NsoftBaseDao implements PtrainCodeDAO {

	public List<PtrainCodeBean> findPtrainCodeList(Map map){
		return (List<PtrainCodeBean>)this.getSqlMapClientTemplate().queryForList("PtrainCode.findPtrainCodeList", map);
	}
	
	public PtrainCodeBean findPtrainCodeBeanByMap(Map map){
		return (PtrainCodeBean)this.getSqlMapClientTemplate().queryForObject("PtrainCode.findPtrainCodeBeanByMap", map);
	}

	public String findPtrainCodeInfoByMap(Map map) {
		return (String)this.getSqlMapClientTemplate().queryForObject("PtrainCode.findPtrainCodeInfoByMap", map);
	}

	public String insertPtrainCode(PtrainCodeBean ptrainCodeBean){
		ptrainCodeBean.setNature("1");
		ptrainCodeBean.setUnitid("");
		return (String)this.getSqlMapClientTemplate().insert("PtrainCode.insertPtrainCode", ptrainCodeBean);
	}

	public void updatePtrainCode(PtrainCodeBean ptrainCodeBean){
		ptrainCodeBean.setNature("1");
		ptrainCodeBean.setUnitid("");
		this.getSqlMapClientTemplate().update("PtrainCode.updatePtrainCode", ptrainCodeBean);
	}

	public void deletePtrainCode(String idStr){
		this.getSqlMapClientTemplate().delete("PtrainCode.deletePtrainCode", idStr);
	}

	public void deletePtrainCodeByMap(Map map){
		this.getSqlMapClientTemplate().delete("PtrainCode.deletePtrainCodeByMap", map);
	}
	
	public String findPtrainCodeSortnum(Map map){
		return (String) this.getSqlMapClientTemplate().queryForObject("PtrainCode.findPtrainCodeSortnum", map);
	}
	
	public String findCodeValueNum(Map map){
		return (String) this.getSqlMapClientTemplate().queryForObject("PtrainCode.findCodeValueNum", map);
	}

	public void updatePtrainCodeUsesign(PtrainCodeBean PtrainCodeBean){
		this.getSqlMapClientTemplate().update("PtrainCode.updatePtrainCodeUsesign", PtrainCodeBean);
	}

	public List<PtrainCodeBean> findPtrainCodeList_Comm(Map map) {
		return this.getSqlMapClientTemplate().queryForList("PtrainCode.findPtrainCodeList_Comm",map);
	}

	public BaseUnitBean findCenterBaseUnitBean(Map map) {
		return (BaseUnitBean)this.getSqlMapClientTemplate().queryForObject("PtrainCode.findCenterBaseUnitBean", map);
	}
}
