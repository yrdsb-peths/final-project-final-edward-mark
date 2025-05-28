import greenfoot.*;

    /**
     * The rabbit is the first animal
     * 2 rabbits merge into a ------
     * 
     * @author (Mark Ku & Edward Wang) 
     * @version (May 2025)
     */
    
public class Rabbit extends Animal {
    // Array to store all frames of the cat's idle animation
    private GreenfootImage[] idle = new GreenfootImage[28];

    // Keeps track of which animation frame to display
    private int imageIndex = 0;

    // Constructor
    public Rabbit() {
        // Animates the rabbit
        for (int i = 0; i < idle.length; i++) {
            idle[i] = new GreenfootImage("images/rabbit-idle/rab" + i + ".png");
            idle[i].scale(70, 60); // Set size of each frame to fit the game
        }

        setImage(idle[0]);

        // Start the animation timer to track time between frame changes
        animationTimer.mark();
    }

    @Override
    protected void animate() {
        // Change frame every 150 milliseconds
        if (animationTimer.millisElapsed() > 150) {
            setImage(idle[imageIndex]);
            imageIndex = (imageIndex + 1) % idle.length; // Loop animation
            animationTimer.mark(); // Reset timer
        }
    }

    // Delegates to Animal class for physics and merging
    public void act() {
        super.act();
    }

    // Merges and creates chicken
    @Override
    protected Animal createMergedAnimal() {
        return new Chicken();
    }
}
