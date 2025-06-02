import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the title screen, it should be displayed as soon as the player runs the game
 * 
 * @author (Mark, Edward) 
 * @version (June 2025)
 */
public class TitleScreen extends World
{
    Label titleLabel = new Label ("Animal Merge", 60);
    
    //Constructor for the class TitleScreen
    public TitleScreen()
    {    
        // Creates a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(500, 600, 1); 
        addObject (titleLabel, getWidth()/2, 200);
        prepare();
        
        setBackground ("images/title.jpg");
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
        addObject(label2,258,300);
        
        Label label3 = new Label("Merge two of the same animal \n to create a new one", 35);
        addObject(label3,258,330);
        
        Label label4 = new Label("Press space to start the game", 35);
        addObject(label4,258,500);
        
        // Sets positions of instructions on title screen
        label.setLocation(255,366);
        
        label2.setLocation(255,406);
        
        label3.setLocation(255,466);
        
        label4.setLocation(255,526);
        
        cat.setLocation(241,290);
    }
}
