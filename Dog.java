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
        checkMerge();
    }

    private void checkMerge() {
    if (!hasLanded) return;

    Dog other = (Dog) getOneIntersectingObject(Dog.class);
    if (other != null && other != this && other.hasLanded && !hasMerged && !other.hasMerged) {
        hasMerged = true;
        other.setMerged();

        MyWorld world = (MyWorld) getWorld();
        int newX = (getX() + other.getX()) / 2;
        int newY = (getY() + other.getY()) / 2;

        Wolf wolf = new Wolf();
        int wolfHeight = wolf.getImage().getHeight();
        int bottomY = world.getHeight() - wolfHeight / 2;

        if (newY + wolfHeight / 2 >= world.getHeight() - 5) {
            newY = bottomY;
        }

        world.addObject(wolf, newX, newY);
        world.increaseScore(2);

        if (world.getFallingAnimal() == this || world.getFallingAnimal() == other) {
            world.clearFallingAnimal();
            world.createAnimal();
        }

        getWorld().removeObject(other);
        getWorld().removeObject(this);
    }
}


    public void setMerged() {
        hasMerged = true;
    }
}
