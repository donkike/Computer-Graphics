
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Enrique Arango Lyons
 */
public class DrawPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        Color[] colors = {Color.red, Color.green};
        int currColor = 0;
        
        Point[] points = Main.points;

        g.setColor(Color.yellow);
        drawPoints(points, g);
        System.out.println("\nOriginals:");
        Util.printArray(points);

        for (int i = 0; i < Main.transformations.length; i++) {
            g.setColor(colors[currColor]);
            currColor = (currColor + 1) % colors.length;
            double val1 = Double.parseDouble(Main.transformations[i][1]), val2;
            ObjectTransformer ot = null;
            double[][] transformationMatrix = null;
            boolean moveToCentroid = false;
            if (Main.transformations[i][0].toUpperCase().equals("R")) {
                ot = new RotationTransformer();
                val1 = Math.toRadians(val1);
            } else if (Main.transformations[i][0].toUpperCase().equals("T")) {
                ot = new TranslationTransformer();
            } else if (Main.transformations[i][0].toUpperCase().equals("S")) {
                ot = new ScaleTransformer();
                moveToCentroid = true;
            }

            if (Main.transformations[i].length > 2) {
                val2 = Double.parseDouble(Main.transformations[i][2]);
                transformationMatrix = ot.apply(val1, val2);
            } else {
                transformationMatrix = ot.apply(val1);
            }
            
            if (moveToCentroid) {
                Point centroid = Point.getCentroid(points);
                Point[] centeredPoints = centroid.translateHere(points);
                centeredPoints = Util.applyTransformation(centeredPoints, transformationMatrix);
                points = centroid.translateAway(centeredPoints);
            } else {
                points = Util.applyTransformation(points, transformationMatrix);
            }
            
            System.out.println("\nTransformation " + (i + 1) + ":");
            Util.printArray(points);
            drawPoints(points, g);
        }
    }

    private void drawPoints(Point[] points, Graphics g) {
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (Main.lines[i][j]) {
                    drawLine(points[i], points[j], g);
                }
            }
        }
    }

    private void drawLine(Point p1, Point p2, Graphics g) {
        g.drawLine((int)p1.x + getWidth() / 2, getHeight() / 2 - (int)p1.y,(int)p2.x + getWidth() / 2, getHeight() / 2 - (int)p2.y);
    }

}
