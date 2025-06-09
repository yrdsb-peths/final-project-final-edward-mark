import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

    /**
     * The end screen / game over screen.
     * 
     * @author (Mark Ku & Edward Wang) 
     * @version (June 2025)
     */
    
public class YouWin extends World
{
    
    private World currentRun;
    
    // Constructor
    public YouWin(World currentRun)
    {    
        // Creates a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(500, 600, 1); 
        // Sets background image
        setBackground ("images/title.jpg");
        
        
        this.currentRun = currentRun;
        // Calls prepare method, adding world labels
        prepare();
        
    }
    
    public void act() 
    {
        // Returns to new game when SPACE is pressed
        if (Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new MyWorld());
        }
        //Returns to current run when C is pressed
        if (Greenfoot.isKeyDown("c") && currentRun != null)
        {
            Greenfoot.setWorld (currentRun);
        }
    }
    
    // Adds labels
    public void prepare()
    {
        Label win = new Label ("You Win!", 60);
        addObject(win, getWidth() / 2, getHeight() / 2 - 50);
        
        Label restart = new Label ("Press <space> to restart the game", 25);
        addObject (restart, getWidth() / 2, getHeight() / 2 + 10);
        
        Label cont = new Label ("Press <c> to continue your previous run", 25);
        addObject (cont,getWidth() / 2, getHeight() / 2 + 60);

    }
}
