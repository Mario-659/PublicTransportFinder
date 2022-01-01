package PublicTransportFinder.database;

import PublicTransportFinder.database.accessors.Accessor;
import PublicTransportFinder.tools.Haversine;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Radar {
    private double x;
    private double y;
    private double proximity = 0.5d;
    private JSONArray inProx = new JSONArray();
    private final Accessor[] accessors;

    public Radar(Accessor[] accessors) {
        this.accessors = accessors;
    }

    public void setProximity(double proximity){
        this.proximity = proximity;
    }

    public void setPoint(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getRadius() { return proximity; }

    public String getDescription(){
        refreshData();
        return buildMessage();
    }

    private JSONArray retrieveData(){
        JSONArray data = new JSONArray();
        for (Accessor accessor : accessors) {
            try {
                data.putAll(new JSONArray(accessor.getAll()));
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } }
        return data;
    }

    private JSONArray getInProx(JSONArray jsonArray){
        JSONArray filtered = new JSONArray();
        for (int i=0; i<jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            if(checkIfInProx(obj)) filtered.put(obj);
        }
        return filtered;
    }

    private boolean checkIfInProx(JSONObject object){
        return Haversine.haversine(x, y, object.getDouble("x"), object.getDouble("y")) <= proximity;
    }

    private void refreshData(){
        inProx = getInProx(retrieveData());
    }


    String buildMessage(){
        int trams=0, buses = 0;
        for(int i = 0; i< inProx.length(); i++){
            JSONObject o = inProx.getJSONObject(i);
            if(o.getString("type").equals("bus")) buses++;
            else trams++;
        }
        return "There are " + buses + " buses and " + trams + " trams in " + proximity + " km proximity";
    }
}
