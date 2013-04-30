package sundial;

// Import the graphics classes.
import java.awt.*;
import javax.swing.*;

/**
 * Meant to be used with the Redwoods sundial project.  Used to display a gnomon
 * in profile with the appropriate angle of incline.
 * 
 * HOW TO USE: 
 * -Create an instance of GnomonDisplay, using the output returned from getGnomeAngle()
 * method in SundialCalculation class.
 * -Call the displayWindow() method on the instance of GnomonDisplay.
 * 
 * @author Troy S. Kobayashi-Bautista
 *
 */
public class GnomonDisplay{
	
	//double representing the angle of the gnomon's incline
	protected double latitude;
	
	//dimensions of the displayed frame
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 700;
			
	//dimensions of gnomon's bottom edge
	private static final int GNO_HEIGHT = 500;
	
	
	/**
	 * Inner class that acts as a panel for displaying the gnomon
	 * Overrides the paintComponent method.
	 * 
	 * @author Troy
	 *
	 */
	private class GnomonPanel extends JPanel{

		/**
		 * Constructor
		 */
	    public GnomonPanel(){
	        super();
	    }
	
	    /**
	     * Overrides the paint method to draw gnomon
	     * 
	     * @param	Graphics g
	     */
	    public void paintComponent(Graphics g){
	    	//calculate uppermost corner of gnomon
	    	int xtarget = (int)(Math.tan(latitude) * GNO_HEIGHT);
	    	
	    	//draw the gnomon bottom edge
	    	g.drawLine(0,  0, 0, GNO_HEIGHT);
	    	
	    	//draw the gnomon hypotenuse
	    	g.drawLine(0, GNO_HEIGHT, xtarget, 0);
	    	
	    	//draw the gnomon vertical edge
	    	g.drawLine(0, 0, xtarget, 0);
	    }
	    
	}
	
	/**
	 * Constructor for a Gnomon Display object
	 * @param doube, latitude angle
	 */
	public GnomonDisplay(double latitude){
		this.latitude = latitude;
	}
	
	
	/**
	 * Creates a frame for displaying the sundial in a new window.
	 */
	public void displayWindow(){
        JFrame frame = new JFrame("Gnomon");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);

        GnomonPanel panel = new GnomonPanel();
        frame.setContentPane(panel);          
        frame.setVisible(true);                   
	}
	
}
