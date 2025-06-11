import greenfoot.*;


/**
 * The wolf is the ninth animal.
 * 2 rhinos merge into a tiger
 * 
 * @author (Mark Ku & Edward Wang) 
 * @version (May 2025)
 */

public class Rhino extends Animal 
{
    private GreenfootImage[] idle = new GreenfootImage[3];
    private int imageIndex = 0;

    //Constructor
    public Rhino() 
    {
        // Animates wolf
        for (int i = 0; i < idle.length; i++) 
        {
            idle[i] = new GreenfootImage("images/rhino-idle/rhino" + i + ".png");
            idle[i].scale(120, 90);
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
        if (animationTimer.millisElapsed() > 250) 
        {
            setImage(idle[imageIndex]);
            imageIndex = (imageIndex + 1) % idle.length;
            animationTimer.mark();
        }
    }
    
    // Merges and creates tiger

    protected Animal createMergedAnimal()
    {
        return new Tiger();
    }
}
