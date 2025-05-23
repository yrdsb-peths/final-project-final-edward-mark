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
    protected boolean falling = false;
    private boolean merging = false;
    
    
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
        if (checkMerge()){ 
            return;  // Stop everything if we merge
            
        }
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
        int speed = 6;  
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
    
        checkMerge(); // attempt merge first
    
        if (getWorld() == null) return; // ensures the world is still there
    
        int bottomY = getWorld().getHeight() - getImage().getHeight() / 2;
    
        if (getY() + getImage().getHeight() / 2 >= getWorld().getHeight()) {
            setLocation(getX(), bottomY);
            hasLanded = true;
            MyWorld world = (MyWorld) getWorld();
            world.clearFallingCat();
            world.createCat();
        }

    }

    
    //Allows user to control the top cat
    public void handleUserInput()
    {
        MyWorld world = (MyWorld) getWorld();
        if (world == null || world.getFallingCat() != this){ 
        return;
        }       
        
        //Ensures cat won't go off screen
        int halfWidth = getImage().getWidth() / 2;
        int worldWidth = getWorld().getWidth();
    
        if (Greenfoot.isKeyDown("left") && getX() - halfWidth > 0) {
            setLocation(getX() - 3, getY()); // moves cat left
        }
    
        if (Greenfoot.isKeyDown("right") && getX() + halfWidth < worldWidth) {
            setLocation(getX() + 3, getY()); // moves cat right
        }
    
        if (Greenfoot.isKeyDown("space")) {
            falling = true; // start falling
        }
    }
    
    
    //Checks if two cats have merged
    private boolean checkMerge() {
        if (getWorld() == null) return false;
    
        Cat other = (Cat) getOneIntersectingObject(Cat.class);
        if (other != null && other != this) {
            MyWorld world = (MyWorld) getWorld();
            int newX = (getX() + other.getX()) / 2;
            int newY = (getY() + other.getY()) / 2;
            
            // Create the dog first to get its height
            Dog dog = new Dog();
            int dogHeight = dog.getImage().getHeight();
            int worldHeight = world.getHeight();
            int bottomY = worldHeight - dogHeight / 2;
            
            // Snap to bottom if very close
            if (newY + dogHeight / 2 >= worldHeight - 5) {
                newY = bottomY;
            }
            
            // Increases score by 1
            world.increaseScore(1);
            
            // Adds dog
            world.addObject(dog, newX, newY);
    
            // Remove both cats
            world.removeObject(other);
            world.removeObject(this);
    
            // Allows the world to spawn a new cat if this was the falling one
            if (world.getFallingCat() == this || world.getFallingCat() == other) {
                world.clearFallingCat();
                world.createCat();
            }
    
            return true; // merged
        }
    
        return false; // no merge
    }

}

