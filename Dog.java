import greenfoot.*;

    /**
     * The dog is the fourth animal.
     * 2 dogs merge into a pig
     * 
     * @author (Mark Ku & Edward Wang) 
     * @version (May 2025)
     */
public class Dog extends Animal {
    private GreenfootImage[] idle = new GreenfootImage[11];
    private int imageIndex = 0;

    public Dog() {
        for (int i = 0; i < idle.length; i++) {
            idle[i] = new GreenfootImage("images/dog-idle/idle" + i + ".png");
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
 
    // Merges and creates pig
    @Override
    protected Animal createMergedAnimal()
    {
        return new Pig();
    }
}
