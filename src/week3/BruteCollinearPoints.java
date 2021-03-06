package week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class BruteCollinearPoints {

    private final List<LineSegment> temp;
    private final int number;

    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();
        else {
            for (Point point : points) {
                if (point == null)
                    throw new IllegalArgumentException();
            }
        }
        Point[] pointsCopy = new Point[points.length];
        System.arraycopy(points, 0, pointsCopy, 0, points.length);
        Insertion.sort(pointsCopy);
        for (int i = 1; i < pointsCopy.length; i++) {
            if (pointsCopy[i].compareTo(pointsCopy[i - 1]) == 0)
                throw new IllegalArgumentException();
        }
        temp = new ArrayList<>();
        for (int firstPoint = 0; firstPoint < points.length - 3; firstPoint++) {
            for (int secondPoint = firstPoint + 1; secondPoint < points.length - 2; secondPoint++) {
                double slopeToIJ = pointsCopy[firstPoint].slopeTo(pointsCopy[secondPoint]);
                for (int thirdPoint = secondPoint + 1; thirdPoint < points.length - 1; thirdPoint++) {
                    if (Double.compare(slopeToIJ, pointsCopy[firstPoint].slopeTo(pointsCopy[thirdPoint])) != 0)
                        continue;
                    for (int forthPoint = thirdPoint + 1; forthPoint < points.length; forthPoint++) {
                        if (Double.compare(slopeToIJ, pointsCopy[firstPoint].slopeTo(pointsCopy[forthPoint])) == 0)
                            temp.add(new LineSegment(pointsCopy[firstPoint], pointsCopy[forthPoint]));
                    }
                }
            }
        }
        number = temp.size();
    }

    public int numberOfSegments() {
        return number;
    }

    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[number];
        for (int i = 0; i < lineSegments.length; i++) {
            lineSegments[i] = temp.get(i);
        }
        return lineSegments;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
