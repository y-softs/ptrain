package com.nomen.ntrain.web.dao;

import com.nomen.ntrain.ptrain.bean.PtrainQuestionsBean;

/**
 * @description 莆田岗位培训_试题数据库操作接�?
 * @author 林木�?
 * @date 2014-3-5
 */
public interface WebQuestionsDAO {

	/**
	 * 查找莆田岗位培训_试题信息
	 * @param id
	 * @return
	 */
	public PtrainQuestionsBean findPtrainQuestionsBeanById(String id);


}
