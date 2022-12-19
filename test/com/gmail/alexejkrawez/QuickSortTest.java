package com.gmail.alexejkrawez;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class QuickSortTest {

    @Test
    void sortIntegers() {
        Integer[] integers1 = { 2, null, 4, -1, 1, 3, 3, 6, 5, 12, null, 0, -1, 13 };
        List<Integer> listForSorting1 = Arrays.asList(integers1);
        Integer[] match1    = { -1, -1, 0, 1, 2, 3, 3, 4, 5, 6, 12, 13, null, null };
        List<Integer> listForMatching1 = Arrays.asList(match1);

        Integer[] integers2 = { 1, 2, 3,   6, 8, 4, 7, 5, 10, 9, 11,   12, 13, null };
        List<Integer> listForSorting2 = Arrays.asList(integers2);
        Integer[] match2    = { 1, 2, 3,   4, 5, 6, 7, 8, 9, 10, 11,   12, 13, null };
        List<Integer> listForMatching2 = Arrays.asList(match2);

        QuickSort.sort(listForSorting1);
        QuickSort.sort(listForSorting2);


        int i = 0;
        for (Integer element : listForSorting1) {
            Assertions.assertEquals(listForMatching1.get(i), element);
            i++;
        }

        int j = 0;
        for (Integer element : listForSorting2) {
            Assertions.assertEquals(listForMatching2.get(j), element);
            j++;
        }
    }

    @Test
    void sortStrings() {
        String[] strings = { null, "13", "12",   "6", "8", "4", "7", "5", "10", "9", "11",   "3", "2", "1" };
        List<String> listForSorting = Arrays.asList(strings);
        String[] match = {null, "13", "12", "9", "8", "7", "6", "5", "4", "11", "10", "3", "2", "1"};
        List<String> listForMatching = Arrays.asList(match);

        QuickSort.sort(listForSorting, 3, listForSorting.size() - 4, Collections.reverseOrder());

        int i = 0;
        for (String element : listForSorting) {
            Assertions.assertEquals(listForMatching.get(i), element);
            i++;
        }
    }


}