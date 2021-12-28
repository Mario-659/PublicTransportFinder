package PublicTransportFinder.view.markerControllers;

import PublicTransportFinder.database.accessors.TramAccessor;
import javafx.scene.web.WebEngine;

public class TramMarkersController extends MarkerController{
    private final WebEngine engine;

    public TramMarkersController(WebEngine engine) {
        super(new TramAccessor());
        this.engine = engine;
    }

    @Override
    protected void updateMarkers() {
        engine.executeScript("document.setTramMarkers(" + dataToJSON() + ")");
    }
}
