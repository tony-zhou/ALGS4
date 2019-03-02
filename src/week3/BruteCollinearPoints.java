package week3;


import edu.princeton.cs.algs4.Insertion;

public class BruteCollinearPoints {

    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();
        else {
            for (Point point: points) {
                if (point == null)
                    throw new IllegalArgumentException();
            }
        }
        Insertion.sort(points);
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i-1]) == 0)
                throw new IllegalArgumentException();
        }
    }

//    public int numberOfSegments() {}
//
//    public LineSegment[] segments() {}

    public static void main(String[] args) {
        Point[] points = {new Point(1,1), new Point(2,2), new Point(1,3)};
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
    }
}
