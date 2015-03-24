package com.nomen.ntrain.ptrain.util;

import java.util.List;

/**
 * 验证IP地址是否属于某个IP段范围
 * @author 连金亮
 * @date   2014-02-14
 */
public class IpValidUtil {
	
	public boolean checkIpValidOper(String ip,List<String> ipSectionList){
		boolean isCanPass = false;
		for(int i=0,len=ipSectionList.size();i<len;i++){
			if(this.ipIsValid(ipSectionList.get(i), ip)){
				isCanPass = true;
				break;
			}
		}
		return isCanPass;
	}
	
	/**
	 * 验证IP是否属于某个IP段
	 * ipSection 	IP段（以'-'分隔开）
	 * ip 			所验证的IP号码
	 * 
	 */
	public boolean ipIsValid(String ipSection, String ip) {
	    ipSection = ipSection.trim();
	    ip = ip.trim();
	    int idx = ipSection.indexOf('-');
	    String beginIP = ipSection.substring(0, idx);
	    String endIP = ipSection.substring(idx + 1);
	    return endIsBigger(beginIP,ip) && endIsBigger(ip,endIP);
	}

	public boolean endIsBigger(String beginIP, String endIP){
	    beginIP = beginIP.trim();
	    endIP = endIP.trim();
	    String[] begin = beginIP.split("\\.");
	    String[] end = endIP.split("\\.");
	    long ips = 0L, ipe = 0L;
	    for (int i = 0; i < 4; ++i) { 
	        ips = ips << 8 | Integer.parseInt(begin[i]);
	        ipe = ipe << 8 | Integer.parseInt(end[i]);
	    }
	    return ips <= ipe;
	}
	
	public static void main(String[] args){
		IpValidUtil ipUtil = new IpValidUtil();
		System.out.println(ipUtil.ipIsValid("8.142.163.1-8.142.177.20", "8.142.177.21"));
	}
}
