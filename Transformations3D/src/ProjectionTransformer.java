
/**
 *
 * @author Enrique Arango Lyons
 */
public class ProjectionTransformer implements ObjectTransformer {

    public double[][] apply(double d) {
        double[][] answer = new double[4][4];
        answer[0][0] =   1;
        answer[1][1] =   1;
        answer[2][2] =   1;
        answer[3][2] = 1 / d;
        return answer;
    }

    public double[][] apply(double arg0, double arg1, double arg2) {
        throw new UnsupportedOperationException("Not supported.");
    }

}
