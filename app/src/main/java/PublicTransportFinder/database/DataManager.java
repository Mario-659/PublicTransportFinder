package PublicTransportFinder.database;

import PublicTransportFinder.database.accessors.Accessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class DataManager {
    private final Accessor accessor;
    private final ObservableList<JSONObject> data = FXCollections.observableArrayList();
    private final HashSet<String> storedLines = new HashSet<>();

    public DataManager(Accessor dataAccessor){
        this.accessor = dataAccessor;
    }

    public void save(String line){
        if(storedLines.contains(line)) return;
        storedLines.add(line);
        update();
    }

    public void delete(String line){
        if(storedLines.remove(line)){
            update();
        }
    }

    public void deleteAll(){
        storedLines.clear();
        update();
    }

    public void update(){
        try{
            String[] lines = storedLines.toArray(new String[0]);
            if(lines.length != 0){
                String retrievedData = accessor.get(lines);
                JSONArray jsonArray = new JSONArray(retrievedData);
                List<JSONObject> jsList = new ArrayList<>();
                for(int i=0; i<jsonArray.length(); i++) jsList.add(jsonArray.getJSONObject(i));
                data.setAll(jsList);
            }
            else data.clear();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); }
    }

    public ObservableList<JSONObject> getData(){
        return data;
    }

    private List<String> toArray(String data){
        return new ArrayList<String>(Arrays.asList(data.replace("[", "").replace("]", "").split(",")));
    }

    private HashMap<String, JSONArray> sortOut(String data){
        JSONArray jsonArray = new JSONArray(data);
        HashMap<String, JSONArray> map = new HashMap<>();
        for (int i=0; i< jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String name = obj.getString("name");
            JSONArray jsArray = map.get(name);
            if(jsArray == null) {
                map.put(name, new JSONArray());
                jsArray = map.get(name);
            }
            jsArray.put(obj);
        }
        return map;
    }
}
