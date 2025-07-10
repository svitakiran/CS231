/*
 * The purpose of this file is to distribute the jobs based on a round
 * robin algorithm and extends the job dispatcher class.
 * Author: Svita Kiran
 * Date: 3/16/24
*/

public class RoundRobinDispatcher extends JobDispatcher {
    private int nextServerIndex;

    public RoundRobinDispatcher(int k, boolean showViz) {
        super(k, showViz);
        nextServerIndex = 0;
    }

    @Override
    public Server pickServer(Job j) {
        Server chosenServer = servers.get(nextServerIndex);
        nextServerIndex = (nextServerIndex + 1) % servers.size();
        return chosenServer;
    }
}
