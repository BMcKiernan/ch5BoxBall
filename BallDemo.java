import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;


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
    private ArrayList<BoxBall> balls = new ArrayList<BoxBall>();
    
    private Random randomLocation = new Random();
    private Random randomColor = new Random();
    
    
    
    public void setCanvasBeforeBoxBounce(int width, int height)
    {
        myCanvas = new Canvas("Box Bounce",width,height,Color.white);
        drawARECTANGLE();
    }
   
    
    public void boxBounce(){
        if(myCanvas==null)
        {
            System.out.println("yo dog initialize setCanvasBeforeBoxBounce(...)");
        }
        else
            {
        
            
            Dimension size = myCanvas.getSize();
            int width = size.width;
            int height = size.height; 
            width -= 10;
            height -= 10;
            int colorIndex = 1;
            
            for(int i= 1; i<6; i++)
            {
                
                int locationX;
                int locationY;
                Dimension sizeForLocation = myCanvas.getSize();
                int randomXLocation =  sizeForLocation.width;
                int randomYLocation = sizeForLocation.height;
                randomXLocation-=20;
                randomYLocation-=20;
                locationX = randomLocation.nextInt(randomXLocation)+1;
                locationY = randomLocation.nextInt(randomYLocation)+1;
                
                if(colorIndex==1)
                {
                     balls.add(new BoxBall(locationX, locationY, 10, width, height, Color.orange, myCanvas));
                }
                else if(colorIndex==2)
                {
                     balls.add(new BoxBall(locationX, locationY, 10, width, height, Color.blue, myCanvas));
                }
                else if(colorIndex==3)
                {
                     balls.add(new BoxBall(locationX, locationY, 10, width, height, Color.green, myCanvas));
                }
                else if(colorIndex==4)
                {
                     balls.add(new BoxBall(locationX, locationY, 10, width, height, Color.magenta, myCanvas));
                }
                else
                {
                     balls.add(new BoxBall(locationX, locationY, 10, width, height, Color.red, myCanvas));
                }
                colorIndex++;
             }
             
             for(int indexx = 0; indexx<balls.size(); indexx++)
             {
                BoxBall ball=null;
                ball = balls.get(indexx);
                ball.draw();
            }
             //Need to configure a way to get balls array to ball.draw() for each element in the ArrayList; Cannot find symbol. Need to create and enable scope to reach while(true)
           
             while(true){
                 myCanvas.wait(25);
                 for(int index = 0; index<balls.size(); index++)
                 {
                    BoxBall ball = null;
                    ball = balls.get(index);
                    ball.move();
                    
                    drawARECTANGLE();
                }
                
            }
        }
    }
    
    private void drawARECTANGLE()
    {
        Dimension size = myCanvas.getSize();
        double x = 5;
        double y = 5;
        double widthToBeFixed = size.width;
        double heightToBeFixed = size.height;
        double width = widthToBeFixed-10.00;
        double height = heightToBeFixed-10.00;
        Shape rectangle = (new Rectangle2D.Double(x, y, width, height));
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
