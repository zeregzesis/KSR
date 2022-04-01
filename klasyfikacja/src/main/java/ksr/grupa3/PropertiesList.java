package ksr.grupa3;

import java.util.*;

import lombok.Data;

@Data
public class PropertiesList {
    protected List<Properties> propertiesList;

    public PropertiesList(List<Properties> l) {
        this.propertiesList = l;
    }

    public PropertiesList() {
        this.propertiesList = new ArrayList<Properties>();
    }

    void shuffle() {
        Collections.shuffle(propertiesList, new Random(42));
    }

    public List<PropertiesList> createSets(double n) {
        shuffle();
        List<PropertiesList> result = new ArrayList<PropertiesList>();
        PropertiesList learn = new PropertiesList();
        PropertiesList test = new PropertiesList();
        int splitPoint = (int) (propertiesList.size() * n);
        for (int i = 0; i < splitPoint; i++) {
            learn.propertiesList.add(propertiesList.get(i));
        }
        for (int i = splitPoint; i < propertiesList.size(); i++) {
            test.propertiesList.add(propertiesList.get(i));
        }

        result.add(learn);
        result.add(test);

        return result;
    }

    int size() {
        return propertiesList.size();
    }

    void add(Properties p) {
        propertiesList.add(p);
    }

}