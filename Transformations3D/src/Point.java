
/**
 *
 * @author Enrique Arango Lyons
 */
public class Point {

    public final double x;
    public final double y;
    public final double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point(double[][] matrix) {
        double f = matrix[matrix.length - 1][0];
        x = matrix[0][0] / f;
        y = matrix[1][0] / f;
        z = matrix[2][0] / f;
    }

    public double[][] toMatrix() {
        double[][] matrix = {{x}, {y}, {z}, {1}};
        return matrix;
    }

    public Point add(Point p) {
        return new Point(x + p.x, y + p.y, z + p.z);
    }

    public Point substract(Point p) {
        return new Point(x - p.x, y - p.y, z - p.z);
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
        double sumX = 0, sumY = 0, sumZ = 0;
        for (int i = 0; i < points.length; i++) {
            sumX += points[i].x;
            sumY += points[i].y;
            sumZ += points[i].z;
        }
        Point p = new Point(sumX / points.length, sumY / points.length, sumZ / points.length);
        return p;
    }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ", " + z + ")";
    }


}
