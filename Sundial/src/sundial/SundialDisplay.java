// Import the graphics classes.
import java.awt.*;
import javax.swing.*;

/**
 * Meant to be used with the Redwoods sundial project.  Used to display a sundial
 * with gnomon and hour lines in separate windows.
 * 
 * HOW TO USE: 
 * -Create an instance of SundialDisplay, using the output returned from getModifiedHourAngles()
 * method in SundialCalculation class.
 * -Call the displayWindow() method on the instance of SundialDisplay.
 * 
 * @author Troy S. Kobayashi-Bautista
 *
 */
public class SundialDisplay{
	
	//13-element array of doubles storing the hour line angles IN RADIANS
	//element 0 is 6AM; element 12 is 6PM
	//retrieved from getModifiedHourAngles() in SundialCalculations class
	protected double[] hourAngles;
	
	//dimensions of the displayed frame
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 700;
			
	//dimensions of sundial rectangle
	private static final int SUN_WIDTH = 700;
	private static final int SUN_HEIGHT = 500;
			
	//origin x-coordinate
	private static final int ORIGIN_X = SUN_WIDTH/2;
	
	//origin y-coordinate is equal to SUN_HEIGHT
	
	
	/**
	 * Inner class that acts as a panel for displaying the sundial
	 * Overrides the paintComponent method.
	 * 
	 * @author Troy
	 *
	 */
	private class SundialPanel extends JPanel{

		/**
		 * Constructor
		 */
	    public SundialPanel(){
	        super();
	    }
	
	    /**
	     * Overrides the paint method to draw the outer rectangle,
	     * hourlines, and gnomon line
	     * 
	     * @param	Graphics g
	     */
	    public void paintComponent(Graphics g){
	        //draw the rectangle to represent the sundial
	        g.drawRect(0, 0, SUN_WIDTH, SUN_HEIGHT);
	        
	        //draw vertical gnomon line
	        g.drawLine(ORIGIN_X, SUN_HEIGHT, ORIGIN_X, 0);
	        
	        //loop through array of angles and call helper method on each
	        for(int i = 0; i < hourAngles.length; i++){
	        	this.paintHourLine(g, hourAngles[i]);
	        }
	    }
	    
	    /**
	     * Private method for use with overridden paintComponent method
	     * Paints an hour line based on given double as angle from gnomon
	     * 
	     * @param	Graphics g
	     * @param	double angle from gnomon line
	     */
	    private void paintHourLine(Graphics g, double angle){
	    	int xtarget = 0;
	    	int ytarget = 0;
	    	
	    	//use tangent function to find correct x-coordinate of hour line's
	    	//non-origin point
	    	xtarget = ORIGIN_X + ((int) (Math.tan(angle) * SUN_HEIGHT));
	    
	    	/*
	    	 * Special calculations made if line hits left or right wall of sundial:
	    	 */
	    	
	    	//if line hits left wall
	    	if(xtarget < 0){
	    		xtarget = 0;
	    		//calculate target y-coordinate using tangent function and
	    		//complementary angle of abs value of given angle (pi/2 - angle)
	    		double newangle = (Math.PI/2.0) - Math.abs(angle);
	    		ytarget = SUN_HEIGHT - ((int) (Math.tan(newangle)*(SUN_WIDTH/2)));
	    	}
	    	//if line hits right wall
	    	else if(xtarget > SUN_WIDTH){
	    		xtarget = SUN_WIDTH;
	    		//calculate target y-coordinate using tangent function and
	    		//complementary angle of given angle (pi/2 - angle)
	    		double newangle = (Math.PI/2.0) - Math.abs(angle);
	    		ytarget = SUN_HEIGHT - ((int) (Math.tan(newangle)*(SUN_WIDTH/2)));
	    	}
	    	
	   
	    	//draw the line using calculated x and y for non-origin point
	    	g.drawLine(xtarget, ytarget, ORIGIN_X, SUN_HEIGHT);
	    }
	    
	}
	
	/**
	 * Constructor for a Sundial Display object
	 * @param hourAngles; array of doubles with 13 elements
	 * 			0 representing 6AM to 12 representing 6PM
	 */
	public SundialDisplay(double[] hourAngles){
		this.hourAngles = hourAngles;
	}
	
	
	/**
	 * Creates a frame for displaying the sundial in a new window.
	 */
	public void displayWindow(){
        JFrame frame = new JFrame("stuff");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);

        SundialPanel panel = new SundialPanel();
        frame.setContentPane(panel);          
        frame.setVisible(true);                   
	}
	
}
