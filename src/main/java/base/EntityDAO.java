package base;

import java.lang.reflect.Array;

public abstract class EntityDAO<T extends HasId> { //A custom collection class
    protected T[] items;
    private final Class<T> type;

    public EntityDAO(Class<T> type) {
        this.type = type;
        items = (T[]) Array.newInstance(type, 0);
    }
    public EntityDAO(T[] items, Class<T> type) {
        this.type = type;
        this.items = items;
    }

    public boolean add(T item) {
        String id = item.getId();

        for (T existing : items) {
            if (id.equals(existing.getId())) {
                return false;
            }
        }

        T[] newItems = (T[]) Array.newInstance(type, items.length + 1);;

        System.arraycopy(items, 0, newItems, 0, items.length);

        newItems[items.length] = item;
        items = newItems;
        return true;
    }

    public T getById(String id) {
        for (T item : items) {
            if (id.equals(item.getId())) {
                return item;
            }
        }
        return null;
    }

    public boolean delete(String id) {
        for (int i = 0; i < items.length; i++) {
            if (id.equals(items[i].getId())) {
                T[] newItems = (T[]) Array.newInstance(type, items.length - 1);;

                int index = 0;
                for (int j = 0; j < items.length; j++) {
                    if (j != i) {
                        newItems[index++] = items[j];
                    }
                }

                items = newItems;
                return true;
            }
        }
        return false;
    }

    public void clear() {
        items = (T[]) Array.newInstance(type, 0);;
    }

    public T[] getAll() {
        return items;
    }
    public void setAll(T[] items) {this.items = items;}
}