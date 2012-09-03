
/**
 *
 * @author Enrique Arango Lyons
 */
public class ScaleTransformer implements ObjectTransformer {

    public double[][] apply(double s) {
        return apply(s, s, s);
    }

    public double[][] apply(double sx, double sy, double sz) {
        double[][] answer = new double[4][4];
        answer[0][0] =   sx;
        answer[1][1] =   sy;
        answer[2][2] =   sz;
        answer[3][3] =   1;
        return answer;
    }

}
