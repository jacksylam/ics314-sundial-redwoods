package sundial;

import java.util.Calendar;
import java.util.Date;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Date temp = new Date();
		Calendar temp2 = Calendar.getInstance();
		temp2.setTime(temp);
		SundialCalculation test = new SundialCalculation(21.29, -144, temp2);
		//double print = test.calculateHourline(13);
		double[] temp3 = test.getModifiedHourAngles();
		//print = Math.toDegrees(print);
		//System.out.println(print);
		
		for(int i = 0; i<temp3.length; i++){
			System.out.println(Math.toDegrees(temp3[i]));
		}
	}

}
