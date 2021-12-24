package PublicTransportFinder.database;

import PublicTransportFinder.database.accessors.Accessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DataManager {
    private final Accessor accessor;
    private final ObservableList<String> data = FXCollections.observableArrayList();
    private final List<String> storedLines = new ArrayList<>();
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    private int refreshRate = 5;

    public DataManager(Accessor dataAccessor){
        this.accessor = dataAccessor;
        refresh();
    }

    private int refreshes = 0;
    public void refresh() {
        final Runnable refresher = new Runnable() {
            public void run() {
                update();
                System.out.println("refreshed" + refreshes++);}
        };
        final ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(refresher, 5, 5, SECONDS);
        scheduler.schedule(new Runnable() {
            public void run() { beeperHandle.cancel(true); }
        }, 60 * 60, SECONDS);
    }

    public void save(String line){
        if(storedLines.contains(line)) return;
        try {
            data.add(accessor.get(line));
            storedLines.add(line);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(String line){
        for (String storedLine : data) {
            if(line.equals(getName(storedLine))){
                data.remove(line);
                break;
            }
        }
    }

    public void deleteAll(){
        data.clear();
        storedLines.clear();
    }

    public void update(){
        List<String> newList = new ArrayList<>();
        for (String line : storedLines) {
            try{
                newList.add(accessor.get(line));
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        data.setAll(newList);
    }

    public ObservableList<String> getData(){
        return data;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }

    private String getName(String JSON){
        JSONArray array = new JSONArray(JSON);
        return array.getJSONObject(0).getString("name");
    }
}
