package com.nomen.ntrain.ptrain.dao;

import java.util.List;

import com.nomen.ntrain.ptrain.bean.PtrainVoteitemBean;

/**
 * @description 投票_投票详细表数据库操作接口
 * @author 翁杨洋
 * @date 2014-12-15
 */
public interface PtrainVoteitemDAO {

	/**
	 * 新增投票详细
	 * @param appId 批次ID
	 * @return
	 */
	public void insertPtrainVoteitemBean(PtrainVoteitemBean bean);
	
	/**
	 * 统计出某个批次特定客户端IP地址投票的次数
	 * @param appId 批次ID
	 * @param clientIp 客户端IP
	 * @return
	 */
	public int findPtrainVoteitemCount(String appId,String clientIp);
	
	/**
	 * 统计出某个批次特定客户端IP地址投票特定的资源ID的次数
	 * @param srcId 资源ID
	 * @param clientIp 客户端IP
	 * @return
	 */
	public int findPtrainVoteitemCountBySrcId(String srcId,String clientIp);
	
	/**
	 * 统计出某个批次特定客户端IP地址投票的资源ID列表
	 * @param appId 批次ID
	 * @param clientIp 客户端IP
	 * @return
	 */
	public List<String> findPtrainVotesrcIdList(String appId,String clientIp);

}
