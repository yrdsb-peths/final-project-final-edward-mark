import greenfoot.*;

/**
 * The cow is the seventh animal.
 * 2 cows merge into a wolf
 * 
 * @author (Mark Ku & Edward Wang) 
 * @version (May 2025)
 */
    
public class Cow extends Animal {
    private GreenfootImage[] idle = new GreenfootImage[7];
    private int imageIndex = 0;

    public Cow() {
        for (int i = 0; i < idle.length; i++) {
            idle[i] = new GreenfootImage("images/cow-idle/cow" + i + ".png");
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
    
    // Merges and creates wolf

    protected Animal createMergedAnimal()
    {
        return new Wolf();
    }
}
