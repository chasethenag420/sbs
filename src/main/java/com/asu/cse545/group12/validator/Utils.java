package com.asu.cse545.group12.validator;

public class Utils{
	public static boolean isEmpty(String str){
		if(str== null || "".equals(str.trim()))
			return true;
		return false;
	}
	public static boolean isEmpty(Integer num){
		if(num== null)
			return true;
		return false;
	}
}