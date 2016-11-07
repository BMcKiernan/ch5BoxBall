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
 * @contributor Brian McKiernan
 * @11/6/2016
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
    
    
    /**
     * This method intitializes the canvas and also draws a rectangle with all of its walls five 
     * integers less then the dimension of the canvas.
     */
    public void setCanvasBeforeBoxBounce(int width, int height)
    {
        myCanvas = new Canvas("Box Bounce",width,height,Color.white);
        drawARECTANGLE();
    }
   
    /**
     * This is where the magic happens. boxBounce randomly assigns sixteen different balls color within a 
     * certain range to avoid transparent colors, it randomly assigns the diameter of the balls and their
     * location within the size of the rectangle that is srawn within the canvas. 
     */
    public void boxBounce(){
        if(myCanvas==null)
        {
            System.out.println("yo dog initialize setCanvasBeforeBoxBounce(...)");
        }
        else
            {
            /*
             * The code within the for loop does everything from randomly assigning color within a 
             * range to initializing the balls adding them to the array and setting their location
             * within the rectangle.
             */
            
            int colorIndex = 1;
            for(int i= 1; i<16; i++)
            {
                //Randomly assigns red, green, blue colors in the range of 0 to 255. 
                int r = randomColor.nextInt(255)+1;
                int g = randomColor.nextInt(255)+1;
                int b = randomColor.nextInt(255)+1;
                Random randomRgb = new Random();
                
                /*
                 * This conditional statement checks to see if the combined color color values for red
                 * green and blue exceeds 425 because as this value gets higher the balls become more 
                 * transparent.
                 */
                
                if((r+g+b)>425)
                {
                   /*
                    * The random number generate chooses a random combination of two of the rgb colors
                    * and divides their value by 7 so that the value is decreased distinguishing
                    * a single colors and minmizing the transparency.
                    */
                    int n = randomRgb.nextInt(3);
                    if(n==0)
                    {
                        r = r/7;
                        g = g/7;
                    }
                    
                    if(n==1)
                    {
                        g = g/7;
                        b = b/7;
                    }
                        
                    if(n==2)
                    {
                        b = b/7;
                        r = r/7;
                            
                    }
                }
                    
                Color colorSet = new Color(r,g,b);
                //Gets the size of the rectangle to draw the rectangle within.
                Dimension size = myCanvas.getSize();
                int width = size.width;
                int height = size.height; 
                
                width -= 10;
                height -= 10;
                
                int locationX;
                int locationY;
                
                int randomXLocation =  size.width;
                int randomYLocation = size.height;
                
                //creates balls with a random diameter from 12 to 35.
                int ballSize = randomSize.nextInt(24)+12;
                
                randomXLocation-=(10+ballSize);
                randomYLocation-=(10+ballSize);
                //The next line randomly assigns an x location to the ball starting at a minimum x
                //positin of 5 so the ball does not get trapped outside the leftwall of the rectangle.
                locationX = randomLocation.nextInt(randomXLocation)+5;
                //The next line randomly assigns a y location to the ball starting at a minimum y
                //position of 5 so the ball does not get trapped outside the topwall of the rectangle.
                locationY = randomLocation.nextInt(randomYLocation)+5;
                //Adds the balls to the balls array one at a time.
                balls.add(new BoxBall(locationX, locationY, ballSize, width, height, colorSet, myCanvas));
            }
            //Draws the balls initial placement.
             for(int indexx = 0; indexx<balls.size(); indexx++)
             {
                BoxBall ball=null;
                ball = balls.get(indexx);
                ball.draw();
            }
            /*
             * This whil loop controls the movement of the boxballs by redrawing them from the array
             * calling the move method from BoxBall and redrawing the rectangle. Creates the illusion of
             * movement.
             */
             while(true){
                 myCanvas.wait(25);
                 for(int index = 0; index<balls.size(); index++)
                 {
                    BoxBall ball;
                    ball = balls.get(index);
                    ball.move();
                }
            }
        }
    }
    
    /**
     * This method draws a rectangle that receives the dimensions of the canvas and then sets the walls
     * of the rectangle to be five less than the dimensions of the canvas.
     */
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
