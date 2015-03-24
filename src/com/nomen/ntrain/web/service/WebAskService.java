package com.nomen.ntrain.web.service;

import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainAskBean;

/**
 * @description 莆田岗位培训_每日三问业务逻辑�?
 * @author 林木�?
 * @date 2014-3-24
 */
public interface WebAskService {

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
	 * 保存莆田岗位培训_每日三问信息
	 * @param ptrainAskBean
	 * @return
	 */
	public void savePtrainAsk(PtrainAskBean ptrainAskBean);

	/**
	 * 删除莆田岗位培训_每日三问信息
	 * @param id
	 * @return
	 */
	public void deletePtrainAskById(String id);

}
