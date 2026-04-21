package logic.base;

public abstract class LogicController<T extends HasId, I, D extends EntityDAO<T>> {
    protected T creation = null;
    protected D DAO;

    public LogicController(D DAO) {
        this.DAO = DAO;
    }

    public abstract void newCreation();

    public boolean save(){
        if (creation!=null){
            DAO.add(creation);
            clear();
            return true;
        }
        return false;
    }

    public abstract void create(I input);

    public void clear(){creation=null;}

    public boolean delete(T toBeDeleted){
        if (toBeDeleted == null){return false;}
        return DAO.delete(toBeDeleted.getId());
    }

    public T[] getAll(){return DAO.getAll();}

    public T getById(int id) {
        return DAO.getById(id);
    }
}
