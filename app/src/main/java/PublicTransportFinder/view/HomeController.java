package PublicTransportFinder.view;

import PublicTransportFinder.database.DataManager;
import PublicTransportFinder.database.accessors.BusAccessor;
import PublicTransportFinder.database.accessors.TramAccessor;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import PublicTransportFinder.tools.JSONParser;

public class HomeController {
    @FXML private WebView map;
    private WebEngine engine;
    private DataManager busManager = new DataManager(new BusAccessor());
    private DataManager tramManager = new DataManager(new TramAccessor());

    private ObservableList<String> busLines = busManager.getData();
    private ObservableList<String> tramLines = tramManager.getData();

    @FXML
    private void initialize(){
        engine = map.getEngine();
        engine.load(getClass().getClassLoader().getResource("googleMaps.html").toString());
        busLines.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                updateBuses();
            }
        });

        tramLines.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {

            }
        });
    }

    @FXML
    private void deleteMarkers(ActionEvent actionEvent) {
        engine.executeScript("document.clearAllBusTramMarkers()");
    }

    @FXML
    private void addBusTracker(ActionEvent actionEvent) {
        String buttonId = ((Node) actionEvent.getSource()).getId();
        busManager.save(buttonId);
    }

    @FXML
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

    private void updateBuses(){
        Platform.runLater(() -> temp());
    }

    private int updatedTimes = 0;
    private void temp(){
        engine.executeScript("document.setBuses(" + JSONParser.parseToJSON(busLines) + ")");
        //TODO delete this, it is for debugging
        System.out.println(updatedTimes++);
    }

    @FXML
    public void setTerr(ActionEvent actionEvent) {
    }
}
