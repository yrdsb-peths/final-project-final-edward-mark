import greenfoot.*;


/**
 * The sheep is the sixth animal
 * 2 sheeps merge into a cow
 * 
 * @author (Mark Ku & Edward Wang) 
 * @version (May 2025)
 */

public class Sheep extends Animal 
{
    private GreenfootImage[] idle = new GreenfootImage[7];
    private int imageIndex = 0;

    //Constructor
    public Sheep() 
    {
        // Animates sheep
        for (int i = 0; i < idle.length; i++) 
        {
            idle[i] = new GreenfootImage("images/sheep-idle/sheep" + i + ".png");
            idle[i].scale(100, 80);
        }
        setImage(idle[0]);
        animationTimer.mark();
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
    
    // Delegates to Animal class for physics and merging
    public void act()
    {
        super.act();
    }
    
    // Merges and creates cow
    @Override
    protected Animal createMergedAnimal()
    {
        return new Cow();
    }
}
