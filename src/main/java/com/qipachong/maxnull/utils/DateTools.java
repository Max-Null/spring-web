
package com.qipachong.maxnull.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateTools {

	public enum DateTypeEnum {
		hour,seven,fifteen, thirty;
	}


	/**
	 * 
	 * @Title: DataCenterController
	 * @param: @param rangeType
	 * @param: @return    
	 * @return: List<String>    
	 * @throws
	 * @Creator: mqc
	 * @Description: 鏍规嵁鐢ㄦ埛閫夋嫨鐨勬棩鏈熺被鍨嬪皝瑁呮煡璇㈡暟鎹殑寮�/缁撴潫鏃堕棿
	 * @Date: 2016-2-25 涓嬪崍3:07:52
	 * @Modifier: 
	 * @Date: 
	 * @Remark:
	 * @Version: V1.0
	 */
	public static List<String> convertDateRange(DateTypeEnum rangeType){
		List<String> list = new ArrayList<String>();

		SimpleDateFormat sdfb=new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		SimpleDateFormat sdfe=new SimpleDateFormat("yyyy-MM-dd HH:59:59");
		SimpleDateFormat sdfd=new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat sdff=new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Calendar c = Calendar.getInstance();

		if(rangeType == DateTypeEnum.hour){
			int hour = c.get(Calendar.HOUR_OF_DAY);
			list.add(sdfb.format(c.getTime()) + "," + sdfe.format(c.getTime()) + ",h");
			for(int k = 0; k<hour; k++){
				c.add(Calendar.HOUR_OF_DAY, -1);
				list.add(sdfb.format(c.getTime()) + "," + sdfe.format(c.getTime()) + ",h");
			}
			return list;
		}else if(rangeType == DateTypeEnum.seven){
			list.add(sdfd.format(c.getTime()) + "," + sdff.format(c.getTime()) + ",d");
			for(int i = 1; i<=6; i++){
				Calendar cs = Calendar.getInstance();
				cs.add(cs.DAY_OF_MONTH, -i);
				list.add(sdfd.format(cs.getTime()) + "," + sdff.format(cs.getTime()) + ",d");
			}
			return list;
		}else if(rangeType == DateTypeEnum.fifteen){
			list.add(sdfd.format(c.getTime()) + "," + sdff.format(c.getTime()) + ",d");
			for(int i = 1; i<=14; i++){
				Calendar cs = Calendar.getInstance();
				cs.add(cs.DAY_OF_MONTH, -i);
				list.add(sdfd.format(cs.getTime()) + "," + sdff.format(cs.getTime()) + ",d");
			}
			return list;
		}else if(rangeType == DateTypeEnum.thirty){
			list.add(sdfd.format(c.getTime()) + "," + sdff.format(c.getTime()) + ",d");
			for(int i = 1; i<=29; i++){
				Calendar cs = Calendar.getInstance();
				cs.add(cs.DAY_OF_MONTH, -i);
				Calendar smallCs = Calendar.getInstance();
				smallCs.set(2016, 6, 8, 0, 0, 0); 

				if(cs.before(smallCs)) {
					break;
				}
				list.add(sdfd.format(cs.getTime()) + "," + sdff.format(cs.getTime()) + ",d");
			}
			return list;
		}
		return null;
	}

	/**
	 * 
	 * @Title: DataCenterController
	 * @param: @param rangeType
	 * @param: @return    
	 * @return: List<String>    
	 * @throws
	 * @Creator: luodezhao
	 * @Description: 鏍规嵁鐢ㄦ埛閫夋嫨鐨勬棩鏈熺被鍨嬪皝瑁呮煡璇㈡暟鎹殑寮�/缁撴潫鏃堕棿鐨勬�鏃堕棿锛岃�涓嶆槸浠庝腑闂存柇寮�
	 * @Date: 2016-7-7 涓嬪崍14:42:52
	 * @Modifier: 
	 * @Date: 
	 * @Remark:
	 * @Version: V1.0
	 */
	public static String getDateRange(DateTypeEnum rangeType){
		String a  = "";
		SimpleDateFormat sdfb=new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		SimpleDateFormat sdfe=new SimpleDateFormat("yyyy-MM-dd HH:59:59");
		SimpleDateFormat sdfd=new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat sdff=new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Calendar c = Calendar.getInstance();
		String todayEnd = sdfe.format(c.getTime());

		if(rangeType == DateTypeEnum.hour ){
			int hour = c.get(Calendar.HOUR_OF_DAY);
			c.add(Calendar.HOUR_OF_DAY, -hour);
			a = sdfb.format(c.getTime()) + "," + todayEnd + ",h";


			return a;
		}else if(rangeType == DateTypeEnum.seven){
			Calendar cs = Calendar.getInstance();
			cs.add(cs.DAY_OF_MONTH, -6);
			a = sdfd.format(cs.getTime()) + "," + todayEnd + ",d";

			return a;
		}else if(rangeType == DateTypeEnum.fifteen){

			Calendar cs = Calendar.getInstance();
			cs.add(cs.DAY_OF_MONTH, -14);
			a = sdfd.format(cs.getTime()) + "," + todayEnd + ",d";

			return a;
		}else if(rangeType == DateTypeEnum.thirty){
			Calendar cs = Calendar.getInstance();
			Calendar smallCs = Calendar.getInstance();
			smallCs.set(2016, 6, 8, 0, 0, 0); 
			cs.add(cs.DAY_OF_MONTH, -29);

			if(cs.before(smallCs)) {
				a= sdfd.format(smallCs.getTime()) + "," + todayEnd + ",d";

			}else {
				a= sdfd.format(cs.getTime()) + "," + todayEnd + ",d";
			}

			return a;
		}
		return null;
	}

	/**
	 * 
	 * @Title: DateTools
	 * @param: @param timeStr
	 * @param: @return    
	 * @return: List<String>    
	 * @throws
	 * @Creator: ldz
	 * @Description: 将一个时间范围字符串，以天/小时 为单位拆分 
	 * @Date: 2016-8-8 下午2:38:11
	 * @Modifier: 
	 * @Date: 
	 * @Remark:
	 * @Version: V1.0
	 */
	public static List<String> getDateList(String timeStr) throws Exception{
		List<String> timeList = new ArrayList<String>();
		String[] timeArr = timeStr.split(",");
		SimpleDateFormat dateBeginFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat dateEndFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		SimpleDateFormat normalFormat =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat hourBeginFormat=new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		SimpleDateFormat hourEndFormat=new SimpleDateFormat("yyyy-MM-dd HH:59:59");
		Date benginDate=(Date) normalFormat.parse(timeArr[0]);
		Date endDate=(Date) normalFormat.parse(timeArr[1]);
		Calendar beginCs = Calendar.getInstance();
		beginCs.setTime(benginDate);
		Calendar endCs = Calendar.getInstance();
		endCs.setTime(endDate);
		Calendar currentCs = endCs;
		if(timeArr[2].equals("d"))
		{	
			while(true)
			{
				String	time= dateBeginFormat.format(currentCs.getTime()) + "," + dateEndFormat.format(currentCs.getTime())  + ",d";
				timeList.add(time);
				currentCs.add(currentCs.DAY_OF_MONTH, -1);	
				if(currentCs.before(beginCs))
				{			
					break;
				}
			}
		}else if(timeArr[2].equals("h"))
		{
		while(true)
		{
			String	time= hourBeginFormat.format(currentCs.getTime()) + "," + hourEndFormat.format(currentCs.getTime())  + ",h";
			timeList.add(time);
			currentCs.add(currentCs.HOUR_OF_DAY, -1);	
			if(currentCs.before(beginCs))
			{			
				break;
			}
		}
			
		}
		return timeList;
	}
} 
