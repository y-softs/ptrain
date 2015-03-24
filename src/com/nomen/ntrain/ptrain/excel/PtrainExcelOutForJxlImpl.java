package com.nomen.ntrain.ptrain.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.nomen.ntrain.ptrain.bean.PtrainAskitemBean;
import com.nomen.ntrain.ptrain.bean.PtrainBbsBean;
import com.nomen.ntrain.ptrain.bean.PtrainPorgBean;
import com.nomen.ntrain.ptrain.bean.PtrainPostuserBean;
import com.nomen.ntrain.ptrain.bean.PtrainReqBean;
import com.nomen.ntrain.ptrain.bean.PtrainRequserBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.enums.PtrainReqReqtypeEnum;
import com.nomen.ntrain.util.JxlCellFormatUtil;
import com.nomen.ntrain.util.PubFunc;

public class PtrainExcelOutForJxlImpl extends JxlCellFormatUtil {
	private PubFunc func = null;
	
	public PtrainExcelOutForJxlImpl(){
		if(null==func){
			this.func = new PubFunc();
		}
	}

	/**	 
	 * 人员岗位关联 人员信息导出Excel
	 */
	public void expPostuser(List<PtrainPostuserBean> dataList,String postTypeName,HttpServletResponse response){
		try {
			ServletOutputStream sos = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(sos);
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			//设置标题行单元格格式[居中加粗]
			WritableCellFormat wcf1 = this._formatCellStyle(10, true,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			//设置内容格式[居左]
			WritableCellFormat wcf2 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.LEFT,
					jxl.format.VerticalAlignment.CENTRE);
			wcf2.setWrap(true);// 自动换行
			//设置内容格式[居中]
			WritableCellFormat wcf3 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			
			//设置初始行数|列数
			int rowIndex = 0, colIndex = 0;
			//添加标题内容
			ws.setRowView(rowIndex, 12*21);	//行高
			ws.setColumnView(colIndex, 7);	//列宽
			ws.addCell(new Label(colIndex++, rowIndex,"序号",wcf1));
			ws.setColumnView(colIndex, 27);
			ws.addCell(new Label(colIndex++, rowIndex,"部门名称",wcf1));
			ws.setColumnView(colIndex, 22);
			ws.addCell(new Label(colIndex++, rowIndex,"班组名称",wcf1));
			ws.setColumnView(colIndex, 14);
			ws.addCell(new Label(colIndex++, rowIndex,"姓名",wcf1));
			ws.setColumnView(colIndex, 21);
			ws.addCell(new Label(colIndex++, rowIndex,"身份证号",wcf1));
			ws.setColumnView(colIndex, 32);
			ws.addCell(new Label(colIndex++, rowIndex,"专业类别",wcf1));
			
			//内容数据
			for(PtrainPostuserBean data:dataList){
				rowIndex++;colIndex=0;
				ws.setRowView(rowIndex, 252);
				ws.addCell(new Label(colIndex++, rowIndex, rowIndex+"", wcf3));//序号
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getDeptname()), wcf2));//部门名称
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getGroupname()), wcf2));//班组名称
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getUsername()), wcf3));//姓名
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getCardid()), wcf3));//身份证号
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getPostname()), wcf2));//岗位名称
			}
			wwb.write();
			// 设置输出类型
			response.setContentType("application/download;charset=UTF-8");
//			response.setContentType("application/msexcel;charset=UTF-8");
			//设置输出文件头
			response.setHeader("Content-disposition","attachment; filename="+convert(postTypeName.replace(" >> ", "-")+".xls"));
			wwb.close();
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**	 
	 * 咨询公司 信息导出Excel
	 */
	public void expPorg(List<PtrainPorgBean> dataList,HttpServletResponse response){
		try {
			ServletOutputStream sos = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(sos);
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			//设置标题行单元格格式[居中加粗]
			WritableCellFormat wcf1 = this._formatCellStyle(10, true,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			//设置内容格式[居左]
			WritableCellFormat wcf2 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.LEFT,
					jxl.format.VerticalAlignment.CENTRE);
			wcf2.setWrap(true);// 自动换行
			//设置内容格式[居中]
			WritableCellFormat wcf3 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			
			//设置初始行数|列数
			int rowIndex = 0, colIndex = 0;
			//添加标题内容
			ws.setRowView(rowIndex, 12*21);	//行高
			ws.setColumnView(colIndex, 7);	//列宽
			ws.addCell(new Label(colIndex++, rowIndex,"序号",wcf1));
			ws.setColumnView(colIndex, 21);
			ws.addCell(new Label(colIndex++, rowIndex,"公司名称",wcf1));
			ws.setColumnView(colIndex, 21);
			ws.addCell(new Label(colIndex++, rowIndex,"公司简介",wcf1));
			ws.setColumnView(colIndex, 27);
			ws.addCell(new Label(colIndex++, rowIndex,"案例",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"联系方式",wcf1));
			
			//内容数据
			for(PtrainPorgBean data:dataList){
				rowIndex++;colIndex=0;
				ws.setRowView(rowIndex, 252);
				ws.addCell(new Label(colIndex++, rowIndex, rowIndex+"", wcf3));//序号
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getOrgname()), wcf2));//公司名称
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getOrgdesc()), wcf2));//公司简介
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getOrgcase()), wcf2));//案例
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getContact()), wcf3));//联系方式
			}
			wwb.write();
			// 设置输出类型
			response.setContentType("application/download;charset=GBK");
