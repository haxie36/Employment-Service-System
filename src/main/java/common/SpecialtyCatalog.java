package common;

//Checks if the specialty is real
public class SpecialtyCatalog {
    private final String[] specialties;

    public SpecialtyCatalog(String[] specialties) {
        this.specialties = specialties;
    }
    public SpecialtyCatalog() {specialties = new String[]{"1"};}

    public boolean isRealSpecialty(String specialty) {
        for (int i=0; i<specialties.length; i++) {
            if (specialty.equals(specialties[i]))
                return true;
        }
        return false;
    }
}
