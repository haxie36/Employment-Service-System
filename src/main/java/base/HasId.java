package base;

public interface HasId {
    String getId();
    void setId(String id);

    static String getClassName() {return "HasId";}
}
