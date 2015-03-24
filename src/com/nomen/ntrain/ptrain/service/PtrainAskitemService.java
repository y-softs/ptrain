package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.nomen.ntrain.ptrain.bean.PtrainAskitemBean;

/**
 * @description 莆田岗位培训_每日三问答题表业务逻辑层
 * @author 林木山
 * @date 2014-3-24
 */
public interface PtrainAskitemService {

	/**
	 * 查找莆田岗位培训_每日三问答题表列表信息[三问答题列表]
	 * @param map
	 * @return
	 */
	public List<PtrainAskitemBean> findPtrainAskitemList(Map map);

	/**
	 * 查找莆田岗位培训_每日三问答题表列表信息[个人查询列表]
	 * @param map
	 * @return
	 */
	public List<PtrainAskitemBean> findPtrainAskitemListQue(Map map);

	/**
	 * 查找莆田岗位培训_每日三问答题表列表信息[试题浏览列表][分页]
	 * @param map
	 * @return
	 */
	public List<PtrainAskitemBean> findPtrainAskitemListSkim(Map map);

	/**
	 * 查找莆田岗位培训_每日三问答题表信息
	 * @param id
	 * @return
	 */
	public PtrainAskitemBean findPtrainAskitemBeanByMap(Map map);

	/**
	 * 新增莆田岗位培训_每日三问答题表信息
	 */
	public void insertPtrainAskitem(Map map);

	/**
	 * 修改莆田岗位培训_每日三问答题表信息
	 */
	public Map updatePtrainAskitem(Map map);

	/**
	 * 删除莆田岗位培训_每日三问答题表信息
	 * @param id
	 * @return
	 */
	public void deletePtrainAskitemById(String id);
	/**
	 * 莆田岗位培训_每日三问 试题浏览 excel导出
	 */
	public void savePtrainAskitemSkimExpExcel(Map map,HttpServletResponse response) throws Exception;

}
