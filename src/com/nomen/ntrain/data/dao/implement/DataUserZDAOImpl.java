package com.nomen.ntrain.data.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.data.bean.DataPostBean;
import com.nomen.ntrain.data.bean.DataUserZBean;
import com.nomen.ntrain.data.dao.DataUserZDAO;
import com.nomen.ntrain.util.NsoftBaseDao;

@SuppressWarnings("unchecked")
public class DataUserZDAOImpl extends NsoftBaseDao implements DataUserZDAO {

	public List findDataUserZList(Map map,int page, int record) {
		map.put("total", this.getObjectTotal("DataUserZ.findDataUserZList", map));
		return this.getSqlMapClientTemplate().queryForList("DataUserZ.findDataUserZList", map, page, record);
	}
	
	public DataUserZBean findDataUserZBeanById(String id) {
		return (DataUserZBean)this.getSqlMapClientTemplate().queryForObject("DataUserZ.findDataUserZBeanById",id);
	}

	public String insertDataUserZBean(DataUserZBean dataUserZBean) {
		return (String) this.getSqlMapClientTemplate().insert("DataUserZ.insertDataUserZBean", dataUserZBean);
	}

	public void updateDataUserZBean(DataUserZBean dataUserZBean) {
		this.getSqlMapClientTemplate().update("DataUserZ.updateDataUserZBean", dataUserZBean);
	}
	
	public void deleteDataUserZBeanByIdStr(String idstr) {
		this.getSqlMapClientTemplate().delete("DataUserZ.deleteDataUserZBeanByIdStr", idstr);
	}

	public List findDataCodeList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("DataUserZ.findDataCodeList", map);
	}

	public String insertDataPostBean(DataPostBean dataPostBean) {
		return (String) this.getSqlMapClientTemplate().insert("DataUserZ.insertDataPostBean", dataPostBean);
	}

	public void updateDataPostBean(DataPostBean dataPostBean) {
		this.getSqlMapClientTemplate().update("DataUserZ.updateDataPostBean", dataPostBean);
	}
}