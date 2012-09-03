
/**
 *
 * @author Enrique Arango Lyons
 */
public class TranslationTransformer implements ObjectTransformer {

    public double[][] apply(double d) {
        return apply(d, d, d);
    }

    public double[][] apply(double dx, double dy, double dz) {
        double[][] answer = new double[4][4];
        answer[0][0] =   1;
        answer[1][1] =   1;
        answer[2][2] =   1;
        answer[0][3] =  dx;
        answer[1][3] =  dy;
        answer[2][3] =  dz;
        answer[3][3] =   1;
        return answer;
    }

}
