package com.nomen.ntrain.ptrain.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainContentBean;

/**
 * @description 莆田岗位培训_资源表数据库操作接口
 * @author 林木山
 * @date 2014-11-17
 */
public interface PtrainContentDAO {

	/**
	 * 查找莆田岗位培训_资源表列表信息[分页]
	 * @param map
	 * @return
	 */
	public List<PtrainContentBean> findPtrainContentList(Map map,int page,int record);

	/**
	 * 查找莆田岗位培训_资源表列表信息[无分页]
	 * @param map
	 * @return
	 */
	public List<PtrainContentBean> findPtrainContentList(Map map);

	/**
	 * 查找莆田岗位培训_资源表信息
	 * @param id
	 * @return
	 */
	public PtrainContentBean findPtrainContentBeanById(String id);

	/**
	 * 添加莆田岗位培训_资源表信息
	 * @param ptrainContentBean
	 * @return
	 */
	public String insertPtrainContent(PtrainContentBean ptrainContentBean);

	/**
	 * 更新莆田岗位培训_资源表信息
	 * @param ptrainContentBean
	 * @return
	 */
	public void updatePtrainContent(PtrainContentBean ptrainContentBean);

	/**
	 * 删除莆田岗位培训_资源表信息
	 * @param id
	 * @return
	 */
	public void deletePtrainContentById(String id);
	
	/**
	 * 查询排序号
	 * @param map：kind，类型
	 */
	public String findPtrainContentSortnum(Map map);

}