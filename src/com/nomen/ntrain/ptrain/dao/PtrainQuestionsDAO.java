package com.nomen.ntrain.ptrain.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainQuestionsBean;

/**
 * @description 莆田岗位培训_试题数据库操作接口
 * @author 林木山
 * @date 2014-3-5
 */
public interface PtrainQuestionsDAO {

	/**
	 * 查找莆田岗位培训_试题列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainQuestionsBean> findPtrainQuestionsList(Map map,int page,int record);

	/**
	 * 查找莆田岗位培训_试题信息
	 * @param id
	 * @return
	 */
	public PtrainQuestionsBean findPtrainQuestionsBeanById(String id);

	/**
	 * 添加莆田岗位培训_试题信息
	 * @param ptrainQuestionsBean
	 * @return
	 */
	public String insertPtrainQuestions(PtrainQuestionsBean ptrainQuestionsBean);

	/**
	 * 添加莆田岗位培训_试题排序号
	 * @param ptrainQuestionsBean
	 * @return
	 */
	public String findPtrainQuestionsSortnum(Map map);

	/**
	 * 更新莆田岗位培训_试题信息
	 * @param ptrainQuestionsBean
	 * @return
	 */
	public void updatePtrainQuestions(PtrainQuestionsBean ptrainQuestionsBean);

	/**
	 * 删除莆田岗位培训_试题信息
	 * @param id
	 * @return
	 */
	public void deletePtrainQuestionsById(String id);

	/**
	 * 删除莆田岗位培训_试题信息
	 * @param unitid,kindid
	 * @return
	 */
	public void deletePtrainQuestionsByMap(Map map);
	
	/**
	 * 更新莆田岗位培训_试题信息[启用、禁止]
	 */
	public void updatePtrainQuestionsUsesign(PtrainQuestionsBean ptrainQuestionsBean);

}
