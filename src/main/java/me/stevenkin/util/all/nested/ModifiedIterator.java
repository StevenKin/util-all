package me.stevenkin.util.all.nested;

import java.util.Iterator;

public interface ModifiedIterator<E> extends Iterator<E> {

    void set(E e);
}
