package com.nomen.ntrain.web.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainBbsBean;

/**
 * @description 莆田岗位培训_知识学习业务逻辑�?
 * @author 许东�?
 * @date 2014-3-24
 */
public interface WebBbsService {

	/**
	 * 查找莆田岗位培训_知识学习列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainBbsBean> findPtrainBbsList(Map map,int tagpage,int record);
	/**
	 * 主帖互动记录  列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainBbsBean> findPtrainBbsChildList(Map map,int tagpage,int record);
	/**
	 * 获取 主帖互动记录个数
	 * @param map
	 * @return
	 */
	public String findPtrainBbsChildCount(Map map);
	/**
	 * 查找莆田岗位培训_知识学习信息
	 * @param id
	 * @return
	 */
	public PtrainBbsBean findPtrainBbsBeanById(String id);

	/**
	 * Map Bean 查询
	 * @param map
	 * @return
	 */
	public PtrainBbsBean findPtrainBbsBeanByMap(Map map);
	/**
	 * 保存莆田岗位培训_知识学习信息
	 * @param ptrainBbsBean
	 * @return
	 */
	public String savePtrainBbs(PtrainBbsBean ptrainBbsBean);
	/**
	 * 添加 回复 Jq
	 * @param ptrainBbsBean
	 * @return
	 */
	public String insertPtrainBbsByJq(PtrainBbsBean ptrainBbsBean);
	/**
	 * 动�?? 添加语句
	 * @param value
	 * @return
	 */
	public String insertPtrainNicelog(String pkId,String userid);
	/**
	 * 更新 浏览次数
	 * @param id
	 */
	public void updatePtrainBbsByBrowse(String id);
	/**
	 * 更新 下载次数
	 * @param id
	 */
	public void updatePtrainBbsByDownnum(String id);
	/**
	 * 更新 对专家解答进行评�?
	 * @param ptrainBbsBean
	 */
	public void updatePtrainBbsByEvasign(PtrainBbsBean ptrainBbsBean);
	/**
	 * 删除莆田岗位培训_知识学习信息
	 * @param id
	 * @return
	 */
	public void deletePtrainBbsById(String id);


}
