import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The dog is the second level of all the animals
 * Created by merging two cats
 * @author (Mark Ku, Edward Wang)
 * @version (May 21, 2025)
 */
public class Dog extends Actor
{
    SimpleTimer animationTimer = new SimpleTimer();
    GreenfootImage[] idle = new GreenfootImage[11];
    private boolean hasMerged = false;
    
    /**
     * Constructor
     */
    public Dog()
    {
        //animates dog using array and for loop
        for (int i = 0; i < 11 ; i ++)
        {
            idle[i] = new GreenfootImage("images/dog-idle/idle" + i + ".png"); 
            idle[i].scale(100,80);
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
        checkMerge();
    }
    
    //Check if dog is touching other dog, if yes, merges into wolf
    public void checkMerge()
    {
        if (!hasMerged)
        {
            Dog other = (Dog) getOneIntersectingObject (Dog.class);
            if (other!= null && other != this)
            {
                hasMerged = true;
                other.setMerged();
                getWorld().addObject (new Wolf(), getX(), getY());
                
                //Increases score by 2 points
                MyWorld world = (MyWorld) getWorld();
                world.increaseScore(2); 
                
                getWorld().removeObject (other);
                getWorld().removeObject (this);
            }
        }
    }
    public void setMerged()
    {
        hasMerged = true;
    }
}
