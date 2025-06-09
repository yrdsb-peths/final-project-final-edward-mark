import greenfoot.*;

    /**
     * The animal class.
     * 
     * @author (Mark Ku & Edward Wang) 
     * @version (June 2025)
     */

public abstract class Animal extends Actor {
    protected int speed = 6;
    protected boolean falling = false;
    protected boolean hasLanded = false;
    protected boolean keyLeftPressed = false;
    protected boolean keyRightPressed = false;
    protected int currentColumn = 1;
    
    protected SimpleTimer animationTimer = new SimpleTimer();
    private SimpleTimer spaceCooldown = new SimpleTimer();
    GreenfootSound mergeSound = new GreenfootSound ("sounds/gainpoints.mp3");
    

    
    protected abstract Animal createMergedAnimal();
    protected abstract void animate();

    public void act() {
        if (!(getWorld() instanceof MyWorld)) 
        {
            return;
        }
        animate(); 

        if (!falling) 
        {
            chooseColumn();
        }
        else 
        {
            moveAnimal();
            checkIfLanded(); //check if the previous animal has landed
        }
    }
    
    //moves the animal down, stops at the bottom of the world
    protected void moveAnimal() 
    {
        int bottomY = getWorld().getHeight() - getImage().getHeight() / 2;
        setLocation(getX(), Math.min(getY() + speed, bottomY));
    }
    
    //handles left/right/space input to move the animal between columns
    protected void chooseColumn() 
    {
        MyWorld world = (MyWorld) getWorld();
        if (world == null || world.getFallingAnimal() != this) return;
    
        if (Greenfoot.isKeyDown("left")) 
        {
            if (!keyLeftPressed && currentColumn > 0) 
            {
                currentColumn--;
                keyLeftPressed = true;
            }
        } 
        else 
        {
            keyLeftPressed = false;
        }
    
        if (Greenfoot.isKeyDown("right")) 
        {
            if (!keyRightPressed && currentColumn < MyWorld.columns.length - 1) 
            {
                currentColumn++;
                keyRightPressed = true;
            }
        } 
        else 
        {
            keyRightPressed = false;
        }
    
        int columnX = MyWorld.columns[currentColumn];
        setLocation(columnX, getY());
    
        // Ensures that space will not be double pressed
        if (Greenfoot.isKeyDown("space") && spaceCooldown.millisElapsed() > 250) 
        {
            falling = true;
            hasLanded = false;
            spaceCooldown.mark(); // restarts the cooldown
        }
    }

    
    //Checks whether the animal has landed on the floor or on another animal
    protected void checkIfLanded() 
    {
        if (!falling || hasLanded || getWorld() == null)
        {
            return;
        }

        MyWorld world = (MyWorld)getWorld();
        int myBottom = getY() + getImage().getHeight()/2;
        int worldBottom = world.getHeight();

        // Check floor
        if (myBottom >= worldBottom) 
        {
            setLocation(getX(), worldBottom - getImage().getHeight()/2);
            finishLanding(world);
            return;
        }

        // Check for animal collision
        for (Animal other : world.getObjects(Animal.class)) 
        {
            if (other != this && other.hasLanded()) 
            {
                int otherTop = other.getY() - other.getImage().getHeight()/2;
                boolean sameColumn = this.currentColumn == other.currentColumn;

                if (sameColumn && myBottom >= otherTop - 2) 
                { // add buffer
                    setLocation(getX(), otherTop - getImage().getHeight()/2);
                    finishLanding(world);
                    return;
                }
            }
        }
    }

    //This method is called when the animal lands
    private void finishLanding(MyWorld world) 
    {
        hasLanded = true;
        falling = false;
        world.clearFallingAnimal();
        checkForMerge(world);
    }

    
    //This method checks if there is another animal in the same column and if they are close enough to merge
    private void checkForMerge(MyWorld world) 
    {
        for (Animal other : world.getObjects(getClass())) 
        {
            if (other != this && other.hasLanded) 
            {
                if (this.currentColumn == other.currentColumn) 
                {
                    int verticalDistance = Math.abs(this.getY() - other.getY());
                    int mergeThreshold = (this.getImage().getHeight() + other.getImage().getHeight()) / 2;
    
                    if (verticalDistance <= mergeThreshold) 
                    {
                        // Merge and stop, the merged animal will continue the chain
                        mergeAnimals(world, other);
                        return; // very important! do not continue after this object is removed
                    }
                }
            }
        }
    }



    //Merges 2 of the same animals into a new one
    //Removes both original animals and spawns a merged one
    protected void mergeAnimals(MyWorld world, Animal other) 
    {
        Animal merged = createMergedAnimal();
        if (merged == null) return;
    
        int x = getX();
        int initialY = Math.min(getY(), other.getY()); // Start near top of the merging pair
    
        // Remove originals
        world.removeObject(this);
        world.removeObject(other);
    
        // Temporarily place merged animal at top (will fall to correct Y)
        world.addObject(merged, x, initialY - merged.getImage().getHeight());
    
        // Set its properties so it behaves like a falling animal
        merged.falling = true;
        merged.hasLanded = false;
        merged.currentColumn = this.currentColumn;
        
         // Adjust score based on the type of the merged animal
        int points = 0;
        if (merged instanceof Chicken){
            points = 2;
        }
        else if (merged instanceof Cat) {
            points = 4;
        } else if (merged instanceof Dog) {
            points = 6;
        } else if (merged instanceof Pig) {
            points = 8;  
        } else if (merged instanceof Sheep) {
            points = 10;
        } else if (merged instanceof Cow) {
            points = 15;
        } else if (merged instanceof Wolf) {
            points = 25;
        } else if (merged instanceof Rhino) {
            points = 50;
        } else if (merged instanceof Tiger) {
            points = 100;
            world.tigerCount();
        }
        

        world.increaseScore(points);
        mergeSound.play();
    
        // Treat it as the "falling animal" to use normal physics
        world.setFallingAnimal(merged);
    }

    //Checks whether the current animal is still falling
    public boolean isFalling() 
    {
        return falling && !hasLanded;
    }
    
    //checks whether the current animal has already landed
    public boolean hasLanded() 
    {
        return hasLanded;
    }
}
