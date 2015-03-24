package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainReqtempBean;

/**
 * @description 莆田岗位培训_培训点菜临时表业务逻辑层
 * @author 林木山
 * @date 2014-3-18
 */
public interface PtrainReqtempService {

	/**
	 * 查找莆田岗位培训_培训点菜临时表列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainReqtempBean> findPtrainReqtempList(Map map);

	/**
	 * 查找莆田岗位培训_培训点菜临时表信息
	 * @param id
	 * @return
	 */
	public PtrainReqtempBean findPtrainReqtempBeanById(String id);

	/**
	 * 保存莆田岗位培训_培训点菜临时表信息
	 * @param ptrainReqtempBean
	 * @return
	 */
	public void savePtrainReqtemp(PtrainReqtempBean ptrainReqtempBean);

	/**
	 * 删除莆田岗位培训_培训点菜临时表信息
	 */
	public void deletePtrainReqtemp(Map map);

	/**
	 * 删除莆田岗位培训_培训点菜临时表信息
	 * @param id
	 * @return
	 */
	public void deletePtrainReqtempByMap(Map map);

	/**
	 * 保存莆田岗位培训__培训点菜临时表信息 Excel导入
	 */
	public void savePtrainReqtempExcel(Map map);
	
	/**
	 * 临时表导入到主表
	 */
	public void savePtrainReqExcel(Map map);

}
