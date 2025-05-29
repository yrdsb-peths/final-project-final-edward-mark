import greenfoot.*;


/**
 * The chicken is the second animal.
 * 
 * @author (Mark Ku & Edward Wang) 
 * @version (May 2025)
 */

public class Chicken extends Animal 
{
    private GreenfootImage[] idle = new GreenfootImage[9];
    private int imageIndex = 0;

    //Constructor
    public Chicken() 
    {
        // Animates chicken
        for (int i = 0; i < idle.length; i++) 
        {
            idle[i] = new GreenfootImage("images/chicken-idle/chicken" + i + ".png");
            idle[i].scale(100, 80);
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
    
    // Merges and creates cat
    @Override
    protected Animal createMergedAnimal()
    {
        return new Cat();
    }
}
