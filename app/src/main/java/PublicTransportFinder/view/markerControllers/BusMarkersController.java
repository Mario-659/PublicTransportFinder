package PublicTransportFinder.view.markerControllers;

import PublicTransportFinder.database.accessors.BusAccessor;
import javafx.scene.web.WebEngine;

public class BusMarkersController extends MarkerController{
    private final WebEngine engine;

    public BusMarkersController(WebEngine engine) {
        super(new BusAccessor());
        this.engine = engine;
    }

    @Override
    public void updateMarkers() {
        engine.executeScript("document.setBusMarkers(" + dataToJSON() + ")");
    }
}
