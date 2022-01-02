package PublicTransportFinder.view.markerControllers;

import PublicTransportFinder.database.DataManager;
import PublicTransportFinder.database.accessors.Accessor;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ToggleButton;
import org.json.JSONObject;

public abstract class MarkerController {
    private final DataManager dataManager;
    private final ObservableList<JSONObject> values;

    protected MarkerController(Accessor accessor){
        dataManager = new DataManager(accessor);
        values = dataManager.getData();
        values.addListener(new ListChangeListener<JSONObject>() {
            @Override
            public void onChanged(Change<? extends JSONObject> c) {
                updateView();
            }
        });
    }

    abstract void updateMarkers();

    public void addMarker(ActionEvent actEv){
        ToggleButton button = (ToggleButton) actEv.getSource();
        if(button.isSelected()) dataManager.save(button.getId());
        else {
            dataManager.delete(button.getId());
        }
    }

    protected String dataToJSON(){
        return values.toString();
    }

    private void updateView(){
        Platform.runLater(this::updateMarkers);
    }

    public void refreshMarkers(){ dataManager.update();}
}