//			response.setContentType("application/msexcel;charset=GBK");
			//设置输出文件头
			response.setHeader("Content-disposition","attachment; filename="+convert("咨询公司详细.xls"));
			wwb.close();
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**	 
	 * 培训中心加菜 信息导出Excel
	 * 兼职教师加菜 信息导出Excel
	 * 课件培训点菜 信息导出Excel
	 * 汇总维护 信息导出Excel
	 */
	public void expReq(List<PtrainReqBean> dataList,String reqtype,HttpServletResponse response){
		try {
			ServletOutputStream sos = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(sos);
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			//设置标题行单元格格式[居中加粗]
			WritableCellFormat wcf1 = this._formatCellStyle(10, true,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			//设置内容格式[居左]
			WritableCellFormat wcf2 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.LEFT,
					jxl.format.VerticalAlignment.CENTRE);
			wcf2.setWrap(true);// 自动换行
			//设置内容格式[居中]
			WritableCellFormat wcf3 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			
			//设置初始行数|列数
			int rowIndex = 0, colIndex = 0;
			//添加标题内容
			ws.setRowView(rowIndex, 12*21);	//行高
			ws.setColumnView(colIndex, 7);	//列宽
			ws.addCell(new Label(colIndex++, rowIndex,"序号",wcf1));
			ws.setColumnView(colIndex, 21);
			ws.addCell(new Label(colIndex++, rowIndex,"专业类别",wcf1));
			ws.setColumnView(colIndex, 27);
			ws.addCell(new Label(colIndex++, rowIndex,"培训项目名称",wcf1));
			ws.setColumnView(colIndex, 59);
			ws.addCell(new Label(colIndex++, rowIndex,"课程介绍",wcf1));
			if(!reqtype.equals(PtrainReqReqtypeEnum.REQ_COUR.getKey()+"")){
				ws.setColumnView(colIndex, 12);
				ws.addCell(new Label(colIndex++, rowIndex,"培训天数",wcf1));
			}
			if(func.IsEmpty(reqtype)){
				ws.setColumnView(colIndex, 14);
				ws.addCell(new Label(colIndex++, rowIndex,"培训类别",wcf1));
				ws.setColumnView(colIndex, 16);
				ws.addCell(new Label(colIndex++, rowIndex,"办班时间",wcf1));
				ws.setColumnView(colIndex, 12);
				ws.addCell(new Label(colIndex++, rowIndex,"报名人数",wcf1));
				ws.setColumnView(colIndex, 12);
				ws.addCell(new Label(colIndex++, rowIndex,"办班状态",wcf1));
			}
			if(!reqtype.equals(PtrainReqReqtypeEnum.REQ_COUR.getKey()+"")){
				ws.setColumnView(colIndex, 20);
				ws.addCell(new Label(colIndex++, rowIndex,"项目来源",wcf1));
				ws.setColumnView(colIndex, 12);
				ws.addCell(new Label(colIndex++, rowIndex,"培训师",wcf1));
			}
			if(func.IsEmpty(reqtype)){
				ws.setColumnView(colIndex, 12);
				ws.addCell(new Label(colIndex++, rowIndex,"发起人",wcf1));
			}
			
			//内容数据
			for(PtrainReqBean data:dataList){
				rowIndex++;colIndex=0;
				ws.setRowView(rowIndex, 252);
				ws.addCell(new Label(colIndex++, rowIndex, rowIndex+"", wcf3));//序号
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getSpecid()), wcf2));//专业类别
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getItemname()), wcf2));//培训项目名称
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getItemdesc()), wcf2));//课程介绍
				if(!reqtype.equals(PtrainReqReqtypeEnum.REQ_COUR.getKey()+"")){
					ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getDaycount()), wcf3));//培训天数
				}
				if(func.IsEmpty(reqtype)){
					String reqtypetemp="";
					for(PtrainReqReqtypeEnum reqtypeEum:PtrainReqReqtypeEnum.values()){
						if(data.getReqtype().equals(reqtypeEum.getKey()+"")){
							reqtypetemp=reqtypeEum.getDesc();break;
						}
					}
					ws.addCell(new Label(colIndex++, rowIndex, func.Trim(reqtypetemp), wcf3));//培训类别
					ws.addCell(new Label(colIndex++, rowIndex, "1".equals(func.Trim(data.getState()))?data.getMaintime():"", wcf3));//办班时间
					ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getIntflag()), wcf3));//报名人数
					ws.addCell(new Label(colIndex++, rowIndex, "0".equals(func.Trim(data.getState()))?"待办班":"已办班", wcf3));//办班状态
				}
				if(!reqtype.equals(PtrainReqReqtypeEnum.REQ_COUR.getKey()+"")){
					ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getReqform()), wcf2));//项目来源
					ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getTeacher()), wcf2));//培训师
				}
				if(func.IsEmpty(reqtype)){
					ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getRequserid()), wcf2));//发起人
				}
			}
			wwb.write();
			// 设置输出类型
			response.setContentType("application/download;charset=GBK");
