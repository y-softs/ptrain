package com.nomen.ntrain.ptrain.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainAskBean;
import com.nomen.ntrain.ptrain.dao.PtrainAskDAO;
import com.nomen.ntrain.ptrain.excel.PtrainExcelOutForJxlImpl;
import com.nomen.ntrain.ptrain.service.PtrainAskService;
@SuppressWarnings("all")
public class PtrainAskServiceImpl extends BaseServiceImpl implements PtrainAskService {

	private PtrainAskDAO ptrainAskDAO;


	public PtrainAskBean findPtrainAskStat(Map map) {
		return ptrainAskDAO.findPtrainAskStat(map);
	}

	public String findPtrainAskNoAskid(Map map) {
		return ptrainAskDAO.findPtrainAskNoAskid(map);
	}

	public PtrainAskBean findPtrainAskPushnum(Map map) {
		return ptrainAskDAO.findPtrainAskPushnum(map);
	}

	public List<PtrainAskBean> findPtrainAskSubday(Map map) {
		return ptrainAskDAO.findPtrainAskSubday(map);
	}

	public PtrainAskBean findPtrainAskBeanById(String id){
		return ptrainAskDAO.findPtrainAskBeanById(id);
	}

	public void savePtrainAsk(PtrainAskBean ptrainAskBean){
		if(func.IsEmpty(func.Trim(ptrainAskBean.getId()))){
			ptrainAskDAO.insertPtrainAsk(ptrainAskBean);
		}else{
			ptrainAskDAO.updatePtrainAsk(ptrainAskBean);
		}
	}

	public void deletePtrainAskById(String id){
		ptrainAskDAO.deletePtrainAskById(id);
	}

	public List<Map> findPtrainAskAppStat(Map map) {;
		return ptrainAskDAO.findPtrainAskAppStat(map);
	}

	public List<Map> findPtrainAskAppStatSpec(Map map) {;
		return ptrainAskDAO.findPtrainAskAppStatSpec(map);
	}

	public void savePtrainAskStatExpExcel(Map map, HttpServletResponse response) throws Exception {
		String sortsign=func.Trim(map.get("sortsign")+"");
		//数据列表
		List<Map> dataList = new ArrayList<Map>();
		if("1".equals(sortsign)){
			dataList = this.ptrainAskDAO.findPtrainAskAppStat(map);
		}else{
			map.put("unitid", "");
			dataList = this.ptrainAskDAO.findPtrainAskAppStatSpec(map);
		}
		PtrainExcelOutForJxlImpl expJxl=new PtrainExcelOutForJxlImpl();
		expJxl.expAskAppStat(dataList,sortsign,response);
	}

	public List<Map> findPtrainAskUser(Map map) {
		int page = Integer.parseInt((String)map.get("tagpage"));
		int record = Integer.parseInt((String)map.get("record"));
		return ptrainAskDAO.findPtrainAskUser(map, page, record);
	}

	public void savePtrainAskUserExpExcel(Map map, HttpServletResponse response) throws Exception {
		//数据列表
		List<Map> dataList = this.ptrainAskDAO.findPtrainAskUser(map);
		PtrainExcelOutForJxlImpl expJxl=new PtrainExcelOutForJxlImpl();
		expJxl.expAskAppStatUser(dataList,response);
	}

	public List<Map> findPtrainAskRank(Map map) {
		return ptrainAskDAO.findPtrainAskUser(map);
	}

	public void savePtrainAskRankExpExcel(Map map, HttpServletResponse response) throws Exception {
		//数据列表
		List<Map> dataList = this.ptrainAskDAO.findPtrainAskUser(map);
		PtrainExcelOutForJxlImpl expJxl=new PtrainExcelOutForJxlImpl();
		expJxl.expAskRank(dataList,response);
	}

	//Get和Set方法
	public PtrainAskDAO getPtrainAskDAO() {
		return ptrainAskDAO;
	}
	public void setPtrainAskDAO(PtrainAskDAO ptrainAskDAO) {
		this.ptrainAskDAO = ptrainAskDAO;
	}
}
