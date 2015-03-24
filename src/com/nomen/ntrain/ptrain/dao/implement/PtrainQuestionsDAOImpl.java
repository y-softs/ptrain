package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainQuestionsBean;
import com.nomen.ntrain.ptrain.dao.PtrainQuestionsDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainQuestionsDAOImpl extends NsoftBaseDao implements PtrainQuestionsDAO {

	public List<PtrainQuestionsBean> findPtrainQuestionsList(Map map,int page,int record){
		map.put("total", this.getObjectTotal("PtrainQuestions.findPtrainQuestionsList", map));
		return (List<PtrainQuestionsBean>)this.getSqlMapClientTemplate().queryForList("PtrainQuestions.findPtrainQuestionsList", map, page, record);
	}

	public PtrainQuestionsBean findPtrainQuestionsBeanById(String id){
		return (PtrainQuestionsBean)this.getSqlMapClientTemplate().queryForObject("PtrainQuestions.findPtrainQuestionsBeanById", id);
	}

	public String findPtrainQuestionsSortnum(Map map) {
		return (String)this.getSqlMapClientTemplate().queryForObject("PtrainQuestions.findPtrainQuestionsSortnum", map);
	}

	public String insertPtrainQuestions(PtrainQuestionsBean ptrainQuestionsBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainQuestions.insertPtrainQuestions", ptrainQuestionsBean);
	}

	public void updatePtrainQuestions(PtrainQuestionsBean ptrainQuestionsBean){
		this.getSqlMapClientTemplate().update("PtrainQuestions.updatePtrainQuestions", ptrainQuestionsBean);
	}

	public void deletePtrainQuestionsById(String id){
		this.getSqlMapClientTemplate().delete("PtrainQuestions.deletePtrainQuestionsById", id);
	}

	public void deletePtrainQuestionsByMap(Map map) {
		this.getSqlMapClientTemplate().delete("PtrainQuestions.deletePtrainQuestionsByMap", map);
	}

	public void updatePtrainQuestionsUsesign(PtrainQuestionsBean ptrainQuestionsBean) {
		this.getSqlMapClientTemplate().update("PtrainQuestions.updatePtrainQuestionsUsesign", ptrainQuestionsBean);
	}

}
