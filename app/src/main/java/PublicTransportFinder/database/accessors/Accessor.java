package PublicTransportFinder.database.accessors;
import PublicTransportFinder.database.Connection;

import java.io.IOException;

public abstract class Accessor{
    private final String postParamsPrefix;
    private final Connection connection = new Connection();

    Accessor(String postParamsPrefix){
        this.postParamsPrefix = postParamsPrefix;
    }

    public String get(String line) throws IOException, InterruptedException {
        return connection.getResponse(postParamsPrefix + line).body();
    }

    public String get(String[] lines) throws IOException, InterruptedException{
        return connection.getResponse(getQuery(lines)).body();
    }

    private String getQuery(String[] lines) throws IOException, InterruptedException{
        StringBuilder query = new StringBuilder();
        for (String line : lines) { query.append(postParamsPrefix).append(line).append("&"); }
        return query.substring(0, query.length()-1);
    }
}
