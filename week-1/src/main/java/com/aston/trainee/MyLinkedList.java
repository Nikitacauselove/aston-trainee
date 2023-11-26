package com.aston.trainee;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class MyLinkedList<E> {
    private int size = 0;

    /**
     * Pointer to first node.
     */
    private Node<E> head;

    /**
     * Pointer to last node.
     */
    private Node<E> tail;

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element the element to add
     */
    private void addLast(E element) {
        Node<E> node = new Node<>(element, null, tail);

        if (tail == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element element to be appended to this list
     */
    public void add(E element) {
        addLast(element);
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the specified
     * collection's iterator.
     *
     * @param collection collection containing elements to be added to this list
     * @throws NullPointerException if the specified collection is null
     */
    @SuppressWarnings("unchecked")
    public void addAll(Collection<? extends E> collection) {
        Object[] array = collection.toArray();

        for (Object object : array) {
            addLast((E) object);
        }
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    public void clear() {
        for (Node<E> current = head; current != null; current = current.next) {
            current.element = null;
            current.next = null;
            current.prev = null;
        }
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index >= size})
     */
    public E get(int index) {
        Objects.checkIndex(index, size);

        return node(index).element;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any
     * subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index >= size})
     */
    public void add(int index, E element) {
        Objects.checkIndex(index, size);

        if (index == size) {
            addLast(element);
        } else {
            Node<E> current = node(index);
            Node<E> newNode = new Node<>(element, current, current.prev);

            if (current.prev == null) {
                head = newNode;
            } else {
                current.prev.next = newNode;
            }
            current.prev = newNode;
            size++;
        }
    }

    /**
     * Removes the element at the specified position in this list.  Shifts any
     * subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index >= size})
     */
    public void remove(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = node(index);
        Node<E> next = node.next;
        Node<E> prev = node.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            node.prev = null;
        }
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }
        node.element = null;
        size--;
    }

    /**
     * Returns the (non-null) Node at the specified element index.
     */
    private Node<E> node(int index) {
        Node<E> node;

        if (index < size / 2) {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(E element, Node<E> next, Node<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     *
     * @return an array containing all of the elements in this list
     *         in proper sequence
     */
    public Object[] toArray() {
        Object[] array = new Object[size];

        int i = 0;
        for (Node<E> current = head; current != null; current = current.next) {
            array[i++] = current.element;
        }
        return array;
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
        E[] array = (E[]) toArray();
        clear();
        Arrays.sort(array, comparator);

        for (E element : array) {
            addLast(element);
        }
    }
}
