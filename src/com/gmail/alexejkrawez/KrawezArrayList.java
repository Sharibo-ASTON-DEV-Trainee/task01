package com.gmail.alexejkrawez;

import java.util.*;

/**
 * Resizable-array implementation of the List interface. Implements
 * optional list operations, and permits all elements, including null.
 * In addition to implementing the List interface, this class provides
 * methods to manipulate the size of the array that is used internally
 * to store the list.
 * <p>! Note that this implementation is not synchronized.</p>
 *
 * <p>package: com.gmail.alexejkrawez;</p>
 * <p>email: AlexejKrawez@gmail.com</p>
 * <p>created: 16.12.2022</p>
 *
 * @since Java v1.8
 *
 * @author Alexej Krawez
 * @version 1.0
 * */
public class KrawezArrayList<E> extends AbstractList<E> implements List<E> {


    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 12;

    /**
     * The maximum size of the array to allocate. Some virtual machines
     * reserve some header words in an array. Attempts to allocate arrays
     * of a larger size will be reduced to allocating an array of the
     * maximum size and activating the flag maxCapacity = true.
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Flag indicating whether the maximum allowed capacity size has been
     * reached or not. Further attempts to increase capacity will be rejected.
     * Item insertion and other capacity expansion actions will fail.
     */
    private boolean maxCapacity;

    /**
     * Actual array capacity, same as a call array.length
     */
    private int capacity;

    /**
     * The size of the KrawezArrayList (the number of elements it contains).
     */
    private int size;

    /**
     * The array into which the elements of the KrawezArrayList are stored.
     * The capacity of the KrawezArrayList is the length of this array.
     * When empty KravetsArrayList is initialized, this array is assigned
     * a capacity = DEFAULT_CAPACITY.
     */
    public Object[] array;

    /**
     * Shows the number of elements in the list.
     *
     * @return the number of elements in the list.
     */
    public int size() {
        return size;
    }


    /**
     * Constructs an empty list with an initial capacity of DEFAULT_CAPACITY.
     */
    public KrawezArrayList() {
        capacity = DEFAULT_CAPACITY;
        array = new Object[capacity];
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *                                  is negative
     */
    public KrawezArrayList(int initialCapacity) {

        if (initialCapacity >= 0 & initialCapacity <= 12) {
            capacity = DEFAULT_CAPACITY;
            array = new Object[DEFAULT_CAPACITY];
        } else if (initialCapacity > 12) {
            capacity = initialCapacity;
            array = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the original order.
     *
     * @param collection the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public KrawezArrayList(Collection<? extends E> collection) {

        if (collection.isEmpty()) {
            capacity = DEFAULT_CAPACITY;
            array = new Object[capacity];
        } else if (collection.size() <= 12) {
            capacity = DEFAULT_CAPACITY;
            size = collection.size();
            array = collection.toArray();
        } else {
            capacity = collection.size();
            size = capacity;
            array = collection.toArray();
        }
    }

    private boolean isEnoughCapacity() {

        if ((size + 1) <= capacity) {
            return true;
        } else {
            return false;
        }
    }

    private boolean growCapacity() {

        if (maxCapacity) {
            return false;
        }

        long newCapacity = (long) capacity + (capacity >> 1);

        if (newCapacity >= MAX_ARRAY_SIZE) {
            capacity = MAX_ARRAY_SIZE;
            maxCapacity = true;
            return false;
        } else {
            capacity = (int) newCapacity;
            array = Arrays.copyOf(array, capacity);
            return true;
        }
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element element to be appended to this list
     * @return true (as specified by {@link Collection#add})
     */
    public boolean add(E element) {

        if (isEnoughCapacity()) {
            array[size++] = element;
            return true;
        } else if (growCapacity()) {
            array[size++] = element;
            return true;
        } else {
            return false;
        }

    }

    /**
     * Inserts the specified element at the specified position in this
     * list.
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public void add(int index, E element) { //TODO void return изначально

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (isEnoughCapacity()) {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = element;
            size++;
        } else if (growCapacity()) {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = element;
            size++;
        }
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the original order. The behavior of this operation is
     * undefined if the specified collection is modified while the operation
     * is in progress. (This implies that the behavior of this call is
     * undefined if the specified collection is this list, and this
     * list is nonempty)
     *
     * @param collection collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    public boolean addAll(Collection<? extends E> collection) {
        Object[] elements = collection.toArray();
        int sizePlus = elements.length;

        if ((size + sizePlus) <= capacity) {
            System.arraycopy(elements, 0, array, size, sizePlus);
            size += sizePlus;
            return true;

        } else {
            long newCapacity = (long) size + sizePlus;

            if (newCapacity <= MAX_ARRAY_SIZE) {
                capacity = (int) newCapacity;
                array = Arrays.copyOf(array, capacity);

                System.arraycopy(elements, 0, array, size, sizePlus);
                size += sizePlus;
                return true;
            } else {
                return false;
            }
        }

    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        return (E) array[index];
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E remove(int index) {

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        E element = (E) array[index];
        fastRemove(index);

        return element;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If the list does not contain the element, it is
     * unchanged.
     *
     * @param element element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    public boolean remove(Object element) {

        if (element == null) {
            for (int index = 0; index < size; index++) {
                if (array[index] == null) {
                    fastRemove(index);
                    return true;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (element.equals(array[index])) {
                    fastRemove(index);
                    return true;
                }
            }
        }

        return false;
    }

    private void fastRemove(int index) {
        int rightPart = size - index - 1;

        if (rightPart > 0) {
            System.arraycopy(array, index + 1, array, index, rightPart);
        }

        array[--size] = null;
    }

    /**
     * Trims the capacity of this KrawezArrayList to the
     * list's current size. An application can use this operation to minimize
     * the storage of an KrawezArrayList.
     */
    public void trimToSize() {

        if (size <= 12) {
            capacity = DEFAULT_CAPACITY;
        } else {
            capacity = size;
        }

        Object[] newArray = new Object[capacity];

        if (size < array.length) {
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    /**
     * Removes all of the elements from this list. The list will
     * be empty after this call returns.
     */
    public void clear() {

        for (int i = 0; i < size; i++) {
            array[i] = null;
        }

        size = 0;
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {

        if (size == 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *
     * @return index of the occurrence or -1
     */
    public int indexOf(Object element) {

        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return i;
                }
            }

        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(array[i])) {
                    return i;
                }
            }

        }

        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *
     * @return last index of the occurrence or -1
     */
    public int lastIndexOf(Object element) {

        if (element == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }

        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (element.equals(array[i])) {
                    return i;
                }
            }

        }

        return -1;
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param element element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    public boolean contains(Object element) {

        if (indexOf(element) > -1) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Sort list elements by Comparator.
     *
     * @param comparator the {@code Comparator} used to compare list elements.
     *                   A {@code null} value indicates that the elements'
     *                   {@linkplain Comparable natural ordering} should be used
     */
    @Override
    public void sort(Comparator<? super E> comparator) {
        Arrays.sort((E[]) array, 0, size, comparator);
    }

}
