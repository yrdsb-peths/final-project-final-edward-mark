import greenfoot.*;

    /**
     * The dog is the third animal.
     * Two dogs merge into a sheep.
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

    protected void animate() {
        if (animationTimer.millisElapsed() > 200) {
            setImage(idle[imageIndex]);
            imageIndex = (imageIndex + 1) % idle.length;
            animationTimer.mark();
        }
    }

    public void act() {
        super.act();
        
    }
   


    
    
    @Override
    protected Animal createMergedAnimal()
    {
        return new Wolf();
    }
}
