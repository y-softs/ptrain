package com.nomen.ntrain.ptrain.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainVotesrcBean;

/**
 * @description 投票管理_投票资源表数据库操作接口
 * @author 翁杨洋
 * @date 2014-12-15
 */
public interface PtrainVotesrcDAO {

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
	 * 更新某个资源投票次数
	 * @param id
	 */
	public void updatePtrainVoteSrcVoteCount(String id);
	
	/**
	 * 新增资源列表Bean
	 * @param id 主键ID
	 * @return
	 */
	public PtrainVotesrcBean findPtrainVotesrcBean(String pkid);

	/**
	 * 新增资源列表Bean
	 * @param ptrainVotesrcBean
	 */
	public String insertPtrainVotesrcBean(PtrainVotesrcBean ptrainVotesrcBean);
	
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
