  import greenfoot.*;

public class GameOverWorld extends World {
    public GameOverWorld(int finalScore) {
        super(500, 600, 1);
        setBackground("images/background.jpg");

        Label gameOverLabel = new Label("Game Over", 70);
        Label scoreLabel = new Label("Score: " + finalScore, 50);
        Label restartLabel = new Label("Press <r> to restart", 30);

        addObject(gameOverLabel, getWidth() / 2, getHeight() / 2 - 50);
        addObject(scoreLabel, getWidth() / 2, getHeight() / 2 + 10);
        addObject(restartLabel, getWidth() / 2, getHeight() / 2 + 60);
    }

    public void act() {
        //checks if user presses r to restart the game
        if (Greenfoot.isKeyDown("r")) {
            Greenfoot.setWorld(new MyWorld()); 
        }
    }
}
