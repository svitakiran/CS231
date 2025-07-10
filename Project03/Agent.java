/*
 * The purpose of this file is to create and represent an agent in
 * the simulation.
 * Author: Svita Kiran
 * Date: 3/7/24
*/

import java.awt.Graphics;

public abstract class Agent {

    protected double x;
    protected double y;
    protected int radius;
    protected boolean moved;
    
    public Agent(double x0, double y0) {
        this.x = x0;
        this.y = y0;
        this.radius = 0;
        this.moved = false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public boolean getMoved() {
        return moved;
    }

    public void setX(double newX) {
        this.x = newX;
    }

    public void setY(double newY) {
        this.y = newY;
    }

    public void setRadius(int newRadius) {
        this.radius = newRadius;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public abstract void updateState(Landscape scape);

    public abstract void draw(Graphics g);

}
