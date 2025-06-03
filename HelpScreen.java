import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the help screen, which gives the user a basic 
 * rundown of how the game works
 * 
 * @author (Mark, Edward) 
 * @version (June 2025)
 */

public class HelpScreen extends World {

    // Constructor
    public HelpScreen() {
        super(500, 600, 1); // Sets world size
        setBackground("images/title.jpg"); // Sets background image
        prepare(); // Adds instructions and visuals
    }

    public void act() {
        // Returns to TitleScreen when SPACE is pressed
        if (Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new TitleScreen());
        }
    }

    // Adds instructions
    private void prepare() {
        Dog dog = new Dog(); 
        addObject(dog, 241, 150);

        addObject(new Label("Use \u2190  \u2192 to move", 30), 255, 230);
        addObject(new Label("Press space to drop", 30), 255, 265);
        addObject(new Label("Merge same animals to evolve!", 30), 255, 305);

        // Shows the merge evolution order
        addObject(new Label("Rabbit → Chicken → Cat → Dog", 26), 255, 345);
        addObject(new Label("→ Pig → Sheep → Cow → Wolf → Tiger", 26), 255, 375);

        addObject(new Label("Press space to return to the menu", 30), 255, 520);
    }
}