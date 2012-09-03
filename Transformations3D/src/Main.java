
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Enrique Arango Lyons
 */
public class Main {
    
    public static Point[] points;
    public static boolean[][] lines;
    
    public static void readInput() throws IOException {
        URL url = Main.class.getResource("input.txt");
        BufferedReader br = new BufferedReader(new FileReader(url.getPath()));
        int numPoints = Integer.parseInt(br.readLine());
        points = new Point[numPoints];
        lines = new boolean[numPoints][numPoints];
        for(int i = 0; i < numPoints; i++) {
            String[] elements = br.readLine().split(" ");
            int x = Integer.parseInt(elements[0]), y = Integer.parseInt(elements[1]), z = Integer.parseInt(elements[2]);
            points[i] = new Point(x, y, z);
        }
        int numLines = Integer.parseInt(br.readLine());
        for (int i = 0; i < numLines; i++) {
            String[] elements = br.readLine().split(" ");
            int p1 = Integer.parseInt(elements[0]), p2 = Integer.parseInt(elements[1]);
            lines[p1][p2] = true;
        }
    }
    
    public static void main(String[] args) {
        try {
            readInput();
        } catch(IOException ioe) {
            System.out.println("Error reading input file:");
            ioe.printStackTrace();
        }

        JFrame mainFrame = new JFrame("Transformations");
        mainFrame.setSize(800, 800);

        DrawPanel drawPanel = new DrawPanel(points, lines, 100);
        mainFrame.add(drawPanel);
        mainFrame.addKeyListener(drawPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

}
