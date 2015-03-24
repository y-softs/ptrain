package com.nomen.ntrain.web.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainPolicyBean;

/**
 * @description 莆田岗位学习_抽题策略数据库操作接口
 * @author 林木山
 * @date 2014-3-14
 */
public interface WebPolicyDAO {

	/**
	 * 查找莆田岗位学习_抽题策略列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainPolicyBean> findPtrainPolicyList(Map map);
}
