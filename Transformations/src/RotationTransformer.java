
/**
 *
 * @author Enrique Arango Lyons
 */
public class RotationTransformer implements ObjectTransformer {

    public double[][] apply(double theta) {
        double[][] answer = new double[3][3];
        answer[0][0] =   Math.cos(theta);
        answer[0][1] = - Math.sin(theta);
        answer[1][0] =   Math.sin(theta);
        answer[1][1] =   Math.cos(theta);
        answer[2][2] =   1;
        return answer;
    }

    public double[][] apply(double arg0, double arg1) {
        throw new UnsupportedOperationException("Not supported.");
    }

}
