package ksr.grupa3;

import java.io.IOException;
import org.mariuszgromada.math.mxparser.*;

public class App {
    public static void main(String[] args) throws IOException {
        Argument x = new Argument("x = 5");
        Expression e = new Expression("0", x);
        double res = e.calculate();
        System.out.println(res);
    }
}