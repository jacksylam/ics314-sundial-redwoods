package sundial;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * This is the User interface for the Sundial Assignment. It is meant to be run
 * with three other classes that provide the calculations of the sundial, as
 * well as the pictures to print out to successfully use the device. The interface
 * itself error checks corresponding to the calculations class in order to provide
 * the best possible data
 *
 * HOW TO USE:
 * -Keep the SundialCalculation.java, GnomonDisplay.java, and SundialDisplay.java
 * classes in the same directory to provide the interface with all needed info.
 *
 * The UI runs on a separate window, and the pictures provided each pop up on
 * a different window, for ease of use.
 *
 * @author Alex Tirgardoon
 *
 */
public class SundialGui extends Applet implements ActionListener {

   //labels to show ease of input
   protected Label title;
   protected Label latitudeLabel;
   protected Label longitudeLabel;
   protected Label dateLabel;
   protected Label info;
   protected Label messageLabel; //notifies user of current process

   //a group of text fields for user input
   protected TextField latitude;
   protected TextField longitude;
   protected TextField month;
   protected TextField day;
   protected TextField year;

   //a checkbox to toggle between daylight savings settings
   protected Checkbox daylightSavings;

   //a button to click to process the information
   protected Button calculate;


   /*
    * A method to provide the arrangement of the user interface.
    * Note there is no layout manager
    */
   public void init() {

      //no layout manager
      //everything is arranged manually
      setLayout(null);
      //size of the window
      setSize(440,330);

      //label of the title (as well as a font to set it apart)
      title = new Label("Sundial - Redwoods");
      title.setFont(new Font("Arial",Font.BOLD, 20));

      //a set of label definitions for the input needed
      //font is defined appropriately for the distinction of user input
      latitudeLabel = new Label("Latitude: ");
      latitudeLabel.setFont(new Font("Arial",Font.PLAIN, 18));
      longitudeLabel = new Label("Longitude: ");
      longitudeLabel.setFont(new Font("Arial",Font.PLAIN, 18));
      Label dateLabel = new Label("Date: ");
      dateLabel.setFont(new Font("Arial",Font.PLAIN, 18));

      //label that never changes to tell user to input values for the sundial
      info = new Label("Please enter the corresponding values");

      //label that changes according to the process needed
      //changes color to red (in further code) to notify if an error was detected
      messageLabel = new Label("Program running.....");
      messageLabel.setFont(new Font("Arial",Font.ITALIC, 14));

      //a button to process the data
      calculate = new Button("Calculate");

      //a checkbox to toggle between daylight savings
      //default is set to "false", which is unchecked
      daylightSavings = new Checkbox("Daylight Savings",false);

      //a set of textfields with defaulted info to help user enter input
      latitude = new TextField("Latitude");
      longitude = new TextField("Longitude");
      month = new TextField("MM");
      day = new TextField("DD");
      year = new TextField("YYYY");

      //this set of code specifies the positions of the GUI components
      //(x position, y position, x width, y width)
      title.setBounds(120,10,1000,20);
      latitudeLabel.setBounds(95,100,90,20);
      latitude.setBounds(190,100,140,20);
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

      //adds the components to the applet itself, one by one
      add(title);
      add(latitudeLabel);
      add(latitude);
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

      //attaches an action to the only component needed for the sundial retrieval
      calculate.addActionListener(this);
   }

