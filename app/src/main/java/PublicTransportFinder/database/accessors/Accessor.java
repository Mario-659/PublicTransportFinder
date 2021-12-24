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
}
