package com.gmail.alexejkrawez;

import java.util.*;

/**
 * Quicksort implementation that takes n*log(n) time on average. Like all
 * proper quick sorts, this sort is stable and runs O(n*n) times in the
 * worst case. This implementation also supports null values and handles
 * them correctly.
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
public class QuickSort {

    /**
     * Sorts the specified range of the specified list of objects
     * according to the order specified by the objects compareTo
     * method. The range to be sorted extends from index fromIndex,
     * inclusive, to index toIndex, exclusive. All elements in the
     * range must be mutually comparable by the specified comparator
     * (that is, c.compare(e1, e2) must not throw a ClassCastException
     * for any elements e1 and e2 in the range).
     *
     * @param list the list to be sorted
     *
     * @throws NullPointerException if the specified list is null
     */
    public static <E> void sort(List<E> list) {

        if (list.isEmpty()) {
            throw new NullPointerException("List is empty");
        } else {
            E[] array = (E[]) list.toArray();
            qSort(array, 0, array.length - 1);

            ListIterator<E> iterator = list.listIterator();
            int i = 0;
            while (iterator.hasNext()) {
                iterator.next();
                iterator.set(array[i++]);
            }

        }
    }

    /**
     * Sorts the specified range of the specified list of objects
     * according to the order specified by the objects compareTo
     * method. The range to be sorted extends from index fromIndex,
     * inclusive, to index toIndex, exclusive. All elements in the
     * range must be mutually comparable by the specified comparator
     * (that is, c.compare(e1, e2) must not throw a ClassCastException
     * for any elements e1 and e2 in the range).
     *
     * @param list the list to be sorted
     * @param fromIndex the index of the first element (inclusive) to be sorted
     * @param toIndex the index of the last element (exclusive) to be sorted
     *
     * @throws ClassCastException if the array contains elements that are not
     *         mutually comparable using the specified comparator.
     * @throws NullPointerException if the specified list is null
     */
    public static <E> void sort(List<E> list, int fromIndex, int toIndex) {

        if (list.isEmpty()) {
            throw new NullPointerException("List is empty");
        } else if (fromIndex < 0 || toIndex >= list.size() || fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex: " + fromIndex + ", toIndex: " + toIndex);
        } else {
            E[] array = (E[]) list.toArray();
            qSort(array, fromIndex, toIndex);

            replaceListElements(list, array, fromIndex);
        }

    }

    /**
     * Sorts the specified range of the specified list of objects
     * according to the order induced by the specified comparator.
     * The range to be sorted extends from index fromIndex, inclusive,
     * to index toIndex, exclusive.  All elements in the range must
     * be mutually comparable by the specified comparator (that is,
     * c.compare(e1, e2) must not throw a ClassCastException for any
     * elements e1 and e2 in the range).
     *
     * @param list the list to be sorted
     * @param fromIndex the index of the first element (inclusive) to be sorted
     * @param toIndex the index of the last element (exclusive) to be sorted
     * @param comparator the comparator to determine the order of the array.
     *                   A null value indicates that the elements' natural
     *                   ordering should be used.
     *
     * @throws ClassCastException if the array contains elements that are not
     *         mutually comparable using the specified comparator.
     * @throws IllegalArgumentException if {@code fromIndex < 0},
     *         {@code toIndex >= list.size()} or {@code fromIndex > toIndex} or
     *         (optional) if the comparator is found to violate the
     *         {@link Comparator} contract
     * @throws NullPointerException if the specified list is null
     */
    public static <E> void sort(List<E> list, int fromIndex, int toIndex,
                                Comparator<? super E> comparator) {

        if (list.isEmpty()) {
            throw new NullPointerException("List is empty");
        } else if (fromIndex < 0 || toIndex >= list.size()  || fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex: " + fromIndex + ", toIndex: " + toIndex);
        } else {
            E[] array = (E[]) list.toArray();
            qSort(array, fromIndex, toIndex, comparator);

            replaceListElements(list, array, fromIndex);
        }

    }

    private static <E> void replaceListElements(List<E> list, E[] array, int fromIndex) {

        ListIterator<E> iterator = list.listIterator();
        int i = 0;
        while (iterator.hasNext()) {
            if (iterator.nextIndex() < fromIndex) {
                iterator.next();
                i++;
            } else {
                iterator.next();
                iterator.set(array[i++]);
            }
        }
    }



    private static <E> void qSort(E[] array, int low, int high) {

        if (low >= high) {
            return;
        }

        int middle = low + (high - low) / 2;

        Comparable pivot = (Comparable) array[middle];


        int f = low, l = high;

        while (f <= l) {

            while ( array[f] != null & pivot == null ||
                    array[f] != null & pivot != null && pivot.compareTo(array[f]) > 0) {

                f++;
            }

            while ( array[l] == null & pivot != null ||
                    array[f] != null & pivot != null && pivot.compareTo(array[l]) < 0) {

                l--;
            }

            if (f <= l) {
                E temp = array[f];
                array[f] = array[l];
                array[l] = temp;
                f++;
                l--;
            }

            if (low < l) {
                qSort(array, low, l);
            }

            if (high > f) {
                qSort(array, f, high);
            }

        }
    }

    private static <E> void qSort(E[] array, int low, int high, Comparator<? super E> comparator) {

        if (low >= high) {
            return;
        }

        int middle = low + (high - low) / 2;
        E pivot = array[middle];


        int f = low, l = high;

        while (f <= l) {

            while (array[f] == null & pivot != null ||
                    array[f] != null & pivot != null && comparator.compare(array[f], pivot) < 0) {

                f++;
            }

            while (array[l] != null & pivot == null ||
                    array[f] != null & pivot != null && comparator.compare(array[l], pivot) > 0) {

                l--;
            }

            if (f <= l) {
                E temp = array[f];
                array[f] = array[l];
                array[l] = temp;
                f++;
                l--;
            }

            if (low < l) {
                qSort(array, low, l, comparator);
            }

            if (high > f) {
                qSort(array, f, high, comparator);
            }
        }

    }




}



