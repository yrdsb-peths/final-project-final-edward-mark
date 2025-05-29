import greenfoot.*;

/**
 * The pig is the fifth animal.
 * 2 pigs merge into a sheep
 * 
 * @author (Mark Ku & Edward Wang) 
 * @version (May 2025)
 */
public class Pig extends Animal {
    private GreenfootImage[] idle = new GreenfootImage[4];
    private int imageIndex = 0;

    public Pig() {
        for (int i = 0; i < idle.length; i++) {
            idle[i] = new GreenfootImage("images/pig-idle/pig" + i + ".png");
            idle[i].scale(100, 80);
        }
        setImage(idle[0]);
        animationTimer.mark();
    }

    // Controls animation speed
    protected void animate() {
        if (animationTimer.millisElapsed() > 200) {
            setImage(idle[imageIndex]);
            imageIndex = (imageIndex + 1) % idle.length;
            animationTimer.mark();
        }
    }

    // Delegates to Animal class for physics and merging
    public void act() {
        super.act();
        
    }
    
    // Merges and creates sheep
    @Override
    protected Animal createMergedAnimal()
    {
        return new Sheep();
    }
}
