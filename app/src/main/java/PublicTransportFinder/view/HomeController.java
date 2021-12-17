package PublicTransportFinder.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class HomeController {
    @FXML private WebView map;
    private WebEngine engine;

    @FXML
    private void initialize(){
        engine = map.getEngine();
        engine.load(getClass().getResource("googleMaps.html").toString());
    }

    @FXML
    private void setTerr(ActionEvent actionEvent) {
        engine.executeScript("document.setMapTypeHybrid()");
    }
}
