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

    @Override
    protected void animate() {
        if (animationTimer.millisElapsed() > 200) {
            setImage(idle[imageIndex]);
            imageIndex = (imageIndex + 1) % idle.length;
            animationTimer.mark();
        }
    }

    @Override
    public void act() {
        super.act();
        checkMerge();
    }

    private void checkMerge() {
        if (!hasMerged) {
            Dog other = (Dog) getOneIntersectingObject(Dog.class);
            if (other != null && other != this) {
                hasMerged = true;
                other.setMerged();
                getWorld().addObject(new Wolf(), getX(), getY());

                MyWorld world = (MyWorld) getWorld();
                world.increaseScore(2);

                getWorld().removeObject(other);
                getWorld().removeObject(this);
            }
        }
    }

    public void setMerged() {
        hasMerged = true;
    }
}
