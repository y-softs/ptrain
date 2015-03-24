package com.nomen.ntrain.web.dao.impl;

import com.nomen.ntrain.ptrain.bean.PtrainQuestionsBean;
import com.nomen.ntrain.util.NsoftBaseDao;
import com.nomen.ntrain.web.dao.WebQuestionsDAO;
@SuppressWarnings("all")
public class WebQuestionsDAOImpl extends NsoftBaseDao implements WebQuestionsDAO {


	public PtrainQuestionsBean findPtrainQuestionsBeanById(String id){
		return (PtrainQuestionsBean)this.getSqlMapClientTemplate().queryForObject("webQuestions.findPtrainQuestionsBeanById", id);
	}
}
