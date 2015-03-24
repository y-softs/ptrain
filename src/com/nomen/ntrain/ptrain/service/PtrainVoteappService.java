package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainVoteappBean;

/**
 * @description 投票_批次业务逻辑层
 * @author 林木山
 * @date 2014-12-15
 */
public interface PtrainVoteappService {

	/**
	 * 查找投票_批次列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainVoteappBean> findPtrainVoteappList(Map map);

	/**
	 * 查找投票_批次信息
	 * @param id
	 * @return
	 */
	public PtrainVoteappBean findPtrainVoteappBeanById(String id);

	/**
	 * 保存投票_批次信息
	 * @param ptrainVoteappBean
	 * @return
	 */
	public void savePtrainVoteapp(PtrainVoteappBean ptrainVoteappBean);

	/**
	 * 删除投票_批次信息
	 * @param id
	 * @return
	 */
	public void deletePtrainVoteappById(String id);

	/**
	 * 更新 默认批次为非默认批次
	 * @param ptrainVoteappBean
	 * @return
	 */
	public void updatePtrainVoteappDefvote(PtrainVoteappBean ptrainVoteappBean);
	
	/**
	 * 获取默认的批次信息
	 * @return
	 */
	public PtrainVoteappBean findDefaultPtrainVoteappBean();

}