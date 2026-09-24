package edu.unc.comp210.List;

public class MyArrayList<E> implements MyList<E> {
    private static final int INITIAL_CAPACITY=10;

    private E[] array;
    int size;

    public MyArrayList(){
        this.array = (E[])(new Object[INITIAL_CAPACITY]);
        this.size = 0;
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean add(E elem) {
        this.size++;
        return false;
    }

    @Override
    public void add(int idx, E elem) {
        this.size++;

    }

    @Override
    public E remove(int idx) {
        this.size--;

        return null;
    }

    @Override
    public E get(int idx) {
        return null;
    }

    @Override
    public int indexOf(E elem) {
        return 0;
    }
}
