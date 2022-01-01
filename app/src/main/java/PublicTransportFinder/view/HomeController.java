package PublicTransportFinder.view;

import PublicTransportFinder.database.Radar;
import PublicTransportFinder.database.accessors.Accessor;
import PublicTransportFinder.database.accessors.BusAccessor;
import PublicTransportFinder.database.accessors.TramAccessor;
import PublicTransportFinder.tools.Refresher;
import PublicTransportFinder.view.markerControllers.BusMarkersController;
import PublicTransportFinder.view.markerControllers.TramMarkersController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.util.Map;

public class HomeController {
    @FXML private WebView map;
    private WebEngine engine;
    private BusMarkersController busController;
    private TramMarkersController tramController;
    private Refresher refresher;
    private final Radar radar = new Radar(new Accessor[]{
            new BusAccessor(),
            new TramAccessor()});

    @FXML
    private void initialize(){
        engine = map.getEngine();
        engine.load(getClass().getClassLoader().getResource("googleMaps.html").toString());

        busController = new BusMarkersController(engine);
        tramController = new TramMarkersController(engine);

        refresher = new Refresher(5, this::updateMarkers);

        JSObject bridge = (JSObject) engine.executeScript("window");
        bridge.setMember("radar", radar);
    }

    @FXML private void addBusTracker(ActionEvent actionEvent) {
        busController.addMarker(actionEvent);}

    @FXML private void addTramTracker(ActionEvent actionEvent) {
        tramController.addMarker(actionEvent);}

    @FXML private void setGrey(ActionEvent actionEvent) {
        engine.executeScript("document.setMapTypeGrey()"); }

    @FXML private void setDark(ActionEvent actionEvent) {
        engine.executeScript("document.setMapTypeDark()"); }

    @FXML private void setRoadmap(ActionEvent actionEvent) {
        engine.executeScript("document.setMapTypeRoad()"); }

    @FXML private void setSatellite(ActionEvent actionEvent) {
        engine.executeScript("document.setMapTypeSatellite()"); }

    @FXML private void setHybrid(ActionEvent actionEvent) {
        engine.executeScript("document.setMapTypeHybrid()"); }

    @FXML private void setTerrain(ActionEvent actionEvent) {
        engine.executeScript("document.setMapTypeTerrain()"); }

    private void updateMarkers(){
        Platform.runLater( () -> {
            busController.refreshMarkers();
            tramController.refreshMarkers(); });
    }

    private final Map<String, Integer> refreshRate = Map.of( "refresh5s", 5,
            "refresh10s", 10, "refresh15s", 15, "refresh30s", 30);

    @FXML
    private void setRefreshRate(ActionEvent actionEvent) {
        String id = ((MenuItem) actionEvent.getSource()).getId();
        refresher.setRefreshRate(refreshRate.get(id));
    }

    private final Map<String, Double> proximity = Map.of("prox100m", 0.1d,
            "prox250m", 0.25d, "prox500m", 0.5d, "prox1km", 1d, "prox2km500m", 2.5d, "prox5km", 5d);

    @FXML
    private void setProximity(ActionEvent actionEvent){
        String id = ((MenuItem) actionEvent.getSource()).getId();
        radar.setProximity(proximity.get(id));
    }
}
