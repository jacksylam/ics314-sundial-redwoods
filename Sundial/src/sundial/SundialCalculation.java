package sundial;

import java.util.Calendar;

public class SundialCalculation extends Throwable {

	private double latitude;
	private double longitude;
	private Calendar date;
	private boolean dayLightSavings;

	public SundialCalculation(double latitude, double longitude, Calendar date, boolean dayLightSavings) {
		this.date = date;
		this.latitude = Math.abs(latitude);
		this.longitude = Math.abs(longitude);
		this.dayLightSavings = dayLightSavings;
	}
	
	public double getGnomeAngle(){
		return latitude;
	}
	
	public double calculateHourline(double hour){
		double timeMeasruredFromNoonInDegrees;
		double angleHourLine;  //in radians
		
		if(sanitizeInput() == false){
			throw new IllegalArgumentException("Latitude and longitude outside of range");
		}
		
		if(hour > 12){
			double tempHour = hour - 12;
			timeMeasruredFromNoonInDegrees = tempHour * 15;
		}
		else{
			double tempHour = 12 - hour;
			timeMeasruredFromNoonInDegrees = tempHour * 15;
		}
		
		timeMeasruredFromNoonInDegrees = Math.toRadians(timeMeasruredFromNoonInDegrees);
		angleHourLine = Math.atan(Math.sin(Math.toRadians(latitude))/Math.tan(timeMeasruredFromNoonInDegrees));  //in radians
		
		double standardMeridian = calculateMeridian();
		double diffLongitude = longitude - standardMeridian;
		double degreeAddToAngle = diffLongitude * 4;
		angleHourLine = angleHourLine - Math.toRadians(degreeAddToAngle);
		
		double eot = calculateEOT() * 4;
		angleHourLine = angleHourLine + Math.toRadians(eot);
		
		return angleHourLine;
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
		b = Math.toRadians(b);
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
