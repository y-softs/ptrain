package com.nomen.ntrain.data.service.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.data.bean.DataPostBean;
import com.nomen.ntrain.data.bean.DataUserZBean;
import com.nomen.ntrain.data.dao.DataUserZDAO;
import com.nomen.ntrain.data.service.DataUserZService;

@SuppressWarnings("unchecked")
public class DataUserZServiceImpl extends BaseServiceImpl implements DataUserZService{
	private DataUserZDAO dataUserZDAO;
	
	public List findDataUserZList(Map map, int page, int record) {
		return this.dataUserZDAO.findDataUserZList(map, page, record);
	}
	
	public DataUserZBean findDataUserZBeanById(String id) {
		return this.dataUserZDAO.findDataUserZBeanById(id);
	}
	
	public String saveDataUserZBean(Map map) {
		DataUserZBean dataUserZBean = (DataUserZBean) map.get("dataUserZBean");
		String userid = (String) map.get("userid");
		String mpost = dataUserZBean.getMpost();
		String pkid = "";
		//新增
		if(func.IsEmpty(dataUserZBean.getId())){
			pkid = this.dataUserZDAO.insertDataUserZBean(dataUserZBean);
			DataPostBean dataPostBean = new DataPostBean();
			dataPostBean.setUserid(pkid);
			dataPostBean.setMpost(mpost);
			dataPostBean.setEstauser(userid);
			this.dataUserZDAO.insertDataPostBean(dataPostBean);
		}else{
			this.dataUserZDAO.updateDataUserZBean(dataUserZBean);
			pkid = dataUserZBean.getId();
			DataPostBean dataPostBean = new DataPostBean();
			dataPostBean.setUserid(pkid);
			dataPostBean.setMpost(mpost);
			dataPostBean.setMainuser(userid);
			this.dataUserZDAO.updateDataPostBean(dataPostBean);
		}
		return pkid;
	}
	
	public void deleteDataUserZBeanByIdStr(String idstr) {
		this.dataUserZDAO.deleteDataUserZBeanByIdStr(idstr);
	}
	
	public List findDataCodeList(Map map) {
		return this.dataUserZDAO.findDataCodeList(map);
	}
	
	//生成get,set方法
	public DataUserZDAO getDataUserZDAO() {
		return dataUserZDAO;
	}
	public void setDataUserZDAO(DataUserZDAO dataUserZDAO) {
		this.dataUserZDAO = dataUserZDAO;
	}
	
}
