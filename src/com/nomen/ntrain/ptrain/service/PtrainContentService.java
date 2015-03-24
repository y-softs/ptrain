package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainContentBean;

/**
 * @description 莆田岗位培训_资源表业务逻辑层
 * @author 林木山
 * @date 2014-11-17
 */
public interface PtrainContentService {

	/**
	 * 查找莆田岗位培训_资源表列表信息[分页]
	 * @param map
	 * @return
	 */
	public List<PtrainContentBean> findPtrainContentList(Map map);
	
	/**
	 * 查找莆田岗位培训_资源表列表信息[无分页]
	 * @param map
	 * @return
	 */
	public List<PtrainContentBean> findPtrainContentListNoPage(Map map);

	/**
	 * 查找莆田岗位培训_资源表信息
	 * @param id
	 * @return
	 */
	public PtrainContentBean findPtrainContentBeanById(String id);

	/**
	 * 保存莆田岗位培训_资源表信息
	 * @param ptrainContentBean
	 * @return
	 */
	public void savePtrainContent(PtrainContentBean ptrainContentBean);

	/**
	 * 删除莆田岗位培训_资源表信息
	 * @param id
	 * @return
	 */
	public void deletePtrainContent(String id,String fatherPath);
	
	/**
	 * 查询排序号
	 * @param map：kind，类型
	 */
	public String findPtrainContentSortnum(Map map);
	
	/**
	 * 莆田岗位培训_资源表_申报/撤回[单个、批量]
	 * 莆田岗位培训_资源表表_审核/退回[单个、批量]
	 */
	public void updatePtrainContentChk(Map map);

}