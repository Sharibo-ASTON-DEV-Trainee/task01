package com.gmail.alexejkrawez;

import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class KrawezArrayListTest {

    private final KrawezArrayList<String> list;

    KrawezArrayListTest() {
        list = new KrawezArrayList<>(Arrays.asList(
                new String[]{ null, "13", "12",  "6", "8", "4", "7", "5", "10", "9", "11", "3", null, "2", "1" }
        ));
    }

    @Test
    void KrawezArrayListConstructorCollectionException(){

        Assertions.assertThrows(NullPointerException.class, () -> {
            new KrawezArrayList<>(null);
                });
    }

    @Test
    void KrawezArrayListConstructorCapacityException(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new KrawezArrayList<>(-3);
        });
    }

    @Test
    void size() {
        Assertions.assertTrue(list.size() == 15);
    }

    @Test
    void addElement() {
        Assertions.assertTrue(list.add(null));
    }

    @Test
    void addIndex() {

        list.add(3, "3");
        Assertions.assertEquals(list.get(3), "3");
    }

    @Test
    void addIndexException() {

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(-3, "3");
        });
    }

    @Test
    void addAll() {

        List<String> listForAdding = new ArrayList<>(Arrays.asList(
                new String[]{ null, "13", "12",  "6", "8", "4", "7", "5", "10", "9", "11", "3", null, "2", "1" }
        ));

        int index = list.size();
        list.addAll(listForAdding);
        Assertions.assertEquals(list.size() - index, listForAdding.size());

        int i = 0;
        for (; index < list.size(); index++) {
            Assertions.assertEquals(list.get(index), listForAdding.get(i));
            i++;
        }

    }

    @Test
    void addAllException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            list.addAll(null);
        });
    }

    @Test
    void get() {
        Assertions.assertEquals(list.get(1), "13");
    }

    @Test
    void getException() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-3);
        });
    }

    @Test
    void removeElement() {
        int size = list.size();
        Assertions.assertTrue(list.remove(null));
        Assertions.assertEquals(size - 1, list.size());

        Assertions.assertFalse(list.remove("666"));
        Assertions.assertEquals(size - 1, list.size());
    }

    @Test
    void removeIndex() {
        Assertions.assertEquals(list.remove(1), "13");
    }

    @Test
    void removeIndexException() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(-3);
        });
    }

    @Test
    void trimToSize() {
        try {
            Field capacity = KrawezArrayList.class.getDeclaredField("capacity");
            capacity.setAccessible(true);

            Assertions.assertTrue(capacity.getInt(list) > 12);
            list.clear();
            list.trimToSize();
            Assertions.assertTrue(capacity.getInt(list) == 12);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void clear() {
        Assertions.assertTrue(list.size() > 0);
        list.clear();
        Assertions.assertTrue(list.size() == 0);
    }

    @Test
    void isEmpty() {
        Assertions.assertFalse(list.isEmpty());
        list.clear();
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    void indexOf() {
        Assertions.assertTrue(list.indexOf(null) == 0);
        Assertions.assertTrue(list.indexOf("666") == -1);
    }

    @Test
    void lastIndexOf() {
        Assertions.assertTrue(list.lastIndexOf(null) == 12);
        Assertions.assertTrue(list.lastIndexOf("666") == -1);
    }

    @Test
    void contains() {
        Assertions.assertTrue(list.contains(null));
        Assertions.assertTrue(list.contains("13"));
        Assertions.assertFalse(list.contains("666"));
    }

    @Test
    void sort() {

        List<String> strings = Arrays.asList(new String[]{"1", "2", "3", "4", "10", "11"});
        List<String> match = Arrays.asList(new String[]{"4", "3", "2", "11", "10", "1"});
        strings.sort(Collections.reverseOrder());

        int index = 0;
        for (String element : strings) {
            Assertions.assertEquals(element, match.get(index++));
        }
    }

    @Test
    void sortException() {

        Assertions.assertThrows(NullPointerException.class, () -> {
                    list.sort(Collections.reverseOrder());
                });
    }

}