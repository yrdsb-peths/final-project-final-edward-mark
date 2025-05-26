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
    
    private int currentColumn = 1;
    private boolean keyLeftPressed = false;
    private boolean keyRightPressed = false;
    

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
        if (!(getWorld() instanceof MyWorld)){
            return;
        }
        if (checkMerge()){ 
            return;  // Stop everything if we merge
            
        }
        animateCat();
        if (!falling) {
            chooseColumn(); // move side to side or start fall
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
    public void chooseColumn()
    {
        MyWorld world = (MyWorld) getWorld();
        if (world == null || world.getFallingCat() != this)
        { 
            return;
        }       
        
        //controls for left arrow
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

        // controls for right arrow
        if (Greenfoot.isKeyDown("right")) {
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
    
        // Start falling when space is pressed
        if (Greenfoot.isKeyDown("space")) 
        {
            falling = true;
        }
    }
    
    
    //Checks if two cats have merged
    private boolean checkMerge() {
        if (getWorld() == null){
            return false;
        }
    
        Cat other = (Cat) getOneIntersectingObject(Cat.class);
        if (other != null && other != this) {
            MyWorld world = (MyWorld) getWorld();
            
            Dog dog = new Dog();
            int dogHeight = dog.getImage().getHeight();
            int worldHeight = world.getHeight();
            int bottomY = worldHeight - dogHeight / 2;
    
            int newX = (getX() + other.getX()) / 2;
            int newY = (getY() + other.getY()) / 2;
    
            // If either of the cats is near the bottom, snap the dog to the bottom
            if (getY() + getImage().getHeight() / 2 >= worldHeight - 2 ||
                other.getY() + other.getImage().getHeight() / 2 >= worldHeight - 2) {
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
    
            return true;
        }
    
        return false;
    }
}

