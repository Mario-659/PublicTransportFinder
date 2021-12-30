package PublicTransportFinder.database.accessors;

public class BusAccessor extends Accessor{
    public BusAccessor() {
        super("busList[bus][]=",
                new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
                        "15", "16", "17", "20", "23", "31", "33", "70", "74"});
    }
}