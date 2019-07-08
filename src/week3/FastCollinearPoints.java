package week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Quick3way;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private final List<LineSegment> temp;
    private final int number;

    public FastCollinearPoints(Point[] points) {

        if (points == null)
            throw new IllegalArgumentException();
        else {
            for (Point point : points) {
                if (point == null)
                    throw new IllegalArgumentException();
            }
        }
        Point[] pointsBase = new Point[points.length];
        System.arraycopy(points, 0, pointsBase, 0, points.length);
        List<Point> pointList = new ArrayList<>(Arrays.asList(points));
        Quick3way.sort(pointsBase);
        for (int i = 1; i < pointsBase.length; i++) {
            if (pointsBase[i].compareTo(pointsBase[i - 1]) == 0)
                throw new IllegalArgumentException();
        }
        temp = new ArrayList<>();
        List<Point> tmp = new ArrayList<>();
        Point start, end;
        for (int i = 0; i < pointsBase.length; i++) {
            pointList.sort(pointsBase[i].slopeOrder());
            int j = 1;
            int k = 2;
            start = end = pointList.get(0);
            tmp.add(start);
            while (k < pointList.size()) {
                if (Double.compare(start.slopeTo(pointList.get(j)), start.slopeTo(pointList.get(k))) == 0) {
                    k++;
                } else {
                    if (k - j > 2) {
                        break;
                    } else {
                        k++;
                        j++;
                    }
                }
            }
            if (k - j > 2) {
                for (int index = j; index < k; index++)
                    tmp.add(pointList.get(index));

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

            }
            tmp.clear();

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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        StdOut.print(collinear.numberOfSegments());
    }
}