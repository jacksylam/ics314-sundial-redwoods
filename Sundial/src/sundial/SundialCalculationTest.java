package sundial;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * JUnit test class for testing SundialCalculation.java
 * @author Jack
 *
 */
public class SundialCalculationTest {

	@Test
	public void testGetModifiedHourAngles() {
		Date temp = new Date(2013,4,27);
		Calendar temp2 = Calendar.getInstance();
		temp2.setTime(temp);
		
		
		SundialCalculation test = new SundialCalculation(21, 144, temp2);
		double[] testArray = {-1.56,-0.92,-0.54,-0.33,-0.19,-0.08,0.006,0.10,0.21,0.35,0.56,0.93,1.57};
		
		double[] orignalArray = test.getModifiedHourAngles();
		for(int i=0;i<testArray.length;i++){
			assertEquals("Equals",testArray[i],orignalArray[i], 0.01);
		}
		
		SundialCalculation test2 = new SundialCalculation(-50, -150, temp2);
		double[] testArray2 = {-1.55,-1.22,-0.91,-0.64,-0.40,-0.18,0.01,0.21,0.42,0.66,0.93,1.24,1.58};
		
		double[] orignalArray2 = test2.getModifiedHourAngles();
		for(int i=0;i<testArray2.length;i++){
			assertEquals("Equals",testArray2[i],orignalArray2[i], 0.01);
		}
		
			
		SundialCalculation test3 = new SundialCalculation(10, 0, temp2);
		double[] testArray3 = {-1.55,-0.56,-0.27,-0.15,-0.08,-0.03,0.01,0.05,0.11,0.18,0.30,0.58,1.58};
		
		double[] orignalArray3 = test3.getModifiedHourAngles();
		for(int i=0;i<testArray3.length;i++){
			assertEquals("Equals",testArray3[i],orignalArray3[i], 0.01);
		}
		
		SundialCalculation test4 = new SundialCalculation(-33, 40, temp2);
		double[] testArray4 = {-1.56,-1.10,-0.74,-0.49,-0.29,-0.13,0.00,0.15,0.31,0.50,0.76,1.12,1.57};
		
		double[] orignalArray4 = test4.getModifiedHourAngles();
		for(int i=0;i<testArray4.length;i++){
			assertEquals("Equals",testArray4[i],orignalArray4[i], 0.01);
		}
		
		SundialCalculation test5 = new SundialCalculation(45, -76, temp2);
		double[] testArray5 = {-1.55,-1.19,-0.87,-0.60,-0.37,-0.17,0.01,0.19,0.390,0.62,0.89,1.22,1.58};
		
		double[] orignalArray5 = test5.getModifiedHourAngles();
		for(int i=0;i<testArray5.length;i++){
			assertEquals("Equals",testArray5[i],orignalArray5[i], 0.01);
		}
	}

}
