import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Cat is the starting animal for the game
 * 
 * @author (Mark & Edward) 
 * @version (May 21 2025)
 */
public class Cat extends Actor
{
    SimpleTimer animationTimer = new SimpleTimer();
    private GreenfootImage[] idle = new GreenfootImage[18];
    int imageIndex = 0;
    
     //Constructor for cat
    public Cat()
    {
        for (int i=0; i<idle.length; i++)
        {
            idle[i] = new GreenfootImage ("images/cat-idle/cat" + i + ".png");
            idle[i].scale (70,60);
        }
        
        setImage (idle[0]);
        animationTimer.mark();
    }
    
    public void act() {
        animateCat();
        moveCat();
    } 

    //animates the Cat
    public void animateCat() 
    {
        if (animationTimer.millisElapsed() > 150) 
        {
            setImage(idle[imageIndex]);
            imageIndex = (imageIndex + 1) % idle.length;
            animationTimer.mark();
        }
    }
    
    public void moveCat() {
        // Cat falls
        int speed = 4;  
        setLocation(getX(), getY() + speed); 

        // Get the width and height of the cat
        int width = getImage().getWidth();
        int height = getImage().getHeight();

        // Check Y boundaries (top and bottom)
        if (getY() > getWorld().getHeight() - height / 2) 
        {
            setLocation(getX(), getWorld().getHeight() - height / 2); 
        }

        if (getY() < height / 2) 
        {
            //GAME OVER
            setLocation(getX(), height / 2);
        }

        // Check X boundaries (left and right)
        if (getX() > getWorld().getWidth() - width / 2) 
        {
            setLocation(getWorld().getWidth() - width / 2, getY());
        }

        if (getX() < width / 2) 
        {
            setLocation(width / 2, getY()); 
        }
    }
}
