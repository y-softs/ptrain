package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainQuestionsBean;

/**
 * @description 莆田岗位培训_试题业务逻辑层
 * @author 林木山
 * @date 2014-3-5
 */
public interface PtrainQuestionsService {

	/**
	 * 查找莆田岗位培训_试题列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainQuestionsBean> findPtrainQuestionsList(Map map);

	/**
	 * 查找莆田岗位培训_试题信息
	 * @param id
	 * @return
	 */
	public PtrainQuestionsBean findPtrainQuestionsBeanById(String id);

	/**
	 * 保存莆田岗位培训_试题信息
	 * @param ptrainQuestionsBean
	 * @return
	 */
	public void savePtrainQuestions(PtrainQuestionsBean ptrainQuestionsBean);

	/**
	 * 添加莆田岗位培训_试题排序号
	 * @param ptrainQuestionsBean
	 * @return
	 */
	public String findPtrainQuestionsSortnum(Map map);

	/**
	 * 删除莆田岗位培训_试题信息
	 * @param id
	 * @return
	 */
	public void deletePtrainQuestionsById(String id);
	/**
	 * 更新莆田岗位培训_试题信息[启用、禁止]
	 */
	public void updatePtrainQuestionsUsesign(PtrainQuestionsBean ptrainQuestionsBean);

}
