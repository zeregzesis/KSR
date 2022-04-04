package ksr.grupa3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVappender {
    File f;
    FileWriter csvfile;

    public CSVappender(String csvfilepath) throws IOException {
        f=new File(csvfilepath);
        csvfile=new FileWriter(csvfilepath, true);
    }   


    public void append(ConfusionMatrix confmat,int k, String metric, String similarityMeasure, double trainSetSize) {
        try {
            //appendHeadline();
            // if(!f.exists() || f.length()==0){
            //     appendHeadline();
            // }
            
            csvfile.append(String.valueOf(k));
            csvfile.append(",");
            csvfile.append(metric);
            csvfile.append(",");
            csvfile.append(similarityMeasure);
            csvfile.append(",");
            csvfile.append(String.valueOf(trainSetSize));
            csvfile.append(",");


            List<Double> results = confmat.generateResults();
            for (int i = 0; i < results.size(); i++) {
                csvfile.append(results.get(i).toString());
                csvfile.append(",");
            }

            csvfile.append("\n");
            csvfile.close();
            //System.out.println("CSV file created succesfully.");
        } catch (Exception e) {
            System.out.println("exception :" + e.getMessage());
        }
    }
    public void finalize() {
        try {
            csvfile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendHeadline() {
        try {
            csvfile.append("k");
            csvfile.append(",");
            csvfile.append("Metric");
            csvfile.append(",");
            csvfile.append("TextSimMeasure");
            csvfile.append(",");
            csvfile.append("trainSetSize");
            csvfile.append(",");
            csvfile.append("accuracy");
            csvfile.append(",");
            csvfile.append("precision_for_classification");
            csvfile.append(",");
            csvfile.append("recall_for_classification");
            csvfile.append(",");
            csvfile.append("f1score_for_classification");
            csvfile.append(",");
            for (Places place : Places.values()) {
                String str = place + "_precision";
                csvfile.append(str);
                csvfile.append(",");
                str = place + "_recall";
                csvfile.append(str);
                csvfile.append(",");
                str = place + "_f1score";
                csvfile.append(str);
                csvfile.append(",");
            }
            csvfile.append("\n");
            //csvfile.close();
           // System.out.println("CSV file created succesfully.");
        } catch (Exception e) {
            System.out.println("exception :" + e.getMessage());
        }
    }

}
