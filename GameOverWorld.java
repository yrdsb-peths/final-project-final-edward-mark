import greenfoot.*;

public class GameOverWorld extends World {
    public GameOverWorld(int finalScore, int highScore) {
        super(500, 600, 1);
        setBackground("images/title.jpg");

        Label gameOverLabel = new Label("Game Over", 70);
        Label scoreLabel = new Label("Score: " + finalScore, 50);
        Label highScoreLabel = new Label("High Score: " + highScore, 40);
        Label restartLabel = new Label("Press <r> to restart", 30);

        addObject(gameOverLabel, getWidth() / 2, getHeight() / 2 - 70);
        addObject(scoreLabel, getWidth() / 2, getHeight() / 2 - 10);
        addObject(highScoreLabel, getWidth() / 2, getHeight() / 2 + 40);
        addObject(restartLabel, getWidth() / 2, getHeight() / 2 + 90);
    }

    public void act() {
        if (Greenfoot.isKeyDown("r")) {
            Greenfoot.setWorld(new MyWorld());
        }
    }
}
