package week2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size = 0;
    private int index = 0;
    private Item[] queue;

    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
    }

    @SuppressWarnings("unchecked")
    private void resize(int len) {
        Item[] tmpQueue = (Item[]) new Object[len];
        int index = 0;
        for (int i = 0; i < queue.length; i++) {
            if (queue[i] != null) {
                tmpQueue[index] = queue[i];
                index++;
            }
        }
        queue = tmpQueue;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (queue.length <= index) {
            resize(2 * size);
        }
        queue[index++] = item;
        size++;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = null;
        while (item == null) {
            int position = StdRandom.uniform(0, index);
            item = queue[position];
            queue[position] = null;
        }
        size--;
        if (size < queue.length / 4) {
            resize(2 * size);
        }
        return item;
    }

    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item;
        int position;
        do {
            position = StdRandom.uniform(0, index);
            item = queue[position];
        } while (item == null);
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private Item[] tmp = queue.clone();
            private int count = size;

            @Override
            public boolean hasNext() {
                return count != 0;
            }

            @Override
            public Item next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                Item item = null;
                while (item == null) {
                    int position = StdRandom.uniform(0, index);
                    item = tmp[position];
                    tmp[position] = null;
                }
                count--;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        RandomizedQueue<Character> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue('A');
        randomizedQueue.enqueue('B');
        randomizedQueue.enqueue('C');
//        StdOut.println(randomizedQueue.sample());
//        StdOut.println(randomizedQueue.dequeue());
//        StdOut.println(randomizedQueue.sample());
//        StdOut.println(randomizedQueue.sample());
        for (char item: randomizedQueue)
            StdOut.println(item);
        for (char item: randomizedQueue)
            StdOut.println(item);
    }

}