   /*
    * Provides the actions performed when the "calculate" button is used.
    * Error checking is embedded in this method as well.
    */
   public void actionPerformed(ActionEvent event){

      //variables to keep the data organized to send to the SundialCalculation.java
      //class as well as the two display classes
      Double latitudeValue;
      Double longitudeValue;
      Calendar calendar;
      Boolean dSavingsState;
      int monthTmp;
      int dayTmp;
      int yearTmp;

      //temporary variables used from user input
      String input;
      Double value;
      int intValue;

      //the following set of code runs if the calculate button is pressed
      if(event.getSource() == calculate) {

         //verifying latitude input
         input = latitude.getText().trim(); //trims any white spaces
         try {
            value = Double.parseDouble(input);
            double absValue = Math.abs(value);
            //error for latitudes over 80 or under 10 because gnome won't work too well
            if ((absValue > 80) || (absValue < 10)) {
               //error message if value entered is out of range
               this.messageLabel.setText("ERROR: Latitude outside of range. Must be -10" +
                  " to -80 or 10 to 80");
               this.messageLabel.setForeground(Color.red); //red for error info
               return;
            }else {
               //sets the corresponding variable to be used later if successful
               latitudeValue = value;
               this.messageLabel.setText("Program running.....");
               this.messageLabel.setForeground(Color.black);
            }
         }catch (NumberFormatException nfe) { //catches error if non numerical value
            this.messageLabel.setText("ERROR: Please enter a numerical value " +
               "for \"latitude\"");
            this.messageLabel.setForeground(Color.red); //red for error info
            return;
         }


         //verifying longitude input
         input = longitude.getText().trim();
         try {
            value = Double.parseDouble(input);
            //error for longitudes that are outside of the range
            if ((value > 180) || (value < -180)) {
               this.messageLabel.setText("ERROR: Longitude outside of range. Must be " +
                  "-180 to 180");
               this.messageLabel.setForeground(Color.red); //red for error info
               return;
            }else {
               //sets the corresponding variable to be used later if successful
               longitudeValue = value;
               this.messageLabel.setText("Program running.....");
               this.messageLabel.setForeground(Color.black);
            }
         }catch (NumberFormatException nfe) { //catches error if non numerical value
            this.messageLabel.setText("ERROR: Please enter a numerical value" +
               " for \"longitude\"");
            this.messageLabel.setForeground(Color.red); //red for error info
            return;
         }


         //verifying month input
         input = month.getText().trim();
         try {
            intValue = Integer.parseInt(input);
            //error for invalid month values
            if (intValue < 1 || intValue > 12) {
               this.messageLabel.setText("ERROR: Month outside of range");
               this.messageLabel.setForeground(Color.red); //red for error info
               return;
            }else {
               //sets the corresponding variable to be used later if successful
               monthTmp = intValue;
               this.messageLabel.setText("Program running.....");
               this.messageLabel.setForeground(Color.black);
            }
         }catch (NumberFormatException nfe) { //catches error if non numerical value
            this.messageLabel.setText("ERROR: Please enter a numerical value" +
               " for \"Month\"");
            this.messageLabel.setForeground(Color.red); //red for error info
            return;
         }

         //verifying day input
         input = day.getText().trim();
         try {
            intValue = Integer.parseInt(input);
            //error for invalid day values
            if (intValue < 1 || intValue > 31) {
               this.messageLabel.setText("ERROR: Day outside of range");
               this.messageLabel.setForeground(Color.red); //red for error info
               return;
            }else {
               //sets the corresponding variable to be used later if successful
               dayTmp = intValue;
               this.messageLabel.setText("Program running.....");
               this.messageLabel.setForeground(Color.black);
            }
         }catch (NumberFormatException nfe) { //catches error if non numerical value
            this.messageLabel.setText("ERROR: Please enter a numerical value" +
               " for \"Day\"");
            this.messageLabel.setForeground(Color.red); //red for error info
            return;
         }


         //verifying year input
         input = year.getText().trim();
         try {
            intValue = Integer.parseInt(input);
            //error for invalid year values
            if (intValue < 2013 || intValue > 2050) {
               this.messageLabel.setText("ERROR: Year outside of range. Must be greater than 2012");
               this.messageLabel.setForeground(Color.red); //red for error info
               return;
            }else {
               //sets the corresponding variable to be used later if successful
               yearTmp = intValue;
               this.messageLabel.setText("Program running.....");
               this.messageLabel.setForeground(Color.black);
            }
         }catch (NumberFormatException nfe) { //catches error if non numerical value
            this.messageLabel.setText("ERROR: Please enter a numerical value" +
               " for \"Year\"");
            this.messageLabel.setForeground(Color.red); //red for error info
            return;
         }

         //verifying the daylight savings checkbox state
         //true if checked, false if not checked
         //sets the corresponding variable to be used later
         if (daylightSavings.getState()){
            dSavingsState = true;
         }else{
            dSavingsState = false;
         }

         //takes the variables inputted by user and converts it into a date object
         calendar = setCalendar(monthTmp, dayTmp, yearTmp);

         //takes corresponding variables inputted and makes a SundialCalculation object
         //the object itself is used for the two display classes
         Date temp = new Date();
         Calendar temp2 = calendar;
         temp2.setTime(temp);
         SundialCalculation test = new SundialCalculation(latitudeValue, longitudeValue, temp2);

         //sets temporary variables based on the SundialCalculation object for output
         double[] temp3 = test.getModifiedHourAngles();
         Double output = test.getGnomeAngle();

         //calls the two corresponding SundialDisplay objects for sundial/gnomon output
         SundialDisplay dis = new SundialDisplay(temp3, dSavingsState);
         dis.displayWindow();
         GnomonDisplay disp = new GnomonDisplay(output);
         disp.displayWindow();

      }

   }

   /*
    * Method to convert a date passed into a date variable to be used for output
    */
   public Calendar setCalendar (int month, int day, int year) {
      Calendar date = Calendar.getInstance();
      date.set(Calendar.YEAR, year);
      date.set(Calendar.MONTH, month);
      date.set(Calendar.DAY_OF_MONTH, day);
      return date;
   }
}
