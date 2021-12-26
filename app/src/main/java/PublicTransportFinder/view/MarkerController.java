package PublicTransportFinder.view;

import PublicTransportFinder.database.DataManager;
import PublicTransportFinder.database.accessors.Accessor;
import PublicTransportFinder.tools.JSONParser;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public abstract class MarkerController {
    private final DataManager dataManager;
    private final ObservableList<String> lines;

    protected MarkerController(Accessor accessor){
        dataManager = new DataManager(accessor);
        lines = dataManager.getData();
        lines.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                updateView();
            }
        });
    }

    abstract void updateMarkers();

    protected void addMarker(ActionEvent actEv){
        dataManager.save(getId(actEv));
    }

    protected void deleteMarker(ActionEvent actEv){
        dataManager.delete(getId(actEv));
    }

    protected String dataToJSON(){
        return JSONParser.parseToJSON(lines);
    }

    private void updateView(){
        Platform.runLater(this::updateMarkers);
    }

    private String getId(ActionEvent actionEvent){
        return ((Node) actionEvent.getSource()).getId();
    }

}
