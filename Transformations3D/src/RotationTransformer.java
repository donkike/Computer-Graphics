
/**
 *
 * @author Enrique Arango Lyons
 */
public class RotationTransformer implements ObjectTransformer {

    public double[][] apply(double theta) {
        throw new UnsupportedOperationException("Not supported.");
    }

    public double[][] apply(double thetaX, double thetaY, double thetaZ) {
        double[][] answer = null;
        double[][] tempAnswer = null;
        if (Double.compare(thetaX, 0) != 0) {
            answer = new double[4][4];
            answer[0][0] = 1;
            answer[1][1] = Math.cos(thetaX);
            answer[1][2] = -Math.sin(thetaX);
            answer[2][1] = Math.sin(thetaX);
            answer[2][2] = Math.cos(thetaX);
            answer[3][3] = 1;
        }
        if (Double.compare(thetaY, 0) != 0) {
            tempAnswer = new double[4][4];
            tempAnswer[0][0] = Math.cos(thetaY);
            tempAnswer[0][2] = Math.sin(thetaY);
            tempAnswer[1][1] = 1;
            tempAnswer[2][0] = -Math.sin(thetaY);
            tempAnswer[2][2] = Math.cos(thetaY);
            tempAnswer[3][3] = 1;
            if (answer != null) {
                answer = Util.multiply(answer, tempAnswer);
            } else {
                answer = tempAnswer;
            }
        }
        if (Double.compare(thetaZ, 0) != 0) {
            tempAnswer = new double[4][4];
            tempAnswer[0][0] = Math.cos(thetaZ);
            tempAnswer[0][1] = -Math.sin(thetaZ);
            tempAnswer[1][0] = Math.sin(thetaZ);
            tempAnswer[1][1] = Math.cos(thetaZ);
            tempAnswer[2][2] = 1;
            tempAnswer[3][3] = 1;
            if (answer != null) {
                answer = Util.multiply(answer, tempAnswer);
            } else {
                answer = tempAnswer;
            }
        }
        return answer;
    }

}
