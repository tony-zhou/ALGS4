package week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args) {
        int len = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        for (int i = 0; i < len; i++) {
            randomizedQueue.enqueue(StdIn.readString());
        }
        for (int j = 0; j < len; j++) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
}
