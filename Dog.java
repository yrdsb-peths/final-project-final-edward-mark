import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The dog is the second level of animals
 * Created by merging two cats
 * @author (Mark Ku, Edward Wang)
 * @version (May 21, 2025)
 */
public class Dog extends Actor
{
    SimpleTimer animationTimer = new SimpleTimer();
    GreenfootImage[] idle = new GreenfootImage[11];
    /**
     * Constructor
     */
    public Dog()
    {
        for (int i = 0; i < 11 ; i ++)
        {
            idle[i] = new GreenfootImage("images/dog-idle/idle" + i + ".png"); 
            idle[i].scale(120,110);
        }
        setImage(idle[0]);
        animationTimer.mark();
    }
    
    /**
     * Animates the dog
     */
    int imageIndex = 0;
    public void animateDog()
    {
        if (animationTimer.millisElapsed() > 200) 
        {
            setImage(idle[imageIndex]);
            imageIndex = (imageIndex + 1) % idle.length;
            animationTimer.mark();
        }
    }
    
    public void act()
    {
        //Animates the dog
        animateDog();
    }
}
