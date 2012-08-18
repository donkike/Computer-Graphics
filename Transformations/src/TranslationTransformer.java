
/**
 *
 * @author Enrique Arango Lyons
 */
public class TranslationTransformer implements ObjectTransformer {

    public double[][] apply(double d) {
        return apply(d, d);
    }

    public double[][] apply(double dx, double dy) {
        double[][] answer = new double[3][3];
        answer[0][0] =   1;
        answer[1][1] =   1;
        answer[0][2] =  dx;
        answer[1][2] =  dy;
        answer[2][2] =   1;
        return answer;
    }

}
