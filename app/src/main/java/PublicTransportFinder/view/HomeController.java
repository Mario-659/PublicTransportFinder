package PublicTransportFinder.view;

import PublicTransportFinder.tools.Refresher;
import PublicTransportFinder.view.markerControllers.BusMarkersController;
import PublicTransportFinder.view.markerControllers.TramMarkersController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.util.Map;

public class HomeController {
    @FXML private WebView map;
    private WebEngine engine;
    private BusMarkersController busController;
    private TramMarkersController tramController;
    private Refresher refresher;

    @FXML
    private void initialize(){
        engine = map.getEngine();
        engine.load(getClass().getClassLoader().getResource("googleMaps.html").toString());

        busController = new BusMarkersController(engine);
        tramController = new TramMarkersController(engine);

        refresher = new Refresher(5, this::updateMarkers);
    }


    @FXML
    private void deleteMarkers(ActionEvent actionEvent) {
        engine.executeScript("document.clearAllBusTramMarkers()");
    }

    @FXML
    private void addBusTracker(ActionEvent actionEvent) {busController.addMarker(actionEvent);}

    @FXML
    private void addTramTracker(ActionEvent actionEvent) {tramController.addMarker(actionEvent);}

    @FXML private void setRoadmap(ActionEvent actionEvent) {
        engine.executeScript("document.setMapTypeRoad()"); }

    @FXML private void setSatellite(ActionEvent actionEvent) {
        engine.executeScript("document.setMapTypeSatellite()"); }

    @FXML private void setHybrid(ActionEvent actionEvent) {
        engine.executeScript("document.setMapTypeHybrid()"); }

    @FXML private void setTerrain(ActionEvent actionEvent) {
        engine.executeScript("document.setMapTypeTerrain()"); }

    @FXML
    public void update(ActionEvent actionEvent) {
        Runnable runnable = refresher.getRunAll();
        runnable.run();
    }

    private void updateMarkers(){
        Platform.runLater( () -> {
            busController.updateMarkers();
            tramController.updateMarkers(); });
    }

    private final Map<String, Integer> refreshRate = Map.of("refresh1s", 1, "refresh5s", 5, "refresh10s", 10, "refresh15s", 15, "refresh30s", 30);

    @FXML
    private void setRefreshRate(ActionEvent actionEvent) {
        String id = ((MenuItem) actionEvent.getSource()).getId();
        refresher.setRefreshRate(refreshRate.get(id));
    }
}
