package com.iotek.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyUtils {

	public static List<String> strsToList(String strings) {
		List<String> sList = new ArrayList<>();
		String[] strs = strings.split("");
		for (String string : strs) {
			sList.add(string);
		}
		return sList;
	}
	
	public static List<Integer> strListToIntList(String strList) {
		strList = strList.substring(1);//删除第一个字符
		strList = strList.substring(0, strList.length()-1);//删除最后一个字符
		String[] arrStrs = strList.split(", ");
		List<Integer> arrInt = new ArrayList<>();
		for (String quedIdsArrStr : arrStrs) {
			arrInt.add(Integer.valueOf(quedIdsArrStr));
		}
		return arrInt;
	}
	
	public static Timestamp getCurrentTimeStamp() {
        Date utilDate  = new Date();//util中的date时间
        Timestamp currentTime = new Timestamp(utilDate.getTime());
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return currentTime;
    }
}
