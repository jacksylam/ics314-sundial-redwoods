import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.Calendar;
import javax.swing.*; 

public class SundialGui extends Applet implements ActionListener {
	
	protected Label title;
	protected Label lattitudeLabel;
	protected Label longitudeLabel;
	protected Label dateLabel;
	protected Label info;
	protected Label messageLabel;
	
	protected TextField lattitude;
	protected TextField longitude;
	
	protected Checkbox daylightSavings;
	protected Button calculate;
	
	protected TextField month;
	protected TextField day;
	protected TextField year;
	
	
	
	
	public void init() {
		
		setLayout(null);
		setSize(440,330);
		
		//JOptionPane.showMessageDialog(null, null, "title", JOptionPane.PLAIN_MESSAGE);
		
		title = new Label("Sundial - Redwoods");
		title.setFont(new Font("Arial",Font.BOLD, 20));
		
		lattitudeLabel = new Label("Lattitude: ");
		lattitudeLabel.setFont(new Font("Arial",Font.PLAIN, 18));
		
		longitudeLabel = new Label("Longitude: ");
		longitudeLabel.setFont(new Font("Arial",Font.PLAIN, 18));
		
		Label dateLabel = new Label("Date: ");
		dateLabel.setFont(new Font("Arial",Font.PLAIN, 18));
		
		info = new Label("Please enter the corresponding values");
		messageLabel = new Label("Program running.....");
		messageLabel.setFont(new Font("Arial",Font.ITALIC, 14));
		
		
		calculate = new Button("Calculate");
		
		daylightSavings = new Checkbox("Daylight Savings",false);
		
			
		lattitude = new TextField("Lattitude");
		longitude = new TextField("Longitude");
		month = new TextField("MM");
		day = new TextField("DD");
		year = new TextField("YYYY");
		
		
		title.setBounds(120,10,1000,20);
		
		lattitudeLabel.setBounds(90,100,90,20);
		lattitude.setBounds(190,100,140,20);
		
		longitudeLabel.setBounds(80,140,90,20);
		longitude.setBounds(190,140,140,20);
		
		dateLabel.setBounds(120,180,50,20);
		
		info.setBounds(90,40,2000,100);
		
		messageLabel.setBounds(0,300,2000,20);
		
		daylightSavings.setBounds(170,200,200,50);
		
		calculate.setBounds(170,250,100,50);
		
		month.setBounds(190,180,35,20);
		
		day.setBounds(235,180,35,20);
		
		year.setBounds(280,180,50,20);
		
		
		
		
		
		add(title);
		add(lattitudeLabel);
		add(lattitude);
		add(longitudeLabel);
		add(longitude);
		add(dateLabel);
		
		add(info);
		add(messageLabel);
		
		add(calculate);
		
		add(daylightSavings);
		
		add(month);
		add(day);
		add(year);
		
		calculate.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent event){
		
		Double lattitudeValue;
		Double longitudeValue;
		Calendar calendar; 
		int monthTmp;
		int dayTmp;
		int yearTmp;
		
		String input;
		Double value;
		int intValue;
		
		if(event.getSource() == calculate) {
			
			//checking lattitude input
			input = lattitude.getText().trim();
			try {
				value = Double.parseDouble(input);
				if (value > 90) {
					this.messageLabel.setText("ERROR: Latitude outside of range");
					this.messageLabel.setForeground(Color.red);
					return;
				}else {
					lattitudeValue = value;
					this.messageLabel.setText("Program running.....");
					this.messageLabel.setForeground(Color.black);
				}
			}catch (NumberFormatException nfe) {
				this.messageLabel.setText("ERROR: Please enter a numerical value" + 
					" for \"lattitude\"");
				this.messageLabel.setForeground(Color.red);
				return;
			}
			
			
			//checking longitude value
			input = longitude.getText().trim();
			try {
				value = Double.parseDouble(input);
				if (value > 190) {
					this.messageLabel.setText("ERROR: Longitude outside of range");
					this.messageLabel.setForeground(Color.red);
					return;
				}else {
					longitudeValue = value;
					this.messageLabel.setText("Program running.....");
					this.messageLabel.setForeground(Color.black);
				}
			}catch (NumberFormatException nfe) {
				this.messageLabel.setText("ERROR: Please enter a numerical value" + 
					" for \"longitude\"");
				this.messageLabel.setForeground(Color.red);
				return;
			}
			

			//checking dates (month)
			input = month.getText().trim();
			try {
				intValue = Integer.parseInt(input);
				if (intValue < 1 || intValue > 12) {
					this.messageLabel.setText("ERROR: Month outside of range");
					this.messageLabel.setForeground(Color.red);
					return;
				}else { 
					//successful
					monthTmp = intValue;
					this.messageLabel.setText("Program running.....");
					this.messageLabel.setForeground(Color.black);
				}
			}catch (NumberFormatException nfe) {
				this.messageLabel.setText("ERROR: Please enter a numerical value" + 
					" for \"Month\"");
				this.messageLabel.setForeground(Color.red);
				return;
			}
			
			//checking dates (day)
			input = day.getText().trim();
			try {
				intValue = Integer.parseInt(input);
				if (intValue < 1 || intValue > 31) {
					this.messageLabel.setText("ERROR: Day outside of range");
					this.messageLabel.setForeground(Color.red);
					return;
				}else { 
					//successful
					dayTmp = intValue;
					this.messageLabel.setText("Program running.....");
					this.messageLabel.setForeground(Color.black);
				}
			}catch (NumberFormatException nfe) {
				this.messageLabel.setText("ERROR: Please enter a numerical value" + 
					" for \"Day\"");
				this.messageLabel.setForeground(Color.red);
				return;
			}
			
			
			//checking dates (year)
			input = year.getText().trim();
			try {
				intValue = Integer.parseInt(input);
				if (intValue < 2013 || intValue > 2050) {
					this.messageLabel.setText("ERROR: Year outside of range");
					this.messageLabel.setForeground(Color.red);
					return;
				}else { 
					//successful
					yearTmp = intValue;
					this.messageLabel.setText("Program running.....");
					this.messageLabel.setForeground(Color.black);
				}
			}catch (NumberFormatException nfe) {
				this.messageLabel.setText("ERROR: Please enter a numerical value" + 
					" for \"Year\"");
				this.messageLabel.setForeground(Color.red);
				return;
			}
			
			
			System.out.println(lattitudeValue);
			System.out.println(longitudeValue);
			System.out.println(monthTmp);
			System.out.println(dayTmp);
			System.out.println(yearTmp);
			
			
			
		}
		
	}
	
	
	/*public setCalendar (int month, int day, int year) {
	}*/
	
	
}