package com.nomen.ntrain.ptrain.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeTest {

public static void main(String[] args)
 {
	  InputStreamReader isr=new InputStreamReader(System.in);
	  BufferedReader br = new BufferedReader(isr);
	  String inString;
  
	  try {
		  SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd"); 
	   
		   while((inString = br.readLine())!=null)
		   {
			    Date date = f.parse(inString);
			    Calendar calendar=Calendar.getInstance();
			    calendar.setTime(date);
			    System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
		   }
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
 }
}
