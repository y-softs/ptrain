package com.nomen.ntrain.ptrain.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.ptrain.bean.PtrainVoteappBean;
import com.nomen.ntrain.ptrain.bean.PtrainVotesrcBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.service.PtrainVoteService;
import com.nomen.ntrain.ptrain.service.PtrainVoteappService;
import com.nomen.ntrain.ptrain.service.PtrainVoteipconfigService;
import com.nomen.ntrain.ptrain.service.PtrainVotesrcService;
import com.nomen.ntrain.ptrain.util.IpValidUtil;
import com.nomen.ntrain.ptrain.util.OperMessage;

/**
 * @description 投票管理_前台action层
 * @author 林木山
 * @date 2014-3-4
 */
@SuppressWarnings("all")
public class PtrainVoteAction extends PtrainAction{
	private PtrainVotesrcService ptrainVotesrcService;
	private PtrainVoteService    ptrainVoteService;
	private PtrainVoteappService ptrainVoteappService;
	private PtrainVoteipconfigService ptrainVoteipconfigService;
	

	/**
	 * 前台投票列表[跳转]
	 * @return
	 */
	public String listPtrainVote(){
		HttpServletRequest req = ServletActionContext.getRequest();
		//查询默认投票批次并获取到appid
		PtrainVoteappBean appBean = this.ptrainVoteappService.findDefaultPtrainVoteappBean();
		String appId = "-1";//在列表页面中要判断appId是否为-1 ，若是则提示批次为设置
		String appName = "";
		if(appBean != null){
			appId = appBean.getId();
			appName = appBean.getAppname();
		}
		req.setAttribute("appId",  appId);
		req.setAttribute("appName",appName);
		
		//查询特定批次下的资源列表
		List voteSrcList = this.ptrainVotesrcService.findPtrainVotesrcList(appId);
		req.setAttribute("voteSrcList",voteSrcList);
		//统计出特定批次下客户端已投票的资源id串（用于判断资源是否可以投票）
		String clientIp = req.getRemoteAddr();
		req.setAttribute("okIdStr",this.ptrainVoteService.findPtrainVotesrcIdStr(appId,clientIp));
		return SUCCESS;
	}
	
	/**
	 * 前台投票_详细[跳转]
	 * @return
	 */
	public String setPtrainVote(){
		HttpServletRequest req = ServletActionContext.getRequest();
		//获取资源ID
		String srcId = req.getParameter("srcId");
		//获取详细
		PtrainVotesrcBean srcBean = this.ptrainVotesrcService.findPtrainVotesrcBean(srcId);
		req.setAttribute("srcBean",srcBean);
		//统计出特定批次下客户端已投票的资源id串（用于判断资源是否可以投票）
		String appId = srcBean.getAppid();
		String clientIp = req.getRemoteAddr();
		req.setAttribute("appId", appId);
		req.setAttribute("okIdStr",this.ptrainVoteService.findPtrainVotesrcIdStr(appId,clientIp));
		return SUCCESS;
	}
	
	/**
	 * 投票
	 */
	public void   savePtrainVoteByJq(){
		HttpServletRequest req = ServletActionContext.getRequest();
		//获取资源ID
		String srcId = req.getParameter("srcId");
		//获取客户端IP地址
		String clientIp = req.getRemoteAddr();
		//获取批次ID
		String appId = func.Trim(req.getParameter("appId"));
		//判断客户端所在的IP地址投票次数是否超过了预先设置的投票总次数
		int    hasVotedCount = this.ptrainVoteService.findPtrainVoteitemCount(appId,clientIp);
		if(hasVotedCount>=PtrainConstant.VOTE_MAXTIME){
			this.printBean(new OperMessage(PtrainConstant.VOTE_OUTTIMES,"每个IP地址只能进行"+PtrainConstant.VOTE_MAXTIME+"次投票！","-1"));
			return ;
		}
		//验证客户端IP地址是否在合法的范围内
		List<String> ipList = this.ptrainVoteipconfigService.findPtrainVoteipconfigStrList(appId);
		if(null != ipList && ipList.size()>0){
			//表示要求检测IP有效性
			IpValidUtil ipValidUtil = new IpValidUtil();
			if(!ipValidUtil.checkIpValidOper(clientIp, ipList)){
				//表示非法IP
				this.printBean(new OperMessage(PtrainConstant.VOTE_OUTIPSCOPE,"对不起！您的IP不属于可投票范围IP，所以不能投票。谢谢！","-1"));
				return ;
			}else{
				//保存投票信息
				String count = this.ptrainVoteService.savePtrainVote(appId,srcId,clientIp);
				this.printBean(new OperMessage(PtrainConstant.VOTE_SUCCESS,"投票成功",count));
				return ;
			}
		}else{
			//保存投票信息
			String count = this.ptrainVoteService.savePtrainVote(appId,srcId,clientIp);
			this.printBean(new OperMessage(PtrainConstant.VOTE_SUCCESS,"投票成功",count));
			return ;
		}
	}
	
	public PtrainVoteipconfigService getPtrainVoteipconfigService() {
		return ptrainVoteipconfigService;
	}

	public void setPtrainVoteipconfigService(
			PtrainVoteipconfigService ptrainVoteipconfigService) {
		this.ptrainVoteipconfigService = ptrainVoteipconfigService;
	}

	//以下为set、get方法
	public PtrainVotesrcService getPtrainVotesrcService() {
		return ptrainVotesrcService;
	}

	public void setPtrainVotesrcService(PtrainVotesrcService ptrainVotesrcService) {
		this.ptrainVotesrcService = ptrainVotesrcService;
	}

	public PtrainVoteService getPtrainVoteService() {
		return ptrainVoteService;
	}

	public void setPtrainVoteService(PtrainVoteService ptrainVoteService) {
		this.ptrainVoteService = ptrainVoteService;
	}

	public PtrainVoteappService getPtrainVoteappService() {
		return ptrainVoteappService;
	}

	public void setPtrainVoteappService(PtrainVoteappService ptrainVoteappService) {
		this.ptrainVoteappService = ptrainVoteappService;
	}
	
}
