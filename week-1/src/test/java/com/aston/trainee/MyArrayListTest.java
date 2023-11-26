package com.aston.trainee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MyArrayListTest {
    private MyArrayList<Integer> arrayList;

    @BeforeEach
    void beforeEach() {
        arrayList = new MyArrayList<>();
    }

    @Test
    void toArray() {
        arrayList.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));

        Assertions.assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, arrayList.toArray());
    }

    @Test
    void get() {
        arrayList.add(1);

        Assertions.assertEquals(1, arrayList.get(0));
    }

    @Test
    void getIndexOutOfBounds() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(Integer.MAX_VALUE));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(Integer.MIN_VALUE));
    }

    @Test
    void add() {
        arrayList.add(1);

        Assertions.assertArrayEquals(new Integer[]{1}, arrayList.toArray());
    }

    @Test
    void addAtPosition() {
        arrayList.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        arrayList.add(0, 0);

        Assertions.assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, arrayList.toArray());
    }

    @Test
    void remove() {
        arrayList.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        arrayList.remove(1);

        Assertions.assertArrayEquals(new Integer[]{1, 3, 4, 5, 6, 7, 8, 9, 10, 11}, arrayList.toArray());
    }

    @Test
    void clear() {
        arrayList.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        arrayList.clear();

        Assertions.assertArrayEquals(new Integer[]{}, arrayList.toArray());
    }

    @Test
    void addAll() {
        arrayList.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));

        Assertions.assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, arrayList.toArray());
    }

    @Test
    void sort() {
        arrayList.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        arrayList.sort((a, b) -> b - a);

        Assertions.assertArrayEquals(new Integer[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, arrayList.toArray());
    }
}
