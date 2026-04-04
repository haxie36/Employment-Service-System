package common;

import interfaces.HasId;

public class EntityCollection<T extends HasId> { //A custom collection class
    protected T[] items;

    public EntityCollection() {
        items = (T[]) new HasId[0];
    }

    public EntityCollection(T[] items) {
        this.items = items;
    }

    public boolean add(T item) {
        String id = item.getId();

        for (T existing : items) {
            if (id.equals(existing.getId())) {
                return false;
            }
        }

        T[] newItems = (T[]) new HasId[items.length + 1];

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
                T[] newItems = (T[]) new HasId[items.length - 1];

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
        items = (T[]) new HasId[0];
    }

    public T[] getAll() {
        return items;
    }
}