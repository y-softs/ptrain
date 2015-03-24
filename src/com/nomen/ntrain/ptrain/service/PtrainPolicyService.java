package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainPolicyBean;

/**
 * @description 莆田岗位学习_抽题策略业务逻辑层
 * @author 林木山
 * @date 2014-3-14
 */
public interface PtrainPolicyService {

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
	 * 保存莆田岗位学习_抽题策略信息
	 * @return
	 */
	public void savePtrainPolicy(PtrainPolicyBean ptrainPolicyBean);

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
