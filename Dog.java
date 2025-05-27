import greenfoot.*;

public class Dog extends Animal {
    private GreenfootImage[] idle = new GreenfootImage[11];
    private int imageIndex = 0;
    private boolean hasMerged = false;

    public Dog() {
        for (int i = 0; i < idle.length; i++) {
            idle[i] = new GreenfootImage("images/dog-idle/idle" + i + ".png");
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

    



    public void setMerged() {
        hasMerged = true;
    }
    
    @Override
    protected Animal createMergedAnimal()
    {
        return new Wolf();
    }
}
