package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainFlowBean;

/**
 * @description 莆田岗位学习_审核流程业务逻辑层
 * @author 林木山
 * @date 2014-3-14
 */
public interface PtrainFlowService {

	/**
	 * 查找莆田岗位学习_审核流程列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainFlowBean> findPtrainFlowList(Map map);

	/**
	 * 保存莆田岗位学习_审核流程信息
	 * @param ptrainFlowBean
	 * @return
	 */
	public void insertPtrainFlow(PtrainFlowBean ptrainFlowBean);

	/**
	 * 删除莆田岗位学习_审核流程信息
	 * @param id
	 * @return
	 */
	public void deletePtrainFlowByMap(Map map);

}
