package com.nomen.ntrain.web.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainReqBean;
import com.nomen.ntrain.ptrain.bean.PtrainRequserBean;

/**
 * @description 莆田岗位培训_培训�?求报名业务�?�辑�?
 * @author 林木�?
 * @date 2014-3-21
 */
public interface WebRequserService {


	/**
	 * 员工加菜 列表[分页]
	 * @param map
	 * @return
	 */
	public List<PtrainReqBean> findPtrainReqList(Map map,int page,int record);
	/**
	 * 查找莆田岗位培训_培训�?求报名列表信�?
	 * @param map
	 * @return
	 */
	public List<PtrainReqBean> findPtrainRequserList(Map mapd);

	/**
	 * 查找莆田岗位培训_培训�?求报名列表信�? 已报名人员列�?
	 * @param map
	 * @return
	 */
	public List<PtrainRequserBean> findPtrainRequserSignList(Map map);

	/**
	 * 保存莆田岗位培训_培训�?求报名信�?
	 * @param ptrainRequserBean
	 * @return
	 */
	public void savePtrainRequser(PtrainRequserBean ptrainRequserBean);

	public PtrainReqBean findPtrainReqBeanById(String id);
	/**
	 * 删除莆田岗位培训_培训�?求报名信�?
	 * @param id
	 * @return
	 */
	public void deletePtrainRequserById(String id);
	public void deletePtrainReqById(String id);

	/**
	 * 查找莆田岗位培训_培训点菜信息
	 * @param id
	 * @return
	 */
	public PtrainReqBean findPtrainReqBeanByMap(Map map);/**
	 * 莆田岗位培训_培训点菜_申报/撤回[单个、批量]
	 * 莆田岗位培训_培训点菜_审核/退回[单个、批量]
	 */
	public void updatePtrainReqChk(Map map);

	public void savePtrainReq(PtrainReqBean ptrainReqBean);
}
