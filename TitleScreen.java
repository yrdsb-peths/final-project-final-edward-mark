import greenfoot.*;  

    /**
     * This is the title screen, it is displayed 
     * as soon as the player runs the game.
     * 
     * @author (Mark, Edward) 
     * @version (June 2025)
     */
    
public class TitleScreen extends World {
    Label titleLabel = new Label("Animal Merge", 60);

    // Constructor for TitleScreen
    public TitleScreen() {
        super(500, 600, 1);  // Sets world size
        setBackground("images/title.jpg"); // Sets background image
        addObject(titleLabel, getWidth() / 2, 200); // Adds game title
        prepare(); 
    }

    public void act() {
        // Starts the game when SPACE is pressed
        if (Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new MyWorld());
        }

        // Goes to HelpScreen when G is pressed
        if (Greenfoot.isKeyDown("g")) {
            Greenfoot.setWorld(new HelpScreen());
        }
    }

    // Adds  instructions and visual elements
    private void prepare() {
        Cat cat = new Cat(); 
        addObject(cat, 241, 290);
        addObject(new Label("Press G for a guide", 35), 255, 366);
        addObject(new Label("Press space to start the game", 35), 255, 426);
    }
}
