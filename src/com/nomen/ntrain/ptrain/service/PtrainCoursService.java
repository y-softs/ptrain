package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainCoursBean;

/**
 * @description 莆田岗位培训_课件资源表业务逻辑层
 * @author 林木山
 * @date 2014-11-15
 */
public interface PtrainCoursService {

	/**
	 * 查找莆田岗位培训_课件资源表列表信息[分页]
	 * @param map
	 * @return
	 */
	public List<PtrainCoursBean> findPtrainCoursList(Map map);

	/**
	 * 查找莆田岗位培训_课件资源表列表信息[无分页]
	 * @param map
	 * @return
	 */
	public List<PtrainCoursBean> findPtrainCoursListNoPage(Map map);

	/**
	 * 查找莆田岗位培训_课件资源表信息
	 * @param id
	 * @return
	 */
	public PtrainCoursBean findPtrainCoursBeanById(String id);

	/**
	 * 保存莆田岗位培训_课件资源表信息
	 * @param ptrainCoursBean
	 * @return
	 */
	public void savePtrainCours(PtrainCoursBean ptrainCoursBean);

	/**
	 * 删除莆田岗位培训_课件资源表信息
	 * @param id主键ID；delsign删除来源(1表示要删除附件)；fatherPath 文件主目录路径
	 * @return
	 */
	public void deletePtrainCours(String id,String delsign,String fatherPath);
	
	/**
	 * 莆田岗位培训_课件资源表_申报/撤回[单个、批量]
	 * 莆田岗位培训_课件资源表_审核/退回[单个、批量]
	 */
	public void updatePtrainCoursChk(Map map);

}