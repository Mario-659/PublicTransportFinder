package PublicTransportFinder.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class HomeController {
    @FXML private WebView map;
    private WebEngine engine;

    @FXML
    private void initialize(){
        engine = map.getEngine();
        engine.load(getClass().getClassLoader().getResource("googleMaps.html").toString());
    }

    @FXML
    private void setTerr(ActionEvent actionEvent) {
        engine.executeScript("document.addBusMarker(51.105286, 17.079487, 13)");
    }

    @FXML
    private void deleteMarkers(ActionEvent actionEvent) {
        engine.executeScript("document.clearAllBusTramMarkers()");
    }

    @FXML
    private void addBusTracker(ActionEvent actionEvent) {
        String buttonId = ((Node) actionEvent.getSource()).getId();
        System.out.println(buttonId);
    }

    public void addTramTracker(ActionEvent actionEvent) {
        String buttonId = ((Node) actionEvent.getSource()).getId();
        System.out.println(buttonId);
    }

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
