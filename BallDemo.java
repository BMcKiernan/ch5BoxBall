import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;


/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Bill Crosbie
 * @version 2015-March-BB
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    public Canvas myCanvas;
    private Rectangle2D damnRectangle;
    private Graphics2D graphic;
    
    
    private Shape rectangle;
    
    
    public void setCanvasBeforeBoxBounce(int width, int height)
    {
        myCanvas = new Canvas("Box Bounce",width,height,Color.white);
    }
    
    
    
    public void boxBounce(){
        if(myCanvas==null)
        {
            System.out.println("yo dog initialize setCanvasBeforeBoxBounce(...)");
        }
        else
            {
        
            drawARECTANGLE();
            
             
             Dimension size = myCanvas.getSize();
            int width = size.width;
            width -= 10;
            int height = size.height; 
            height -= 10;
            
         
           
            BoxBall ball = new BoxBall(50, 50, 75,width,height, Color.BLUE, myCanvas);
            ball.draw();             
           
           
        while(true){
           myCanvas.wait(25);
           ball.move();
           myCanvas.draw(damnRectangle);
        }
             }
    }
    
    public void drawARECTANGLE()
    {
        Dimension size = myCanvas.getSize();
        double x = 5;
        double y = 5;
        double widthToBeFixed = size.width;
        double heightToBeFixed = size.height;
        double width = widthToBeFixed-10.00;
        double height = heightToBeFixed-10.00;

        rectangle = (new Rectangle2D.Double(x, y, width, height));
        myCanvas.draw(rectangle);
    }
    
    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
}
