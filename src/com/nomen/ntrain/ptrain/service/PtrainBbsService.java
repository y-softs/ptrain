package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.nomen.ntrain.ptrain.bean.PtrainBbsBean;

/**
 * @description 莆田岗位培训_知识学习业务逻辑层
 * @author 许东雄
 * @date 2014-3-24
 */
public interface PtrainBbsService {

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
	 * 动态 添加语句
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
	 * 更新 对专家解答进行评价
	 * @param ptrainBbsBean
	 */
	public void updatePtrainBbsByEvasign(PtrainBbsBean ptrainBbsBean);
	/**
	 * 删除莆田岗位培训_知识学习信息
	 * @param id
	 * @return
	 */
	public void deletePtrainBbsById(String id);
	
	/**
	 * 查询统计_知识学习情况_应用统计 Excel导出
	 */
	public void savePtrainBbsExpExcel(Map map,HttpServletResponse response) throws Exception;
	
	/**
	 * 查询统计_知识学习情况_最佳知识 Excel导出
	 */
	public void savePtrainBbsRankExpExcel(Map map,HttpServletResponse response) throws Exception;
	

	/**
	 * 查询统计_知识学习情况_专业版主 Excel导出
	 */
	public void savePtrainBbsModeExpExcel(Map map,HttpServletResponse response) throws Exception;

}
