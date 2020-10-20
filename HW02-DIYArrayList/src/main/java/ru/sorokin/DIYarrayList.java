package ru.sorokin;


import java.util.*;

public class DIYarrayList<T> implements List<T> {
    private Object[] myArrayList;
    private int size;
    private static final int DEFAULT_LENGHT = 10;

    public DIYarrayList() {
        this.myArrayList = (T[]) new Object[DEFAULT_LENGHT];
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(myArrayList, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        gettBigger(1);
        myArrayList[size++] = t; //fix
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] temporaryArray = c.toArray();
        int numNew = temporaryArray.length;
        final int temporarySize;
        temporarySize = size;
        gettBigger(numNew);
        System.arraycopy(temporaryArray, size, myArrayList, temporarySize, numNew);
        size = temporarySize + numNew;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Object[] temporaryArray = c.toArray();
        int numNew = temporaryArray.length;
        final int temporarySize;
        temporarySize = size;
        gettBigger(numNew);
        int numMoved = temporarySize - index;
        if (numMoved > 0) {
            System.arraycopy(myArrayList, index, myArrayList, index + numNew, numMoved);
        }
        System.arraycopy(temporaryArray, 0, myArrayList, index, numNew);
        size = temporarySize + numNew;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListItr();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }


    private class ListItr implements ListIterator<T> {
        int cursor;

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return (T) myArrayList[cursor++];
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public T previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(T t) {
            myArrayList[cursor - 1] = t;

        }

        @Override
        public void add(T t) {
            myArrayList[size++] = t;

        }
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        if (myArrayList.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (Object i : myArrayList) {
                if (i != null) {
                    sb.append(i).append(" ");
                }
            }
            return sb.toString();
        }

        return "array is empty";
    }

    public void sort(Comparator<? super T> c) {
        Arrays.sort((T[]) myArrayList, 0, size, c);
    }

    private void gettBigger(int numNew) {
        if (numNew > (myArrayList.length - (size))) {
            myArrayList = Arrays.copyOf(myArrayList, (myArrayList.length * 3) / 2 + 1 + numNew);
        }
    }


}





