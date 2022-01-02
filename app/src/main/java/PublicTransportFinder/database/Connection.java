package PublicTransportFinder.database;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Connection {
    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    public HttpResponse<String> getResponse(String params) throws IOException, InterruptedException {
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