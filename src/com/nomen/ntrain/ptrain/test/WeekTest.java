package com.nomen.ntrain.ptrain.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeekTest {
	public static void main(String[] args) throws ClassNotFoundException, IOException, ParseException {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("��������ڣ��磺2008-8-8����");
		String str = br.readLine();
		Date date = dateFormatter.parse(str);
		dateFormatter.applyPattern("D");//
		System.out.println("һ���еĵڼ��죺" + dateFormatter.format(date));
		
		dateFormatter.applyPattern("d");
		System.out.println("һ�����еĵڼ��죺" + dateFormatter.format(date));
		
		dateFormatter.applyPattern("w");
		System.out.println("һ���еĵڼ��ܣ�" + dateFormatter.format(date));
		
		dateFormatter.applyPattern("W");
		System.out.println("һ�����еĵڼ��ܣ�" + dateFormatter.format(date));
		
		dateFormatter.applyPattern("E");
		System.out.println("һ�������е�������" + dateFormatter.format(date));
		
		br.close();
	}
}
