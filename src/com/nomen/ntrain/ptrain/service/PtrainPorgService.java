package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.nomen.ntrain.ptrain.bean.PtrainPorgBean;

/**
 * @description 莆田岗位培训_咨询公司业务逻辑层
 * @author 林木山
 * @date 2014-3-13
 */
public interface PtrainPorgService {

	/**
	 * 查找莆田岗位培训_咨询公司列表信息
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
	 * 保存莆田岗位培训_咨询公司信息
	 * @param ptrainPorgBean
	 * @return
	 */
	public void savePtrainPorg(PtrainPorgBean ptrainPorgBean);

	/**
	 * 删除莆田岗位培训_咨询公司信息
	 * @param id
	 * @return
	 */
	public void deletePtrainPorgById(String id);

	/**
	 * 查找莆田岗位培训_咨询公司信息 排序号
	 * @param id
	 * @return
	 */
	public String findPtrainPorgSortnum(Map map);

	/**
	 * 删除莆田岗位培训_咨询公司信息 excel导入
	 * @param id
	 * @return
	 */
	public void savePtrainPorgImpExcel(Map map);
	
	/**
	 * 莆田岗位培训_咨询公司信息 excel导出
	 */
	public void savePtrainPorgExpExcel(Map map,HttpServletResponse response) throws Exception;

}
