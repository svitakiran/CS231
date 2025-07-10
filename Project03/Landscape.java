/*
 * The purpose of this file is to create the landscape or environment
 * where the agents will be.
 * Author: Svita Kiran
 * Date: 3/7/24
*/

import java.awt.Graphics;
import java.util.Random;

public class Landscape {

    private int width;
    private int height;
    private LinkedList<Agent> agents;

    public Landscape(int w, int h) {
        this.width = w;
        this.height = h;
        this.agents = new LinkedList<>();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void addAgent(Agent a) {
        agents.add(a);
    }

    public String toString() {
        return "num Agents on landscape: " + agents.size();
    }

    public LinkedList<Agent> getNeighbors(double x0, double y0, double radius) {
        LinkedList<Agent> neighbors = new LinkedList<>();
        for (Agent agent : agents) {
            double distance = Math.sqrt(Math.pow(x0 - agent.getX(), 2) + Math.pow(y0 - agent.getY(), 2));
            if (distance <= radius) {
                neighbors.add(agent);
            }
        }
        return neighbors;
    }

    public void draw(Graphics g) {
        for (Agent agent : agents) {
            agent.draw(g);
        }
    }

    public int updateAgents() {
        Random rand = new Random();
        int numMoved = 0;

        int index = rand.nextInt(agents.size());
        Agent deleted = null;
        int current = 0;
        for (Agent agent : agents) {
            if (current == index) {
                deleted = agent;
                break;
            }
            current++;
        }

        if (deleted != null) {
            double x = deleted.getX();
            double y = deleted.getY();
            int radius = deleted.getRadius();

            AntiSocialAgent newAgent = new AntiSocialAgent(x, y, radius);
            agents.add(newAgent);
        }

        for (Agent agent : agents) {
            boolean moved = agent.getMoved();
            agent.updateState(this);
            if (agent.getMoved() != moved) {
                numMoved++;
            }
        }
        return numMoved;
    }
}
