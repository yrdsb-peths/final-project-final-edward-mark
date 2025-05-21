import greenfoot.*;
/**
 * The main world
 * 
 * @author Mark Ku & Edward Wang
 * @version May 2025
 */

public class MyWorld extends World {
    public MyWorld() {
        super(500, 600, 1);
        
        Cat cat = new Cat();
        addObject(cat, 250, 25);
    }
}
