package com.nomen.ntrain.ptrain.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainTeacherBean;

/**
 * @description 莆田岗位培训_讲师团队数据库操作接口
 * @author 林木山
 * @date 2014-3-14
 */
public interface PtrainTeacherDAO {

	/**
	 * 查找莆田岗位培训_讲师团队列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainTeacherBean> findPtrainTeacherList(Map map,int page,int record);

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
	 * 添加莆田岗位培训_讲师团队信息
	 * @param ptrainTeacherBean
	 * @return
	 */
	public String insertPtrainTeacher(PtrainTeacherBean ptrainTeacherBean);

	/**
	 * 更新莆田岗位培训_讲师团队信息
	 * @param ptrainTeacherBean
	 * @return
	 */
	public void updatePtrainTeacher(PtrainTeacherBean ptrainTeacherBean);

	/**
	 * 删除莆田岗位培训_讲师团队信息
	 * @param id
	 * @return
	 */
	public void deletePtrainTeacherById(String id);

}
