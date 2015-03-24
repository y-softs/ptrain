package com.nomen.ntrain.ptrain.service.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainQuestionsBean;
import com.nomen.ntrain.ptrain.dao.PtrainQuestionsDAO;
import com.nomen.ntrain.ptrain.service.PtrainQuestionsService;
@SuppressWarnings("all")
public class PtrainQuestionsServiceImpl extends BaseServiceImpl implements PtrainQuestionsService {

	private PtrainQuestionsDAO ptrainQuestionsDAO;

	public List<PtrainQuestionsBean> findPtrainQuestionsList(Map map){
		int page = Integer.parseInt((String)map.get("tagpage"));
		int record = Integer.parseInt((String)map.get("record"));
		return ptrainQuestionsDAO.findPtrainQuestionsList(map, page, record);
	}

	public PtrainQuestionsBean findPtrainQuestionsBeanById(String id){
		return ptrainQuestionsDAO.findPtrainQuestionsBeanById(id);
	}

	public String findPtrainQuestionsSortnum(Map map) {
		return ptrainQuestionsDAO.findPtrainQuestionsSortnum(map);
	}

	public void savePtrainQuestions(PtrainQuestionsBean ptrainQuestionsBean){
		if(func.IsEmpty(func.Trim(ptrainQuestionsBean.getId()))){
			ptrainQuestionsDAO.insertPtrainQuestions(ptrainQuestionsBean);
		}else{
			ptrainQuestionsDAO.updatePtrainQuestions(ptrainQuestionsBean);
		}
	}

	public void deletePtrainQuestionsById(String id){
		ptrainQuestionsDAO.deletePtrainQuestionsById(id);
	}

	public void updatePtrainQuestionsUsesign(PtrainQuestionsBean ptrainQuestionsBean) {
		ptrainQuestionsDAO.updatePtrainQuestionsUsesign(ptrainQuestionsBean);
	}

	//Get和Set方法
	public PtrainQuestionsDAO getPtrainQuestionsDAO() {
		return ptrainQuestionsDAO;
	}
	public void setPtrainQuestionsDAO(PtrainQuestionsDAO ptrainQuestionsDAO) {
		this.ptrainQuestionsDAO = ptrainQuestionsDAO;
	}
}
