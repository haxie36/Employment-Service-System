package base;

public abstract class LogicController<T extends HasId, I> {
    protected T creation = null;
    protected EntityCollection<T> collection;

    public LogicController(EntityCollection<T> collection) {
        this.collection = collection;
    }

    public abstract void newCreation();

    public boolean save(){
        if (creation!=null){
            setCreationId(); //temp
            collection.add(creation);
            clear();
            return true;
        }
        return false;
    }
    //temp
    private void setCreationId(){
        creation.setId(String.valueOf(collection.getAll().length+1));
    }

    public abstract void create(I input);

    public void clear(){creation=null;}

    public boolean delete(T toBeDeleted){return collection.delete(toBeDeleted.getId());}
}
