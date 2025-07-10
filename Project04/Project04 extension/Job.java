/*
file name:      Job.java
Authors:        Ike Lage
last modified:  03/07/2024
*/

public class Job {

    public double arrivalTime;
    private double finishTime;
    private double processingTimeNeeded;
    private double processingTimeSpent;
    private double size; // New attribute to represent the size of the job

    public Job(double arrivalTime, double processingTimeNeeded, double size) {
        this.arrivalTime = arrivalTime;
        this.processingTimeNeeded = processingTimeNeeded;
        this.processingTimeSpent = 0.;
        this.size = size;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public double getProcessingTimeNeeded() {
        return processingTimeNeeded;
    }

    public double getProcessingTimeRemaining() {
        return processingTimeNeeded - processingTimeSpent;
    }

    public boolean isFinished() {
        return getProcessingTimeRemaining() <= 0;
    }

    public double timeInQueue() {
        return finishTime - arrivalTime;
    }

    public void process(double timeToProcessFor, double timeProcessingStarted) {
        processingTimeSpent += timeToProcessFor;
        if (isFinished()) {
            finishTime = timeProcessingStarted + timeToProcessFor;
        }
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String toString() {
        return "Arrival: " + arrivalTime + ", Finish: " + finishTime + ", Time Needed: " + processingTimeNeeded
                + ", Time Spent: " + processingTimeSpent;
    }
}
