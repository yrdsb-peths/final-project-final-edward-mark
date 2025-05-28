import greenfoot.*;


/**
 * The sheep is the fourth animal
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
        return new Wolf();
    }
}
