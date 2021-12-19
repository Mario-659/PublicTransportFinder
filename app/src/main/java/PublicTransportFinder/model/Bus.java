package PublicTransportFinder.model;

public class Bus extends PublicTransport{
    private final float longitude;
    private final float latitude;
    private static final String icon = "images/blueIcon.png";

    public Bus(float longitude, float latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public float getLongitude() {
        return longitude;
    }

    @Override
    public float getLatitude() {
        return latitude;
    }

    @Override
    public String getIcon() {
        return icon;
    }
}
