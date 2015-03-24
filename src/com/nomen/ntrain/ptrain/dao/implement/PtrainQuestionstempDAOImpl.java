package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainQuestionstempBean;
import com.nomen.ntrain.ptrain.dao.PtrainQuestionstempDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainQuestionstempDAOImpl extends NsoftBaseDao implements PtrainQuestionstempDAO {

	public List<PtrainQuestionstempBean> findPtrainQuestionstempList(Map map,int page,int record){
		map.put("total", this.getObjectTotal("PtrainQuestionstemp.findPtrainQuestionstempList", map));
		return (List<PtrainQuestionstempBean>)this.getSqlMapClientTemplate().queryForList("PtrainQuestionstemp.findPtrainQuestionstempList", map, page, record);
	}

	public PtrainQuestionstempBean findPtrainQuestionstempBeanById(String id){
		return (PtrainQuestionstempBean)this.getSqlMapClientTemplate().queryForObject("PtrainQuestionstemp.findPtrainQuestionstempBeanById", id);
	}

	public String insertPtrainQuestionstemp(PtrainQuestionstempBean ptrainQuestionstempBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainQuestionstemp.insertPtrainQuestionstemp", ptrainQuestionstempBean);
	}

	public void updatePtrainQuestionstemp(PtrainQuestionstempBean ptrainQuestionstempBean){
		this.getSqlMapClientTemplate().update("PtrainQuestionstemp.updatePtrainQuestionstemp", ptrainQuestionstempBean);
	}

	public void deletePtrainQuestionstempByMap(Map map) {
		this.getSqlMapClientTemplate().delete("PtrainQuestionstemp.deletePtrainQuestionstempByMap", map);
	}

	public void updatePtrainQuestionstempTypeid(Map map) {
		this.getSqlMapClientTemplate().update("PtrainQuestionstemp.updatePtrainQuestionstempTypeid", map);
	}

	public void deletePtrainQuestionstempById(String id){
		this.getSqlMapClientTemplate().delete("PtrainQuestionstemp.deletePtrainQuestionstempById", id);
	}

	public void updatePtrainQuestionstempExc(Map map) {
		this.getSqlMapClientTemplate().update("PtrainQuestionstemp.updatePtrainQuestionstempExc", map);
	}

	public void insertPtrainQuesExcel(Map map) {
		this.getSqlMapClientTemplate().insert("PtrainQuestionstemp.insertPtrainQuesExcel", map);
	}

	public void updatePtrainQuestionstempDatasign(Map map) {
		this.getSqlMapClientTemplate().update("PtrainQuestionstemp.updatePtrainQuestionstempDatasign", map);
	}

}
