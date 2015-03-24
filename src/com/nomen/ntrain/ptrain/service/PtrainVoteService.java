package com.nomen.ntrain.ptrain.service;


/**
 * @description 投票管理_投票业务逻辑层
 * @author 连金亮
 * @date 2014-11-15
 */
public interface PtrainVoteService {
	
	/**
	 * 保存投票信息  1、修改资源对应的投票数=投票数+1
				2、新增投票历史记录
	   @param appId    批次ID
	   		  srcId    资源ID
	          clientIp 客户端IP
	 * @return
	 */
	public String savePtrainVote(String appId,String srcId,String clientIp);
	
	/**
	 * 统计出某个批次特定客户端IP地址投票的次数
	 * @param srcId 资源ID
	 * @param clientIp 客户端IP
	 * @return
	 */
	public int findPtrainVoteitemCount(String srcId,String clientIp);
	
	/**
	 * 统计出某个批次特定客户端IP地址投票的资源ID串
	 * @param appId 批次ID
	 * @param clientIp 客户端IP
	 * @return
	 */
	public String findPtrainVotesrcIdStr(String appId,String clientIp);
}
