package com.nomen.ntrain.base.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.bean.BaseExceptionBean;

/**
 * 错误日志 业务接口 
 * @author 郑学仕
 * @date   2014-11-21
 */
public interface BaseExceptionService {
	
	/**
	 * 错误列表
	 * @param map [year]
	 * @param page
	 * @param record
	 * @return
	 */
	public List<BaseExceptionBean> findBaseExceptionList(Map map,int page,int record);
	
	/**
	 * 详细
	 * @param id
	 * @return
	 */
	public BaseExceptionBean   findBaseExceptionBean(String id);
	
	/**
	 * 新增日志
	 * @param bean
	 */
	public void  insertBaseExceptionBean(BaseExceptionBean bean);
	
	/**
	 * 删除日志
	 * @param map [year]
	 * @return
	 */
	public void  deleteBaseException(Map map);

}
