package base;

public abstract class LogicController<T extends HasId, I, D extends EntityDAO<T>> {
    protected T creation = null;
    protected D DAO;

    public LogicController(D DAO) {
        this.DAO = DAO;
    }

    public abstract void newCreation();

    public boolean save(){
        if (creation!=null){
            setCreationId(); //temp
            DAO.add(creation);
            clear();
            return true;
        }
        return false;
    }
    //temp
    protected void setCreationId(){
        creation.setId(String.valueOf(DAO.getAll().length+1));
    }

    public abstract void create(I input);

    public void clear(){creation=null;}

    public boolean delete(T toBeDeleted){return DAO.delete(toBeDeleted.getId());}

    public T[] getAll(){return DAO.getAll();}
}
