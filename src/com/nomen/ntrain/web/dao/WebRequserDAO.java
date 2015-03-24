package com.nomen.ntrain.web.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainReqBean;
import com.nomen.ntrain.ptrain.bean.PtrainRequserBean;

/**
 * @description 莆田岗位培训_培训�?求报名数据库操作接口
 * @author 林木�?
 * @date 2014-3-21
 */
public interface WebRequserDAO {

	/**
	 * 查找莆田岗位培训_培训�?求报名列表信息[分页]
	 * @param map
	 * @return
	 */
	public List<PtrainReqBean> findPtrainRequserList(Map map,int page,int record);
	

	/**
	 * 员工加菜 列表[分页]
	 * @param map
	 * @return
	 */
	public List<PtrainReqBean> findPtrainReqList(Map map,int page,int record);
	/**
	 * 查找莆田岗位培训_培训�?求报名列表信息[无分页]
	 * @param map
	 * @return
	 */
	public List<PtrainReqBean> findPtrainRequserList(Map map);

	/**
	 * 查找莆田岗位培训_培训�?求报名列表信�? 已报名人员列表[分页]
	 * @param map
	 * @return
	 */
	public List<PtrainRequserBean> findPtrainRequserSignList(Map map,int page,int record);

	/**
	 * 查找莆田岗位培训_培训�?求报名列表信�? 已报名人员列表[无分页]
	 * @param map
	 * @return
	 */
	public List<PtrainRequserBean> findPtrainRequserSignList(Map map);
	public PtrainReqBean findPtrainReqBeanById(String id);


	/**
	 * 添加莆田岗位培训_培训�?求报名信�?
	 * @param ptrainRequserBean
	 * @return
	 */
	public String insertPtrainRequser(PtrainRequserBean ptrainRequserBean);

	/**
	 * 更新莆田岗位培训_培训�?求报名信�?
	 * @param ptrainRequserBean
	 * @return
	 */
	public void updatePtrainRequser(PtrainRequserBean ptrainRequserBean);
	public void updatePtrainReq(PtrainReqBean ptrainReqBean);

	/**
	 * 删除莆田岗位培训_培训�?求报名信�?
	 * @param id
	 * @return
	 */
	public void deletePtrainRequserById(String id);
	public void deletePtrainReqById(String id);
	public String insertPtrainReq(PtrainReqBean ptrainReqBean);

	/**
	 * 查找莆田岗位培训_培训点菜信息
	 * @param id
	 * @return
	 */
	public PtrainReqBean findPtrainReqBeanByMap(Map map);

}
