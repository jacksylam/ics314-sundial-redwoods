package sundial;

import java.util.Calendar;

public class SundialCalculation extends Exception {

	private double latitude;
	private double longitude;
	private Calendar date;
	private boolean dayLightSavings;
	boolean easternHemisphere;

	public SundialCalculation(double latitude, double longitude, Calendar date, boolean dayLightSavings) {
		this.date = date;
		if(longitude < 0){
			easternHemisphere = false;
		}
		else{
			easternHemisphere = true;
		}
		this.latitude = Math.abs(latitude);
		this.longitude = Math.abs(longitude);
		this.dayLightSavings = dayLightSavings;
	}
	
	public double getGnomeAngle(){
		return latitude;
	}
	
	public double calculateHourline(double hour) throws IllegalArgumentException {
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
		angleHourLine = Math.atan(Math.sin(Math.toRadians(latitude))*Math.tan(timeMeasruredFromNoonInDegrees));
		
		double standardMeridian = calculateMeridian();
		double diffLongitude;
		double degreeAddToAngle;
		//+ OR - IF GREATER?  NOT SURE NEED TO CHECK.
		if(easternHemisphere == true){
			if(longitude > standardMeridian){
				 diffLongitude = longitude - standardMeridian;
				 degreeAddToAngle = diffLongitude * 4;
				 degreeAddToAngle = degreeAddToAngle/60.0;
				 angleHourLine = angleHourLine + Math.toRadians(degreeAddToAngle);
			}
		
			else{
				 diffLongitude = standardMeridian - longitude;
				 degreeAddToAngle = diffLongitude * 4;
				 degreeAddToAngle = degreeAddToAngle/60.0;
				 angleHourLine = angleHourLine - Math.toRadians(degreeAddToAngle);
			}
		}
		else{//in western hemisphere, need to switch the values around
			if(longitude > standardMeridian){
				 diffLongitude = longitude - standardMeridian;
				 degreeAddToAngle = diffLongitude * 4;
				 degreeAddToAngle = degreeAddToAngle/60.0;
				 angleHourLine = angleHourLine - Math.toRadians(degreeAddToAngle);
			}
		
			else{
				 diffLongitude = standardMeridian - longitude;
				 degreeAddToAngle = diffLongitude * 4;
				 degreeAddToAngle = degreeAddToAngle/60.0;
				 angleHourLine = angleHourLine + Math.toRadians(degreeAddToAngle);
			}
		}
		
		
		double eot = (calculateEOT()/60) * 15;
		angleHourLine = angleHourLine + Math.toRadians(eot);
		
		if(dayLightSavings == true){
			angleHourLine = angleHourLine + Math.toRadians(15);
		}
		
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
	
	//Returns in minutes
	private double calculateEOT(){
		int dayOfYear = date.get(Calendar.DAY_OF_YEAR);
		double b = ((dayOfYear-81)/365.0)*360.0;
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

	public double[] getAllAngles(){
		double[] hourAngles = new double[13];
		for(int i=0;i<13;i++){
			int j = i+6;
			hourAngles[i] = calculateHourline(j);
		}
		return hourAngles;
	}
}
