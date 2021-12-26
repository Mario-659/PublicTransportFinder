package PublicTransportFinder.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class HomeController {
    @FXML private WebView map;
    private WebEngine engine;
    private BusMarkersController busController;
    private TramMarkersController tramController;

    @FXML
    private void initialize(){
        engine = map.getEngine();
        engine.load(getClass().getClassLoader().getResource("googleMaps.html").toString());

        busController = new BusMarkersController(engine);
        tramController = new TramMarkersController(engine);
    }

    @FXML
    private void deleteMarkers(ActionEvent actionEvent) {
        engine.executeScript("document.clearAllBusTramMarkers()");
    }

    @FXML
    private void addBusTracker(ActionEvent actionEvent) {busController.addMarker(actionEvent);}

    @FXML
    private void addTramTracker(ActionEvent actionEvent) {tramController.addMarker(actionEvent);}

    @FXML
    private void setRoadmap(ActionEvent actionEvent) {
        engine.executeScript("document.setMapTypeRoad()");
    }

    @FXML
    private void setSatellite(ActionEvent actionEvent) {
        engine.executeScript("document.setMapTypeSatellite()");
    }

    @FXML
    private void setHybrid(ActionEvent actionEvent) {
        engine.executeScript("document.setMapTypeHybrid()");
    }

    @FXML
    private void setTerrain(ActionEvent actionEvent) {
        engine.executeScript("document.setMapTypeTerrain()");
    }
}
