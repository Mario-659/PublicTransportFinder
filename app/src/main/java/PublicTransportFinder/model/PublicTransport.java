package PublicTransportFinder.model;

public abstract class PublicTransport {

    public abstract float getLongitude();
    public abstract float getLatitude();
    public abstract String getIcon();

    public String toJSON(){
        return "{ lat: " + getLatitude() + ", lon: " + getLongitude() + ", icon: \"" + getIcon() + "\" }";
    }
}
