import greenfoot.*;


/**
 * The wolf is the eigth animal.
 * 
 * @author (Mark Ku & Edward Wang) 
 * @version (May 2025)
 */

public class Wolf extends Animal 
{
    private GreenfootImage[] idle = new GreenfootImage[2];
    private int imageIndex = 0;

    //Constructor
    public Wolf() 
    {
        // Animates wolf
        for (int i = 0; i < idle.length; i++) 
        {
            idle[i] = new GreenfootImage("images/wolf-idle/wolfidle" + i + ".png");
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
    @Override
    protected Animal createMergedAnimal()
    {
        return new Tiger();
    }
}
