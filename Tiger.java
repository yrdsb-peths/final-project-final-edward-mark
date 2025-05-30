import greenfoot.*;


/**
 * The tiger is the tenth animal.
 * 
 * @author (Mark Ku & Edward Wang) 
 * @version (May 2025)
 */

public class Tiger extends Animal 
{
    private GreenfootImage[] idle = new GreenfootImage[7];
    private int imageIndex = 0;

    //Constructor
    public Tiger() 
    {
        // Animates tiger
        for (int i = 0; i < idle.length; i++) 
        {
            idle[i] = new GreenfootImage("images/saber-idle/tiger" + i + ".png");
            idle[i].scale(110, 80);
        }
        setImage(idle[0]);
        animationTimer.mark();
    }

    // Delegates to Animal class for physics and merging
    public void act()
    {
        super.act();
    }
    
    // Controls animation speed
    protected void animate() 
    {
        if (animationTimer.millisElapsed() > 200) 
        {
            setImage(idle[imageIndex]);
            imageIndex = (imageIndex + 1) % idle.length;
            animationTimer.mark();
        }
    }
    
    // Final animal - does not merge
    @Override
    protected Animal createMergedAnimal()
    {
        return null;
    }
}
