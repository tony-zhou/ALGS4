package week2;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        private Item value;
        private Node next;
        private Node previous;
    }

    public Deque() {}

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldFirst = first;
        first = new Node();
        first.value = item;
        first.next = oldFirst;
        if (oldFirst == null)
            last = first;
        else
            oldFirst.previous = first;
        size += 1;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node();
        last.value = item;
        last.previous = oldLast;
        if (oldLast != null)
            oldLast.next = last;
        else
            first = last;
        size += 1;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.value;
        first = first.next;
        if (first != null)
            first.previous = null;
        else
            last = null;
        size -= 1;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.value;
        last = last.previous;
        if (last == null)
            first = null;
        else
            last.next = null;
        size -= 1;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (current == null)
                    throw new NoSuchElementException();
                Item item = current.value;
                current = current.next;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addLast("hello");
        StdOut.println(deque.removeFirst());
        deque.addFirst("hello");
        deque.addLast("world");
        deque.addFirst("1900");
        for (String s: deque)
            StdOut.println(s);
    }
}
