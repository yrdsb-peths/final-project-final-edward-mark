import greenfoot.*;
import java.util.Random;

public class MyWorld extends World {
    public static final int[] columns = {62, 187, 312, 437};
    private Animal fallingAnimal;
    private boolean merging = false;
    public int score = 0;
    public static int highScore = 0;
    Label scoreLabel;
    Label highScoreLabel;
    private Random rand = new Random();
    private SimpleTimer spawnTimer = new SimpleTimer();
    private boolean readyToSpawn = true;

    public MyWorld() {
        super(500, 600, 1);
        setBackground("images/background.jpg");
        createAnimal();

        scoreLabel = new Label("0", 50);
        addObject(scoreLabel, 50, 30);
        highScoreLabel = new Label ("High score: " + highScore, 30);
        addObject (highScoreLabel, 380, 30);
    }
    
    public void act() {
        if (!readyToSpawn && spawnTimer.millisElapsed() > 300) {
            readyToSpawn = true;
        }
    
        if (fallingAnimal == null && readyToSpawn) {
            createAnimal();
        }
    
        checkGameOver(); 
    }


    

    public void createAnimal() {
        if (fallingAnimal == null && readyToSpawn) {
            Animal animal;
            int type = rand.nextInt(4); // 0=Rabbit, 1=Chicken, 2=Cat, 3=Dog
            if (type == 0) {
                animal = new Rabbit();
            } else if (type == 1) {
                animal = new Chicken();
            } else if (type == 2){
                animal = new Cat();
            } else {
                animal = new Dog();
            }
    
            int x = getWidth() / 2;
            int y = 25;
            fallingAnimal = animal;
            addObject(animal, x, y);
    
            // prevent instant re-spawn
            readyToSpawn = false;
            spawnTimer.mark();
        }
    }
    
    public void clearFallingAnimal() {
        fallingAnimal = null;
        }

    public Animal getFallingAnimal() {
        return fallingAnimal;
    }

    public void increaseScore(int amount) {
        score += amount;
        scoreLabel.setValue(score);
        if (score > highScore)
        {
            highScore = score;
            highScoreLabel.setValue("High score: " + highScore);
        }
    }
    
    public void setFallingAnimal(Animal a) {
        this.fallingAnimal = a;
    }
    
    //This method ends the game when an animal goes over y=100
    public void checkGameOver() {
        int gameOverY = 100; //adjust height as needed !!!!
    
        for (Animal animal : getObjects(Animal.class)) {
            if (animal.hasLanded() && animal.getY() < gameOverY) {
                Greenfoot.setWorld(new GameOverWorld(score, highScore));
                return;
            }
        }
    }

}
