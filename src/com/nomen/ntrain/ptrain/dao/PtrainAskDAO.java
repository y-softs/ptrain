package com.nomen.ntrain.ptrain.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainAskBean;

/**
 * @description 莆田岗位培训_每日三问数据库操作接口
 * @author 林木山
 * @date 2014-3-24
 */
public interface PtrainAskDAO {

	/**
	 * 查找每日三问 累计答题次数/得分
	 * @param map
	 * @return
	 */
	public PtrainAskBean findPtrainAskStat(Map map);
	
	/**
	 * 查找每日三问 未完成答题三问主记录ID
	 * @param map
	 * @return
	 */
	public String findPtrainAskNoAskid(Map map);

	/**
	 * 查找每日三问 出题轮次
	 */
	public PtrainAskBean findPtrainAskPushnum(Map map);

	/**
	 * 查找每日三问 已答题日期
	 */
	public List<PtrainAskBean> findPtrainAskSubday(Map map);

	/**
	 * 查找莆田岗位培训_每日三问信息
	 * @param id
	 * @return
	 */
	public PtrainAskBean findPtrainAskBeanById(String id);

	/**
	 * 添加莆田岗位培训_每日三问信息
	 * @param ptrainAskBean
	 * @return
	 */
	public String insertPtrainAsk(PtrainAskBean ptrainAskBean);

	/**
	 * 更新莆田岗位培训_每日三问信息
	 * @param ptrainAskBean
	 * @return
	 */
	public void updatePtrainAsk(PtrainAskBean ptrainAskBean);

	/**
	 * 删除莆田岗位培训_每日三问信息
	 * @param id
	 * @return
	 */
	public void deletePtrainAskById(String id);
	/**
	 * =====================查询统计相关===================
	 */
	/**
	 * 查询统计_每日三问情况_应用统计(按部门) 列表信息
	 */
	public List<Map> findPtrainAskAppStat(Map map);
	/**
	 * 查询统计_每日三问情况_应用统计（按专业） 列表信息
	 */
	public List<Map> findPtrainAskAppStatSpec(Map map);
	/**
	 * 查询统计_每日三问情况_应用统计 答题人员列表[分页]
	 */
	public List<Map> findPtrainAskUser(Map map,int page,int record);
	/**
	 * 查询统计_每日三问情况_应用统计 答题人员列表[无分页]
	 * 查询统计_每日三问情况_英雄榜单 列表信息[无分页]
	 */
	public List<Map> findPtrainAskUser(Map map);

}
