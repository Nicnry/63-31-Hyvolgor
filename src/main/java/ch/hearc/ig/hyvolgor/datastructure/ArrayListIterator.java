package ch.hearc.ig.hyvolgor.datastructure;

import java.util.Iterator;


public class ArrayListIterator<E> implements Iterator<E> {

    private final List<E> list;
    private int current = 0;

    public ArrayListIterator(ArrayList<E> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return current < list.size();
    }

    @Override
    public E next() {
        return list.get(current++);
    }
}