import greenfoot.*;
import java.util.Random;

public class MyWorld extends World {
    public static final int[] columns = {62, 187, 312, 437};
    private Animal fallingAnimal;
    private boolean merging = false;
    public int score = 0;
    Label scoreLabel;
    private Random rand = new Random();

    public MyWorld() {
        super(500, 600, 1);
        setBackground("images/background.jpg");
        createAnimal();

        scoreLabel = new Label("0", 50);
        addObject(scoreLabel, 30, 30);
    }
    
    public void act()
    {
        if (fallingAnimal == null || (!fallingAnimal.isFalling() && fallingAnimal.hasLanded()))
        {
            createAnimal();
        }
    }
    

    
    public void createAnimal() {
        if (fallingAnimal == null) {
            Animal animal;
            int type = rand.nextInt(3); // 0=Cat, 1=Dog, 2=Wolf
            if (type == 0) {
                animal = new Cat();
            } else if (type == 1) {
                animal = new Dog();
            } else {
                animal = new Wolf();
            }

            int x = getWidth() / 2;
            int y = 25;
            fallingAnimal = animal;
            addObject(animal, x, y);
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
    }
}
