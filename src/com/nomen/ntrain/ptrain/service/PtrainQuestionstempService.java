package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainQuestionstempBean;

/**
 * @description 莆田岗位培训_试题临时表业务逻辑层
 * @author 林木山
 * @date 2014-3-5
 */
public interface PtrainQuestionstempService {

	/**
	 * 查找莆田岗位培训_试题临时表列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainQuestionstempBean> findPtrainQuestionstempList(Map map);

	/**
	 * 查找莆田岗位培训_试题临时表信息
	 * @param id
	 * @return
	 */
	public PtrainQuestionstempBean findPtrainQuestionstempBeanById(String id);

	/**
	 * 保存莆田岗位培训_试题临时表信息
	 * @param ptrainQuestionstempBean
	 * @return
	 */
	public void savePtrainQuestionstemp(PtrainQuestionstempBean ptrainQuestionstempBean);

	/**
	 * 删除莆田岗位培训_试题临时表信息
	 * @param unitid,kindid
	 * @return
	 */
	public void deletePtrainQuestionstempByMap(Map map);

	/**
	 * 删除莆田岗位培训_试题临时表信息
	 * @param id
	 * @return
	 */
	public void deletePtrainQuestionstempById(String id);

	/**
	 * 保存莆田岗位培训_试题临时表信息 Excel导入
	 */
	public void savePtrainQuestionstempExcel(Map map);
	
	/**
	 * 临时表导入到主表
	 */
	public void savePrrainQuesExcel(Map map);
}
