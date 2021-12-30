package PublicTransportFinder.database;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.json.*;

class ConnectionTest {
    private static Connection connection = new Connection();
//    private final Connection connection = new Connection();

//    @Test void getsJSONWithBuses() throws IOException, InterruptedException {
//        assertGetsJSON("busList[bus][]=", "132");
//    }

//    @Test void getsJSONWithTrams() throws IOException, InterruptedException {
//        assertGetsJSON("busList[tram][]=", "2");
//    }

    private void assertGetsJSON(String postPrefix, String line) throws IOException, InterruptedException {
        HttpResponse<String> response = connection.getResponse(postPrefix + line);
        assertEquals(200, response.statusCode());
        assertEquals(line, getName(response.body()));
    }


    private String getName(String JSON){
        JSONArray array = new JSONArray(JSON);
        return array.getJSONObject(0).getString("name");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpResponse<String> response = connection.getResponse("busList[bus][]");
        System.out.println(response.body());

        response = connection.getResponse("busList[bus][]=259&busList[bus][]=257");
        System.out.println(response.body());

        response = connection.getResponse("x=51.104164&y=17.03746&rad=5");
        System.out.println(response.body());

        response = connection.getResponse("x=51.104164&y=17.03746");
        System.out.println(response.body());

        response = connection.getResponse("x=51.104164&y=17.03746&proximity=5");
        System.out.println(response.body());
    }
}
