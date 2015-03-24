package com.nomen.ntrain.ptrain.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainReqBean;
import com.nomen.ntrain.ptrain.bean.PtrainRequserBean;

/**
 * @description 莆田岗位培训_培训需求报名数据库操作接口
 * @author 林木山
 * @date 2014-3-21
 */
public interface PtrainRequserDAO {

	/**
	 * 查找莆田岗位培训_培训需求报名列表信息[分页]
	 * @param map
	 * @return
	 */
	public List<PtrainReqBean> findPtrainRequserList(Map map,int page,int record);

	/**
	 * 查找莆田岗位培训_培训需求报名列表信息[无分页]
	 * @param map
	 * @return
	 */
	public List<PtrainReqBean> findPtrainRequserList(Map map);

	/**
	 * 查找莆田岗位培训_培训需求报名列表信息 已报名人员列表[分页]
	 * @param map
	 * @return
	 */
	public List<PtrainRequserBean> findPtrainRequserSignList(Map map,int page,int record);

	/**
	 * 查找莆田岗位培训_培训需求报名列表信息 已报名人员列表[无分页]
	 * @param map
	 * @return
	 */
	public List<PtrainRequserBean> findPtrainRequserSignList(Map map);

	/**
	 * 查找莆田岗位培训_培训需求报名信息
	 * @param id
	 * @return
	 */
	public PtrainRequserBean findPtrainRequserBeanById(String id);

	/**
	 * 添加莆田岗位培训_培训需求报名信息
	 * @param ptrainRequserBean
	 * @return
	 */
	public String insertPtrainRequser(PtrainRequserBean ptrainRequserBean);

	/**
	 * 更新莆田岗位培训_培训需求报名信息
	 * @param ptrainRequserBean
	 * @return
	 */
	public void updatePtrainRequser(PtrainRequserBean ptrainRequserBean);

	/**
	 * 删除莆田岗位培训_培训需求报名信息
	 * @param id
	 * @return
	 */
	public void deletePtrainRequserById(String id);

}
