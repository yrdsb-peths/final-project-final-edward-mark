import greenfoot.*;

public abstract class Animal extends Actor {
    protected int speed = 6;
    protected boolean falling = false;
    protected boolean hasLanded = false;
    protected boolean keyLeftPressed = false;
    protected boolean keyRightPressed = false;
    protected int currentColumn = 1;
    protected SimpleTimer animationTimer = new SimpleTimer();
    
    protected abstract Animal createMergedAnimal();
    protected abstract void animate();

    public void act() {
        if (!(getWorld() instanceof MyWorld)) return;

        animate(); 

        if (!falling) {
            chooseColumn();
        } else {
            moveAnimal();
            checkIfLanded();
        }
    }
    
    //moves the animal down, stops at the bottom of the world
    protected void moveAnimal() {
        int bottomY = getWorld().getHeight() - getImage().getHeight() / 2;
        setLocation(getX(), Math.min(getY() + speed, bottomY));
    }
    
    //handles left/right/space input to move the animal between columns
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
    
    //Checks whether the animal has landed on the floor or on another animal
    protected void checkIfLanded() {
        if (hasLanded || getWorld() == null) return;

        MyWorld world = (MyWorld)getWorld();
        int myBottom = getY() + getImage().getHeight()/2;
        int worldBottom = world.getHeight();

        // Check floor
        if (myBottom >= worldBottom) {
            setLocation(getX(), worldBottom - getImage().getHeight()/2);
            finishLanding(world);
            return;
        }

        // Check animal collision
        for (Animal other : world.getObjects(Animal.class)) {
            if (other != this && other.hasLanded()) {
                int otherTop = other.getY() - other.getImage().getHeight()/2;
                boolean sameColumn = this.currentColumn == other.currentColumn;

                if (sameColumn && myBottom >= otherTop - 2) { // add small buffer
                    setLocation(getX(), otherTop - getImage().getHeight()/2);
                    finishLanding(world);
                    return;
                }
            }
        }
    }

    //This method is called when the animal lands
    private void finishLanding(MyWorld world) {
        hasLanded = true;
        falling = false;
        world.clearFallingAnimal();
        checkForMerge(world);
    }

    
    //This method checks if there is another animal in the same column and if they are close enough to merge
    private void checkForMerge(MyWorld world) {
        if (falling) return;

        for (Animal other : world.getObjects(getClass())) {
            if (other != this && other.hasLanded) {
                if (this.currentColumn == other.currentColumn) {
                    int verticalDistance = Math.abs(this.getY() - other.getY());
                    int mergeThreshold = (this.getImage().getHeight() + other.getImage().getHeight()) / 2;

                    if (verticalDistance <= mergeThreshold) {
                        mergeAnimals(world, other);
                        return;
                    }
                }
            }
        }
    }

    //Merges 2 of the same animals into a new one
    //Removes both original animals and spawns a merged one
    
    protected void mergeAnimals(MyWorld world, Animal other) {
        Animal merged = createMergedAnimal();
        if (merged == null) return;
    
        int x = getX();
        int y = Math.max(getY(), other.getY()); // spawn where lower animal was
    
        // Remove original animals
        world.removeObject(this);
        world.removeObject(other);
    
        // Add merged animal at correct location
        world.addObject(merged, x, y);
    
        merged.hasLanded = true;
        merged.currentColumn = this.currentColumn;
    
        world.increaseScore(10);
    
        // Immediately check for another merge
        merged.checkForMerge(world);
    }

    //Checks whether the current animal is still falling
    public boolean isFalling() {
        return falling && !hasLanded;
    }
    
    //checks whether the current animal has already landed
    public boolean hasLanded() {
        return hasLanded;
    }
}
