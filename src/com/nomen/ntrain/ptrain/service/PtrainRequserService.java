package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.nomen.ntrain.ptrain.bean.PtrainReqBean;
import com.nomen.ntrain.ptrain.bean.PtrainRequserBean;

/**
 * @description 莆田岗位培训_培训需求报名业务逻辑层
 * @author 林木山
 * @date 2014-3-21
 */
public interface PtrainRequserService {

	/**
	 * 查找莆田岗位培训_培训需求报名列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainReqBean> findPtrainRequserList(Map mapd);

	/**
	 * 查找莆田岗位培训_培训需求报名列表信息 已报名人员列表
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
	 * 保存莆田岗位培训_培训需求报名信息
	 * @param ptrainRequserBean
	 * @return
	 */
	public void savePtrainRequser(PtrainRequserBean ptrainRequserBean);

	/**
	 * 删除莆田岗位培训_培训需求报名信息
	 * @param id
	 * @return
	 */
	public void deletePtrainRequserById(String id);
	
	/**
	 * 查询统计_自助需求情况 excel导出
	 */
	public void savePtrainRequserExpExcel(Map map,HttpServletResponse response) throws Exception;
	
	/**
	 * 查询统计_自助需求情况 已报名人员 excel导出
	 */
	public void savePtrainRequserSignExpExcel(Map map,HttpServletResponse response) throws Exception;

}
