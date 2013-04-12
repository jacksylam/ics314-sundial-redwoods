package sundial;

import java.util.Calendar;

public class SundialCalculation extends Throwable {

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
		
		if(sanitizeInput() == false){
			throw new IllegalArgumentException("Latitude and longitude outside of range");
		}
		
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
		//x mod 15 = y
		//if y < 7.5 Go less
		//if y > 7.5 Go more
		double standardMeridian = (int) longitude/15;
		int remainder = (int) (longitude%15);
		if(remainder == 7){
			double tenths = longitude - (int) longitude;
			if(tenths > 0.5){
				standardMeridian = (standardMeridian+1)*15;
			}
			else{
				standardMeridian = standardMeridian*15;
			}
		}
		else if(remainder < 7.5){
			standardMeridian = standardMeridian*15;
		}
		else{
			standardMeridian = (standardMeridian+1)*15;
		}
		return standardMeridian;
	}
	
	private double calculateEOT(){
		int dayOfYear = date.get(Calendar.DAY_OF_YEAR);
		double b = ((dayOfYear-81)/365)*360;
		double e = 9.87*Math.sin(2*b) - 7.53*Math.cos(b) - 1.5*Math.sin(b);
		return e;
	}
	
	private boolean sanitizeInput(){
		boolean ret = true;
		if(latitude > 90){
			ret = false;
		}
		if(longitude > 190){
			return false;
		}
		
		return ret;
	}

}
