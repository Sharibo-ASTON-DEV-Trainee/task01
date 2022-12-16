package com.gmail.alexejkrawez;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        /* QuickSort */
        Integer[] integers1 = { 2, null, 4, -1, 1, 3, 3, 6, 5, 12, null, 0, -1, 13 };
        Integer[] integers2 = { 1, 2, 3,   6, 8, 4, 7, 5, 10, 9, 11,   12, 13, null };
        String[] strings1 = { null, "13", "12",   "6", "8", "4", "7", "5", "10", "9", "11",   "3", "2", "1" };
        List<Integer> listForSorting1 = Arrays.asList(integers1);
        List<Integer> listForSorting2 = Arrays.asList(integers2);
        List<String>  listForSorting3 = Arrays.asList(strings1);
        List<Integer> emptyListForSorting = new ArrayList<>();

        // 1
        System.out.println("Sorting:");
        System.out.println("list1:" + listForSorting1);

//        QuickSort.sort(listForSorting1, -5, listForSorting1.size());  // IllegalArgumentException
//        QuickSort.sort(listForSorting1, 10, 2);                       // IllegalArgumentException
//        QuickSort.sort(emptyListForSorting);                          // NullPointerException
        QuickSort.sort(listForSorting1);

        System.out.println("list1:" + listForSorting1);

        // 2
        System.out.println("Sorting by range:");
        System.out.println("list2:" + listForSorting2);

        QuickSort.sort(listForSorting2, 3, listForSorting2.size() - 4);

        System.out.println("list2:" + listForSorting2);

        // 3
        System.out.println("Sorting by strings:");
        System.out.println("list3:" + listForSorting3);

        QuickSort.sort(listForSorting3, 3, listForSorting3.size() - 4, Collections.reverseOrder());

        System.out.println("list3:" + listForSorting3);

        System.out.println("\n\n");




        /* KrawezArrayList */
        KrawezArrayList<Integer> initialCapacityList = new KrawezArrayList<>(100);
        System.out.println("size by initialCapacityList: " + initialCapacityList.size()); // 0


        ArrayList<String> strings = new ArrayList<>();
        strings.add("arrayList 1");
        strings.add("arrayList 2");
        strings.add("arrayList 3");
        strings.add(null);

        KrawezArrayList<String> listWithCollection = new KrawezArrayList<>(strings);
        System.out.println("listWithCollection: " + listWithCollection);


        KrawezArrayList<String> list = new KrawezArrayList<>();

        list.add(null);
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add(null);
        list.add("10");
        list.add("11");
        list.add("12");
        list.add("13");   // grow capacity from 12 to 18 (default = 12)

        System.out.println("list: " + list);
        System.out.println();

        String index10 = list.get(10);
//        System.out.println(list.get(16));     // IndexOutOfBoundsException
        System.out.println("index №10: " + index10 + " " + index10.getClass());
        System.out.println("index of null: "             + list.indexOf(null));
        System.out.println("last index of null: "        + list.lastIndexOf(null));
        System.out.println("contains \"7\": "            + list.contains("7"));
        System.out.println();

        System.out.println("remove by index: "        + list.remove(12));
        System.out.println("remove by element: "      + list.remove("12"));
        System.out.println("remove by null-element: " + list.remove(null));

        list.clear();
        System.out.println("after clear is empty: " + list.isEmpty());

        list.trimToSize();
        System.out.println("size after clear(): " + list.size());
        System.out.println();

        list.addAll(strings);
        System.out.println("size after addAll(arrayListCollection): " + list.size());

        list.add(0, "new first element");
        System.out.println("add at position 0: " + list.get(0) + " , size: " + list.size());
        System.out.println();

    }
}