import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Cat is the starting animal for the game
 * 
 * @author (Mark & Edward) 
 * @version (May 21 2025)
 */
public class Cat extends Actor
{
    private int speed = 4;
    SimpleTimer animationTimer = new SimpleTimer();
    private GreenfootImage[] idle = new GreenfootImage[18];
    int imageIndex = 0;
    private boolean hasLanded = false;
    private boolean falling = false;
    
    
     //Constructor for cat
    public Cat()
    {
        for (int i=0; i<idle.length; i++)
        {
            idle[i] = new GreenfootImage ("images/cat-idle/cat" + i + ".png");
            idle[i].scale (70,60);
        }
        
        setImage (idle[0]);
        animationTimer.mark();
    }
    
    public void act() {
        animateCat();
        if (!falling) {
            handleUserInput(); // move side to side or start fall
        } else {
            moveCat();
            checkIfLanded();
        }
    } 

    //animates the Cat
    public void animateCat() 
    {   
        if (animationTimer.millisElapsed() > 150) 
        {
            setImage(idle[imageIndex]);
            imageIndex = (imageIndex + 1) % idle.length;
            animationTimer.mark();
        }
    }
    
    public void moveCat() 
    {
        // Cat falls
        int speed = 4;  
        int newY = getY() + speed;
        int bottomY = getWorld().getHeight() - getImage().getHeight() / 2;
            
        setLocation(getX(), getY() + speed); //cat falls
        //ensures cat doesn't fall off the screen
        if (newY >= bottomY) {
            setLocation(getX(), bottomY);
        } else {
            setLocation(getX(), newY); 
        }
    }
    
    //this method checks if the current cat has landed before 
    //spawning the new Cat
    private void checkIfLanded()
    {
        if (hasLanded) return;

        int bottomY = getWorld().getHeight() - getImage().getHeight() / 2;
    
        // Merge if touching another Cat
        Cat otherCat = (Cat) getOneIntersectingObject(Cat.class);
        if (otherCat != null && otherCat != this && !otherCat.hasLanded) {
            // Create Dog at average position
            int newX = (getX() + otherCat.getX()) / 2;
            int newY = (getY() + otherCat.getY()) / 2;
    
            MyWorld world = (MyWorld) getWorld();
            world.addObject(new Dog(), newX, newY);
    
            world.removeObject(otherCat);
            world.removeObject(this);
            return;
        }

        //Stop if it lands on ground
        if (getY() + getImage().getHeight() / 2 >= getWorld().getHeight()) {
            setLocation(getX(), bottomY);
            hasLanded = true;
            MyWorld world = (MyWorld) getWorld();
            world.clearFallingCat();
            world.createCat();
        }
    }

    
    public void handleUserInput()
    {
        int halfWidth = getImage().getWidth() / 2;
        int worldWidth = getWorld().getWidth();
    
        if (Greenfoot.isKeyDown("left") && getX() - halfWidth > 0) {
            setLocation(getX() - 3, getY()); // move left
        }
    
        if (Greenfoot.isKeyDown("right") && getX() + halfWidth < worldWidth) {
            setLocation(getX() + 3, getY()); // move right
        }
    
        if (Greenfoot.isKeyDown("space")) {
            falling = true; // start falling
        }
    }
}

