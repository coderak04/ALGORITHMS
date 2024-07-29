import java.util.*;

public class convexHull {
    static int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) return 0; // colinear
        return (val > 0) ? 1 : 2; // clock or counterclockwise
    }

    static void convexHull(Point points[], int n) {
        if (n < 3) return;

        Vector<Point> hull = new Vector<Point>();

        int l = 0;
        for (int i = 1; i < n; i++)
            if (points[i].x < points[l].x)
                l = i;

        int p = l, q;
        do {
            hull.add(points[p]);
            q = (p + 1) % n;
            for (int i = 0; i < n; i++) {
                if (orientation(points[p], points[i], points[q]) == 2)
                    q = i;
            }
            p = q;
        } while (p != l);

        for (int i = 0; i < hull.size(); i++)
            System.out.println("(" + hull.get(i).x + ", " + hull.get(i).y + ")");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of points in the polygon: ");
        int n = scanner.nextInt();

        Point[] points = new Point[n];
        System.out.println("Enter the coordinates of each point (x y):");
        for (int i = 0; i < n; i++) {
            System.out.print("Enter coordinates of point " + (i + 1) + ": ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points[i] = new Point(x, y);
        }

        convexHull(points, n);
    }
}

class Point {
    int x, y;
    Point() {
        x = 0;
        y = 0;
    }
    Point(int a, int b) {
        x = a;
        y = b;
    }
}
