package PublicTransportFinder.database;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Connection {
    //TODO from time to time an exception GOAWAY wos thrown, changed client to a local variable and problem seems to be resolved
//    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    public HttpResponse<String> getResponse(String params) throws IOException, InterruptedException {
        System.out.println("sending request with params = " + params);
        HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers
                        .ofString(params, StandardCharsets.UTF_8))
                .uri(URI.create("https://mpk.wroc.pl/bus_position"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}