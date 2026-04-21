package profile;

import logic.profile.ServiceArea;

public class TestServiceArea extends ServiceArea {
    @Override
    public boolean isServiceArea(String area) {
        return area.equals("test");
    }
}
