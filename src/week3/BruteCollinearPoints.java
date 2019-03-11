package week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class BruteCollinearPoints {

    private final Point[] points;

    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();
        else {
            for (Point point : points) {
                if (point == null)
                    throw new IllegalArgumentException();
            }
        }
        Insertion.sort(points);
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i - 1]) == 0)
                throw new IllegalArgumentException();
        }
        this.points = new Point[points.length];
        System.arraycopy(points, 0, this.points, 0, points.length);
    }

    public int numberOfSegments() {
        return segments().length;
    }

    public LineSegment[] segments() {
        List<LineSegment> temp = new ArrayList<>();
        for (int firstPoint = 0; firstPoint < points.length - 3; firstPoint++) {
            for (int secondPoint = firstPoint + 1; secondPoint < points.length - 2; secondPoint++) {
                double slopeToIJ = points[firstPoint].slopeTo(points[secondPoint]);
                for (int thirdPoint = secondPoint + 1; thirdPoint < points.length - 1; thirdPoint++) {
                    if (Double.compare(slopeToIJ, points[firstPoint].slopeTo(points[thirdPoint])) != 0)
                        continue;
                    for (int forthPoint = thirdPoint + 1; forthPoint < points.length; forthPoint++) {
                        if (Double.compare(slopeToIJ, points[firstPoint].slopeTo(points[forthPoint])) == 0)
                            temp.add(new LineSegment(points[firstPoint], points[forthPoint]));
                    }
                }
            }
        }
        LineSegment[] lineSegments = new LineSegment[temp.size()];
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
