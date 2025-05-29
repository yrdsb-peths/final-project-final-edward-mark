import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the title screen, it should be displayed as soon as the player runs the game
 * 
 * @author (Mark, Edward) 
 * @version (May 2025)
 */
public class TitleScreen extends World
{
    Label titleLabel = new Label ("Animal Merge", 60);
    
    //Constructor for the class TitleScreen
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(500, 600, 1); 
        addObject (titleLabel, getWidth()/2, 200);
        prepare();
        
        setBackground ("images/background.jpg");
    }
    
    public void act ()
    {
        if (Greenfoot.isKeyDown("space"))
        {
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld (gameWorld);
        }
    }
    
    private void prepare()
    {
        Cat cat = new Cat();
        addObject (cat, 479, 87);
        cat.setLocation(258,130);

        Label label = new Label("Use \u2190  \u2192 to move", 35);
        addObject(label,258,270);
        Label label2 = new Label("Press space to drop", 35);
        addObject(label2,258,333);
        
        Label label3 = new Label("Press space to start the game", 35);
        addObject(label3,258,333);

        label2.setLocation(255,437);
        
        label3.setLocation(255,500);

        label.setLocation(260,366);
        
        cat.setLocation(241,290);
    }
}
