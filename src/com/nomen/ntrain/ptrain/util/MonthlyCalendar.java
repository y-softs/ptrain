package com.nomen.ntrain.ptrain.util;

import java.util.ArrayList;
import java.util.List;

import com.nomen.ntrain.ptrain.action.PtrainAction;

@SuppressWarnings("all")
public class MonthlyCalendar extends PtrainAction{
	    // 日历的年月
	    private String yearMonth;
	    private String yearMonth2;
	    //已答题的日期串
	    private String ansDayStr;
	    // 点击返回的日期（需更换背景颜色）
	    private String changeColorDay;
	    // 一个年月的日期
	    private List<String> days;
	    
	    public MonthlyCalendar(String year,String month,String day,String changeDay){
	    	try {
		        this.yearMonth=year+"."+month;
		        this.yearMonth2=year+"-"+month;
		        this.ansDayStr = day;
		        String dayy = "";
		        if(this.func.Cint(changeDay) < 10 && changeDay.length() > 1){
		        	dayy = changeDay.substring(1,changeDay.length());
			        this.changeColorDay = dayy;
		        }else{
			        this.changeColorDay = changeDay;
		        }
		        fillDays();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    private void fillDays(){
	        days=new ArrayList<String>();
	        
	        // 每个月第一日前的空白
	        int spacesBeforeFirstDay=DateTimeUtil.getWeekOfFirstDay(yearMonth);
	        for(int i=0;i<spacesBeforeFirstDay;i++){
	            days.add("");
	        }
	        
	        // 每个月的日期
	        int dayCountInaMonth=DateTimeUtil.getDaysInAMonth(yearMonth);
	        for(int i=0;i<dayCountInaMonth;i++){
	            days.add((i+1)+"");
	        }    
	    }
	    public String getText(){
	        StringBuffer sb=new StringBuffer();
	        sb.append("<thead>" +
		        		  "<tr>" +
		        			"<td width="+"15%"+" nowrap>日</td>" +
		        			"<td width="+"15%"+" nowrap>一</td>" +
		        			"<td width="+"14%"+" nowrap>二</td>" +
		        			"<td width="+"14%"+" nowrap>三</td>" +
		        			"<td width="+"14%"+" nowrap>四</td>" +
		        			"<td width="+"14%"+" nowrap>五</td>" +
		        			"<td width="+"14%"+" nowrap>六</td>" +
		        		  "</tr>"+
	        		  "</thead>"+
			          "<tbody>"+
			          "<tr bgColor="+"#FFFFFF"+">");
	        String color = "";
	        for(int i=0;i<days.size();i++){
		    	/**
	    		  *日期表格中“蓝色”（fontcolor_blue）表示已答，
	    		  *		    “红色”（fontcolor_red）表示未答，
	    		  *			“加粗”（fontcolor_b）表示当前日期，DateTimeUtil.getDateTimeNum() == days.get(i)
		    	 */
	        	if(this.ansDayStr.indexOf(","+days.get(i)+",")>=0){
	        		color = "fontcolor_blue";
	        	}else{
	        		color = "fontcolor_red";
	        	}
	        	String thisDate = DateTimeUtil.getCurrDate(),thisDayTemp=days.get(i),thisDay=(thisDayTemp.length()<2?"0":"")+thisDayTemp;
	        	//当前日期已答给蓝色字体并加粗
	        	if((this.yearMonth2+"-"+thisDay).equals(thisDate) && this.ansDayStr.indexOf(","+days.get(i)+",")>=0){
	        		color = "fontcolor_blue_b";
	        	//当前日期未答红色字体并加粗
	        	}else if((this.yearMonth2+"-"+thisDay).equals(thisDate) && this.ansDayStr.indexOf(","+days.get(i)+",")<0){
	        		color = "fontcolor_red_b";
	        	}
		    	/**
		    	 * “灰色”(style=\"background:#DDD\")背景表示选中日期
		    	 */
		    	String style = "";
		    	if(!this.func.IsEmpty(this.changeColorDay)){
			    	if(days.get(i).equals(this.changeColorDay)){
				    	style = "background:#DDDDDD";//给选中的日期上灰色背景
			    	}
		    	}
		    	/**
		    	 * 空白项不给点击事件
		    	 */
		    	String onclick = "";
		    	if(!this.func.IsEmpty(days.get(i))){
		    		onclick = "onclick="+"changBgcolor('"+days.get(i)+"',this);";
		    	}
	        	/**
	        	 * 构建日历
	        	 */
	            sb.append("<td id="+"td_"+i+" name="+"day"+" align="+"center"+" nowrap "+onclick+""+" style="+"cursor:pointer;"+style+""+">"+
	                	  "<span class="+
	                	  	color
	                	  +">"+days.get(i)+"</span>"+
	                  "</td>");
	            if((i+1) % 7 == 0){
	    	        sb.append("</tr><tr bgColor="+"#FFFFFF"+">");
	            }
	        }
	        sb.append("</tr></tbody>");
	        return sb.toString();
	    }
}
