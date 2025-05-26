import greenfoot.*;

/**
 * Abstract base class for all animals (Cat, Dog, Wolf).
 * Handles falling, movement, and animation delegation.
 */
public abstract class Animal extends Actor {
    protected int speed = 6;
    protected boolean falling = false;
    protected boolean hasLanded = false;
    protected boolean keyLeftPressed = false;
    protected boolean keyRightPressed = false;
    protected int currentColumn = 1;
    protected SimpleTimer animationTimer = new SimpleTimer();
    
    public void act() {
        if (!(getWorld() instanceof MyWorld)) return;

        animate(); // Defined by subclass

        if (!falling) {
            chooseColumn();
        } else {
            moveAnimal();
            checkIfLanded();
        }
    }

    // Must be implemented by each subclass
    protected abstract void animate();

    protected void moveAnimal() {
        int newY = getY() + speed;
        int bottomY = getWorld().getHeight() - getImage().getHeight() / 2;

        if (newY >= bottomY) {
            setLocation(getX(), bottomY);
        } else {
            setLocation(getX(), newY);
        }
    }

    protected void chooseColumn() {
        MyWorld world = (MyWorld) getWorld();
        if (world == null || world.getFallingAnimal() != this) return;

        if (Greenfoot.isKeyDown("left")) {
            if (!keyLeftPressed && currentColumn > 0) {
                currentColumn--;
                keyLeftPressed = true;
            }
        } else {
            keyLeftPressed = false;
        }

        if (Greenfoot.isKeyDown("right")) {
            if (!keyRightPressed && currentColumn < MyWorld.columns.length - 1) {
                currentColumn++;
                keyRightPressed = true;
            }
        } else {
            keyRightPressed = false;
        }

        int columnX = MyWorld.columns[currentColumn];
        setLocation(columnX, getY());

        if (Greenfoot.isKeyDown("space")) {
            falling = true;
            hasLanded = false;
        }
    }

    protected void checkIfLanded() {
        if (hasLanded) return;
        if (getWorld() == null) return;

        int bottomY = getWorld().getHeight() - getImage().getHeight() / 2;

        if (getY() >= bottomY) {
            setLocation(getX(), bottomY);
            hasLanded = true;
            MyWorld world = (MyWorld) getWorld();
            world.clearFallingAnimal();
            world.createAnimal();
        }

    }
}
