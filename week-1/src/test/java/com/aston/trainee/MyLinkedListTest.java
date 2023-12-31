package com.aston.trainee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MyLinkedListTest {
    private MyLinkedList<Integer> linkedList;

    @BeforeEach
    void beforeEach() {
        linkedList = new MyLinkedList<>();
    }

    @Test
    void toArray() {
        linkedList.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));

        Assertions.assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, linkedList.toArray());
    }

    @Test
    void get() {
        linkedList.add(1);

        Assertions.assertEquals(1, linkedList.get(0));
    }

    @Test
    void getIndexOutOfBounds() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(Integer.MAX_VALUE));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(Integer.MIN_VALUE));
    }

    @Test
    void add() {
        linkedList.add(1);

        Assertions.assertArrayEquals(new Integer[]{1}, linkedList.toArray());
    }

    @Test
    void addAtPosition() {
        linkedList.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        linkedList.add(0, 0);

        Assertions.assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, linkedList.toArray());
    }

    @Test
    void remove() {
        linkedList.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        linkedList.remove(1);

        Assertions.assertArrayEquals(new Integer[]{1, 3, 4, 5, 6, 7, 8, 9, 10, 11}, linkedList.toArray());
    }

    @Test
    void clear() {
        linkedList.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        linkedList.clear();

        Assertions.assertArrayEquals(new Integer[]{}, linkedList.toArray());
    }

    @Test
    void addAll() {
        linkedList.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));

        Assertions.assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, linkedList.toArray());
    }

    @Test
    void sort() {
        linkedList.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        linkedList.sort((a, b) -> b - a);

        Assertions.assertArrayEquals(new Integer[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, linkedList.toArray());
    }
}
