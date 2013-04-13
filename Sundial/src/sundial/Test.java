package sundial;

import java.util.Calendar;
import java.util.Date;

import javax.xml.crypto.Data;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hi!");
		System.out.println("Test2");
		
		Date temp = new Date();
		Calendar temp2 = Calendar.getInstance();
		temp2.setTime(temp);
		SundialCalculation test = new SundialCalculation(21.29, 157.81, temp2, false);
		double print = test.calculateHourline(13);
		print = Math.toDegrees(print);
		System.out.println(print);
	}

}
