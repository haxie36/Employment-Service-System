package common;

import logic.common.SpecialtyCatalog;

public class TestSpecialtyCatalog extends SpecialtyCatalog {
    @Override
    public boolean isRealSpecialty(String specialty) {
        return specialty.equals("121");
    }
}
