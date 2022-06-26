package ksr.grupa3.gui;

import ksr.grupa3.ling.Summary;

import java.util.List;

public class SummaryObs {
    private String sentence;
    private double T1;
    private double T2;
    private double T3;
    private double T4;
    private double T5;

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public double getT1() {
        return T1;
    }

    public void setT1(double t1) {
        T1 = t1;
    }

    public double getT2() {
        return T2;
    }

    public void setT2(double t2) {
        T2 = t2;
    }

    public double getT3() {
        return T3;
    }

    public void setT3(double t3) {
        T3 = t3;
    }

    public double getT4() {
        return T4;
    }

    public void setT4(double t4) {
        T4 = t4;
    }

    public double getT5() {
        return T5;
    }

    public void setT5(double t5) {
        T5 = t5;
    }

    public double getT6() {
        return T6;
    }

    public void setT6(double t6) {
        T6 = t6;
    }

    public double getT7() {
        return T7;
    }

    public void setT7(double t7) {
        T7 = t7;
    }

    public double getT8() {
        return T8;
    }

    public void setT8(double t8) {
        T8 = t8;
    }

    public double getT9() {
        return T9;
    }

    public void setT9(double t9) {
        T9 = t9;
    }

    public double getT10() {
        return T10;
    }

    public void setT10(double t10) {
        T10 = t10;
    }

    public double getT11() {
        return T11;
    }

    public void setT11(double t11) {
        T11 = t11;
    }

    public double getOS() {
        return OS;
    }

    public void setOS(double OS) {
        this.OS = OS;
    }

    public double getEOS() {
        return EOS;
    }

    public void setEOS(double EOS) {
        this.EOS = EOS;
    }

    private double T6;
    private double T7;
    private double T8;
    private double T9;
    private double T10;
    private double T11;
    private double OS;
    private double EOS;

    public SummaryObs(Summary summary, List<Double> weights, List<Double> extweights) {

        List<Double> measures = summary.generateSummaryMeasures(weights, extweights);
        if (measures.size() > 1) {
            T1 = measures.get(0);
            T2 = measures.get(1);
            T3 = measures.get(2);
            T4 = measures.get(3);
            T5 = measures.get(4);
            T6 = measures.get(5);
            T7 = measures.get(6);
            T8 = measures.get(7);
            T9 = measures.get(8);
            T10 = measures.get(9);
            T11 = measures.get(10);
            OS = measures.get(11);
            EOS = measures.get(12);
            sentence = summary.generateSummaryString();
        } else {
            T1 = measures.get(0);
            T2 = -1;
            T3 = -1;
            T4 = -1;
            T5 = -1;
            T6 = -1;
            T7 = -1;
            T8 = -1;
            T9 = -1;
            T10 = -1;
            T11 = -1;
            OS = -1;
            EOS = -1;
            sentence = summary.generateSummaryString();
        }


    }
    public String toString(){
        return sentence+" "+T1+" "+T2+" "+T3+" "+T4+" "+T5+" "+T6+" "+T7+" "+T8+" "+T9+" "+T10+" "+T11+" "+OS+" "+EOS;
    }
}