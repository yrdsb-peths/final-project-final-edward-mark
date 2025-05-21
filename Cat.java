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
    private boolean hasLanded = false;
    
    
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
        if (!hasLanded)
        {
            moveCat();
            checkIfLanded();
        }
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
    
    public void moveCat() 
    {
        // Cat falls
        int speed = 4;  
        setLocation(getX(), getY() + speed); 
    }
    
    //this method checks if the current cat has landed before 
    //spawning the new Cat
    private void checkIfLanded()
    {
        int height = getImage().getHeight();
        int bottomY = getWorld().getHeight() - height/2;
        if (getY()>= bottomY)
        {
            setLocation (getX(), bottomY);
            hasLanded = true;
            
            MyWorld world = (MyWorld) getWorld();
            world.clearFallingCat();
            world.createCat();
        }
    }
}

