package com.nomen.ntrain.web.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainParamBean;

/**
 * @description 莆田岗位学习_抽题策略数据库操作接�?
 * @author 林木�?
 * @date 2014-3-14
 */
public interface WebParamDAO {

	/**
	 * 查找莆田岗位学习_抽题策略列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainParamBean> findPtrainParamList(Map map);

	/**
	 * 查找莆田岗位学习_抽题策略信息
	 * @param id
	 * @return
	 */
	public PtrainParamBean findPtrainParamBeanById(String id);

	/**
	 * 添加莆田岗位学习_抽题策略信息
	 * @param ptrainParamBean
	 * @return
	 */
	public String insertPtrainParam(PtrainParamBean ptrainParamBean);

	/**
	 * 更新莆田岗位学习_抽题策略信息
	 * @param ptrainParamBean
	 * @return
	 */
	public void updatePtrainParam(PtrainParamBean ptrainParamBean);

	/**
	 * 删除莆田岗位学习_抽题策略信息
	 * @param id
	 * @return
	 */
	public void deletePtrainParamById(String id);

}