//			response.setContentType("application/msexcel;charset=GBK");
			//设置输出文件头
			String filename=reqtype.equals(PtrainReqReqtypeEnum.REQ_COM.getKey()+"")?"培训中心加菜":
				reqtype.equals(PtrainReqReqtypeEnum.REQ_EXP.getKey()+"")?"兼职教师加菜":
					reqtype.equals(PtrainReqReqtypeEnum.REQ_COUR.getKey()+"")?"课件培训点菜":"汇总维护";
			response.setHeader("Content-disposition","attachment; filename="+convert(filename+".xls"));
			wwb.close();
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**	 
	 * 查询统计_每日三问情况_应用统计 导出Excel
	 */
	public void expAskAppStat(List<Map> dataList,String sortSign,HttpServletResponse response){
		try {
			ServletOutputStream sos = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(sos);
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			//设置标题行单元格格式[居中加粗]
			WritableCellFormat wcf1 = this._formatCellStyle(10, true,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			//设置内容格式[居左]
			WritableCellFormat wcf2 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.LEFT,
					jxl.format.VerticalAlignment.CENTRE);
			wcf2.setWrap(true);// 自动换行
			//设置内容格式[居中]
			WritableCellFormat wcf3 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			String strOper1="专业名称",strOper2="专业参加人数";
			if("1".equals(sortSign)){
				strOper1="部门名称";
				strOper2="部门应参加人数";
			}
			//设置初始行数|列数
			int rowIndex = 0, colIndex = 0;
			//添加标题内容
			ws.setRowView(rowIndex, 12*21);	//行高
			ws.setColumnView(colIndex, 7);	//列宽
			ws.addCell(new Label(colIndex++, rowIndex,"序号",wcf1));
			ws.setColumnView(colIndex, 29);
			ws.addCell(new Label(colIndex++, rowIndex,strOper1,wcf1));
			ws.setColumnView(colIndex, 19);
			ws.addCell(new Label(colIndex++, rowIndex,strOper2,wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"达标人数",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"不达标人数",wcf1));
			ws.setColumnView(colIndex, 14);
			ws.addCell(new Label(colIndex++, rowIndex,"达标比例",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"人均得分",wcf1));
			
			//内容数据
			int i=0,j=0,k=0;
			for(Map data:dataList){
				rowIndex++;colIndex=0;
				//ws.setRowView(rowIndex, 252);
				String usernum=func.Trim(data.get("USERNUM")+""),reachnum=func.Trim(data.get("REACHNUM")+""),reachper=func.Trim(data.get("REACHPER")+""),
					datalevel=func.Trim(data.get("DATALEVEL")+""),notReachnum="",indexs="";
				if(!func.IsEmpty(usernum)&&!func.IsEmpty(reachnum))notReachnum=(Integer.valueOf(usernum)-Integer.valueOf(reachnum))+"";
				if("1".equals(datalevel)){
					i++;
					indexs=i+"";
					j=0;
				}else if("2".equals(datalevel)){
					j++;
					indexs=i+"."+j;
					k=0;
				}else if("3".equals(datalevel)){
					k++;
					indexs=i+"."+j+"."+k;
				}
				ws.addCell(new Label(colIndex++, rowIndex, indexs+"", wcf3));//序号
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.get("APPNAME")+""), wcf2));//部门名称/专业名称
				ws.addCell(new Label(colIndex++, rowIndex, usernum, wcf3));//部门人数/专业参加人数
				ws.addCell(new Label(colIndex++, rowIndex, !func.IsEmpty(usernum)?reachnum:"", wcf3));//达标人数
				ws.addCell(new Label(colIndex++, rowIndex, notReachnum, wcf3));//不达标人数
				ws.addCell(new Label(colIndex++, rowIndex, func.IsEmpty(reachper)?"":reachper+"%", wcf3));//达标比例
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.get("AVESCORE")+""), wcf3));//人均得分
			}
			wwb.write();
			// 设置输出类型
			response.setContentType("application/download;charset=GBK");
