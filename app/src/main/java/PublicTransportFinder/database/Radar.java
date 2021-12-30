package PublicTransportFinder.database;

import PublicTransportFinder.database.accessors.BusAccessor;
import PublicTransportFinder.database.accessors.TramAccessor;
import PublicTransportFinder.tools.Haversine;
import org.json.JSONArray;
import org.json.JSONObject;

public class Radar {
    private final String[] busLines = {"A", "C", "D", "K", "N", "100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "118", "119", "120", "121", "122", "124", "125", "126", "127", "128", "129", "130", "131", "132", "133", "134", "136", "140", "142", "143", "144", "145", "146", "147", "148", "149", "150", "151", "206", "240", "241", "242", "243", "244", "245", "246", "247", "248", "249", "250", "251", "253", "255", "257", "259", "315", "319", "602", "607", "700", "703", "709", "731"};
    private final String[] tramLines = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "15", "16", "17", "20", "23", "31", "33", "70", "74"};
    private float x;
    private float y;
    private float proximity;
    private JSONArray buses;
    private JSONArray trams;

    private JSONArray inRange = new JSONArray();

    String busData;
    String tramData;

    private final BusAccessor busAccessor = new BusAccessor();
    private final TramAccessor tramAccessor = new TramAccessor();

    public void setPoint(float x, float y, float proximity){
        System.out.println("Point has been set: " + x + ", " + y);
        this.x = x;
        this.y = y;
        this.proximity = proximity;
    }

    public float getRadius() { return proximity; }

    public String getDescription(){
        System.out.println("works");
        inRange = new JSONArray();
        getData();
        getJsonObj();
        checkLine(buses);
        checkLine(trams);
        String message = buildMessage();
        System.out.println(message);
        return message;
    }

    private void getData(){
        try{
            busData = busAccessor.get(busLines);
            System.out.println(busData);
            tramData = tramAccessor.get(tramLines);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void getJsonObj(){
        try{
            buses = new JSONArray(busData);
            trams = new JSONArray(tramData);
        }catch(Exception ignored) {}
    }

    private void checkLine(JSONArray line){
        for (int i=0; i<line.length(); i++) {
            checkIfInRange(line.getJSONObject(i));
        }
    }

    private void checkIfInRange(JSONObject object){
        System.out.println("x: " + x + "  y: " + y + " proximity: " + proximity);
        double X =  object.getDouble("x");
        double Y = object.getDouble("y");
        if((float) Haversine.haversine(x, y,X, Y) <= proximity) inRange.put(object);
    }

    String buildMessage(){
        int trams=0, buses = 0;
        for(int i=0; i<inRange.length(); i++){
            JSONObject o = inRange.getJSONObject(i);
            if(o.getString("type").equals("bus")) buses++;
            else trams++;
        }
        return "There are " + buses + " and " + trams + " trams";
    }

}
