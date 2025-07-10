/*
 * The purpose of this file is to assign the jobs to the servers based on
 * whichever server has the shortest queue and wait time of the jobs.
 * Author: Svita Kiran
 * Date: 3/16/24
*/

public class ShortestQueueDispatcher extends JobDispatcher {
    public ShortestQueueDispatcher(int k, boolean showViz) {
        super(k, showViz);
    }

    @Override
    public Server pickServer(Job j) {
        Server shortestQueueServer = servers.get(0);
        for (Server server : servers) {
            if (server.size() < shortestQueueServer.size()) {
                shortestQueueServer = server;
            }
        }
        return shortestQueueServer;
    }
}
