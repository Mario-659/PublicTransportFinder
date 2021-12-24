package PublicTransportFinder.database;

import PublicTransportFinder.database.accessors.BusAccessor;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataManagerTest {
    private DataManager busManager = new DataManager(new BusAccessor());

    @Test void notifiesListeners(){
        ObservableList<String> list = busManager.getData();
        final boolean[] notified = {false};
        list.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                notified[0] = true;
            }
        });
        busManager.save("136");
        assertTrue(notified[0]);
    }
}
