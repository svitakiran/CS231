/*
 * The purpose of this file is to simulate the Social and AntiSocial
 * agents that were created in the other classes. This simulation has
 * certain measurements such as radius and runs until all the agents
 * stop moving.
 * Author: Svita Kiran
 * Date: 3/7/24
*/

import java.util.Random;

public class AgentSimulation {
    public static void main(String[] args) throws InterruptedException {
        
        Landscape landscape = new Landscape(500, 500);
        Random rand = new Random();
        
        int N = Integer.parseInt(args[0]);
        int randInt = rand.nextInt(N);
        int numSocialAgents = randInt;
        int numAntiSocialAgents = N-randInt;

        System.out.println("Number of Social Agents: " + numSocialAgents);
        System.out.println("Number of AntiSocial Agents: " + numAntiSocialAgents);

        for (int i = 0; i < numSocialAgents; i++) {
            double x = rand.nextDouble() * landscape.getWidth();
            double y = rand.nextDouble() * landscape.getHeight();
            landscape.addAgent(new SocialAgent(x, y, 25));
        }

        for (int i = 0; i < numAntiSocialAgents; i++) {
            double x = rand.nextDouble() * landscape.getWidth();
            double y = rand.nextDouble() * landscape.getHeight();
            landscape.addAgent(new AntiSocialAgent(x, y, 25));
        }

        LandscapeDisplay display = new LandscapeDisplay(landscape);

        int numAgentsMoved = 1;
        int i = 0;
        
        while ((numAgentsMoved > 0) && (i < 5000)) {
            numAgentsMoved = landscape.updateAgents();
            display.repaint();
            Thread.sleep(250);
            i++;
        }
    System.out.println("Number of Iterations: " + i);
    }

}