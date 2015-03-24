package com.nomen.ntrain.ptrain.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainFlowBean;

/**
 * @description 莆田岗位学习_审核流程数据库操作接口
 * @author 林木山
 * @date 2014-3-14
 */
public interface PtrainFlowDAO {

	/**
	 * 查找莆田岗位学习_审核流程列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainFlowBean> findPtrainFlowList(Map map);
	/**
	 * 添加莆田岗位学习_审核流程信息
	 * @param ptrainFlowBean
	 * @return
	 */
	public String insertPtrainFlow(PtrainFlowBean ptrainFlowBean);

	/**
	 * 删除莆田岗位学习_审核流程信息
	 * @param map
	 * @return
	 */
	public void deletePtrainFlowByMap(Map map);

}
