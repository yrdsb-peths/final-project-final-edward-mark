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

    @Override
    public void act() {
        super.act();
        checkMerge();
    }

    private void checkMerge() {
        if (getWorld() == null) return;

        Cat other = (Cat) getOneIntersectingObject(Cat.class);
        if (other != null && other != this) {
            MyWorld world = (MyWorld) getWorld();
            int newX = (getX() + other.getX()) / 2;
            int newY = (getY() + other.getY()) / 2;

            Dog dog = new Dog();
            int dogHeight = dog.getImage().getHeight();
            int bottomY = world.getHeight() - dogHeight / 2;

            if (newY + dogHeight / 2 >= world.getHeight() - 5) {
                newY = bottomY;
            }

            world.increaseScore(1);
            world.addObject(dog, newX, newY);
            world.removeObject(other);
            world.removeObject(this);

            if (world.getFallingAnimal() == this || world.getFallingAnimal() == other) {
                world.clearFallingAnimal();
                world.createAnimal();
            }
        }
    }
}
