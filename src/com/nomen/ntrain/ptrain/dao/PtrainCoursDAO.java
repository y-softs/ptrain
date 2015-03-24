package com.nomen.ntrain.ptrain.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainCoursBean;

/**
 * @description 莆田岗位培训_课件资源表数据库操作接口
 * @author 林木山
 * @date 2014-11-15
 */
public interface PtrainCoursDAO {

	/**
	 * 查找莆田岗位培训_课件资源表列表信息[分页]
	 * @param map
	 * @return
	 */
	public List<PtrainCoursBean> findPtrainCoursList(Map map,int page,int record);

	/**
	 * 查找莆田岗位培训_课件资源表列表信息[无分页]
	 * @param map
	 * @return
	 */
	public List<PtrainCoursBean> findPtrainCoursList(Map map);

	/**
	 * 查找莆田岗位培训_课件资源表信息
	 * @param id
	 * @return
	 */
	public PtrainCoursBean findPtrainCoursBeanById(String id);

	/**
	 * 添加莆田岗位培训_课件资源表信息
	 * @param ptrainCoursBean
	 * @return
	 */
	public String insertPtrainCours(PtrainCoursBean ptrainCoursBean);

	/**
	 * 更新莆田岗位培训_课件资源表信息
	 * @param ptrainCoursBean
	 * @return
	 */
	public void updatePtrainCours(PtrainCoursBean ptrainCoursBean);

	/**
	 * 删除莆田岗位培训_课件资源表信息
	 * @param id
	 * @return
	 */
	public void deletePtrainCoursById(String id);

}