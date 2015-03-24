package com.nomen.ntrain.ptrain.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainQuestionstempBean;

/**
 * @description 莆田岗位培训_试题临时表数据库操作接口
 * @author 林木山
 * @date 2014-3-5
 */
public interface PtrainQuestionstempDAO {

	/**
	 * 查找莆田岗位培训_试题临时表列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainQuestionstempBean> findPtrainQuestionstempList(Map map,int page,int record);

	/**
	 * 查找莆田岗位培训_试题临时表信息
	 * @param id
	 * @return
	 */
	public PtrainQuestionstempBean findPtrainQuestionstempBeanById(String id);

	/**
	 * 添加莆田岗位培训_试题临时表信息
	 * @param ptrainQuestionstempBean
	 * @return
	 */
	public String insertPtrainQuestionstemp(PtrainQuestionstempBean ptrainQuestionstempBean);

	/**
	 * 更新莆田岗位培训_试题临时表信息
	 * @param ptrainQuestionstempBean
	 * @return
	 */
	public void updatePtrainQuestionstemp(PtrainQuestionstempBean ptrainQuestionstempBean);

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
	 * 更新莆田岗位培训_试题临时表信息[试题ID]
	 * @param unitid
	 * @return
	 */
	public void updatePtrainQuestionstempTypeid(Map map);

	/**
	 * 更新莆田岗位培训_试题临时表信息[异常处理]
	 * @param unitid
	 * @return
	 */
	public void updatePtrainQuestionstempExc(Map map);

	/**
	 * 试题 临时表导入到主表
	 * @param unitid
	 * @return
	 */
	public void insertPtrainQuesExcel(Map map);
	
	/**
	 * 更新莆田岗位培训_试题临时表信息[已导标志]
	 */
	public void updatePtrainQuestionstempDatasign(Map map);

}
