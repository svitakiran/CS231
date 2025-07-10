/*
 * The purpose of this file is to represent one server in the 
 * total server farm simulation. It keeps track of the jobs and the queue.
 * Author: Svita Kiran
 * Date: 3/17/24 
*/

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.Queue;

public class Server {
    private Queue<Job> jobQueue;
    private double time;
    private double totalWaitingTime;
    private double remainingTime;
    private int numJobs;

    public Server() {
        jobQueue = new LinkedList<>();
        time = 0;
        totalWaitingTime = 0;
        remainingTime = 0;
        numJobs = 0;
    }

    public void addJob(Job job) {
        jobQueue.add(job);
        remainingTime += job.getProcessingTimeNeeded();
    }

    public void processTo(double time) {
        double timeLeft = time - this.time;
        while (!jobQueue.isEmpty() && timeLeft > 0) {
            Job job = jobQueue.peek();
            double timeToProcess = Math.min(timeLeft, job.getProcessingTimeRemaining());
            job.process(timeToProcess, this.time);
            totalWaitingTime += job.timeInQueue();
            remainingTime -= timeToProcess;
            if (job.isFinished()) {
                jobQueue.poll();
                numJobs++;
            }
            timeLeft -= timeToProcess;
        }
        this.time = time;
    }

    public double remainingWorkInQueue() {
        return remainingTime;
    }

    public int size() {
        return jobQueue.size();
    }

    public double timeInQueue() {
        return totalWaitingTime;
    }

    public double getWaitTimeRatio(Job job) {
        double waitTime = job.timeInQueue();
        if (waitTime == 0) {
            return Double.MAX_VALUE; // Avoid division by zero
        } else {
            return job.getSize() / waitTime; // Calculate ratio
        }
    }

    public void draw(Graphics g, Color c, double loc, int numberOfServers) {
        double sep = (ServerFarmViz.HEIGHT - 20) / (numberOfServers + 2.0);
        g.setColor(Color.BLACK);
        g.setFont(new Font(g.getFont().getName(), g.getFont().getStyle(), (int) (72.0 * (sep * .5) / Toolkit.getDefaultToolkit().getScreenResolution())));
        g.drawString("Work: " + (remainingWorkInQueue() < 1000 ? remainingWorkInQueue() : ">1000"), 2, (int) (loc + .2 * sep));
        g.drawString("Jobs: " + (size() < 1000 ? size() : ">1000"), 5 , (int) (loc + .55 * sep));
        g.setColor(c); 
        g.fillRect((int) (3 * sep), (int) loc, (int) (.8 * remainingWorkInQueue()), (int) sep);
        g.drawOval(2 * (int) sep, (int) loc, (int) sep, (int) sep);
        if (remainingWorkInQueue() == 0) g.setColor(Color.GREEN.darker());
        else g.setColor(Color.RED.darker());
        g.fillOval(2 * (int) sep, (int) loc, (int) sep, (int) sep);
    }
}
