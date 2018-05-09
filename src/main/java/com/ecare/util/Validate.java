package com.ecare.util;

import java.util.*;


public class Validate {
	public static boolean isInteger(String s, List<String> messageList) {
	    Scanner sc = new Scanner(s.trim());
	    if(!sc.hasNextInt()) return false;
	    // we know it starts with a valid int, now make sure
	    // there's nothing left!
	    sc.nextInt();
	    return !sc.hasNext();
	}
	
	public static boolean isString(String s, List<String> messageList) {
		if (s.equals(null) || s.isEmpty()) return false;
		return true;
		
	}
	
}
