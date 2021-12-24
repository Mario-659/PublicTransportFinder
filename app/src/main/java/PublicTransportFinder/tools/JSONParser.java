package PublicTransportFinder.tools;

import javafx.collections.ObservableList;

public class JSONParser {
    public static String parseToJSON(ObservableList<String> list){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (String s : list) {
            stringBuilder.append(s);
            stringBuilder.append(',');
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
