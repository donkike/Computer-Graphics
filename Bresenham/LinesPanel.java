import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Program that draws consecutive lines using Bresenham's algorithm
 * @author Enrique Arango Lyons
 */
public class LinesPanel extends JPanel {

    // Panel's height
    private int h;

    // Panel's width
    private int w;

    // Space between consecutive lines
    private int delta;

    /**
     * Constructor
     * @param delta space between consecutive lines
     */
    public LinesPanel(int delta) {
        super();
        this.delta = delta;
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Dimension size = getSize();
      Insets insets = getInsets();

      Graphics2D g2d = (Graphics2D) g;

      // Sets height and width for current panel
      w =  size.width - insets.left - insets.right;
      h =  size.height - insets.top - insets.bottom;

      // Set background color
      g2d.setColor(Color.lightGray);
      g2d.fillRect(0, 0, w, h);

      // Draws lines for every pixel
      g.setColor(Color.red);
      for (int i = 0; i < w; i++) {
          drawLine(g2d, i, 0, w, i + 1);
      }

      // Draws lines for specified delta
      g.setColor(Color.blue);
      for (int i = 0; i < w; i += delta) {
          drawLine(g2d, i, 0, w, i + 1);
      }

      // Draws lines in first and second octant
      g.setColor(Color.magenta);
      for(int i = 0; i < 400; i += 10) {
          drawLine(g2d, 100, 150, 500 - i, 150 + i);
      }
    }

    /**
     * Draws a point on (x, y)
     * @param g the Graphics object to draw on
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public void drawPoint(Graphics g, int x, int y) {
        // Coordinates must be converted to simulate the Cartesian plane
        // (the screen's y-coordinate is inverted)
        g.drawLine(x, h - y, x,  h - y);
    }

    /**
     * Draws a line from (x1, y1) to (x2, y2); all values must be positive
     * @param g  the Graphics object to draw on
     * @param x1 first x-coordinate
     * @param y1 first y-coordinate
     * @param x2 second x-coordinate
     * @param y2 second y-coordinate
     */
    public void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
        int dy = y2 - y1;
        int dx = x2 - x1;
        int inc1, inc2, d;
        
        // Check whether the line is in the first or second octant
        if (dy <= dx) {
            inc1 = 2 * dy;
            inc2 = 2 * dy - 2 * dx;
            d = 2 * dy - dx;
            for (int x = x1, y = y1; x <= x2; x++) {
                drawPoint(g, x, y);
                if (d <= 0) {
                    d += inc1;
                } else {
                    d += inc2;
                    y++;
                }
            }        
        } else {
            inc1 = 2 * dx;
            inc2 = 2 * dx - 2 * dy;
            d = 2 * dx - dy;
            for (int x = x1, y = y1; y <= y2; y++) {
                drawPoint(g, x, y);
                if (d <= 0) {
                    d += inc1;
                } else {
                    d += inc2;
                    x++;
                }
            } 
        }
        
    }

    /**
     * 
     * @param args first argument can optionally be the delta to use; defaults to 10
     */
    public static void main(String[] args) {
        int delta;
        if (args.length > 0) delta = Integer.parseInt(args[0]);
        else delta = 10;

        JFrame frame = new JFrame("Lines");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new LinesPanel(delta));
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
