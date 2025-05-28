import greenfoot.*;


/**
 * The wolf is the third animal.
 * 
 * @author (Mark Ku & Edward Wang) 
 * @version (May 2025)
 */

public class Tiger extends Animal 
{
    private GreenfootImage[] idle = new GreenfootImage[8];
    private int imageIndex = 0;

    //Constructor
    public Tiger() 
    {
        // Animates tiger
        for (int i = 0; i < idle.length; i++) 
        {
            idle[i] = new GreenfootImage("images/tiger-idle/tiger" + i + ".png");
            idle[i].scale(100, 80);
        }
        setImage(idle[0]);
        animationTimer.mark();
    }

    public void act()
    {
        super.act();
    }
    
    protected void animate() 
    {
        if (animationTimer.millisElapsed() > 200) 
        {
            setImage(idle[imageIndex]);
            imageIndex = (imageIndex + 1) % idle.length;
            animationTimer.mark();
        }
    }
    
    @Override
    protected Animal createMergedAnimal()
    {
        return null;
    }
}
