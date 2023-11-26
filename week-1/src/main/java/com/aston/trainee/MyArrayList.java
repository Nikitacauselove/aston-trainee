package com.aston.trainee;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class MyArrayList<E> {
    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The array buffer into which the elements of the MyArrayList are stored.
     */
    private Object[] elementData = new Object[DEFAULT_CAPACITY];

    /**
     * The number of elements MyArrayList contains.
     */
    private int size = 0;

    /**
     * Increases the capacity to ensure that it can hold at least the
     * number of elements specified by the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     */
    private Object[] grow(int minCapacity) {
        int newCapacity = Math.max(minCapacity, size) + size % 2;
        Object[] array = new Object[newCapacity];

        System.arraycopy(elementData, 0, array, 0, size);
        return array;
    }

    /**
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     *
     * @return an array containing all of the elements in this list in
     *         proper sequence
     */
    public Object[] toArray() {
        Object[] array = new Object[size];

        System.arraycopy(elementData, 0, array, 0, size);
        return array;
    }

    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param  index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index >= size()})
     */
    public E get(int index) {
        Objects.checkIndex(index, elementData.length);

        return elementData(index);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element element to be appended to this list*/
    public void add(E element) {
        add(size, element);
    }

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index >= size()})
     */
    public void add(int index, E element) {
        Objects.checkIndex(index, elementData.length);

        if (size == elementData.length) {
            elementData = grow(size);
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to be removed
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index >= size()})
     */
    public void remove(int index) {
        Objects.checkIndex(index, elementData.length);

        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
    }

    /**
     * Removes all of the elements from this list.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the
     * specified collection's Iterator.
     *
     * @param collection collection containing elements to be added to this list
     * @throws NullPointerException if the specified collection is null
     */
    public void addAll(Collection<? extends E> collection) {
        Object[] array = collection.toArray();

        if (elementData.length < size + array.length) {
            elementData = grow(size + array.length);
        }
        for (int i = 0; i < collection.size(); i++) {
            elementData[size++] = array[i];
        }
    }

    /**
     * Sorts this list according to the order induced by the specified
     * {@link Comparator}.
     *
     * @param comparator the {@code Comparator} used to compare list elements.
     *          A {@code null} value indicates that the elements'
     *          {@linkplain Comparable natural ordering} should be used
     * @throws ClassCastException if the list contains elements that are not
     *         <i>mutually comparable</i> using the specified comparator
     * @throws IllegalArgumentException
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     *         if the comparator is found to violate the {@link Comparator}
     *         contract
     */
    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super E> comparator) {
        Arrays.sort((E[]) elementData, 0, size, comparator);
    }
}
