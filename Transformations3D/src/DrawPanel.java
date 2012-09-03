
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author Enrique Arango Lyons
 */
public class DrawPanel extends JPanel implements KeyListener {

    private int d;
    private double[][] projectMatrix;
    private Point[] points;
    private boolean[][] lines;

    public DrawPanel(Point[] points, boolean[][] lines, int d) {
        this.points = points;
        this.lines = lines;
        this.d = d;
        this.projectMatrix = new ProjectionTransformer().apply(d);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.green);
        drawPoints(points, g);
    }

    private void drawPoints(Point[] points, Graphics g) {
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (Main.lines[i][j]) {
                    Point point1 = new Point(Util.multiply(projectMatrix, points[i].toMatrix()));
                    Point point2 = new Point(Util.multiply(projectMatrix, points[j].toMatrix()));
                    drawLine(point1, point2, g);
                }
            }
        }
    }

    private void drawLine(Point p1, Point p2, Graphics g) {
        g.drawLine((int)p1.x + getWidth() / 2, getHeight() / 2 - (int)p1.y,(int)p2.x + getWidth() / 2, getHeight() / 2 - (int)p2.y);
    }

    public void keyTyped(KeyEvent ke) { }

    public void keyPressed(KeyEvent ke) {
        ObjectTransformer ot = null;
        double[][] transformationMatrix = null;
        boolean moveToCentroid = false;
        switch(ke.getKeyCode()) {
            case (KeyEvent.VK_UP):
                ot = new TranslationTransformer();
                transformationMatrix = ot.apply(0, 20, 0);
                break;
            case (KeyEvent.VK_DOWN):
                ot = new TranslationTransformer();
                transformationMatrix = ot.apply(0, -20, 0);
                break;
            case (KeyEvent.VK_RIGHT):
                ot = new TranslationTransformer();
                transformationMatrix = ot.apply(20, 0, 0);
                break;
            case (KeyEvent.VK_LEFT):
                ot = new TranslationTransformer();
                transformationMatrix = ot.apply(-20, 0, 0);
                break;
            case (KeyEvent.VK_ENTER):
                ot = new TranslationTransformer();
                transformationMatrix = ot.apply(0, 0, 20);
                break;
            case (KeyEvent.VK_SPACE):
                ot = new TranslationTransformer();
                transformationMatrix = ot.apply(0, 0, -20);
                break;
            case (KeyEvent.VK_PLUS):
                ot = new ScaleTransformer();
                transformationMatrix = ot.apply(1.05);
                moveToCentroid = true;
                break;
            case (KeyEvent.VK_MINUS):
                ot = new ScaleTransformer();
                transformationMatrix = ot.apply(0.95);
                moveToCentroid = true;
                break;
            case (KeyEvent.VK_Q):
                ot = new RotationTransformer();
                transformationMatrix = ot.apply(0, 0, Math.PI / 32);
                moveToCentroid = true;
                break;
            case (KeyEvent.VK_E):
                ot = new RotationTransformer();
                transformationMatrix = ot.apply(0, 0, -Math.PI / 32);
                moveToCentroid = true;
                break;
            case (KeyEvent.VK_A):
                ot = new RotationTransformer();
                transformationMatrix = ot.apply(0, -Math.PI / 32, 0);
                moveToCentroid = true;
                break;
            case (KeyEvent.VK_D):
                ot = new RotationTransformer();
                transformationMatrix = ot.apply(0, Math.PI / 32, 0);
                moveToCentroid = true;
                break;
            case (KeyEvent.VK_W):
                ot = new RotationTransformer();
                transformationMatrix = ot.apply(-Math.PI / 32, 0, 0);
                moveToCentroid = true;
                break;
            case (KeyEvent.VK_S):
                ot = new RotationTransformer();
                transformationMatrix = ot.apply(Math.PI / 32, 0, 0);
                moveToCentroid = true;
                break;
        }
        if (transformationMatrix != null) {
            if (moveToCentroid) {
                Point centroid = Point.getCentroid(points);
                Point[] centeredPoints = centroid.translateHere(points);
                centeredPoints = Util.applyTransformation(centeredPoints, transformationMatrix);
                points = centroid.translateAway(centeredPoints);
            } else {
                points = Util.applyTransformation(points, transformationMatrix);
            }
            repaint();
        }
    }

    public void keyReleased(KeyEvent ke) { }

}
