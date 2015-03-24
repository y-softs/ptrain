package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;
import com.nomen.ntrain.ptrain.bean.PtrainVotesrcBean;

/**
 * @description 投票管理_投票资源业务逻辑层
 * @author 林木山
 * @date 2014-3-4
 */
public interface PtrainVotesrcService {

	/**
	 * 资源列表[非分页]
	 * @param appId 批次ID
	 * @return
	 */
	public List<PtrainVotesrcBean> findPtrainVotesrcList(String appId);

	/**
	 * 资源列表[非分页]
	 * @param appid 批次ID
	 * @return
	 */
	public List<PtrainVotesrcBean> findPtrainVotesrcListByPage(Map map,int page,int record);
	
	/**
	 * 资源列表Bean
	 * @param id 主键ID
	 * @return
	 */
	public PtrainVotesrcBean findPtrainVotesrcBean(String pkid);
	
	/**
	 * 保存资源列表Bean
	 * @param ptrainVotesrcBean
	 */
	public String savePtrainVotesrcBean(PtrainVotesrcBean ptrainVotesrcBean);
	
	/**
	 * 修改资源列表Bean
	 * @param ptrainVotesrcBean
	 */
	public void updatePtrainVotesrcBean(PtrainVotesrcBean ptrainVotesrcBean);
	
	/**
	 * 查询下一个排序号
	 * @param appid 批次ID
	 */
	public String findPtrainVotesrcNextSortnum(String appid);
	
	/**
	 * 删除投票资源表
	 * @param 
	 */
	public void deletePtrainVotesrcBean(String idstr);
}
