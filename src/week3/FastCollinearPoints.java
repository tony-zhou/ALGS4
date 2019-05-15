package week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        List<Point> pointList = new ArrayList<>(Arrays.asList(points));
        Collections.sort(pointList);
        for (int i = 1; i < pointList.size(); i++) {
            if (pointList.get(i).compareTo(pointList.get(i - 1)) == 0)
                throw new IllegalArgumentException();
        }
        temp = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            pointList.sort(points[i].slopeOrder());
            int j = 1;
            int k = 2;
            List<Point> tmp = new ArrayList<>();
            tmp.add(pointList.get(0));
            while (k < pointList.size()) {
                if (Double.compare(pointList.get(0).slopeTo(pointList.get(j)), pointList.get(0).slopeTo(pointList.get(k))) == 0) {
                    tmp.add(pointList.get(j));
                    tmp.add(pointList.get(k));
                    k++;
                } else {
                    j = k;
                    k = j + 1;
                    if (tmp.size() >= 4) {
                        Point start = pointList.get(0);
                        Point end = pointList.get(0);
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
                        if (!duplicateLineSegment) {
                            temp.add(line);
                        }
                    }
                    tmp.clear();
                    tmp.add(pointList.get(0));
                }
            }
            if (tmp.size() >= 4) {
                Point start = pointList.get(0);
                Point end = pointList.get(0);
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