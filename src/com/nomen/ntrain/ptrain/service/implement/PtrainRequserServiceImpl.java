package com.nomen.ntrain.ptrain.service.implement;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainReqBean;
import com.nomen.ntrain.ptrain.bean.PtrainRequserBean;
import com.nomen.ntrain.ptrain.dao.PtrainRequserDAO;
import com.nomen.ntrain.ptrain.excel.PtrainExcelOutForJxlImpl;
import com.nomen.ntrain.ptrain.service.PtrainRequserService;
@SuppressWarnings("all")
public class PtrainRequserServiceImpl extends BaseServiceImpl implements PtrainRequserService {

	private PtrainRequserDAO ptrainRequserDAO;

	public List<PtrainReqBean> findPtrainRequserList(Map map){
		int page = Integer.parseInt((String)map.get("tagpage"));
		int record = Integer.parseInt((String)map.get("record"));
		return ptrainRequserDAO.findPtrainRequserList(map, page, record);
	}

	public List<PtrainRequserBean> findPtrainRequserSignList(Map map) {
		int page = Integer.parseInt((String)map.get("tagpage"));
		int record = Integer.parseInt((String)map.get("record"));
		return ptrainRequserDAO.findPtrainRequserSignList(map, page, record);
	}

	public PtrainRequserBean findPtrainRequserBeanById(String id){
		return ptrainRequserDAO.findPtrainRequserBeanById(id);
	}

	public void savePtrainRequser(PtrainRequserBean ptrainRequserBean){
		if(func.IsEmpty(func.Trim(ptrainRequserBean.getId()))){
			ptrainRequserDAO.insertPtrainRequser(ptrainRequserBean);
		}else{
			ptrainRequserDAO.updatePtrainRequser(ptrainRequserBean);
		}
	}

	public void deletePtrainRequserById(String id){
		ptrainRequserDAO.deletePtrainRequserById(id);
	}

	public void savePtrainRequserExpExcel(Map map, HttpServletResponse response) throws Exception {
		//数据列表
		List<PtrainReqBean> dataList = this.ptrainRequserDAO.findPtrainRequserList(map);
		PtrainExcelOutForJxlImpl expJxl=new PtrainExcelOutForJxlImpl();
		expJxl.expRequser(dataList,response);
	}

	public void savePtrainRequserSignExpExcel(Map map, HttpServletResponse response) throws Exception {
		String itemname=func.Trim(map.get("itemname")+"");//专业类别
		String specname=func.Trim(map.get("specname")+"");//项目名称
		//数据列表
		List<PtrainRequserBean> dataList = this.ptrainRequserDAO.findPtrainRequserSignList(map);
		PtrainExcelOutForJxlImpl expJxl=new PtrainExcelOutForJxlImpl();
		expJxl.expRequserSign(dataList,specname,itemname,response);
	}

	//Get和Set方法
	public PtrainRequserDAO getPtrainRequserDAO() {
		return ptrainRequserDAO;
	}
	public void setPtrainRequserDAO(PtrainRequserDAO ptrainRequserDAO) {
		this.ptrainRequserDAO = ptrainRequserDAO;
	}
}
