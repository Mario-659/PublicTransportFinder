package PublicTransportFinder.tools;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Refresher {
    private ScheduledFuture<?> future;
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    private final Runnable runAll;

    public Refresher(int refreshRate, Function... operations){
        runAll  = () -> {
            try{
                for (Function operation : operations) {
                    operation.execute(); }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        };
        setScheduler(refreshRate);
    }

    public void setRefreshRate(int refreshRate){
        future.cancel(false);
        setScheduler(refreshRate);
    }

    private void setScheduler(int refreshRate) {
        future = scheduler.scheduleAtFixedRate(runAll, refreshRate, refreshRate, SECONDS);
        scheduler.schedule(() -> { future.cancel(true); }, 60 * 60, SECONDS);
    }

    public Runnable getRunAll(){ return runAll;}
}
