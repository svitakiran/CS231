/*
 * The purpose of the file is to create an agent that moves away from
 * the other agents (hence AntiSocial) and extends the Agent class.
 * Author: Svita Kiran
 * Date: 3/7/24
*/

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class AntiSocialAgent extends Agent {
    
    public AntiSocialAgent(double x0, double y0, int radius) {
        super(x0, y0);
        this.radius = radius;
    }

    public void draw(Graphics g) {
        if (!moved) {
            g.setColor(new Color(255, 0, 0));
        } else {
            g.setColor(new Color(255, 125, 125));
        }
        g.fillOval((int) getX(), (int) getY(), 5, 5);
    }

    public void updateState(Landscape scape) {
        LinkedList<Agent> neighbors = scape.getNeighbors(getX(), getY(), getRadius());
        if (neighbors.size() > 1) {
            Random rand = new Random();
            double newX = getX() + rand.nextInt(21) - 10; // Random value between -10 and 10
            double newY = getY() + rand.nextInt(21) - 10; // Random value between -10 and 10
            // Ensure the new coordinates stay within the landscape boundaries
            newX = Math.max(0, Math.min(scape.getWidth(), newX));
            newY = Math.max(0, Math.min(scape.getHeight(), newY));
            setX(newX);
            setY(newY);
            moved = true;
        } else {
            moved = false;
        }
    }
}
