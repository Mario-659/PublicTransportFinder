package PublicTransportFinder.database;

import PublicTransportFinder.database.accessors.Accessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private final Accessor accessor;
    private final ObservableList<String> data = FXCollections.observableArrayList();
    private final List<String> storedLines = new ArrayList<>();

    public DataManager(Accessor dataAccessor){
        this.accessor = dataAccessor;
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
                storedLines.remove(storedLine);
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

    private String getName(String JSON){
        JSONArray array = new JSONArray(JSON);
        return array.getJSONObject(0).getString("name");
    }
}
