import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cat extends Actor
{
    /**
     * Act - do whatever the Cat wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        moveCat();
    }

    public void moveCat() {
        // Cat falls
        int speed = 4;  
        setLocation(getX(), getY() + speed); 

        // Get the width and height of the cat
        int width = getImage().getWidth();
        int height = getImage().getHeight();

        // Check Y boundaries (top and bottom)
        if (getY() > getWorld().getHeight() - height / 2) {
            setLocation(getX(), getWorld().getHeight() - height / 2); 
        }

        if (getY() < height / 2) {
            //GAME OVER
            setLocation(getX(), height / 2);
        }

        // Check X boundaries (left and right)
        if (getX() > getWorld().getWidth() - width / 2) {
            setLocation(getWorld().getWidth() - width / 2, getY());
        }

        if (getX() < width / 2) {
            setLocation(width / 2, getY()); 
        }
    }
}
