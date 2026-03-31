package registration;

public class ServiceArea {
    private final String[] serviceArea;

    public ServiceArea(String[] serviceArea) {
        this.serviceArea = serviceArea;
    }
    public ServiceArea() {serviceArea = new String[]{"1"};}

    public boolean isServiceArea(String area) {
        for (int i=0; i<serviceArea.length; i++) {
            if (area.equals(serviceArea[i]))
                return true;
        }
        return false;
    }
}
