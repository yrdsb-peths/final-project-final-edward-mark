import greenfoot.*;
/**
 * The main world
 * 
 * @author Mark Ku & Edward Wang
 * @version May 2025
 */

public class MyWorld extends World 
{
    private Cat fallingCat;
    private boolean merging = false;
    public int score = 0;
    Label scoreLabel;
    
    public MyWorld() 
    {
        super(500, 600, 1);
        
        //Creates starting cat
        createCat();
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
    
    public void createCat()
    {
        //only spawn new cat if there isn't already a falling cat
        if (fallingCat == null)
        {
            fallingCat = new Cat();
            int x = getWidth()/2;
            int y = 25;
            addObject (fallingCat, x, y);
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
