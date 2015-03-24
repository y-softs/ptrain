package com.nomen.ntrain.ptrain.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainBbsBean;

/**
 * @description 莆田岗位培训_知识学习数据库操作接口
 * @author 许东雄
 * @date 2014-3-24
 */
public interface PtrainBbsDAO {

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
	 * 知识学习_应用统计 列表
	 * @param map
	 * @return
	 */
	public List<PtrainBbsBean> findPtrainAppStatiList(Map map);
	/**
	 * 知识学习_专业版主 列表
	 * @param map
	 * @return
	 */
	public List<PtrainBbsBean> findPtrainModeList(Map map);
	/**
	 * 知识学习_最佳知识 列表
	 * @param map
	 * @return
	 */
	public List<PtrainBbsBean>	findPtrainRankList(Map map);
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
	 * 添加莆田岗位培训_知识学习信息
	 * @param ptrainBbsBean
	 * @return
	 */
	public String insertPtrainBbs(PtrainBbsBean ptrainBbsBean);
	/**
	 * 添加 回复 Jq
	 * @param ptrainBbsBean
	 * @return
	 */
	public String insertPtrainBbsByJq(PtrainBbsBean ptrainBbsBean);
	/**
	 * 动态 添加语句
	 * @param value
	 * @return
	 */
	public String insertPtrainNicelog(String value);
	/**
	 * 更新莆田岗位培训_知识学习信息
	 * @param ptrainBbsBean
	 * @return
	 */
	public void updatePtrainBbs(PtrainBbsBean ptrainBbsBean);
	/**
	 * 动态更新 SQL语句
	 * @param usql
	 */
	public void updateDynamicSQL(String usql);
	/**
	 * 删除莆田岗位培训_知识学习信息
	 * @param id
	 * @return
	 */
	public void deletePtrainBbsById(String id);
	/**
	 * 根据帖子ID删除记录
	 * @param id
	 */
	public void deletePtrainNicelogByBbsId(String id);

}
