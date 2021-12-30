package PublicTransportFinder.database;

import PublicTransportFinder.database.accessors.Accessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataManager {
    private final Accessor accessor;
    private final ObservableList<String> data = FXCollections.observableArrayList();
    private final HashMap<String, String> storedLines = new HashMap<>();

    public DataManager(Accessor dataAccessor){
        this.accessor = dataAccessor;
    }

    public void save(String line){
        if(storedLines.containsKey(line)) return;
        try {
            storedLines.put(line, accessor.get(line));
            resetList();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(String line){
        if(storedLines.remove(line) != null){
            resetList();
        }
    }

    public void deleteAll(){
        storedLines.clear();
        resetList();
    }

    public void update(){
        try{
            for (Map.Entry<String, String> entry : storedLines.entrySet()) {
                    entry.setValue(accessor.get(entry.getKey()));
            }
            //TODO zrobić tutaj zeby na raz ściągało i z tego nową hash mape może, a potem zrób ten radar
//            String[] lines = storedLines.keySet().toArray(new String[0]);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); }
        resetList();
    }

    public ObservableList<String> getData(){
        return data;
    }

    private void resetList(){
        data.setAll(storedLines.values());
    }
}
