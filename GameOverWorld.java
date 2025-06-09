import greenfoot.*;

    /**
     * The end screen / game over screen.
     * 
     * @author (Mark Ku & Edward Wang) 
     * @version (June 2025)
     */

public class GameOverWorld extends World {
    public GameOverWorld(int finalScore, int highScore) {
        super(500, 600, 1);
        setBackground("images/title.jpg");
        
        // Creates labels
        Label gameOverLabel = new Label("Game Over", 70);
        Label scoreLabel = new Label("Score: " + finalScore, 50);
        Label highScoreLabel = new Label("High Score: " + highScore, 40);
        Label restartLabel = new Label("Press <r> to restart", 30);
        
        // Adds labels
        addObject(gameOverLabel, getWidth() / 2, getHeight() / 2 - 70);
        addObject(scoreLabel, getWidth() / 2, getHeight() / 2 - 10);
        addObject(highScoreLabel, getWidth() / 2, getHeight() / 2 + 40);
        addObject(restartLabel, getWidth() / 2, getHeight() / 2 + 90);
    }

    // Goes back to game world if user presses r
    public void act() {
        if (Greenfoot.isKeyDown("r")) {
            Greenfoot.setWorld(new MyWorld());
        }
    }
}
