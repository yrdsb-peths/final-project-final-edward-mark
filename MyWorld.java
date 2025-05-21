import greenfoot.*;
/**
 * The main world
 * 
 * @author Mark Ku & Edward Wang
 * @version May 2025
 */

public class MyWorld extends World {
    private Cat fallingCat;
    
    public MyWorld() {
        super(500, 600, 1);
        createCat();
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
}
