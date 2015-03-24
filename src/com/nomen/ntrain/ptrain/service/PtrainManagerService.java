package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainManagerBean;

/**
 * @description 莆田岗位培训_知识版主业务逻辑层
 * @author 林木山
 * @date 2014-3-10
 */
public interface PtrainManagerService {

	/**
	 * 查找莆田岗位培训_知识版主列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainManagerBean> findPtrainManagerList(Map map);

	/**
	 * 查找莆田岗位培训_知识版主信息
	 * @param id
	 * @return
	 */
	public PtrainManagerBean findPtrainManagerBeanById(String id);

	/**
	 * 保存莆田岗位培训_知识版主信息
	 * @param ptrainManagerBean
	 * @return
	 */
	public void savePtrainManager(PtrainManagerBean ptrainManagerBean);

	/**
	 * 删除莆田岗位培训_知识版主信息
	 * @param id
	 * @return
	 */
	public void deletePtrainManagerById(String id);

	/**
	 * 查找莆田岗位培训_知识版主 已有人员
	 * @param map
	 * @return DataUserBean
	 */
	public List findDataUserListHas(Map map);

}
