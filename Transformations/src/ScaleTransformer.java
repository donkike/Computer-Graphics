
/**
 *
 * @author Enrique Arango Lyons
 */
public class ScaleTransformer implements ObjectTransformer {

    public double[][] apply(double s) {
        return apply(s, s);
    }

    public double[][] apply(double sx, double sy) {
        double[][] answer = new double[3][3];
        answer[0][0] =   sx;
        answer[1][1] =   sy;
        answer[2][2] =   1;
        return answer;
    }

}
