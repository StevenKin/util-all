package me.stevenkin.util.all.nested;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class ArrayNestedList<E> implements NestedList<E> {
    private List<NestedList<E>> list = new ArrayList<>();

    private E e;

    private boolean plainMod;

    private boolean isNull;

    public ArrayNestedList(E e) {
        if (e == null || e instanceof NestedList)
            throw new IllegalArgumentException();
        this.e = e;
        this.plainMod = true;
        this.isNull = false;
    }

    public ArrayNestedList(List<NestedList<E>> lists) {
        if (lists != null && lists.size() > 0) {
            lists.forEach(list::add);
        }
        this.plainMod = false;
    }

    @Override
    public E get(int index) {
        if (index < 0)
            throw new IllegalArgumentException();
        ModifiedIterator<E> modIter = iterator();
        int i = 0;
        while (modIter.hasNext()) {
            E e = modIter.next();
            if (i == index) {
                return e;
            }
            i++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public void set(int index, E e) {
        if (index < 0)
            throw new IllegalArgumentException();
        ModifiedIterator<E> modIter = iterator();
        int i = 0;
        while (modIter.hasNext()) {
            modIter.next();
            if (i == index) {
                modIter.set(e);
            }
            i++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public boolean contain(E e) {
        return indexOf(e) > -1;
    }

    @Override
    public int indexOf(E e) {
        ModifiedIterator<E> modIter = iterator();
        int i = 0;
        while (modIter.hasNext()) {
            E e1 = modIter.next();
            if (e1 == e || e1.equals(e)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public NestedList<E> addAll(NestedList<E> nestedList) {
        if (nestedList != null) {
            list.add(nestedList);
        }
        return this;
    }

    @Override
    public void remove(int index) {
        if (index < 0)
            throw new IllegalArgumentException();
        ModifiedIterator<E> modIter = iterator();
        int i = 0;
        while (modIter.hasNext()) {
            modIter.next();
            if (i == index) {
                modIter.remove();
            }
            i++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public boolean remove(E e) {
        ModifiedIterator<E> modIter = iterator();
        while (modIter.hasNext()) {
            E e1 = modIter.next();
            if (e1 == e || e1.equals(e)) {
                modIter.remove();
            }
        }
        return true;
    }

    @Override
    public ModifiedIterator<E> iterator() {
        return new ArrayNestedListIterator();
    }

    private class ArrayNestedListIterator implements ModifiedIterator<E> {
        private Iterator<NestedList<E>> iterator;

        private ModifiedIterator<E> current;

        private int step;

        private boolean callNext;

        public ArrayNestedListIterator() {
            if (plainMod) {
                if (!isNull) {
                    this.step = 1;
                } else {
                    this.step = 0;
                }
            }
            this.iterator = list.iterator();
            this.current = null;
            this.callNext = false;
        }

        @Override
        public void set(E e) {
            if (!callNext)
                throw new IllegalStateException();
            if (plainMod) {
                if (isNull)
                    throw new IllegalStateException();
                ArrayNestedList.this.e = e;
            } else {
                current.set(e);
            }
        }

        @Override
        public boolean hasNext() {
            boolean b = hasNext0();
            if (b) {
                callNext = false;
            }
            return b;
        }

        private boolean hasNext0() {
            if (plainMod) {
                return step > 0;
            } else {
                if (current != null && current.hasNext())
                    return true;
                while (iterator.hasNext()) {
                    ModifiedIterator<E> modIter = iterator.next().iterator();
                    if (modIter.hasNext()) {
                        current = modIter;
                        return true;
                    }
                }
                return false;
            }
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new IllegalStateException();
            callNext = true;
            if (plainMod) {
                step = 0;
                return e;
            } else {
                return current.next();
            }
        }

        @Override
        public void remove() {
            if (!callNext)
                throw new IllegalStateException();
            if (plainMod) {
                isNull = true;
                e = null;
            } else {
                current.remove();
            }
            callNext = false;
        }
    }

    public static <E> NestedList<E> of(E[] es) {
        List<NestedList<E>> lists = new ArrayList<>();
        if (es != null && es.length > 0) {
            Stream.of(es).forEach(e -> lists.add(new ArrayNestedList<>(e)));
        }
        return new ArrayNestedList<>(lists);
    }
}
