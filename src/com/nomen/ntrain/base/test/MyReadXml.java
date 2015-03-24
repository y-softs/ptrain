package com.nomen.ntrain.base.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nomen.ntrain.util.PubFunc;

public class MyReadXml {
	public void readPubveiwsXml(String filePath,String confName){
		Document document =null;
        try {
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	//使应用程序能够从 XML 文档获取生成 DOM 对象树的解析器=入殓师
        	DocumentBuilder builder = factory.newDocumentBuilder();
        	//从 XML 获取一个 Document
        	document = builder.parse(new File(filePath));
        	Element rootElement = document.getDocumentElement();
        	Element confPurvews = (Element) rootElement.getElementsByTagName(confName+"Purviews").item(0);
        	if(confPurvews instanceof Element){
             	NodeList list = confPurvews.getElementsByTagName("row");
            	for(int i=0;i<list.getLength();i++){
            		Element row =(Element)list.item(i);
	        	  	//NodeList childNodes = row.getChildNodes();
            		//一级权限
            		Element firPur = (Element) row.getElementsByTagName("firPur").item(0);
        			if(firPur instanceof Element){
   	        	 		String jj=firPur.getAttribute("v");
   	        	 		System.out.println(jj+"__________");
	        			Node name=firPur.getElementsByTagName("name").item(0);
	        			Node value=firPur.getElementsByTagName("value").item(0);
	        			if(name instanceof Element&&value instanceof Element){
	   	        	 		System.out.println(name.getFirstChild().getNodeValue()+" 权限值："+value.getFirstChild().getNodeValue());
	        			}
        			}
            		//二级权限
            		NodeList secPurList = row.getElementsByTagName("secPur");
            		//System.out.println(secPurList.getLength());
            		for(int j=0;j<secPurList.getLength();j++){
            			Element secPur =(Element)secPurList.item(j);
            			Node name=secPur.getElementsByTagName("name").item(0);
            			Node value=secPur.getElementsByTagName("value").item(0);
	        			if(name instanceof Element&&value instanceof Element){
	   	        	 		System.out.println(name.getFirstChild().getNodeValue()+" 权限值："+value.getFirstChild().getNodeValue());
	        			}
            		}
            	}
        	 }
        } catch (IOException e) {
            e.printStackTrace();
			System.out.println("Xml文件读取失败！");
		}catch (Exception e) {
            e.printStackTrace();
        }finally{
 		   if(document!=null)document=null;
        }
	}
    public static void main(String[] args) throws IOException, ParseException {
    	PubFunc p=new PubFunc();
    	System.out.println(p.dateToString(new Date(), "HHmm"));
    	//DAY_OF_WEEK_IN_MONTH();
    	//MyReadXml m=new MyReadXml();
    	//m.readPubveiwsXml("E:\\workspace\\node\\WebRoot\\WEB-INF\\pubviews.xml","union");
    }
    public static void DAY_OF_WEEK_IN_MONTH() throws IOException, ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
//        calendar.setTime(new Date());
        int month = calendar.get(Calendar.MONTH);
        int count = 0,thisWeekNum=getThisWeek();
        System.out.println(thisWeekNum);
        while (calendar.get(Calendar.MONTH) == month) {
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                StringBuilder builder = new StringBuilder();
                builder.append("week:");
                builder.append(++count);
                builder.append(" (");
                builder.append(format.format(calendar.getTime()));
                builder.append(" - ");
                calendar.add(Calendar.DATE, 6);
                builder.append(format.format(calendar.getTime()));
                builder.append(")");
                if(thisWeekNum==count)System.out.println(builder.toString());
            }
            calendar.add(Calendar.DATE, 1);
        }
//        int weekDay = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_WEEK_IN_MONTH);
//        System.out.println(weekDay);
//        calendar.setTime(new Date());
//		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
//		System.out.println(calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
	}
    public static int getThisWeek() throws IOException, ParseException{
	    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println("请键入日期（如：2008-8-8）：");
//		String str = br.readLine();
//		Date date = dateFormatter.parse(str);
		Date date = new Date();
//		dateFormatter.applyPattern("D");
//		System.out.println("一年中的第几天：" + dateFormatter.format(date));
//		
//		dateFormatter.applyPattern("d");
//		System.out.println("一个月中的第几天：" + dateFormatter.format(date));
//		
//		dateFormatter.applyPattern("w");
//		System.out.println("一年中的第几周：" + dateFormatter.format(date));
//		
//		dateFormatter.applyPattern("W");
//		System.out.println("一个月中的第几周：" + dateFormatter.format(date));
//		
//		dateFormatter.applyPattern("E");
//		System.out.println("一个星期中的天数：" + dateFormatter.format(date));
		
//		br.close();
		dateFormatter.applyPattern("W");
		return Integer.valueOf(dateFormatter.format(date));
    }
}
