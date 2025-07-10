/*
 * The purpose of this file is to manage the jobs there in the server farm.
 * It keeps track of the servers and assigns them new jobs based on
 * the times.
 * Author: Svita Kiran
 * Date: 3/16/24
*/

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class JobDispatcher {
    protected double time;
    protected ServerFarmViz viz;
    protected ArrayList<Server> servers;
    protected int numJobsHandled;

    public JobDispatcher(int k, boolean showViz) {
        time = 0;
        numJobsHandled = 0;
        viz = new ServerFarmViz(this, showViz);
        servers = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            servers.add(new Server());
        }
    }

    public double getTime() {
        return time;
    }

    public ArrayList<Server> getServerList() {
        return servers;
    }

    public void advanceTimeTo(double time) {
        for (Server server : servers) {
            server.processTo(time);
        }
        this.time = time;
    }

    public void handleJob(Job job) {
        advanceTimeTo(job.getArrivalTime());
        viz.repaint();
        Server chosenServer = pickServer(job);
        chosenServer.addJob(job);
        viz.repaint();
        numJobsHandled++;
    }

    public void finishUp() {
        double maxTime = 0;
        for (Server server : servers) {
            if (server.remainingWorkInQueue() > maxTime) {
                maxTime = server.remainingWorkInQueue();
            }
        }
        advanceTimeTo(time + maxTime);
    }

    public int getNumJobsHandled() {
        return numJobsHandled;
    }

    // Existing method renamed to getAverageRatio()
    public double getAverageRatio() {
        double totalWaitingTime = 0;
        for (Server server : servers) {
            totalWaitingTime += server.timeInQueue();
        }
        return totalWaitingTime / numJobsHandled;
    }

    // New method to calculate average waiting time
    public double getAverageWaitingTime() {
        double totalWaitingTime = 0;
        for (Server server : servers) {
            totalWaitingTime += server.timeInQueue();
        }
        return totalWaitingTime / numJobsHandled;
    }

    public void draw(Graphics g) {
        double sep = (ServerFarmViz.HEIGHT - 20) / (servers.size() + 2.0);
        g.drawString("Time: " + time, (int) sep, ServerFarmViz.HEIGHT - 20);
        g.drawString("Jobs handled: " + numJobsHandled, (int) sep, ServerFarmViz.HEIGHT - 10);
        for (int i = 0; i < servers.size(); i++) {
            servers.get(i).draw(g, (i % 2 == 0) ? Color.GRAY : Color.DARK_GRAY, (i + 1) * sep, servers.size());
        }
    }

    public Server pickServer(Job j) {
        // Implementation of picking server should be provided in subclasses
        return null;
    }
}
