import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the title screen, it should be displayed as soon as the player runs the game
 * 
 * @author (Mark, Edward) 
 * @version (June 2025)
 */
public class TitleScreen extends World {
    Label titleLabel = new Label("Animal Merge", 60);

    // Constructor for TitleScreen
    public TitleScreen() {
        super(500, 600, 1);  // Set world size
        setBackground("images/title.jpg"); // Set background image
        addObject(titleLabel, getWidth() / 2, 200); // Add game title
        prepare(); // Add UI elements and visuals
    }

    public void act() {
        // Start the game when SPACE is pressed
        if (Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new MyWorld());
        }

        // Go to HelpScreen when G is pressed
        if (Greenfoot.isKeyDown("g")) {
            Greenfoot.setWorld(new HelpScreen());
        }
    }

    // Adds UI instructions and visual elements
    private void prepare() {
        Cat cat = new Cat(); // Visual example animal
        addObject(cat, 241, 290);
        addObject(new Label("Press G for a guide", 35), 255, 366);
        addObject(new Label("Press space to start the game", 35), 255, 426);
    }
}
