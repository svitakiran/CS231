/*
 * The purpose of this file is to create a type of dispatcher that assigns
 * jobs based on the least amount of work that the server has.
 * Author: Svita Kiran
 * Date: 3/16/24
*/

public class LeastWorkDispatcher extends JobDispatcher {
    public LeastWorkDispatcher(int k, boolean showViz) {
        super(k, showViz);
    }

    @Override
    public Server pickServer(Job j) {
        Server leastWorkServer = servers.get(0);
        for (Server server : servers) {
            if (server.remainingWorkInQueue() < leastWorkServer.remainingWorkInQueue()) {
                leastWorkServer = server;
            }
        }
        return leastWorkServer;
    }
}
