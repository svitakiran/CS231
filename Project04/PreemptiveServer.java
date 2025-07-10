/*
 * The purpose of this file is to optimize and make the server class
 * more functional based on how the jobs in its queue are processed.
 * Author: Svita Kiran
 * Date: 3/19/24
*/

public class PreemptiveServer extends Server {

    @Override
    public void processTo(double time) {
        double timeLeft = time - this.time;
        while (!jobQueue.isEmpty() && timeLeft > 0) {
            Job job = jobQueue.findMin(new Comparator<Job>() {
                @Override
                public int compare(Job o1, Job o2) {
                    return Double.compare(o1.getProcessingTimeRemaining(), o2.getProcessingTimeRemaining());
                }
            });
            double timeToProcess = Math.min(timeLeft, job.getProcessingTimeRemaining());
            job.process(timeToProcess, this.time);
            totalWaitingTime += job.timeInQueue();
            remainingTime -= timeToProcess;
            if (job.isFinished()) {
                jobQueue.removeMin(new Comparator<Job>() {
                    @Override
                    public int compare(Job o1, Job o2) {
                        return Double.compare(o1.getProcessingTimeRemaining(), o2.getProcessingTimeRemaining());
                    }
                });
                numJobs++;
            }
            timeLeft -= timeToProcess;
        }
        this.time = time;
    }
}
