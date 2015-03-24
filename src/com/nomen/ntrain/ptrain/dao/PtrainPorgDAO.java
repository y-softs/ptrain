package com.nomen.ntrain.ptrain.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainPorgBean;

/**
 * @description 莆田岗位培训_咨询公司数据库操作接口
 * @author 林木山
 * @date 2014-3-13
 */
public interface PtrainPorgDAO {

	/**
	 * 查找莆田岗位培训_咨询公司列表信息[分页]
	 * @param map
	 * @return
	 */
	public List<PtrainPorgBean> findPtrainPorgList(Map map,int page,int record);

	/**
	 * 查找莆田岗位培训_咨询公司列表信息[无分页]
	 * @param map
	 * @return
	 */
	public List<PtrainPorgBean> findPtrainPorgList(Map map);

	/**
	 * 查找莆田岗位培训_咨询公司信息
	 * @param id
	 * @return
	 */
	public PtrainPorgBean findPtrainPorgBeanById(String id);

	/**
	 * 添加莆田岗位培训_咨询公司信息
	 * @param ptrainPorgBean
	 * @return
	 */
	public String insertPtrainPorg(PtrainPorgBean ptrainPorgBean);

	/**
	 * 更新莆田岗位培训_咨询公司信息
	 * @param ptrainPorgBean
	 * @return
	 */
	public void updatePtrainPorg(PtrainPorgBean ptrainPorgBean);

	/**
	 * 删除莆田岗位培训_咨询公司信息
	 * @param id
	 * @return
	 */
	public void deletePtrainPorgById(String id);

	/**
	 * 删除莆田岗位培训_咨询公司信息
	 * @param id
	 * @return
	 */
	public void deletePtrainPorgByMap(Map map);

	/**
	 * 查找莆田岗位培训_咨询公司信息 排序号
	 * @param id
	 * @return
	 */
	public String findPtrainPorgSortnum(Map map);

}
