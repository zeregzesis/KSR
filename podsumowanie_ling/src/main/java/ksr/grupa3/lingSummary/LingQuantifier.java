package ksr.grupa3.lingSummary;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LingQuantifier {
    private String name;
    private QuantifierType quantifierType;

    /*
    public double compatibility(double value){
        switch (quantifierType){
            case ALMOST_NONE:
                return Math.exp(-Math.pow(12.5 * value, 2));
            case SOME:
                return Math.exp(-Math.pow(12.5 * value - 3.125, 2));
            case ABOUT_HALF:
                return Math.exp(-Math.pow(12.5 * value - 6.25, 2));
            case MOST:
                return Math.exp(-Math.pow(12.5 * value - 9.375, 2));
            case ALMOST_ALL:
                return Math.exp(-Math.pow(12.5 * value - 12.5, 2));
            case MUCH_MORE_THAN_500:
                if (value < 500)
                    return 0;
                else if (value > 2000)
                    return 1;
                else
                    return (2.0/3000.0) * value - (1.0/3.0);
            case ABOUT_FEW_THOUSAND:
                if (value < 4000)
                    return Math.exp(-Math.pow(0.001 * value - 4, 2));
                else if (value > 6000)
                    return Math.exp(-Math.pow(0.001 * value - 6, 2));
                else
                    return 1;
            case MUCH_LESS_THAN_8000:
                if (value > 8000)
                    return 0;
                else if (value < 6000)
                    return 1;
                else
                    return 0.0005 * value + 4;
            default:
                return 0;
        }
    }*/
}
