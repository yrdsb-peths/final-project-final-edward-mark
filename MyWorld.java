import greenfoot.*;
/**
 * The main world
 * 
 * @author Mark Ku & Edward Wang
 * @version May 2025
 */

public class MyWorld extends World 
{
    public static final int[] columns = {62, 187, 312, 437};
    private Cat fallingCat;
    private boolean merging = false;
    public int score = 0;
    Label scoreLabel;
    
    public MyWorld() 
    {
        super(500, 600, 1);
        
        //Creates starting cat
        createAnimal();
        setBackground("images/background.jpg");
        
        //Creates score
        scoreLabel = new Label("0",50);
        addObject(scoreLabel, 30, 30);
    }
    
    public boolean isMerging() {
        return merging;
    }

    public void setMerging(boolean m) {
        merging = m;
    }
    
    public void act() {
        merging = false; // Reset merging flag every frame
    }
    
    //Randomly creates animal 
    public void createAnimal()
    {
        if (fallingCat == null)
        {
            int choice = Greenfoot.getRandomNumber(3); // 0 = Cat, 1 = Dog, 2 = Wolf
            Actor animal;
    
            if (choice == 0) {
                animal = new Cat();
                fallingCat = (Cat) animal; // Only Cat is controllable
            } else if (choice == 1) {
                animal = new Dog();
                fallingCat = null;
            } else {
                animal = new Wolf();
                fallingCat = null;
            }
    
            int x = getWidth() / 2;
            int y = 25;
            addObject(animal, x, y);
        }
    }

    
    //allow the next cat to spawn
    public void clearFallingCat()
    {
        fallingCat = null;
    }
    
    public Cat getFallingCat() 
    {
        return fallingCat;
    }  
    
    //Increases score
    public void increaseScore(int amount)
    {
        score+=amount; 
        scoreLabel.setValue(score);
    }
}
