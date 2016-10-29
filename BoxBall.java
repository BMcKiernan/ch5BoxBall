import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
import java.awt.geom.Rectangle2D;

/**
 * Write a description of class BoxBall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BoxBall
{
    // instance variables - replace the example below with your own
    private Ellipse2D.Double circle;
    private Color color;
    private int xPosition;
    private int yPosition;
    private int diameter;
    private final int bottomWall;
    private final int topWall; 
    private final int leftWall;
    private final int rightWall;
    private Canvas canvas;
    private double ySpeed=5;
    private double xSpeed=5;
    private Random ballPosition = new Random();
    
    

    /**
     * Constructor for objects of class BoxBall
     */
    public BoxBall(int xPos, int yPos, int ballDiameter, int width, int height, Color ballColor, Canvas drawingCanvas)
    {
       diameter = ballDiameter;
       xPosition = xPos;
       yPosition = yPos;
       leftWall = 0;
       rightWall = width;
       bottomWall = height;
       topWall = 0;
       color = ballColor;
       canvas = drawingCanvas;

    }
    
    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }
    
    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }
    
    /**
     * Move this ball according to its position and speed and redraw.
     **/
   public void move()
   {
    
        // remove from canvas at the current position
        erase();
        // compute new position
        yPosition += ySpeed;
        xPosition += xSpeed;
        // check if it has hit the ground
         if (xPosition < leftWall) {
            xSpeed = -xSpeed;
        }
        
        if (xPosition > rightWall-diameter) {
            xSpeed = -xSpeed;
        }
        
        if (yPosition < topWall) {
            ySpeed = -ySpeed;
        }
        
        if (yPosition > bottomWall-diameter ) {
            ySpeed = -ySpeed;
        }
        // draw again at new position
        draw();
      }
    
     /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
