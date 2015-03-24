package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.nomen.ntrain.ptrain.bean.PtrainReqBean;

/**
 * @description 莆田岗位培训_培训点菜业务逻辑层
 * @author 林木山
 * @date 2014-3-14
 */
public interface PtrainReqService {

	/**
	 * 查找莆田岗位培训_培训点菜列表信息[分页]
	 * @param map
	 * @return
	 */
	public List<PtrainReqBean> findPtrainReqList(Map map);

	/**
	 * 查找莆田岗位培训_培训点菜列表信息[无分页]
	 * @param map
	 * @return
	 */
	public List<PtrainReqBean> findPtrainReqListNoPage(Map map);

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
	 * 保存莆田岗位培训_培训点菜信息
	 * @param ptrainReqBean
	 * @return
	 */
	public void savePtrainReq(PtrainReqBean ptrainReqBean);

	/**
	 * 删除莆田岗位培训_培训点菜信息
	 */
	public void deletePtrainReqByMap(Map map);
	
	/**
	 * 莆田岗位培训_培训点菜信息 excel导出
	 */
	public void savePtrainReqExpExcel(Map map,HttpServletResponse response) throws Exception;
	
	/**
	 * 莆田岗位培训_培训点菜_申报/撤回[单个、批量]
	 * 莆田岗位培训_培训点菜_审核/退回[单个、批量]
	 */
	public void updatePtrainReqChk(Map map);

	/**
	 * 添加莆田岗位培训_试题信息 记录复制
	 */
	public String insertPtrainReqByCopy(Map map);

}
