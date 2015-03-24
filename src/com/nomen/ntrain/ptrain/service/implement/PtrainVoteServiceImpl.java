package com.nomen.ntrain.ptrain.service.implement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainVoteitemBean;
import com.nomen.ntrain.ptrain.dao.PtrainVoteitemDAO;
import com.nomen.ntrain.ptrain.dao.PtrainVotesrcDAO;
import com.nomen.ntrain.ptrain.service.PtrainVoteService;
@SuppressWarnings("all")
public class PtrainVoteServiceImpl implements PtrainVoteService {
	private PtrainVoteitemDAO ptrainVoteitemDAO;
	private PtrainVotesrcDAO  ptrainVotesrcDAO;
	
	
	public String savePtrainVote(String appId,String srcId, String clientIp) {
		//根据srcId以及clientIp查询是否存在该记录,若存在,则跳过12步骤
		int count = this.ptrainVoteitemDAO.findPtrainVoteitemCountBySrcId(srcId, clientIp);
		if(count==0){
			//统计该批次+客户端IP已投票的记录ID串
			String okIdStr = this.findPtrainVotesrcIdStr(appId,clientIp);
			if(okIdStr.indexOf(","+srcId+",")<0){
				//1、修改资源对应的投票数=投票数+1
				this.ptrainVotesrcDAO.updatePtrainVoteSrcVoteCount(srcId);
				//2、新增投票历史记录
				PtrainVoteitemBean itemBean = new PtrainVoteitemBean();
				itemBean.setSrcid(srcId);
				itemBean.setVoteip(clientIp);
				itemBean.setVoteflag("1");
				this.ptrainVoteitemDAO.insertPtrainVoteitemBean(itemBean);
			}
		}
		//3、统计出该资源的投票数
		int total = this.ptrainVoteitemDAO.findPtrainVoteitemCountBySrcId(srcId, "");
		return total+"";
	}

	public int findPtrainVoteitemCount(String srcId, String clientIp) {
		return this.ptrainVoteitemDAO.findPtrainVoteitemCount(srcId,clientIp);
	}

	public String findPtrainVotesrcIdStr(String appId, String clientIp) {
		String srcIdStr = ",";
		List<String> idList = this.ptrainVoteitemDAO.findPtrainVotesrcIdList(appId,clientIp);
		for(String srcId : idList){
			srcIdStr += srcId + ",";
		}
		return srcIdStr;
	}
	
	
	//以下为set get方法
	public PtrainVoteitemDAO getPtrainVoteitemDAO() {
		return ptrainVoteitemDAO;
	}

	public void setPtrainVoteitemDAO(PtrainVoteitemDAO ptrainVoteitemDAO) {
		this.ptrainVoteitemDAO = ptrainVoteitemDAO;
	}

	public PtrainVotesrcDAO getPtrainVotesrcDAO() {
		return ptrainVotesrcDAO;
	}

	public void setPtrainVotesrcDAO(PtrainVotesrcDAO ptrainVotesrcDAO) {
		this.ptrainVotesrcDAO = ptrainVotesrcDAO;
	}
	
}
