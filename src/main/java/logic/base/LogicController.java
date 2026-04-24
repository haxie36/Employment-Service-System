package logic.base;

public abstract class LogicController<T extends HasId, I, D extends EntityDAO<T>> {
    protected T creation = null;
    protected D DAO;

    public LogicController(D DAO) {
        this.DAO = DAO;
    }

    public abstract void newCreation();

    public void save(){
        if (creation == null) {
            throw new IllegalArgumentException("Save is imposible!");
        }
        DAO.add(creation);
        clear();
    }

    public abstract void create(I input);

    public void clear(){creation=null;}

    public boolean delete(T toBeDeleted){
        if (toBeDeleted == null)
            throw new IllegalArgumentException("Object is null!");
        return DAO.delete(toBeDeleted.getId());
    }

    public T[] getAll(){return DAO.getAll();}

    public T getById(int id) {
        return DAO.getById(id);
    }
}
