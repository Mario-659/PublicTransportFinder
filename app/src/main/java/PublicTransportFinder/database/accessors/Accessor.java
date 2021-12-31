package PublicTransportFinder.database.accessors;
import PublicTransportFinder.database.Connection;

import java.io.IOException;

public abstract class Accessor{
    private final String postParamsPrefix;
    private final Connection connection = new Connection();
    private final String[] allLines;

    Accessor(String postParamsPrefix, String[] allLines){
        this.allLines = allLines;
        this.postParamsPrefix = postParamsPrefix;
    }

    public String get(String line) throws IOException, InterruptedException {
        return connection.getResponse(postParamsPrefix + line).body();
    }

    public String get(String[] lines) throws IOException, InterruptedException{
        return connection.getResponse(getQuery(lines)).body();
    }

    public String getAll() throws IOException, InterruptedException {
        return get(allLines);
    }

    private String getQuery(String[] lines) {
        if(lines.length == 1) return postParamsPrefix + lines[0];
        StringBuilder query = new StringBuilder();
        for (String line : lines) { query.append(postParamsPrefix).append(line).append("&"); }
        return query.substring(0, query.length()-1);
    }
}
