package ksr.grupa3;

import java.util.Map;
import java.lang.*;
import java.util.*;


public class ManhattanMetric extends Metric{
    double calculate_distance_between_properties(Properties v1, Properties v2, TextSimilarityMeasure fun){
        double numsum=0;

        List<Integer> v1tmpnum=v1.getNumericValuesAsList();
        List<Integer> v2tmpnum=v2.getNumericValuesAsList();


        List<String> v1tmpstr=v1.getStringValuesAsList();
        List<String> v2tmpstr=v2.getStringValuesAsList();

        int i=0;
        while(i<v1tmpnum.size()){
                numsum+=Math.abs(v1tmpnum.get(i)-v2tmpnum.get(i));
            i++;
        }

        double textsum=0;
       
        while(i<v1tmpstr.size()){
                double d= fun.sim(v1tmpstr.get(i),v2tmpstr.get(i)) ;
                numsum+=Math.abs(d);
            i++;
        }

        return  numsum+textsum;

	}

    }