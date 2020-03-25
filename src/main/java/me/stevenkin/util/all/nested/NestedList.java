package me.stevenkin.util.all.nested;


public interface NestedList<E> extends Iterable<E> {

    E get(int index);

    void set(int index, E e);

    boolean contain(E e);

    int indexOf(E e);

    NestedList<E> addAll(NestedList<E> nestedList);

    void remove(int index);

    boolean remove(E e);

    ModifiedIterator<E> iterator();

}
