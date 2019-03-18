package week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class FastCollinearPoints {

    private final Point[] points;

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();
        else {
            for (Point point : points) {
                if (point == null)
                    throw new IllegalArgumentException();
            }
        }
        this.points = new Point[points.length];
        System.arraycopy(points, 0, this.points, 0, points.length);
        Insertion.sort(this.points);
        for (int i = 1; i < this.points.length; i++) {
            if (this.points[i].compareTo(this.points[i - 1]) == 0)
                throw new IllegalArgumentException();
        }
    }

    public int numberOfSegments() {
        return segments().length;
    }

    public LineSegment[] segments() {
        List<LineSegment> temp = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            Insertion.sort(points, points[i].slopeOrder());
            int j = 1;
            int k = 2;
            List<Point> tmp = new ArrayList<>();
            tmp.add(points[0]);
            while (k < points.length) {
                if (Double.compare(points[0].slopeTo(points[j]), points[0].slopeTo(points[k])) == 0) {
                    tmp.add(points[j]);
                    tmp.add(points[k]);
                    k++;
                } else {
                    j = k;
                    k = j + 1;
                    if (tmp.size() >= 4) {
                        Point start = points[0];
                        Point end = points[0];
                        for (Point p : tmp) {
                            if (p.compareTo(start) < 0)
                                start = p;
                            else if (p.compareTo(end) > 0)
                                end = p;
                        }
                        LineSegment line = new LineSegment(start, end);
                        boolean duplicateLineSegment = false;
                        for (LineSegment lineSegment : temp) {
                            if (lineSegment.toString().compareTo(line.toString()) == 0) {
                                duplicateLineSegment = true;
                                break;
                            }
                        }
                        if (!duplicateLineSegment)
                            temp.add(line);
                    } else if (tmp.size() > 1) {
                        tmp = new ArrayList<>();
                        tmp.add(points[0]);
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
