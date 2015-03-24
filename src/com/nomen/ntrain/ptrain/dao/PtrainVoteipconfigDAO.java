package com.nomen.ntrain.ptrain.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainVoteappBean;
import com.nomen.ntrain.ptrain.bean.PtrainVoteipconfigBean;

/**
 * @description 投票_合法IP段数据库操作接口
 * @author 林木山
 * @date 2014-12-15
 */
public interface PtrainVoteipconfigDAO {

	/**
	 * 查找投票_合法IP段列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainVoteipconfigBean> findPtrainVoteipconfigList(Map map);
	
	/**
	 * 查找投票_合法IP段Bean
	 * @param string id 主键ID
	 * @return
	 */
	public PtrainVoteipconfigBean findPtrainVoteipconfigBeanById(String id);
	
	/**
	 * 查找投票_合法IP段Str
	 * @param string appid 批次id
	 * @return
	 */
	public List<String> findPtrainVoteipconfigStrList(String appid);

	/**
	 * 添加投票_合法IP段信息
	 * @param ptrainVoteipconfigBean
	 * @return
	 */
	public String insertPtrainVoteipconfigBean(PtrainVoteipconfigBean ptrainVoteipconfigBean);

	/**
	 * 更新投票_合法IP段信息
	 * @param ptrainVoteipconfigBean
	 * @return
	 */
	public void updatePtrainVoteipconfigBean(PtrainVoteipconfigBean ptrainVoteipconfigBean);

	/**
	 * 删除投票_合法IP段信息
	 * @param id
	 * @return
	 */
	public void deletePtrainVoteipconfigBean(String id);
}