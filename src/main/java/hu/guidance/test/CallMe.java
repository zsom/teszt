package hu.guidance.test;

import java.util.Date;
import hu.guidance.test.scp.SCPUtil;

public class CallMe {
	int number;
	
	public CallMe(int i) {
		int number2 = getNumber();
		
		System.out.println(number2);
		
		this.number = i;
	}
	
	public CallMe() {
		this.number = 1_2_3;
	}
	
	int getNumber() {
		
		throw new NullPointerException();
		
		//return number;
	}
	
	public static void main(String[] args) {
		CallMe cm = new CallMe(5);
		
		SCPUtil a;
		
		Date d1;
		
		java.sql.Date d2;
		
		Object o;
		
		java.lang.Object o2;
		 
		System.out.println(cm.number);
	}
}
