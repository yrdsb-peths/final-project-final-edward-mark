import greenfoot.*;

public class Cat extends Animal {
    private GreenfootImage[] idle = new GreenfootImage[18];
    private int imageIndex = 0;

    public Cat() {
        for (int i = 0; i < idle.length; i++) {
            idle[i] = new GreenfootImage("images/cat-idle/cat" + i + ".png");
            idle[i].scale(70, 60);
        }
        setImage(idle[0]);
        animationTimer.mark();
    }

    @Override
    protected void animate() {
        if (animationTimer.millisElapsed() > 150) {
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
        return new Dog();
    }
}
