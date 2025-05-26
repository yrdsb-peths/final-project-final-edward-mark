import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wolf here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wolf extends Actor
{
    SimpleTimer animationTimer = new SimpleTimer();
    GreenfootImage[] idle = new GreenfootImage[2];
    /**
     * Constructor
     */
    public Wolf()
    {
        //animates wolf using array and for loop
        for (int i = 0; i < idle.length ; i ++)
        {
            idle[i] = new GreenfootImage("images/wolf-idle/wolfidle" + i + ".png"); 
            idle[i].scale(130,100);
        }
        setImage(idle[0]);
        animationTimer.mark();
    }
    
    /**
     * Animates the wolf
     */
    int imageIndex = 0;
    public void animateWolf()
    {
        if (animationTimer.millisElapsed() > 250) 
        {
            setImage(idle[imageIndex]);
            imageIndex = (imageIndex + 1) % idle.length;
            animationTimer.mark();
        }
    }
    
    public void act()
    {
        //Animates the wolf
        animateWolf();
    }
}
