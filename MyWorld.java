import greenfoot.*;
/**
 * The main world
 * 
 * @author Mark Ku & Edward Wang
 * @version May 2025
 */

public class MyWorld extends World {
    public MyWorld() {
        super(600, 400, 1);
        
        Hero hero = new Hero();
        addObject(hero, 300, 200);
    }
}
