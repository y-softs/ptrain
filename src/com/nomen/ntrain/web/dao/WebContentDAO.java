package com.nomen.ntrain.web.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainContentBean;


/**
 * @description 莆田岗位培训_资源表
 * @author 许东雄
 * @date 2014-11-15
 */
public interface WebContentDAO {
	
	/**
	 * 资源列表
	 * @param map
	 * @return
	 */
	public List<PtrainContentBean>	findPtrainContentList(Map map,int tagpage,int record);
	
	/**
	 * Bean查询
	 * @param id
	 * @return
	 */
	public PtrainContentBean findPtrainContentBean(String id);
	
}