//			response.setContentType("application/msexcel;charset=GBK");
			//设置输出文件头
			response.setHeader("Content-disposition","attachment; filename="+convert("每日三问应用统计详细.xls"));
			wwb.close();
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**	 
	 * 查询统计_每日三问情况_应用统计 答题人员 导出Excel
	 */
	public void expAskAppStatUser(List<Map> dataList,HttpServletResponse response){
		try {
			ServletOutputStream sos = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(sos);
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			//设置标题行单元格格式[居中加粗]
			WritableCellFormat wcf1 = this._formatCellStyle(10, true,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			//设置内容格式[居左]
			WritableCellFormat wcf2 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.LEFT,
					jxl.format.VerticalAlignment.CENTRE);
			wcf2.setWrap(true);// 自动换行
			//设置内容格式[居中]
			WritableCellFormat wcf3 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			
			//设置初始行数|列数
			int rowIndex = 0, colIndex = 0;
			//添加标题内容
			ws.setRowView(rowIndex, 12*21);	//行高
			ws.setColumnView(colIndex, 7);	//列宽
			ws.addCell(new Label(colIndex++, rowIndex,"序号",wcf1));
			ws.setColumnView(colIndex, 21);
			ws.addCell(new Label(colIndex++, rowIndex,"部门名称",wcf1));
			ws.setColumnView(colIndex, 21);
			ws.addCell(new Label(colIndex++, rowIndex,"班组名称",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"姓名",wcf1));
			ws.setColumnView(colIndex, 22);
			ws.addCell(new Label(colIndex++, rowIndex,"身份证号",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"答题次数",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"累计得分",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"达标状态",wcf1));
			
			//内容数据
			for(Map data:dataList){
				rowIndex++;colIndex=0;
				ws.setRowView(rowIndex, 252);
				ws.addCell(new Label(colIndex++, rowIndex, rowIndex+"", wcf3));//序号
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.get("DEPTNAME")+""), wcf2));//部门名称
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.get("GROUPNAME")+""), wcf2));//班组名称
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.get("USERNAME")+""), wcf3));//姓名
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.get("USERID")+""), wcf3));//身份证号
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.get("ASKNUM")+""), wcf3));//答题次数
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.get("SCORE")+""), wcf3));//累计得分
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.get("REACHSTAT")+""), wcf3));//达标状态
			}
			wwb.write();
			// 设置输出类型
			response.setContentType("application/download;charset=GBK");
//			response.setContentType("application/msexcel;charset=GBK");
			//设置输出文件头
			response.setHeader("Content-disposition","attachment; filename="+convert("每日三问应用统计答题人员详细.xls"));
			wwb.close();
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**	 
	 * 查询统计_每日三问情况_英雄榜单 导出Excel
	 */
	public void expAskRank(List<Map> dataList,HttpServletResponse response){
		try {
			ServletOutputStream sos = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(sos);
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			//设置标题行单元格格式[居中加粗]
			WritableCellFormat wcf1 = this._formatCellStyle(10, true,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			//设置内容格式[居左]
			WritableCellFormat wcf2 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.LEFT,
					jxl.format.VerticalAlignment.CENTRE);
			wcf2.setWrap(true);// 自动换行
			//设置内容格式[居中]
			WritableCellFormat wcf3 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			
			//设置初始行数|列数
			int rowIndex = 0, colIndex = 0;
			//添加标题内容
			ws.setRowView(rowIndex, 12*21);	//行高
			ws.setColumnView(colIndex, 7);	//列宽
			ws.addCell(new Label(colIndex++, rowIndex,"序号",wcf1));
			ws.setColumnView(colIndex, 21);
			ws.addCell(new Label(colIndex++, rowIndex,"部门名称",wcf1));
			ws.setColumnView(colIndex, 21);
			ws.addCell(new Label(colIndex++, rowIndex,"班组名称",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"姓名",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"答题次数",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"累计得分",wcf1));
			
			//内容数据
			for(Map data:dataList){
				rowIndex++;colIndex=0;
				ws.setRowView(rowIndex, 252);
				ws.addCell(new Label(colIndex++, rowIndex, rowIndex+"", wcf3));//序号
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.get("DEPTNAME")+""), wcf2));//部门名称
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.get("GROUPNAME")+""), wcf2));//班组名称
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.get("USERNAME")+""), wcf3));//姓名
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.get("ASKNUM")+""), wcf3));//答题次数
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.get("SCORE")+""), wcf3));//累计得分
			}
			wwb.write();
			// 设置输出类型
			response.setContentType("application/download;charset=GBK");
