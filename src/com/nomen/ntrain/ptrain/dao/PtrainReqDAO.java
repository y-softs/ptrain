package com.nomen.ntrain.ptrain.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainReqBean;

/**
 * @description 莆田岗位培训_培训点菜数据库操作接口
 * @author 林木山
 * @date 2014-3-14
 */
public interface PtrainReqDAO {

	/**
	 * 查找莆田岗位培训_培训点菜列表信息[分页]
	 * @param map
	 * @return
	 */
	public List<PtrainReqBean> findPtrainReqList(Map map,int page,int record);

	/**
	 * 查找莆田岗位培训_培训点菜列表信息[无分页]
	 * @param map
	 * @return
	 */
	public List<PtrainReqBean> findPtrainReqList(Map map);

	/**
	 * 查找莆田岗位培训_培训点菜信息
	 * @param id
	 * @return
	 */
	public PtrainReqBean findPtrainReqBeanById(String id);

	/**
	 * 查找莆田岗位培训_培训点菜信息
	 * @param id
	 * @return
	 */
	public PtrainReqBean findPtrainReqBeanByMap(Map map);

	/**
	 * 添加莆田岗位培训_培训点菜信息
	 * @param ptrainReqBean
	 * @return
	 */
	public String insertPtrainReq(PtrainReqBean ptrainReqBean);

	/**
	 * 更新莆田岗位培训_培训点菜信息
	 * @param ptrainReqBean
	 * @return
	 */
	public void updatePtrainReq(PtrainReqBean ptrainReqBean);

	/**
	 * 删除莆田岗位培训_培训点菜信息
	 * @param id
	 * @return
	 */
	public void deletePtrainReqById(String id);

	/**
	 * 删除莆田岗位培训_培训点菜信息
	 * @param id
	 * @return
	 */
	public void deletePtrainReqByMap(Map map);

	/**
	 * 添加莆田岗位培训_试题信息 记录复制
	 */
	public String insertPtrainReqByCopy(PtrainReqBean ptrainReqBean);

}
