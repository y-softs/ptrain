package com.nomen.ntrain.ptrain.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainPolicyBean;

/**
 * @description 莆田岗位学习_抽题策略数据库操作接口
 * @author 林木山
 * @date 2014-3-14
 */
public interface PtrainPolicyDAO {

	/**
	 * 查找莆田岗位学习_抽题策略列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainPolicyBean> findPtrainPolicyList(Map map);

	/**
	 * 查找莆田岗位学习_抽题策略信息
	 * @param id
	 * @return
	 */
	public PtrainPolicyBean findPtrainPolicyBeanById(String id);

	/**
	 * 添加莆田岗位学习_抽题策略信息
	 * @param ptrainPolicyBean
	 * @return
	 */
	public String insertPtrainPolicy(PtrainPolicyBean ptrainPolicyBean);

	/**
	 * 更新莆田岗位学习_抽题策略信息
	 * @param ptrainPolicyBean
	 * @return
	 */
	public void updatePtrainPolicy(PtrainPolicyBean ptrainPolicyBean);

	/**
	 * 删除莆田岗位学习_抽题策略信息
	 * @param id
	 * @return
	 */
	public void deletePtrainPolicyById(String id);

	/**
	 * 删除莆田岗位学习_抽题策略信息
	 * @param map
	 * @return
	 */
	public void deletePtrainPolicyByMap(Map map);

}