//			response.setContentType("application/msexcel;charset=GBK");
			//设置输出文件头
			response.setHeader("Content-disposition","attachment; filename="+convert("每日三问英雄榜单详细.xls"));
			wwb.close();
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**	 
	 * 查询统计_知识学习情况_应用统计 导出Excel
	 */
	public void expBbsAppStat(List<PtrainBbsBean> dataList,HttpServletResponse response){
		try {
			ServletOutputStream sos = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(sos);
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			//设置标题行单元格格式[居中加粗]
			WritableCellFormat wcf1 = this._formatCellStyle(10, true,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			//设置内容格式[居左]
			WritableCellFormat wcf2 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.LEFT,
					jxl.format.VerticalAlignment.CENTRE);
			wcf2.setWrap(true);// 自动换行
			//设置内容格式[居中]
			WritableCellFormat wcf3 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			
			//设置初始行数|列数
			int rowIndex = 0, colIndex = 0;
			//添加标题内容
			ws.setRowView(rowIndex, 400);	//行高
			ws.setColumnView(colIndex, 7);	//列宽
			ws.addCell(new Label(colIndex++, rowIndex,"序号",wcf1));
			ws.setColumnView(colIndex, 16);
			ws.addCell(new Label(colIndex++, rowIndex,"专业类别",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"帖子总数",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"资源数",wcf1));
			ws.setColumnView(colIndex, 18);
			ws.addCell(new Label(colIndex++, rowIndex,"资源平均下载次数",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"提问数",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"答疑数",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"答疑率",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"满意率",wcf1));
			ws.setColumnView(colIndex, 18);
			ws.addCell(new Label(colIndex++, rowIndex,"帖子平均点赞数",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"浏览数",wcf1));
			
			//内容数据
			for(PtrainBbsBean data:dataList){
				rowIndex++;colIndex=0;
				ws.setRowView(rowIndex, 400);
				ws.addCell(new Label(colIndex++, rowIndex, rowIndex+"", wcf3));//序号
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getStrflag()), wcf3));//专业类别
				ws.addCell(new Label(colIndex++, rowIndex, func.Cint(data.getResnum())+func.Cint(data.getPutnum())+"", wcf3));//帖子总数
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getResnum()), wcf3));//资源数
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getDownrate()), wcf3));//下载率
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getPutnum()), wcf3));//提问数
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getAnsnum()), wcf3));//答疑数
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getAnsrate()), wcf3));//答疑率
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getEvarate()), wcf3));//满意率
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getIntflag()), wcf3));//帖子平均点赞数
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getBrowse()), wcf3));//浏览数
			}
			wwb.write();
			// 设置输出类型
			response.setContentType("application/download;charset=GBK");
