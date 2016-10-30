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
    
    private Random randomLocation = new Random(300);
    private Random randomColor = new Random();
    private Random randomSize = new Random();
    
    
    
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
            width -= 4;
            height -= 4;
            int colorIndex = 1;
            
            for(int i= 1; i<101; i++)
            {
                int r = randomColor.nextInt(255)+1;
                int g = randomColor.nextInt(255)+1;
                int b = randomColor.nextInt(255)+1;
                Random randomRgb = new Random();
                if((r+g+b)>425)
                {
                    int n = randomRgb.nextInt(3);
                    
                    if(n==0)
                    {
                            r = r/2;
                            g = g/2;
                    }
                    
                    if(n==1)
                    {
                        g = g/2;
                        b = b/2;
                    }
                        
                    if(n==2)
                        {
                            b = b/2;
                            r = r/2;
                            
                        }
                    }
                    
                Color colorSet = new Color(r,g,b);
                
                int locationX;
                int locationY;
                Dimension sizeForLocation = myCanvas.getSize();
                int randomXLocation =  sizeForLocation.width;
                int randomYLocation = sizeForLocation.height;
                int ballSize = randomSize.nextInt(24)+10;
                randomXLocation-=(4+ballSize);
                randomYLocation-=(4+ballSize);
                locationX = randomLocation.nextInt(randomXLocation)+2;
                locationY = randomLocation.nextInt(randomYLocation)+2;
                
                
                balls.add(new BoxBall(locationX, locationY, ballSize, width, height, colorSet, myCanvas));
               
            }
             
             for(int indexx = 0; indexx<balls.size(); indexx++)
             {
                BoxBall ball=null;
                ball = balls.get(indexx);
                ball.draw();
            }
             //Need to configure a way to get balls array to ball.draw() for each element in the ArrayList; Cannot find symbol. Need to create and enable scope to reach while(true)
           
             while(true){
                 myCanvas.wait(10);
                 for(int index = 0; index<balls.size(); index++)
                 {
                    BoxBall ball = null;
                    ball = balls.get(index);
                    ball.move();
                    
                    drawARECTANGLE();
                }
                drawARECTANGLE();
            }
        }
    }
    
    private void drawARECTANGLE()
    {
        Dimension size = myCanvas.getSize();
        double x = 2;
        double y = 2;
        double widthToBeFixed = size.width;
        double heightToBeFixed = size.height;
        double width = widthToBeFixed-4.00;
        double height = heightToBeFixed-4.00;
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
