/*
 * The purpose of this file is to distribute the jobs randomly between
 * all the servers and is an extension of the job dispatcher class.
 * Author: Svita Kiran
 * Date: 3/16/24
*/

import java.util.Random;

public class RandomDispatcher extends JobDispatcher {
    private Random random;

    public RandomDispatcher(int k, boolean showViz) {
        super(k, showViz);
        random = new Random();
    }

    @Override
    public Server pickServer(Job j) {
        return servers.get(random.nextInt(servers.size()));
    }
}