//			response.setContentType("application/msexcel;charset=GBK");
			//设置输出文件头
			response.setHeader("Content-disposition","attachment; filename="+convert("知识学习情况应用统计详细.xls"));
			wwb.close();
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**	 
	 * 查询统计_知识学习情况_最佳知识 导出Excel
	 */
	public void expBbsRank(List<PtrainBbsBean> dataList,HttpServletResponse response){
		try {
			ServletOutputStream sos = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(sos);
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			//设置标题行单元格格式[居中加粗]
			WritableCellFormat wcf1 = this._formatCellStyle(10, true,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			//设置内容格式[居左]
			WritableCellFormat wcf2 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.LEFT,
					jxl.format.VerticalAlignment.CENTRE);
			wcf2.setWrap(true);// 自动换行
			//设置内容格式[居中]
			WritableCellFormat wcf3 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			
			//设置初始行数|列数
			int rowIndex = 0, colIndex = 0;
			//添加标题内容
			ws.setRowView(rowIndex, 400);	//行高
			ws.setColumnView(colIndex, 7);	//列宽
			ws.addCell(new Label(colIndex++, rowIndex,"序号",wcf1));
			ws.setColumnView(colIndex, 16);
			ws.addCell(new Label(colIndex++, rowIndex,"专业类别",wcf1));
			ws.setColumnView(colIndex, 80);
			ws.addCell(new Label(colIndex++, rowIndex,"帖子",wcf1));
			ws.setColumnView(colIndex, 30);
			ws.addCell(new Label(colIndex++, rowIndex,"部门名称",wcf1));
			ws.setColumnView(colIndex, 15);
			ws.addCell(new Label(colIndex++, rowIndex,"班组名称",wcf1));
			ws.setColumnView(colIndex, 15);
			ws.addCell(new Label(colIndex++, rowIndex,"发帖人",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"浏览数",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"点赞数",wcf1));
			
			//内容数据
			for(PtrainBbsBean data:dataList){
				rowIndex++;colIndex=0;
				ws.setRowView(rowIndex, 400);
				ws.addCell(new Label(colIndex++, rowIndex, rowIndex+"", wcf3));//序号
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getStrflag()), wcf3));//专业类别
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getTitle()), wcf2));//帖子
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getDeptname()), wcf2));//部门
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getGroupname()), wcf3));//班组
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getUsername()), wcf3));//发帖人
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getBrowse()), wcf3));//资源数
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getNice()), wcf3));//点赞数
			}
			wwb.write();
			// 设置输出类型
			response.setContentType("application/download;charset=GBK");
//			response.setContentType("application/msexcel;charset=GBK");
			//设置输出文件头
			response.setHeader("Content-disposition","attachment; filename="+convert("知识学习情况最佳知识详细.xls"));
			wwb.close();
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**	 
	 * 查询统计_知识学习情况_专业版主 导出Excel
	 */
	public void expBbsMode(List<PtrainBbsBean> dataList,HttpServletResponse response){
		try {
			ServletOutputStream sos = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(sos);
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			//设置标题行单元格格式[居中加粗]
			WritableCellFormat wcf1 = this._formatCellStyle(10, true,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			//设置内容格式[居左]
			WritableCellFormat wcf2 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.LEFT,
					jxl.format.VerticalAlignment.CENTRE);
			wcf2.setWrap(true);// 自动换行
			//设置内容格式[居中]
			WritableCellFormat wcf3 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			
			//设置初始行数|列数
			int rowIndex = 0, colIndex = 0;
			//添加标题内容
			ws.setRowView(rowIndex, 400);	//行高
			ws.setColumnView(colIndex, 7);	//列宽
			ws.addCell(new Label(colIndex++, rowIndex,"序号",wcf1));
			ws.setColumnView(colIndex, 16);
			ws.addCell(new Label(colIndex++, rowIndex,"专业类别",wcf1));
			ws.setColumnView(colIndex, 80);
			ws.addCell(new Label(colIndex++, rowIndex,"版主",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"已答情况",wcf1));
			
			//内容数据
			for(PtrainBbsBean d:dataList){
				rowIndex++;colIndex=0;
				ws.setRowView(rowIndex, 400);
				ws.addCell(new Label(colIndex++, rowIndex, rowIndex+"", wcf3));//序号
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(d.getStrflag()), wcf3));//专业类别
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(d.getManagement()), wcf2));//版主
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(d.getIntflag()), wcf3));//回答情况
			}
			wwb.write();
			// 设置输出类型
			response.setContentType("application/download;charset=GBK");
			//设置输出文件头
			response.setHeader("Content-disposition","attachment; filename="+convert("知识学习专业版主.xls"));
			wwb.close();
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**	 
	 * 查询统计_自助需求情况 导出Excel
	 */
	public void expRequser(List<PtrainReqBean> dataList,HttpServletResponse response){
		try {
			ServletOutputStream sos = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(sos);
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			//设置标题行单元格格式[居中加粗]
			WritableCellFormat wcf1 = this._formatCellStyle(10, true,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			//设置内容格式[居左]
			WritableCellFormat wcf2 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.LEFT,
					jxl.format.VerticalAlignment.CENTRE);
			wcf2.setWrap(true);// 自动换行
			//设置内容格式[居中]
			WritableCellFormat wcf3 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			
			//设置初始行数|列数
			int rowIndex = 0, colIndex = 0;
			//添加标题内容
			ws.setRowView(rowIndex, 12*21);	//行高
			ws.setColumnView(colIndex, 7);	//列宽
			ws.addCell(new Label(colIndex++, rowIndex,"序号",wcf1));
			ws.setColumnView(colIndex, 21);
			ws.addCell(new Label(colIndex++, rowIndex,"专业类别",wcf1));
			ws.setColumnView(colIndex, 39);
			ws.addCell(new Label(colIndex++, rowIndex,"项目名称",wcf1));
			ws.setColumnView(colIndex, 15);
			ws.addCell(new Label(colIndex++, rowIndex,"培训类别",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"状态",wcf1));
			ws.setColumnView(colIndex, 14);
			ws.addCell(new Label(colIndex++, rowIndex,"办班时间",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"报名人数",wcf1));
			
			//内容数据
			for(PtrainReqBean data:dataList){
				rowIndex++;colIndex=0;
				ws.setRowView(rowIndex, 252);
				String reqtypetemp="";
				for(PtrainReqReqtypeEnum reqtypeEum:PtrainReqReqtypeEnum.values()){
					if(data.getReqtype().equals(reqtypeEum.getKey()+"")){
						reqtypetemp=reqtypeEum.getDesc();break;
					}
				}
				boolean tempBool="1".equals(func.Trim(data.getState()));
				ws.addCell(new Label(colIndex++, rowIndex, rowIndex+"", wcf3));//序号
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getSpecid()), wcf2));//专业类别
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getItemname()), wcf2));//项目名称
				ws.addCell(new Label(colIndex++, rowIndex, reqtypetemp, wcf3));//培训类别
				ws.addCell(new Label(colIndex++, rowIndex, tempBool?"已办班":"待办班", wcf3));//状态
				ws.addCell(new Label(colIndex++, rowIndex, tempBool?func.Trim(data.getMaintime()):"", wcf3));//办班时间
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getIntflag()), wcf3));//报名人数
			}
			wwb.write();
			// 设置输出类型
			response.setContentType("application/download;charset=GBK");
