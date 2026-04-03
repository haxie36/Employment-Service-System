package registration;

//Checks if an address is serviced or not
public class ServiceArea {
    private final String[] serviceArea;

    public ServiceArea(String[] serviceArea) {this.serviceArea = serviceArea;}
    public ServiceArea() {serviceArea = new String[]{"1"};}

    public boolean isServiceArea(String area) {
        for (String servicedArea : serviceArea) {
            if (area.equals(servicedArea))
                return true;
        }
        return false;
    }
}
