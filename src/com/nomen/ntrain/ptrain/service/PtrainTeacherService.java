package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainTeacherBean;

/**
 * @description 莆田岗位培训_讲师团队业务逻辑层
 * @author 林木山
 * @date 2014-3-14
 */
public interface PtrainTeacherService {

	/**
	 * 查找莆田岗位培训_讲师团队列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainTeacherBean> findPtrainTeacherList(Map map);

	/**
	 * 查找莆田岗位培训_讲师团队信息
	 * @param id
	 * @return
	 */
	public PtrainTeacherBean findPtrainTeacherBeanById(String id);

	/**
	 * 查询排序号
	 */
	public String findPtrainTeacherSortnum(Map map);

	/**
	 * 保存莆田岗位培训_讲师团队信息
	 * @param ptrainTeacherBean
	 * @return
	 */
	public void savePtrainTeacher(PtrainTeacherBean ptrainTeacherBean);

	/**
	 * 删除莆田岗位培训_讲师团队信息
	 * @param id
	 * @return
	 */
	public void deletePtrainTeacherById(String id);

}