//			response.setContentType("application/msexcel;charset=GBK");
			//设置输出文件头
			response.setHeader("Content-disposition","attachment; filename="+convert("自助需求情况详细.xls"));
			wwb.close();
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**	 
	 * 查询统计_自助需求情况 已报名人员 导出Excel
	 */
	public void expRequserSign(List<PtrainRequserBean> dataList,String specname,String itemname,HttpServletResponse response){
		try {
			ServletOutputStream sos = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(sos);
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			//设置标题行单元格格式[居中加粗]
			WritableCellFormat wcf1 = this._formatCellStyle(10, true,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			//设置内容格式[居左]
			WritableCellFormat wcf2 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.LEFT,
					jxl.format.VerticalAlignment.CENTRE);
			wcf2.setWrap(true);// 自动换行
			//设置内容格式[居中]
			WritableCellFormat wcf3 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			
			//设置初始行数|列数
			int rowIndex = 0, colIndex = 0;
			ws.setRowView(rowIndex, 12*21);	//行高
			//添加 表头信息
			ws.mergeCells(0, 0, 1, 0);
			ws.addCell(new Label(0, rowIndex,"专业类别："+specname,wcf2));
			ws.mergeCells(2, 0, 5, 0);
			ws.addCell(new Label(2, rowIndex,"项目名称："+itemname,wcf2));
			//添加标题内容
			rowIndex = 1;
			colIndex=0;
			ws.setRowView(rowIndex, 12*21);	//行高
			ws.setColumnView(colIndex, 7);	//列宽
			ws.addCell(new Label(colIndex++, rowIndex,"序号",wcf1));
			ws.setColumnView(colIndex, 32);
			ws.addCell(new Label(colIndex++, rowIndex,"部门名称",wcf1));
			ws.setColumnView(colIndex, 29);
			ws.addCell(new Label(colIndex++, rowIndex,"班组名称",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"姓名",wcf1));
			ws.setColumnView(colIndex, 20);
			ws.addCell(new Label(colIndex++, rowIndex,"报名时间",wcf1));
			
			//内容数据
			for(PtrainRequserBean data:dataList){
				rowIndex++;colIndex=0;
				ws.setRowView(rowIndex, 252);
				ws.addCell(new Label(colIndex++, rowIndex, (rowIndex-1)+"", wcf3));//序号
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getDeptname()), wcf2));//部门名称
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getGroupname()), wcf2));//班组名称
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getUserid()), wcf3));//姓名
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getRegdate()), wcf3));//报名时间
			}
			wwb.write();
			// 设置输出类型
			response.setContentType("application/download;charset=GBK");
