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
		return 0;
	}
	
	private double calculateMeridian(){
		return 0;
	}
	
	private double calculateEOT(){
		int dayOfYear = date.get(Calendar.DAY_OF_YEAR);
		return 0;
	}

}
