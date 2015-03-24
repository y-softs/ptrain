package com.nomen.ntrain.ptrain.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainAskitemBean;

/**
 * @description 莆田岗位培训_每日三问答题表数据库操作接口
 * @author 林木山
 * @date 2014-3-24
 */
public interface PtrainAskitemDAO {

	/**
	 * 查找莆田岗位培训_每日三问答题表列表信息[三问答题列表]
	 * @param map
	 * @return
	 */
	public List<PtrainAskitemBean> findPtrainAskitemList(Map map);

	/**
	 * 查找莆田岗位培训_每日三问答题表列表信息[个人查询列表]
	 * @param map
	 * @return
	 */
	public List<PtrainAskitemBean> findPtrainAskitemListQue(Map map);

	/**
	 * 查找莆田岗位培训_每日三问答题表列表信息[试题浏览列表][分页]
	 * @param map
	 * @return
	 */
	public List<PtrainAskitemBean> findPtrainAskitemListSkim(Map map,int page,int record);

	/**
	 * 查找莆田岗位培训_每日三问答题表列表信息[试题浏览列表][excel导出][无分页]
	 * @param map
	 * @return
	 */
	public List<PtrainAskitemBean> findPtrainAskitemListSkimExp(Map map);

	/**
	 * 查找莆田岗位培训_每日三问答题表信息
	 * @param id
	 * @return
	 */
	public PtrainAskitemBean findPtrainAskitemBeanByMap(Map map);

	/**
	 * 添加莆田岗位培训_每日三问答题表信息
	 */
	public void insertPtrainAskitem(Map map);

	/**
	 * 更新莆田岗位培训_每日三问答题表信息
	 * @param ptrainAskitemBean
	 * @return
	 */
	public void updatePtrainAskitem(PtrainAskitemBean ptrainAskitemBean);

	/**
	 * 删除莆田岗位培训_每日三问答题表信息
	 * @param id
	 * @return
	 */
	public void deletePtrainAskitemById(String id);

}
