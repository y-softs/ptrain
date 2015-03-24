package com.nomen.ntrain.ptrain.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainReqtempBean;

/**
 * @description 莆田岗位培训_培训点菜临时表数据库操作接口
 * @author 林木山
 * @date 2014-3-18
 */
public interface PtrainReqtempDAO {

	/**
	 * 查找莆田岗位培训_培训点菜临时表列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainReqtempBean> findPtrainReqtempList(Map map,int page,int record);

	/**
	 * 查找莆田岗位培训_培训点菜临时表信息
	 * @param id
	 * @return
	 */
	public PtrainReqtempBean findPtrainReqtempBeanById(String id);

	/**
	 * 添加莆田岗位培训_培训点菜临时表信息
	 * @param ptrainReqtempBean
	 * @return
	 */
	public String insertPtrainReqtemp(PtrainReqtempBean ptrainReqtempBean);

	/**
	 * 更新莆田岗位培训_培训点菜临时表信息
	 * @param ptrainReqtempBean
	 * @return
	 */
	public void updatePtrainReqtemp(PtrainReqtempBean ptrainReqtempBean);

	/**
	 * 删除莆田岗位培训_培训点菜临时表信息
	 * @param id
	 * @return
	 */
	public void deletePtrainReqtempById(String id);

	/**
	 * 删除莆田岗位培训_培训点菜临时表信息
	 * @param id
	 * @return
	 */
	public void deletePtrainReqtempByMap(Map map);

	/**
	 * 更新莆田岗位培训__培训点菜临时表信息[专业ID等]
	 * @param unitid
	 * @return
	 */
	public void updatePtrainReqtempSpecid(Map map);

	/**
	 * 更新莆田岗位培训__培训点菜临时表信息[异常处理]
	 * @param unitid
	 * @return
	 */
	public void updatePtrainReqtempExc(Map map);

	/**
	 * 培训点菜 临时表导入到主表
	 * @param unitid
	 * @return
	 */
	public void insertPtrainReqExcel(Map map);
	
	/**
	 * 更新莆田岗位培训__培训点菜临时表信息[已导标志]
	 */
	public void updatePtrainReqtempDatasign(Map map);

}
