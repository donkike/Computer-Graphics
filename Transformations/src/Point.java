
/**
 *
 * @author Enrique Arango Lyons
 */
public class Point {

    public final double x;
    public final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(double[][] matrix) {
        x = matrix[0][0];
        y = matrix[1][0];
    }

    public double[][] toMatrix() {
        double[][] matrix = {{x}, {y}, {1}};
        return matrix;
    }

    public Point add(Point p) {
        return new Point(x + p.x, y + p.y);
    }

    public Point substract(Point p) {
        return new Point(x - p.x, y - p.y);
    }

     public Point[] translateHere(Point[] points) {
        Point[] centeredPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            centeredPoints[i] = points[i].substract(this);
        }
        return centeredPoints;
    }

    public Point[] translateAway(Point[] points) {
        Point[] restoredPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            restoredPoints[i] = points[i].add(this);
        }
        return restoredPoints;
    }

    public static Point getCentroid(Point[] points) {
        double sumX = 0, sumY = 0;
        for (int i = 0; i < points.length; i++) {
            sumX += points[i].x;
            sumY += points[i].y;
        }
        Point p = new Point(sumX / points.length, sumY / points.length);
        return p;
    }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }


}
