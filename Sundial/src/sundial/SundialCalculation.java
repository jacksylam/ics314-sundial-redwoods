package sundial;

import java.util.Calendar;

public class SundialCalculation {

	double latitude;
	double longitude;
	Calendar date;

	public SundialCalculation(double latitude, double longitude, Calendar date) {
		this.date = date;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getGnomeAngle(){
		return latitude;
	}
	
	public double calculateHourline(int hour){
		double timeMeasruredFromNoonInDegrees;
		double angleHourLine;
		if(hour > 12){
			int tempHour = hour - 12;
			timeMeasruredFromNoonInDegrees = tempHour * 15;
		}
		else{
			int tempHour = 12 - hour;
			timeMeasruredFromNoonInDegrees = tempHour * 15;
		}
		
		
		angleHourLine = Math.atan(Math.sin(latitude)/Math.tan(timeMeasruredFromNoonInDegrees));
		
		return 0;
	}
	
	private double calculateMeridian(){
		return 0;
	}
	
	private double calculateEOT(){
		int dayOfYear = date.get(Calendar.DAY_OF_YEAR);
		double b = ((dayOfYear-81)/365)*360;
		double e = 9.87*Math.sin(2*b) - 7.53*Math.cos(b) - 1.5*Math.sin(b);
		return e;
	}
	
	private boolean sanitizeInput(){
		return true;
	}

}