//			response.setContentType("application/msexcel;charset=GBK");
			//设置输出文件头
			response.setHeader("Content-disposition","attachment; filename="+convert("自助需求情况已报名人员详细.xls"));
			wwb.close();
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**	 
	 * 常态学习_每日三问_试题浏览 导出Excel
	 */
	public void expAskitemSkim(List<PtrainAskitemBean> dataList,HttpServletResponse response){
		try {
			ServletOutputStream sos = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(sos);
			WritableSheet ws = wwb.createSheet("Sheet1", 0);
			
			//设置标题行单元格格式[居中加粗]
			WritableCellFormat wcf1 = this._formatCellStyle(10, true,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			//设置内容格式[居左]
			WritableCellFormat wcf2 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.LEFT,
					jxl.format.VerticalAlignment.CENTRE);
			wcf2.setWrap(true);// 自动换行
			//设置内容格式[居中]
			WritableCellFormat wcf3 = this._formatCellStyle(10, false,
					jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
					jxl.format.Alignment.CENTRE,
					jxl.format.VerticalAlignment.CENTRE);
			
			//设置初始行数|列数
			int rowIndex = 0, colIndex = 0;
			//添加标题内容
			ws.setRowView(rowIndex, 500);	//行高
			ws.setColumnView(colIndex, 7);	//列宽
			ws.addCell(new Label(colIndex++, rowIndex,"序号",wcf1));
			ws.setColumnView(colIndex, 13);
			ws.addCell(new Label(colIndex++, rowIndex,"题型",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"专业类别",wcf1));
			ws.setColumnView(colIndex, 109);
			ws.addCell(new Label(colIndex++, rowIndex,"题目",wcf1));
			ws.setColumnView(colIndex, 12);
			ws.addCell(new Label(colIndex++, rowIndex,"答案",wcf1));
			ws.setColumnView(colIndex, 30);
			ws.addCell(new Label(colIndex++, rowIndex,"选项A",wcf1));
			ws.setColumnView(colIndex, 30);
			ws.addCell(new Label(colIndex++, rowIndex,"选项B",wcf1));
			ws.setColumnView(colIndex, 30);
			ws.addCell(new Label(colIndex++, rowIndex,"选项C",wcf1));
			ws.setColumnView(colIndex, 30);
			ws.addCell(new Label(colIndex++, rowIndex,"选项D",wcf1));
			ws.setColumnView(colIndex, 30);
			ws.addCell(new Label(colIndex++, rowIndex,"选项E",wcf1));
			ws.setColumnView(colIndex, 30);
			ws.addCell(new Label(colIndex++, rowIndex,"选项F",wcf1));
			ws.setColumnView(colIndex, 30);
			ws.addCell(new Label(colIndex++, rowIndex,"选项G",wcf1));
			
			//内容数据
			for(PtrainAskitemBean data:dataList){
				rowIndex++;colIndex=0;
				ws.setRowView(rowIndex, 252*2);
				String type=func.Trim(data.getTypeid()),answer1=func.Trim(data.getAnswer1());
				if(type.indexOf(PtrainConstant.TYPE_JUDGE)>-1)answer1=answer1.equals("1")?"正确":(answer1.equals("0")?"错误":"");
				ws.addCell(new Label(colIndex++, rowIndex, rowIndex+"", wcf3));//序号
				ws.addCell(new Label(colIndex++, rowIndex, type.replace("题", ""), wcf3));//题型
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getStrflag()), wcf2));//专业类别
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getTopic()), wcf2));//题目
				ws.addCell(new Label(colIndex++, rowIndex, answer1, wcf2));//答案
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getOption1()), wcf2));//选项A
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getOption2()), wcf2));//选项B
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getOption3()), wcf2));//选项C
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getOption4()), wcf2));//选项G
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getOption5()), wcf2));//选项E
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getOption6()), wcf2));//选项F
				ws.addCell(new Label(colIndex++, rowIndex, func.Trim(data.getOption7()), wcf2));//选项G
			}
			wwb.write();
			// 设置输出类型
			response.setContentType("application/download;charset=GBK");
//			response.setContentType("application/msexcel;charset=GBK");
			//设置输出文件头
			response.setHeader("Content-disposition","attachment; filename="+convert("试题浏览详细.xls"));
			wwb.close();
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//============================辅助方法=============================
	public static String convert(String str){
	     try{
	       //把ISO编译过的字符转译为GBK
	       return new String(str.getBytes("GBK"),"ISO-8859-1");
	     }catch(Exception e){return null;}
	}
}