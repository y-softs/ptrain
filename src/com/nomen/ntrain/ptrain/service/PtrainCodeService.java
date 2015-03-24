package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.bean.BaseUnitBean;
import com.nomen.ntrain.ptrain.bean.PtrainCodeBean;

/**
 * @description 岗位培训_代码表业务逻辑层
 * @author 林木山
 * @date 2014-3-4
 */
public interface PtrainCodeService {
	
	/**
	 * 查找 岗位培训_代码表 列表信息
	 * @param map：level，查询代码级别 ；fatherid，代码父级ID；unitid，单位ID；
	 * 			  usesign，启用状态；fields，关键字段；keyword，关键字内容；
	 * 			  sortfield，排序字段；
	 * @return
	 */
	public List<PtrainCodeBean> findPtrainCodeList(Map map);

	/**
	 * 查找 岗位培训_代码表Bean信息  
	 * @param map 主键id；查询父级信息是：linkfield,fatherid
	 * @return
	 */
	public PtrainCodeBean findPtrainCodeBeanByMap(Map map);
	/**
	 * 提供外部调用 列表 (小许-加)
	 * @param map
	 * @return
	 */
	public List<PtrainCodeBean> findPtrainCodeList_Comm(Map map);

	/**
	 * 查找 岗位培训_代码表 各级名称
	 * @param map 主键id；fatherid
	 * @return
	 */
	public String findPtrainCodeInfoByMap(Map map);

	/**
	 * 保存 岗位培训_代码表信息
	 * @param PtrainCodeBean
	 * @return
	 */
	public void savePtrainCode(PtrainCodeBean PtrainCodeBean);
	
	/**
	 * 查询排序号
	 * @param map：fatherid，代码父级ID；unitid，单位ID
	 */
	public String findPtrainCodeSortnum(Map map);
	
	/**
	 * 	查询编码是否存在
	 * @param map：fatherid，代码父级ID；unitid，单位ID；codevalue，编码
	 */
	public String findCodeValueNum(Map map);

	/**
	 * 删除、批量删除 岗位培训_代码表信息
	 * @param 主键id或idStr
	 * @return
	 */
	public void deletePtrainCode(String idStr);
	
	/**
	 * 更新代码信息[启用、禁止]
	 */
	public void updatePtrainCodeUsesign(PtrainCodeBean PtrainCodeBean);

	/**
	 * 查询本单位的市级单位[]
	 * @param unitid:单位ID；datalevel:市级单位所处等级
	 */
	public BaseUnitBean findCenterBaseUnitBean(Map map);
	
	/**
	 *莆田岗位培训_三问专业设置 excel导入
	 */
	public void savePtrainCodeExcel(Map map);

}
